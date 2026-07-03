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
    private JButton backButton;
    private JTextField tesseraSanitariaField;
    private JTextField medicoAlboField;
    private JLabel tesseraLabel;
    private JLabel medicoAlboLabel;
    private JLabel diagnosiLabel;
    private JButton inviaButton;
    private JTextField diagnosiField;
    private JTextField RepartoField;
    private JLabel repartoLabel;
    private JLabel idLettoLABEL;
    private JTextField lettoField;
    private JTextField id_ricoveroField;
    private JLabel id_ricoveroLabel;
    private JComboBox repartoComboBox;
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

        backButton.addActionListener(new ActionListener() {
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
                //aggiunto codice che permette di provare a salvare il ricovero, in caso contrario va in errore
                boolean salvato = false;
                int id_reparto=0;
                if(repartoComboBox.getSelectedItem().toString().equals("Cardiologia")){
                    id_reparto=1;
                }
                else if(repartoComboBox.getSelectedItem().toString().equals("Chirurgia")){
                    id_reparto=2;
                }
                else if(repartoComboBox.getSelectedItem().toString().equals("Neurologia")){
                    id_reparto=3;
                }
                else if(repartoComboBox.getSelectedItem().toString().equals("Pediatria")){
                    id_reparto=4;
                }

                try {
                    if(modificaRicovero){
                        Ricovero ricovero = new Ricovero(tesseraSanitariaField.getText(),Integer.parseInt(medicoAlboField.getText()), diagnosiField.getText(),id_reparto,Integer.parseInt(lettoField.getText()),currentTime,endtime,Integer.parseInt(id_ricoveroField.getText()));
                        salvato=controller.modificaRicovero(ricovero);
                    }
                    else{
                        Ricovero ricovero = new Ricovero(tesseraSanitariaField.getText(),Integer.parseInt(medicoAlboField.getText()), diagnosiField.getText(),id_reparto,Integer.parseInt(lettoField.getText()),currentTime,endtime);
                        salvato = controller.salvaRicovero(ricovero);}
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
