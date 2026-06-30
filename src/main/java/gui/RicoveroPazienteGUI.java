package gui;

import controller.Controller;
import model.Ricovero;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RicoveroPazienteGUI extends JFrame {
    JFrame framePrecedente;
    private JPanel RicoveroPazientePanel;
    private JButton ritornaIndietroButton;
    private JTable tabledati;
    private JLabel idPazienteLabel;
    private JLabel medicoIdLabel;
    private JLabel dataInizioLabel;
    private JLabel dataFineLabel;
    private JLabel motivoLabel;
    private JLabel repartoLabel;

    public RicoveroPazienteGUI(Controller controller, JFrame framePrecedente, List<Ricovero> ricoveri) {
        this.framePrecedente = framePrecedente;
        setContentPane(RicoveroPazientePanel);
        setTitle("Ricovero");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo

        String[] colonne = {"id_paziente", "MedicoAlbo", "Letto", "diagnosi", "reparto"};
        DefaultTableModel model = new DefaultTableModel(colonne, 0);
        tabledati.setModel(model);
        for (Ricovero r : ricoveri) {

            // 4. Estrai i dati dall'oggetto usando i getter della tua classe Ricovero
            // NOTA: Sostituisci i metodi sotto con i veri getter della tua classe!
            Object[] riga = {
                    r.getTessera_sanitaria(),           // Colonna 0
                    r.getMedico_id(),        // Colonna 1 (es. Nome del paziente o oggetto Paziente)
                    r.getId_letto(),
                    r.getDiagnosi(),
                    r.getReparto()
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
