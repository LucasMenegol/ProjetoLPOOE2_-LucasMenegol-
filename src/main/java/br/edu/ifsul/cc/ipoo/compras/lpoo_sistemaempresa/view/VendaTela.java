package br.edu.ifsul.cc.ipoo.compras.lpoo_sistemaempresa.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import br.edu.ifsul.cc.ipoo.compras.lpoo_sistemaempresa.dao.PersistenciaJPA;
import br.edu.ifsul.cc.ipoo.compras.lpoo_sistemaempresa.model.Cliente;
import br.edu.ifsul.cc.ipoo.compras.lpoo_sistemaempresa.model.Produto;
import br.edu.ifsul.cc.ipoo.compras.lpoo_sistemaempresa.model.Venda;

public class VendaTela extends JFrame {

    private PersistenciaJPA persistencia;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField valorField;
    private JComboBox<Cliente> clienteComboBox;
    private JComboBox<Produto> produtoComboBox;

    public VendaTela() {
        persistencia = new PersistenciaJPA();
        setTitle("Gestão de Vendas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criação da tabela
        String[] columnNames = {"ID", "Data", "Valor", "Cliente"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Painel de controle
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        
        // Campos de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Valor:"));
        valorField = new JTextField();
        inputPanel.add(valorField);

        inputPanel.add(new JLabel("Cliente:"));
        clienteComboBox = new JComboBox<>();
        atualizarClientes();
        inputPanel.add(clienteComboBox);

        inputPanel.add(new JLabel("Produto:"));
        produtoComboBox = new JComboBox<>();
        atualizarProdutos();
        inputPanel.add(produtoComboBox);

        // Botões
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Adicionar");
        JButton editButton = new JButton("Editar");
        JButton deleteButton = new JButton("Excluir");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        panel.add(inputPanel);
        panel.add(buttonPanel);
        add(panel, BorderLayout.SOUTH);

        // Ações dos botões
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Venda venda = new Venda();
                    venda.setData(Calendar.getInstance());
                    venda.setValor(Double.parseDouble(valorField.getText()));
                    venda.setCliente((Cliente) clienteComboBox.getSelectedItem());
                    List<Produto> produtos = new ArrayList<>();
                    produtos.add((Produto) produtoComboBox.getSelectedItem());
                    venda.setProdutos(produtos);
                    persistencia.persist(venda);
                    atualizarTabela();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (Integer) tableModel.getValueAt(selectedRow, 0);
                Venda venda = (Venda) persistencia.find(Venda.class, id);
                if (venda != null) {
                    venda.setValor(Double.parseDouble(valorField.getText()));
                    venda.setCliente((Cliente) clienteComboBox.getSelectedItem());

                    List<Produto> produtos = new ArrayList<>();
                    produtos.add((Produto) produtoComboBox.getSelectedItem());
                    venda.setProdutos(produtos);

                    persistencia.persist(venda);
                    atualizarTabela();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
});



        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow >= 0) {
                        int id = (Integer) tableModel.getValueAt(selectedRow, 0);
                        Venda venda = (Venda) persistencia.find(Venda.class, id);
                        persistencia.remover(venda);
                        atualizarTabela();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        atualizarTabela();
    }

    private void atualizarTabela() {
        try {
            List<Venda> vendas = persistencia.findAll(Venda.class);
            tableModel.setRowCount(0);
            for (Venda venda : vendas) {
                tableModel.addRow(new Object[]{
                        venda.getId(),
                        venda.getData().getTime(),
                        venda.getValor(),
                        venda.getCliente().getNome()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void atualizarClientes() {
    try {
        List<Cliente> clientes = persistencia.findAll(Cliente.class);
        clienteComboBox.removeAllItems();
        for (Cliente cliente : clientes) {
            clienteComboBox.addItem(cliente);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private void atualizarProdutos() {
    try {
        List<Produto> produtos = persistencia.findAll(Produto.class);
        produtoComboBox.removeAllItems();
        for (Produto produto : produtos) {
            produtoComboBox.addItem(produto);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VendaTela().setVisible(true));
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
