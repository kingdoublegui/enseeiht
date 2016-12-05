/** TestElementaire décrit un seul test.
 * @depend_notdefined - <send> - DeviseInvalideException
 * @author	Xavier Crégut
 * @version	$Revision: 1.2 $
 */
abstract public class TestElementaire implements Test {

	/** Le nom du test. */
	private String nom;

	/** Initialiser ce test.
	 * @param sonNom le nom du test
	 */
	public TestElementaire(String sonNom) {
		this.nom = sonNom;
	}

	final public void lancer() {
		ResultatTest resultats = new ResultatTest();
		this.lancer(resultats);
		System.out.println(resultats);
	}

	/** Lancer les trois méthodes : preparer, tester puis nettoyer.  Le seul
	 * intérêt est de garantir l'exécution de nettoyer, même si une
	 * exception se produit (finally).  Les exceptions ne sont pas récupérées
	 * ici mais propagées.
	 */
	private void lancerSansControle() throws Throwable {
		try {
			this.preparer();
			this.tester();
		} finally {
			this.nettoyer();
		}
	}

	final public void lancer(ResultatTest resultats) {
		try {
			resultats.incrementerTest();
			this.lancerSansControle();
		} catch (Echec e) {
			resultats.ajouterEchec(this, e);
		} catch (Throwable e) {
			resultats.ajouterErreur(this, e);
		}
	}

	/** Préparer les données pour le test. */
	protected void preparer() throws Throwable {
	}

	/** Réaliser le test effectif. */
	abstract protected void tester() throws Throwable;

	/** Nettoyer après l'exécution de tester. */
	protected void nettoyer() throws Throwable {
	}

}
