import Models.Okres;
import Models.Osoba;
import Models.PCRTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicalApp {

    private final JFrame jFrame = new JFrame("PCR Testy");
    private final JPanel jPanel =  new JPanel();
    private final Application app = new Application();
    private final int componentHeight = 40;
    private final int componentWidth = 140;
    private final int componentDistance = 12;

    private boolean novyTest = false;

    public GraphicalApp() {
        jFrame.setVisible(true);
        jFrame.setSize(1400, 800);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(null);

        jPanel.setLayout(null);
        jPanel.setBounds(0, 0, 600, 500);

        jFrame.add(jPanel);

        app.addRandomPersons(1000000);
        app.addRandomPCRTest(1000000);

        addVytvorPCRTestComponents();
        addVyhladajPCRTest();
    }

    private void addVyhladajPCRTest() {
        JButton jButton = new JButton("Vyhladaj PCR test");
        jPanel.add(jButton);

        jButton.setBounds(10 + componentWidth, 10, componentWidth, componentHeight);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kodTestu = JOptionPane.showInputDialog("Zadaj kod PCR testu");
                if (kodTestu != null) {
                    PCRTest test = app.getPCRTest(kodTestu);
                    if (test != null) {
                        Okres okres = app.getOkres(test.getKodOkresu());
                        String text = String.format("Kod testu: %s\n", test.getKodTestu());
                        text += String.format("Kod pracoviska: %s\n", test.getKodPracoviska());
                        text += String.format("Kod okresu: %s\n", test.getKodOkresu());
                        text += String.format("Kod kraja: %s\n", test.getKodKraja());
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
                        Osoba osoba = app.getOsoba(test.getRodCisloPacienta());
                        if (osoba != null) {
                            text += String.format("Meno: %s\n", osoba.getMeno());
                            text += String.format("Priezvisko: %s\n", osoba.getPriezvisko());
                            text += String.format("Datum Narodenia: %s\n", osoba.getDatumNarodenia());
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
        jButton.setBounds(10, 10, componentWidth, componentHeight);
        ulozPcrTest.setBounds(10, componentHeight * 7 + componentDistance, componentWidth, componentHeight);

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

        kodPracoviska.setText("12345");
        kodOkresu.setText("205");


        rodneCislo.setBounds(10, componentHeight + componentDistance, componentWidth, componentHeight);
        kodPracoviska.setBounds(10, componentHeight * 2 + componentDistance, componentWidth, componentHeight);
        kodOkresu.setBounds(10, componentHeight * 3 + componentDistance, componentWidth, componentHeight);
        poznamka.setBounds(10, componentHeight * 4 + componentDistance, componentWidth, componentHeight);
        vysledokTrue.setBounds(10, componentHeight * 5 + componentDistance, componentWidth, componentHeight);
        vysledokFalse.setBounds(10, componentHeight * 6, componentWidth, componentHeight);

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
                        System.out.println(poznamka.getText());
                        int kodKraju = Integer.parseInt(kodOkresu.getText()) / 100;
                        System.out.println(kodKraju);
                        boolean positive = vysledokTrue.isSelected();
                        System.out.println(app.addPCRTest(rodneCislo.getText(), Integer.parseInt(kodPracoviska.getText()), Integer.parseInt(kodOkresu.getText()), kodKraju, positive, poznamka.getText()));
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
