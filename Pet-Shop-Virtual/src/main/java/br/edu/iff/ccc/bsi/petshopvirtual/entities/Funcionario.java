package br.edu.iff.ccc.bsi.petshopvirtual.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "funcionarios")
public class Funcionario extends Pessoa {

    @Column(nullable = false)
    @DecimalMin(value = "1000.00", inclusive = true, message = "O salário deve ser no mínimo 1000")
    @DecimalMax(value = "20000.00", inclusive = true, message = "O salário deve ser no máximo 20000")
    private double salario;

    @Column(nullable = false, length = 30)
    @Size(min = 5, max = 30, message = "O cargo deve ter entre 5 e 30 caracteres")
    private String cargo;

    public Funcionario() {
    }

    public Funcionario(String cpf, String nome, String email, String telefone, String endereco, double salario, String cargo) {
        super(cpf, nome, email, telefone, endereco);
        this.salario = salario;
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + getId() +
                ", cpf='" + getCpf() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                ", endereco='" + getEndereco() + '\'' +
                ", salario=" + salario +
                ", cargo='" + cargo + '\'' +
                '}';
    }

}
