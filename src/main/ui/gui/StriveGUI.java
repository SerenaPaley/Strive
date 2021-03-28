package ui.gui;

import model.Agenda;
import model.Goal;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.StriveApp;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
//CITATION: modeled off of List Demo Project
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html

// represents a graphical user interface for strive
public class StriveGUI extends JPanel implements ListSelectionListener {

    private JList goalList;
    private DefaultListModel baseList;
    //private StriveApp striveApp;

    private static final String removeString = "Remove goal";
    private JButton addButton;
    private JButton removeButton;
    private JButton saveButton;
    private JButton loadButton;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private static final String JSON_STORE = "./data/agenda.json";
    private Agenda agenda;
    private static final String addString = "Add Goal";
    private JTextField nametextField;
    private JTextField tftextField;
    private JTextField startextField;
    private JLabel name;
    private JLabel tf;
    private JLabel stars;
    private JScrollPane scrollPane;
    //private JTextField newGoal;
    private JPanel buttonPanel;
    private static String SOUND = "./data/magic-chime-01.wav";
    private int index;
    private static int WIDTH = 300;
    private static int HEIGHT = 300;

    // EFFECTS: runs the Strive GUI
    public StriveGUI() {

        super(new BorderLayout());
        JFrame frame = new JFrame("Strive");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        //create list
        baseList = new DefaultListModel();

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

    // MODIFIES: this
    // EFFECTS: creates the list panel for goals
    public void createList() {
        goalList = new JList(baseList);
        goalList.setSelectedIndex(0);
        goalList.addListSelectionListener(this);
        goalList.setVisibleRowCount(20);
        scrollPane = new JScrollPane(goalList);
        scrollPane.setBounds(0,0, WIDTH, HEIGHT / 2);
    }

    // MODIFIES: this
    // EFFECTS: creates the button pannel and adds buttons to it
    public void createButtons() {
        //Create a panel for buttons at the bottom
        buttonPanel = new JPanel();
        this.buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //c.fill = GridBagConstraints.HORIZONTAL;
        //c.insets = new Insets(3,0,3,0);
        buttonPanel.setBounds(0,HEIGHT / 2, WIDTH, HEIGHT);
        agenda = new Agenda("Serena's Goals");

        addButtonMethods();

        removeButton();
        removeButtonLocation();

        saveButton();
        saveButtonLocation();

        loadButton();
        loadButtonLocation();

        nameLabel();

        nameText();
        nameTextLocation();

        tfLabel();

        timeframeText();
        timeframeTextLocation();

        starsLabel();

        starText();
        starTextLocation();

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(WIDTH,HEIGHT / 2,WIDTH,HEIGHT / 2));
        add(scrollPane, BorderLayout.CENTER);
    }

    // EFFECTS: calls methods to add and change location of the add button
    private void addButtonMethods() {
        addButton();
        addButtonLocation();
    }

    // REQUIRES: chooseing a timeframe of daily, weekly, monthly or yearly
    // MODIFIES: this
    // EFFECTS: creates a button which adds a goal to the list when clicked. Also plays a sounds when clicked

