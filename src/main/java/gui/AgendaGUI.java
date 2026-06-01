package gui;

import controller.Controller;
import model.*;
import javax.swing.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class AgendaGUI extends JFrame {

    private JPanel AgendaPanel;
    private JLabel LabelGiorno;
    private Controller controller;
    public AgendaGUI(Controller controller) {
        this.controller = controller;
        setContentPane(AgendaPanel);
        setTitle("Agenda");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo

        LabelGiorno.setText("Giorno: "+ controller.medicoMostraAgenda() );

    }




}
