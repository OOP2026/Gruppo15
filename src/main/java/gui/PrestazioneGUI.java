package gui;

import controller.Controller;
import model.Esito;
import model.Medico;
import model.Prestazione;
import model.TipoPrestazione;

import javax.swing.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrestazioneGUI extends JFrame {
    private JComboBox prestazioneComboBox;
    private JPanel creaPrestazionePanel;
    private JTextField descrizioneField;
    private JComboBox esitoComboBox;
    private JTextField tesseraField;
    private JButton inviaButton;
    private JButton ritornaIndietroButton;
    private JLabel tipoPrestazione;
    private JLabel descrizioneLabel;
    private JLabel esitoLabel;
    private JLabel tesseraLabel;
    private JTextField idPrestazioneField;
    private JLabel idPrestazioneLabel;
    private static final Logger logger = Logger.getLogger(PrestazioneGUI.class.getName());

    private TipoPrestazione tipoPrestazioneEnum;
    private Esito esitoEnum;
    private Timestamp timestamp;
    public PrestazioneGUI(Controller controller, JFrame frameprecedente, Medico medico, boolean modifica) {
        // 1. Configurazione comune della finestra (Rimosse le duplicazioni)
        setContentPane(creaPrestazionePanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        // 2. Configurazione specifica basata sulla modalità (Modifica vs Creazione)
        if (modifica) {
            setTitle("Modifica Prestazione");
            setSize(300, 350);
            inviaButton.setText("Modifica");
        } else {
            setTitle("Creazione prestazione");
            setSize(300, 300);
            idPrestazioneField.setVisible(false);
            idPrestazioneLabel.setVisible(false);
        }
        setLocationRelativeTo(null); // Centra lo schermo

        // 3. Delega dei listener a un metodo dedicato per abbattere la Complessità Cognitiva
        inizializzaListener(controller, frameprecedente, medico, modifica);
    }

    // Metodo di supporto per isolare la complessità logica dei bottoni
    private void inizializzaListener(Controller controller, JFrame frameprecedente, Medico medico, boolean modifica) {

        // Listener del tasto Invia (Salva / Modifica)
        inviaButton.addActionListener(e -> {
            // Mappatura pulita tramite metodi helper esternati
            tipoPrestazioneEnum = determinaTipoPrestazione(prestazioneComboBox.getSelectedIndex());
            esitoEnum = determinaEsito(esitoComboBox.getSelectedIndex());

            // Creazione dell'oggetto modello
            String descrizione = descrizioneField.getText();
            String tessera = tesseraField.getText();
            Prestazione prestazione = new Prestazione(tipoPrestazioneEnum, timestamp, esitoEnum, descrizione, tessera, medico.getId());

            // Controllo e parsing dell'ID solo se siamo in modalità modifica
            if (modifica) {
                try {
                    int idPrestazione = Integer.parseInt(idPrestazioneField.getText().trim());
                    prestazione.setId(idPrestazione);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PrestazioneGUI.this,
                            "Inserisci un ID prestazione valido per la modifica.",
                            "Errore Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Interazione con il Database tramite Controller
            boolean salvato = false;
            try {
                if (modifica) {
                    salvato = controller.modificaPrestazione(prestazione);
                } else {
                    salvato = controller.salvaPrestazione(prestazione);
                }
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, "Errore durante il salvataggio nel database", ex);
                JOptionPane.showMessageDialog(PrestazioneGUI.this, "Errore durante il salvataggio nel database.", "Errore", JOptionPane.ERROR_MESSAGE);
            }

            // Feedback finale all'utente
            if (salvato) {
                JOptionPane.showMessageDialog(null, "Prestazione registrata!");
            } else {
                JOptionPane.showMessageDialog(null, "Errore durante il salvataggio.");
            }
        });

        // Listener del tasto Indietro
        ritornaIndietroButton.addActionListener(e -> {
            frameprecedente.setVisible(true);
            dispose();
        });
    }
    private TipoPrestazione determinaTipoPrestazione(int index) {
        return (index == 0) ? TipoPrestazione.visita : TipoPrestazione.intervento;
    }

    private Esito determinaEsito(int index) {
        if (index == 0) {
            return Esito.inCorso;
        } else if (index == 1) {
            return Esito.positivo;
        } else {
            return Esito.negativo;
        }
    }


}
