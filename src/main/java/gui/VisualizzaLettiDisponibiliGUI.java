package gui;

import controller.Controller;
import model.Letto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class VisualizzaLettiDisponibiliGUI extends JFrame{
    private JPanel lettiDisponibiliPanel;
    private JTable lettiDisponibiliTable;
    private JButton backButton;
    private JComboBox repartiBox;
    private JButton cercaButton;
    private JScrollPane scrollPanel;
    private final transient Controller controller;
    private final DefaultTableModel tableModel;


    public VisualizzaLettiDisponibiliGUI (Controller controller, JFrame frameprecedente){
        this.controller = controller;
        setContentPane(lettiDisponibiliPanel);
        setTitle("Elenco dei letti disponibili");
        setSize(750, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        scrollPanel(lettiDisponibiliTable);

        String[] colonne = {"Codice", "Stanza", "Stato"};

        tableModel = new DefaultTableModel(colonne, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Rende la tabella non modificabile
            }
        };
        lettiDisponibiliTable.setModel(tableModel);

        cercaButton.addActionListener(e -> {

            int idReparto = 0;

            String reparto = Objects.requireNonNull(repartiBox.getSelectedItem()).toString();

            switch (reparto) {
                case "Cardiologia":
                    idReparto = 1;
                    break;
                case "Chirurgia":
                    idReparto = 2;
                    break;
                case "Neurologia":
                    idReparto = 3;
                    break;
                case "Pediatria":
                    idReparto = 4;
                    break;
            }



            caricaLettiDisponibili(idReparto);
        });


        backButton.addActionListener(e -> {
            frameprecedente.setVisible(true);

            dispose();
        });
    }

    private void scrollPanel(JTable lettiDisponibiliTable) {
        // Daniele inserisci motivazione
    }

    private void caricaLettiDisponibili(int idReparto) {
        try {

            tableModel.setRowCount(0);

            List<Letto> letti = controller.visualizzaLettiDisponibili(idReparto);


            for (Letto letto : letti) {

                Object[] riga = {
                        letto.getId_letto(),
                        letto.getStanza().getCodice(),
                        "Disponibile"
                };

                tableModel.addRow(riga);
            }


        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Errore nel caricamento dei letti.",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
