/** Programme de test de la classe Monnaie.
 * @author	Xavier Crégut
 * @version	$Revision: 1.1 $
 */
public class TEMonnaie4 extends TEMonnaie {

    public TEMonnaie4 (String nom) {
        super(nom);
    }

	public void tester() throws DeviseInvalideException {
		m1.retrancher(m2);
        if (m1.getValeur() != 42) {
            throw new Echec();
        }
	}

}
