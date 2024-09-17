package br.edu.iff.ccc.bsi.petshopvirtual.service;
import java.util.Optional;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Funcionario;

public interface FuncionarioServiceInterface {
	  Funcionario salvarFuncionario(Funcionario funcionario);

	    void deletarFuncionario(Long id);

	    Optional<Funcionario> obterFuncionarioPorId(Long id);
}
