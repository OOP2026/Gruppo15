package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedicoGUI extends JFrame {
    private JPanel MedicoPanel;
    private JButton mostraAgendaButton;
    private JButton ritornaIndietroButton;
    private JFrame framePrecedente;
    public MedicoGUI(Controller controller,JFrame framePrecedente) {
        setContentPane(MedicoPanel);
        setTitle("Medico");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra lo schermo
        mostraAgendaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgendaGUI agendaGUI = new AgendaGUI(controller);
                controller.medicoMostraAgenda();

                agendaGUI.setVisible(true);

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
