package br.edu.iff.ccc.bsi.petshopvirtual.entities;
import org.springframework.hateoas.RepresentationModel;
import jakarta.persistence.*;

@Entity
@Table(name = "permissoes")
public class Permissao extends RepresentationModel<Permissao> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private int nivel;

    public Permissao() {
    }

    public Permissao(String nome, int nivel) {
        this.nome = nome;
        this.nivel = nivel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
