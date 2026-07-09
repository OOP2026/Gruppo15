package gui;

import model.Agenda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class VisualizzaAgendeAssegnateGUI extends JFrame {
    private JPanel visualizzaAgendeAssegnate;
    private JTable tableDati;
    private JButton ritornaIndietroButton;

    public VisualizzaAgendeAssegnateGUI(JFrame framePrecedente, List<Agenda> agendaList)  {
        setContentPane(visualizzaAgendeAssegnate);
        setTitle("Visualizza agende assegnate");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo

        String[] colonne = {"id_agenda","id_medico"};
        DefaultTableModel model = new DefaultTableModel(colonne, 0);
        tableDati.setModel(model);

        for (Agenda agenda : agendaList) {
            Object[] riga={
                    agenda.getId_agenda(),
                    agenda.getId_medico(),

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
