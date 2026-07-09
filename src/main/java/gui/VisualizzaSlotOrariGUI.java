package gui;

import model.SlotOrario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.List;

public class VisualizzaSlotOrariGUI extends JFrame {
    private JPanel visualizzaSlotPanel;
    private JTable table1;
    private JButton ritornaIndietroButton;

    public VisualizzaSlotOrariGUI(JFrame framePrecedente, List<SlotOrario> slotOrari) {
        setContentPane(visualizzaSlotPanel);
        setTitle("Elenco dei slot presenti");
        setSize(750, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] colonne = {"ID", "Giorno", "Orario di inizio turno","Orario di fine turno","Medico"};

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
                    slotOrario.getAgenda().getMedico().getNome() + " " + slotOrario.getAgenda().getMedico().getCognome()


            };
            tableModel.addRow(riga);
        }


        ritornaIndietroButton.addActionListener(e -> {
            framePrecedente.setVisible(true);
            dispose();
        });
    }
}
