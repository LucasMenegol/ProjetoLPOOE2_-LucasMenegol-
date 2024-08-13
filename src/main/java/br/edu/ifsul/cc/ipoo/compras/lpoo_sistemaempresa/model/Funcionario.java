package br.edu.ifsul.cc.ipoo.compras.lpoo_sistemaempresa.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    @Column(name = "cpf", nullable = false, length = 100)
    private String cpf;
    @Column(name = "cargo", nullable = false, length = 100)
    private String cargo;
    @Column(name = "valor", precision = 2, nullable = false)
    private double salario;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<Venda> vendas;

    public Funcionario() {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    // Métodos de negócios

    public void registrarVenda() {
        // Implementação do método registrarVenda
    }
}

