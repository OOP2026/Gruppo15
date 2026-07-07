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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VisualizzaPazientiInDimissioneGUI extends JFrame {
    private JPanel visualizzaPazientiPanel;
    private JPanel dataPanel;
    private JButton cercaButton;
    private JTable table1;
    private JButton ritornaIndietroButton;
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
    }
}
