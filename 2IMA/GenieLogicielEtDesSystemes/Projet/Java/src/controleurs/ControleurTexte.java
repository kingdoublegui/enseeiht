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
	private Jeu jeu;
	private Scanner sc;

	public ControleurTexte(Afficheur afficheur, Jeu jeu) {
		this.aff = afficheur;
		this.jeu = jeu;
		this.joueur = jeu.getExplorateur();
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
			getConnaissancesVisibles(lieuCourant, joueur);


			// Chemin Obligatoire et visible
			destination = getCheminObligatoire(lieuCourant);
			if (destination != null) {
				emprunterChemin(destination);
			}
			updateneeded=false;
			if(lieuCourant.getEmplacement()!=Emplacement.fin){
				// Actions Facultatives
				while (!updateneeded) {

					// Choisir l'action à effectuer
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

					case 3 : 
						deposerObjet(joueur,lieuCourant);
						action=0;
						updateneeded=true;
						break;
					case 4 : 
						prendreObjet(joueur, lieuCourant);
						action=0;
						updateneeded=true;
						break;
					case 5 :  
						prs = choisirPers(lieuCourant);
						action=0;
						if(prs!=null){
							interaction(prs);
							updateneeded=true;
						}
						break;
					case 6 : 
						destination = choisirChemin(lieuCourant);
						if (destination!= null) {
							emprunterChemin(destination);
							action=0;
							updateneeded=true;
						}
						break;
					case 7 :
						TransformerUnObjet(this.joueur);
						updateneeded=true;
					}
				}
			} else {
				aff.finJeu();
			}
			// Changer de lieu
		}
	}

	/** L'explorateur recoit toutes les connaissances visibles dont les conditions sont respectées du lieu 
	 * 
	 * @param l lieu contenant les connaissances
	 * @param e explorateur qui recoit les connaissances
	 */
	protected void getConnaissancesVisibles(Lieu l, Explorateur e) {
		for(PossessionConditionnee connCond: l.getConnaissancesCond()){
			if(listConditionRespecte(connCond.getConditions(), e)){
				Connaissance c = (Connaissance) connCond.getPossession();
				e.addConnaissances(c);
				aff.nouvelleConnaissance(c);
			}
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
	protected void interaction(Personne pers){
			Choix choix_courant = choixInteraction(pers,this.joueur);
			//MAJ de l'emplacement /!\ VOIR CHOIX DE DEBUT
			while(choix_courant.getExtre() !=Emplacement.fin){
				aff.interaction(pers);
				//IL FAUT GARDER LES ACTION CONDITIONNEE QUI REFERENCE LES ACTIONS PR CHANGER LE CHOIX ....
				List<ActionConditionnee> actionsconditionneepossibles = actionConditionneePossible(choix_courant.getActions(), this.joueur);
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
					if(actionsconditionneepossibles.get(choix-1).getChoixofferts().size()!=0){
					}
				choix_courant = choixAction(actionsconditionneepossibles.get(choix-1).getChoixofferts());
				}	
			}
	}
	
	/** Met a jour les possessions du joueur après une action
	 * 
	 * @param a : action que vient d'effectuer le joueur
	 * @param e : explorateur ayant effectué l'action
	 */
	private void faireAction(Action a ,Explorateur e) {
		// On consomme les objets à consommer.
		for(Objet o : a.getObjetsconsommes()){
			aff.consommerObjet(o);
			e.consommeObjet(o);
		}
		
		// On ajoute les nouvelles connaissances et objets
		for(PossessionConditionnee p : a.getPossessions()){ 
			// Si les conditions sont respectées
			if(listConditionRespecte(p.getConditions(), this.joueur)){ 
				if (p.getPossession() instanceof Connaissance){
					this.joueur.addPossessionConditionnee(p);
					aff.nouvelleConnaissance((Connaissance) p.getPossession());
				} else if (p.getPossession() instanceof Objet) {
					Objet o = (Objet) p.getPossession();
					if(this.joueur.getPoids()+o.getQuantite()<=this.joueur.getPoidsMax()){ //si place
						this.joueur.addPossessionConditionnee(p); // on ajoute
						aff.nouvelObjet(o);
					} else {
						aff.placeInsuffisante(o);
					}
				}
			}
		}
	}
	
	/**A partir d'une lite d'actions conditionee, renvoie les actions conditionnee possible
	 * 
	 * @param actions Les actions a tester
	 * @return les actions possibles
	 */
	protected List<ActionConditionnee> actionConditionneePossible(List<ActionConditionnee> actions, Explorateur explo){
		ArrayList<ActionConditionnee> res = new ArrayList<ActionConditionnee> ();
		for(ActionConditionnee a : actions){
			if(listConditionRespecte(a.getConditions(), explo)){
				res.add(a);
			}
		}
		return res;
	}
	
	/** Renvoie le choix que propose la personne
	 * 
	 * @param p La personne qui propose
	 * @param e le joueur
	 * @return le choix proposé
	 */
	protected Choix choixInteraction(Personne p,Explorateur e){
		Choix  res = null;
		for(Choix c : p.getChoix()){
			if(this.choixPossible(c,e) && c.getExtre().equals(Emplacement.depart)){
				res=c;
			}
		}
		return res;
	}
	
	//Renvoie un boolean caracterisant le fait qu'un choix est possible ou non /!\ PAS DE SENS
	protected boolean choixPossible(Choix c,Explorateur e){
		return(listConditionRespecte(c.getConditions(), e));	
	}
	
	/** Vérifie qu'une condition est respectée
	 * 
	 * @param c Condition à tester
	 * @param e Joueur
	 * @return True si la condition est respectée, false sinon
	 */
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
				if(!(e.connaissancePossedee(com.getCon()))){
					res=false;
				}
			}
		}
		return res;
	}
	
	/** Vérifie qu'une liste de condition est respectée
	 * 
	 * @param c Liste de conditions à tester
	 * @param e Joueur
	 * @return True si toutes les conditions sont respectées, false sinon
	 */	protected boolean listConditionRespecte(List<Condition> c,Explorateur e){
		boolean res=true;
		for(Condition con : c){
			res=res&&conditionRespectee(con,e); // opérateur
		}
		return res;
	}
	
	//MET A JOUR LEXTREMITE DU CHOIX SI CONDITION RESPECTE , ICI RENVOI LA VALEUR AJOUTER A LINITIALISATION , /!\ A VOIR PEUT ETRE MAL COMPRIS ...
	protected Emplacement updateChoix(Choix c){
		if(listConditionRespecte(c.getConditions(), this.joueur) && c.getConditions().size()!=0) { // si liste de condition significatif on renvoie lextremite reel
			return  c.getExtre();
		} else { //renvoie uen extremite intermediaire
			return Emplacement.intermediaire;
		}
	}
	
	/** Déposer un objet dans le lieu avec une quantité voulue,
	 * si cela est autorisé par le lieu
	 * 
	 * @param e : explorateur
	 * @param l : lieu dans lequel on prend l'objet
	 */
	protected void deposerObjet(Explorateur e, Lieu l){
		// Si le lieu autorise un dépot
		if (listConditionRespecte(l.getConditionsDepotObjet(), e)) {

			// Lister les objets
			int nbObj = aff.listerObjets(e.getObjets());

			//Si l'explorateur possède au moins un objet
			if(nbObj>0){ 
				
				// Lire le choix de l'utilisateur
				int indice_objet = lireInt(1, nbObj,
							"Choisissez un objet ",
							"Veuillez saisir un indentifiant valide");
				Objet objet_choisi = e.getObjets().get(indice_objet-1);
				aff.choisirObjet(objet_choisi);

				int choix_quantite=1;
				// Choisir la quantité s'il y a plus d'un exemplaire disponible
				if (objet_choisi.getQuantite() != 1){
					choix_quantite = lireInt(1, objet_choisi.getQuantite(), 
								"Veuillez saisir la quantité à déposer",
								"Saisissez un nombre entre 1 et "+objet_choisi.getQuantite());	
				}

				// Création du nouvel objet
				Objet objet_depose = new Objet(choix_quantite, objet_choisi.getObjet());
				PossessionConditionnee poss = new PossessionConditionnee(objet_depose);

				// Retrait de l'objet de l'inventaire
				e.consommeObjet(objet_depose);
				aff.deposerObjet(objet_depose);
				
				// Ajout au lieu
				l.addPossessionConditionnee(poss);
			}
		} else {
			aff.depotImpossible();
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
		
		// Lister les objets
		int nbObj = aff.listerObjets(objetsVisibles);
		
		//Si il y a au moins un objet visible et prenable
		if(nbObj>0){ 

			// Lire le choix de l'utilisateur
			int indice_objet = lireInt(1, nbObj, "Choisissez un objet ", "Veuillez saisir un indentifiant valide");
			Objet objet_choisi = objetsVisibles.get(indice_objet-1);
			aff.choisirObjet(objet_choisi);

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
	
	/** Fait choisir au joueur la personne avec qui il veut interragir.
	 * 
	 * @param l Lieu dans lequel se trouve le joueur
	 * @return La personne choisie
	 */
	protected Personne choisirPers(Lieu l) {
		aff.listerPersonnes(l.getPersonnes());
		if (l.getPersonnes().size() != 0) {
			int indice = lireInt(1, l.getPersonnes().size(), "A qui voulez vous parler ?", "Choisissez un numéro valide svp");
			return l.getPersonnes().get(indice-1);
		} else {
			return null;
		}
		
	}
	
	/** Renvoit la liste des chemins empruntables d'un lieu
	 * 
	 * @param l Lieu dans lequel se trouve le joueur
	 * @return Liste des chemins valides
	 */
	private List<Chemin> getCheminsValides(Jeu jeu, Lieu lieu, Explorateur explo) {
		List<Chemin> cheminsValide = new ArrayList<Chemin>();
		for(ComposantTerritoire c : jeu.getTerritoire().getComposantTerritoire()) {
			if(c instanceof Chemin){
				Chemin ch = (Chemin) c;
				if(ch.getDepart().equals(lieu)){
					if(listConditionRespecte(ch.getConditionsopen(), explo)&& listConditionRespecte(ch.getConditionsvis(), explo)){
						cheminsValide.add(ch);
					}
				}
			}
		}
		return cheminsValide;
	}
	
	/** Fait choisir au joueur le chemin qu'il souhaite emprunter.
	 * 
	 * @param l Lieu dans lequel se trouve le joueur
	 * @return Le chemin choisi.
	 */
	protected Chemin choisirChemin(Lieu l){
		List<Chemin> cheminsValide = getCheminsValides(this.jeu, lieuCourant, this.joueur);
		
		int nbChemins = aff.listerChemins(cheminsValide);
		if (nbChemins == 0) {
			return null;
		} else {
			int indice = lireInt(1, nbChemins, "Quel chemin voulez vous prendre ?", "Saisissez un nombre correct svp");
			return cheminsValide.get(indice-1);
		}
	}
	
	
	/** Emprunter un chemin. 
	 * Consomme les objets nécessaires et récupères les possessions dont les conditions sont respectées
	 * 
	 * @param ch Chemin à emprunter.
	 */	
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

	
	/** Renvoit le chemin obligatoire visible d'un lieu
	 * 
	 * @param l Lieu dans lequel on cherche le chemin
	 * @return Le chemin s'il existe, null sinon
	 */
	protected Chemin getCheminObligatoire(Lieu l){
		Chemin res = null;
		for(ComposantTerritoire c : this.jeu.getTerritoire().getComposantTerritoire()){ //CREATION DE CETTE LISTE
			if(c instanceof Chemin){
				Chemin che = (Chemin) c;
				if(che.getDepart().equals(l)){
					if(che.getObligation().equals(Obligation.obligatoire) &&
							listConditionRespecte(che.getConditionsvis(), joueur) && 
							listConditionRespecte(che.getConditionsopen(), joueur)){
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
		System.out.println("(7) Transformer un objet");
		choix = lireInt(1, 7);
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
	
	/** Demande à l'utilisateur de saisir un entier compris entre min et max 
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
	public void TransformerUnObjet(Explorateur e){
		List<Objet> objetTransformable = getObjetTransformable(e.getObjets(),e);
		if(objetTransformable.size()!=0){
			for (int i=0;i< objetTransformable.size();i++){
				System.out.println("("+(i+1)+") Objet à transformer :"+objetTransformable.get(i).getName());
			}
			int indice = lireInt(1, objetTransformable.size(),
					"Veillez choisir un objet à transformer  ",
					"Veuillez saisir un indentifiant valide");
			List<Transformation> transfopossible = getTransformationPossible(objetTransformable.get(indice-1),e);
			for (int i=0;i< transfopossible.size();i++){
				System.out.println("("+(i+1)+")"+"Cette transformation vous offrera "+ transfopossible.get(i).getObjetString());;
			}
			int indice2 = lireInt(1, transfopossible.size(),
					"Veillez choisir une transformation  ",
					"Veuillez saisir un indentifiant valide");
			System.out.println("Vous avez consommé un "+objetTransformable.get(indice-1).getName());
			System.out.print(" et obtenu "+transfopossible.get(indice2-1).getObjetString());
			e.consommeObjet(objetTransformable.get(indice-1));
			for( Objet o : transfopossible.get(indice2-1).getObjets()){
				PossessionConditionnee pos = new PossessionConditionnee();
				pos.setPossession(o);
				e.addPossessionConditionnee(pos);
			}
		} else {
			System.out.println("Aucun objet à transformer");
		}
		
	}
	
	public List<Objet> getObjetTransformable(List<Objet> obj,Explorateur e){
		List<Objet> res = new ArrayList<Objet>();
		boolean objetadd;
		for( Objet o : obj){
			objetadd=false;
			for(Transformation t : o.getObjet().getConditiontrans()){
				if (listConditionRespecte(t.getConditions(), e) && !objetadd){
					res.add(o);
					objetadd=true;
				}
			}
		}
		return res;
	}
	public List<Transformation> getTransformationPossible(Objet o,Explorateur e){
		List<Transformation> res = new ArrayList<Transformation>();
			for(Transformation t : o.getObjet().getConditiontrans()){
				if (listConditionRespecte(t.getConditions(), e) ){
					res.add(t);
				}
		}
		return res;
	}

}
