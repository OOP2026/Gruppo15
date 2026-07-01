package gui;

import controller.Controller;
import model.Esito;
import model.Medico;
import model.Prestazione;
import model.TipoPrestazione;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;

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
    private JTextField id_prestazioneField;
    private JLabel id_prestazioneLabel;


    private TipoPrestazione tipoPrestazioneEnum;
    private Esito esitoEnum;
    private Timestamp timestamp;
    public PrestazioneGUI(Controller controller, JFrame frameprecedente, Medico medico, boolean modifica) {
        if (modifica) {
            setContentPane(creaPrestazionePanel);
            setTitle("Modifica Prestazione");
            setSize(300, 350);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null); // Centra lo schermo
            inviaButton.setText("Modifica");
        }
        else {
            setContentPane(creaPrestazionePanel);
            setTitle("Creazione prestazione");
            setSize(300, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null); // Centra lo schermo
            id_prestazioneField.setVisible(false);
            id_prestazioneLabel.setVisible(false);
        }

        inviaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(prestazioneComboBox.getSelectedIndex()==0){
                    tipoPrestazioneEnum=TipoPrestazione.visita;
                }
                else {
                    tipoPrestazioneEnum=TipoPrestazione.intervento;
                }
                if (esitoComboBox.getSelectedIndex()==0){
                    esitoEnum=Esito.inCorso;
                }
                else if (esitoComboBox.getSelectedIndex()==1) {
                    esitoEnum=Esito.positivo;
                }
                else if (esitoComboBox.getSelectedIndex()==2) {
                    esitoEnum=Esito.negativo;
                }

               Prestazione prestazione = new Prestazione(tipoPrestazioneEnum,timestamp,esitoEnum,descrizioneField.getText(),tesseraField.getText(), medico.getId());

                if (modifica) {
                    try {
                        int idPrestazione = Integer.parseInt(id_prestazioneField.getText().trim());
                        prestazione.setId(idPrestazione); // Assicurati che la classe Prestazione abbia il metodo setId(int id)
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(PrestazioneGUI.this, "Inserisci un ID prestazione valido per la modifica.", "Errore Input", JOptionPane.ERROR_MESSAGE);
                        return; // Interrompe l'esecuzione se l'ID non è numerico
                    }
                }

                boolean salvato=false;
                try {
                    if(modifica){
                        salvato=controller.modificaPrestazione(prestazione);
                    }
                 else {
                        salvato = controller.salvaPrestazione(prestazione);
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(
                            PrestazioneGUI.this,
                            "Errore durante il salvataggio nel database.",
                            "Errore",
                            JOptionPane.ERROR_MESSAGE
                    );
                    ex.printStackTrace();
                }

                if(salvato){
                    JOptionPane.showMessageDialog(null, "Prestazione registrata!");
                }else{
                    JOptionPane.showMessageDialog(null,"Errore durante il salvataggio.");
                }
            }
        });


        ritornaIndietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameprecedente.setVisible(true);
                dispose();
            }
        });
    }


}
