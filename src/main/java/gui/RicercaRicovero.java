package gui;

import controller.Controller;
import model.Ricovero;

import javax.swing.*;

import java.sql.SQLException;
import java.util.List;

public class RicercaRicovero extends JFrame {
    private JPanel mostraRicoveriPanel;
    private JLabel ricoveriLabel;
    private JTextField tesseraSanitariaField;
    private JButton inviaButton;
    private JButton ritornaIndietroButton;

    public RicercaRicovero(Controller controller, JFrame frameprecedente){
        setContentPane(mostraRicoveriPanel);
        setTitle("RicercaRicovero");
        setSize(400, 300);
        setLocationRelativeTo(null); // Centra lo schermo


        inviaButton.addActionListener(e -> {


                List<Ricovero> ricoveri;
                try {
                    ricoveri = controller.mostraRicoveri(tesseraSanitariaField.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                VisualizzaRicoveroPerPazienteGUI visualizzaRicoveroPerPazienteGUI;
                try {
                    visualizzaRicoveroPerPazienteGUI = new VisualizzaRicoveroPerPazienteGUI(controller, frameprecedente, ricoveri);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                visualizzaRicoveroPerPazienteGUI.setVisible(true);
                dispose();


        });
        ritornaIndietroButton.addActionListener(e -> {
            frameprecedente.setVisible(true);
            dispose();
        });
    }
    }

