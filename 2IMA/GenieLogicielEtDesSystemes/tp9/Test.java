/** Un test décrit une ou plusieurs vérification automatique sur une
 * application.
 *
 * @author	Xavier Crégut
 * @version	$Revision: 1.2 $
 */
public interface Test {

	/** Lancer le test. Les résultats du test sont ajoutés dans le paramètre
	 * resultats.
	 * @param resultats collecteur des résultats de ce test
	 */
	void lancer(ResultatTest resultats);

}
