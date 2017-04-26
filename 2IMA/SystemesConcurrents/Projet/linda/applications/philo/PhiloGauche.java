package applications.philo;

// Time-stamp: <08 déc 2009 08:30 queinnec@enseeiht.fr>

import outils.Semaphore;

public class PhiloGauche implements StrategiePhilo {

    /****************************************************************/

    Semaphore[] fourchettes;

    public PhiloGauche (int nbPhilosophes) {
        fourchettes = new Semaphore[nbPhilosophes];
        for (int iSemaphore = 0; iSemaphore < nbPhilosophes; iSemaphore++)
            fourchettes[iSemaphore] = new Semaphore(1);
    }

    /** Le philosophe no demande les fourchettes.
     *  Précondition : il n'en possède aucune.
     *  Postcondition : quand cette méthode retourne, il possède les deux fourchettes adjacentes à son assiette. */
    public void demanderFourchettes (int no) throws InterruptedException {
        int fourchetteGauche = Main.FourchetteGauche(no);
        int fourchetteDroite = Main.FourchetteDroite(no);

        if (no == 0) {
            fourchettes[fourchetteGauche].P();
            IHMPhilo.poser(fourchetteGauche, EtatFourchette.AssietteDroite);
        }

        fourchettes[fourchetteDroite].P();
        IHMPhilo.poser(fourchetteDroite, EtatFourchette.AssietteGauche);

        if (no != 0) {
            fourchettes[fourchetteGauche].P();
            IHMPhilo.poser(fourchetteGauche, EtatFourchette.AssietteDroite);
        }
    }

    /** Le philosophe no rend les fourchettes.
     *  Précondition : il possède les deux fourchettes adjacentes à son assiette.
     *  Postcondition : il n'en possède aucune. Les fourchettes peuvent être libres ou réattribuées à un autre philosophe. */
    public void libererFourchettes (int no) {
        int fourchetteGauche = Main.FourchetteGauche(no);
        int fourchetteDroite = Main.FourchetteDroite(no);
        fourchettes[fourchetteGauche].V();
        IHMPhilo.poser(fourchetteGauche, EtatFourchette.Table);
        fourchettes[fourchetteDroite].V();
        IHMPhilo.poser(fourchetteDroite, EtatFourchette.Table);
    }

    /** Nom de cette stratégie (pour la fenêtre d'affichage). */
    public String nom() {
        return "Implantation Sémaphores, stratégie du gaucher";
    }

}

