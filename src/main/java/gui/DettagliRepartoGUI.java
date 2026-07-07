package gui;

import controller.Controller;
import model.Letto;
import model.Reparto;
import model.Stanza;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class DettagliRepartoGUI extends JFrame{
    private JPanel dettagliRepartoPanel;
    private JPanel contenitorePanel;
    private Controller controller;

    public DettagliRepartoGUI(Controller controller, JFrame framePrecedente, Reparto reparto) throws SQLException {
        this.controller = controller;
        setContentPane(dettagliRepartoPanel);
        setTitle("Dettagli del reparto");
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        contenitorePanel.setLayout(new BoxLayout(contenitorePanel, BoxLayout.Y_AXIS));

        List<Stanza> stanzeReparto = controller.mostraStanze(reparto.getId());
        JPanel intestazione = new JPanel(new GridLayout(1, 3));

        intestazione.add(new JLabel("Stanza"));
        intestazione.add(new JLabel("Letto"));
        intestazione.add(new JLabel("Stato"));

        contenitorePanel.add(intestazione);


        //ciclo for che permette di creare ogni stanza dato un determinato reparto
        for (Stanza stanza : reparto.getStanze()) {
            stanza.setReparto(reparto);

            JPanel stanzaPanel = new JPanel();
            stanzaPanel.setLayout(new BoxLayout(stanzaPanel, BoxLayout.Y_AXIS));

            if (stanza.getLetti().isEmpty()) {

                JPanel riga = new JPanel(new GridLayout(1,3));
                riga.add(new JLabel("Stanza " + stanza.getCodice()));
                riga.add(new JLabel("-"));
                riga.add(new JLabel("Nessun letto"));

                stanzaPanel.add(riga);
            }

            //ciclo for che consente di creare sotto ogni stanza la lista di tutti i letti di quella stanza
            for (Letto letto : stanza.getLetti()) {

                JPanel rigaLetto = new JPanel(new GridLayout(1, 3));
                rigaLetto.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

                JLabel lblStanza = new JLabel(String.valueOf(stanza.getCodice()));
                JLabel lblLetto = new JLabel(String.valueOf(letto.getId_letto()));

                rigaLetto.add(lblStanza);
                rigaLetto.add(lblLetto);
                JLabel lblStato = new JLabel();
                System.out.println(letto.getStato());
                if(letto.getStato()){

                   lblStato.setText("Occupato");
                   lblStato.setForeground(Color.RED);
                } else {
                    lblStato.setText("Libero");
                    lblStato.setForeground(Color.GREEN);
                }
                rigaLetto.add(lblStato);
                stanzaPanel.add(rigaLetto);
            }


            contenitorePanel.add(stanzaPanel);
        }

    }
}
