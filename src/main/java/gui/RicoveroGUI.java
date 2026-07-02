package gui;
import controller.Controller;
import model.Paziente;
import model.Ricovero;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;

public class RicoveroGUI extends JFrame {
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
    private JTextField nomeField;
    private JTextField CognomeField;
    private JLabel cognomeLabel;
    private JLabel nomeLabel;
    private JTextField id_ricoveroField;
    private JLabel id_ricoveroLabel;
    private JFrame framePrecedente;

    public RicoveroGUI(Controller controller, JFrame framePrecedente, boolean modificaRicovero) {
        this.framePrecedente = framePrecedente;
        this.controller = controller;
        id_ricoveroField.setVisible(false);
        id_ricoveroLabel.setVisible(false);
        setContentPane(RegistraRicoveroPanel);
        setTitle("Amministratore");
        setSize(300, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo
        if (modificaRicovero) {
            inviaButton.setText("Modifica Ricovero");
            id_ricoveroField.setVisible(true);
            id_ricoveroLabel.setVisible(true);
            setSize(300, 500);
        }

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
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                Timestamp endtime=new Timestamp(System.currentTimeMillis());

                Paziente paziente = new Paziente(TesseraSanitariaField.getText(),nomeField.getText(),CognomeField.getText(),DiagnosiField.getText());
                //aggiunto codice che permette di provare a salvare il ricovero, in caso contrario va in errore
                boolean salvato = false;
                try {
                    if(modificaRicovero){
                        Ricovero ricovero = new Ricovero(TesseraSanitariaField.getText(),Integer.parseInt(MedicoAlboField.getText()),DiagnosiField.getText(),RepartoField.getText(),Integer.parseInt(LettoField.getText()),currentTime,endtime,Integer.parseInt(id_ricoveroField.getText()));
                        salvato=controller.modificaRicovero(ricovero,paziente);
                    }
                    else{
                        Ricovero ricovero = new Ricovero(TesseraSanitariaField.getText(),Integer.parseInt(MedicoAlboField.getText()),DiagnosiField.getText(),RepartoField.getText(),Integer.parseInt(LettoField.getText()),currentTime,endtime);
                        salvato = controller.salvaRicovero(ricovero,paziente);}
                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog(
                            RicoveroGUI.this,
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
