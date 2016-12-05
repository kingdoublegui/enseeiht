/** SuiteMonnaie regroupe les programmes de test de Monnaie.
  * @author	Xavier Crégut
  * @version	$Revision: 1.1 $
  */
public class SuiteMonnaie {
	public static void main(String[] args) {
		SuiteTest suite = new SuiteTest();
		suite.ajouter(new TEMonnaie1());
		suite.ajouter(new TEMonnaie2());
		suite.ajouter(new TEMonnaieEchec());
		suite.ajouter(new TEMonnaieErreur());

		ResultatTest statistiques = new ResultatTest();
		suite.lancer(statistiques);
		System.out.println(statistiques);
	}
}
