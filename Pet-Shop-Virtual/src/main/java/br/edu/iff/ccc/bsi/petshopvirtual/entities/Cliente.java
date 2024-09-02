package br.edu.iff.ccc.bsi.petshopvirtual.entities;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente extends Pessoa {

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos;

    public Cliente() {
    }
    public Cliente(String cpf, String nome, String email, String telefone, String endereco) {
        super(cpf, nome, email, telefone, endereco);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + getId() +
                ", cpf='" + getCpf() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                ", endereco='" + getEndereco() + '\'' +
                '}';
    }

}
