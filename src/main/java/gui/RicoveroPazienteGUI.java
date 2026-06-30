package gui;

import controller.Controller;
import model.Ricovero;

import javax.swing.*;
import java.sql.Timestamp;

public class RicoveroPazienteGUI extends JFrame {
    JFrame framePrecedente;
    private JPanel RicoveroPazientePanel;
    private JButton ritornaIndietroButton;
    private JLabel idPazienteLabel;
    private JLabel medicoIdLabel;
    private JLabel dataInizioLabel;
    private JLabel dataFineLabel;
    private JLabel motivoLabel;
    private JLabel repartoLabel;

    public RicoveroPazienteGUI(Controller controller, JFrame framePrecedente, Ricovero ricovero) {
        this.framePrecedente = framePrecedente;
        setContentPane(RicoveroPazientePanel);
        setTitle("Ricovero");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo
        idPazienteLabel.setText("Tessera: \n" + ricovero.getTessera_sanitaria());
        medicoIdLabel.setText("Albo medico: "+ Integer.toString(ricovero.getMedico_id()));
        dataInizioLabel.setText("Data inizio ricovero: "+ricovero.getDataInizio().toString());
        //dataFineLabel.setText(ricovero.getDataFine().toString());
        if(ricovero.getDataFine()==null){
            dataFineLabel.setText("Ancora in reparto");
        }
        else{
            dataFineLabel.setText(ricovero.getDataFine().toString());
        }
        motivoLabel.setText("Diagnosi: "+ ricovero.getDiagnosi());
       repartoLabel.setText("Ricovero: "+ ricovero.getReparto());





    }

}
