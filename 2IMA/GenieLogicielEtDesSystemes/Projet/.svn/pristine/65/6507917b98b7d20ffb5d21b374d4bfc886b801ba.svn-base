package afficheurs;

import java.util.List;
import modele.*;

public class AfficheurTexte implements Afficheur {
	
	@Override
	public void afficherLieu(Lieu lieu) {
		System.out.println("Vous êtes dans " + lieu.getName());
	}

	@Override
	public void visiterLieu(Lieu lieu) {
		List<Objet> objetsVisibles = lieu.getObjetsVisibles();
		List<Personne> personnesVisibles = lieu.getPersonnesVisibles();
		
		System.out.println(objetsVisibles.size() + " objet(s) sont visibles ici :");
		for(Objet o : objetsVisibles) {
			System.out.println("   " + o.getName());
		}
		
		System.out.println(objetsVisibles.size() + " personne(s) sont visibles ici :");
		for(Personne prs : personnesVisibles) {
			System.out.println("   " + prs.getName());
		}
	}

	@Override
	public void afficherCheminsAccessibles(Lieu lieu, List<Chemin> chemins) {
		System.out.println("Les chemins accessibles sont : ");
		for (Chemin c : chemins){
			if (c.getDepart == lieu && c.isAccessible) {
				System.out.print("   " + c.getDescription());
			}		
		}
	}

	@Override
	public void emprunterChemin(Chemin chemin) {
		System.out.println("Vous emprunter le chemin " + chemin.getDescription());
	}
	
	@Override
	public void afficherObjetsPossedes(Explorateur explorateur) {
		List<Objet> objets = explorateur.getObjets();
		int poidsMax = explorateur.getPoidsMax();
		int poidsTotal = 0;
		
		System.out.println("******************* Objets Possedes *******************");
		System.out.println("Quantite Nom                                      Poids");
		for (Objet o : objets){
			System.out.print(String.format("%-50%", o.getName())); //Formatter en une String de 50 char de long
			System.out.println(o.getPoids());
			poidsTotal += o.getPoids();
		}
		System.out.println("*******************************************************");
		System.out.println("Poids Total : " + poidsTotal + "/" + poidsMax);
		System.out.println("*******************************************************");
	}
	
	@Override
	public void afficherConnaissances(Explorateur explorateur) {
		List<Connaissance> connaissances = explorateur.getConnaissances();
		
		System.out.println("************** Connaissances Maitrisées ***************");
		for (Connaissance c : connaissances){
			System.out.print(c.getName());
		}
		System.out.println("*******************************************************");
	}

	@Override
	public void nouvelleConnaissance(Connaissance connaissance) {
		System.out.println("Vous venez d'acquérir la connaissance : " + connaissance.getName());
	}

	@Override
	public void nouvelObjet(Objet objet) {
		System.out.println("Vous venez d'acquérir l'objet : " + objet.getName());		
	}

	@Override
	public void deposerObjet(Objet objet) {	
		System.out.println("Vous venez de déposer l'objet : " + objet.getName());		
	}

	@Override
	public void interaction(Personne personne) {
		System.out.println(personne.getName() + " vous propose les actions suivantes");
		
	}

	@Override
	public void listerChoix(List<Choix> choix) {
		int indice = 1;
		
		System.out.println("Que voulez vous faire ?");
		for (Choix c : choix) {
			System.out.print("   " + indice + ") " + c.getName());
			indice++;
		}
	}
}
