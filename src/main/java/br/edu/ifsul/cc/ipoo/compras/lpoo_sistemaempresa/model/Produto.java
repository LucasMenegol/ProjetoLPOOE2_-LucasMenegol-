package br.edu.ifsul.cc.ipoo.compras.lpoo_sistemaempresa.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    
    @Column(name = "preco", precision = 10, scale = 2, nullable = false)
    private double preco;
    
    @Column(name = "quantidade", nullable = false)
    private long quantidade;

    public Produto() {
    }

    // Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(long quantidade) {
        this.quantidade = quantidade;
    }
    
    @Override
    public String toString() {
    return nome; // para Produto
}

}
