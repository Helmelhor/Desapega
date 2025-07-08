package view;

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
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCadastroFuncionario frame = new JCadastroFuncionario();
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

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 664, 640);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cadastrar funcionário");
		lblNewLabel.setBounds(187, 5, 290, 38);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(36, 127, 400, 20);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nome Completo");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(36, 102, 114, 14);
		panel.add(lblNewLabel_1);

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

        JLabel lblNewLabel_3 = new JLabel("Data de Nascimento");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_3.setBounds(36, 242, 146, 14);
        panel.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Sexo");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_4.setBounds(36, 316, 46, 14);
        panel.add(lblNewLabel_4);

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Feminino"}));
        comboBox.setBounds(36, 341, 96, 22);
        panel.add(comboBox);

        JLabel lblNewLabel_5 = new JLabel("Email");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_5.setBounds(36, 393, 46, 14);
        panel.add(lblNewLabel_5);

        textField_2 = new JTextField();
        textField_2.setBounds(36, 418, 269, 20);
        panel.add(textField_2);
        textField_2.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setBounds(36, 494, 186, 20);
        panel.add(textField_3);
        textField_3.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("Telefone/Celular");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_6.setBounds(33, 469, 114, 14);
        panel.add(lblNewLabel_6);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setForeground(new Color(255, 255, 255));
        btnAdicionar.setBackground(new Color(45, 60, 215));
        btnAdicionar.setBounds(300, 585, 89, 23);
        panel.add(btnAdicionar);

		JFormattedTextField finalCampoCPF = campoCPF;
		btnAdicionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cpfDigitado = finalCampoCPF.getText();

				if (cpfDigitado.contains("_")) {
					JOptionPane.showMessageDialog(
							JCadastroFuncionario.this,
							"CPF inválido! Preencha todos os dígitos corretamente.",
							"Erro de Validação",
							JOptionPane.ERROR_MESSAGE
					);
				} else {
					JOptionPane.showMessageDialog(
							JCadastroFuncionario.this,
							"Cadastro realizado com sucesso!",
							"Sucesso",
							JOptionPane.INFORMATION_MESSAGE
					);
				}
			}
		});

		panel.add(btnAdicionar);

		JButton btnNewButton = new JButton("Voltar");
        btnNewButton.setBounds(22, 21, 73, 23);
        panel.add(btnNewButton);

		btnNewButton.addActionListener(e ->{
			JTelaPrincipal telaHome = new JTelaPrincipal();
			telaHome.setVisible(true);
			dispose();
		});

	}
}
