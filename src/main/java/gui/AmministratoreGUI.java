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
    private JButton modificaRicoveroButton;
    private JButton modificaSlotOrarioButton;
    private JButton assegnaAgendaAdUnButton;
    private JButton aggiungiMedicoButton;
    private JButton aggiungiPazienteButton;
    private JButton modificaPazienteButton;
    private JButton visualizzaStrutturaOspedalieraButton;
    private JFrame framePrecedente;

    //aggiunto anche qui l'oggetto amministratore passato dal controller

    public AmministratoreGUI(Controller controller,JFrame framePrecedente, Amministratore admin) {
        this.framePrecedente=framePrecedente;
        setContentPane(AmministratorePanel);
        setTitle("Amministratore");
        setSize(300, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo

        registraRicoveroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RicoveroGUI registraRicovero = new RicoveroGUI(controller,AmministratoreGUI.this, false);
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
                SlotOrarioGUI registraSlotOrario = new SlotOrarioGUI( controller, AmministratoreGUI.this,false);
                registraSlotOrario.setVisible(true);

                dispose();
            }
        });
        modificaRicoveroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RicoveroGUI modificaRicovero = new RicoveroGUI(controller, AmministratoreGUI.this,true);
                modificaRicovero.setVisible(true);

            }
        });
        modificaSlotOrarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SlotOrarioGUI registraSlotOrario = new SlotOrarioGUI( controller, AmministratoreGUI.this,true);
                registraSlotOrario.setVisible(true);

                dispose();
            }
        });

        visualizzaStrutturaOspedalieraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualizzaRepartiGUI visualizzaRepartiGUI = new VisualizzaRepartiGUI(controller, AmministratoreGUI.this);
                visualizzaRepartiGUI.setVisible(true);

                dispose();
            }
        });
    }


}
