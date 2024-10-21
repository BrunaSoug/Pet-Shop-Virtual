package br.edu.iff.ccc.bsi.petshopvirtual.entities;
import org.springframework.hateoas.RepresentationModel;
import java.time.LocalDate;
public class FuncionarioDTO extends RepresentationModel<FuncionarioDTO> {
    private Long id;
    private String nome;
    private String cargo;
    private Double salario;
    private String departamentoNome; 
    private LocalDate dataContratacao; 

    public FuncionarioDTO(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.cargo = funcionario.getCargo();
        this.salario = funcionario.getSalario();
        this.departamentoNome = funcionario.getDepartamento() != null ? funcionario.getDepartamento().getNome() : "N/A";
    
        this.dataContratacao = funcionario.getDataContratacao();
    }
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public String getDepartamentoNome() {
        return departamentoNome;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }
}
