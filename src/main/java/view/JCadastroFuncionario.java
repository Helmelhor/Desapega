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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // Centraliza a tela sempre que for aberta

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 664, 640);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblCadastrarFuncionario = new JLabel("Cadastrar funcionário");
		lblCadastrarFuncionario.setBounds(187, 5, 290, 38);
		lblCadastrarFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 31));
		panel.add(lblCadastrarFuncionario);

		preencherNome = new JTextField();
		preencherNome.setBounds(36, 127, 400, 20);
		panel.add(preencherNome);
		preencherNome.setColumns(10);

		JLabel lblNome = new JLabel("Nome Completo");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNome.setBounds(36, 102, 114, 14);
		panel.add(lblNome);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCpf.setBounds(36, 170, 100, 14);
		panel.add(lblCpf);

		JFormattedTextField campoCPF = null;

		try {
			MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
			cpfMask.setPlaceholderCharacter('_');
			campoCPF = new JFormattedTextField(cpfMask);
			campoCPF.setBounds(36, 195, 269, 20);
			panel.add(campoCPF);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//Código para as datas de nascimento

		JComboBox dataNascimento = new JComboBox();
		dataNascimento.setBounds(36, 267, 43, 22);
		panel.add(dataNascimento);

		for (int data = 01; data <= 31; data++) {
            dataNascimento.addItem(data);
		}

		JComboBox mesNascimento = new JComboBox();
		mesNascimento.setBounds(89, 267, 43, 22);
		panel.add(mesNascimento);
		for (int mes = 01; mes <= 12; mes++) {
            mesNascimento.addItem(mes);
		}


        JComboBox anoNascimento = new JComboBox<>();
        anoNascimento.setBounds(142, 267, 80, 22);
        for (int ano = 2025; ano >= 1900; ano--) {
            anoNascimento.addItem(ano);


		}
        panel.add(anoNascimento);

        JLabel lblDataNascimento = new JLabel("Data de Nascimento");
        lblDataNascimento.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblDataNascimento.setBounds(36, 242, 146, 14);
        panel.add(lblDataNascimento);

        JLabel lblSexo = new JLabel("Sexo");
        lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblSexo.setBounds(36, 316, 46, 14);
        panel.add(lblSexo);

        JComboBox BoxSexo = new JComboBox();
        BoxSexo.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Feminino"}));
        BoxSexo.setBounds(36, 341, 96, 22);
        panel.add(BoxSexo);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblEmail.setBounds(36, 393, 46, 14);
        panel.add(lblEmail);

        preencherEmail = new JTextField();
        preencherEmail.setBounds(36, 418, 269, 20);
        panel.add(preencherEmail);
        preencherEmail.setColumns(10);

		try {
			MaskFormatter telefoneMask = new MaskFormatter("(##)#####-####");
			telefoneMask.setPlaceholderCharacter('_');
			preencherTelefone = new JFormattedTextField(telefoneMask);
			preencherTelefone.setBounds(36, 494, 186, 20);
			panel.add(preencherTelefone);
			preencherTelefone.setColumns(10);
		} catch (Exception e) {
			e.printStackTrace();
		}

		JLabel lblTelefone = new JLabel("Telefone/Celular");
        lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTelefone.setBounds(33, 469, 114, 14);
        panel.add(lblTelefone);

		JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setForeground(new Color(255, 255, 255));
        btnAdicionar.setBackground(new Color(45, 60, 215));
        btnAdicionar.setBounds(300, 585, 89, 23);
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
					String senhaCriptografada = BCrypt.hashpw(cpf, BCrypt.gensalt());
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

		panel.add(btnAdicionar);

		JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(22, 21, 73, 23);
        panel.add(btnVoltar);

		btnVoltar.addActionListener(e ->{
			JTelaPrincipal telaHome = new JTelaPrincipal();
			telaHome.setVisible(true);
			dispose();
		});

	}
}
