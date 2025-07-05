package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class JCadastroProduto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCadastroProduto frame = new JCadastroProduto();
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
	public JCadastroProduto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCadastroProduto = new JLabel("Cadastro de Produto");
		lblCadastroProduto.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCadastroProduto.setBounds(267, 10, 225, 22);
		contentPane.add(lblCadastroProduto);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNome.setBounds(46, 84, 67, 13);
		contentPane.add(lblNome);

		textField = new JTextField();
		textField.setBounds(237, 78, 468, 30);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescricao.setBounds(46, 133, 86, 13);
		contentPane.add(lblDescricao);

		JLabel lblQuantidadeEstoque = new JLabel("Quantidade em estoque :");
		lblQuantidadeEstoque.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQuantidadeEstoque.setBounds(46, 182, 199, 13);
		contentPane.add(lblQuantidadeEstoque);

		JLabel lblPrecoCusto = new JLabel("Preço de custo:");
		lblPrecoCusto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrecoCusto.setBounds(46, 227, 133, 13);
		contentPane.add(lblPrecoCusto);

		JLabel lblPrecoVenda = new JLabel("Preço de Venda:");
		lblPrecoVenda.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrecoVenda.setBounds(46, 267, 119, 13);
		contentPane.add(lblPrecoVenda);

		JLabel lblCodigoBarras = new JLabel("Código de barras:");
		lblCodigoBarras.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCodigoBarras.setBounds(46, 309, 133, 13);
		contentPane.add(lblCodigoBarras);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(237, 116, 468, 30);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(237, 165, 468, 30);
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(237, 221, 468, 30);
		contentPane.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(237, 261, 468, 30);
		contentPane.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(237, 303, 468, 30);
		contentPane.add(textField_5);

		JButton Salvar = new JButton("Salvar");
		Salvar.setBackground(Color.GRAY);
		Salvar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Salvar.setBounds(336, 368, 85, 21);
		contentPane.add(Salvar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBackground(new Color(192, 192, 192));
		btnVoltar.setBounds(10, 12, 77, 23);
		contentPane.add(btnVoltar);

		btnVoltar.addActionListener(e -> {
			JTelaPrincipal telaHome = new JTelaPrincipal();
			telaHome.setVisible(true);
			dispose();
		});

	}
}
