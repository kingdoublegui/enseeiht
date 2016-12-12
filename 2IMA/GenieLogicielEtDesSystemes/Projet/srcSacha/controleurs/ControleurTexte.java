package controleurs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import afficheurs.Afficheur;
import modele.*;

public class ControleurTexte implements Controleur {

	private Afficheur aff;
	private Lieu lieuCourant;
	private Explorateur joueur;
	private Difficulte diff;
	private Jeu jeu;
	
	public ControleurTexte(Afficheur afficheur, Jeu jeu) {
		this.aff = afficheur;
		this.jeu = jeu;
		this.joueur = jeu.getExplorateur();
		this.diff = jeu.getDifficulte();
		this.lieuCourant = jeu.getTerritoire().getLieuDepart();
	}

	@Override
	public void jouer() {
		int action = 0;
		Chemin destination;
		Personne prs;
		boolean updateneeded = false;
		
		while ((this.lieuCourant.getEmplacement()) != Emplacement.fin) {
			// Informer le joueur sur le lieu
			aff.afficherLieu(lieuCourant);

			
			// Interaction obligatoire et visible
			prs = lieuCourant.getInteractionObligatoire();
			if (prs != null){
				//interagir
				
			}
			
			// Recuperer Connaissances visibles
			for(PossessionConditionnee poss: lieuCourant.getPossessions()){
				if(poss.getPossession() instanceof Connaissance){
					if(listConditionRespecte(poss.getConditions(), this.joueur)){
						Connaissance c = (Connaissance) poss.getPossession();
						joueur.addPossessionConditionnee(poss);
						aff.nouvelleConnaissance(c);
					}
				}
			}
				
			
			
			// Chemin Obligatoire et visible
			destination = getCheminObligatoire(lieuCourant);
			if (destination != null) {
				System.out.println("JE PASSE ICI" + destination.getName());
				emprunterChemin(destination);
			}
			updateneeded=false;
			// Actions Facultatives
			while (!updateneeded) {
				
				// Lister choix possibles (deposer / prendre objet, interagir, emprunter chemin)
				action = choisirAction();
				
				// Effectuer Action
				switch (action) {
					case 1 :
						 aff.afficherConnaissances(joueur);
						 aff.afficherObjetsPossedes(joueur);
						 action=0;
						 break;
					case 2 :
						aff.visiterLieu(lieuCourant);
						action=0;
						break;
						
					case 3 : deposerObjet(this.joueur,lieuCourant);
							 action=0;
					         updateneeded=true;
							break;
					case 4 : prendreObjet();
							 action=0;
							 updateneeded=true;
							break;
					case 5 :  prs = choisirPers(this.lieuCourant);
							  action=0;
							if(prs!=null){
								interaction(prs);
								updateneeded=true;
							}
							break;
					case 6 : destination = choisirChemin(lieuCourant);
						     emprunterChemin(destination);
						     action=0;
						     updateneeded=true;
							//ATTENTION SI POSSIBLE
							break;
				}
			}
			
			// Changer de lieu
		}
	}

