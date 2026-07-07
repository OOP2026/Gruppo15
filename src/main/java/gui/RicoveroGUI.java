package gui;
import com.toedter.calendar.JDateChooser;
import controller.Controller;
import model.Ricovero;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

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
    private JCheckBox fineRicoveroCheckBox;
    private JPanel dataPanel;
    private JFrame framePrecedente;
    private JDateChooser dataDimissionePrevista;
    private Timestamp dataDimissionePrevistaStamp;

    public RicoveroGUI(Controller controller, JFrame framePrecedente, boolean modificaRicovero) {
        this.framePrecedente = framePrecedente;
        this.controller = controller;
        id_ricoveroField.setVisible(false);
        id_ricoveroLabel.setVisible(false);
        fineRicoveroCheckBox.setVisible(false);
        setContentPane(RegistraRicoveroPanel);
        setTitle("Amministratore");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);// Centra lo schermo
        dataDimissionePrevista = new JDateChooser();
        dataDimissionePrevista.setDateFormatString("dd/MM/yyyy");
        dataDimissionePrevista.getDateEditor().getUiComponent().setFocusable(false);
        dataPanel.setLayout(new java.awt.BorderLayout());
        dataPanel.add(dataDimissionePrevista);

        if (modificaRicovero) {
            inviaButton.setText("Modifica Ricovero");
            id_ricoveroField.setVisible(true);
            id_ricoveroLabel.setVisible(true);
            setSize(300, 470);
            fineRicoveroCheckBox.setVisible(true);
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                framePrecedente.setVisible(true);
                dispose();
            }
        });
        fineRicoveroCheckBox.addActionListener(e -> {
            // 1. Controlla se la checkbox è selezionata
            boolean isChecked = fineRicoveroCheckBox.isSelected();

            // 2. Se è selezionata, DISATTIVA il calendario (setEnabled(false))
            //    Se non è selezionata, lo ATTIVA (setEnabled(true))
            dataDimissionePrevista.setEnabled(!isChecked);

            // 3. (Opzionale) Se vuoi anche svuotare la data quando viene spuntata la checkbox:
            if (isChecked) {
                dataDimissionePrevista.setDate(null);
            }
        });


        inviaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                Timestamp endtime=new Timestamp(System.currentTimeMillis());
                boolean salvato = false;
                int id_reparto=0;
                boolean fineRicovero=fineRicoveroCheckBox.isSelected();
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
               Date dataSelezionata = dataDimissionePrevista.getDate();
                if (dataSelezionata!=null){
                    dataDimissionePrevistaStamp=new Timestamp(dataSelezionata.getTime());
                }
                else {

                }


                try {

                    if(modificaRicovero && fineRicovero){
                        Ricovero ricovero = new Ricovero(tesseraSanitariaField.getText(),Integer.parseInt(medicoAlboField.getText()), diagnosiField.getText(),id_reparto,Integer.parseInt(lettoField.getText()),currentTime,endtime,Integer.parseInt(id_ricoveroField.getText()),dataDimissionePrevistaStamp);
                        salvato=controller.modificaRicovero(ricovero,fineRicovero);
                    }
                    else if (modificaRicovero){
                        Ricovero ricovero = new Ricovero(tesseraSanitariaField.getText(),Integer.parseInt(medicoAlboField.getText()), diagnosiField.getText(),id_reparto,Integer.parseInt(lettoField.getText()),currentTime,endtime,Integer.parseInt(id_ricoveroField.getText()),dataDimissionePrevistaStamp);
                        salvato = controller.modificaRicovero(ricovero,fineRicovero);}
                    else {
                        Ricovero ricovero =new Ricovero(tesseraSanitariaField.getText(),Integer.parseInt(medicoAlboField.getText()), diagnosiField.getText(),id_reparto,Integer.parseInt(lettoField.getText()),currentTime,endtime,dataDimissionePrevistaStamp);
                        salvato=controller.salvaRicovero(ricovero);
                    }
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
