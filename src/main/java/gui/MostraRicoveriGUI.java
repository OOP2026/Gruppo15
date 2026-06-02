package gui;

import controller.Controller;
import model.Ricovero;

import javax.swing.*;

public class MostraRicoveriGUI extends JFrame {
    private JPanel mostraRicoveriPanel;
    private JLabel ricoveriLabel;
    private JLabel ricoveriOutput;
    private JFrame frameprecedente;

    public MostraRicoveriGUI(Controller controller, JFrame frameprecedente){
        this.frameprecedente = frameprecedente;
        setContentPane(mostraRicoveriPanel);
        setTitle("Ricoveri");
        setSize(300, 200);
        setLocationRelativeTo(null); // Centra lo schermo

        ricoveriOutput.setText(controller.mostraRicovero());


        

    }
    }