	/** Propose au joueur les differentes actions non obligatoires possibles
	 *  Retourne un entier correspondant a l'action choisie
	 * 		1 pour deposer un objet
	 * 		2 pour prendre un objet visible
	 * 		3 pour interagir avec un personnage visible
	 * 		4 pour emprunter un chemin
	 */
	// A FINIR
	public void interaction(Personne pers){
			Choix choix_courant = choixInteraction(pers,this.joueur);
//Mise aj our de l'emplacement /!\ VOIR CHOIX DE DEBUT
			while(choix_courant.getExtre() !=Emplacement.fin){
				aff.interaction(pers);
				Scanner sc = new Scanner(System.in);
				//IL FAUT GARDER LES ACTION CONDITIONNEE QUI REFERENCE LES ACTIONS PR CHANGER LE CHOIX ....
				List<ActionConditionnee> actionsconditionneepossibles = actionConditionneePossible(choix_courant.getActions());
				List<Action> actionspossibles = new ArrayList<Action>();
				for(int i=0;i<actionsconditionneepossibles.size();i++){
					actionspossibles.add(actionsconditionneepossibles.get(i).getAction());
				}
				boolean choixvalide = false;
				while(!choixvalide){
					aff.listerActions(actionspossibles);
					String str = sc.nextLine();
					int longueur = actionspossibles.size();
					int choix = Integer.parseInt(str);
					if(choix<longueur && choix>=0){
						choixvalide=true;
						updateAction(actionspossibles.get(choix),this.joueur);
					}										
				choix_courant = choixAction(actionsconditionneepossibles.get(choix).getChoixofferts());
				}	
			}
			//SI FINIS INTERACTION FINIS
	}
	//A partir d'une lsite daction conditionee renvoie les actions conditionnee possible apres avoir testé les conditions de chaque action
	public List<ActionConditionnee> actionConditionneePossible(List<ActionConditionnee> actions){
		ArrayList<ActionConditionnee> res = new ArrayList<ActionConditionnee> ();
		for(ActionConditionnee a : actions){
			if(listConditionRespecte(a.getConditions(), this.joueur)){
				res.add(a);
			}
		}
		return res;
		
	}
	
	//Renvoie le choix que propose la personne
	public Choix choixInteraction(Personne p,Explorateur e){
		Choix  res = null;
		for(Choix c : p.getChoix()){
			if(this.choixPossible(c,e) && c.getExtre().equals(Emplacement.depart)){
				res=c;
			}
		}
		return res;
	}
	//Renvoie un boolean caracterisant le fait qu'un choxi est possible ou non /!\ PAS DE SENSS
	public boolean choixPossible(Choix c,Explorateur e){
		return(listConditionRespecte(c.getConditions(), e));
	}
	
	public boolean conditionRespectee(Condition c ,Explorateur e){ // AP ARTIR D'UNE CONDITION VERIFIE SI ELEL EST VRAIS 
		boolean res=true;
		for( Comparaison com : c.getComparaisons()){
			if(!com.isConn()){
				Objet o = com.getObjet();
				Comparateur comp = com.getComp();
				switch (comp){
					case exactement :
						if(!(e.getQuantiteObjet(o) == o.getQuantite())){
							res=false;
						}
						break;
					case plus:
						if(!(e.getQuantiteObjet(o) > o.getQuantite())){
							res=false;
						}
						break;
					case moins:
						if(!(e.getQuantiteObjet(o) < o.getQuantite())){
							res=false;
						}
						break;
				}
				
			}else {
				System.out.println("JE DOIT VERIFIER UNE CONNAISSANCE");
				if(!(e.connaissancePossedee(com.getCon()))){
					res=false;
				}
			}
		}
		return res;
	}
	public boolean listConditionRespecte(List<Condition> c,Explorateur e){
		boolean res=true;
		for(Condition con : c){
			res=res&&conditionRespectee(con,e);
		}
		return res;
	}
	
