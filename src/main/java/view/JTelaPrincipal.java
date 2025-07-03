package view;

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

		JButton btnNewButton = new JButton("Caixa");
		btnNewButton.setBounds(10, 10, 153, 33);
		contentPane.add(btnNewButton);

		JButton btnCadastrarProduto = new JButton("Cadastrar Produto");
		btnCadastrarProduto.setBounds(10, 53, 153, 34);
		contentPane.add(btnCadastrarProduto);

		btnCadastrarProduto.addActionListener(e -> {
			JCadastroProduto cadastroProduto = new JCadastroProduto();
			cadastroProduto.setVisible(true);
			dispose();
		});

		JButton btnCadastrarFuncionrio = new JButton("Cadastrar Funcionário");
		btnCadastrarFuncionrio.setBounds(10, 97, 153, 40);
		contentPane.add(btnCadastrarFuncionrio);

		JButton btnEstoque = new JButton("Estoque");
		btnEstoque.setBounds(10, 146, 153, 35);
		contentPane.add(btnEstoque);

		JButton btnFinanas = new JButton("Finanças");
		btnFinanas.setBounds(10, 191, 153, 31);
		contentPane.add(btnFinanas);

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

		JLabel lblNewLabel = new JLabel("Dashboard");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(196, 20, 94, 13);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Desempenho semanal");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(196, 157, 143, 13);
		contentPane.add(lblNewLabel_1);

	}
}
