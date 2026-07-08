package gui;

import controller.Controller;
import model.Agenda;
import model.SlotOrario;

import javax.swing.*;
import java.sql.SQLException;

public class AgendaGUI extends JFrame {

    private JPanel AgendaPanel;
    private JLabel agendaLabel;
    private Controller controller;
    public AgendaGUI(Controller controller) throws SQLException {
        this.controller = controller;
        setContentPane(AgendaPanel);
        setTitle("Agenda");
        setSize(400, 200);
        setLocationRelativeTo(null); // Centra lo schermo

        try {
            Agenda agenda = controller.mostraAgenda();
            String testoSlots = "<html>"; //variabile temporanea per aggiungere tutti gli slot presi con il get, letta come html

            // ciclo per mostrare tutti gli slot uno a uno

            for (SlotOrario slot : agenda.getSlots()) {
                testoSlots += slot.toString() + "<br>";
            }
            testoSlots += "</html>";

            // inserisco il testo ottenuto nell'areaAgenda
            agendaLabel.setText(testoSlots);
        }catch (SQLException e){
            e.printStackTrace();

            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);}
    }




}
