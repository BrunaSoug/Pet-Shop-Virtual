package br.edu.iff.ccc.bsi.petshopvirtual.entities;
import org.springframework.hateoas.RepresentationModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido extends RepresentationModel<ItemPedido> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    @Max(value = 99, message = "A quantidade deve ser no máximo 99")
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    public ItemPedido() {
    }

    public ItemPedido(int quantidade, Produto produto, Pedido pedido) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.pedido = pedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    @Override
    public String toString() {
        return "ItemPedido{" +
                "id=" + getId() +
                ", quantidade=" + getQuantidade() +
                ", produto=" + getProduto().getNomeProduto() +  
                ", pedido=" + getPedido().getId() +  
                '}';
    }

}
