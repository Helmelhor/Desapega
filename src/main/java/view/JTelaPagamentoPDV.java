package view;

import com.desapega.Desapega_System.Domain.Controllers.CriacaoPagamento;
import com.desapega.Desapega_System.Domain.Models.PedidoPagamento;
import com.desapega.Desapega_System.Domain.Models.Produtos;
import com.desapega.Desapega_System.Services.BDServices;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class JTelaPagamentoPDV extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JComboBox<Produtos> comboProdutos;
    private final JButton botaoAdicionar;
    private final JTable tabelaItens;
    private final DefaultTableModel tableModel;
    private final JLabel labelTotal;
    private final JButton botaoCancelar;
    private final JComboBox<String> comboFormaPagamento;
    private JButton btnNewButton;

    // Paleta de cores
    private final Color COLOR_BG = Color.decode("#153448");
    private final Color COLOR_BTN = Color.decode("#3C5B6F");
    private final Color COLOR_BTN_TEXT = Color.decode("#153448"); // texto dos botões mais escuro
    private final Color COLOR_LABEL = Color.decode("#DFD0B8");
    private final Color COLOR_PANEL = Color.decode("#948979");

    private void atualizarTotal() {
        double totalVenda = 0.0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String valor = tableModel.getValueAt(i, 3).toString().replace("R$", "").trim().replace(",", ".");
            totalVenda += Double.parseDouble(valor);
        }
        labelTotal.setText(String.format("Total da venda: R$ %.2f", totalVenda));
    }

    private List<PedidoPagamento.Item> montarItensParaPagamento() {
        List<PedidoPagamento.Item> itens = new ArrayList<>();

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String nome = tableModel.getValueAt(i, 0).toString();
            double preco = Double.parseDouble(tableModel.getValueAt(i, 1).toString().replace("R$", "").replace(",", ".").trim());
            int quantidade = Integer.parseInt(tableModel.getValueAt(i, 2).toString());

            PedidoPagamento.Item item = new PedidoPagamento.Item();
            item.setId(i + 1); // ou ID real do banco
            item.setTitle(nome);
            item.setCurrency_id("BRL");
            item.setQuantity(quantidade);
            item.setUnit_price(preco);

            itens.add(item);
        }

        return itens;
    }

    public JTelaPagamentoPDV() {

        setTitle("PDV - Caixa Aberto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 700);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBackground(COLOR_BG);
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);

        JPanel painelNorte = new JPanel(new BorderLayout(5, 5));
        painelNorte.setBackground(COLOR_BG);

        JLabel lblSelecionar = new JLabel("Selecionar produto:");
        lblSelecionar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblSelecionar.setForeground(COLOR_LABEL);
        painelNorte.add(lblSelecionar, BorderLayout.NORTH);

        comboProdutos = new JComboBox<>();
        comboProdutos.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        comboProdutos.setBackground(COLOR_PANEL);
        comboProdutos.setForeground(COLOR_LABEL);
        comboProdutos.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Produtos) {
                    Produtos produto = (Produtos) value;
                    value = produto.getNomeProduto() + " - R$ " + produto.getPrecoProduto();
                }
                JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                lbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                lbl.setForeground(COLOR_LABEL);
                lbl.setBackground(isSelected ? COLOR_BTN : COLOR_PANEL);
                return lbl;
            }
        });

        List<Produtos> produtos = BDServices.consultarTodosProdutos();
        for (Produtos produto : produtos) {
            if (produto.getEstoqueProduto() >= 1) {
                comboProdutos.addItem(produto);
            }
        }

        painelNorte.add(comboProdutos, BorderLayout.CENTER);

        botaoAdicionar = createModernButton("Adicionar");
        painelNorte.add(botaoAdicionar, BorderLayout.EAST);
        contentPane.add(painelNorte, BorderLayout.NORTH);

        String[] colunas = {"Nome", "Preço Unitário", "Qtd.", "Total"};
        tableModel = new DefaultTableModel(null, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaItens = new JTable(tableModel);
        tabelaItens.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabelaItens.setRowHeight(28);

        // Cabeçalho customizado com cor escura
        JTableHeader header = tabelaItens.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 15));
        header.setBackground(COLOR_PANEL);
        header.setForeground(COLOR_BG); // cor escura para o texto do cabeçalho

        tabelaItens.setBackground(COLOR_PANEL);
        tabelaItens.setForeground(COLOR_BG); // texto da tabela mais escuro
        tabelaItens.setSelectionBackground(COLOR_BTN);
        tabelaItens.setSelectionForeground(COLOR_BG); // texto selecionado mais escuro

        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produtos produtoSelecionado = (Produtos) comboProdutos.getSelectedItem();
                if (produtoSelecionado != null) {
                    String nome = produtoSelecionado.getNomeProduto();
                    double precoUnitario = produtoSelecionado.getPrecoProduto().doubleValue();
                    int quantidade = 1;
                    double total = precoUnitario * quantidade;

                    tableModel.addRow(new Object[]{
                            nome,
                            String.format("R$ %.2f", precoUnitario),
                            quantidade,
                            String.format("R$ %.2f", total)
                    });

                    atualizarTotal();
                    btnNewButton.setEnabled(true);
                }
            }
        });

        tabelaItens.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int linhaSelecionada = tabelaItens.getSelectedRow();

                if (linhaSelecionada != -1 && SwingUtilities.isLeftMouseButton(evt)) {
                    int opcao = JOptionPane.showConfirmDialog(
                            JTelaPagamentoPDV.this,
                            "Deseja remover este item da venda?",
                            "Confirmar remoção",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (opcao == JOptionPane.YES_OPTION) {
                        tableModel.removeRow(linhaSelecionada);
                        atualizarTotal();

                        if (tableModel.getRowCount() == 0) {
                            btnNewButton.setEnabled(false);
                        }
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tabelaItens);
        scrollPane.getViewport().setBackground(COLOR_PANEL);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_LABEL, 2, true));
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel painelSul = new JPanel(new BorderLayout(10, 10));
        painelSul.setBackground(COLOR_BG);

        labelTotal = new JLabel("Total da venda: R$ 0.00");
        labelTotal.setFont(new Font("Segoe UI", Font.BOLD, 22));
        labelTotal.setForeground(COLOR_LABEL);
        labelTotal.setHorizontalAlignment(SwingConstants.CENTER);
        painelSul.add(labelTotal, BorderLayout.NORTH);

        JPanel painelBotoesFinais = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        painelBotoesFinais.setBackground(COLOR_BG);

        botaoCancelar = createModernButton("Cancelar Venda");
        String[] formasPagamento = {"Dinheiro", "Cartão de Crédito", "Cartão de Débito", "Pix"};
        comboFormaPagamento = new JComboBox<>(formasPagamento);
        comboFormaPagamento.setPreferredSize(new Dimension(160, 30));
        comboFormaPagamento.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        comboFormaPagamento.setBackground(COLOR_PANEL);
        comboFormaPagamento.setForeground(COLOR_LABEL);

        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhas = tableModel.getRowCount();
                for (int i = linhas - 1; i >= 0; i--) {
                    tableModel.removeRow(i);
                }
                labelTotal.setText("Total da venda: R$ 0.00");
                btnNewButton.setEnabled(false);
            }
        });

        JButton botaoVoltar = createModernButton("Voltar");
        botaoVoltar.addActionListener(e -> {
            JTelaPrincipal telaPrincipal = new JTelaPrincipal();
            telaPrincipal.setVisible(true);
            dispose();
        });

        painelBotoesFinais.add(botaoVoltar);
        painelBotoesFinais.add(botaoCancelar);

        JLabel lblFormaPagamento = new JLabel("Forma de Pagamento:");
        lblFormaPagamento.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblFormaPagamento.setForeground(COLOR_LABEL);
        painelBotoesFinais.add(lblFormaPagamento);

        painelBotoesFinais.add(comboFormaPagamento);

        painelSul.add(painelBotoesFinais, BorderLayout.SOUTH);

        contentPane.add(painelSul, BorderLayout.SOUTH);

        btnNewButton = createModernButton("Finalizar Compra");
        btnNewButton.setEnabled(false);
        painelSul.add(btnNewButton, BorderLayout.CENTER);

        btnNewButton.addActionListener(e -> {
            List<PedidoPagamento.Item> itens = montarItensParaPagamento();

            CriacaoPagamento pagamentoController = new CriacaoPagamento();
            String urlPagamento = pagamentoController.criarPagamentoComItens(itens);

            if (urlPagamento != null) {
                try {
                    Desktop.getDesktop().browse(new URI(urlPagamento));
                    int resposta = JOptionPane.showConfirmDialog(this, "O pagamento foi confirmado?", "Confirmar Pagamento", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        for (int i = 0; i < tableModel.getRowCount(); i++) {
                            String nome = tableModel.getValueAt(i, 0).toString();
                            int quantidadeComprada = Integer.parseInt(tableModel.getValueAt(i, 2).toString());

                            Produtos produto = BDServices.buscarProdutoPorNome(nome);
                            if (produto != null) {
                                int novoEstoque = produto.getEstoqueProduto() - quantidadeComprada;
                                produto.setEstoqueProduto(novoEstoque);
                                BDServices.atualizarEstoque(produto);
                            } else {
                                JOptionPane.showMessageDialog(this, "Venda cancelada. Nenhum estoque alterado.");
                            }
                        }
                        JOptionPane.showMessageDialog(this, "Estoque atualizado com sucesso!");
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "Venda cancelada. Nenhum estoque alterado.");
                    }

                    int linhas = tableModel.getRowCount();
                    for (int i = linhas - 1; i >= 0; i--) {
                        tableModel.removeRow(i);
                    }
                    labelTotal.setText("Total da venda: R$ 0.00");
                    btnNewButton.setEnabled(false);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Erro ao abrir o navegador para pagamento.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao criar link de pagamento.");
            }
        });

    }

    // Botão moderno utilitário
    private JButton createModernButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(COLOR_BTN);
        btn.setForeground(COLOR_BTN_TEXT); // cor escura
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(COLOR_LABEL, 2, true));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        return btn;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                // Melhora a aparência para o look and feel do sistema operacional
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            JTelaPagamentoPDV frame = new JTelaPagamentoPDV();
          //centralizando a tela
			frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
