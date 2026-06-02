package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AmministratoreGUI extends JFrame {
    private JPanel AmministratorePanel;
    private JLabel AmministratoreLabel;
    private JButton registraRicoveroButton;
    private JButton ritornaIndietroButton;
    private JButton visualizzaRicoveriButton;
    private JFrame framePrecedente;

    public AmministratoreGUI(Controller controller,JFrame framePrecedente) {
        this.framePrecedente=framePrecedente;
        setContentPane(AmministratorePanel);
        setTitle("Amministratore");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo

        registraRicoveroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistraRicoveroGUI registraRicovero = new RegistraRicoveroGUI(controller,AmministratoreGUI.this);
                registraRicovero.setVisible(true);
                dispose();

            }
        });
        ritornaIndietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                framePrecedente.setVisible(true);
                dispose();
            }
        });


        visualizzaRicoveriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MostraRicoveriGUI mostraRicoveriGUI = new MostraRicoveriGUI(controller, AmministratoreGUI.this);
                mostraRicoveriGUI.setVisible(true);
            }
        });
    }

}
