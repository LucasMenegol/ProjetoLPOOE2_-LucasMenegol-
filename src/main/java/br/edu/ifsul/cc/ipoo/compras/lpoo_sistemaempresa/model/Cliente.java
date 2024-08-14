package br.edu.ifsul.cc.ipoo.compras.lpoo_sistemaempresa.model;

import java.util.ArrayList;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    
    @Column(name = "endereco", nullable = false, length = 255)
    private String endereco;
    @Column(name = "fone", nullable = false, length = 15)
    private String telefone;
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Venda> vendas;

    public Cliente() {
        vendas=new ArrayList<>();
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    // Métodos de negócios

    public void consultarHistoricoDeCompras() {
        // Implementação do método consultarHistoricoDeCompras
    }
    
    @Override
    public String toString() {
    return nome; // para Cliente
}
}

