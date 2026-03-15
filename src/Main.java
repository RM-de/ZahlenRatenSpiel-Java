import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.io.IOException;
import java.io.*;
import java.util.*;


public class Main {
    static long recordTimestamp;
    static int bestRecord = Integer.MAX_VALUE;
    static String bestName = "_";
    static int lastRecord = Integer.MAX_VALUE;
    static String lastName = "_";

    static Integer myNumber = ThreadLocalRandom.current().nextInt(0, 10000 + 1);
    static Integer tries = 0;
    static JFrame frame;
    static JLabel nameLabel;
    static JLabel experienceLabel;
    static JTextField bestRecordLabelField;
    static JTextField bestRecordNameField;
    static JTextField bestRecordExperienceField;
    static JTextField lastRecordLabelField;
    static JTextField lastRecordNameField;
    static JTextField lastRecordExperienceField;
    static JTextField resultLabelField;
    static JTextField resultDetailsField;
    static JLabel hinweisText;
    static JTextField eingabeField;
    static JButton button;
    static JButton tryAgainButton;
    static JLabel erfolgLabel;
    static Font meineSchrift;



    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        openUI();
    }

    public static void openUI() {


        frame = new JFrame("ZahlenRatenSpiel");
        frame.setSize(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 10, 2, 10);
        Dimension feldGroße = new Dimension(140, 30);


        nameLabel = new JLabel("Name:");
        nameLabel.setPreferredSize(feldGroße);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 15));


        experienceLabel = new JLabel("Experience:");
        experienceLabel.setPreferredSize(feldGroße);
        experienceLabel.setFont(new Font("Arial", Font.PLAIN, 15));


        JTextField bestRecordLabelField = new JTextField("Best Record:");
        bestRecordLabelField.setPreferredSize(feldGroße);
        bestRecordLabelField.setFont(new Font("Arial", Font.PLAIN, 14));
        bestRecordLabelField.setEditable(false);
        bestRecordLabelField.setBackground(Color.WHITE);


        bestRecordNameField = new JTextField();
        bestRecordNameField.setPreferredSize(feldGroße);
        bestRecordNameField.setFont(new Font("Arial", Font.PLAIN, 15));
        bestRecordNameField.setEditable(false);
        bestRecordNameField.setBackground(Color.WHITE);


        bestRecordExperienceField = new JTextField();
        bestRecordExperienceField.setPreferredSize(feldGroße);
        bestRecordExperienceField.setFont(new Font("Arial", Font.PLAIN, 15));
        bestRecordExperienceField.setEditable(false);
        bestRecordExperienceField.setBackground(Color.WHITE);


        lastRecordLabelField = new JTextField("Last Record:");
        lastRecordLabelField.setPreferredSize(feldGroße);
        lastRecordLabelField.setFont(new Font("Arial", Font.PLAIN, 14));
        lastRecordLabelField.setEditable(false);
        lastRecordLabelField.setBackground(Color.WHITE);


        lastRecordNameField = new JTextField();
        lastRecordNameField.setPreferredSize(feldGroße);
        lastRecordNameField.setFont(new Font("Arial", Font.PLAIN, 15));
        lastRecordNameField.setEditable(false);
        lastRecordNameField.setBackground(Color.WHITE);


        lastRecordExperienceField = new JTextField();
        lastRecordExperienceField.setPreferredSize(feldGroße);
        lastRecordExperienceField.setFont(new Font("Arial", Font.PLAIN, 15));
        lastRecordExperienceField.setEditable(false);
        lastRecordExperienceField.setBackground(Color.WHITE);


        resultLabelField = new JTextField("Result:");
        resultLabelField.setPreferredSize(feldGroße);
        resultLabelField.setFont(new Font("Arial", Font.PLAIN, 14));
        resultLabelField.setEditable(false);
        resultLabelField.setBackground(Color.WHITE);


        resultDetailsField = new JTextField();
        resultDetailsField.setPreferredSize(feldGroße);
        resultDetailsField.setFont(new Font("Arial", Font.PLAIN, 15));
        resultDetailsField.setEditable(false);
        resultDetailsField.setBackground(Color.WHITE);


        hinweisText = new JLabel("Bitte geben Sie eine Zahl zwischen 0 und 10000 ein! ");
        hinweisText.setPreferredSize(feldGroße);
        hinweisText.setFont(new Font("Arial", Font.PLAIN, 14));


        eingabeField = new JTextField();
        eingabeField.setPreferredSize(feldGroße);
        eingabeField.setFont(new Font("Arial", Font.PLAIN, 16));
        eingabeField.requestFocusInWindow();
        eingabeField.setHorizontalAlignment(JTextField.CENTER);


        JButton button = new JButton("Klicken");
        button.setPreferredSize(feldGroße);
        button.setFont(new Font("Arial", Font.PLAIN, 15));


        JButton tryAgainButton = new JButton("Try again");
        tryAgainButton.setPreferredSize(feldGroße);
        tryAgainButton.setFont(new Font("Arial", Font.PLAIN, 15));


        JLabel erfolgLabel = new JLabel("Viel Erfolg: 🏆");
        erfolgLabel.setForeground(new Color(0, 150, 0));
        erfolgLabel.setPreferredSize(feldGroße);
        erfolgLabel.setFont(new Font("Arial", Font.PLAIN, 16));


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    eingabeField.setForeground(Color.BLACK);
                    String textFromEingabeField = eingabeField.getText();

                    if (textFromEingabeField.equals("Bitte klicken Sie auf (Try again)")) {

                        return;
                    }
                    Integer number = Integer.parseInt(textFromEingabeField);
                    RasoolsRound(number);

                } catch (Exception exception) {
                    if (!eingabeField.getText().equals("Bitte klicken Sie auf (Try again)")) {
                        resultDetailsField.setText("Bitte geben Sie nur Zahlen ein!");


                        resultDetailsField.setFont(new Font("Arial", Font.PLAIN, 14));
                        resultDetailsField.setForeground(Color.RED);
                        eingabeField.setText("");
                        eingabeField.requestFocusInWindow();

                    }
                }
            }
        });

        tryAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                myNumber = ThreadLocalRandom.current().nextInt(0, 10000 + 1);
                tries = 0;
                eingabeField.setEditable(true);
                eingabeField.setText("");


                button.setEnabled(true);
                resultDetailsField.setText("Neues Spiel! Rate die Zahl! 😊");
                resultDetailsField.setFont(new Font("Arial", Font.PLAIN, 14));
                resultDetailsField.setForeground(Color.RED);
                eingabeField.setForeground(Color.BLACK);
                eingabeField.requestFocusInWindow();

            }
        });

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(30, 4, 1, 10);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.;
        gbc.weighty = 0.;
        frame.add(nameLabel, gbc);


        gbc.insets = new Insets(30, 4, 1, 10);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        frame.add(experienceLabel, gbc);


        gbc.insets = new Insets(2, 40, 2, 2);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        frame.add(bestRecordLabelField, gbc);


        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        frame.add(bestRecordNameField, gbc);


        gbc.insets = new Insets(2, 2, 2, 40);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        frame.add(bestRecordExperienceField, gbc);


        gbc.insets = new Insets(2, 40, 2, 2);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        frame.add(lastRecordLabelField, gbc);


        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        frame.add(lastRecordNameField, gbc);


        gbc.insets = new Insets(2, 2, 2, 40);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        frame.add(lastRecordExperienceField, gbc);


        gbc.insets = new Insets(2, 40, 2, 2);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        frame.add(resultLabelField, gbc);


        gbc.insets = new Insets(2, 2, 2, 40);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        frame.add(resultDetailsField, gbc);


        gbc.insets = new Insets(2, 40, 2, 40);
        hinweisText.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        frame.add(hinweisText, gbc);


        gbc.insets = new Insets(2, 90, 10, 90);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        eingabeField.requestFocusInWindow();
        frame.add(eingabeField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(2, 90, 10, 90);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        frame.add(button, gbc);


        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(2, 90, 2, 90);
        frame.add(tryAgainButton, gbc);


        gbc.insets = new Insets(30, 25, 30, 10);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(erfolgLabel, gbc);
        

        frame.setResizable(false);
        frame.pack();
        eingabeField.requestFocusInWindow();
        frame.setLocationRelativeTo(null);
        ladeDaten();
        frame.setVisible(true);

    }

    public static void RasoolsRound(Integer number) {

        eingabeField.setForeground(Color.BLACK);
        System.out.println("DEBUG: Unser tippt: " + number + " |  Ziel ist: " + myNumber);
        tries++;
        if (number.equals(myNumber)) {

            String name = JOptionPane.showInputDialog(frame, " Glückwunsch! Bitte gegbe deine Name ein!");

            if (name == null || name.isEmpty()) {
                name = "Unbekannt";
            }

            lastRecordNameField.setText(name);
            lastRecordExperienceField.setText(String.valueOf(tries));


            if (tries < bestRecord) {
                bestRecord = tries;
                bestName = name;
                bestRecordNameField.setText(bestName);
                bestRecordExperienceField.setText(String.valueOf(bestRecord));
                recordTimestamp = System.currentTimeMillis();


                resultDetailsField.setText("NEW RECORD! " + name + " hat " + tries + " Versuche gebraucht!");
                resultDetailsField.setFont(new Font("Arial", Font.PLAIN, 12));
                resultDetailsField.setForeground(new Color(0, 150, 0));
            } else {

                resultDetailsField.setText("Glückwunsch! " + name + " hat " + tries + " Versuche gebraucht.");
                resultDetailsField.setForeground(new Color(0, 150, 0));
                resultDetailsField.setFont(new Font("Arial" , Font.PLAIN,13));
            }

            speichereDaten(bestName, bestRecord, recordTimestamp, name, tries);
            eingabeField.setText("Bitte klicken Sie auf (Try again)");
            eingabeField.setFont(new Font("Arial", Font.PLAIN, 14));
            eingabeField.setForeground(Color.BLUE);
            eingabeField.setEditable(false);
            button.setEnabled(false);

            return;
        }

        if (number < myNumber) {
            System.out.println("Falsch! Die Zahl ist zu klein!");
            resultDetailsField.setText( " Falsch!  " + number + "  ist zu klein! " );

        } else {

            System.out.println("Falsch! Die Zahl ist zu groß!");
            resultDetailsField.setText( " Falsch!  " +  number  + "  ist zu groß! ");
        }

        resultDetailsField.setFont(new Font("Arial", Font.PLAIN, 15));
        resultDetailsField.setForeground(Color.RED);
        eingabeField.setText("");
        eingabeField.requestFocusInWindow();
    }

    public static void speichereDaten(String bestName, int bestRecord, long recordTimestamp, String lastName, int lastRecord) {

        try (FileWriter writer = new FileWriter("ergebnisse.txt")) {
            writer.write(bestName + ";" + bestRecord + ";" + recordTimestamp + "\n");
            writer.write(lastName + ";" + lastRecord + "\n");

        } catch (IOException e) {
            System.out.println("Fehler beim Speichern!");

        }
    }

    public static void ladeDaten() {
        File datei = new File("ergebnisse.txt");
        if (!datei.exists()) return;

        try (Scanner scanner = new Scanner(datei)) {
            if (scanner.hasNextLine()) {
                String[] bestDaten = scanner.nextLine().split(";");

                if (bestDaten.length >= 3) {
                    long zeit = Long.parseLong(bestDaten[2]);

                    if (zeit == 0 ||
                            (System.currentTimeMillis() - zeit < 604800000L)) {

                        bestName = bestDaten[0];
                        bestRecord = Integer.parseInt(bestDaten[1]);
                        recordTimestamp = zeit;
                        bestRecordNameField.setText(bestName);
                        bestRecordExperienceField.setText(String.valueOf(bestRecord));
                    }
                }
            }

                if (scanner.hasNextLine()) {

                    String[] lastDaten = scanner.nextLine().split(";");
                    lastRecordNameField.setText(lastDaten[0]);
                    lastRecordExperienceField.setText(lastDaten[1]);
                }

            } catch(Exception e) {

                System.out.println("Fehler beim Laden");
            }
        }
    }

