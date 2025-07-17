package view;

import com.desapega.Desapega_System.Domain.Models.Funcionario;
import com.desapega.Desapega_System.Domain.Models.Usuario;
import com.desapega.Desapega_System.Services.BDServices;
import com.desapega.Desapega_System.Domain.Enum.TipoUsuario;
import org.mindrot.jbcrypt.BCrypt;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JCadastroFuncionario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField preencherNome;
	private JTextField preencherEmail;
	private JFormattedTextField preencherTelefone;

	// Paleta de cores
	private final Color COLOR_BG = Color.decode("#153448");
	private final Color COLOR_BTN = Color.decode("#F5F5DC"); // fundo dos botões mais claro (bege)
	private final Color COLOR_BTN_TEXT = Color.decode("#153448");
	private final Color COLOR_LABEL = Color.decode("#153448"); // texto dos labels escuro
	private final Color COLOR_PANEL = Color.decode("#948979");
	private final Color COLOR_FIELD_BG = Color.decode("#DFD0B8");
	private final Color COLOR_FIELD_TEXT = Color.decode("#153448");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCadastroFuncionario frame = new JCadastroFuncionario();
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
	public JCadastroFuncionario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		contentPane = new JPanel();
		contentPane.setBackground(COLOR_BG);
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // Centraliza a tela sempre que for aberta

		JPanel panel = new JPanel();
		panel.setBackground(COLOR_PANEL);
		panel.setBounds(10, 10, 664, 640);
		panel.setLayout(null);
		contentPane.add(panel);

		JLabel lblCadastrarFuncionario = new JLabel("Cadastrar funcionário");
		lblCadastrarFuncionario.setBounds(120, 5, 420, 38);
		lblCadastrarFuncionario.setFont(new Font("Segoe UI", Font.BOLD, 32));
		lblCadastrarFuncionario.setForeground(COLOR_BG);
		lblCadastrarFuncionario.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblCadastrarFuncionario);

		JLabel lblNome = new JLabel("Nome Completo");
		lblNome.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNome.setForeground(COLOR_LABEL); // escuro
		lblNome.setBounds(36, 102, 180, 20);
		panel.add(lblNome);

		preencherNome = new JTextField();
		preencherNome.setBounds(36, 127, 400, 28);
		preencherNome.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		preencherNome.setBackground(COLOR_FIELD_BG);
		preencherNome.setForeground(COLOR_FIELD_TEXT);
		preencherNome.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(preencherNome);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblCpf.setForeground(COLOR_LABEL); // escuro
		lblCpf.setBounds(36, 170, 100, 20);
		panel.add(lblCpf);

		JFormattedTextField campoCPF = null;
		try {
			MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
			cpfMask.setPlaceholderCharacter('_');
			campoCPF = new JFormattedTextField(cpfMask);
			campoCPF.setBounds(36, 195, 269, 28);
			campoCPF.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			campoCPF.setBackground(COLOR_FIELD_BG);
			campoCPF.setForeground(COLOR_FIELD_TEXT);
			campoCPF.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
			panel.add(campoCPF);
		} catch (Exception e) {
			e.printStackTrace();
		}

		JLabel lblDataNascimento = new JLabel("Data de Nascimento");
		lblDataNascimento.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblDataNascimento.setForeground(COLOR_LABEL); // escuro
		lblDataNascimento.setBounds(36, 242, 180, 20);
		panel.add(lblDataNascimento);

		JComboBox dataNascimento = new JComboBox();
		dataNascimento.setBounds(36, 267, 43, 28);
		dataNascimento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		dataNascimento.setBackground(COLOR_FIELD_BG);
		dataNascimento.setForeground(COLOR_FIELD_TEXT);
		panel.add(dataNascimento);
		for (int data = 1; data <= 31; data++) {
			dataNascimento.addItem(data);
		}

		JComboBox mesNascimento = new JComboBox();
		mesNascimento.setBounds(89, 267, 43, 28);
		mesNascimento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mesNascimento.setBackground(COLOR_FIELD_BG);
		mesNascimento.setForeground(COLOR_FIELD_TEXT);
		panel.add(mesNascimento);
		for (int mes = 1; mes <= 12; mes++) {
			mesNascimento.addItem(mes);
		}

		JComboBox anoNascimento = new JComboBox<>();
		anoNascimento.setBounds(142, 267, 80, 28);
		anoNascimento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		anoNascimento.setBackground(COLOR_FIELD_BG);
		anoNascimento.setForeground(COLOR_FIELD_TEXT);
		for (int ano = 2025; ano >= 1900; ano--) {
			anoNascimento.addItem(ano);
		}
		panel.add(anoNascimento);

		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblSexo.setForeground(COLOR_LABEL); // escuro
		lblSexo.setBounds(36, 316, 46, 20);
		panel.add(lblSexo);

		JComboBox BoxSexo = new JComboBox();
		BoxSexo.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Feminino"}));
		BoxSexo.setBounds(36, 341, 120, 28);
		BoxSexo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		BoxSexo.setBackground(COLOR_FIELD_BG);
		BoxSexo.setForeground(COLOR_FIELD_TEXT);
		panel.add(BoxSexo);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblEmail.setForeground(COLOR_LABEL); // escuro
		lblEmail.setBounds(36, 393, 80, 20);
		panel.add(lblEmail);

		preencherEmail = new JTextField();
		preencherEmail.setBounds(36, 418, 269, 28);
		preencherEmail.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		preencherEmail.setBackground(COLOR_FIELD_BG);
		preencherEmail.setForeground(COLOR_FIELD_TEXT);
		preencherEmail.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(preencherEmail);

		JLabel lblTelefone = new JLabel("Telefone/Celular");
		lblTelefone.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblTelefone.setForeground(COLOR_LABEL); // escuro
		lblTelefone.setBounds(33, 469, 180, 20);
		panel.add(lblTelefone);

		try {
			MaskFormatter telefoneMask = new MaskFormatter("(##)#####-####");
			telefoneMask.setPlaceholderCharacter('_');
			preencherTelefone = new JFormattedTextField(telefoneMask);
			preencherTelefone.setBounds(36, 494, 186, 28);
			preencherTelefone.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			preencherTelefone.setBackground(COLOR_FIELD_BG);
			preencherTelefone.setForeground(COLOR_FIELD_TEXT);
			preencherTelefone.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
			panel.add(preencherTelefone);
			preencherTelefone.setColumns(10);
		} catch (Exception e) {
			e.printStackTrace();
		}

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBackground(COLOR_BTN); // fundo mais claro
		btnAdicionar.setForeground(COLOR_BTN_TEXT);
		btnAdicionar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnAdicionar.setFocusPainted(false);
		btnAdicionar.setBorder(BorderFactory.createLineBorder(COLOR_LABEL, 2, true));
		btnAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnAdicionar.setBounds(300, 585, 120, 32);
		panel.add(btnAdicionar);

		JFormattedTextField finalCampoCPF = campoCPF;

		btnAdicionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String nome = preencherNome.getText().trim();
					String cpf = finalCampoCPF.getText().trim();
					String email = preencherEmail.getText().trim();
					String telefone = preencherTelefone.getText().trim();
					String sexo = BoxSexo.getSelectedItem().toString();

					int dia = (int) dataNascimento.getSelectedItem();
					int mes = (int) mesNascimento.getSelectedItem();
					int ano = (int) anoNascimento.getSelectedItem();
					String dataNascimentoStr = String.format("%02d/%02d/%04d", dia, mes, ano);

					if (cpf.contains("_") || nome.isEmpty() || email.isEmpty()) {
						JOptionPane.showMessageDialog(
								JCadastroFuncionario.this,
								"Preencha todos os campos corretamente.",
								"Erro de Validação",
								JOptionPane.ERROR_MESSAGE
						);
						return;
					}

					Funcionario funcionario = new Funcionario();
					funcionario.setNome(nome);
					funcionario.setCpf(cpf);
					funcionario.setDataNascimento(dataNascimentoStr);
					funcionario.setSexo(sexo);
					funcionario.setEmail(email);
					funcionario.setTelefone(telefone);

					Usuario usuario = new Usuario();
					String cpfApenasDigitos = finalCampoCPF.getText().replaceAll("[^\\d]", "");
					String senhaCriptografada = BCrypt.hashpw(cpfApenasDigitos, BCrypt.gensalt());
					usuario.setSenhaUsuario(senhaCriptografada);
					usuario.setNomeUsuario(nome);
					usuario.setTelefoneUsuario(telefone);
					usuario.setEmailUsuario(email);
					usuario.setTipo_usuario(TipoUsuario.USUARIO);

					BDServices.adicionarFuncionario(funcionario);
					BDServices.adicionarUsuario(usuario);

					JOptionPane.showMessageDialog(
							JCadastroFuncionario.this,
							"Funcionário cadastrado com sucesso!",
							"Sucesso",
							JOptionPane.INFORMATION_MESSAGE
					);

					preencherNome.setText("");
					preencherEmail.setText("");
					preencherTelefone.setText("");
					finalCampoCPF.setText("");
					dataNascimento.setSelectedItem(1);
					mesNascimento.setSelectedItem(1);
					anoNascimento.setSelectedItem(2025);

				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(
							JCadastroFuncionario.this,
							"Erro ao cadastrar funcionário: " + ex.getMessage(),
							"Erro",
							JOptionPane.ERROR_MESSAGE
					);
				}
			}
		});

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBackground(COLOR_BTN); // fundo mais claro
		btnVoltar.setForeground(COLOR_BTN_TEXT);
		btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnVoltar.setFocusPainted(false);
		btnVoltar.setBorder(BorderFactory.createLineBorder(COLOR_LABEL, 2, true));
		btnVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnVoltar.setBounds(22, 21, 90, 28);
		panel.add(btnVoltar);

		btnVoltar.addActionListener(e ->{
			JTelaPrincipal telaHome = new JTelaPrincipal();
			telaHome.setVisible(true);
			dispose();
		});
	}
}
