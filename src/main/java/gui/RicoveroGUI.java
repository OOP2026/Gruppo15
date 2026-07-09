package gui;
import com.toedter.calendar.JDateChooser;
import controller.Controller;
import model.Ricovero;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RicoveroGUI extends JFrame {
    private transient Controller controller;
    private JPanel registraRicoveroPanel;
    private JButton backButton;
    private JTextField tesseraSanitariaField;
    private JTextField medicoAlboField;
    private JLabel tesseraLabel;
    private JLabel medicoAlboLabel;
    private JLabel diagnosiLabel;
    private JButton inviaButton;
    private JTextField diagnosiField;
    private JLabel repartoLabel;
    private JLabel idLettoLABEL;
    private JTextField lettoField;
    private JTextField idRicoveroField;
    private JLabel idRicoveroLabel;
    private JComboBox repartoComboBox;
    private JCheckBox fineRicoveroCheckBox;
    private JPanel dataPanel;
    private JDateChooser dataDimissionePrevista;
    private Timestamp dataDimissionePrevistaStamp;
    private static final Logger logger = Logger.getLogger(RicoveroGUI.class.getName());
    public RicoveroGUI(Controller controller, JFrame framePrecedente, boolean modificaRicovero) {
        // 1. Inizializzazione degli stati di default delle componenti della GUI
        idRicoveroField.setVisible(false);
        idRicoveroLabel.setVisible(false);
        fineRicoveroCheckBox.setVisible(false);
        setContentPane(registraRicoveroPanel);
        setTitle("Amministratore");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Configurazione del JDateChooser
        dataDimissionePrevista = new JDateChooser();
        dataDimissionePrevista.setDateFormatString("dd/MM/yyyy");
        dataDimissionePrevista.getDateEditor().getUiComponent().setFocusable(false);
        dataPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        dataPanel.add(dataDimissionePrevista);

        // 2. Modifiche condizionali della GUI in base alla modalità
        if (modificaRicovero) {
            inviaButton.setText("Modifica Ricovero");
            idRicoveroField.setVisible(true);
            idRicoveroLabel.setVisible(true);
            setSize(300, 470);
            fineRicoveroCheckBox.setVisible(true);
        } else {
            setSize(300, 400);
        }

        // 3. ORA CENTRIAMO LO SCHERMO (Ora che setSize è definitivo!)
        setLocationRelativeTo(null);

        // 4. Delega della logica dei Listener per azzerare la complessità
        inizializzaListener(controller, framePrecedente, modificaRicovero);
    }

    // Metodo helper per gestire tutti i Listener delle azioni utente
    private void inizializzaListener(Controller controller, JFrame framePrecedente, boolean modificaRicovero) {

        // Listener pulsante Indietro (Lambda)
        backButton.addActionListener(e -> {
            framePrecedente.setVisible(true);
            dispose();
        });

        // Listener CheckBox Fine Ricovero (Lambda)
        fineRicoveroCheckBox.addActionListener(e -> {
            boolean isChecked = fineRicoveroCheckBox.isSelected();
            dataDimissionePrevista.setEnabled(!isChecked);
            if (isChecked) {
                dataDimissionePrevista.setDate(null);
            }
        });

        // Listener pulsante Invia (Convertito in Lambda pulita)
        inviaButton.addActionListener(e -> {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            Timestamp endtime = new Timestamp(System.currentTimeMillis());
            boolean fineRicovero = fineRicoveroCheckBox.isSelected();

            // Estrazione dell'ID reparto tramite metodo helper dedicato
            int idReparto = determinaIdReparto(repartoComboBox.getSelectedItem().toString());

            // Parsing della data selezionata
            Date dataSelezionata = dataDimissionePrevista.getDate();
            if (dataSelezionata != null) {
                dataDimissionePrevistaStamp = new Timestamp(dataSelezionata.getTime());
            }

            boolean salvato = false;
            try {
                // Estrazione parametri dai campi di testo
                String tessera = tesseraSanitariaField.getText();
                int idMedico = Integer.parseInt(medicoAlboField.getText());
                String diagnosi = diagnosiField.getText();
                int letto = Integer.parseInt(lettoField.getText());

                if (modificaRicovero) {
                    int idRicovero = Integer.parseInt(idRicoveroField.getText());
                    Ricovero ricovero = new Ricovero(tessera, idMedico, diagnosi, idReparto, letto, currentTime, endtime, idRicovero, dataDimissionePrevistaStamp);
                    salvato = controller.modificaRicovero(ricovero, fineRicovero);
                } else {
                    Ricovero ricovero = new Ricovero(tessera, idMedico, diagnosi, idReparto, letto, currentTime, endtime, dataDimissionePrevistaStamp);
                    salvato = controller.salvaRicovero(ricovero);
                }
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, "Errore durante il salvataggio nel database", ex);
                JOptionPane.showMessageDialog(RicoveroGUI.this, "Errore durante il salvataggio nel database.", "Errore", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(RicoveroGUI.this, "Verifica che i campi numerici siano corretti.", "Errore Input", JOptionPane.ERROR_MESSAGE);
            }

            // Feedback all'utente
            if (salvato) {
                JOptionPane.showMessageDialog(null, "Ricovero registrato!");
            } else {
                JOptionPane.showMessageDialog(null, "Errore durante il salvataggio.");
            }
        });
    }

    // Metodo di supporto per mappare le stringhe della ComboBox negli ID del DB
    private int determinaIdReparto(String nomeReparto) {
        if (nomeReparto.equals("Cardiologia")) return 1;
        if (nomeReparto.equals("Chirurgia")) return 2;
        if (nomeReparto.equals("Neurologia")) return 3;
        if (nomeReparto.equals("Pediatria")) return 4;
        return 0;
    }


}
