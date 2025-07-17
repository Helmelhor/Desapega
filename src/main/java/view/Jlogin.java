package view;

import com.desapega.Desapega_System.Domain.Models.Usuario;
import com.desapega.Desapega_System.Services.BDServices;
import com.desapega.Desapega_System.Domain.Enum.TipoUsuario;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Jlogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;

    // Paleta de cores
    private final Color COLOR_BG = Color.decode("#153448");
    private final Color COLOR_PANEL = Color.decode("#948979");
    private final Color COLOR_BTN = Color.decode("#F5F5DC"); // fundo do botão mais claro (bege)
    private final Color COLOR_BTN_TEXT = Color.decode("#153448");
    private final Color COLOR_LABEL = Color.decode("#DFD0B8");
    private final Color COLOR_FIELD_BG = Color.decode("#DFD0B8");
    private final Color COLOR_FIELD_TEXT = Color.decode("#153448");

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
        setBounds(100, 100, 430, 320);
        contentPane = new JPanel();
        contentPane.setBackground(COLOR_BG);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(COLOR_PANEL);
        panel.setBounds(60, 20, 300, 240);
        panel.setLayout(null);
        contentPane.add(panel);

        JLabel lblNewLabel_1 = new JLabel("Seja bem vindo!");
        lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblNewLabel_1.setForeground(COLOR_BG); // cor escura
        lblNewLabel_1.setBounds(70, 10, 180, 25);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblNewLabel.setForeground(COLOR_BG); // cor escura
        lblNewLabel.setBounds(60, 50, 56, 20);
        panel.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(60, 75, 180, 28);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        textField.setBackground(COLOR_FIELD_BG);
        textField.setForeground(COLOR_FIELD_TEXT);
        textField.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
        panel.add(textField);

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblSenha.setForeground(COLOR_BG); // cor escura
        lblSenha.setBounds(60, 110, 56, 20);
        panel.add(lblSenha);

        passwordField = new JPasswordField();
        passwordField.setBounds(60, 135, 180, 28);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        passwordField.setBackground(COLOR_FIELD_BG);
        passwordField.setForeground(COLOR_FIELD_TEXT);
        passwordField.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
        panel.add(passwordField);

        JButton btnNewButton = new JButton("Entrar");
        btnNewButton.setBounds(60, 180, 180, 32);
        btnNewButton.setBackground(COLOR_BTN); // fundo mais claro
        btnNewButton.setForeground(COLOR_BTN_TEXT);
        btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnNewButton.setFocusPainted(false);
        btnNewButton.setBorder(BorderFactory.createLineBorder(COLOR_LABEL, 2, true));
        btnNewButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
