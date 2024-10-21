package br.edu.iff.ccc.bsi.petshopvirtual.entities;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Cliente extends Pessoa {

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos;
   
    public Cliente() {
    }

    public Cliente(String cpf, String nome, String email, String telefone, String endereco, LocalDate dataNascimento) {
        super(cpf, nome, email, telefone, endereco);
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
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
                ", dataNascimento=" + dataNascimento +
                '}';
    }

}
