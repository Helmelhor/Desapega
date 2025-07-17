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

	// Paleta de cores
	private final Color COLOR_BG = Color.decode("#153448");
	private final Color COLOR_BTN = Color.decode("#F5F5DC"); // fundo dos botões mais claro (bege)
	private final Color COLOR_BTN_TEXT = Color.decode("#153448");
	private final Color COLOR_LABEL = Color.decode("#DFD0B8");
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
		contentPane.setBackground(COLOR_BG);
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // Centraliza a tela sempre que for aberta

		JPanel panel = new JPanel();
		panel.setBackground(COLOR_PANEL);
		panel.setBounds(20, 20, 700, 380);
		panel.setLayout(null);
		contentPane.add(panel);

		JLabel lblCadastroProduto = new JLabel("Cadastro de Produto");
		lblCadastroProduto.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblCadastroProduto.setForeground(COLOR_BG);
		lblCadastroProduto.setBounds(220, 10, 300, 32);
		lblCadastroProduto.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblCadastroProduto);

		JLabel campoNome = new JLabel("Nome:");
		campoNome.setFont(new Font("Segoe UI", Font.BOLD, 15));
		campoNome.setForeground(COLOR_LABEL);
		campoNome.setBounds(46, 60, 120, 25);
		panel.add(campoNome);

		textField = new JTextField();
		textField.setBounds(180, 60, 480, 28);
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		textField.setBackground(COLOR_FIELD_BG);
		textField.setForeground(COLOR_FIELD_TEXT);
		textField.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField);

		JLabel campoDescricao = new JLabel("Descrição:");
		campoDescricao.setFont(new Font("Segoe UI", Font.BOLD, 15));
		campoDescricao.setForeground(COLOR_LABEL);
		campoDescricao.setBounds(46, 100, 120, 25);
		panel.add(campoDescricao);

		textField_1 = new JTextField();
		textField_1.setBounds(180, 100, 480, 28);
		textField_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		textField_1.setBackground(COLOR_FIELD_BG);
		textField_1.setForeground(COLOR_FIELD_TEXT);
		textField_1.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_1);

		JLabel campoEstoque = new JLabel("Quantidade em estoque:");
		campoEstoque.setFont(new Font("Segoe UI", Font.BOLD, 15));
		campoEstoque.setForeground(COLOR_LABEL);
		campoEstoque.setBounds(46, 140, 180, 25);
		panel.add(campoEstoque);

		textField_2 = new JTextField();
		textField_2.setBounds(230, 140, 430, 28);
		textField_2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		textField_2.setBackground(COLOR_FIELD_BG);
		textField_2.setForeground(COLOR_FIELD_TEXT);
		textField_2.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_2);

		JLabel lblPrecoCusto = new JLabel("Preço de custo:");
		lblPrecoCusto.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblPrecoCusto.setForeground(COLOR_LABEL);
		lblPrecoCusto.setBounds(46, 180, 140, 25);
		panel.add(lblPrecoCusto);

		textField_3 = new JTextField();
		textField_3.setBounds(180, 180, 480, 28);
		textField_3.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		textField_3.setBackground(COLOR_FIELD_BG);
		textField_3.setForeground(COLOR_FIELD_TEXT);
		textField_3.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_3);

		JLabel campoPreco = new JLabel("Preço de Venda:");
		campoPreco.setFont(new Font("Segoe UI", Font.BOLD, 15));
		campoPreco.setForeground(COLOR_LABEL);
		campoPreco.setBounds(46, 220, 140, 25);
		panel.add(campoPreco);

		textField_4 = new JTextField();
		textField_4.setBounds(180, 220, 480, 28);
		textField_4.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		textField_4.setBackground(COLOR_FIELD_BG);
		textField_4.setForeground(COLOR_FIELD_TEXT);
		textField_4.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_4);

		JLabel lblCodigoBarras = new JLabel("Código de barras:");
		lblCodigoBarras.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblCodigoBarras.setForeground(COLOR_LABEL);
		lblCodigoBarras.setBounds(46, 260, 140, 25);
		panel.add(lblCodigoBarras);

		textField_5 = new JTextField();
		textField_5.setBounds(180, 260, 480, 28);
		textField_5.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		textField_5.setBackground(COLOR_FIELD_BG);
		textField_5.setForeground(COLOR_FIELD_TEXT);
		textField_5.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_5);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBackground(COLOR_BTN); // fundo mais claro
		btnSalvar.setForeground(COLOR_BTN_TEXT);
		btnSalvar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnSalvar.setFocusPainted(false);
		btnSalvar.setBorder(BorderFactory.createLineBorder(COLOR_LABEL, 2, true));
		btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnSalvar.setBounds(320, 320, 120, 32);
		panel.add(btnSalvar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBackground(COLOR_BTN); // fundo mais claro
		btnVoltar.setForeground(COLOR_BTN_TEXT);
		btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnVoltar.setFocusPainted(false);
		btnVoltar.setBorder(BorderFactory.createLineBorder(COLOR_LABEL, 2, true));
		btnVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnVoltar.setBounds(20, 20, 90, 28);
		panel.add(btnVoltar);

		btnVoltar.addActionListener(e -> {
			JTelaPrincipal telaHome = new JTelaPrincipal();
			telaHome.setVisible(true);
			
		});

		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String nome = textField.getText().trim();
					String descricao = textField_1.getText().trim();
					int estoque = Integer.parseInt(textField_2.getText().trim());
					BigDecimal preco = new BigDecimal(textField_4.getText().trim());

					Produtos produtoExistente = BDServices.buscarProdutoPorNome(nome);

					if (produtoExistente != null) {
						int novoEstoque = produtoExistente.getEstoqueProduto() + estoque;
						produtoExistente.setEstoqueProduto(novoEstoque);
						produtoExistente.setPrecoProduto(preco);
						produtoExistente.setDescricaoProduto(descricao); // opcional
						BDServices.atualizarEstoque(produtoExistente);

						JOptionPane.showMessageDialog(null, "Produto já existia. Estoque e preço atualizados com sucesso!");

					} else {
						Produtos produto = new Produtos();
						produto.setNomeProduto(nome);
						produto.setDescricaoProduto(descricao);
						produto.setPrecoProduto(preco);
						produto.setEstoqueProduto(estoque);
						produto.setDataCadastro(LocalDateTime.now());

						BDServices.adicionarProduto(produto);

						JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
					}

					dispose();

				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + ex.getMessage());
				}
			}
		});

	}
}
