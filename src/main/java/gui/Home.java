package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
        controller = new Controller(this);
        setContentPane(HomePanel);
        setTitle("Login");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null); // Centra lo schermo

        //modificato l'access in modo che non venga aperta immediatamente la prossima schermata, ma che venga prima eseguito il metodo "eseguiLogin"
        //il controller aprirà poi medicoGUI o amministratoreGUI in base ai dati inseriti

        Access.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = Utente.getText();
                String password = new String(passwordField.getPassword());

                try {
                    controller.eseguiLogin(email, password);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf()); // Oppure FlatDarkLaf() per il tema scuro
        } catch(Exception ex) {
            System.err.println("Impossibile caricare il tema");
        }
        Home frame = new Home();
    }
}
