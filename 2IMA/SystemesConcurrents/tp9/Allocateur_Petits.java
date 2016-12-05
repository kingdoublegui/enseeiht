// Time-stamp: <10 jan 2011 13:36 queinnec@enseeiht.fr>

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

/** Allocateur de ressources,
 * stratégie d'ordonnancement: priorité aux petits demandeurs,
 *
 * Implantation: moniteur (java 5), une var condition par taille de demande.
 */
public class Allocateur_Petits implements Allocateur {

    // Nombre total de ressources.
    private final int nbRessources;

    // Nombre de ressources actuellement disponibles
    // invariant 0 <= nbLibres <= nbRessources
    private int nbLibres;

    // Protection des variables partagées
    private Lock moniteur;

    // Une condition de blocage par taille de demande
    // tableau [nbRessources+1] dont on n'utilise pas la case 0
    private Condition[] classe; 

    // Le nombre de processus en attente à chaque étage
    // tableau [nbRessources+1] dont on n'utilise pas la case 0
    private int[] tailleClasse;

    /** Initilialise un nouveau gestionnaire de ressources pour nbRessources. */
    public Allocateur_Petits (int nbRessources)
    {
        this.nbRessources = nbRessources;
        this.nbLibres = nbRessources;
        this.moniteur = new ReentrantLock();
        classe = new Condition[nbRessources+1];
        tailleClasse = new int[nbRessources+1];

        for (int iRessource = 1; iRessource <= nbRessources; iRessource++) {
            classe[iRessource] = moniteur.newCondition();
            tailleClasse[iRessource] = 0;
        }
    }

    /** Demande à obtenir `demande' ressources. */
    public void allouer (int demande) throws InterruptedException
    {
        moniteur.lock();

        while (demande > nbLibres) {
            tailleClasse[demande]++;
            classe[demande].await();
            tailleClasse[demande]--;
        }
        nbLibres -= demande;

        moniteur.unlock();
    }

    /** Libère `rendu' ressources. */
    public void liberer (int rendu) throws InterruptedException
    {
        moniteur.lock();

        nbLibres += rendu;
        int nbLibresFictif = nbLibres;
        int[]  tailleClasseFictif = tailleClasse.clone();
        for (int iDemande = 1; iDemande <= nbRessources; iDemande++) {
            while (iDemande <= nbLibresFictif && tailleClasseFictif[iDemande] > 0) {
                classe[iDemande].signal();
                nbLibresFictif -=iDemande;
                tailleClasseFictif[iDemande]--;
            }
        }

        moniteur.unlock();
    }

    /** Chaîne décrivant la stratégie d''allocation. */
    public String nomStrategie ()
    {
        return "Priorité aux petits demandeurs";
    }

}
