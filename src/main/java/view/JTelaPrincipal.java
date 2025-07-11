package view;

import com.desapega.Desapega_System.Domain.Models.Produtos;
import com.desapega.Desapega_System.Services.BDServices;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.util.List;
import javax.swing.UIManager;

public class JTelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		setBounds(100, 100, 621, 305);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setLocationRelativeTo(null); // Centraliza a tela sempre que for aberta

		JButton btnCaixa = new JButton("Caixa");
		btnCaixa.setBounds(10, 10, 153, 33);
		contentPane.add(btnCaixa);

		btnCaixa.addActionListener(e -> {
			JTelaPagamentoPDV caixa = new JTelaPagamentoPDV();
			caixa.setVisible(true);
			dispose();
		});

		JButton btnCadastrarProduto = new JButton("Cadastrar Produto");
		btnCadastrarProduto.setBounds(10, 53, 153, 34);
		contentPane.add(btnCadastrarProduto);

		btnCadastrarProduto.addActionListener(e -> {
			JCadastroProduto cadastroProduto = new JCadastroProduto();
			cadastroProduto.setVisible(true);
			dispose();
		});

		JButton btnCadastrarFuncionario = new JButton("Cadastrar Funcionário");
		btnCadastrarFuncionario.setBounds(10, 97, 153, 40);
		contentPane.add(btnCadastrarFuncionario);

		btnCadastrarFuncionario.addActionListener(e ->{
			JCadastroFuncionario cadastroFuncionario = new JCadastroFuncionario();
			cadastroFuncionario.setVisible(true);
			dispose();
		});

		JButton btnEstoque = new JButton("Estoque");
		btnEstoque.setBounds(10, 146, 153, 35);
		contentPane.add(btnEstoque);

		btnEstoque.addActionListener(e -> {
			//List<Produtos> listaProdutos = BDServices.consultarTodosProdutos();
			//JEstoque estoqueProdutos = new JEstoque(listaProdutos);
			JEstoqueProdutos estoqueProdutos = new JEstoqueProdutos();
			estoqueProdutos.setVisible(true);
			dispose();
		});

		JButton btnFinancas = new JButton("Finanças");
		btnFinancas.setBounds(10, 191, 153, 31);
		contentPane.add(btnFinancas);

		btnFinancas.addActionListener(e -> {
			JFinancas financas= new JFinancas();
			financas.setVisible(true);
			dispose();
		});

		JList list = new JList();
		list.setBounds(386, 111, 10, 0);
		contentPane.add(list);

		JEditorPane editorPane = new JEditorPane();
		editorPane.setBackground(UIManager.getColor("Button.light"));
		editorPane.setBounds(196, 40, 323, 82);
		contentPane.add(editorPane);

		JEditorPane editorPane_1 = new JEditorPane();
		editorPane_1.setBackground(UIManager.getColor("Button.light"));
		editorPane_1.setBounds(196, 177, 337, 45);
		contentPane.add(editorPane_1);

		JLabel lblDashboard = new JLabel("Dashboard");
		lblDashboard.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDashboard.setBounds(196, 20, 94, 13);
		contentPane.add(lblDashboard);

		JLabel lblDesempenhoSemanal = new JLabel("Desempenho semanal");
		lblDesempenhoSemanal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDesempenhoSemanal.setBounds(196, 157, 143, 13);
		contentPane.add(lblDesempenhoSemanal);

	}
}
