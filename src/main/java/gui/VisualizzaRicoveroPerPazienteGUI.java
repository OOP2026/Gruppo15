package gui;

import controller.Controller;
import model.Ricovero;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class VisualizzaRicoveroPerPazienteGUI extends JFrame {
    JFrame framePrecedente;
    private JPanel ricoveroPazientePanel;
    private JButton ritornaIndietroButton;
    private JTable tabledati;

    public VisualizzaRicoveroPerPazienteGUI(Controller controller, JFrame framePrecedente, List<Ricovero> ricoveri) throws SQLException {
        this.framePrecedente = framePrecedente;
        setContentPane(ricoveroPazientePanel);
        setTitle("Ricovero");
        setSize(850, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo

        String[] colonne = {"Tessera Sanitaria","N_Ricovero","MedicoAlbo", "Letto", "diagnosi", "reparto","Inizio ricovero","Fine ricovero","Data dimissione prevista"};
        DefaultTableModel model = new DefaultTableModel(colonne, 0);
        tabledati.setModel(model);
        for (Ricovero r : ricoveri) {
            int idReparto = r.getReparto();
            String nomeReparto = controller.getNomeReparto(idReparto);
            String ricoveroInformazione=null;
            if(r.getDataFine()==null){
                ricoveroInformazione="Ancora in ricovero";

            }
            else {
                ricoveroInformazione=r.getDataFine().toString();
            }//  Estrai i dati
            Object[] riga = {
                    r.getTessera_sanitaria(),           // Colonna 0
                    r.getId_ricovero(),
                    r.getMedico_id(),
                    r.getId_letto(),
                    r.getDiagnosi(),
                    nomeReparto,
                    r.getDataInizio().toString(),
                    ricoveroInformazione,
                    r.getDataDimissionePrevistaStamp(),
            };

            // 5. Aggiungi la riga appena creata al model della tabella
            model.addRow(riga);
        }




        ritornaIndietroButton.addActionListener(e -> {
            framePrecedente.setVisible(true);
            dispose();
        });
    }

}
