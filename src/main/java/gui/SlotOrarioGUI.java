package gui;

import controller.Controller;
import model.Agenda;
import model.SlotOrario;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalTime;

public class SlotOrarioGUI extends JFrame{
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
    private JComboBox giornoCombobox;
    private JComboBox orarioInizioCombobox;
    private JComboBox orarioFineCombobox;
    private JTextField idSlotField;
    private JLabel idSlotLabel;
    private transient Controller controller;
    private transient SlotOrario slotOrario;
    private static final Logger logger = Logger.getLogger(SlotOrarioGUI.class.getName());
    public SlotOrarioGUI(Controller controller, JFrame framePrecedente,boolean modifica){
        idSlotField.setVisible(false);
        idSlotLabel.setVisible(false);

        if(modifica){
            salvaButton.setText("Modifica");
            idSlotField.setVisible(true);
            idSlotLabel.setVisible(true);
            setTitle("Modifica slot orario");
        }
        setContentPane(registraSlotPanel);
        setTitle("Creazione Slot");
        setSize(300, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo


        //button per tornare alla schermata precedente
        annullaButton.addActionListener(e -> {
            framePrecedente.setVisible(true);
            dispose();
        });

        //button per salvare un nuovo slot orario nel db
        salvaButton.addActionListener(e -> {
            Agenda agenda = new Agenda();
            agenda.setId_agenda(Integer.parseInt(agendaField.getText()));

            if (modifica){
                 slotOrario = new SlotOrario(Objects.requireNonNull(giornoCombobox.getSelectedItem()).toString(), LocalTime.parse(Objects.requireNonNull(orarioInizioCombobox.getSelectedItem()).toString()), LocalTime.parse(Objects.requireNonNull(orarioFineCombobox.getSelectedItem()).toString()),agenda,Integer.parseInt(idSlotField.getText()));
            }
            else {
                 slotOrario = new SlotOrario(Objects.requireNonNull(giornoCombobox.getSelectedItem()).toString(), LocalTime.parse(Objects.requireNonNull(orarioInizioCombobox.getSelectedItem()).toString()), LocalTime.parse(Objects.requireNonNull(orarioFineCombobox.getSelectedItem()).toString()),agenda);
            }

            boolean salvato = false;
            try {
                if(modifica){
                    salvato= controller.modificaSlotOrario(slotOrario);
                }
                else {
                    salvato = controller.salvaSlotOrario(slotOrario);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                        SlotOrarioGUI.this,
                        "Errore durante il salvataggio nel database.",
                        "Errore",
                        JOptionPane.ERROR_MESSAGE
                );
                logger.log(Level.SEVERE, "Errore durante l'operazione nel database", ex);
            }

            if(salvato){
                JOptionPane.showMessageDialog(null, "Slot orario registrato!");
            }else{
                JOptionPane.showMessageDialog(null,"Errore durante il salvataggio.");
            }

        });
    }
}
