package gui;

import controller.Controller;
import model.Amministratore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AmministratoreGUI extends JFrame {
    private JPanel AmministratorePanel;
    private JLabel AmministratoreLabel;
    private JButton registraRicoveroButton;
    private JButton ritornaIndietroButton;
    private JButton visualizzaRicoveriButton;
    private JButton registraSlotOrarioButton;
    private JFrame framePrecedente;

    //aggiunto anche qui l'oggetto amministratore passato dal controller

    public AmministratoreGUI(Controller controller,JFrame framePrecedente, Amministratore admin) {
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

                RicercaRicovero ricercaRicovero = new RicercaRicovero(controller, AmministratoreGUI.this);
                ricercaRicovero.setVisible(true);
                dispose();
            }
        });

        //button per mostrare la schemrata di registra slot orario
        registraSlotOrarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistraSlotOrarioGUI registraSlotOrario = new RegistraSlotOrarioGUI( controller, AmministratoreGUI.this);
                registraSlotOrario.setVisible(true);

                dispose();
            }
        });
    }


}
