package gui;

import controller.Controller;

import javax.swing.*;
import java.sql.SQLException;

public class AssegnaAgendaGUI extends JFrame {

    private JPanel assegnaAgendaPanel;
    private JTextField idAgendaField;
    private JLabel idAgenda;
    private JTextField idMedicoField;
    private JButton assegnaButton;
    private JButton ritornaIndietroButton;

    public AssegnaAgendaGUI(Controller controller, JFrame framePrecedente)  {
        setContentPane(assegnaAgendaPanel);
        setTitle("Amministratore");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo


        assegnaButton.addActionListener(e -> {
            try {
                controller.assegnaAgenda(Integer.parseInt(idAgendaField.getText()),Integer.parseInt(idMedicoField.getText()));

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        });
        ritornaIndietroButton.addActionListener(e -> {
            framePrecedente.setVisible(true);
            dispose();
        });
    }

}
