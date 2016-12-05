/** Programme de test qui détecte une erreur de programmation.
 *
 * @author	Xavier Crégut
 * @version	$Revision: 1.2 $
 */
public class TEMonnaieErreur extends TestElementaire {

	public TEMonnaieErreur() {
		super("Un test qui échoue (erreur de programmation)");
	}

	public void tester() {
		throw new RuntimeException("Test qui devait échouer !");
	}

	public static void main(String[] args) {
		new TEMonnaieErreur().lancer();
	}

}
