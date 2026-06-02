package gui;

import controller.Controller;
import model.SlotOrario;

import javax.swing.*;

public class AgendaGUI extends JFrame {

    private JPanel AgendaPanel;
    private JLabel LabelGiorno;
    private JTextArea areaAgenda;
    private Controller controller;
    public AgendaGUI(Controller controller) {
        this.controller = controller;
        setContentPane(AgendaPanel);
        setTitle("Agenda");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo

      //  LabelGiorno.setText("Giorno: "+ controller.medicoMostraAgenda() );


        String testoSlots = ""; //variabile temporanea per aggiungere tutti gli slot presi con il get

        // ciclo per mostrare tutti gli slot uno a uno

        for(SlotOrario slot : controller.medicoMostraAgenda()){
            testoSlots += slot.toString() + "\n";
        }
        // inserisco il testo ottenuto nell'areaAgenda
        areaAgenda.setText(testoSlots);
    }




}
