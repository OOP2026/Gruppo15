package gui;

import controller.Controller;
import model.Amministratore;
import model.Paziente;
import model.SlotOrario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

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
    private JButton visualizzaTuttiIPazientiButton;
    private JButton visualizzaSlotOrariButton;
    private JButton visualizzaAgendeAssegnateButton;
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
        aggiungiPazienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PazienteGUI pazienteGUI =new PazienteGUI(controller,AmministratoreGUI.this,false);
                pazienteGUI.setVisible(true);
                dispose();
            }
        });
        modificaPazienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PazienteGUI pazienteGUI =new PazienteGUI(controller,AmministratoreGUI.this,true);
                pazienteGUI.setVisible(true);
                dispose();
            }
        });
        visualizzaTuttiIPazientiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Paziente> pazienti=null;
                try {

                    pazienti = controller.mostraPazienti(); // da qua si blocca

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                VisualizzaTuttiPazientiGUI visualizzaTuttiPazientiGUI = new VisualizzaTuttiPazientiGUI(controller, AmministratoreGUI.this, pazienti);
                visualizzaTuttiPazientiGUI.setVisible(true);
                dispose();
            }
        });

        aggiungiMedicoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreaMedicoGUI creaMedicoGUI = new CreaMedicoGUI(controller, AmministratoreGUI.this);
                creaMedicoGUI.setVisible(true);

                dispose();
            }
        });
        assegnaAgendaAdUnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AssegnaAgendaGUI assegnaAgendaGUI = new AssegnaAgendaGUI(controller, AmministratoreGUI.this);
                assegnaAgendaGUI.setVisible(true);
                dispose();
            }
        });
        visualizzaSlotOrariButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<SlotOrario> slotOrari=null;
                try {
                   slotOrari= controller.mostraSlotOrari();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                VisualizzaSlotOrariGUI visualizzaSlotOrariGUI= null;
                try {
                    visualizzaSlotOrariGUI = new VisualizzaSlotOrariGUI(controller, AmministratoreGUI.this,slotOrari);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                visualizzaSlotOrariGUI.setVisible(true);
                dispose();

            }
        });
        visualizzaAgendeAssegnateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }



}
