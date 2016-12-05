public class TETests {

    public static void main (String[] args) {
        TestElementaire t1 = new TEMonnaie1("Test1");
        TestElementaire t2 = new TEMonnaie2("Test2");
        TestElementaire t3 = new TEMonnaie3("Test3");
        TestElementaire t4 = new TEMonnaie4("Test4");
        ResultatTest rs = new ResultatTest();
        t1.lancer(rs);
        t2.lancer(rs);
        t3.lancer(rs);
        t4.lancer(rs);

        SuiteTest suite = new SuiteTest();
        suite.ajouter(t1);
        suite.ajouter(t2);
        suite.lancer(rs);
        System.out.println("Resultats : \n" + rs);
    }

}
