package br.edu.iff.ccc.bsi.petshopvirtual.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "usuarios")
public class Usuario extends Pessoa {

    @Column(unique = true, nullable = false, length = 30)
    @Size(min = 3, max = 30, message = "O login deve ter entre 3 e 30 caracteres")
    private String login;

    @Column(nullable = false, length = 30)
    @Size(min = 6, max = 30, message = "A senha deve ter entre 6 e 30 caracteres")
    private String senha;

    @Column(nullable = false)
    @Min(value = 1, message = "Permissão deve ser no mínimo 1")
    @Max(value = 15, message = "Permissão deve ser no máximo 15")
    private int permissao;

    public Usuario() {
    }

    public Usuario(String cpf, String nome, String email, String telefone, String endereco, String login, String senha, int permissao) {
        super(cpf, nome, email, telefone, endereco);
        this.login = login;
        this.senha = senha;
        this.permissao = permissao;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getPermissao() {
        return permissao;
    }

    public void setPermissao(int permissao) {
        this.permissao = permissao;
    }
}
