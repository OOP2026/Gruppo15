package gui;

import controller.Controller;
import model.Medico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MedicoGUI extends JFrame {
    private JPanel MedicoPanel;
    private JButton mostraAgendaButton;
    private JButton ritornaIndietroButton;
    private JLabel nomeLabel;
    private JButton creaPrestazioneButton;
    private JButton modificaPrestazioneButton;
    private JButton visualizzaLePrestazioniButton;
    private JLabel cognomeLabel;
    private JFrame framePrecedente;

    //passiamo anche il medico per ottenere i dati da visualizzare
    public MedicoGUI(Controller controller,JFrame framePrecedente, Medico medico) {
        setContentPane(MedicoPanel);
        //assegno alle label create i valori ottenuti dall'oggetto medico
        nomeLabel.setText(medico.getNome()+ " "+ medico.getCognome());
        setTitle("Medico");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo
        mostraAgendaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgendaGUI agendaGUI = null;
                try {
                    agendaGUI = new AgendaGUI(controller, medico);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                agendaGUI.setVisible(true);

            }
        });
        ritornaIndietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                framePrecedente.setVisible(true);
                dispose();
            }
        });


        creaPrestazioneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrestazioneGUI prestazioneGUI = null;
                prestazioneGUI =new PrestazioneGUI(controller,MedicoGUI.this, medico,false);
                prestazioneGUI.setVisible(true);
                dispose();

            }
        });



        modificaPrestazioneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrestazioneGUI prestazioneGUI = null;
                prestazioneGUI =new PrestazioneGUI(controller,MedicoGUI.this, medico,true);
                prestazioneGUI.setVisible(true);
                dispose();
            }
        });
        visualizzaLePrestazioniButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualizzaPrestazioniGUI viewLista = new VisualizzaPrestazioniGUI(controller,MedicoGUI.this);
                viewLista.setVisible(true);
                dispose();
            }
        });
    }


}
