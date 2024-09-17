package br.edu.iff.ccc.bsi.petshopvirtual.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;


@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 60)
    @Size(min = 1, max = 60, message = "O nome do produto deve ter entre 1 e 60 caracteres")
    private String nomeProduto;

    @Column(nullable = false, length = 20)
    @Size(min = 1, max = 20, message = "A categoria do produto deve ter entre 1 e 20 caracteres")
    private String categoriaProduto;

    @Column(nullable = false)
    @Min(value = 0, message = "A quantidade em estoque deve ser no mínimo 0")
    @Max(value = 9999, message = "A quantidade em estoque deve ser no máximo 9999")
    private int quantidadeEstoque;

    @Column(nullable = false)
    @DecimalMin(value = "1.00", inclusive = true, message = "O preço deve ser no mínimo 1")
    @DecimalMax(value = "99999.00", inclusive = true, message = "O preço deve ser no máximo 99999")
    private double preco;

    public Produto() {
    }

    public Produto(String nomeProduto, String categoriaProduto, int quantidadeEstoque, double preco) {
        this.nomeProduto = nomeProduto;
        this.categoriaProduto = categoriaProduto;
        this.quantidadeEstoque = quantidadeEstoque;
        this.preco = preco;
    }

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(String categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    @Override
    public String toString() {
        return "Produto{" +
                "id=" + getId() +
                ", nome='" + getNomeProduto() + '\'' +
                ", categoria='" + getCategoriaProduto() + '\'' +
                ", quantidadeEstoque=" + getQuantidadeEstoque() +
                ", preco=" + getPreco() +
                '}';
    }

}
