/** Programme de test de la classe Monnaie.
 * @author	Xavier Crégut
 * @version	$Revision: 1.1 $
 */
public class TEMonnaie2 extends TEMonnaie {

    public TEMonnaie2 (String nom) {
        super(nom);
    }

	public void tester() throws DeviseInvalideException {
		m1.retrancher(m2);
	}

}
