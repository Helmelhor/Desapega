package view;

import com.desapega.Desapega_System.Domain.Controllers.CriacaoPagamento;
import com.desapega.Desapega_System.Domain.Models.ItemPedido;
import com.desapega.Desapega_System.Domain.Models.Pedido;
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
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class JTelaPagamentoPDV extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JComboBox<Produtos> comboProdutos;
    private final JButton botaoAdicionar;
    private final JTable tabelaItens;
    private final DefaultTableModel tableModel;
    private final JLabel labelTotal;
    private final JButton botaoCancelar;
    private final JComboBox<String> comboFormaPagamento;
    private JButton btnFinalizarCompra;

    // Paleta de cores
    private final Color COLOR_BG = Color.decode("#153448");
    private final Color COLOR_BTN = Color.decode("#F5F5DC"); // botões mais claros
    private final Color COLOR_BTN_TEXT = Color.decode("#153448"); // texto dos botões escuro
    private final Color COLOR_LABEL = Color.decode("#153448"); // texto dos labels escuro
    private final Color COLOR_PANEL = Color.decode("#948979");
    private final Color COLOR_TABLE_TEXT = Color.decode("#153448"); // texto da tabela escuro
    private final Color COLOR_HEADER_TEXT = Color.decode("#153448"); // texto do cabeçalho escuro

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
        lblSelecionar.setForeground(COLOR_LABEL); // branco
        painelNorte.add(lblSelecionar, BorderLayout.NORTH);

        comboProdutos = new JComboBox<>();
        comboProdutos.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        comboProdutos.setBackground(COLOR_PANEL);
        comboProdutos.setForeground(COLOR_LABEL); // branco
        comboProdutos.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Produtos) {
                    Produtos produto = (Produtos) value;
                    value = produto.getNomeProduto() + " - R$ " + produto.getPrecoProduto();
                }
                JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                lbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                lbl.setForeground(COLOR_LABEL); // branco
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
        header.setForeground(COLOR_HEADER_TEXT); // texto do cabeçalho escuro

        tabelaItens.setBackground(COLOR_PANEL);
        tabelaItens.setForeground(COLOR_TABLE_TEXT); // texto da tabela escuro
        tabelaItens.setSelectionBackground(COLOR_BTN);
        tabelaItens.setSelectionForeground(COLOR_TABLE_TEXT); // texto selecionado escuro

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
                    btnFinalizarCompra.setEnabled(true);
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
                            btnFinalizarCompra.setEnabled(false);
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
        labelTotal.setForeground(COLOR_LABEL); // branco
        labelTotal.setHorizontalAlignment(SwingConstants.CENTER);
        painelSul.add(labelTotal, BorderLayout.NORTH);

        JPanel painelBotoesFinais = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        painelBotoesFinais.setBackground(COLOR_BG);

        botaoCancelar = createModernButton("Cancelar Venda");
        botaoCancelar.setForeground(COLOR_BTN_TEXT); // texto escuro
        String[] formasPagamento = {"Dinheiro", "Cartão de Crédito", "Cartão de Débito", "Pix"};
        comboFormaPagamento = new JComboBox<>(formasPagamento);
        comboFormaPagamento.setPreferredSize(new Dimension(160, 30));
        comboFormaPagamento.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        comboFormaPagamento.setBackground(COLOR_PANEL);
        comboFormaPagamento.setForeground(COLOR_LABEL); // texto escuro
        comboFormaPagamento.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                lbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                lbl.setForeground(COLOR_LABEL); // texto escuro
                lbl.setBackground(isSelected ? COLOR_BTN : COLOR_PANEL);
                return lbl;
            }
        });

        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhas = tableModel.getRowCount();
                for (int i = linhas - 1; i >= 0; i--) {
                    tableModel.removeRow(i);
                }
                labelTotal.setText("Total da venda: R$ 0.00");
                btnFinalizarCompra.setEnabled(false);
            }
        });

        JButton botaoVoltar = createModernButton("Voltar");
        botaoVoltar.setForeground(COLOR_BTN_TEXT); // texto escuro
        botaoVoltar.addActionListener(e -> {
            JTelaPrincipal telaPrincipal = new JTelaPrincipal();
            telaPrincipal.setVisible(true);
            dispose();
        });

        painelBotoesFinais.add(botaoVoltar);
        painelBotoesFinais.add(botaoCancelar);

        JLabel lblFormaPagamento = new JLabel("Forma de Pagamento:");
        lblFormaPagamento.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblFormaPagamento.setForeground(COLOR_LABEL); // branco
        painelBotoesFinais.add(lblFormaPagamento);

        painelBotoesFinais.add(comboFormaPagamento);

        painelSul.add(painelBotoesFinais, BorderLayout.SOUTH);

        contentPane.add(painelSul, BorderLayout.SOUTH);

        btnFinalizarCompra = createModernButton("Finalizar Compra");
        btnFinalizarCompra.setEnabled(false);
        painelSul.add(btnFinalizarCompra, BorderLayout.CENTER);

        btnFinalizarCompra.addActionListener(e -> {
            List<PedidoPagamento.Item> itens = montarItensParaPagamento();

            CriacaoPagamento pagamentoController = new CriacaoPagamento();
            String urlPagamento = pagamentoController.criarPagamentoComItens(itensPagamento);

            if (urlPagamento != null) {
                try {
                    Desktop.getDesktop().browse(new URI(urlPagamento));

                    int resposta = JOptionPane.showConfirmDialog(
                            this,
                            "O pagamento foi confirmado?",
                            "Confirmar Pagamento",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (resposta == JOptionPane.YES_OPTION) {
                        // -----------------------------
                        // AGRUPAMENTO DE ITENS POR PRODUTO
                        // -----------------------------
                        Map<Long, ItemPedido> itensAgrupados = new HashMap<>();
                        BigDecimal totalPedido = BigDecimal.ZERO;

                        // percorre os itens da tabela para agrupar
                        for (int i = 0; i < tableModel.getRowCount(); i++) {
                            String nomeProduto = tableModel.getValueAt(i, 0).toString();
                            int quantidadeComprada = Integer.parseInt(tableModel.getValueAt(i, 2).toString());
                            BigDecimal precoUnit = new BigDecimal(
                                    tableModel.getValueAt(i, 1).toString()
                                            .replace("R$", "")
                                            .replace(",", ".")
                                            .trim()
                            );

                            Produtos produto = BDServices.buscarProdutoPorNome(nomeProduto);
                            if (produto != null) {
                                // Atualiza estoque
                                int novoEstoque = produto.getEstoqueProduto() - quantidadeComprada;
                                produto.setEstoqueProduto(novoEstoque);
                                BDServices.atualizarEstoque(produto);

                                // Agrupa por id_produto
                                ItemPedido existente = itensAgrupados.get(produto.getIdProduto());
                                if (existente == null) {
                                    ItemPedido item = new ItemPedido();
                                    item.setProduto(produto);
                                    item.setQuantidade(quantidadeComprada);
                                    item.setValorTotal(precoUnit.multiply(BigDecimal.valueOf(quantidadeComprada)));
                                    itensAgrupados.put(produto.getIdProduto(), item);
                                } else {
                                    existente.setQuantidade(existente.getQuantidade() + quantidadeComprada);
                                    existente.setValorTotal(
                                            existente.getValorTotal()
                                                    .add(precoUnit.multiply(BigDecimal.valueOf(quantidadeComprada)))
                                    );
                                }
                            }
                        }

                        // -----------------------------
                        // CRIA O PEDIDO E VINCULA ITENS AGRUPADOS
                        // -----------------------------
                        Pedido pedido = new Pedido();
                        pedido.setDataPedido(LocalDateTime.now());
                        pedido.setItensPedido(new ArrayList<>(itensAgrupados.values()));

                        // calcula o total
                        for (ItemPedido item : itensAgrupados.values()) {
                            item.setPedido(pedido); // vínculo ManyToOne
                            totalPedido = totalPedido.add(item.getValorTotal());
                        }

                        pedido.setValorTotal(totalPedido);

                        // salva tudo de uma vez com cascade
                        BDServices.adicionarPedido(pedido);

                        JOptionPane.showMessageDialog(this,
                                "Estoque atualizado e pedido registrado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Venda cancelada. Nenhum estoque alterado.");
                    }

                    // Limpa tabela e reseta total
                    int linhas = tableModel.getRowCount();
                    for (int i = linhas - 1; i >= 0; i--) {
                        tableModel.removeRow(i);
                    }
                    labelTotal.setText("Total da venda: R$ 0.00");
                    btnFinalizarCompra.setEnabled(false);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this,
                            "Erro ao abrir o navegador para pagamento.");
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Erro ao criar link de pagamento.");
            }
        });



    }

    // Botão moderno utilitário
    private JButton createModernButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(COLOR_BTN);
        btn.setForeground(COLOR_BTN_TEXT); // texto escuro
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
