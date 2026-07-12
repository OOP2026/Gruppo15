package gui;

import controller.Controller;
import model.Reparto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class VisualizzaRepartiGUI extends JFrame{
    private JTable repartiTable;
    private JScrollPane repertiScrollPane;
    private JPanel repartiPanel;
    private JButton backButton;
    private final transient Controller controller;
    private final DefaultTableModel tableModel;
    private List<Reparto> reparti;
    private static final Logger logger = Logger.getLogger(VisualizzaRepartiGUI.class.getName());
    public VisualizzaRepartiGUI(Controller controller, JFrame framePrecedente)  {
        setContentPane(repartiPanel);
        setTitle("Elenco dei letti disponibili");
        setSize(750, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.controller = controller;

        String[] colonne = {"Codice", "Stanza", "Stato"};

        tableModel = new DefaultTableModel(colonne, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Rende la tabella non modificabile
            }
        };
        repartiTable.setModel(tableModel);

        caricaReparti();

        repartiTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {

                    int riga = repartiTable.getSelectedRow();

                    if (riga != -1) {


                        Reparto repartoSelezionato = reparti.get(riga);
                        new DettagliRepartoGUI(repartoSelezionato).setVisible(true);
                    }
                }
            }
        });

        backButton.addActionListener(e -> {
            framePrecedente.setVisible(true);

            dispose();
        });


    }
    private void caricaReparti() {
        try {
            tableModel.setRowCount(0);

            reparti = controller.mostraReparti();

            // Ciclo per aggiungere le righe al modello della tabella
            for (Reparto r : reparti) {
                Object[] riga = {
                        r.getId(),
                        r.getNomeReparto(),
                        r.getPiano(),
                        r.getStanze()
                };
                tableModel.addRow(riga);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Errore nel caricamento delle prestazioni dal database.", "Errore", JOptionPane.ERROR_MESSAGE);
            logger.log(Level.SEVERE, "Errore", e);

        }
    }

}
