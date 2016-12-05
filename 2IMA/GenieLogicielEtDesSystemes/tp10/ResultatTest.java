import java.util.*;

/** La classe ResultatTest collecte le résultat des tests lancés.
  * @navassoc * - "* test échoué" TestElementaire
  *
  * @author	Xavier Crégut
  * @version	$Revision: 1.4 $
  */
public class ResultatTest {
	private int nbTests;	// nb de tests lancés
	private List<TestExecute> echecs;	// tests en échec
	private List<TestExecute> erreurs;	// tests en erreur

	/** Initialiser les statistiques. */
	public ResultatTest() {
		this.nbTests = 0;
		this.echecs = new ArrayList<TestExecute>();
		this.erreurs = new ArrayList<TestExecute>();
	}

	/** Enregistrer le lancement d'un nouveau test. */
	public void incrementerTest() {
		this.nbTests++;
	}

	/** Obtenir le nombre de tests lancés.
	 * @return le nombre de tests lancés
	 */
	public int getNbTests() {
		return this.nbTests;
	}

	/** Obtenir le nombre de tests en échec.
	 * @return le nombre de tests en échec
	 */
	public int getNbEchecs() {
		return this.echecs.size();
	}

	/** Obtenir le nombre de tests qui ont échoué.
	 * @return le nombre de tests qui ont échoué
	 */
	public int getNbErreurs() {
		return this.erreurs.size();
	}

	/** Enregistrer un test en échec (erreur fonctionnelle).
	 * @param test le test en échec
	 * @param cause la raison de l'échec
	 */
	public void ajouterEchec(TestElementaire test, Throwable cause) {
		System.out.println();
		cause.printStackTrace();
		System.out.println();

		this.echecs.add(new TestExecute(test, cause));
	}

	/** Enregistrer un test en erreur (erreur de programmation).
	 * @param test le test en erreur
	 * @param cause la raison de l'erreur
	 */
	public void ajouterErreur(TestElementaire test, Throwable cause) {
		System.out.println();
		cause.printStackTrace();
		System.out.println();

		this.erreurs.add(new TestExecute(test, cause));
	}

	public String toString() {
		return "Nb de tests lancés		: " + this.nbTests + "\n"
			+ "Nb d'erreurs fontionnelles	: " + this.getNbEchecs() + "\n"
			+ "Nb d'erreurs de programmation	: " + this.getNbErreurs();
	}

	/** La classe TestExecute conserve un test en échec et son résultat. */
	static public class TestExecute {
		private TestElementaire test;
		private Throwable causeErreur;

		public TestExecute(TestElementaire test, Throwable causeErreur) {
			this.test = test;
			this.causeErreur = causeErreur;
		}

		public TestElementaire getTest() {
			return this.test;
		}

		public Throwable getCauseErreur() {
			return this.causeErreur;
		}
	}

}
