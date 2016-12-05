/** Programme de test de la classe Monnaie.
 *
 * @author	Xavier Crégut
 * @version	$Revision: 1.2 $
 */
public class TEMonnaie1 extends TEMonnaie {

	public TEMonnaie1() {
		super("Tester ajouter");
	}

	public void tester() throws DeviseInvalideException {
		m1.ajouter(m2);
		Assert.assertTrue(m1.getValeur() == 12);
	}

	public static void main(String[] args) {
		new TEMonnaie1().lancer();
	}

}
