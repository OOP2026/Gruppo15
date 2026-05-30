package gui;

import javax.swing.*;

public class Home extends JFrame {
    private JLabel Prova;
    private JButton Nero;
    private JPanel HomePanel;

    public Home() {
        setContentPane(HomePanel);
        setTitle("Home");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        Home frame = new Home();
    }
}
