/** TEMonnaie factorise l'initialisation des données m1 et m2 des différents
 * programmes de test de la classe Monnaie.
 *
 * @author	Xavier Crégut
 * @version	$Revision: 1.1 $
 */
abstract public class TEMonnaie extends TestElementaire {

	protected Monnaie m1;
	protected Monnaie m2;

	public TEMonnaie(String nom) {
		super(nom);
	}

	protected void preparer() {
		m1 = new Monnaie(5, "euro");
		m2 = new Monnaie(7, "euro");
	}

}
