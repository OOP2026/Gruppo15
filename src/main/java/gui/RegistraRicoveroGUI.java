package gui;
import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistraRicoveroGUI extends JFrame {
    private JPanel RegistraRicoveroPanel;
    private JButton BackButton;
    private JTextField nomeField;
    private JTextField cognomeField;
    private JLabel nomeLabel;
    private JLabel cognomeLabel;
    private JTextField diagnosiField;
    private JLabel diagnosiLabel;
    private JTextField tesseraField;
    private JLabel tesseraLabel;
    private JButton inviaButton;
    private JFrame framePrecedente;

    public RegistraRicoveroGUI(Controller controller,JFrame framePrecedente) {
        this.framePrecedente = framePrecedente;
        setContentPane(RegistraRicoveroPanel);
        setTitle("Amministratore");
        setSize(300, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo


        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                framePrecedente.setVisible(true);
                dispose();
            }
        });


        inviaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.salvaRicovero(nomeField.getText(),cognomeField.getText(),tesseraField.getText(), diagnosiField.getText());
                JOptionPane.showMessageDialog(null, "Ricovero registrado com sucesso!");
                



            }
        });
    }


}
