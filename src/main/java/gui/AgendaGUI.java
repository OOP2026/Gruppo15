package gui;

import controller.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Agenda;
import model.SlotOrario;

import javax.swing.*;
import java.sql.SQLException;

public class AgendaGUI extends JFrame {

    private static final Logger logger = Logger.getLogger(AgendaGUI.class.getName());
    private JPanel agendaPanel;
    private JLabel agendaLabel;
    private transient Controller controller;
    public AgendaGUI(Controller controller) throws SQLException {
        setContentPane(agendaPanel);
        setTitle("Agenda");
        setSize(400, 200);
        setLocationRelativeTo(null); // Centra lo schermo

        try {
            Agenda agenda = controller.mostraAgenda();

            // 1. Inizializza lo StringBuilder
            StringBuilder testoSlots = new StringBuilder("<html>");

            // ciclo per mostrare tutti gli slot uno a uno
            for (SlotOrario slot : agenda.getSlots()) {
                // 2. Usa il metodo .append() al posto di +=
                testoSlots.append(slot.toString()).append("<br>");
            }

            // 3. Aggiungi la chiusura del tag HTML
            testoSlots.append("</html>");

            // 4. Converti in stringa normale alla fine con .toString()
            agendaLabel.setText(testoSlots.toString());

        } catch (SQLException e) {
            // Nota: anche qui SonarQube ti segnalerà e.printStackTrace(),
            // puoi sostituirlo usando il logger come fatto in precedenza.

            logger.log(Level.SEVERE, "Errore durante la lettura dell'agenda", e);

            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);
        }
    }




}
