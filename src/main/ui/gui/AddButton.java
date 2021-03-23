package ui.gui;

import model.Goal;

import javax.swing.*;
import java.awt.*;

public class AddButton {

    private JButton addButton;
    private static final String addString = "Add Goal";
    private static int HEIGHT = 500;
    private static int WIDTH = 500;
    private JPanel panel;


    public AddButton() {
        //this.panel = panel;
        addButton = new JButton(addString);
        //createAddButton(panel);
    }

    public void createAddButton(JPanel panel) {
        this.panel = panel;
//        addButton = new JButton(addString);
        addButton.setPreferredSize(new Dimension(200, 200));
        addButton.setLocation(0,HEIGHT / 2 + 5);
        addButton.setLocation(WIDTH / 2, HEIGHT / 2 + 200);
        this.panel.add(addButton);
    }

//    public Goal retrieveGoal(//something from the Jtextfield) {
//
//
//    }
}
