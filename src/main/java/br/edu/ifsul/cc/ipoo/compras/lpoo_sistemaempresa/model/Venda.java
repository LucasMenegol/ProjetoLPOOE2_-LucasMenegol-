package br.edu.ifsul.cc.ipoo.compras.lpoo_sistemaempresa.model;

import java.util.Calendar;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "data", nullable = false)
    private Calendar data;
    
    @Column(name = "valor", precision = 2, nullable = false)
    private double valor;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    public Venda() {
    }

    // Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        cliente.getVendas().add(this);
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    // Métodos de negócios

    public void finalizarVenda() {
        // Implementação do método finalizarVenda
    }

    public void emitirRecibo() {
        // Implementação do método emitirRecibo
    }
}

