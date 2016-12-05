/** Programme de test de la classe Monnaie.
 *
 * @author	Xavier Crégut
 * @version	$Revision: 1.2 $
 */
public class TEMonnaie2 extends TEMonnaie {

	public TEMonnaie2() {
		super("Tester retrancher");
	}

	public void tester() throws DeviseInvalideException {
		m1.retrancher(m2);
		Assert.assertTrue(m1.getValeur() == -2);
	}

	public static void main(String[] args) {
		new TEMonnaie2().lancer();
	}

}
