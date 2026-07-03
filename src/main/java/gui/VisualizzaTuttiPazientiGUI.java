package gui;

import controller.Controller;
import model.Paziente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;


public class VisualizzaTuttiPazientiGUI extends JFrame {
    JFrame framePrecedente;
    private JPanel VisualizzaPazientiPanel;
    private JTable tabledati;

    public VisualizzaTuttiPazientiGUI(Controller controller, JFrame framePrecedente, List<Paziente> listaPazienti) {
        this.framePrecedente = framePrecedente;
        setContentPane(VisualizzaPazientiPanel);
        setTitle("Visualizza pazienti");
        setSize(850, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo

        String[] colonne = {"Tessera Sanitaria","Nome","Cognome", "Diagnosi", "Cura"};
        DefaultTableModel model = new DefaultTableModel(colonne, 0);
        tabledati.setModel(model);

        for (Paziente p : listaPazienti) {
            Object[] riga={
                 p.getTessera(),
                 p.getNome(),
                 p.getCognome(),
                 p.getDiagnosi(),
                 p.getCura(),

            };
            // 5. Aggiungi la riga appena creata al model della tabella
            model.addRow(riga);
        }
    }
}
