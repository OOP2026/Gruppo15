package gui;

import controller.Controller;

import javax.swing.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Home extends JFrame {
    private static final Logger logger = Logger.getLogger(Home.class.getName());
    private final transient Controller controller;
    private JButton access;
    private JPanel homePanel;
    private JTextField utente;
    private JLabel utenteLabel;
    private JPasswordField passwordField;
    private JLabel passwordLabel;
    private JLabel titolo;
    private JCheckBox mostraPasswordCheckBox;

    public Home() {
        controller = new Controller(this);
        setContentPane(homePanel);
        setTitle("Login");
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null); // Centra lo schermo

        //checkbox che permette di mostrare e nascondere la password
        mostraPasswordCheckBox.addActionListener(e -> {
            if (mostraPasswordCheckBox.isSelected()) {
                // Mostra la password
                passwordField.setEchoChar((char) 0);
            } else {
                // Nasconde la password
                passwordField.setEchoChar('•'); // oppure '*'
            }
        });

        //modificato l'access in modo che non venga aperta immediatamente la prossima schermata, ma che venga prima eseguito il metodo "eseguiLogin"
        //il controller aprirà poi medicoGUI o amministratoreGUI in base ai dati inseriti

        access.addActionListener(e -> {
            String email = utente.getText();
            String password = new String(passwordField.getPassword());

            try {
                controller.eseguiLogin(email, password);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        });

    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf()); // Oppure FlatDarkLaf() per il tema scuro
        } catch(Exception ex) {
            logger.log(Level.SEVERE, "Impossibile caricare il tema", ex);
        }
        Home frame = new Home();
        frame.setVisible(true);
    }
}
