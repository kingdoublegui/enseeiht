/** TEMonnaie décrit un seul test sur la monnaie.
 * @depend_notdefined - <send> - DeviseInvalideException
 * @author	Thibault Meunier
 * @version	$Revision: 1.0 $
 */
abstract public class TEMonnaie extends TestElementaire {

    protected Monnaie m1;
    protected Monnaie m2;
    protected Monnaie m3;

	/** Initialiser ce test
	 * @param sonNom le nom du test
	 */
	public TEMonnaie(String sonNom) {
		super(sonNom);
	}

	/** Préparer les données pour le test. */
	protected void preparer() throws Throwable {
        m1 = new Monnaie(5, "euro");
        m2 = new Monnaie(7, "euro");
        m3 = new Monnaie(7, "dollar");
	}

}
