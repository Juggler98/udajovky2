import Models.Okres;
import Models.Osoba;
import Models.PCRTest;
import Models.UzemnaJednotka;
import twoThreeTree.TTTree;
import twoThreeTree.TTTreeNode;

import java.util.Date;
import java.util.Random;

public class Application {

    private final TTTree<String, Osoba> personTree = new TTTree<>();
    private final TTTree<String, PCRTest> pcrTree = new TTTree<>();
    private final TTTree<Integer, UzemnaJednotka> okresTree = new TTTree<>();

    private final String[] names = {"Alexander", "Jett", "Griffin", "Tyson", "Donavan", "Remington", "German", "Frederick", "Camden", "Peter", "Gunnar", "Joey", "Leroy", "Braylon", "Darius", "Simeon", "Parker", "Colten", "Finnegan", "Esteban", "Nathaniel", "Marshall", "Hamza", "Mohammed", "Alden", "Kadin", "Will", "Orlando", "Lyric", "Shawn", "Ronald", "Brycen", "Kaleb", "Jaylan", "Keenan", "Bryant", "Eden", "Zaire", "Jarrett", "Gunner", "Dante", "Adonis", "Rex", "Giancarlo", "Grayson", "Alex", "Alfredo", "Ariel", "Cade", "Frank", "Craig", "Avery", "Rolando", "Daniel", "Nigel", "Malcolm", "Carmelo", "John", "Octavio", "Adrian", "Kash", "Shamar", "Alessandro", "Oliver", "Deangelo", "Kade", "Todd", "Spencer", "Trevin", "Jessie", "Santiago", "Theodore", "Damien", "Cayden", "Kelton", "Chance", "Jacob", "Jayvon", "Cael", "Zackary", "Javon", "Chandler", "Bentley", "Bronson", "Mekhi", "Emilio", "Hassan", "Micah", "Ronin", "Dennis", "Coby", "Rigoberto", "Morgan", "Oswaldo", "Brogan", "Wyatt", "Seamus", "Darian", "Titus", "Heath", "Marlene", "Miriam", "Kaylah", "Mylie", "Jaelyn", "Angeline", "Georgia", "Mia", "Rachael", "Zoie", "Emely", "Keira", "Cynthia", "Alena", "Mercedes", "Amara", "Carla", "Daisy", "Angie", "Lexie", "Reese", "Christine", "June", "Lila", "Angelina", "Sylvia", "Jacey", "Adalynn", "Alaina", "Dominique", "Rory", "Payten", "Casey", "Kira", "Noemi", "Fatima", "Alexandria", "Renee", "Jazmine", "Olive", "Cailyn", "Myah", "Evie", "Andrea", "Elizabeth", "Mariana", "Erika", "Kiersten", "Trinity", "Carissa", "Abagail", "Nicole", "Marlie", "Jewel", "Jasmine", "Kaia", "Mikayla", "Elise", "Leila", "Alia", "Alisha", "Kyla", "Juliet", "Paityn", "Isla", "Mareli", "Cristina", "Belinda", "Heidi", "Chana", "Shaniya", "Tania", "Isabela", "Avery", "Siena", "Amiya", "Madalyn", "Ryleigh", "Tara", "Jaylene", "Emery", "Jenna", "Jazlyn", "Shannon", "Brielle", "Reagan", "Averi", "Caitlin", "Abbey", "Aisha", "Cecelia", "Jocelynn", "Jordin", "Janiah", "Karissa", "Chelsea", "Kylie", "Eliana", "Sarah", "Kendall"};
    private final String[] lastNames = {"Giles", "Todd", "Wilkerson", "Meyer", "Padilla", "Buchanan", "Dillon", "Joseph", "Mullen", "Moss", "Finley", "Compton", "Fritz", "Freeman", "Solis", "Browning", "Ball", "Adkins", "Nunez", "Travis", "Gilmore", "Santiago", "Mayo", "Carson", "Bauer", "Brandt", "Yu", "Fleming", "Paul", "English", "Douglas", "Pacheco", "Carlson", "Mcintyre", "Sampson", "Oliver", "Tapia", "Galloway", "Bautista", "Mccormick", "Singleton", "Newman", "Gordon", "Davila", "Ramsey", "Brooks", "Colon", "Donaldson", "Farmer", "Mcguire", "Garza", "Villarreal", "Alexander", "Kennedy", "Cross", "Hall", "Charles", "Mcconnell", "Bass", "Daniels", "Bishop", "Odonnell", "Salinas", "Wise", "Ayala", "Koch", "Kirk", "Schwartz", "Lindsey", "Leon", "Werner", "Bowers", "Carr", "Mooney", "Norton", "Beck", "Mcknight", "Phelps", "Valencia", "Richards", "Gallegos", "Potter", "Brewer", "Martin", "Garrison", "Meza", "Herring", "Harding", "Wong", "Mata", "Booth", "Mason", "Frazier", "Hughes", "Montgomery", "Robertson", "Zamora", "Ryan", "Shields", "Maynard"};
    private final String[] okresNames = {"Bratislava I", "Bratislava II", "Bratislava III", "Bratislava IV", "Bratislava V", "Malacky", "Pezinok", "Senec", "Dunajská Streda", "Galanta", "Hlohovec", "Piešťany", "Senica", "Skalica", "Trnava", "Bánovce nad Bebravou", "Ilava", "Myjava", "Nové Mesto nad Váhom", "Partizánske", "Považská Bystrica", "Prievidza", "Púchov", "Trenčín", "Komárno", "Levice", "Nitra", "Nové Zámky", "Šaľa", "Topoľčany", "Zlaté Moravce", "Bytča", "Čadca", "Dolný Kubín", "Kysucké Nové Mesto", "Liptovský Mikuláš", "Martin", "Námestovo", "Ružomberok", "Turčianske Teplice", "Tvrdošín", "Žilina", "Banská Bystrica", "Banská Štiavnica", "Brezno", "Detva", "Krupina", "Lučenec", "Poltár", "Revúca", "Rimavská Sobota", "Veľký Krtíš", "Zvolen", "Žarnovica", "Žiar nad Hronom", "Bardejov", "Humenné", "Kežmarok", "Levoča", "Medzilaborce", "Poprad", "Prešov", "Sabinov", "Snina", "Stará Ľubovňa", "Stropkov", "Svidník", "Vranov nad Topľov", "Gelnica", "Košice I", "Košice II", "Košice III", "Košice IV", "Košice - okolie", "Michalovce", "Rožňava", "Sobrance", "Spišská Nová Ves", "Trebišov"};
    private final Integer[] okresCodes = {101, 102, 103, 104, 105, 106, 107, 108, 201, 202, 203, 204, 205, 206, 207, 301, 302, 303, 304, 305, 306, 307, 308, 309, 401, 402, 403, 404, 405, 406, 407, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 701, 702, 703, 704, 705, 706, 707, 708, 709, 710, 711, 712, 713, 801, 802, 803, 804, 805, 806, 807, 808, 809, 810, 811};
    private final String[] krajNames = {"Bratislavský", "Trnavský", "Trenčiansky", "Nitriansky", "Žilinský", "Banskobystrický", "Prešovský", "Košický"};
    private final Integer[] krajCodes = {1, 2, 3, 4, 5, 6, 7, 8};


