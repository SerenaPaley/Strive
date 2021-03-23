package ui.gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
//CITATION: modeled off of List Demo Project
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html

public class StriveGUI extends JPanel implements ListSelectionListener {

    private JList goalList;
    private DefaultListModel baseList;

    private static final String removeString = "Remove goal";
    private JButton addButton;
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

//        //Create a panel for buttons at the bottom
//        buttonPanel = new JPanel();
//        this.buttonPanel.setLayout(new GridBagLayout());
//        GridBagConstraints c = new GridBagConstraints();
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.insets = new Insets(4,75,4,75);
//        buttonPanel.setBounds(0,(HEIGHT),WIDTH, (HEIGHT / 2) + 100);
//        buttonPanel.setLayout(new BoxLayout(buttonPanel,
//                BoxLayout.LINE_AXIS));

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

        c.gridy = 0;
        c.gridx = 0;
        AddButton addButton = new AddButton();
        addButton.createAddButton(buttonPanel);

        c.gridy = 1;
        c.gridx = 1;
        TextField textField = new TextField(addButton);
        textField.createButton(buttonPanel);



        buttonPanel.setBorder(BorderFactory.createEmptyBorder(WIDTH,HEIGHT / 2,WIDTH,HEIGHT));
        add(scrollPane, BorderLayout.CENTER);

    }

    public JList getGoalList() {
        return goalList;
    }

    public DefaultListModel getBaseList() {
        return baseList;
    }




    //effects blah blah blah
    public void makeGUI() {

//        JFrame frame = new JFrame("Strive");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //Create and set up the content pane.
//        JComponent newContentPane = new StriveGUI();
//        //newContentPane.setOpaque(true); //content panes must be opaque
//        frame.setContentPane(newContentPane);

//        //show panel
//        frame.add(scrollPane);
//        frame.pack();
//        frame.setVisible(true);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    public static void main(String[] args) {
        //SwingUtilities.invokeLater(new Runnable() {
        new StriveGUI();



//        } );
    }
}


