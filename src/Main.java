import Models.Okres;
import Models.Osoba;
import tests.TestGenerator;
import twoThreeTree.TTTree;
import twoThreeTree.TTTreeNode;

import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        //Application app = new Application();
        //app.addRandomPersons(50);
        //app.printPersonTree();

        //app.addRandomPCRTest(50);
        //app.printPcrTree();

        //app.printOkresTree();

        //Okres okres = app.getOkres(205);
        //System.out.println(okres.getNazov());
        //System.out.println(app.getKrajName(okres.getKodKraja()));

        new GraphicalApp();

        //TestGenerator test = new TestGenerator();
        //test.runTests();
    }
}
