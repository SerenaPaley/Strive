package ui.gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//CITATION: modeled off of List Demo Project
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html

public class StriveGUI extends JPanel implements ListSelectionListener {

    private JList goalList;
    private DefaultListModel baseList;

    private static final String removeString = "Remove goal";
    private JButton addButton;
    private static final String addString = "Add Goal";
    private JTextField nametextField;
    private JTextField tftextField;
    private JTextField startextField;
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


        //Create and set up the content pane.
        //JComponent newContentPane = new StriveGUI();
        //newContentPane.setOpaque(true); //content panes must be opaque
        //frame.setContentPane(newContentPane);
        //create list
        baseList = new DefaultListModel();
        baseList.addElement("Test goal");
        baseList.addElement("Test goal1");
        baseList.addElement("Test goal2");
        //add from phase 2

        //for each goal n goal list from phase 2 add it here

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
        c.insets = new Insets(4,75,4,75);
        buttonPanel.setBounds(0,(HEIGHT),WIDTH, (HEIGHT / 2) + 100);


        addButton();
        addButtonLocation();


        nameText();
        nameTextLocation();


        timeframeText();
        timeframeTextLocation();

        starText();
        starTextLocation();



        buttonPanel.setBorder(BorderFactory.createEmptyBorder(WIDTH,HEIGHT / 2,WIDTH,HEIGHT));
        add(scrollPane, BorderLayout.CENTER);

    }

    //add button ------------------------------------------------

    public void addButton() {
        //this.panel = panel;
        addButton = new JButton(addString);
        //createAddButton(panel);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                baseList.addElement(makeGoal(nametextField, tftextField, startextField));

            }
        });
    }

    public void addButtonLocation() {
        addButton.setPreferredSize(new Dimension(200, 200));
        //addButton.setLocation(0,HEIGHT / 2 + 5);
        addButton.setLocation((WIDTH / 2), HEIGHT / 2 + 200);
        buttonPanel.add(addButton);
    }

    //add button ------------------------------------------------


    // name textfield ------------------------------------------------

    public void nameText() {
        nametextField = new JTextField();
    }

    public void nameTextLocation() {
        nametextField.setPreferredSize(new Dimension(300, 100));
        nametextField.setLocation((WIDTH / 2) + 100, HEIGHT / 2 + 400);
        buttonPanel.add(nametextField);
    }
// name textfield ------------------------------------------------

    // timeframe textfield ------------------------------------------------

    public void timeframeText() {
        tftextField = new JTextField();

    }

    public void timeframeTextLocation() {
        tftextField.setPreferredSize(new Dimension(300, 100));
        tftextField.setLocation((WIDTH / 2) + 200, HEIGHT / 2 + 400);
        buttonPanel.add(tftextField);
    }
// timeframe textfield ------------------------------------------------

    // star textfield ------------------------------------------------

    public void starText() {
        startextField = new JTextField();
    }

    public void starTextLocation() {
        startextField.setPreferredSize(new Dimension(300, 100));
        startextField.setLocation((WIDTH / 2) + 200, HEIGHT / 2 + 400);
        buttonPanel.add(startextField);
    }
// star textfield ------------------------------------------------


    public String makeGoal(JTextField nametextField, JTextField tftextField, JTextField startextField) {
        return nametextField.getText() + ", " + tftextField.getText() + ", " + startextField.getText() + " stars";
    }


//    public String convertTimeframe(JTextField tftextField) {
//        String
//        if (tftextField.equals("D")) {
//
//        }
//    }

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







//    //effects blah blah blah
//    public void makeGUI() {
//
//    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    public static void main(String[] args) {
        //SwingUtilities.invokeLater(new Runnable() {
        new StriveGUI();




    }
}


