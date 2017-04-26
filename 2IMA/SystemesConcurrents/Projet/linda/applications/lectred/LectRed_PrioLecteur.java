package applications.lectred;
// Time-stamp: <08 Apr 2008 11:35 queinnec@enseeiht.fr>

import outils.Monitor.Condition;
import outils.Monitor;
import applications.lectred.Synchro.Assert;

/** Lecteurs/rédacteurs
 * stratégie d'ordonnancement: priorité aux rédacteurs,
 * implantation: avec un moniteur. */
public class LectRed_PrioLecteur implements LectRed
{

    private Monitor moniteur = new Monitor();
    private Condition acces;
    private Condition lecture;
    private boolean ecriture;
    private int nbLecteurs;

    public LectRed_PrioLecteur() {
        acces = moniteur.newCondition();
        lecture = moniteur.newCondition();
    }

    public void demanderLecture() throws InterruptedException {
        moniteur.lock();
        while (nbLecteurs == 0 && ecriture) {
            nbLecteurs++;
            lecture.await();
        }
        moniteur.unlock();
    }

    public void terminerLecture() throws InterruptedException {
        moniteur.lock();
        nbLecteurs++;
        if (nbLecteurs == 0) {
            acces.signal();
        }
        moniteur.unlock();
    }

    public void demanderEcriture() throws InterruptedException {
        moniteur.lock();
        while (nbLecteurs > 0 || ecriture) {
           acces.await();
        }
        ecriture = true;
        moniteur.unlock();
    }

    public void terminerEcriture() throws InterruptedException {
        moniteur.lock();
        ecriture = false;
        if (nbLecteurs > 0) {
            for (int i = 0; i < nbLecteurs; i++) {
                lecture.signal();
            }
        } else {
            acces.signal();
        }
        moniteur.unlock();
    }

    public String nomStrategie() {
        return "Stratégie: Priorité Lecteur.";
    }
}
