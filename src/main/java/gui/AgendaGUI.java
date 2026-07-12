package gui;

import controller.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Agenda;
import model.SlotOrario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class AgendaGUI extends JFrame {

    private static final Logger logger = Logger.getLogger(AgendaGUI.class.getName());
    private JPanel agendaPanel;
    private JScrollPane scrollPanel;
    private JTable agendaTable;
    private transient Controller controller;

    public AgendaGUI(Controller controller) throws SQLException {
        setContentPane(agendaPanel);
        setTitle("Agenda");
        setSize(700, 500);
        setLocationRelativeTo(null); // Centra lo schermo

        String[] colonne = {"Giorno", "Inizio", "Fine"};
        DefaultTableModel model = new DefaultTableModel(colonne, 0){
            @Override
            public  boolean isCellEditable(int row, int column){
                return false;
            }
        };

        try {
            Agenda agenda = controller.mostraAgenda();

            // ciclo per mostrare tutti gli slot uno a uno
            for (SlotOrario slot : agenda.getSlots()) {
                model.addRow(new Object[]{
                        slot.getGiorno(),
                        slot.getOraInizio(),
                        slot.getOraFine()
                });
            }

            agendaTable.setModel(model);

        } catch (SQLException e) {
            // Nota: anche qui SonarQube ti segnalerà e.printStackTrace(),
            // puoi sostituirlo usando il logger come fatto in precedenza.

            logger.log(Level.SEVERE, "Errore durante la lettura dell'agenda", e);

            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);
        }
    }




}
