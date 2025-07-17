package view;

import com.desapega.Desapega_System.Domain.Models.ItemPedido;
import com.desapega.Desapega_System.Domain.Models.Pedido;
import com.desapega.Desapega_System.Domain.Models.Produtos;
import com.desapega.Desapega_System.Services.BDServices;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.time.format.DateTimeFormatter;

import java.util.List;

public class JFinancas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	// Paleta de cores
	private final Color COLOR_BG = Color.decode("#153448");
	private final Color COLOR_BTN = Color.decode("#3C5B6F");
	private final Color COLOR_BTN_TEXT = Color.decode("#153448"); // texto dos botões escuro
	private final Color COLOR_LABEL = Color.decode("#DFD0B8");
	private final Color COLOR_PANEL = Color.decode("#948979");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				JFinancas frame = new JFinancas();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFinancas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 342);
		contentPane = new JPanel();
		contentPane.setBackground(COLOR_BG);
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // Centraliza a tela sempre que for aberta

		JLabel lblResumoFinanceiro = new JLabel("Resumo Financeiro");
		lblResumoFinanceiro.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblResumoFinanceiro.setForeground(COLOR_LABEL);
		lblResumoFinanceiro.setBounds(180, 10, 250, 28);
		lblResumoFinanceiro.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblResumoFinanceiro);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 53, 534, 207);
		scrollPane.setBackground(COLOR_PANEL);
		scrollPane.getViewport().setBackground(COLOR_PANEL);
		scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_LABEL, 2, true));
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.setRowHeight(26);
		table.setBackground(COLOR_PANEL);
		table.setForeground(COLOR_BG); // texto escuro
		table.setSelectionBackground(COLOR_BTN);
		table.setSelectionForeground(COLOR_BG);

		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Segoe UI", Font.BOLD, 15));
		header.setBackground(COLOR_PANEL);
		header.setForeground(COLOR_BG); // cabeçalho escuro

		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][]{
				},
				new String[]{
						"Ent./Saída", "Data", "Produto", "Valor ", "Quantidade P."
				}
		));

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(254, 270, 100, 30);
		btnVoltar.setBackground(COLOR_BTN);
		btnVoltar.setForeground(COLOR_BTN_TEXT); // texto escuro
		btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnVoltar.setFocusPainted(false);
		btnVoltar.setBorder(BorderFactory.createLineBorder(COLOR_LABEL, 2, true));
		btnVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnVoltar);

		btnVoltar.addActionListener(e -> {
			JTelaPrincipal telaHome = new JTelaPrincipal();
			telaHome.setVisible(true);
			dispose();
		});
		carregarDados();
	}

	private void carregarDados() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);

		// Cria o formatter para o formato desejado
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		// Primeiro as ENTRADAS
		List<Produtos> produtos = BDServices.consultarTodosProdutos();
		for (Produtos produto : produtos) {
			String tipo = "Entrada";
			String data = produto.getDataCadastro() != null
					? produto.getDataCadastro().format(formatter)
					: "-";
			String nomeProduto = produto.getNomeProduto();
			double precoUnit = produto.getPrecoProduto().doubleValue();
			int quantidade = produto.getEstoqueProduto();

			modelo.addRow(new Object[]{
					tipo,
					data,
					nomeProduto,
					String.format("R$ %.2f", precoUnit * quantidade),
					quantidade
			});
		}

		// Consulta todos os pedidos no banco
		List<Pedido> pedidos = BDServices.consultarTodosPedidos();

		for (Pedido pedido : pedidos) {
			String tipo = "Saída"; // Ex.: pode ser alterado futuramente
			// Usa o formatter para formatar a data
			String data = pedido.getDataPedido().format(formatter);

			StringBuilder produtosConcat = new StringBuilder();
			double valorTotal = 0.0;
			int quantidadeTotal = 0;

			for (ItemPedido item : pedido.getItensPedido()) {
				if (item.getProduto() != null) {
					produtosConcat.append(item.getProduto().getNomeProduto()).append(", ");
					valorTotal += item.getProduto().getPrecoProduto().doubleValue() * item.getQuantidade();
					quantidadeTotal += item.getQuantidade();
				}
			}

			// Remove a vírgula final
			String listaProdutos = produtosConcat.toString();
			if (listaProdutos.endsWith(", ")) {
				listaProdutos = listaProdutos.substring(0, listaProdutos.length() - 2);
			}

			modelo.addRow(new Object[]{
					tipo,
					data,
					listaProdutos,
					String.format("R$ %.2f", valorTotal),
					quantidadeTotal
			});
		}
	}
}