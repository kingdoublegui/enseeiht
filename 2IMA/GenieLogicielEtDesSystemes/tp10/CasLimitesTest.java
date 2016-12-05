/** Tester quelques cas limites.
  * @author	Xavier CrÃ©gut
  * @version	$Revision$
  */

public class CasLimitesTest {

	public void testOK() {
		// OK.
	}

	private void testQuiDoitEtreIgnore() {
		// car il est privÃ© !
	}

	public void testQuiDoitEtreIgnore2(int a) {
		// car il prend des paramÃ¨tres
	}

	public void testQuiDoitEtreIgnore3(String n) {
		// car il prend des paramÃ¨tres
	}

	static public void testQuiDoitEtreIgnore4() {
		// car il est static
	}


}
