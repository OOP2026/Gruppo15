package gui;

import controller.Controller;
import model.Ricovero;
import model.SlotOrario;

import javax.swing.*;

public class MostraRicoveriGUI extends JFrame {
    private JLabel titoloLabel;
    private JTextArea textArea1;
    private JPanel mostraRicoveriPanel;
    private JFrame frameprecedente;

    public MostraRicoveriGUI(Controller controller, JFrame frameprecedente){
        this.frameprecedente = frameprecedente;
        setContentPane(mostraRicoveriPanel);
        setTitle("Ricoveri");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo

        String testoSlots = "<html>"; //variabile temporanea per aggiungere tutti gli slot presi con il get, letta come html
        

    }
    }

