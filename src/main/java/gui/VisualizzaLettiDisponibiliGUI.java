package gui;

import controller.Controller;
import model.Letto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class VisualizzaLettiDisponibiliGUI extends JFrame{
    private JPanel lettiDisponibiliPanel;
    private JTable lettiDisponibiliTable;
    private JButton backButton;
    private JComboBox repartiBox;
    private JButton cercaButton;
    private JScrollPane scrollPanel;
    private Controller controller;
    private DefaultTableModel tableModel;


    public VisualizzaLettiDisponibiliGUI (Controller controller, JFrame frameprecedente){
        this.controller = controller;
        setContentPane(lettiDisponibiliPanel);
        setTitle("Elenco dei letti disponibili");
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        int idReparto;
        scrollPanel(lettiDisponibiliTable);

        String[] colonne = {"Codice", "Stanza", "Stato"};

        tableModel = new DefaultTableModel(colonne, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Rende la tabella non modificabile
            }
        };
        lettiDisponibiliTable.setModel(tableModel);

        cercaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int idReparto = 0;

                String reparto = repartiBox.getSelectedItem().toString();

                if (reparto.equals("Cardiologia")) {
                    idReparto = 1;
                } else if (reparto.equals("Chirurgia")) {
                    idReparto = 2;
                } else if (reparto.equals("Neurologia")) {
                    idReparto = 3;
                } else if (reparto.equals("Pediatria")) {
                    idReparto = 4;
                }

                System.out.println(idReparto);

                caricaLettiDisponibili(idReparto);
            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameprecedente.setVisible(true);

                dispose();
            }
        });
    }

    private void scrollPanel(JTable lettiDisponibiliTable) {
    }

    private void caricaLettiDisponibili(int idReparto) {
        try {

            tableModel.setRowCount(0);

            List<Letto> letti = controller.visualizzaLettiDisponibili(idReparto);

            System.out.println(idReparto);
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
