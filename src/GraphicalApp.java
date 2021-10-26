import Models.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class GraphicalApp {

    private final JFrame jFrame = new JFrame("PCR Testy");
    private final JPanel jPanel = new JPanel();
    private final Application app = new Application();
    private final int componentHeight = 40;
    private final int componentWidth = 170;
    private final int componentDistance = 12;
    private final int componentSecondLine = 370;
    private final int posun = 10;

    private boolean novyTest = false;
    private boolean hladajPreOkresy = false;
    private boolean hladajTestyPrePracovisko = false;
    private boolean novaOsoba = false;

    public GraphicalApp() {
        jFrame.setVisible(true);
        jFrame.setSize(1600, 800);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(null);

        jPanel.setLayout(null);
        jPanel.setBounds(0, 0, 1600, 800);

        jFrame.add(jPanel);

        app.addRandomPersons(10000);
        app.addRandomPCRTest(10000);

        addVytvorPCRTestComponents();
        addVyhladajPCRTest();
        addVypisTestovPacienta();
        addVypisPozitivnychTestovPreOkres();
        addAllTestyPracovisko();
        addPridajOsoba();

        test();

//        ArrayList<Osoba> interval = app.getIntervalOsoba("550000123456", "6000001234");
//        for (Osoba o : interval) {
//            System.out.println(o.getKey());
//        }
        //app.printPersonTree();
//        System.out.println("size " + app.getPersonCount());
//        System.out.println("size " + interval.size());
    }

    private void test() {
        ArrayList<PCRTestDate> arrayList = app.getDateIntervalTest(app.getOkres(205).getPozitivneTesty(), new Date(98, 10, 5), new Date(122, 10, 5));
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("positive: " + arrayList.get(i).getData().getRodCisloPacienta());
            System.out.println("date: " + arrayList.get(i).getData().getDatum());
        }
        System.out.println(app.getOkres(105).getTesty().getSize());
        System.out.println(app.getOkres(105).getPozitivneTesty().getSize());

        JButton[] buttons = new JButton[15];
        for (int i = 0; i < buttons.length; i++) {
            if (i != 10 && i != 12) {
                buttons[i] = new JButton("");
                jPanel.add(buttons[i]);
                if (i > 5) {
                    buttons[i].setBounds(posun + componentWidth * (i - 6), posun + 370, componentWidth, componentHeight);
                } else {
                    buttons[i].setBounds(posun + componentWidth * (3 + i), posun, componentWidth, componentHeight);
                }
            }
        }
    }

    private void addPridajOsoba() {
        JButton jButton = new JButton("Pridaj osobu");
        jPanel.add(jButton);

        jButton.setBounds(posun + componentWidth * 6, posun + componentSecondLine, componentWidth, componentHeight);

        JButton uloz = new JButton("Uloz");
        uloz.setBounds(posun + componentWidth * 6, componentHeight * 4 + componentDistance + componentSecondLine, componentWidth, componentHeight);

        JTextField meno = new JTextField();
        JTextField priezvisko = new JTextField();
        JTextField rodneCislo = new JTextField();

        meno.setToolTipText("Meno");
        priezvisko.setToolTipText("Priezvisko");
        rodneCislo.setToolTipText("Rodne cislo");

        meno.setBounds(posun + componentWidth * 6, componentHeight + componentDistance + componentSecondLine, componentWidth, componentHeight);
        priezvisko.setBounds(posun + componentWidth * 6, componentHeight * 2 + componentDistance + componentSecondLine, componentWidth, componentHeight);
        rodneCislo.setBounds(posun + componentWidth * 6, componentHeight * 3 + componentDistance + componentSecondLine, componentWidth, componentHeight);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novaOsoba = !novaOsoba;
                if (novaOsoba) {
                    jPanel.add(meno);
                    jPanel.add(priezvisko);
                    jPanel.add(rodneCislo);
                    jPanel.add(uloz);
                } else {
                    jPanel.remove(meno);
                    jPanel.remove(priezvisko);
                    jPanel.remove(rodneCislo);
                    jPanel.remove(uloz);
                }
                jPanel.repaint();

            }
        });

        uloz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!rodneCislo.getText().equals("")) {
                    if (rodneCislo.getText().length() == 9 || rodneCislo.getText().length() == 10) {
                        if (app.addOsoba(meno.getText(), priezvisko.getText(), rodneCislo.getText()) != null) {
                            JOptionPane.showMessageDialog(null, "Osoba bola vytvorena");
                            novaOsoba = !novaOsoba;
                            jPanel.remove(meno);
                            jPanel.remove(priezvisko);
                            jPanel.remove(rodneCislo);
                            jPanel.remove(uloz);
                            jPanel.repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "Osobu sa nepodarilo vytvorit");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Zla dlzka rodneho cisla");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Zadaj rodne cislo");
                }
            }
        });
    }

    private void addAllTestyPracovisko() {
        JButton jButton = new JButton("Testy pre pracovisko");
        jPanel.add(jButton);

        jButton.setBounds(posun + componentWidth * 4, posun + componentSecondLine, componentWidth, componentHeight);

        JButton zobrazPcrTest = new JButton("Zobraz");
        zobrazPcrTest.setBounds(posun + componentWidth * 4, componentHeight * 4 + componentDistance + componentSecondLine, componentWidth, componentHeight);

        JTextField startDate = new JTextField();
        JTextField endDate = new JTextField();
        JTextField kodPracoviska = new JTextField();

        startDate.setToolTipText("Zaciatocny datum");
        endDate.setToolTipText("Koncovy datum");
        kodPracoviska.setToolTipText("Kod okresu");

        startDate.setText("01.01.2020");
        endDate.setText("01.01.2022");
        kodPracoviska.setText("205");


        startDate.setBounds(posun + componentWidth * 4, componentHeight + componentDistance + componentSecondLine, componentWidth, componentHeight);
        endDate.setBounds(posun + componentWidth * 4, componentHeight * 2 + componentDistance + componentSecondLine, componentWidth, componentHeight);
        kodPracoviska.setBounds(posun + componentWidth * 4, componentHeight * 3 + componentDistance + componentSecondLine, componentWidth, componentHeight);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hladajTestyPrePracovisko = !hladajTestyPrePracovisko;
                if (hladajTestyPrePracovisko) {
                    jPanel.add(startDate);
                    jPanel.add(endDate);
                    jPanel.add(kodPracoviska);
                    jPanel.add(zobrazPcrTest);
                } else {
                    jPanel.remove(startDate);
                    jPanel.remove(endDate);
                    jPanel.remove(kodPracoviska);
                    jPanel.remove(zobrazPcrTest);
                }
                jPanel.repaint();

            }
        });

        zobrazPcrTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startDate.getText().length() == 10 && endDate.getText().length() == 10) {
                    int day = Integer.parseInt(startDate.getText().substring(0, 2));
                    int month = Integer.parseInt(startDate.getText().substring(3, 5));
                    int year = Integer.parseInt(startDate.getText().substring(6));
                    Date startDate = new Date(year - 1900, month - 1, day);

                    day = Integer.parseInt(endDate.getText().substring(0, 2));
                    month = Integer.parseInt(endDate.getText().substring(3, 5));
                    year = Integer.parseInt(endDate.getText().substring(6));
                    Date endDate = new Date(year - 1900, month - 1, day);

                    ArrayList<PCRTestDate> testy = app.getDateIntervalTest(app.getPracovisko(Integer.parseInt(kodPracoviska.getText())).getTesty(), startDate, endDate);
                    String text = "PCR testy";
                    int index = 1;
                    for (PCRTestDate test : testy) {
                        Okres okres = app.getOkres(test.getData().getKodOkresu());
                        Pracovisko pracovisko = app.getPracovisko(test.getData().getKodPracoviska());
                        text += String.format("\n\nTest %d\n", index++);
                        text += String.format("Kod testu: %s\n", test.getData().getKodTestu());
                        text += String.format("Kod pracoviska: %s\n", test.getData().getKodPracoviska());
                        text += String.format("Kod okresu: %s\n", test.getData().getKodOkresu());
                        text += String.format("Kod kraja: %s\n", test.getData().getKodKraja());
                        text += String.format("Nazov pracoviska: %s\n", pracovisko.getNazov());
                        text += String.format("Nazov okresu: %s\n", okres.getNazov());
                        text += String.format("Nazov kraja: %s\n", app.getKrajName(okres.getKodKraja()));
                        if (test.getData().isVysledok()) {
                            text += "Vysledok: Pozitivny\n";
                        } else {
                            text += "Vysledok: Negativny\n";
                        }
                        if (test.getData().getPoznamka() != null && !test.getData().getPoznamka().equals("")) {
                            text += String.format("Poznamka: %s\n", test.getData().getPoznamka());
                        }
                        text += "\nOsoba:\n";
                        text += String.format("Rodne cislo: %s\n", test.getData().getRodCisloPacienta());
                        Osoba osoba = test.getData().getOsoba();
                        if (osoba != null) {
                            text += String.format("Meno: %s\n", osoba.getMeno());
                            text += String.format("Priezvisko: %s\n", osoba.getPriezvisko());
                            text += String.format("Datum Narodenia: %s\n", osoba.getDatumNarodenia().getDate() + "." + (osoba.getDatumNarodenia().getMonth() + 1) + "." + (1900 + osoba.getDatumNarodenia().getYear()));
                        } else {
                            text += "Dalsie udaje neexistuju\n";
                        }
                    }
                    JOptionPane.showMessageDialog(null, text, "PCR Testy", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(null, "Zadaj datum vo formate DD.MM.YYYY");
                }


            }
        });
    }

    private void addVypisPozitivnychTestovPreOkres() {
        JButton jButton = new JButton("Testy (+) pre okres");
        jPanel.add(jButton);

        jButton.setBounds(posun + componentWidth * 2, posun, componentWidth, componentHeight);

        JButton zobrazPcrTest = new JButton("Zobraz");
        zobrazPcrTest.setBounds(posun + componentWidth * 2, componentHeight * 4 + componentDistance, componentWidth, componentHeight);

        JTextField startDate = new JTextField();
        JTextField endDate = new JTextField();
        JTextField kodOkresu = new JTextField();

        startDate.setToolTipText("Zaciatocny datum");
        endDate.setToolTipText("Koncovy datum");
        kodOkresu.setToolTipText("Kod okresu");

        startDate.setText("01.01.2020");
        endDate.setText("01.01.2022");
        kodOkresu.setText("205");


        startDate.setBounds(posun + componentWidth * 2, componentHeight + componentDistance, componentWidth, componentHeight);
        endDate.setBounds(posun + componentWidth * 2, componentHeight * 2 + componentDistance, componentWidth, componentHeight);
        kodOkresu.setBounds(posun + componentWidth * 2, componentHeight * 3 + componentDistance, componentWidth, componentHeight);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hladajPreOkresy = !hladajPreOkresy;
                if (hladajPreOkresy) {
                    jPanel.add(startDate);
                    jPanel.add(endDate);
                    jPanel.add(kodOkresu);
                    jPanel.add(zobrazPcrTest);
                } else {
                    jPanel.remove(startDate);
                    jPanel.remove(endDate);
                    jPanel.remove(kodOkresu);
                    jPanel.remove(zobrazPcrTest);
                }
                jPanel.repaint();

            }
        });

        zobrazPcrTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startDate.getText().length() == 10 && endDate.getText().length() == 10) {
                    int day = Integer.parseInt(startDate.getText().substring(0, 2));
                    int month = Integer.parseInt(startDate.getText().substring(3, 5));
                    int year = Integer.parseInt(startDate.getText().substring(6));
                    Date startDate = new Date(year - 1900, month - 1, day);

                    day = Integer.parseInt(endDate.getText().substring(0, 2));
                    month = Integer.parseInt(endDate.getText().substring(3, 5));
                    year = Integer.parseInt(endDate.getText().substring(6));
                    Date endDate = new Date(year - 1900, month - 1, day);

                    ArrayList<PCRTestDate> testy = app.getDateIntervalTest(app.getOkres(Integer.parseInt(kodOkresu.getText())).getPozitivneTesty(), startDate, endDate);
                    String text = "PCR testy";
                    int index = 1;
                    for (PCRTestDate test : testy) {
                        Okres okres = app.getOkres(test.getData().getKodOkresu());
                        Pracovisko pracovisko = app.getPracovisko(test.getData().getKodPracoviska());
                        text += String.format("\n\nTest %d\n", index++);
                        text += String.format("Kod testu: %s\n", test.getData().getKodTestu());
                        text += String.format("Kod pracoviska: %s\n", test.getData().getKodPracoviska());
                        text += String.format("Kod okresu: %s\n", test.getData().getKodOkresu());
                        text += String.format("Kod kraja: %s\n", test.getData().getKodKraja());
                        text += String.format("Nazov pracoviska: %s\n", pracovisko.getNazov());
                        text += String.format("Nazov okresu: %s\n", okres.getNazov());
                        text += String.format("Nazov kraja: %s\n", app.getKrajName(okres.getKodKraja()));
                        if (test.getData().isVysledok()) {
                            text += "Vysledok: Pozitivny\n";
                        } else {
                            text += "Vysledok: Negativny\n";
                        }
                        if (test.getData().getPoznamka() != null && !test.getData().getPoznamka().equals("")) {
                            text += String.format("Poznamka: %s\n", test.getData().getPoznamka());
                        }
                        text += "\nOsoba:\n";
                        text += String.format("Rodne cislo: %s\n", test.getData().getRodCisloPacienta());
                        Osoba osoba = test.getData().getOsoba();
                        if (osoba != null) {
                            text += String.format("Meno: %s\n", osoba.getMeno());
                            text += String.format("Priezvisko: %s\n", osoba.getPriezvisko());
                            text += String.format("Datum Narodenia: %s\n", osoba.getDatumNarodenia().getDate() + "." + (osoba.getDatumNarodenia().getMonth() + 1) + "." + (1900 + osoba.getDatumNarodenia().getYear()));
                        } else {
                            text += "Dalsie udaje neexistuju\n";
                        }
                    }
                    JOptionPane.showMessageDialog(null, text, "PCR Testy", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(null, "Zadaj datum vo formate DD.MM.YYYY");
                }


            }
        });
    }

    private void addVypisTestovPacienta() {
        JButton jButton = new JButton("Zobraz testy pacienta");
        jPanel.add(jButton);

        jButton.setBounds(posun + componentWidth, posun + componentHeight, componentWidth, componentHeight);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rodCislo = JOptionPane.showInputDialog("Zadaj rodne cislo");
                if (rodCislo != null) {
                    Osoba osoba = app.getOsoba(rodCislo);
                    if (osoba != null) {
                        ArrayList<PCRTestDate> testy = osoba.getTesty();
                        String text = String.format("Rodne cislo: %s\n", osoba.getRodCislo());
                        text += String.format("Meno: %s\n", osoba.getMeno());
                        text += String.format("Priezvisko: %s\n", osoba.getPriezvisko());
                        text += String.format("Datum Narodenia: %s\n", osoba.getDatumNarodenia().getDate() + "." + (osoba.getDatumNarodenia().getMonth() + 1) + "." + (1900 + osoba.getDatumNarodenia().getYear()));
                        int index = 1;
                        for (PCRTestDate test : testy) {
                            Okres okres = app.getOkres(test.getData().getKodOkresu());
                            Pracovisko pracovisko = app.getPracovisko(test.getData().getKodPracoviska());
                            text += String.format("\n\nTest %d\n", index++);
                            text += String.format("Kod testu: %s\n", test.getData().getKodTestu());
                            text += String.format("Kod pracoviska: %s\n", test.getData().getKodPracoviska());
                            text += String.format("Kod okresu: %s\n", test.getData().getKodOkresu());
                            text += String.format("Kod kraja: %s\n", test.getData().getKodKraja());
                            text += String.format("Nazov pracoviska: %s\n", pracovisko.getNazov());
                            text += String.format("Nazov okresu: %s\n", okres.getNazov());
                            text += String.format("Nazov kraja: %s\n", app.getKrajName(okres.getKodKraja()));
                            if (test.getData().isVysledok()) {
                                text += "Vysledok: Pozitivny\n";
                            } else {
                                text += "Vysledok: Negativny\n";
                            }
                            if (test.getData().getPoznamka() != null && !test.getData().getPoznamka().equals("")) {
                                text += String.format("Poznamka: %s\n", test.getData().getPoznamka());
                            }
                        }
                        JOptionPane.showMessageDialog(null, text, "PCR Testy", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Osoba neexistuje", "Osoba nenajdena", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void addVyhladajPCRTest() {
        JButton jButton = new JButton("Vyhladaj PCR test");
        jPanel.add(jButton);

        jButton.setBounds(posun + componentWidth, posun, componentWidth, componentHeight);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kodTestu = JOptionPane.showInputDialog("Zadaj kod PCR testu");
                if (kodTestu != null) {
                    PCRTest test = app.getPCRTest(kodTestu);
                    if (test != null) {
                        Okres okres = app.getOkres(test.getKodOkresu());
                        Pracovisko pracovisko = app.getPracovisko(test.getKodPracoviska());
                        String text = String.format("Kod testu: %s\n", test.getKodTestu());
                        text += String.format("Kod pracoviska: %s\n", test.getKodPracoviska());
                        text += String.format("Kod okresu: %s\n", test.getKodOkresu());
                        text += String.format("Kod kraja: %s\n", test.getKodKraja());
                        text += String.format("Nazov pracoviska: %s\n", pracovisko.getNazov());
                        text += String.format("Nazov okresu: %s\n", okres.getNazov());
                        text += String.format("Nazov kraja: %s\n", app.getKrajName(okres.getKodKraja()));
                        if (test.isVysledok()) {
                            text += "Vysledok: Pozitivny\n";
                        } else {
                            text += "Vysledok: Negativny\n";
                        }
                        if (test.getPoznamka() != null && !test.getPoznamka().equals("")) {
                            text += String.format("Poznamka: %s\n", test.getPoznamka());
                        }
                        text += "\nOsoba:\n";
                        text += String.format("Rodne cislo: %s\n", test.getRodCisloPacienta());
                        Osoba osoba = test.getOsoba();
                        if (osoba != null) {
                            text += String.format("Meno: %s\n", osoba.getMeno());
                            text += String.format("Priezvisko: %s\n", osoba.getPriezvisko());
                            text += String.format("Datum Narodenia: %s\n", osoba.getDatumNarodenia().getDate() + "." + (osoba.getDatumNarodenia().getMonth() + 1) + "." + (1900 + osoba.getDatumNarodenia().getYear()));
                        } else {
                            text += "Dalsie udaje neexistuju\n";
                        }
                        JOptionPane.showMessageDialog(null, text, "PCR Test", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Test pre tento kod neexistuje", "Test nenajdeny", JOptionPane.ERROR_MESSAGE);
                    }
                }
                System.out.println(kodTestu);
            }
        });
    }

    private void addVytvorPCRTestComponents() {
        JButton jButton = new JButton("Vytvor PCR test");
        jPanel.add(jButton);

        JButton ulozPcrTest = new JButton("Ulozit");
        jButton.setBounds(posun, posun, componentWidth, componentHeight);
        ulozPcrTest.setBounds(posun, componentHeight * 7 + componentDistance, componentWidth, componentHeight);

        JTextField rodneCislo = new JTextField();
        JTextField kodPracoviska = new JTextField();
        JTextField kodOkresu = new JTextField();
        //JTextField kodKraja = new JTextField();
        JTextField poznamka = new JTextField();
        JRadioButton vysledokTrue = new JRadioButton("Positive");
        JRadioButton vysledokFalse = new JRadioButton("Negative");

        rodneCislo.setToolTipText("Rodne cislo");
        kodPracoviska.setToolTipText("Kod pracoviska");
        kodOkresu.setToolTipText("Kod okresu");
        //kodKraja.setToolTipText("Kod kraju");
        poznamka.setToolTipText("Poznamka");

        kodPracoviska.setText("203");
        kodOkresu.setText("205");


        rodneCislo.setBounds(posun, componentHeight + componentDistance, componentWidth, componentHeight);
        kodPracoviska.setBounds(posun, componentHeight * 2 + componentDistance, componentWidth, componentHeight);
        kodOkresu.setBounds(posun, componentHeight * 3 + componentDistance, componentWidth, componentHeight);
        poznamka.setBounds(posun, componentHeight * 4 + componentDistance, componentWidth, componentHeight);
        vysledokTrue.setBounds(posun, componentHeight * 5 + componentDistance, componentWidth, componentHeight);
        vysledokFalse.setBounds(posun, componentHeight * 6, componentWidth, componentHeight);

        vysledokTrue.setActionCommand("p");
        vysledokFalse.setActionCommand("n");

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("p")) {
                    vysledokFalse.setSelected(false);
                } else if (e.getActionCommand().equals("n")) {
                    vysledokTrue.setSelected(false);

                }
            }
        };

        vysledokTrue.addActionListener(actionListener);
        vysledokFalse.addActionListener(actionListener);


        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novyTest = !novyTest;
                if (novyTest) {
                    jPanel.add(rodneCislo);
                    jPanel.add(kodPracoviska);
                    jPanel.add(kodOkresu);
                    jPanel.add(poznamka);
                    jPanel.add(vysledokTrue);
                    jPanel.add(vysledokFalse);
                    jPanel.add(ulozPcrTest);
                } else {
                    jPanel.remove(rodneCislo);
                    jPanel.remove(kodPracoviska);
                    jPanel.remove(kodOkresu);
                    jPanel.remove(poznamka);
                    jPanel.remove(vysledokTrue);
                    jPanel.remove(vysledokFalse);
                    jPanel.remove(ulozPcrTest);
                }
                jPanel.repaint();

            }
        });

        ulozPcrTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!rodneCislo.getText().equals("")) {
                    if (rodneCislo.getText().length() == 9 || rodneCislo.getText().length() == 10) {
                        int kodKraju = Integer.parseInt(kodOkresu.getText()) / 100;
                        boolean positive = vysledokTrue.isSelected();
                        Osoba osoba = app.getOsoba(rodneCislo.getText());
                        if (osoba == null) {
                            String meno = JOptionPane.showInputDialog(null, "Zadaj meno novej osoby");
                            String priezvisko = JOptionPane.showInputDialog(null, "Zadaj priezvisko novej osoby");
                            osoba = app.addOsoba(meno, priezvisko, rodneCislo.getText());
                        }
                        if (app.addPCRTest(null, rodneCislo.getText(), Integer.parseInt(kodPracoviska.getText()), Integer.parseInt(kodOkresu.getText()), kodKraju, positive, poznamka.getText(), osoba)) {
                            JOptionPane.showMessageDialog(null, "Test bol vytvoreny");
                            novyTest = !novyTest;
                            jPanel.remove(rodneCislo);
                            jPanel.remove(kodPracoviska);
                            jPanel.remove(kodOkresu);
                            jPanel.remove(poznamka);
                            jPanel.remove(vysledokTrue);
                            jPanel.remove(vysledokFalse);
                            jPanel.remove(ulozPcrTest);
                            jPanel.repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "Test sa nepodarilo vytvorit");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Zla dlzka rodneho cisla");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Zadaj rodne cislo");
                }
            }
        });
    }

}
