package gui;

import controller.Controller;

import javax.swing.*;

public class AmministratoreGUI extends JFrame {
    private JPanel AmministratorePanel;

    public AmministratoreGUI(Controller controller) {
        setContentPane(AmministratorePanel);
        setTitle("Amministratore");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo
    }

}
