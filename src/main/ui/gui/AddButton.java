//package ui.gui;
//
//import model.Goal;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class AddButton extends Tool {
//
//    private JButton addButton;
//    private static final String addString = "Add Goal";
//    private static int HEIGHT = 500;
//    private static int WIDTH = 500;
//    private JPanel panel;
//    private TextField textField;
//    private StriveGUI striveGUI;
//
//
//    public void AddButton() {
//        //this.panel = panel;
//        addButton = new JButton(addString);
//        //createAddButton(panel);
//        addButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                striveGUI.getBaseList().addElement(textField.getTextField());
//
//            }
//        });
//    }
//
//    public void createAddButton(JPanel panel) {
//        this.panel = panel;
//       addButton = new JButton(addString);
//        addButton.setPreferredSize(new Dimension(200, 200));
//        addButton.setLocation(0,HEIGHT / 2 + 5);
//        addButton.setLocation(WIDTH / 2, HEIGHT / 2 + 200);
//        this.panel.add(addButton);
//    }
//
////    public Goal retrieveGoal(//something from the Jtextfield) {
////
////
////    }
//
//
//    public void actionPerformed(ActionEvent e) {
//        striveGUI.getBaseList().addElement(textField.getTextField());
//
//    }
//}
