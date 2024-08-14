package br.edu.ifsul.cc.ipoo.compras.lpoo_sistemaempresa.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import br.edu.ifsul.cc.ipoo.compras.lpoo_sistemaempresa.dao.PersistenciaJPA;
import br.edu.ifsul.cc.ipoo.compras.lpoo_sistemaempresa.model.Cliente;

public class ClienteTela extends JFrame {

    private PersistenciaJPA persistencia;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nomeField, enderecoField, telefoneField, emailField;

    public ClienteTela() {
        persistencia = new PersistenciaJPA();
        setTitle("Gestão de Clientes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criação da tabela
        String[] columnNames = {"ID", "Nome", "Endereço", "Telefone", "Email"};
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
                    enderecoField.setText((String) tableModel.getValueAt(selectedRow, 2));
                    telefoneField.setText((String) tableModel.getValueAt(selectedRow, 3));
                    emailField.setText((String) tableModel.getValueAt(selectedRow, 4));
                }
            }
        });

        // Painel de controle
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        // Campos de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        inputPanel.add(nomeField);
        inputPanel.add(new JLabel("Endereço:"));
        enderecoField = new JTextField();
        inputPanel.add(enderecoField);
        inputPanel.add(new JLabel("Telefone:"));
        telefoneField = new JTextField();
        inputPanel.add(telefoneField);
        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

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
                Cliente cliente = new Cliente();
                cliente.setNome(nomeField.getText());
                cliente.setEndereco(enderecoField.getText());
                cliente.setTelefone(telefoneField.getText());
                cliente.setEmail(emailField.getText());
                persistencia.persist(cliente);
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
                    Cliente cliente = (Cliente) persistencia.find(Cliente.class, id);
                    if (cliente != null) {
                        cliente.setNome(nomeField.getText());
                        cliente.setEndereco(enderecoField.getText());
                        cliente.setTelefone(telefoneField.getText());
                        cliente.setEmail(emailField.getText());
                        persistencia.persist(cliente);
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
                        Cliente cliente = (Cliente) persistencia.find(Cliente.class, id);
                        persistencia.remover(cliente);
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
            List<Cliente> clientes = persistencia.findAll(Cliente.class);
            tableModel.setRowCount(0);
            for (Cliente cliente : clientes) {
                tableModel.addRow(new Object[]{
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getEndereco(),
                        cliente.getTelefone(),
                        cliente.getEmail()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClienteTela().setVisible(true));
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
