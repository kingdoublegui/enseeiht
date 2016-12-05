/** Programme de test de la classe Monnaie.
 * @author	Xavier Crégut
 * @version	$Revision: 1.1 $
 */
public class TEMonnaie3 extends TEMonnaie {

    public TEMonnaie3 (String nom) {
        super(nom);
    }

	public void tester() throws DeviseInvalideException {
		m1.ajouter(m3);
	}

}
