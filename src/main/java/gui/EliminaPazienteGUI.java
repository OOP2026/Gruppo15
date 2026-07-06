package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo

        ritornaIndietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                framePrecedente.setVisible(true);
                dispose();
            }

        });
        eliminaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.eliminaPaziente(inserimentoTesseraField.getText());
                    JOptionPane.showMessageDialog(null, "Paziente eliminato con successo!");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
