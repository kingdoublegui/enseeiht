public class IncrSeq {

    static long cpt = 0L;
    static final long NB_IT = 1000L;

    public static void main(String[] args) {
        for (int i = 0; i < IncrSeq.NB_IT; i++) {
        	// boucle imbriquée pour permettre (éventuellement) de tester différents
        	// grains de synchronisation
            for (int j = 1; j<=1000000; j++) {
            	// incrémentation tordue pour éviter l'optimisation classique
            	// du compilateur consistant à remplacer une boucle for par son résultat
                IncrSeq.cpt=IncrSeq.cpt + j/j;
            }
            // Attente de 1 ms pour éviter l'utilisation du cache
            // Vous pouvez  (éventuellement) commenter l'attente 
            // pour voir l'effet (intéressant) du mécanisme de cache
            try {
                Thread.sleep(1,0);
            }
            catch (InterruptedException ie)
            {
                System.out.println("InterruptedException : " + ie);
            }
        }
    }
}