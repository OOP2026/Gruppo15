package gui;

import controller.Controller;
import model.Medico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedicoGUI extends JFrame {
    private JPanel MedicoPanel;
    private JButton mostraAgendaButton;

    public MedicoGUI(Controller controller) {
        setContentPane(MedicoPanel);
        setTitle("Medico");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo
        mostraAgendaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgendaGUI agendaGUI = new AgendaGUI(controller);
                agendaGUI.setVisible(true);
                dispose();
            }
        });
    }
}
