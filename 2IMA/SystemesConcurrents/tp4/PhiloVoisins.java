// Time-stamp: <08 déc 2009 08:30 queinnec@enseeiht.fr>

import java.util.concurrent.Semaphore;

public class PhiloVoisins implements StrategiePhilo {

    /****************************************************************/

    Semaphore[] philosophes;
    EtatPhilosophe[] etats;

    public PhiloVoisins (int nbPhilosophes) {
        philosophes = new Semaphore[nbPhilosophes];
        etats = new EtatPhilosophe[nbPhilosophes];
        for (int iPhilosophe = 0; iPhilosophe < nbPhilosophes; iPhilosophe++) {
            philosophes[iPhilosophe] = new Semaphore(0);
            etats[iPhilosophe] = EtatPhilosophe.Pense;
        }
    }

    /** Le philosophe no demande les fourchettes.
     *  Précondition : il n'en possède aucune.
     *  Postcondition : quand cette méthode retourne, il possède les deux fourchettes adjacentes à son assiette. */
    public void demanderFourchettes (int no) throws InterruptedException {
        synchronized (philosophes) {
            int fourchetteGauche = Main.FourchetteGauche(no);
            int fourchetteDroite = Main.FourchetteDroite(no);
            if (peutManger(no)) {
                philosophes[no].release();
                etats[no] = EtatPhilosophe.Mange;
                IHMPhilo.poser(fourchetteGauche, EtatFourchette.AssietteDroite);
                IHMPhilo.poser(fourchetteDroite, EtatFourchette.AssietteGauche);
            } else {
                etats[no] = EtatPhilosophe.Demande;
                philosophes[no].acquire();
            }
        }
    }

    /** Le philosophe no rend les fourchettes.
     *  Précondition : il possède les deux fourchettes adjacentes à son assiette.
     *  Postcondition : il n'en possède aucune. Les fourchettes peuvent être libres ou réattribuées à un autre philosophe. */
    public void libererFourchettes (int no) {
        synchronized(philosophes) {
            etats[no] = EtatPhilosophe.Pense;
            int philoGauche = Main.PhiloGauche(no);
            int philoDroite = Main.PhiloDroite(no);
            int fourchetteGauche = Main.FourchetteGauche(no);
            int fourchetteDroite = Main.FourchetteDroite(no);

            IHMPhilo.poser(fourchetteGauche, EtatFourchette.Table);
            IHMPhilo.poser(fourchetteDroite, EtatFourchette.Table);
            if (etats[philoGauche] == EtatPhilosophe.Demande
                    && peutManger(philoGauche)) {
                etats[philoGauche] = EtatPhilosophe.Mange;
                philosophes[philoGauche].release();
                IHMPhilo.poser(Main.FourchetteGauche(philoGauche), EtatFourchette.AssietteDroite);
                IHMPhilo.poser(fourchetteGauche, EtatFourchette.AssietteGauche);
            }
            if (etats[philoDroite] == EtatPhilosophe.Demande
                    && peutManger(philoDroite)) {
                etats[philoDroite] = EtatPhilosophe.Mange;
                philosophes[philoDroite].release();
                IHMPhilo.poser(fourchetteDroite, EtatFourchette.AssietteDroite);
                IHMPhilo.poser(Main.FourchetteDroite(philoDroite), EtatFourchette.AssietteGauche);
            }
        }
    }

    /** Nom de cette stratégie (pour la fenêtre d'affichage). */
    public String nom() {
        return "Implantation Sémaphores, stratégie des fourchettes metaphysiques";
    }

    private final boolean peutManger(int no) {
        return etats[Main.PhiloGauche(no)] != EtatPhilosophe.Mange
        && etats[Main.PhiloDroite(no)] != EtatPhilosophe.Mange;
    }

}

