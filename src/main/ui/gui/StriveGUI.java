package ui.gui;

import model.Agenda;
import persistence.JsonReader;
import ui.StriveApp;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
//CITATION: modeled off of List Demo Project
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html

public class StriveGUI extends JPanel implements ListSelectionListener {

    private JList goalList;
    private DefaultListModel baseList;
    private StriveApp striveApp;

    private static final String removeString = "Remove goal";
    private JButton addButton;
    private JButton removeButton;
    private JButton saveButton;
    private JButton loadButton;
    private JsonReader jsonReader;
    private static final String addString = "Add Goal";
    private JTextField nametextField;
    private JTextField tftextField;
    private JTextField startextField;
    private JLabel name;
    private JLabel tf;
    private JLabel stars;
    private JScrollPane scrollPane;
    private JTextField newGoal;
    private JPanel buttonPanel;
    private static int WIDTH = 400;
    private static int HEIGHT = 400;


    public StriveGUI() {

        super(new BorderLayout());
        JFrame frame = new JFrame("Strive");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //create list
        baseList = new DefaultListModel();
        baseList.addElement("Test goal");
        baseList.addElement("Test goal1");
        baseList.addElement("Test goal2");
        //add from phase 2

        //make list
        createList();

        //create button
        createButtons();


        //show panel
        frame.add(scrollPane);
        frame.add(buttonPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public void createList() {
        goalList = new JList(baseList);
        goalList.setSelectedIndex(0);
        goalList.addListSelectionListener(this);
        goalList.setVisibleRowCount(20);
        scrollPane = new JScrollPane(goalList);
        scrollPane.setBounds(0,0, WIDTH, HEIGHT / 2);
    }

    public void createButtons() {
        //Create a panel for buttons at the bottom
        buttonPanel = new JPanel();
        this.buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(3,0,3,0);
        buttonPanel.setBounds(0,HEIGHT / 2,WIDTH, HEIGHT);

        c.gridx = 0;
        c.gridy = 0;
        addButton();
        addButtonLocation();

        c.gridx = 0;
        c.gridy = 1;
        removeButton();
        removeButtonLocation();

        saveButton();
        saveButtonLocation();

        loadButton();
        loadButtonLocation();


        c.gridx = 1;
        c.gridy = 0;
        nameLabel();

        c.gridx = 1;
        c.gridy = 1;
        nameText();
        nameTextLocation();

        c.gridx = 2;
        c.gridy = 0;
        tfLabel();

        c.gridx = 2;
        c.gridy = 1;
        timeframeText();
        timeframeTextLocation();

        c.gridx = 3;
        c.gridy = 1;
        starsLabel();

        c.gridx = 3;
        c.gridy = 0;
        starText();
        starTextLocation();




        buttonPanel.setBorder(BorderFactory.createEmptyBorder(WIDTH,HEIGHT / 2,WIDTH,HEIGHT));
        add(scrollPane, BorderLayout.CENTER);

    }

    //add button ------------------------------------------------

    public void addButton() {
        addButton = new JButton(addString);
        addButton.setBackground(Color.PINK);
        addButton.setOpaque(true);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                baseList.addElement(makeGoal(nametextField, tftextField, startextField));

            }
        });
    }

    public void addButtonLocation() {
        addButton.setPreferredSize(new Dimension(200, 200));
        //addButton.setLocation((WIDTH / 2), HEIGHT / 2 + 200);
        buttonPanel.add(addButton);
    }

    //add button ------------------------------------------------


    //remove button ------------------------------------------------

    public void removeButton() {

        removeButton = new JButton(removeString);

        removeButton.setBackground(new Color(0,165, 190));
        removeButton.setOpaque(true);
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = goalList.getSelectedIndex();
                baseList.remove(index);
                baseList.removeElement(index);
                //System.out.println("I am in the action listener");

            }
        });
    }

    public void removeButtonLocation() {
        removeButton.setPreferredSize(new Dimension(200, 200));
        //removeButton.setLocation((WIDTH / 2), HEIGHT / 2 + 400);
        buttonPanel.add(removeButton);
    }

    //remove button ------------------------------------------------







    // name textfield ------------------------------------------------

    public void nameText() {
        nametextField = new JTextField();
    }

    public void nameTextLocation() {
        nametextField.setPreferredSize(new Dimension(300, 100));
        //nametextField.setLocation((WIDTH / 2) + 100, HEIGHT / 2 + 400);
        buttonPanel.add(nametextField);
    }
