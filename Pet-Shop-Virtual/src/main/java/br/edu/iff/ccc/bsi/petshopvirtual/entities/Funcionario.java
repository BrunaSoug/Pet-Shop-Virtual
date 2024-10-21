package br.edu.iff.ccc.bsi.petshopvirtual.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Funcionario extends Pessoa {

    @Column(nullable = false)
    @DecimalMin(value = "1000.00", inclusive = true, message = "O salário deve ser no mínimo 1000")
    @DecimalMax(value = "20000.00", inclusive = true, message = "O salário deve ser no máximo 20000")
    private double salario;

    @Column(nullable = false, length = 30)
    @Size(min = 5, max = 30, message = "O cargo deve ter entre 5 e 30 caracteres")
    private String cargo;

    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;

    @Column(nullable = false)
    private LocalDate dataContratacao;
    
    public Funcionario() {
    }

    public Funcionario(String cpf, String nome, String email, String telefone, String endereco, double salario, String cargo, Departamento departamento, LocalDate dataContratacao) {
        super(cpf, nome, email, telefone, endereco);
        this.salario = salario;
        this.cargo = cargo;
        this.departamento = departamento;
        this.dataContratacao = dataContratacao;
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

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
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
                ", departamento='" + (departamento != null ? departamento.getNome() : "N/A") + '\'' +
                ", dataContratacao=" + dataContratacao +
                '}';
    }

}
