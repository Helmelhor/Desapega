package view;

import com.desapega.Desapega_System.Domain.Models.Produtos;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class JEstoqueProdutos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField textField_24;
	private JTextField textField_25;
	private JTextField textField_26;
	private JTextField textField_27;
	private JTextField textField_28;
	private JTextField textField_29;
	private JTextField textField_30;
	private JTextField textField_31;
	private JTextField textField_32;
	private JTextField textField_33;
	private JTextField textField_34;
	private JTextField textField_35;
	private JButton btnNewButton_1;
	private JButton btnVoltar;

	// Paleta de cores
	private final Color COLOR_BG = Color.decode("#153448");
	private final Color COLOR_BTN = Color.decode("#3C5B6F");
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
					JEstoqueProdutos frame = new JEstoqueProdutos();
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
	public JEstoqueProdutos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,500,500);
		contentPane = new JPanel();
		contentPane.setBackground(COLOR_BG);
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(COLOR_PANEL);
		panel.setBounds(10, 0, 456, 450);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("ESTOQUE");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel.setForeground(COLOR_BG);
		lblNewLabel.setBounds(140, 11, 180, 40);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Código");
		lblNewLabel_1.setForeground(COLOR_BG);
		lblNewLabel_1.setBackground(COLOR_LABEL);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 134, 80, 23);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Nome");
		lblNewLabel_2.setForeground(COLOR_BG);
		lblNewLabel_2.setBackground(COLOR_LABEL);
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_2.setBounds(89, 134, 125, 23);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Quantidade");
		lblNewLabel_3.setForeground(COLOR_BG);
		lblNewLabel_3.setBackground(COLOR_LABEL);
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_3.setBounds(214, 135, 60, 23);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Descrição");
		lblNewLabel_4.setForeground(COLOR_BG);
		lblNewLabel_4.setBackground(COLOR_LABEL);
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_4.setBounds(273, 135, 150, 22);
		panel.add(lblNewLabel_4);

		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.setBounds(273, 399, 89, 30);
		btnNewButton.setBackground(COLOR_BTN);
		btnNewButton.setForeground(COLOR_BTN_TEXT);
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorder(BorderFactory.createLineBorder(COLOR_LABEL, 2, true));
		btnNewButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel.add(btnNewButton);

		// Campos de texto modernizados
		JTextField[] fields = {
			textField, textField_1, textField_2, textField_3, textField_4, textField_5, textField_6, textField_7, textField_8,
			textField_9, textField_10, textField_11, textField_12, textField_13, textField_14, textField_15, textField_16, textField_17,
			textField_18, textField_19, textField_20, textField_21, textField_22, textField_23, textField_24, textField_25, textField_26,
			textField_27, textField_28, textField_29, textField_30, textField_31, textField_32, textField_33, textField_34, textField_35
		};

		// Exemplo para textField:
		textField = new JTextField();
		textField.setBounds(10, 175, 80, 20);
		textField.setColumns(10);
		textField.setBackground(COLOR_FIELD_BG);
		textField.setForeground(COLOR_FIELD_TEXT);
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 157, 80, 20);
		textField_1.setBackground(COLOR_FIELD_BG);
		textField_1.setForeground(COLOR_FIELD_TEXT);
		textField_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_1.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(10, 194, 80, 20);
		textField_2.setBackground(COLOR_FIELD_BG);
		textField_2.setForeground(COLOR_FIELD_TEXT);
		textField_2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_2.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(10, 213, 80, 20);
		textField_3.setBackground(COLOR_FIELD_BG);
		textField_3.setForeground(COLOR_FIELD_TEXT);
		textField_3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_3.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(10, 232, 80, 20);
		textField_4.setBackground(COLOR_FIELD_BG);
		textField_4.setForeground(COLOR_FIELD_TEXT);
		textField_4.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_4.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(10, 250, 80, 20);
		textField_5.setBackground(COLOR_FIELD_BG);
		textField_5.setForeground(COLOR_FIELD_TEXT);
		textField_5.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_5.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(10, 269, 80, 20);
		textField_6.setBackground(COLOR_FIELD_BG);
		textField_6.setForeground(COLOR_FIELD_TEXT);
		textField_6.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_6.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_6);

		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(10, 288, 80, 20);
		textField_7.setBackground(COLOR_FIELD_BG);
		textField_7.setForeground(COLOR_FIELD_TEXT);
		textField_7.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_7.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(10, 308, 80, 20);
		textField_8.setBackground(COLOR_FIELD_BG);
		textField_8.setForeground(COLOR_FIELD_TEXT);
		textField_8.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_8.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_8);

		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(89, 157, 125, 20);
		textField_9.setBackground(COLOR_FIELD_BG);
		textField_9.setForeground(COLOR_FIELD_TEXT);
		textField_9.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_9.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_9);

		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(89, 175, 125, 20);
		textField_10.setBackground(COLOR_FIELD_BG);
		textField_10.setForeground(COLOR_FIELD_TEXT);
		textField_10.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_10.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_10);

		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(89, 194, 125, 20);
		textField_11.setBackground(COLOR_FIELD_BG);
		textField_11.setForeground(COLOR_FIELD_TEXT);
		textField_11.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_11.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_11);

		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(89, 213, 125, 20);
		textField_12.setBackground(COLOR_FIELD_BG);
		textField_12.setForeground(COLOR_FIELD_TEXT);
		textField_12.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_12.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_12);

		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(89, 232, 125, 20);
		textField_13.setBackground(COLOR_FIELD_BG);
		textField_13.setForeground(COLOR_FIELD_TEXT);
		textField_13.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_13.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_13);

		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(89, 250, 125, 20);
		textField_14.setBackground(COLOR_FIELD_BG);
		textField_14.setForeground(COLOR_FIELD_TEXT);
		textField_14.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_14.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_14);

		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(89, 269, 125, 20);
		textField_15.setBackground(COLOR_FIELD_BG);
		textField_15.setForeground(COLOR_FIELD_TEXT);
		textField_15.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_15.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_15);

		textField_16 = new JTextField();
		textField_16.setColumns(10);
		textField_16.setBounds(89, 288, 125, 20);
		textField_16.setBackground(COLOR_FIELD_BG);
		textField_16.setForeground(COLOR_FIELD_TEXT);
		textField_16.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_16.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_16);

		textField_17 = new JTextField();
		textField_17.setColumns(10);
		textField_17.setBounds(89, 308, 125, 20);
		textField_17.setBackground(COLOR_FIELD_BG);
		textField_17.setForeground(COLOR_FIELD_TEXT);
		textField_17.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_17.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_17);

		textField_18 = new JTextField();
		textField_18.setColumns(10);
		textField_18.setBounds(214, 157, 60, 20);
		textField_18.setBackground(COLOR_FIELD_BG);
		textField_18.setForeground(COLOR_FIELD_TEXT);
		textField_18.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_18.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_18);

		textField_19 = new JTextField();
		textField_19.setColumns(10);
		textField_19.setBounds(214, 175, 60, 20);
		textField_19.setBackground(COLOR_FIELD_BG);
		textField_19.setForeground(COLOR_FIELD_TEXT);
		textField_19.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_19.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_19);

		textField_20 = new JTextField();
		textField_20.setColumns(10);
		textField_20.setBounds(214, 194, 60, 20);
		textField_20.setBackground(COLOR_FIELD_BG);
		textField_20.setForeground(COLOR_FIELD_TEXT);
		textField_20.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_20.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_20);

		textField_21 = new JTextField();
		textField_21.setColumns(10);
		textField_21.setBounds(214, 213, 60, 20);
		textField_21.setBackground(COLOR_FIELD_BG);
		textField_21.setForeground(COLOR_FIELD_TEXT);
		textField_21.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_21.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_21);

		textField_22 = new JTextField();
		textField_22.setColumns(10);
		textField_22.setBounds(214, 232, 60, 20);
		textField_22.setBackground(COLOR_FIELD_BG);
		textField_22.setForeground(COLOR_FIELD_TEXT);
		textField_22.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_22.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_22);

		textField_23 = new JTextField();
		textField_23.setColumns(10);
		textField_23.setBounds(214, 250, 60, 20);
		textField_23.setBackground(COLOR_FIELD_BG);
		textField_23.setForeground(COLOR_FIELD_TEXT);
		textField_23.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_23.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_23);

		textField_24 = new JTextField();
		textField_24.setColumns(10);
		textField_24.setBounds(214, 269, 60, 20);
		textField_24.setBackground(COLOR_FIELD_BG);
		textField_24.setForeground(COLOR_FIELD_TEXT);
		textField_24.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_24.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_24);

		textField_25 = new JTextField();
		textField_25.setColumns(10);
		textField_25.setBounds(214, 288, 60, 20);
		textField_25.setBackground(COLOR_FIELD_BG);
		textField_25.setForeground(COLOR_FIELD_TEXT);
		textField_25.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_25.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_25);

		textField_26 = new JTextField();
		textField_26.setColumns(10);
		textField_26.setBounds(214, 308, 60, 20);
		textField_26.setBackground(COLOR_FIELD_BG);
		textField_26.setForeground(COLOR_FIELD_TEXT);
		textField_26.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_26.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_26);

		textField_27 = new JTextField();
		textField_27.setColumns(10);
		textField_27.setBounds(273, 157, 150, 20);
		textField_27.setBackground(COLOR_FIELD_BG);
		textField_27.setForeground(COLOR_FIELD_TEXT);
		textField_27.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_27.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_27);

		textField_28 = new JTextField();
		textField_28.setColumns(10);
		textField_28.setBounds(273, 175, 150, 20);
		textField_28.setBackground(COLOR_FIELD_BG);
		textField_28.setForeground(COLOR_FIELD_TEXT);
		textField_28.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_28.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_28);

		textField_29 = new JTextField();
		textField_29.setColumns(10);
		textField_29.setBounds(273, 194, 150, 20);
		textField_29.setBackground(COLOR_FIELD_BG);
		textField_29.setForeground(COLOR_FIELD_TEXT);
		textField_29.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_29.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_29);

		textField_30 = new JTextField();
		textField_30.setColumns(10);
		textField_30.setBounds(273, 213, 150, 20);
		textField_30.setBackground(COLOR_FIELD_BG);
		textField_30.setForeground(COLOR_FIELD_TEXT);
		textField_30.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_30.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_30);

		textField_31 = new JTextField();
		textField_31.setColumns(10);
		textField_31.setBounds(273, 232, 150, 20);
		textField_31.setBackground(COLOR_FIELD_BG);
		textField_31.setForeground(COLOR_FIELD_TEXT);
		textField_31.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_31.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_31);

		textField_32 = new JTextField();
		textField_32.setColumns(10);
		textField_32.setBounds(273, 250, 150, 20);
		textField_32.setBackground(COLOR_FIELD_BG);
		textField_32.setForeground(COLOR_FIELD_TEXT);
		textField_32.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_32.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_32);

		textField_33 = new JTextField();
		textField_33.setColumns(10);
		textField_33.setBounds(273, 269, 150, 20);
		textField_33.setBackground(COLOR_FIELD_BG);
		textField_33.setForeground(COLOR_FIELD_TEXT);
		textField_33.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_33.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_33);

		textField_34 = new JTextField();
		textField_34.setColumns(10);
		textField_34.setBounds(273, 288, 150, 20);
		textField_34.setBackground(COLOR_FIELD_BG);
		textField_34.setForeground(COLOR_FIELD_TEXT);
		textField_34.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_34.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_34);

		textField_35 = new JTextField();
		textField_35.setColumns(10);
		textField_35.setBounds(273, 308, 150, 20);
		textField_35.setBackground(COLOR_FIELD_BG);
		textField_35.setForeground(COLOR_FIELD_TEXT);
		textField_35.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textField_35.setBorder(BorderFactory.createLineBorder(COLOR_BG, 1, true));
		panel.add(textField_35);

		btnNewButton_1 = new JButton("Adicionar");
		btnNewButton_1.setBounds(89, 399, 100, 30);
		btnNewButton_1.setBackground(COLOR_BTN);
		btnNewButton_1.setForeground(COLOR_BTN_TEXT);
		btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setBorder(BorderFactory.createLineBorder(COLOR_LABEL, 2, true));
		btnNewButton_1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel.add(btnNewButton_1);

		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(1, 11, 83, 28);
		btnVoltar.setBackground(COLOR_BTN);
		btnVoltar.setForeground(COLOR_BTN_TEXT);
		btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnVoltar.setFocusPainted(false);
		btnVoltar.setBorder(BorderFactory.createLineBorder(COLOR_LABEL, 2, true));
		btnVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel.add(btnVoltar);

		btnVoltar.addActionListener(e -> {
			JTelaPrincipal telaHome = new JTelaPrincipal();
			telaHome.setVisible(true);
			dispose();
		});

	}
}
