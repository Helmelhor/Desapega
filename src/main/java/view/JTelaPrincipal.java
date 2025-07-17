package view;

import com.desapega.Desapega_System.Domain.Models.Produtos;
import com.desapega.Desapega_System.Services.BDServices;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import view.components.GraficoPizzaProdutos;
import javax.swing.JLabel;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class JTelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	// Cores da paleta
	private final Color COLOR_BG = Color.decode("#153448");
	private final Color COLOR_BTN = Color.decode("#F5F5DC"); // fundo dos botões mais claro (bege)
	private final Color COLOR_BTN_TEXT = Color.decode("#153448"); // texto dos botões escuro
	private final Color COLOR_LABEL = Color.decode("#153448"); // texto dos labels escuro
	private final Color COLOR_GRAPH_BG = Color.decode("#948979");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JTelaPrincipal frame = new JTelaPrincipal();
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
	public JTelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 350);
		contentPane = new JPanel();
		contentPane.setBackground(COLOR_BG);
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setLocationRelativeTo(null); // Centraliza a tela sempre que for aberta

		Font btnFont = new Font("Segoe UI", Font.BOLD, 15);

		// Botão customizado
		JButton btnCaixa = createModernButton("Caixa");
		btnCaixa.setBounds(20, 30, 180, 40);
		contentPane.add(btnCaixa);
		btnCaixa.addActionListener(e -> {
			JTelaPagamentoPDV caixa = new JTelaPagamentoPDV();
			caixa.setVisible(true);
			dispose();
		});

		JButton btnCadastrarProduto = createModernButton("Cadastrar Produto");
		btnCadastrarProduto.setBounds(20, 80, 180, 40);
		contentPane.add(btnCadastrarProduto);
		btnCadastrarProduto.addActionListener(e -> {
			JCadastroProduto cadastroProduto = new JCadastroProduto();
			cadastroProduto.setVisible(true);
		});

		JButton btnCadastrarFuncionario = createModernButton("Cadastrar Funcionário");
		btnCadastrarFuncionario.setBounds(20, 130, 180, 40);
		contentPane.add(btnCadastrarFuncionario);
		btnCadastrarFuncionario.addActionListener(e ->{
			JCadastroFuncionario cadastroFuncionario = new JCadastroFuncionario();
			cadastroFuncionario.setVisible(true);
			dispose();
		});

		JButton btnEstoque = createModernButton("Estoque");
		btnEstoque.setBounds(20, 180, 180, 40);
		contentPane.add(btnEstoque);
		btnEstoque.addActionListener(e -> {
			List<Produtos> listaProdutos = BDServices.consultarTodosProdutos();
			JEstoque estoqueProdutos = new JEstoque(listaProdutos);
			estoqueProdutos.setVisible(true);
			dispose();
		});

		JButton btnFinancas = createModernButton("Finanças");
		btnFinancas.setBounds(20, 230, 180, 40);
		contentPane.add(btnFinancas);
		btnFinancas.addActionListener(e -> {
			JFinancas financas= new JFinancas();
			financas.setVisible(true);
			dispose();
		});

		// Gráfico de Pizza de Produtos
		JPanel graphPanel = new JPanel();
		graphPanel.setBackground(COLOR_GRAPH_BG);
		graphPanel.setBounds(230, 60, 420, 220);
		graphPanel.setLayout(null);
		graphPanel.setBorder(BorderFactory.createLineBorder(COLOR_LABEL, 2, true));
		contentPane.add(graphPanel);

		GraficoPizzaProdutos graficoPizza = new GraficoPizzaProdutos();
		graficoPizza.setBounds(10, 10, 400, 200);
		graphPanel.add(graficoPizza);

		// Título Dashboard
		JLabel lblDashboard = new JLabel("Dashboard");
		lblDashboard.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblDashboard.setForeground(COLOR_LABEL); // escuro
		lblDashboard.setBounds(230, 20, 200, 30);
		contentPane.add(lblDashboard);

		// Subtítulo Desempenho semanal
		JLabel lblDesempenhoSemanal = new JLabel("Desempenho semanal");
		lblDesempenhoSemanal.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblDesempenhoSemanal.setForeground(COLOR_LABEL); // escuro
		lblDesempenhoSemanal.setBounds(230, 285, 200, 20);
		contentPane.add(lblDesempenhoSemanal);

	}

	// Método utilitário para criar botões modernos
	private JButton createModernButton(String text) {
		JButton btn = new JButton(text);
		btn.setBackground(COLOR_BTN); // fundo mais claro
		btn.setForeground(COLOR_BTN_TEXT); // texto escuro
		btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btn.setFocusPainted(false);
		// Substitui createRoundBorder por LineBorder com cantos arredondados
		btn.setBorder(new LineBorder(COLOR_LABEL, 2, true));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setHorizontalAlignment(SwingConstants.CENTER);
		return btn;
	}
}
