package gui;

import controller.Controller;
import model.Letto;
import model.Reparto;
import model.Stanza;

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

        //ciclo for che permette di creare ogni stanza dato un determinato reparto
        for (Stanza stanza : reparto.getStanze()) {

            JPanel pannelloStanza = new JPanel();
            System.out.println(stanza.getCodice());
            System.out.println(stanza.getReparto());


            JLabel titolo = new JLabel("Stanza " + stanza.getCodice());

            pannelloStanza.add(titolo);
            pannelloStanza.setLayout(new BoxLayout(pannelloStanza, BoxLayout.Y_AXIS));

            //ciclo for che consente di creare sotto ogni stanza la lista di tutti i letti di quella stanza
            for (Letto letto : stanza.getLetti()) {

                JPanel rigaLetto = new JPanel(new GridLayout(1, 2));

                rigaLetto.add(new JLabel("Letto " + letto.getId_letto()));
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
                pannelloStanza.add(rigaLetto);
            }


            contenitorePanel.add(pannelloStanza);
        }

    }
}
