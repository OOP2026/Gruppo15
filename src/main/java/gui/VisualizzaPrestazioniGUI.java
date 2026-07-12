package gui;

import controller.Controller;
import model.Prestazione;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class VisualizzaPrestazioniGUI extends JFrame {
    private JTable tabellaPrestazioni;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private transient Controller controller;
    private JPanel visualizzaDatiPanel;
    private JTable tableDati;
    private JButton ritornaIndietroButton;
    private static final Logger logger = Logger.getLogger(VisualizzaPrestazioniGUI.class.getName());
    public VisualizzaPrestazioniGUI(Controller controller, JFrame framePrecedente) {
        setContentPane(visualizzaDatiPanel);
        setTitle("Elenco Prestazioni Mediche");
        setSize(750, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.controller = controller;

        // 1. Definiamo le colonne della tabella
        String[] colonne = {"ID", "Paziente (Tessera)", "ID Medico", "Tipo", "Descrizione", "Esito", "Data"};

        tableModel = new DefaultTableModel(colonne, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Rende la tabella non modificabile
            }
        };
        tableDati.setModel(tableModel);


        caricaDatiTabelle();

        ritornaIndietroButton.addActionListener(e -> {
            framePrecedente.setVisible(true);
            dispose();
        });
    }

    private void caricaDatiTabelle() {
        try {
            // Puliamo la tabella prima di inserire i dati
            tableModel.setRowCount(0);

            // Chiamata al controller per prendere la lista
            List<Prestazione> prestazioni = controller.mostraTutteLePrestazioni();

            // Ciclo per aggiungere le righe al modello della tabella
            for (Prestazione p : prestazioni) {
                Object[] riga = {
                        p.getId(),
                        p.getTesseraPaziente(),
                        p.getMedicoAlbo(),
                        p.getTipo_prestazione(),
                        p.getDescrizione(),
                        p.getEsito(),
                        p.getData()
                };
                tableModel.addRow(riga);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Errore nel caricamento delle prestazioni dal database.", "Errore", JOptionPane.ERROR_MESSAGE);
            logger.log(Level.SEVERE, "Errore", e);
        }

    }
}