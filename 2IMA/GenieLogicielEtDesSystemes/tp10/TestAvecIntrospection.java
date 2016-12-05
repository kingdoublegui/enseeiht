import java.lang.reflect.*;

/** TestAvecIntrospection décrit un test élémentaire caractérisé par une classe
 * de test et la méthode de cette classe qui correspond au test.
 *
 * @author	Xavier Crégut
 * @version	$Revision: 1.3 $
 */
public class TestAvecIntrospection extends TestElementaire {

	Object objet;
    Method methode;

	public TestAvecIntrospection(Object objet, Method methode) {
		super(methode.getName());
		assert methode != null;
		assert objet != null;
        this.objet = objet;
        this.methode = methode;
	}

	protected void preparer() throws Throwable {
        try {
            objet.getClass().getMethod("preparer").invoke(objet);
        } catch (NoSuchMethodException e) {
            System.out.println("Pas de methode preparer");
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
	}

	protected void tester() throws Throwable {
        try {
            methode.invoke(objet);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
	}

	protected void nettoyer() throws Throwable {
        try {
        objet.getClass().getMethod("nettoyer").invoke(objet);
        } catch (NoSuchMethodException e) {
            System.out.println("Pas de methode nettoyer");
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
	}

}
