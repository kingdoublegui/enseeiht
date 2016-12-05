//v0.2 8/10/16 (PM)
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.concurrent.ExecutionException;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

class TriLocal  implements Callable<int[]> {
    private int début;
    private int fin;
    private int[] tableau;
    private int[] résultat;

    TriLocal(int[] t, int d, int f) {
        début = d;
        fin = f;
        tableau = t;
    }

    @Override
    public	int[] call() {
        return TF.traiterTronçon(tableau, début, fin) ;
    }
}

class TraiterFragment extends RecursiveTask<int[]> {
    private int début;
    private int fin;
    private int[] tableau;
    int[] resTri=null;

    private int max = 0;

    TraiterFragment(int[] t, int d, int f) {
        début = d;
        fin = f;
        tableau = t;
    }

    @Override
    protected int[] compute() {
        int taille;
        //si la tâche est trop grosse, on la décompose en 2
// ********* A compléter
    	return resTri;
    }
}

public class TF {
    static int seuil;

    static int[] fusion(int[] t1, int[]t2) {
        int [] résultat = new int[t1.length+t2.length];
        int i1=0;
        int i2=0;
        int iR=0;

        while ((iR<résultat.length) && (i1<t1.length) && (i2<t2.length)) {
            if (t1[i1]<t2[i2]) {
                résultat[iR]=t1[i1];
                i1++;
            } else {
                résultat[iR]=t2[i2];
                i2++;
            }
            iR++;
        }
        while (i1<t1.length) {
            résultat[iR]=t1[i1];
            i1++;
            iR++;
        }
        while (i2<t2.length) {
            résultat[iR]=t2[i2];
            i2++;
            iR++;
        }
        return résultat;
    }

    static int[] traiterTronçon(int[] t, int début, int fin) {
        int taille;
        int[] resTri;
        //si le fragment est trop gros, on le décompose en 2
        if((fin-début+1) > TF.seuil) {
            taille = (fin-début+1)/2;
            return TF.fusion(traiterTronçon(t, début, début+taille-1),
            										traiterTronçon(t, début+taille, fin));
        } else { //traitement direct : quicksort
            resTri = Arrays.copyOfRange(t, début,fin+1);
            Arrays.sort(resTri);
            return resTri;
        }
    }

    static int[] TFMono(int[] t) {
        return traiterTronçon(t, 0, t.length-1);
    }

    static int[] TFPool(ExecutorService xs, int[] t, int nbT)
    throws InterruptedException, ExecutionException {
        int d; 									//indice de départ d'un tri local
        int f = 0; 								//indice de fin d'un tri local
        int grain = Math.max(1,t.length/nbT); 	/*taille d'une recherche locale 
        										 * = taille du tableau/nombre de tâches lancées
        										 * (ou 1 dans le cas (aberrant) où il y a plus
        										 * de tâches que d'éléments du tableau) */
        
        List<Future<int[]>> résultats = new ArrayList<Future<int[]>>(nbT);
        List<int[]> resTri = new LinkedList<int[]>(); //recueille et fusionne les résultats

        //soumettre les tâches
// ********* A compléter
        //récupérer et fusionner les résultats.
// ********* A compléter
		return null; // *********** A corriger
    }

    static int[] TFForkJoin(ForkJoinPool f, int[] t) {
        TraiterFragment tout = new TraiterFragment(t,0, t.length-1);
        int[] resTri = null; // ********* A corriger
        return resTri;
    }

    public static void main(String[] args)
    throws InterruptedException, ExecutionException, IOException, FileNotFoundException {

        int nbOuvriersPool=0; // nb ouvriers du pool fixe. Bonne valeur : nb de processeurs disponibles
        int nbEssais=0;
        int nbTâches=0;
        int tailleTronçon=-0; //nombre d'éléments du tableau traités par chaque ouvrier
        String chemin="";
        int[] tableau;
        long départ, fin;
        int [] resTri;

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
                throw new IllegalArgumentException("Usage : TF <fichier> <nb essais> "+
                                                   "<nb tâches (pool)> <taille tronçon (FJ, Mono)> <nb ouvriers du pool (pool)>");
            }
        }
        if ((nbEssais<1) || (nbTâches<1) || (tailleTronçon<1) || (nbOuvriersPool<1)
                || !Files.isRegularFile(Paths.get(chemin), LinkOption.NOFOLLOW_LINKS))
            throw new IllegalArgumentException("Usage : TF <fichier> <nb essais> "
                                                + "<nb tâches (pool)> "
                                                + "<taille tronçon (FJ, Mono)> "
                                                + "<nb ouvriers du pool (pool)>");
        //appel correct
        tempsMono = new long[nbEssais];
        tempsPool = new long[nbEssais];
        tempsForkJoin = new long[nbEssais];
        tableau=TableauxDisque.charger(chemin);
        TF.seuil=tailleTronçon;

        System.out.println(Runtime.getRuntime().availableProcessors()+" processeurs disponibles pour la JVM");

        //créer un pool avec un nombre fixe d'ouvriers
        ExecutorService poule = Executors.newFixedThreadPool(nbOuvriersPool);

        //créer un pool ForkJoin
        ForkJoinPool fjp = new ForkJoinPool();

        for (int i = 0; i < nbEssais; i++) {
            départ = System.nanoTime();
            resTri = TFMono(tableau);
            fin = System.nanoTime();
            tempsMono[i] = (fin - départ);
            TableauxDisque.sauver(chemin+"triéMono",resTri);
            System.out.println("Essai ["+i+"] : durée (mono) " + tempsMono[i]/1_000+"µs");
        }
        System.out.println("--------------------");

        for (int i = 0; i < nbEssais; i++) {
            départ = System.nanoTime();
            resTri = TFPool(poule, tableau, nbTâches);
            fin = System.nanoTime();
            tempsPool[i] = (fin - départ);
            TableauxDisque.sauver(chemin+"triéPF",resTri);
            System.out.println("Essai ["+i+"] : durée (PF) " + tempsPool[i]/1_000+"µs");
        }
        poule.shutdown();
        System.out.println("--------------------");

        for (int i = 0; i < nbEssais; i++) {
            départ = System.nanoTime();
            resTri = TFForkJoin(fjp,tableau);
            fin = System.nanoTime();
            tempsForkJoin[i] = (fin - départ);
            TableauxDisque.sauver(chemin+"triéFJ",resTri);
            System.out.println("Essai ["+i+"] : durée (FJ) "+tempsForkJoin[i]/1_000+"µs");
        }
        System.out.println("--------------------");
    }
}