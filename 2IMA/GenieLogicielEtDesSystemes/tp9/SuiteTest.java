import java.util.*;

/** Une suite de test est un ensemble de test.
 *
 * @author	Xavier Crégut
 * @version	$Revision: 1.2 $
 * @has * - * Test
 */
public class SuiteTest implements Test {

    Collection<Test> tests = new ArrayList<Test>();

	/** Les tests de la suite. */
	/** Ajouter un nouveau test dans la suite.
	 * @param test le test à ajouter
	 */
	public void ajouter(Test test) {
        tests.add(test);
	}

	public void lancer(ResultatTest resultats) {
        for (Test test : tests) {
            test.lancer(resultats);
        }
	}

}
