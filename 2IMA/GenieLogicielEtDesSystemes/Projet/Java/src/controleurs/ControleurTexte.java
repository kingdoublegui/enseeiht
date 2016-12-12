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
	private Scanner sc;

	public ControleurTexte(Afficheur afficheur, Jeu jeu) {
		this.aff = afficheur;
		this.jeu = jeu;
		this.joueur = jeu.getExplorateur();
		this.diff = jeu.getDifficulte();
		this.lieuCourant = jeu.getTerritoire().getLieuDepart();
		this.sc = new Scanner(System.in);
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
				interaction(prs);
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
						
					case 3 : deposerObjet(joueur,lieuCourant);
							 action=0;
					         updateneeded=true;
							break;
					case 4 : prendreObjet(joueur, lieuCourant);
							 action=0;
							 updateneeded=true;
							break;
					case 5 :  prs = choisirPers(lieuCourant);
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
	 *  	1 pour lister les possessions du joueur
	 *  	2 pour lister les elmts du lieu
	 * 		3 pour deposer un objet
	 * 		4 pour prendre un objet visible
	 * 		5 pour interagir avec un personnage visible
	 * 		6 pour emprunter un chemin
	 */
	// A FINIR
	protected void interaction(Personne pers){
			Choix choix_courant = choixInteraction(pers,this.joueur);
			//MAJ de l'emplacement /!\ VOIR CHOIX DE DEBUT
			while(choix_courant.getExtre() !=Emplacement.fin){
				aff.interaction(pers);
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
					if(choix<=longueur && choix>0){
						choixvalide=true;
						faireAction(actionspossibles.get(choix-1),this.joueur);
					}										
				choix_courant = choixAction(actionsconditionneepossibles.get(choix).getChoixofferts());
				}	
			}
	}
	
	//APRES UNE ACTION MET A JOUR LES POSSESION DU JOUEUR
	private void faireAction(Action a ,Explorateur e){
		for(Objet o : a.getObjetsconsommes()){
			System.out.println("Vous avez consommé" +o.getQuantite()+" "+o.getName());
			e.consommeObjet(o);
		}
		for(PossessionConditionnee p : a.getPossessions()){ // AJOUT DES POSSESSIONS 
			if(listConditionRespecte(p.getConditions(), this.joueur)){ //si condition ok 
				if (p.getPossession() instanceof Connaissance){
					this.joueur.addPossessionConditionnee(p); // on ajoute
				}
				if (p.getPossession() instanceof Objet){
					Objet o = (Objet) p.getPossession();
					if(this.joueur.getPoids()+o.getQuantite()<=this.joueur.getPoidsMax()){ //si place
						this.joueur.addPossessionConditionnee(p); // on ajoute
						System.out.println("Vous venez d'acquérir"+o.getQuantite()+" "+o.getName() +"poid courant" + e.getPoids() +"poidmax" +e.getPoidsMax());
					} else {
						System.out.println("Pas assez de place pour acquérir "+o.getQuantite()+" "+o.getName());
					}
				}
			}
		}
	}
	
	//A partir d'une lsite daction conditionee renvoie les actions conditionnee possible apres avoir testé les conditions de chaque action
	protected List<ActionConditionnee> actionConditionneePossible(List<ActionConditionnee> actions){
		ArrayList<ActionConditionnee> res = new ArrayList<ActionConditionnee> ();
		for(ActionConditionnee a : actions){
			if(listConditionRespecte(a.getConditions(), this.joueur)){
				res.add(a);
			}
		}
		return res;
		
	}
	
	//Renvoie le choix que propose la personne
	protected Choix choixInteraction(Personne p,Explorateur e){
		Choix  res = null;
		for(Choix c : p.getChoix()){
			if(this.choixPossible(c,e) && c.getExtre().equals(Emplacement.depart)){
				res=c;
			}
		}
		return res;
	}
	//Renvoie un boolean caracterisant le fait qu'un choxi est possible ou non /!\ PAS DE SENSS
	protected boolean choixPossible(Choix c,Explorateur e){
		return(listConditionRespecte(c.getConditions(), e));	
	}
	
	// A PARTIR D'UNE CONDITION VERIFIE SI ELLE EST VRAIE
	protected boolean conditionRespectee(Condition c ,Explorateur e){ 
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
				
			} else {
				System.out.println("JE DOIT VERIFIER UNE CONNAISSANCE");
				if(!(e.connaissancePossedee(com.getCon()))){
					res=false;
				}
			}
		}
		return res;
	}
	protected boolean listConditionRespecte(List<Condition> c,Explorateur e){
		boolean res=true;
		for(Condition con : c){
			res=res&&conditionRespectee(con,e);
		}
		return res;
	}
	

	
	//MET A JOUR LEXTREMITE DU CHOIX SI CONDITION RESPECTE , ICI RENVOI LA VALEUR AJOUTER A LINITIALISATION , /!\ A VOIR PEUT ETRE MAL COMPRIS ...
	protected Emplacement updateChoix(Choix c){
		if(listConditionRespecte(c.getConditions(), this.joueur) && c.getConditions().size()!=0){ // si liste de condition significatif on renvoie lextremite reel
			return  c.getExtre();
		} else { //renvoie uen extremite intermediaire
			return Emplacement.intermediaire;
		}
	}
	
	//PERMET DE DEPOSER UN OBJET AVEC QUANTITE VARIABLE
	protected void deposerObjet(Explorateur e,Lieu l){
		if(e.getObjets().size()!=0){ //SI DEPOT AUTORISE PAR LE LIEU
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
				System.out.println("Vous ne possédez pas d'objet");
		}
	}

	/** Prendre un objet disponible dans le lieu avec une quantité voulue
	 * 
	 * @param e : explorateur
	 * @param l : lieu dans lequel on prend l'objet
	 */
	protected void prendreObjet(Explorateur e, Lieu l){

		
		// Lister les objets pouvant être pris
		List<Objet> objetsVisibles = l.getObjetsVisibles();
		
		//Si il y a au moins un objet visible et prenable
		if(objetsVisibles.size()!=0){ 

			// Lister les objets
			aff.listerObjets(objetsVisibles);
			int nbObj = objetsVisibles.size();

			// Lire le choix de l'utilisateur
			int indice_objet = lireInt(1, nbObj, "Choisissez un objet ", "Veuillez saisir un indentifiant valide");
			Objet objet_choisi = objetsVisibles.get(indice_objet-1);
			System.out.println("Objet choisi " + objet_choisi.getName());


			int choix_quantite=1;
			// Choisir la quantité s'il y a plus d'un exemplaire disponible
			if (objet_choisi.getQuantite() != 1){
				choix_quantite = lireInt(1, objet_choisi.getQuantite(), 
						"Veuillez saisir la quantité à prendre",
						"Saisissez un nombre entre 1 et "+objet_choisi.getQuantite());	
			}
			
			// Création du nouvel objet
			Objet objet_pris = new Objet(choix_quantite, objet_choisi.getObjet());
			PossessionConditionnee poss = new PossessionConditionnee(objet_pris);
			
			// Retrait de l'objet du lieu
			lieuCourant.rmObjet(objet_pris);
			
			// Ajout à l'inventaire
			e.addPossessionConditionnee(poss);
			aff.nouvelObjet(objet_choisi);
			
		}
	}
	
	protected Personne choisirPers(Lieu l) {
		aff.listerPersonnes(l.getPersonnes());
		if (l.getPersonnes().size() != 0) {
			int indice = lireInt(1, l.getPersonnes().size(), "A qui voulez vous parler ?", "Choisissez un numéro valide svp");
			return l.getPersonnes().get(indice-1);
		} else {
			return null;
		}
		
	}
	
	private List<Chemin> getCheminsValides(Jeu jeu, Lieu lieu, Explorateur explo) {
		List<Chemin> cheminsValide = new ArrayList<Chemin>();
		for(ComposantTerritoire c : jeu.getTerritoire().getComposantTerritoire()) {
			if(c instanceof Chemin){
				Chemin ch = (Chemin) c;
				if(ch.getDepart().equals(this)){
					if(listConditionRespecte(ch.getConditionsopen(), explo)&& listConditionRespecte(ch.getConditionsvis(), explo)){
						cheminsValide.add(ch);
					}
				}
			}
		}
		return cheminsValide;
	}
	
	protected Chemin choisirChemin(Lieu l){
		List<Chemin> cheminsValide = getCheminsValides(this.jeu, lieuCourant, this.joueur);
		
		aff.listerChemins(cheminsValide);
		int indice = lireInt(1, cheminsValide.size(), "Quel chemin voulez vous prendre ?", "Saisissez un nombre correct svp");
		return cheminsValide.get(indice);
	}
	
	
	// A VERIFIER
	protected void emprunterChemin(Chemin ch){
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
						&& (o.getQuantite() + joueur.getPoids() <= joueur.getPoidsMax())) {
					joueur.addPossessionConditionnee(p);
					aff.nouvelObjet(o);
				}
			} 
		}
		
		// aller dans le lieu suivant
		this.lieuCourant = ch.getFin();
	}

	
	protected Chemin getCheminObligatoire(Lieu l){
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
	
	protected int choisirAction(){
		int choix=0;
		System.out.println("Que voulez vous faire ?");
		System.out.println("(1) Lister les connaissances et les objets possédées");
		System.out.println("(2) Lister ce qui est visible dans le lieu (personnes + objets)");
		System.out.println("(3) Déposer un objet");
		System.out.println("(4) Prendre un objet");
		System.out.println("(5) Interagir avec une personne");
		System.out.println("(6) Choisir un chemin");
		choix = lireInt(1, 6);
		return choix;
	}
	
	protected Choix choixAction(List<ChoixConditionne> choix){
		Choix res =null;
		for(ChoixConditionne c : choix){
			if(listConditionRespecte(c.getConditions(), this.joueur)){
				res=c.getChoix();
				return res;
			}
		}
		return res;
	}

	/** Demande à l'utilisateur de saisir un entier compris entre min et max 
	 * 
	 */
	
	private int lireInt(int min, int max) {
		//return lireInt(min, max, "Saisissez un entier entre "+min+" et "+max, "");
		return lireInt(min, max, "", "Saisissez un nombre entre "+min+" et "+max);
	}
	
	/** Demande à l'urilisateur de saisir un entier compris entre min et max 
	 * 
	 * @param texteAcceuil : texte affiché avant chaque essai
	 * @param texteEchec   : texte affiché à chaque saisie incorrecte
	 */
	private int lireInt(int min, int max, String texteAcceuil, String texteEchec){
		int res = min-1;
		boolean succes = false;

		while(!succes) {
			try  {

				System.out.println(texteAcceuil);

				String str = sc.nextLine();
				System.out.println(str);
				res = Integer.parseInt(str);
				
				if (res>=min && res<=max){
					succes = true;
				} else {
					System.out.println(texteEchec);
				}
			} catch (Exception e){
				System.out.println(texteEchec);
				//e.printStackTrace();
			}
		}
		return res;
	}

}
