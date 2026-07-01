package gui;

import controller.Controller;
import model.Agenda;
import model.SlotOrario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalTime;

public class RegistraSlotOrarioGUI extends JFrame{
    private JPanel registraSlotPanel;
    private JButton annullaButton;
    private JLabel giornoLabel;
    private JTextField giornoField;
    private JTextField oraInizioField;
    private JTextField agendaField;
    private JLabel oraInizioLabel;
    private JLabel oraFineLabel;
    private JLabel agendaLabel;
    private JButton salvaButton;
    private JTextField oraFineField;
    private Controller controller;
    private JFrame framePrecedente;

    public RegistraSlotOrarioGUI(Controller controller, JFrame framePrecedente){
        this.framePrecedente = framePrecedente;
        this.controller = controller;
        setContentPane(registraSlotPanel);
        setTitle("Creazione Slot");
        setSize(300, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo

        //button per tornare alla schermata precedente
        annullaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                framePrecedente.setVisible(true);
                dispose();
            }
        });

        //button per salvare un nuovo slot orario nel db
        salvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Agenda agenda = new Agenda();
                agenda.setId_agenda(Integer.parseInt(agendaField.getText()));


                SlotOrario slotOrario = new SlotOrario(giornoField.getText(), LocalTime.parse(oraInizioField.getText()), LocalTime.parse(oraFineField.getText()), agenda);
                boolean salvato = false;
                try {
                    salvato = controller.salvaSlotOrario(slotOrario);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(
                            RegistraSlotOrarioGUI.this,
                            "Errore durante il salvataggio nel database.",
                            "Errore",
                            JOptionPane.ERROR_MESSAGE
                    );
                    ex.printStackTrace();
                }

                if(salvato){
                    JOptionPane.showMessageDialog(null, "Slot orario registrato!");
                }else{
                    JOptionPane.showMessageDialog(null,"Errore durante il salvataggio.");
                }

            }
        });
    }
}
