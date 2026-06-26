package gui;
import controller.Controller;
import model.Ricovero;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RegistraRicoveroGUI extends JFrame {
    private Controller controller;
    private JPanel RegistraRicoveroPanel;
    private JButton BackButton;
    private JTextField TesseraSanitariaField;
    private JTextField MedicoAlboField;
    private JLabel TesseraLabel;
    private JLabel MedicoAlboLabel;
    private JLabel diagnosiLabel;
    private JButton inviaButton;
    private JTextField DiagnosiField;
    private JTextField RepartoField;
    private JLabel RepartoLabel;
    private JLabel IdLettoLABEL;
    private JTextField LettoField;
    private JFrame framePrecedente;

    public RegistraRicoveroGUI(Controller controller,JFrame framePrecedente) {
        this.framePrecedente = framePrecedente;
        this.controller = controller;
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
                Ricovero ricovero = new Ricovero(TesseraSanitariaField.getText(),Integer.parseInt(MedicoAlboField.getText()),DiagnosiField.getText(),RepartoField.getText(),Integer.parseInt(LettoField.getText()));

                //aggiunto codice che permette di provare a salvare il ricovero, in caso contrario va in errore
                boolean salvato = false;
                try {
                    salvato = controller.salvaRicovero(ricovero);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(
                            RegistraRicoveroGUI.this,
                            "Errore durante il salvataggio nel database.",
                            "Errore",
                            JOptionPane.ERROR_MESSAGE
                    );
                    ex.printStackTrace();
                }

                if(salvato){
                    JOptionPane.showMessageDialog(null, "Ricovero registrato!");
                }else{
                    JOptionPane.showMessageDialog(null,"Errore durante il salvataggio.");
                }

            }
        });
    }


}
