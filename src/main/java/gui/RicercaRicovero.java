package gui;

import controller.Controller;
import model.Ricovero;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RicercaRicovero extends JFrame {
    private JPanel mostraRicoveriPanel;
    private JLabel ricoveriLabel;
    private JTextField tesseraSanitariaField;
    private JButton inviaButton;
    private JButton ritornaIndietroButton;
    private JFrame frameprecedente;

    public RicercaRicovero(Controller controller, JFrame frameprecedente){
        this.frameprecedente = frameprecedente;
        setContentPane(mostraRicoveriPanel);
        setTitle("RicercaRicovero");
        setSize(400, 300);
        setLocationRelativeTo(null); // Centra lo schermo


        inviaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Ricovero ricovero;
                try {
                    ricovero = controller.mostraRicovero(tesseraSanitariaField.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                RicoveroPazienteGUI ricoveroPazienteGUI = new RicoveroPazienteGUI(controller, frameprecedente, ricovero);
                ricoveroPazienteGUI.setVisible(true);
                dispose();

            }
        });
        ritornaIndietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameprecedente.setVisible(true);
                dispose();
            }
        });
    }
    }

