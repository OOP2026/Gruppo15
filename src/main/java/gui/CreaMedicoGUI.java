package gui;

import controller.Controller;
import model.Medico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class CreaMedicoGUI extends JFrame{
    private JPanel creaMedicoPanel;
    private JLabel aggiungiMedicoLabel;
    private JButton annullaButton;
    private JButton salvaButton;
    private JLabel nomeLabel;
    private JTextField nomeField;
    private JLabel cognomeLabel;
    private JTextField cognomeField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JCheckBox mostraPasswordCheckBox;
    private Controller controller;

    public CreaMedicoGUI(Controller controller, JFrame framePrecedente){
        this.controller = controller;
        setContentPane(creaMedicoPanel);
        setTitle("Crea un medico");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo

        mostraPasswordCheckBox.addActionListener(e -> {
            if (mostraPasswordCheckBox.isSelected()) {
                // Mostra la password
                passwordField.setEchoChar((char) 0);
            } else {
                // Nasconde la password
                passwordField.setEchoChar('•'); // oppure '*'
            }
        });

        annullaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                framePrecedente.setVisible(true);

                dispose();
            }
        });

        salvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //controllo per non aggiungere un medico con campi vuoti "inutile se fatto anche da SQL"
                if (nomeField.getText().trim().isEmpty()
                        || cognomeField.getText().trim().isEmpty()
                        || emailField.getText().trim().isEmpty()
                        || passwordField.getPassword().length == 0) {

                    JOptionPane.showMessageDialog(
                            CreaMedicoGUI.this,
                            "Compila tutti i campi!",
                            "Errore",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Medico medico = new Medico();

                medico.setNome(nomeField.getText().trim());
                medico.setCognome(cognomeField.getText().trim());
                medico.setEmail(emailField.getText().trim());
                medico.setPassword(new String(passwordField.getPassword()));
                medico.setRuolo("medico");

                try {

                    boolean inserito = controller.aggiungiMedico(medico);

                    if (inserito) {
                        JOptionPane.showMessageDialog(
                                CreaMedicoGUI.this,
                                "Medico creato con successo!");

                        framePrecedente.setVisible(true);
                        dispose();

                    } else {

                        JOptionPane.showMessageDialog(
                                CreaMedicoGUI.this,
                                "Errore durante il salvataggio.",
                                "Errore",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(
                            CreaMedicoGUI.this,
                            ex.getMessage(),
                            "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }
}
