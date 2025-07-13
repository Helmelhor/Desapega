package view;

import com.desapega.Desapega_System.Domain.Models.Usuario;
import com.desapega.Desapega_System.Services.BDServices;
import com.desapega.Desapega_System.Domain.Enum.TipoUsuario;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class Jlogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Jlogin frame = new Jlogin();
                  //centralizando a tela
					frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Jlogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(176, 219, 156));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(176, 219, 156));
        panel.setBounds(82, 11, 254, 239);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setBounds(84, 46, 56, 14);
        panel.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(84, 74, 86, 20);
        panel.add(textField);
        textField.setColumns(10);

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(84, 105, 56, 14);
        panel.add(lblSenha);

        passwordField = new JPasswordField();
        passwordField.setBounds(84, 130, 86, 20);
        panel.add(passwordField);

        JLabel lblNewLabel_1 = new JLabel("Seja bem vindo!");
        lblNewLabel_1.setBounds(84, 11, 111, 14);
        panel.add(lblNewLabel_1);

        JButton btnNewButton = new JButton("Entrar");
        btnNewButton.setBounds(84, 170, 89, 23);
        panel.add(btnNewButton);

        btnNewButton.addActionListener(e -> {
            String usuarioDigitado = textField.getText();
            String senhaDigitada = new String(passwordField.getPassword());

            Usuario usuario = BDServices.autenticar(usuarioDigitado, senhaDigitada);
            if (usuario != null) {
                if (usuario.getTipo_usuario() == TipoUsuario.ADMIN) {
                    JTelaPrincipal telaPrincipal = new JTelaPrincipal();
                    telaPrincipal.setVisible(true);
                } else if (usuario.getTipo_usuario() == TipoUsuario.USUARIO) {
                    JTelaPagamentoPDV telaPagamento = new JTelaPagamentoPDV();
                    telaPagamento.setVisible(true);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos.");
            }
        });

        getRootPane().setDefaultButton(btnNewButton);
    }
}
