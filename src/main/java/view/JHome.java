package view;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class JHome extends JFrame {
    public JHome() {
        setTitle("Tela Inicial");
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblBemVindo = new JLabel("Bem-vindo à tela inicial!");
        lblBemVindo.setBounds(100, 100, 250, 20);
        getContentPane().add(lblBemVindo);
    }
}
