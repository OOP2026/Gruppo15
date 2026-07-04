package gui;

import controller.Controller;
import model.Ricovero;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class VisualizzaRicoveroPerPazienteGUI extends JFrame {
    JFrame framePrecedente;
    private JPanel RicoveroPazientePanel;
    private JButton ritornaIndietroButton;
    private JTable tabledati;

    public VisualizzaRicoveroPerPazienteGUI(Controller controller, JFrame framePrecedente, List<Ricovero> ricoveri) throws SQLException {
        this.framePrecedente = framePrecedente;
        setContentPane(RicoveroPazientePanel);
        setTitle("Ricovero");
        setSize(850, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo

        String[] colonne = {"Tessera Sanitaria","N_Ricovero","MedicoAlbo", "Letto", "diagnosi", "reparto","Inizio ricovero","Fine ricovero"};
        DefaultTableModel model = new DefaultTableModel(colonne, 0);
        tabledati.setModel(model);
        for (Ricovero r : ricoveri) {
            int idReparto = r.getReparto();
            String nomeReparto = controller.getNomeReparto(idReparto);
            //  Estrai i dati
            Object[] riga = {
                    r.getTessera_sanitaria(),           // Colonna 0
                    r.getId_ricovero(),
                    r.getMedico_id(),
                    r.getId_letto(),
                    r.getDiagnosi(),
                    nomeReparto,
                    r.getDataInizio().toString(),
                    r.getDataFine().toString()
            };

            // 5. Aggiungi la riga appena creata al model della tabella
            model.addRow(riga);
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
