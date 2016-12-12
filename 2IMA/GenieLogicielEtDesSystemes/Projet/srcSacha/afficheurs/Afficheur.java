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
	
	/** Informe le joueur qu'il possède un nouvel objet.
	 * 
	 * @param objet nouvel objet
	 */
	public void nouvelObjet(Objet objet);
	
	/** Informe le joueur qu'il depose un objet.
	 * 
	 * @param objet  objet deposé
	 */
	public void deposerObjet(Objet objet);
	
	/** Affiche l'interaction avec un personnage
	 * 
	 * @param personne Personne avec qui on interagit
	 */
	public void interaction(Personne personne);
	
	/** Liste les choix réalisable par l'utilisateur
	 * 
	 * @param choix liste a afficher
	 */
	public void listerActions(List<Action> actions);
}
