/** Programme de test de la classe Monnaie.
 * @author	Xavier Crégut
 * @version	$Revision: 1.1 $
 */
public class TEMonnaie1 extends TEMonnaie {

    public TEMonnaie1 (String nom) {
        super(nom);
    }

	public void tester() throws DeviseInvalideException {
		m1.ajouter(m2);
	}

}
