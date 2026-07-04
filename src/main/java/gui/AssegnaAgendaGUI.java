package gui;

import controller.Controller;
import model.Medico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AssegnaAgendaGUI extends JFrame {

    private JPanel assegnaAgendaPanel;
    private JTextField idAgendaField;
    private JLabel idAgenda;
    private JTextField idMedicoField;
    private JButton assegnaButton;

    public AssegnaAgendaGUI(Controller controller, JFrame framePrecedente)  {
        setContentPane(assegnaAgendaPanel);
        setTitle("Amministratore");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo


        assegnaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.assegnaAgenda(Integer.parseInt(idAgendaField.getText()),Integer.parseInt(idMedicoField.getText()));

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

}
