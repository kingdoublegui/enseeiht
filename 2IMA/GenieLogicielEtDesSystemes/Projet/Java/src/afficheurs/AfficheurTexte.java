package afficheurs;

import java.util.List;
import modele.*;

public class AfficheurTexte implements Afficheur {
	
	@Override
	public void afficherLieu(Lieu lieu) {
		System.out.println();
		System.out.println("Vous êtes dans " + lieu.getName());
		System.out.println();
	}

	@Override
	public void visiterLieu(Lieu lieu) {
		List<Objet> objetsVisibles = lieu.getObjetsVisibles();
		List<Personne> personnesVisibles = lieu.getPersonnes();
		
		System.out.println("******************* Objets Visibles *******************");
		listerObjets(objetsVisibles);
		System.out.println("***************** Personnes  Visibles *****************");
		listerPersonnes(personnesVisibles);
		System.out.println("*******************************************************");

	}

	@Override
	public void afficherCheminsAccessibles(Lieu lieu, List<Chemin> chemins) {
		System.out.println("Les chemins accessibles sont : ");
		for (Chemin c : chemins){
			if (c.getDepart() == lieu && c.isAccessible()) {
				System.out.print("   " + c.getDescription());
			}		
		}
	}

	@Override
	public void emprunterChemin(Chemin chemin) {
		System.out.println("Vous empruntez " + chemin.getDescription());
	}
	
	@Override
	public void afficherObjetsPossedes(Explorateur explorateur) {
		List<Objet> objets = explorateur.getObjets();
		int poidsMax = explorateur.getPoidsMax();
		
		System.out.println("******************* Objets Possedes *******************");
		listerObjets(objets);
		System.out.println("*******************************************************");
		System.out.println("Poids Total : " + explorateur.getPoids() + "/" + poidsMax);
		System.out.println("*******************************************************");
		System.out.println();
	}
	
	@Override
	public void afficherConnaissances(Explorateur explorateur) {
		List<Connaissance> connaissances = explorateur.getConnaissances();
		
		System.out.println("************** Connaissances Maitrisées ***************");
		listerConnaissances(connaissances);
		System.out.println("*******************************************************");
		System.out.println();
	}

	@Override
	public void nouvelleConnaissance(Connaissance connaissance) {
		System.out.println("Vous venez d'acquérir la connaissance : " + connaissance.getName());
	}

	@Override
	public void choisirObjet(Objet objet) {
		System.out.println("Vous avez choisi : " + objet.getName());
	}
	
	@Override
	public void nouvelObjet(Objet objet) {
		System.out.println("Vous venez d'acquérir : " + objet.getQuantite() + " " + objet.getName());		
	}
	
	@Override
	public void placeInsuffisante(Objet objet) {
		System.out.println("Pas assez de place pour acquérir "+objet);
	}
	
	@Override
	public void depotImpossible() {
		System.out.println("Vous n'avez pas le droit de déposer des objets ici");
	}
	
	@Override
	public void deposerObjet(Objet objet) {	
		System.out.println("Vous venez de déposer " + objet);		
	}

	@Override
	public void consommerObjet(Objet objet) {	
		System.out.println("Vous venez de consommer " + objet);		
	}
	
	@Override
	public void interaction(Personne personne) {
		System.out.println(personne.getName() + " vous propose les actions suivantes");
		
	}

	@Override
	public int listerActions(List<Action> actions) {
		int indice = 0;
		
		if (actions.size() == 0) {
			System.out.println("Il n'y a aucunes actions");
		} else {
			System.out.println("Que voulez vous faire ?");
		}
		
		for (Action a : actions) {
			indice++;
			System.out.println("   " + indice + ") " + a.getName());
		}
		return indice;
	}
	
	@Override
	public int listerObjets(List<Objet> objets) {
		int indice = 0;
		
		if (objets.size() == 0) {
			System.out.println("Il n'y a aucun objets");
		} else {
			System.out.println("Id  Quantite Nom                                  Poids");
		}
		for (Objet o : objets){
			indice++;
			System.out.println(String.format("%2d)%9d %-37s%d",indice , o.getQuantite(), o.getObjet().getName(), o.getPoids())); 
		}
		return indice;
	}
	
	@Override
	public int listerConnaissances(List<Connaissance> connaissances) {
		int indice = 0;
		
		if (connaissances.size() == 0) {
			System.out.println("Il n'y a aucune connaissances");
		} 
		for (Connaissance c : connaissances){
			indice++;
			System.out.println("   " + indice + ") " + c.getName()); 
		}
		return indice;
	}
	
	@Override
	public int listerPersonnes(List<Personne> personnes) {
		int indice = 0;
		
		if (personnes.size() == 0) {
			System.out.println("Il n'y a personne");
		}
		for (Personne p : personnes){
			indice++;
			System.out.println("   " + indice + ") " + p.getName()); 
		}
		return indice;
	}
	
	@Override
	public int listerChemins(List<Chemin> chemins) {
		int indice = 0;
		
		if (chemins.size() == 0) {
			System.out.println("Il n'y a aucun chemins");
		}
		for (Chemin ch : chemins){
			indice++;
			System.out.println("   " + indice + ") " + ch.getDescription()); 
		}
		return indice;
	}
	
	@Override
	public void finJeu() {
		System.out.println("Vous avez fini le jeu !");
	}
}
