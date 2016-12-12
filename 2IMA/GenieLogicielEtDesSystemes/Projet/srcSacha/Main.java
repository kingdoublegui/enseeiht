import modele.*;
import afficheurs.*;
import controleurs.*;

public class Main {
	
	public static void main(String[] args){
		Jeu jeu = new Jeu("JeuENigme",Difficulte.Facile);
		Territoire territoire = new Territoire("Enonce");
		jeu.addTerritoire(territoire);
		Lieu debut = new Lieu("Debut",Emplacement.Depart);
		territoire.addComposantTerritoire(debut);
		Lieu succes = new Lieu("Succes",Emplacement.Fin);
		territoire.addComposantTerritoire(succes);
		Lieu echec = new Lieu("Echec",Emplacement.Fin);
		territoire.addComposantTerritoire(echec);
		Lieu enigme = new Lieu("Enigme");
		territoire.addComposantTerritoire(sphynx);
		Personne sphynx = new Personne("Sphynx");
		enigme.addPersonne(sphynx);
		Chemin initialisation = new Chemin("Initialisation",debut,enigme);
		
		
		Chemin bonchemin = new Chemin("BonChemin",enigme,succes);
		territoire.addComposantTerritoire(bonchemin);
		bonchemin.addObligation(Obligation.Obligatoire);
		bonchemin.addVisibilite(Visibilite.Invisible);
		ConditionAutre bonnefin = new ConditionAutre("BonneFin");
		bonchemin.addConditionVisibilite(bonnefin);
		
		Chemin mauvaischemin = new Chemin("MauvaisChemin",enigme,echec);
		territoire.addComposantTerritoire(mauvaischemin);//.game faux
		mauvaischemin.addObligation(Obligation.Obligatoire);
		mauvaischemin.addVisibilite(Visibilite.Invisible);
		ConditionAutre mauvaisefin = new ConditionAutre("Mauvaisefin");
		mauvaischemin.addConditionVisibilite(mauvaisefin);
		
		ObjetElementaire tentative = new ObjetElementaire("Tentative",1);
		jeu.addComposant(tentative);
		
		Connaissance reussite = new Connaissance("Reussite");
		jeu.addComposant(reussite);
		
		Explorateur joueur = new Explorateur("Joueur",3);
		jeu.addComposant(joueur);
		joueur.addPossession(new PossessionConditionnee(new Objet(3,tentative)));
		
		sphynx.addObligation(Obligation.Obligatoire);
		Choix question = new Choix("Question"); //emplacement ????
		jeu.addComposant(question);
		sphynx.addChoix(question);
		
		Action bonnereponse = new Action("BonneReponse"); //selon selon ????
		ActionConditionnee bonnereponsecondtionne = new ActionConditionnee(bonnereponse);
		question.addActionConditionnee(bonnereponsecondtionne);
		Choix finquestion = new Choix("FinQuestion"); //Oblig� d'y faire reference avec []
		ChoixConditionne finquestionconditionnee = new ChoixConditionne(finquestion);
		bonnereponsecondtionne.addChoixoffert(finquestionconditionnee);
		
		Action mauvaisereponse = new Action("MauvaiseReponse"); //selon selon ????
		ActionConditionnee mauvaisereponsecondtionne = new ActionConditionnee(mauvaisereponse);
		question.addActionConditionnee(mauvaisereponsecondtionne);
		//ATTENTION QUESTION DEJA CREE BON COURAGE .... POUR LA TRADUCTION Ajouter le nomd'action devant toutes les variables ???
		ChoixConditionne questionconditionnee = new ChoixConditionne(question);
		mauvaisereponsecondtionne.addChoixoffert(questionconditionnee);
		ConditionAutre encoretentative = new ConditionAutre("EncoreTentative");
		questionconditionnee.addContion(encoretentative);
		ChoixConditionne finquestionconditionnee2 = new ChoixConditionne(finquestion);
		mauvaisereponsecondtionne.addChoixoffert(finquestionconditionnee2);
		finquestionconditionnee2.addContion(mauvaisefin);
		//pas compris reference au dessus 
		finquestion.addExtremite(Emplacement.Fin);

		bonnereponse.addPossession(new PossessionConditionnee()); //Erreur .... ObjetElementaire non definis
		mauvaisereponse.addObjetConsommes(new Objet(1,tentative));
		//de m�me reussite ....
		
		
		
		mauvaisefin.addComparaison(new ComparaisonObjet(new Objet(0,tentative),Comparateur.Egal));
		encoretentative.addComparaison(new ComparaisonObjet(new Objet(1,tentative),Comparateur.Superieur));
		
		
		
		
		
		
		//de m�me d�ja cr�e horrible car on va ajout� des conditions cette fois ci .....
		
		
		
		
		
		
		
		
		
		
		//question.addd .....
		
		
		

		Afficheur afficheur = new AfficheurTexte();
		Controleur controleur = new ControleurTexte(afficheur, jeu);
		
		controleur.jouer();
	}
}