    public Application() {
        for (int i = 0; i < okresCodes.length; i++) {
            Okres okres = new Okres(okresCodes[i], okresCodes[i] / 100, okresNames[i]);
            okresTree.add(okres);
        }
    }

    public void printPersonTree() {
        personTree.preorder((TTTreeNode<String, Osoba>) personTree.getRoot());
    }

    public void printPcrTree() {
        pcrTree.preorder((TTTreeNode<String, PCRTest>) pcrTree.getRoot());
    }

    public void printOkresTree() {
        okresTree.preorder((TTTreeNode<Integer, UzemnaJednotka>) okresTree.getRoot());
    }

//    public String getOkresName(int kodOkresu) {
//        return okresTree.search(kodOkresu).getNazov();
//    }

    public Okres getOkres(int kodOkresu) {
        return (Okres) okresTree.search(kodOkresu);
    }

    public String getKrajName(int kodKraja) {
        if (kodKraja >= krajNames.length) {
            return "Neznamy kraj " + kodKraja;
        }
        return krajNames[kodKraja];
    }

    public PCRTest getPCRTest(String kodTestu) {
        return pcrTree.search(kodTestu);
    }

    public Osoba getOsoba(String rodCislo) {
        return personTree.search(rodCislo);
    }

    public boolean addPCRTest(String rodCislo, int kodPracoviska, int kodOkresu, int kodKraju, boolean vysledok, String poznamka) {
        //Date date = new Date(rok - 1900, mesiac - 1, den);
        PCRTest pcrTest = new PCRTest(rodCislo, kodPracoviska, kodOkresu, kodKraju, vysledok, poznamka);
        return pcrTree.add(pcrTest);
    }