	//APRES UNE ACTION MET A JOUR LES POSSESION DU JOUEUR
	public void updateAction(Action a ,Explorateur e){
		for(Objet o : a.getObjetsconsommes()){
			System.out.println("vous avez consommé" +o.getQuantite()+" "+o.getName());
			e.consommeObjet(o);
		}
		for(PossessionConditionnee p : a.getPossessions()){ // AJOUT DES POSSESSIONS 
			if(listConditionRespecte(p.getConditions(), this.joueur)){ //si condition ok 
				if (p.getPossession() instanceof Connaissance){
					this.joueur.addPossessionConditionnee(p); // on ajoute
				}
				if (p.getPossession() instanceof Objet){
					Objet o = (Objet) p.getPossession();
					if(this.joueur.getPoid()+o.getQuantite()<=this.joueur.getPoidsMax()){ //si place
						this.joueur.addPossessionConditionnee(p); // on ajoute
						this.joueur.modifPoid(o.getQuantite());
						System.out.println("Vous venez d'acquérir"+o.getQuantite()+" "+o.getName() +"poid courant" + e.getPoid() +"poidmax" +e.getPoidsMax());
					} else {
						System.out.println("Pas assez de place pour acquérir "+o.getQuantite()+" "+o.getName());
					}
				}
			}
		}
	}
	//MET A JOUR LEXTREMITE DU CHOIX SI CONDITION RESPECTE , ICI RENVOI LA VALEUR AJOUTER A LINITIALISATION , /!\ A VOIR PEUT ETRE MAL COMPRIS ...
	public Emplacement updateChoix(Choix c){
		if(listConditionRespecte(c.getConditions(), this.joueur) && c.getConditions().size()!=0){ // si liste de condition significatif on renvoie lextremite reel
			return  c.getExtre();
		} else { //renvoie uen extremite intermediaire
			return Emplacement.intermediaire;
		}
	}
	//PERMET DE DEPOSER UN OBJET AVEC QUANTITE VARIABLE
	public void deposerObjet(Explorateur e,Lieu l){
		if(listConditionRespecte(l.getConditions(),e) && e.getObjets().size()!=0){ //SI DEPOT AUTORISE PAR LE LIEU
			int indice = 0;		
			System.out.println("Que voulez vous déposer ?");
			for (Objet o : e.getObjets()) {
				System.out.println("   " + indice + ") " + o.getName());
				indice++;
			}
			Scanner sc = new Scanner(System.in);
			int choix_objet=-1;
			boolean choix_valide=false;
			while(!choix_valide){
				System.out.println("Veuillez choisir un objet valide");
				String str = sc.nextLine();
				choix_objet = Integer.parseInt(str);
				if(choix_objet >=0 && choix_objet<indice){
					choix_valide=true;
				} else {
							//TEXTE SI VOULU
				}
			}
			Objet objet_choisi = e.getObjets().get(choix_objet); //OBJET CHOISI
			System.out.println("Objet choisi " + objet_choisi.getName());
			choix_valide=false;
			int choix_quantite=0;
			while(!choix_valide){
				System.out.println("Veuillez choisir la quantite à jeter comprise entre"+" 1 et "+objet_choisi.getQuantite());
				String str = sc.nextLine();
				choix_quantite = Integer.parseInt(str);
				if(choix_quantite>0 && choix_quantite <= objet_choisi.getQuantite()){
					System.out.println("Vous venez de déposer  : "+choix_quantite+" " + objet_choisi.getName());
					e.consommeObjet(objet_choisi);
					PossessionConditionnee poss = new PossessionConditionnee();
					//CREATION OBJET + AJOUT DANS LE LIEU 
					Objet objet_depose = new Objet();
					objet_depose.setObjet(objet_choisi.getObjet());
					objet_depose.setQuantite(choix_quantite);
					poss.setPossession(objet_depose);
					l.addPossessionConditionnee(poss);
					choix_valide=true;
				} else {
					//TEXTE SI VOULU
				}								
			}
		}else {
			if(e.getObjets().size()!=0) {
				System.out.println("Depot non autorisé");
			} else {
				System.out.println("Vous ne possédez pas d'objet");
			}

		}

	}
	
	public void prendreObjet(){
		// TODO
	}
	
	public Personne choisirPers(Lieu l){
		Personne res = null;
		int indice = 0;	
		if(l.getPersonnes().size()!=0){ //sil il y'a des personnes
			System.out.println("A qui voulez vous parler ?");
			for (Personne p : l.getPersonnes()) {
				System.out.println("   " + indice + ") " + p.getName());
				indice++;
			}
			Scanner sc = new Scanner(System.in);
			int choix_personne =-1;
			boolean choix_valide = false;
			while(!choix_valide){
				System.out.println("Veuillez choisir une personne");
				String str = sc.nextLine();
				choix_personne = Integer.parseInt(str);
				if(choix_personne >=0 && choix_personne<indice){
					res = l.getPersonnes().get(choix_personne);
					choix_valide=true;
				}
			}
			return res;
		} else {
			System.out.println("Il n' y a personne à qui parler");
			return res;
		}
	}
	
