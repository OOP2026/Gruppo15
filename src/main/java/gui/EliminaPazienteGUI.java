package gui;

import controller.Controller;

import javax.swing.*;
import java.sql.SQLException;

public class EliminaPazienteGUI extends JFrame {
    private JPanel eliminaPazientePanel;
    private JTextField inserimentoTesseraField;
    private JLabel inserimentoTesseraLabel;
    private JButton eliminaButton;
    private JButton ritornaIndietroButton;

    public EliminaPazienteGUI(Controller controller, JFrame framePrecedente) {
        setContentPane(eliminaPazientePanel);
        setTitle("Elimina paziente");
        setSize(300, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo

        ritornaIndietroButton.addActionListener(e -> {
            framePrecedente.setVisible(true);
            dispose();
        });
        eliminaButton.addActionListener(e -> {
            try {
                controller.eliminaPaziente(inserimentoTesseraField.getText());
                JOptionPane.showMessageDialog(null, "Paziente eliminato con successo!");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