    public boolean addOsoba(String meno, String priezvisko, String rodCislo) {
        Date actualDate = new Date(System.currentTimeMillis());
        int year = Integer.parseInt(rodCislo.substring(0, 2));
        int month = Integer.parseInt(rodCislo.substring(2,4));
        int day = Integer.parseInt(rodCislo.substring(4,6));
        Date date = new Date(year < actualDate.getYear() - 100 ? year + 100 : year, month - 1, day);
        Osoba osoba = new Models.Osoba(meno, priezvisko, date, rodCislo);
        return personTree.add(osoba);

    }


    public void addRandomPCRTest(int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            //Date actualDate = new Date(System.currentTimeMillis());
            String rodCislo = this.getRandomRodCislo();
            int kodPracoviska = random.nextInt(100);
            int kodOkresu = okresCodes[random.nextInt(okresCodes.length)];
            int kodKraju = krajCodes[random.nextInt(krajCodes.length)];
            boolean vysledok = random.nextInt(2) == 1;
            String poznamka = null;
            PCRTest pcrTest = new PCRTest(rodCislo, kodPracoviska, kodOkresu, kodKraju, vysledok, poznamka);
            pcrTree.add(pcrTest);
        }
    }

    public void addRandomPersons(int count) {
        Random random = new Random();
        random.setSeed(50);
        Date actualDate = new Date(System.currentTimeMillis());
        for (int i = 0; i < count; i++) {
            String randomName = names[random.nextInt(this.names.length)];
            String randomLastName = lastNames[random.nextInt(this.lastNames.length)];

            int randomCode;
            int randomYear = random.nextInt(100);
            String randomCodeStr;
            if (randomYear < 54 && randomYear > actualDate.getYear() - 100) {
                randomCode = random.nextInt(1000);
                if (randomCode < 10) {
                    randomCodeStr = "00" + randomCode;
                } else if (randomCode < 100) {
                    randomCodeStr = "0" + randomCode;
                } else {
                    randomCodeStr = "" + randomCode;
                }
            } else {
                randomCode = random.nextInt(10000);
                if (randomCode < 10) {
                    randomCodeStr = "000" + randomCode;
                } else if (randomCode < 100) {
                    randomCodeStr = "00" + randomCode;
                } else if (randomCode < 1000) {
                    randomCodeStr = "0" + randomCode;
                } else {
                    randomCodeStr = "" + randomCode;
                }
            }
            int randomMonth = random.nextInt(12) + 1;
            int randomDay = random.nextInt(30) + 1;
            if (randomMonth == 2 && randomDay > 28) {
                randomDay = 28;
            }
            String randomRodCislo = "" + (randomYear < 10 ? ("0" + randomYear) : randomYear) + (randomMonth < 10 ? ("0" + randomMonth) : randomMonth) + (randomDay < 10 ? ("0" + randomDay) : randomDay) + randomCodeStr;
//            System.out.println(randomRodCislo);

            Date date = new Date(randomYear < actualDate.getYear() - 100 ? randomYear + 100 : randomYear, randomMonth - 1, randomDay);
//            System.out.println(date.getYear() + 1900);
//            System.out.println(date.getMonth());
//            System.out.println(date.getDate());
            Osoba osoba = new Models.Osoba(randomName, randomLastName, date, randomRodCislo);
            personTree.add(osoba);
        }
    }

    private String getRandomRodCislo() {
        Random random = new Random();
        random.setSeed(50);
        Date actualDate = new Date(System.currentTimeMillis());
        int randomCode;
        int randomYear = random.nextInt(100);
        String randomCodeStr;
        if (randomYear < 54 && randomYear > actualDate.getYear() - 100) {
            randomCode = random.nextInt(1000);
            if (randomCode < 10) {
                randomCodeStr = "00" + randomCode;
            } else if (randomCode < 100) {
                randomCodeStr = "0" + randomCode;
            } else {
                randomCodeStr = "" + randomCode;
            }
        } else {
            randomCode = random.nextInt(10000);
            if (randomCode < 10) {
                randomCodeStr = "000" + randomCode;
            } else if (randomCode < 100) {
                randomCodeStr = "00" + randomCode;
            } else if (randomCode < 1000) {
                randomCodeStr = "0" + randomCode;
            } else {
                randomCodeStr = "" + randomCode;
            }
        }
        int randomMonth = random.nextInt(12) + 1;
        int randomDay = random.nextInt(30) + 1;
        if (randomMonth == 2 && randomDay > 28) {
            randomDay = 28;
        }
        String randomRodCislo = "" + (randomYear < 10 ? ("0" + randomYear) : randomYear) + (randomMonth < 10 ? ("0" + randomMonth) : randomMonth) + (randomDay < 10 ? ("0" + randomDay) : randomDay) + randomCodeStr;
        System.out.println(randomRodCislo);
        return randomRodCislo;
    }


}
