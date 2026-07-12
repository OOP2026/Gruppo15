package gui;

import controller.Controller;
import model.Paziente;

import javax.swing.*;
import java.sql.SQLException;

public class PazienteGUI extends JFrame {
    private JTextField tesseraSanitariaField;
    private JLabel tesseraLabel;
    private JLabel diagnosiLabel;
    private JTextField diagnosiField;
    private JTextField nomeField;
    private JLabel nomeLabel;
    private JTextField cognomeField;
    private JLabel cognomeLabel;
    private JButton aggiungiPazienteButton;
    private JButton ritornaIndietroButton;
    private JPanel pazientePanel;
    private JTextField curaField;
    private JLabel curaLabel;

    public PazienteGUI(Controller controller, JFrame framePrecedente, boolean modifica) {
        setContentPane(pazientePanel);
        setTitle("Paziente");
        setSize(300, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermoù
        if(modifica){
            aggiungiPazienteButton.setText("Modifica Paziente");
        }


        ritornaIndietroButton.addActionListener(e -> {
            framePrecedente.setVisible(true);
            dispose();
        });
        aggiungiPazienteButton.addActionListener(e -> {
            Paziente p = new Paziente(tesseraSanitariaField.getText(),nomeField.getText(),cognomeField.getText(),diagnosiField.getText(),curaField.getText());
            if(modifica){
                try {
                    controller.modificaPaziente(p);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
            else {
                try {
                    controller.aggiungiPaziente(p);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }

        });
    }
}
