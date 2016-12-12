import modele.*;
import afficheurs.*;
import controleurs.*;

public class MainJava {

	/**
	  * AUTOGENERATED CLASS
	  * Do not modify the code
	  * Please use toJava.mtl inside an Acceleo Project
	  */

	public static void main(String[] args) {
		/**
		 * Init all elements
		 */
		// Init Jeu
		Jeu jeuEnigme = new Jeu("JeuEnigme");
		// Init Composants
		Territoire enonce = new Territoire("Enonce");
		Lieu debut = new Lieu("Debut");
		Lieu succes = new Lieu("Succes");
		Lieu echec = new Lieu("Echec");
		Lieu enigme = new Lieu("Enigme");
		Chemin initialisation = new Chemin("Initialisation");
		Chemin bonChemin = new Chemin("BonChemin");
		Chemin mauvaisChemin = new Chemin("MauvaisChemin");
		ObjetElementaire tentative = new ObjetElementaire("Tentative");
		Connaissance reussite = new Connaissance("Reussite");
		Explorateur joueur = new Explorateur("Joueur");
		Personne sphinx = new Personne("Sphinx");
		Choix question = new Choix("Question");
		Choix finQuestion = new Choix("FinQuestion");
		Action bonneReponse = new Action("BonneReponse");
		Action mauvaiseReponse = new Action("MauvaiseReponse");
		Condition bonneFin = new Condition("BonneFin");
		Condition mauvaiseFin = new Condition("MauvaiseFin");
		Condition premiereQuestion = new Condition("PremiereQuestion");
		Condition encoreTentative = new Condition("EncoreTentative");
		
		// Init Dependencies
		// Init Objets
		Objet tentative1 = new Objet();
		Objet tentative0 = new Objet();
		Objet tentative3 = new Objet();
		
		// Init PossessionsConditionnees
		PossessionConditionnee tentative3ObjetPossessionConditionnee = new PossessionConditionnee();
		PossessionConditionnee reussitePossessionPossessionConditionnee = new PossessionConditionnee();
		
		// Init Transformations
		
		// Init ChoixConditionnes
		ChoixConditionne questionEncoreTentativeChoixConditionne = new ChoixConditionne();
		ChoixConditionne finQuestionMauvaiseFinChoixConditionne = new ChoixConditionne();
		ChoixConditionne finQuestionChoixConditionne = new ChoixConditionne();
		
		// Init ActionsConditionnees
		ActionConditionnee bonneReponseEncoreTentativeActionConditionnee = new ActionConditionnee();
		ActionConditionnee mauvaiseReponseActionConditionnee = new ActionConditionnee();
		
		// Init Comparaisons
		Comparaison plusTentative1Comparaison = new Comparaison();
		Comparaison reussiteComparaison = new Comparaison();
		Comparaison exactementTentative3Comparaison = new Comparaison();
		Comparaison exactementTentative0Comparaison = new Comparaison();
		
		
		/**
		 * Fill all elements
		 */
		// Fill Jeu
		jeuEnigme.setTerritoire(enonce);
		jeuEnigme.setExplorateur(joueur);
		jeuEnigme.setDifficulte(Difficulte.facile);
		
		// Fill Composants
		// Fill Territoires
		enonce.addComposants(debut);
		enonce.addComposants(succes);
		enonce.addComposants(echec);
		enonce.addComposants(enigme);
		enonce.addComposants(initialisation);
		enonce.addComposants(bonChemin);
		enonce.addComposants(mauvaisChemin);
		
		// Fill Lieux
		enigme.addPersonnes(sphinx);
		enigme.setPosition(Emplacement.intermediaire);
		succes.setPosition(Emplacement.fin);
		echec.setPosition(Emplacement.fin);
		debut.setPosition(Emplacement.depart);
		
		// Fill Chemins
		bonChemin.setDepart(enigme);
		bonChemin.setFin(succes);
		bonChemin.setOuverture(Ouverture.ouvert);
		bonChemin.setObligation(Obligation.obligatoire);
		bonChemin.setVisibilite(Visibilite.visible);
		bonChemin.addConditionVisibilite(bonneFin);
		mauvaisChemin.setDepart(enigme);
		mauvaisChemin.setFin(succes);
		mauvaisChemin.setOuverture(Ouverture.ouvert);
		mauvaisChemin.setObligation(Obligation.obligatoire);
		mauvaisChemin.setVisibilite(Visibilite.visible);
		mauvaisChemin.addConditionVisibilite(mauvaiseFin);
		initialisation.setDepart(debut);
		initialisation.setFin(enigme);
		initialisation.setOuverture(Ouverture.ouvert);
		initialisation.setObligation(Obligation.facultatif);
		initialisation.setVisibilite(Visibilite.visible);
		
		// Fill Personnes
		sphinx.setObligation(Obligation.obligatoire);
		sphinx.addChoix(question);
		
		// Fill Explorateurs
		joueur.addPossessionConditionnee(tentative3ObjetPossessionConditionnee);
		joueur.setPoidsMax(3);
		
		// Fill Connaissances
		reussite.setVisibilite(Visibilite.visible);
		
		// Fill ObjetsElementaires
		tentative.setVisibilite(Visibilite.visible);
		tentative.setTransformabilite(Transformabilite.immuable);
		
		// Fill Conditions
		premiereQuestion.addComparaison(exactementTentative3Comparaison);
		premiereQuestion.setOperateur(Operateur.et);
		premiereQuestion.setDifficulte(Difficulte.);
		premiereQuestion.setProbabilite(0);
		mauvaiseFin.addComparaison(exactementTentative0Comparaison);
		mauvaiseFin.setOperateur(Operateur.et);
		mauvaiseFin.setDifficulte(Difficulte.);
		mauvaiseFin.setProbabilite(0);
		bonneFin.addComparaison(reussiteComparaison);
		bonneFin.setOperateur(Operateur.et);
		bonneFin.setDifficulte(Difficulte.);
		bonneFin.setProbabilite(0);
		encoreTentative.addComparaison(plusTentative1Comparaison);
		encoreTentative.setOperateur(Operateur.et);
		encoreTentative.setDifficulte(Difficulte.);
		encoreTentative.setProbabilite(0);
		
		// Fill Choix
		question.addAction(bonneReponseEncoreTentativeActionConditionnee);
		question.addAction(mauvaiseReponseActionConditionnee);
		question.setPosition(Emplacementdepart);
		question.addCondition(premiereQuestion);
		finQuestion.setPosition(Emplacementfin);
		
		// Fill Actions
		mauvaiseReponse.addObjetConditionne(tentative1);
		bonneReponse.addPossessionConditionnee(reussitePossessionPossessionConditionnee);
		
		// Fill Dependencies
		// Fill Objets
		tentative1.setQuantite(1);
		tentative1.setObjet(Tentative);
		tentative0.setQuantite(0);
		tentative0.setObjet(Tentative);
		tentative3.setQuantite(3);
		tentative3.setObjet(Tentative);
		
		// Fill PossessionsConditionnees
		tentative3ObjetPossessionConditionnee.setPossession(Tentative3Objet);
		reussitePossessionPossessionConditionnee.setPossession(ReussitePossession);
		
		// Fill Transformations
		
		// Fill ChoixConditionnes
		questionEncoreTentativeChoixConditionne.setChoix(question);
		questionEncoreTentativeChoixConditionne.addCondition(encoreTentative);
		finQuestionMauvaiseFinChoixConditionne.setChoix(finQuestion);
		finQuestionMauvaiseFinChoixConditionne.addCondition(mauvaiseFin);
		finQuestionChoixConditionne.setChoix(finQuestion);
		
		// Fill ActionsConditionnees
		bonneReponseEncoreTentativeActionConditionnee.setAction(bonneReponse);
		bonneReponseEncoreTentativeActionConditionnee.addCondition(encoreTentative);
		bonneReponseEncoreTentativeActionConditionnee.addChoixOffert(finQuestionChoixConditionne);
		mauvaiseReponseActionConditionnee.setAction(mauvaiseReponse);
		mauvaiseReponseActionConditionnee.addChoixOffert(questionEncoreTentativeChoixConditionne);
		mauvaiseReponseActionConditionnee.addChoixOffert(finQuestionMauvaiseFinChoixConditionne);
		
		// Fill Comparaisons
		plusTentative1Comparaison.setComparateur(Comparateur.plus);
		plusTentative1Comparaison.setObjet(tentative1);
		reussiteComparaison.setConnaissance(reussite);
		exactementTentative3Comparaison.setComparateur(Comparateur.exactement);
		exactementTentative3Comparaison.setObjet(tentative3);
		exactementTentative0Comparaison.setComparateur(Comparateur.exactement);
		exactementTentative0Comparaison.setObjet(tentative0);
		

		/**
		 * Launch the game
		 */
		Controlleur controlleur = new COntrolleurTexte(new AfficheurTexte(), jeuEnigme);
		controlleur.jouer();
	}

}
