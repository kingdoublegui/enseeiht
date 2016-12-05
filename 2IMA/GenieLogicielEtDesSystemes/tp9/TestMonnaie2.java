/** Programme de test de la classe Monnaie.
  * @author	Xavier Crégut
  * @version	$Revision: 1.1 $
  */
public class TestMonnaie2 {

	public static void main(String[] args) throws DeviseInvalideException {
		Monnaie m1 = new Monnaie(5, "euro");
		Monnaie m2 = new Monnaie(7, "euro");

		m1.retrancher(m2);
		System.out.println("valeur de m1 = " + m1.getValeur());
	}

}