// name textfield ------------------------------------------------

    // timeframe textfield ------------------------------------------------

    public void timeframeText() {
        tftextField = new JTextField();

    }

    public void timeframeTextLocation() {
        tftextField.setPreferredSize(new Dimension(300, 100));
        //tftextField.setLocation((WIDTH / 2) + 200, HEIGHT / 2 + 400);
        buttonPanel.add(tftextField);
    }
// timeframe textfield ------------------------------------------------

    // star textfield ------------------------------------------------

    public void starText() {
        startextField = new JTextField();
    }

    public void starTextLocation() {
        startextField.setPreferredSize(new Dimension(300, 100));
        //startextField.setLocation((WIDTH / 2) + 200, HEIGHT / 2 + 400);
        buttonPanel.add(startextField);
    }
// star textfield ------------------------------------------------


    public String makeGoal(JTextField nametextField, JTextField tftextField, JTextField startextField) {
        return nametextField.getText() + ", " + tftextField.getText() + ", " + startextField.getText() + " stars";
    }


    public void nameLabel() {
        name = new JLabel("Goal Name");
        //name.setLocation(((WIDTH / 2) + 100), HEIGHT / 2 + 380);
        buttonPanel.add(name);
    }

    public void tfLabel() {
        tf = new JLabel("Time Frame");
       // tf.setLocation(((WIDTH / 2) + 200), HEIGHT / 2 + 380);
        buttonPanel.add(tf);
    }

    public void starsLabel() {
        stars = new JLabel("Number of Stars");
        //stars.setLocation(((WIDTH / 2) + 200), HEIGHT / 2 + 380);
        buttonPanel.add(stars);
    }

    public void saveButton() {
        saveButton = new JButton("Save List");
        saveButton.setBackground(Color.PINK);
        saveButton.setOpaque(true);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    striveApp.getJsonWriter().open();
                    striveApp.getJsonWriter().write(striveApp.getMyAgenda());
                    striveApp.getJsonWriter().close();
                    System.out.println("Saved! " + striveApp.getMyAgenda().getName() + striveApp.getJsonStore());
                } catch (FileNotFoundException d) {
                    System.out.println("Error: unable to write to file " + striveApp.getJsonStore());
                }

            }
        });
    }

    public void saveButtonLocation() {
        saveButton.setPreferredSize(new Dimension(200, 200));
        //addButton.setLocation((WIDTH / 2), HEIGHT / 2 + 200);
        buttonPanel.add(saveButton);
    }


    public void loadButton() {
        loadButton = new JButton("Load List");
        loadButton.setBackground(Color.PINK);
        loadButton.setOpaque(true);
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Agenda temp = jsonReader.read();
                    striveApp.setMyAgenda(temp);
                   // System.out.println("Successfully loaded " + striveApp.getMyAgenda().getName() + " from " + striveApp.getJsonStore());
                } catch (IOException d) {
                    //System.out.println("Error: unable to reade from file: " + striveApp.getJsonStore());
                }

                //reset


            }
        });
    }

    public void loadButtonLocation() {
        loadButton.setPreferredSize(new Dimension(200, 200));
        //addButton.setLocation((WIDTH / 2), HEIGHT / 2 + 200);
        buttonPanel.add(loadButton);
    }




    //getters and setters
    public JList getGoalList() {
        return goalList;
    }

    public DefaultListModel getBaseList() {
        return baseList;
    }

    public String getNameText() {
        return nametextField.getText();
    }

    public String getTimeframeText() {
        return tftextField.getText();
    }



    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    public static void main(String[] args) {
        //SwingUtilities.invokeLater(new Runnable() {
        new StriveGUI();

    }
}


