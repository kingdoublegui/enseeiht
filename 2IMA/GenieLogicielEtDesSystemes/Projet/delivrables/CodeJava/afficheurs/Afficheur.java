package afficheurs;

import java.util.List;
import modele.*;

public interface Afficheur {

	/** Affiche le lieu
	 * 
	 * @param lieu
	 */
	public void afficherLieu(Lieu lieu);
	
	/** Affiche les objets et les personnes visibles dans le lieu
	 * 
	 * @param lieu
	 */
	public void visiterLieu(Lieu lieu);
	
	/** Affiche les chemins visibles et ouverts depuis un lieu
	 * 
	 * @param lieu
	 * @param chemins liste de tous les chemins du jeu.
	 */
	public void afficherCheminsAccessibles(Lieu lieu, List<Chemin> chemins);
	
	/** Informe le joueur qu'il emprunte un chemin.
	 * 
	 * @param chemin
	 */
	public void emprunterChemin(Chemin chemin);
		
	/** Liste les objets que possède le joueur, leur quantité et leur taille. 
	* Affiche également la taille cumulée des objets portés et la taille totale disponible
	* 
	* @param objets liste d'objets a afficher
	*/
	public void afficherObjetsPossedes(Explorateur explorateur);
	
	/** Affiche les connaissances d'un joueur
	 * 
	 * @param explo Personne qui possède les connaissances a afficher.
	 */
	public void afficherConnaissances(Explorateur explo);
	
	/** Informe le joueur qu'il possède une nouvelle connaissance.
	 * 
	 * @param connaissance nouvelle connaissance
	 */
	public void nouvelleConnaissance(Connaissance connaissance);
	
	/** Informe le joueur qu'il a choisi un objet.
	 * 
	 * @param objet nouvel objet
	 */
	public void choisirObjet(Objet objet);
	
	/** Informe le joueur qu'il possède un nouvel objet.
	 * 
	 * @param objet nouvel objet
	 */
	public void nouvelObjet(Objet objet);
	
	/** Informe le joueur qu'il n'a pas assez de place pour acquérir un objet.
	 * 
	 * @param objet trop gros
	 */
	public void placeInsuffisante(Objet objet);
	
	/** Informe le joueur qu'il ne peut pas poser un objet ici un objet.
	 */
	public void depotImpossible();
	
	/** Informe le joueur qu'il depose un objet.
	 * 
	 * @param objet  objet deposé
	 */
	public void deposerObjet(Objet objet);
	
	/** Informe le joueur qu'il consomme un objet.
	 * 
	 * @param objet  objet consommé
	 */
	public void consommerObjet(Objet objet);
	
	/** Affiche l'interaction avec un personnage
	 * 
	 * @param personne Personne avec qui on interagit
	 */
	public void interaction(Personne personne);
	
	/** Affiche les choix réalisable par l'utilisateur
	 *  Informe le joueur si celui ci est vide
	 * 
	 * @param choix liste a afficher
	 * @return le nombre d'éléments de la liste
	 */
	public int listerActions(List<Action> actions);

	/** Affiche un ensemble d'objets
	 *  Informe le joueur si celui ci est vide
	 *  
	 * @param objets liste a afficher
	 * @return le nombre d'éléments de la liste
	 */
	public int listerObjets(List<Objet> objets);
	
	/** Affiche un ensemble de connaissances
	 *  Informe le joueur si celui ci est vide
	 *  
	 * @param connaissances liste a afficher
	 * @return le nombre d'éléments de la liste
	 */
	public int listerConnaissances(List<Connaissance> connaissances);
	
	/** Affiche un ensemble de personnes
	 *  Informe le joueur si celui ci est vide
	 *  
	 * @param personnes liste a afficher
	 * @return le nombre d'éléments de la liste
	 */
	public int listerPersonnes(List<Personne> personnes);
	
	/** Affiche un ensemble de chemin
	 *  Informe le joueur si celui ci est vide
	 *  
	 * @param chemins liste a afficher
	 * @return le nombre d'éléments de la liste
	 */
	public int listerChemins(List<Chemin> chemins);
	
	/** Fin du jeu
	 */
	public void finJeu();
}
