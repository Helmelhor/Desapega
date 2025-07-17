package view;

import com.desapega.Desapega_System.Domain.Models.ItemPedido;
import com.desapega.Desapega_System.Domain.Models.Pedido;
import com.desapega.Desapega_System.Services.BDServices;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class JFinancas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

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

	public JFinancas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblResumoFinanceiro = new JLabel("Resumo Financeiro");
		lblResumoFinanceiro.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblResumoFinanceiro.setBounds(250, 10, 200, 30);
		contentPane.add(lblResumoFinanceiro);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 53, 630, 250);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][]{},
				new String[]{
						"Tipo", "Data", "Produto(s)", "Valor Total", "Qtd Total"
				}
		));
		scrollPane.setViewportView(table);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(300, 320, 100, 25);
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

		// Consulta todos os pedidos no banco
		List<Pedido> pedidos = BDServices.consultarTodosPedidos();

		for (Pedido pedido : pedidos) {
			String tipo = "Saída"; // Ex.: pode ser alterado futuramente
			String data = pedido.getDataPedido().toString();
			StringBuilder produtosConcat = new StringBuilder();
			double valorTotal = 0.0;
			int quantidadeTotal = 0;

			// Itera sobre os itens já relacionados ao pedido
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