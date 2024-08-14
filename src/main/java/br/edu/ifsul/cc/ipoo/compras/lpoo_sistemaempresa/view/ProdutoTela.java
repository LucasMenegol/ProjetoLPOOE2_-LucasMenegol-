package br.edu.ifsul.cc.ipoo.compras.lpoo_sistemaempresa.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import br.edu.ifsul.cc.ipoo.compras.lpoo_sistemaempresa.dao.PersistenciaJPA;
import br.edu.ifsul.cc.ipoo.compras.lpoo_sistemaempresa.model.Produto;

public class ProdutoTela extends JFrame {

    private PersistenciaJPA persistencia;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nomeField, precoField, quantidadeField;

    public ProdutoTela() {
        persistencia = new PersistenciaJPA();
        setTitle("Gestão de Produtos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criação da tabela
        String[] columnNames = {"ID", "Nome", "Preço", "Quantidade"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Adiciona o ListSelectionListener
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Verifica se a seleção foi concluída
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    nomeField.setText((String) tableModel.getValueAt(selectedRow, 1));
                    precoField.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    quantidadeField.setText(tableModel.getValueAt(selectedRow, 3).toString());
                }
            }
        });

        // Painel de controle
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        // Campos de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        inputPanel.add(nomeField);
        inputPanel.add(new JLabel("Preço:"));
        precoField = new JTextField();
        inputPanel.add(precoField);
        inputPanel.add(new JLabel("Quantidade:"));
        quantidadeField = new JTextField();
        inputPanel.add(quantidadeField);

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
        addButton.addActionListener(e -> {
            try {
                Produto produto = new Produto();
                produto.setNome(nomeField.getText());
                produto.setPreco(Double.parseDouble(precoField.getText()));
                produto.setQuantidade(Integer.parseInt(quantidadeField.getText()));
                persistencia.persist(produto);
                atualizarTabela();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        editButton.addActionListener(e -> {
            try {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (Integer) tableModel.getValueAt(selectedRow, 0);
                    Produto produto = (Produto) persistencia.find(Produto.class, id);
                    if (produto != null) {
                        produto.setNome(nomeField.getText());
                        produto.setPreco(Double.parseDouble(precoField.getText()));
                        produto.setQuantidade(Integer.parseInt(quantidadeField.getText()));
                        persistencia.persist(produto);
                        atualizarTabela();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        deleteButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (Integer) tableModel.getValueAt(selectedRow, 0);
                Produto produto = (Produto) persistencia.find(Produto.class, id);
                if (produto != null) {
                    persistencia.remover(produto);
                    atualizarTabela();
                }
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
            List<Produto> produtos = persistencia.findAll(Produto.class);
            tableModel.setRowCount(0);
            for (Produto produto : produtos) {
                tableModel.addRow(new Object[]{
                        produto.getId(),
                        produto.getNome(),
                        produto.getPreco(),
                        produto.getQuantidade()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProdutoTela().setVisible(true));
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