    public void addButton() {
        addButton = new JButton(addString);
        addButton.setBackground(new Color(0,165, 190));
        addButton.setOpaque(true);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String timeframeForGoal = tftextField.getText();
                int starValue = Integer.parseInt(startextField.getText());
                Goal.TimeFrame stringToTf = convertStringToTimeframe(timeframeForGoal);
                Goal goalToAdd = new Goal(nametextField.getText(),stringToTf,starValue);
                agenda.addGoal(goalToAdd);
                convertAgendaToList();
               //sound
                playsound();
                nametextField.setText("");
                tftextField.setText("");
                startextField.setText("");

            }
        });
    }

    // MODIFIES: this
    // EFFECTS: changes the location and size of add button
    public void addButtonLocation() {
        addButton.setPreferredSize(new Dimension(200, 200));
        addButton.setLocation((WIDTH / 2), HEIGHT / 2 + 200);
        buttonPanel.add(addButton);
    }


    // MODIFIES: this
    // EFFECTS: creates a button which removes a goal from the list when clicked
    public void removeButton() {
        removeButton = new JButton(removeString);
        removeButton.setBackground(new Color(0,165, 190));
        removeButton.setOpaque(true);
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                index = goalList.getSelectedIndex();
                baseList.remove(index);
                baseList.removeElement(index);
                //System.out.println("I am in the action listener");
                agenda.removeGoal(index + 1);
                //System.out.println("I made it here");
                convertAgendaToList();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: changes location and size of remove button
    public void removeButtonLocation() {
        removeButton.setPreferredSize(new Dimension(200, 200));
        buttonPanel.add(removeButton);
    }


    // EFFECTS: creates a text field for the goal name
    public void nameText() {
        nametextField = new JTextField();
    }

    // MODIFIES: this
    // EFFECTS: changes location and size for name text field
    public void nameTextLocation() {
        nametextField.setPreferredSize(new Dimension(100, 100));
        //nametextField.setLocation((WIDTH / 2) + 100, HEIGHT / 2 + 400);
        buttonPanel.add(nametextField);
    }


    // EFFECTS: creates a text field for the goal time frame
    public void timeframeText() {
        tftextField = new JTextField();
    }

    // MODIFIES: this
    // EFFECTS: changes location and size for time frame text field
    public void timeframeTextLocation() {
        tftextField.setPreferredSize(new Dimension(100, 100));
        //tftextField.setLocation((WIDTH / 2) + 200, HEIGHT / 2 + 400);
        buttonPanel.add(tftextField);
    }

    // EFFECTS: creates a text field for the number of stars a goal has
    public void starText() {
        startextField = new JTextField();
    }

    // MODIFIES: this
    // EFFECTS: changes location and size for star text field
    public void starTextLocation() {
        startextField.setPreferredSize(new Dimension(100, 100));
        //startextField.setLocation((WIDTH / 2) + 200, HEIGHT / 2 + 400);
        buttonPanel.add(startextField);
    }

    // MODIFIES: this
    // EFFECTS: concatenates the name, time frame and star text fields into a single string
    public String makeGoal(String nameTrueText, String tfTrueText, String starTrueText) {
        return nameTrueText + ", " + tfTrueText + ", " + starTrueText + " stars";
    }

    // MODIFIES: this
    // EFFECTS: adds a name label to button panel
    public void nameLabel() {
        name = new JLabel("Goal Name");
        //name.setLocation(((WIDTH / 2) + 100), HEIGHT / 2 + 380);
        buttonPanel.add(name);
    }

    // MODIFIES: this
    // EFFECTS: adds a time frame label to button panel
    public void tfLabel() {
        tf = new JLabel("Time Frame");
       // tf.setLocation(((WIDTH / 2) + 200), HEIGHT / 2 + 380);
        buttonPanel.add(tf);
    }

    // MODIFIES: this
    // EFFECTS: adds a star label to button panel
    public void starsLabel() {
        stars = new JLabel("Number of Stars");
        //stars.setLocation(((WIDTH / 2) + 200), HEIGHT / 2 + 380);
        buttonPanel.add(stars);
    }

    // MODIFIES: this
    // EFFECTS: creates a button which saves the goal list
    public void saveButton() {
        saveButton = new JButton("Save List");
        saveButton.setBackground(Color.PINK);
        saveButton.setOpaque(true);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(agenda);
                    jsonWriter.close();
                    System.out.println("Saved! " + agenda.getName() + JSON_STORE);
                } catch (FileNotFoundException s) {
                    System.out.println("Error: unable to write to file " + JSON_STORE);
                }

            }
        });
    }

    // MODIFIES: this
    // EFFECTS: changes loction and size of save button
    public void saveButtonLocation() {
        saveButton.setPreferredSize(new Dimension(100, 100));
        //addButton.setLocation((WIDTH / 2), HEIGHT / 2 + 200);
        buttonPanel.add(saveButton);
    }

    // MODIFIES: this
    // EFFECTS: creates a button which loads a saved goal list
    public void loadButton() {
        loadButton = new JButton("Load List");
        loadButton.setBackground(Color.PINK);
        loadButton.setOpaque(true);
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    agenda = jsonReader.read();
                    System.out.println("Successfully loaded " + agenda.getName() + " from " + JSON_STORE);
                } catch (IOException d) {
                    System.out.println("Error: unable to read from file: " + JSON_STORE);
                }
                convertAgendaToList();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: changes location and size of load button
    public void loadButtonLocation() {
        loadButton.setPreferredSize(new Dimension(100, 100));
        //addButton.setLocation((WIDTH / 2), HEIGHT / 2 + 200);
        buttonPanel.add(loadButton);
    }

    // MODIFIES: this
    // EFFECTS: converts an agenda to strings of individual goals
    public void convertAgendaToList() {
        baseList.clear();
        for (Goal goal: agenda.getGoalList()) {
            int star = goal.getNumStars();
            String newGoal = makeGoal(goal.getName(),convertTimeframe(goal.getTimeFrame()),convertNumStars(star));
            baseList.addElement(newGoal);
        }
    }

    // MODIFIES: this
    // EFFECTS: changes time frame to a string
    public String convertTimeframe(Goal.TimeFrame timeframe) {
        if (timeframe.equals(Goal.TimeFrame.DAILY)) {
            return "daily";
        } else  if (timeframe.equals(Goal.TimeFrame.WEEKLY)) {
            return "weekly";
        } else  if (timeframe.equals(Goal.TimeFrame.MONTHLY)) {
            return "monthly";
        } else if (timeframe.equals(Goal.TimeFrame.YEARLY)) {
            return "yearly";
        } else if (timeframe.equals(Goal.TimeFrame.COMPLETED)) {
            return "completed";
        }
        return "";
    }

    // MODIFIES: this
    // EFFECTS: converts a string to a time frame
    public Goal.TimeFrame convertStringToTimeframe(String timeframe) {
        if (timeframe.equals("daily")) {
            return Goal.TimeFrame.DAILY;
        } else  if (timeframe.equals("weekly")) {
            return Goal.TimeFrame.WEEKLY;
        } else  if (timeframe.equals("monthly")) {
            return Goal.TimeFrame.MONTHLY;
        } else if (timeframe.equals("yearly")) {
            return Goal.TimeFrame.YEARLY;
        } else if (timeframe.equals("completed")) {
            return Goal.TimeFrame.COMPLETED;
        }
        return Goal.TimeFrame.DAILY;
    }

    public String convertNumStars(int stars) {
        return String.valueOf(stars);
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


    // MODIFIES: this
    // EFFECTS: used .wav file to play a sound
    // CITATION: used SOUND: downloaded from Sound Jay https://www.soundjay.com/magic-sound-effect.html
    // CITATION: method modled from http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    public void playsound() {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(SOUND).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }


    // EFFECTS: is not changed
    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    // MODIFIES: this
    // EFFECTS: runs the Strive GUI
    public static void main(String[] args) {
        //SwingUtilities.invokeLater(new Runnable() {
        new StriveGUI();
    }
}


