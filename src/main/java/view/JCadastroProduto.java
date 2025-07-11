package view;

import com.desapega.Desapega_System.Domain.Models.Produtos;
import com.desapega.Desapega_System.Services.BDServices;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
	public JCadastroProduto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // Centraliza a tela sempre que for aberta

		JLabel lblCadastroProduto = new JLabel("Cadastro de Produto");
		lblCadastroProduto.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCadastroProduto.setBounds(267, 10, 225, 22);
		contentPane.add(lblCadastroProduto);

		JLabel campoNome = new JLabel("Nome:");
		campoNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		campoNome.setBounds(46, 84, 67, 13);
		contentPane.add(campoNome);

		textField = new JTextField();
		textField.setBounds(237, 78, 468, 30);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel campoDescricao = new JLabel("Descrição:");
		campoDescricao.setFont(new Font("Tahoma", Font.BOLD, 14));
		campoDescricao.setBounds(46, 133, 86, 13);
		contentPane.add(campoDescricao);

		JLabel campoEstoque = new JLabel("Quantidade em estoque :");
		campoEstoque.setFont(new Font("Tahoma", Font.BOLD, 14));
		campoEstoque.setBounds(46, 182, 199, 13);
		contentPane.add(campoEstoque);

		JLabel lblPrecoCusto = new JLabel("Preço de custo:");
		lblPrecoCusto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrecoCusto.setBounds(46, 227, 133, 13);
		contentPane.add(lblPrecoCusto);

		JLabel campoPreco = new JLabel("Preço de Venda:");
		campoPreco.setFont(new Font("Tahoma", Font.BOLD, 14));
		campoPreco.setBounds(46, 267, 119, 13);
		contentPane.add(campoPreco);

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

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBackground(Color.GRAY);
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSalvar.setBounds(336, 368, 85, 21);
		contentPane.add(btnSalvar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBackground(new Color(192, 192, 192));
		btnVoltar.setBounds(10, 12, 77, 23);
		contentPane.add(btnVoltar);

		btnVoltar.addActionListener(e -> {
			JTelaPrincipal telaHome = new JTelaPrincipal();
			telaHome.setVisible(true);
			dispose();
		});

		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String nome = textField.getText().trim();
					String descricao = textField_1.getText().trim();
					int estoque = Integer.parseInt(textField_2.getText().trim());
					BigDecimal preco = new BigDecimal(textField_4.getText().trim());
					//BigDecimal preco = new BigDecimal(campoPreco.getText().trim());
					//int estoque = Integer.parseInt(campoEstoque.getText().trim());

					Produtos produto = new Produtos();
					produto.setNomeProduto(nome);
					produto.setDescricaoProduto(descricao);
					produto.setPrecoProduto(preco);
					produto.setEstoqueProduto(estoque);
					produto.setDataCadastro(LocalDateTime.now());
					//produto.setUsuario(usuarioLogado);

					BDServices.adicionarProduto(produto);

					JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
					dispose();

				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + ex.getMessage());
				}
			}
		});

	}
}
