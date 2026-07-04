package gui;

import controller.Controller;
import model.SlotOrario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class VisualizzaSlotOrariGUI extends JFrame {
    private JPanel visualizzaSlotPanel;
    private JTable table1;
    private JButton ritornaIndietroButton;

    public VisualizzaSlotOrariGUI(Controller controller, JFrame framePrecedente, List<SlotOrario> slotOrari) throws SQLException {
        setContentPane(visualizzaSlotPanel);
        setTitle("Elenco dei slot presenti");
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] colonne = {"ID", "Giorno", "Orario di inizio turno","Orario di fine turno","Agenda associata"};

        DefaultTableModel tableModel = new DefaultTableModel(colonne, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Rende la tabella non modificabile
            }
        };
        table1.setModel(tableModel);

        for (SlotOrario slotOrario : slotOrari) {
            Object[] riga = {
                    slotOrario.getId_slot(),           // Colonna 0
                    slotOrario.getGiorno(),
                    slotOrario.getOraInizio(),
                    slotOrario.getOraFine(),
                    slotOrario.getAgenda().getId_agenda()


            };
            tableModel.addRow(riga);
        }


        ritornaIndietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                framePrecedente.setVisible(true);
                dispose();
            }
        });
    }
}
