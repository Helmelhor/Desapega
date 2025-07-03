package view;

import com.desapega.Desapega_System.repository.UsuarioRepository;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
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
			String usuario = textField.getText();
			String senha = new String(passwordField.getPassword());

			boolean autenticado = UsuarioRepository.autenticar(usuario, senha);
			if (autenticado) {
				JHome home = new JHome();
				home.setVisible(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos.");
			}
		});
	}
}
