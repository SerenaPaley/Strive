package ui.gui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextField extends Tool implements ActionListener, DocumentListener {
    private JTextField textField;
    private boolean enabled = false;
    private StriveGUI striveGUI;
    private JPanel panel;


    public TextField(AddButton addButton) {
        textField = new JTextField();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String goal = textField.getText();
        int index = -1;

        index = striveGUI.getGoalList().getSelectedIndex(); //get selected index
        if (index == -1) { //no selection, so insert at beginning
            index = 0;
        } else {           //add after the selected item
            index++;
        }

        striveGUI.getBaseList().addElement(textField.getText());

        striveGUI.getGoalList().setSelectedIndex(index);
        striveGUI.getGoalList().ensureIndexIsVisible(index);

    }

    @Override
    public void insertUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }

    public void createButton(JPanel panel) {
        this.panel = panel;
//        addButton = new JButton(addString);
        textField.setPreferredSize(new Dimension(300, 100));
        textField.setLocation(0,HEIGHT / 2 + 150);
        textField.setLocation(WIDTH / 2, HEIGHT / 2 + 400);
        this.panel.add(textField);
    }
}
