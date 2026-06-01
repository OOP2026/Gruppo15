package gui;

import controller.Controller;

import javax.swing.*;

public class AgendaGUI extends JFrame {

    private JPanel AgendaPanel;

    public AgendaGUI(Controller controller) {
        setContentPane(AgendaPanel);
        setTitle("Agenda");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo

    }




}
