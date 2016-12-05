//v0.1 8/10/16 (PM)

import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.concurrent.ExecutionException;

import java.util.List;
import java.util.LinkedList;

import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

class MaxLocal  implements Callable<Integer> {
    private int debut;
    private int fin;
    private int[] tableau;

    MaxLocal(int[] t, int d, int f) {
        debut = d;
        fin = f;
        tableau = t;
    }

    @Override
    public	Integer call() { // le résultat doit être un objet
        int ml = 0;
        for (int i = debut; i < fin; i++) {
            ml = ml>tableau[i] ? ml : tableau[i];
        }
        return new Integer(ml);
    }
}

class TraiterFragment extends RecursiveTask<Integer> {
    private int debut;
    private int fin;
    private int[] tableau;
    static int seuil;

    private int max = 0;

    TraiterFragment(int[] t, int d, int f) {
        debut = d;
        fin = f;
        tableau = t;
    }

    protected Integer compute() {
        // si la tâche est trop grosse, on la décompose en 2
        if (fin - debut == 1) {
            return tableau[debut];
        } else if (fin - debut == 0) {
            return 0;
        }
        int middle = Math.round((fin+debut)/2);
        TraiterFragment sp1 = new TraiterFragment(tableau, debut, middle);
        TraiterFragment sp2 = new TraiterFragment(tableau, middle, fin);

        sp1.fork();
        int max2 = sp2.compute();
        int max1 = sp1.join();

        return max1>max2 ? max1 : max2;
    }
}

public class MaxTab {

    static int maxMono(int[] t) {
        int max = 0;
        for (int i = 0; i <t.length; i++) {
            max = Math.max(t[i], max);
        }
        return max;
    }

    static int maxPoolFixe(ExecutorService xs, int[] t, int nbT)
    throws InterruptedException, ExecutionException {
        int max = 0;
        int d;									//indice de départ d'une recherche locale
        int f = 0;  							//indice de fin d'une recherche locale
        int grain = Math.max(1,t.length/nbT); 	/*taille d'une recherche locale 
        										 * = taille du tableau/nombre de tâches lancées
        										 * (ou 1 dans le cas (aberrant) où il y a plus
        										 * de tâches que d'éléments du tableau) */
        List<Future<Integer>> resultats=new LinkedList<Future<Integer>>();

        //soumettre les tâches
        for (int iGrain = 0; iGrain*grain < t.length; iGrain++) {
            int debut = iGrain*grain;
            int fin = Math.min(t.length, (iGrain+1)*grain);
            resultats.add(xs.submit(new MaxLocal(t, debut, fin)));
        }

        //recuperer les résultats
        for (Future<Integer> curMax : resultats) {
            max = max > curMax.get() ? max : curMax.get(); 
        }
        return max;
    }

    static int maxForkJoin(ForkJoinPool f, int[] t) {
        TraiterFragment tout = new TraiterFragment(t,0, t.length);
        int max = tout.invoke();
        return max;
    }

    public static void main(String[] args)
    throws InterruptedException, ExecutionException, IOException, FileNotFoundException {

        
    	 
        int nbOuvriersPool=0; // nb ouvriers du pool fixe. Bonne valeur : nb de processeurs disponibles
        int nbEssais=-1;
        int nbTâches=-1;
        int tailleTronçon=-1; //nombre d'éléments du tableau traités par chaque ouvrier
        String chemin="";
        int[] tableau;
        long départ, fin;
        int max;

        long[] tempsMono, tempsPool,tempsForkJoin;

        if (args.length == 5) { //analyse des paramètres
            chemin = args[0];
            try {
                nbEssais = Integer.parseInt (args[1]);
                nbTâches = Integer.parseInt (args[2]);
                tailleTronçon = Integer.parseInt (args[3]);
                nbOuvriersPool = Integer.parseInt (args[4]);
            }
            catch (NumberFormatException nfx) {
                throw new IllegalArgumentException("Usage : MaxTab <fichier> <nb essais> "+
                                              	"<nb tâches (pool)> <taille tronçon (FJ)>"
                                                + " <nb ouvriers du pool (pool)>");            }
        }
        if ((nbEssais<1) || (nbTâches<1) || (tailleTronçon<1) || (nbOuvriersPool<1)
                || !Files.isRegularFile(Paths.get(chemin), LinkOption.NOFOLLOW_LINKS))
            throw new IllegalArgumentException("Usage : MaxTab <fichier> <nb essais> " +
                                            	"<nb tâches (pool)> <taille tronçon (FJ)>"
                                                + " <nb ouvriers du pool (pool)>");
        //appel correct
        tempsMono = new long[nbEssais];
        tempsPool = new long[nbEssais];
        tempsForkJoin = new long[nbEssais];
        tableau=TableauxDisque.charger(chemin);
        System.out.println(Runtime.getRuntime().availableProcessors()+" processeurs disponibles pour la JVM");

        //créer un pool avec un nombre fixe d'ouvriers
        ExecutorService poule = Executors.newFixedThreadPool(nbOuvriersPool);

        //créer un pool ForkJoin
        ForkJoinPool fjp = new ForkJoinPool();
        TraiterFragment.seuil=tailleTronçon;

        for (int i = 0; i < nbEssais; i++) {
            départ = System.nanoTime();
            max = maxMono(tableau);
            fin = System.nanoTime();
            tempsMono[i] = (fin - départ);
            System.out.println("Essai ["+i+"] : maximum = " + max+", durée (mono) " +
                               tempsMono[i]/1_000+ "µs");
        }
        System.out.println("--------------------");

        for (int i = 0; i < nbEssais; i++) {
            départ = System.nanoTime();
            max = maxPoolFixe(poule, tableau, nbTâches);
            fin = System.nanoTime();
            tempsPool[i] = (fin - départ);
            System.out.println("Essai ["+i+"] : maximum = " + max+", durée (pool) " +
                               tempsPool[i]/1_000+ "µs");
        }
        poule.shutdown();
        System.out.println("--------------------");

        for (int i = 0; i < nbEssais; i++) {
            départ = System.nanoTime();
            max = maxForkJoin(fjp,tableau);
            fin = System.nanoTime();
            tempsForkJoin[i] = (fin - départ);
            System.out.println("Essai ["+i+"] : maximum = " + max+", durée (FJ) " +
                               tempsForkJoin[i]/1_000+ "µs");
        }
        System.out.println("--------------------");
    }
}
