package gui;

import controller.Controller;
import model.Medico;

import javax.swing.*;
import java.sql.SQLException;

public class MedicoGUI extends JFrame {
    private JPanel medicoPanel;
    private JButton mostraAgendaButton;
    private JButton ritornaIndietroButton;
    private JLabel nomeLabel;
    private JButton creaPrestazioneButton;
    private JButton modificaPrestazioneButton;
    private JButton visualizzaLePrestazioniButton;
    private JLabel cognomeLabel;

    //passiamo anche il medico per ottenere i dati da visualizzare
    public MedicoGUI(Controller controller,JFrame framePrecedente, Medico medico) {
        setContentPane(medicoPanel);
        //assegno alle label create i valori ottenuti dall'oggetto medico
        nomeLabel.setText(medico.getNome()+ " "+ medico.getCognome());
        setTitle("Medico");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo
        mostraAgendaButton.addActionListener(e -> {
            AgendaGUI agendaGUI;
            try {
                agendaGUI = new AgendaGUI(controller);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            agendaGUI.setVisible(true);

        });
        ritornaIndietroButton.addActionListener(e -> {
            framePrecedente.setVisible(true);
            dispose();
        });


        creaPrestazioneButton.addActionListener(e -> {
            PrestazioneGUI prestazioneGUI;
            prestazioneGUI =new PrestazioneGUI(controller,MedicoGUI.this, medico,false);
            prestazioneGUI.setVisible(true);
            dispose();

        });



        modificaPrestazioneButton.addActionListener(e -> {
            PrestazioneGUI prestazioneGUI;
            prestazioneGUI =new PrestazioneGUI(controller,MedicoGUI.this, medico,true);
            prestazioneGUI.setVisible(true);
            dispose();
        });
        visualizzaLePrestazioniButton.addActionListener(e -> {
            VisualizzaPrestazioniGUI viewLista = new VisualizzaPrestazioniGUI(controller, MedicoGUI.this);
            viewLista.setVisible(true);
            dispose();
        });
    }


}
