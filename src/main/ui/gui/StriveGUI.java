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
    //private JButton addButton;
    private JScrollPane scrollPane;
    private JTextField newGoal;
    private JPanel buttonPanel;
    private static int WIDTH = 500;
    private static int HEIGHT = 500;


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
        //add from phase 2

        //make list
        createList();

        //Create a panel for buttons at the bottom
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel,
                BoxLayout.LINE_AXIS));

        //create button
        AddButton addButton = new AddButton();
        addButton.createAddButton(buttonPanel);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(WIDTH,HEIGHT / 2,WIDTH,HEIGHT));

        add(scrollPane, BorderLayout.CENTER);

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


