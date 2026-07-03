package gui;

import controller.Controller;
import model.Reparto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class VisualizzaRepartiGUI extends JFrame{
    private JTable repartiTable;
    private JScrollPane repertiScrollPane;
    private JPanel repartiPanel;
    private JButton backButton;
    private Controller controller;
    private DefaultTableModel tableModel;
    private List<Reparto> reparti;

    public VisualizzaRepartiGUI(Controller controller, JFrame framePrecedente){
        this.controller = controller;
        setContentPane(repartiPanel);
        setTitle("Elenco dei reparti presenti");
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] colonne = {"ID", "Reparto", "Piano"};

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

                        int idReparto = (int) tableModel.getValueAt(riga, 0);

                        Reparto repartoSelezionato = reparti.get(riga);
                        try {
                            new DettagliRepartoGUI(controller, VisualizzaRepartiGUI.this, repartoSelezionato).setVisible(true);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                framePrecedente.setVisible(true);

                dispose();
            }
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
            e.printStackTrace();
        }
    }

}
