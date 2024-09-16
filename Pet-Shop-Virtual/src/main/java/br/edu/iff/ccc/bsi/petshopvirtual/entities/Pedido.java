package br.edu.iff.ccc.bsi.petshopvirtual.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private LocalDate dataPedido;

    @Column(nullable = false)
    @DecimalMin(value = "1.00", inclusive = true, message = "O valor total deve ser no mínimo 1.00")
    @DecimalMax(value = "999999.00", inclusive = true, message = "O valor total deve ser no máximo 999999.00")
    private double valorTotal;

    @Column(nullable = false, length = 20)
    @Size(min = 3, max = 20, message = "A forma de pagamento deve ter entre 3 e 20 caracteres")
    private String formaPagamento;

    @Column(nullable = false)
    private boolean finalizado;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itensPedido;

    public Pedido() {
    }

    public Pedido(LocalDate dataPedido, double valorTotal, String formaPagamento, boolean finalizado, Cliente cliente) {
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
        this.formaPagamento = formaPagamento;
        this.finalizado = finalizado;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }
    
    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + getId() +
                ", dataPedido=" + getDataPedido() +
                ", valorTotal=" + getValorTotal() +
                ", formaPagamento='" + getFormaPagamento() + '\'' +
                ", finalizado=" + isFinalizado() +
                ", cliente=" + getCliente().getNome() +
                '}';
    }

}