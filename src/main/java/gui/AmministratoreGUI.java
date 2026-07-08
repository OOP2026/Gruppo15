package gui;

import controller.Controller;
import model.Agenda;
import model.Paziente;
import model.SlotOrario;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class AmministratoreGUI extends JFrame {
    private JPanel amministratorePanel;
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
    private JButton eliminaSlotOrarioButton;
    private JButton visualizzaLettiDisponibiliButton;
    private JButton visualizzaPazientiInDimissioneButton;
    private JLabel amministratoreLabel;

    public AmministratoreGUI(Controller controller,JFrame framePrecedente) {
        setContentPane(amministratorePanel);
        setTitle("Amministratore");
        setSize(400, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo
        registraRicoveroButton.addActionListener(e -> {
            RicoveroGUI registraRicovero = new RicoveroGUI(controller,AmministratoreGUI.this, false);
            registraRicovero.setVisible(true);
            dispose();


        });
        ritornaIndietroButton.addActionListener(e -> {
            framePrecedente.setVisible(true);
            dispose();
        });


        visualizzaRicoveriButton.addActionListener(e -> {

            RicercaRicovero ricercaRicovero = new RicercaRicovero(controller, AmministratoreGUI.this);
            ricercaRicovero.setVisible(true);
            dispose();
        });

        //button per mostrare la schemrata di registra slot orario
        registraSlotOrarioButton.addActionListener(e -> {
            SlotOrarioGUI registraSlotOrario = new SlotOrarioGUI( controller, AmministratoreGUI.this,false);
            registraSlotOrario.setVisible(true);

            dispose();
        });
        modificaRicoveroButton.addActionListener(e -> {
            RicoveroGUI modificaRicovero = new RicoveroGUI(controller, AmministratoreGUI.this,true);
            modificaRicovero.setVisible(true);

        });
        modificaSlotOrarioButton.addActionListener(e -> {
            SlotOrarioGUI registraSlotOrario = new SlotOrarioGUI( controller, AmministratoreGUI.this,true);
            registraSlotOrario.setVisible(true);

            dispose();
        });

        visualizzaStrutturaOspedalieraButton.addActionListener(e -> {
            VisualizzaRepartiGUI visualizzaRepartiGUI = null;
            try {
                visualizzaRepartiGUI = new VisualizzaRepartiGUI(controller, AmministratoreGUI.this);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            visualizzaRepartiGUI.setVisible(true);

            dispose();
        });
        aggiungiPazienteButton.addActionListener(e -> {
            PazienteGUI pazienteGUI =new PazienteGUI(controller,AmministratoreGUI.this,false);
            pazienteGUI.setVisible(true);
            dispose();
        });
        modificaPazienteButton.addActionListener(e -> {
            PazienteGUI pazienteGUI =new PazienteGUI(controller,AmministratoreGUI.this,true);
            pazienteGUI.setVisible(true);
            dispose();
        });
        visualizzaTuttiIPazientiButton.addActionListener(e -> {
            List<Paziente> pazienti=null;

            pazienti = controller.mostraPazienti(); // da qua si blocca

            VisualizzaTuttiPazientiGUI visualizzaTuttiPazientiGUI = new VisualizzaTuttiPazientiGUI(controller, AmministratoreGUI.this, pazienti);
            visualizzaTuttiPazientiGUI.setVisible(true);
            dispose();
        });

        aggiungiMedicoButton.addActionListener(e -> {
            CreaMedicoGUI creaMedicoGUI = new CreaMedicoGUI(controller, AmministratoreGUI.this);
            creaMedicoGUI.setVisible(true);

            dispose();
        });
        assegnaAgendaAdUnButton.addActionListener(e -> {
            AssegnaAgendaGUI assegnaAgendaGUI = new AssegnaAgendaGUI(controller, AmministratoreGUI.this);
            assegnaAgendaGUI.setVisible(true);
            dispose();
        });
        visualizzaSlotOrariButton.addActionListener(e -> {
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

        });
        visualizzaAgendeAssegnateButton.addActionListener(e -> {
            List<Agenda> agendaList=null;
            try {
                agendaList=controller.mostraTutteLeAgende();
                VisualizzaAgendeAssegnateGUI agendeAssegnateGUI= new VisualizzaAgendeAssegnateGUI(controller,AmministratoreGUI.this,agendaList);
                agendeAssegnateGUI.setVisible(true);
                dispose();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        });

        visualizzaLettiDisponibiliButton.addActionListener(e -> {
            VisualizzaLettiDisponibiliGUI lettiDisponibiliGUI = new VisualizzaLettiDisponibiliGUI(controller, AmministratoreGUI.this);
            lettiDisponibiliGUI.setVisible(true);

            dispose();
        });
        visualizzaPazientiInDimissioneButton.addActionListener(e -> {
            VisualizzaPazientiInDimissioneGUI visualizzaPazientiInDimissioneGUI=new VisualizzaPazientiInDimissioneGUI(controller, AmministratoreGUI.this);
            visualizzaPazientiInDimissioneGUI.setVisible(true);
            dispose();
        });
    }



}
