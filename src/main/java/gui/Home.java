package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {
    private Controller controller;
    private JButton Access;
    private JPanel HomePanel;
    private JTextField Utente;
    private JLabel UtenteLabel;
    private JPasswordField passwordField;
    private JLabel PasswordLabel;
    private JLabel Titolo;
    private JButton accediComeMedicoButton;

    public Home() {
        controller = new Controller();
        setContentPane(HomePanel);
        setTitle("Login");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null); // Centra lo schermo

        Access.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AmministratoreGUI secondagui=new AmministratoreGUI(controller,Home.this);
                secondagui.setVisible(true);
                dispose();
            }
        });

    }

    public static void main(String[] args) {
        Home frame = new Home();
    }
}
