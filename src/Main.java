import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Osoba osobaTest = new Osoba("adam", "Beliansky", new Date(System.currentTimeMillis()), "9808281500");
        PcrTest test = new PcrTest(new Date(System.currentTimeMillis()), "9808281500", 5, 5, 5, false, "A");
        BSTree<String, Osoba> personTree = new BSTree<>();
        personTree.add(osobaTest.getRodCislo(), osobaTest);
    }

}