	public Chemin choisirChemin(Lieu l){
		Chemin res= null;
		ArrayList<Chemin> cheminvalide = new ArrayList<Chemin>(); //LISTE DES CHEMINS VALIDE
		for(ComposantTerritoire c : this.jeu.getTerritoire().getComposantTerritoire()){ //CREATION DE CETTE LISTE
			if(c instanceof Chemin){
				Chemin che = (Chemin) c;
				if(che.getDepart().equals(l)){
					if(listConditionRespecte(che.getConditionsopen(), this.joueur )&& listConditionRespecte(che.getConditionsvis(), this.joueur)){
						cheminvalide.add(che);
					}
				}
			}
		}
		int indice = 0;		
		System.out.println("Quel chemin voulz vous prendre ?");
		for (Chemin ch : cheminvalide) {
			System.out.println("   " + indice + ") " + ch.getName());
			indice++;
		}
		int choix_chemin =-1;
		Scanner sc = new Scanner(System.in);
		boolean choix_valide = false;
		while(!choix_valide){
			System.out.println("Veuillez choisir un chemin");
			String str = sc.nextLine();
			choix_chemin = Integer.parseInt(str);
			if(choix_chemin >=0 && choix_chemin<indice){
				res = cheminvalide.get(choix_chemin);
				choix_valide=true;
			}
		}
		
		return res;
	}
	
	
	// A VERIFIER
	public void emprunterChemin(Chemin ch){
		aff.emprunterChemin(ch);		
		// Recuperer connaissances et objets, si possible
		for (PossessionConditionnee p : ch.getPossessions()) {
			if (p.getPossession() instanceof Connaissance) {
				Connaissance c = (Connaissance) p.getPossession();
				if (listConditionRespecte(p.getConditions(), joueur)) {
					joueur.addPossessionConditionnee(p);
					aff.nouvelleConnaissance(c);
				}
			} 
			if (p.getPossession() instanceof Objet) {
				Objet o = (Objet) p.getPossession();
				if (listConditionRespecte(p.getConditions(), joueur)
						&& (o.getQuantite() + joueur.getPoid() <= joueur.getPoidsMax())) {
					joueur.addPossessionConditionnee(p);
					joueur.modifPoid(o.getQuantite());
					aff.nouvelObjet(o);
				}
			} 
		}
		
		// aller dans le lieu suivant
		this.lieuCourant = ch.getFin();
	}

	
	public Chemin getCheminObligatoire(Lieu l){
		Chemin res = null;
		for(ComposantTerritoire c : this.jeu.getTerritoire().getComposantTerritoire()){ //CREATION DE CETTE LISTE
			if(c instanceof Chemin){
				Chemin che = (Chemin) c;
				if(che.getDepart().equals(l)){
					if(che.getObligation().equals(Obligation.obligatoire) && listConditionRespecte(che.getConditionsvis(), this.joueur)){
						res=che;
						return res;
					}
				}
			}
		}
		return res;
	}
	
	public int choisirAction(){
		int choix=0;
		Scanner sc = new Scanner(System.in);
		while (choix<=0 || choix >6){
			System.out.println("Que voulz vous faire ?");
			System.out.println("(1) Lister les connaissances et les objets possédées");
			System.out.println("(2) Lister ce qui est visible dans le lieu (personnes + objets)");
			System.out.println("(3) Déposer un objet");
			System.out.println("(4) Prendre un objet");
			System.out.println("(5) Interagir avec une personne");
			System.out.println("(6) Choisir un chemin");
			String str = sc.nextLine();
			choix = Integer.parseInt(str);
		}
		return choix;
	}
	
	public Choix choixAction(List<ChoixConditionne> choix){
		Choix res =null;
		for(ChoixConditionne c : choix){
			if(listConditionRespecte(c.getConditions(), this.joueur)){
				res=c.getChoix();
				return res;
			}
		}
		return res;
	}

}
