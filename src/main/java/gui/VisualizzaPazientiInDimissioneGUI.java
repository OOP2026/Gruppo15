package gui;

import com.toedter.calendar.JDateChooser;
import controller.Controller;
import model.Ricovero;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VisualizzaPazientiInDimissioneGUI extends JFrame {
    private JPanel visualizzaPazientiPanel;
    private JPanel dataPanel;
    private JButton cercaButton;
    private JTable table1;
    private JButton ritornaIndietroButton;
    private JLabel messaggioLabel;
    private JDateChooser dataDimissionePrevista;
    private DefaultTableModel tableModel;
    public VisualizzaPazientiInDimissioneGUI(Controller controller,JFrame framePrecedente) {
        setContentPane(visualizzaPazientiPanel);
        setTitle("Amministratore");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);// Centra lo schermo
        table1.setVisible(false);
        dataDimissionePrevista = new JDateChooser();
        dataDimissionePrevista.setDateFormatString("dd/MM/yyyy");
        dataDimissionePrevista.getDateEditor().getUiComponent().setFocusable(false);
        dataPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        dataPanel.add(dataDimissionePrevista);

        String[] colonne = {"PazienteID", "Medico", "DataInizio", "DataFine","Reparto","Motivo","Letto","Data dimissione prevista"};

        tableModel = new DefaultTableModel(colonne, 0) ;
        table1.setModel(tableModel);

        cercaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table1.setVisible(true);
                Date dateDimissionePrevista = new Date();
                List<Ricovero> listaRicoveri = new ArrayList<>();
                dateDimissionePrevista=dataDimissionePrevista.getDate();
                try {
                    listaRicoveri=controller.mostraRicoveriDataDimissione(dateDimissionePrevista);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                tableModel.setRowCount(0);

                if (listaRicoveri.isEmpty()) {
                    table1.setVisible(false);
                    messaggioLabel.setText("Nessun paziente in dimissione il " + new SimpleDateFormat("dd/MM/yyyy").format(dataDimissionePrevista.getDate()));
                    return;
                }

                messaggioLabel.setText("");
                table1.setVisible(true);

                for(Ricovero ricovero : listaRicoveri) {
                    Object[] riga = {
                            ricovero.getTessera_sanitaria(),
                            ricovero.getMedico_id(),
                            ricovero.getDataInizio(),
                            ricovero.getDataFine(),
                            ricovero.getReparto(),
                            ricovero.getDiagnosi(),
                            ricovero.getId_letto(),
                            ricovero.getDataDimissionePrevistaStamp()
                    };
                    tableModel.addRow(riga);
                }
            }
        });

        ritornaIndietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                framePrecedente.setVisible(true);

                dispose();
            }
        });
    }
}
