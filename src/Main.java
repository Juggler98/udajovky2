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

        int p = Integer.MAX_VALUE / 2;
        System.out.println(p);

        System.out.println(new Date(0, 0, 1));
        System.out.println(new Date(-10000, 11, 31));
        new GraphicalApp();

        //TestGenerator test = new TestGenerator();
        //test.runTests();
    }
}
