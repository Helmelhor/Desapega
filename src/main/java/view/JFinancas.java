package view;

import com.desapega.Desapega_System.Domain.Models.ItemPedido;
import com.desapega.Desapega_System.Domain.Models.Pedido;

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFinancas frame = new JFinancas();

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
	public JFinancas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // Centraliza a tela sempre que for aberta

		JLabel lblResumoFinanceiro = new JLabel("Resumo Finaceiro");
		lblResumoFinanceiro.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblResumoFinanceiro.setBounds(216, 10, 137, 22);
		contentPane.add(lblResumoFinanceiro);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 53, 534, 207);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][]{
				},
				new String[]{
						"Ent./Sa\u00EDda", "Data", "Produto", "Valor ", "Quantidade P."
				}
		));

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(254, 270, 85, 21);
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

		List<Pedido> pedidos = com.desapega.Desapega_System.Services.BDServices.consultarTodosPedidos();

		for (Pedido pedido : pedidos) {
			String tipo = "Saída";
			String data = pedido.getDataPedido().toString();
			String produto = "";
			double valorTotal = 0.0;
			int quantidadeTotal = 0;

			for (ItemPedido item : pedido.getItensPedido()) {
				if (item.getProdutos() != null) {
					produto += item.getProdutos().getNomeProduto() + ", ";
					valorTotal += item.getProdutos().getPrecoProduto().doubleValue() * item.getQuantidade();
					quantidadeTotal += item.getQuantidade();
				}
			}

			if (produto.endsWith(", ")) {
				produto = produto.substring(0, produto.length() - 2);
			}

			modelo.addRow(new Object[]{
					tipo,
					data,
					produto,
					String.format("R$ %.2f", valorTotal),
					quantidadeTotal
			});
		}
	}
}