package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
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
		setBounds(100, 100, 612, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblResumoFinanceiro = new JLabel("Resumo Finaçeiro");
		lblResumoFinanceiro.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblResumoFinanceiro.setBounds(216, 10, 137, 22);
		contentPane.add(lblResumoFinanceiro);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 53, 534, 207);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ent./Sa\u00EDda", "Data", "Produto", "Valor ", "Quantidade P."
			}
		));
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(254, 270, 85, 21);
		contentPane.add(btnVoltar);

        btnVoltar.addActionListener(e ->{
			JTelaPrincipal telaHome = new JTelaPrincipal();
			telaHome.setVisible(true);
			dispose();
		});

	}
}

