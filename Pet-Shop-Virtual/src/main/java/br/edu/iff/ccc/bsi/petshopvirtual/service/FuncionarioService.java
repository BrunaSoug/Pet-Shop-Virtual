package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Funcionario;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class FuncionarioService implements FuncionarioServiceInterface {
	@Autowired
    private FuncionarioRepository repofuncionario;
    
    public FuncionarioService(FuncionarioRepository repofuncionario) {
        this.repofuncionario = repofuncionario;
    }
    @Override
    public Funcionario salvarFuncionario(Funcionario funcionario) {
        try {
            Funcionario savedFuncionario = repofuncionario.save(funcionario);
            System.out.println("Funcionario salvo com sucesso: " + savedFuncionario);
            return savedFuncionario;
        } catch (Exception e) {
            System.err.println("Erro ao salvar o funcionário: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void deletarFuncionario(Long id) {
        try {
            if (repofuncionario.existsById(id)) {
                repofuncionario.deleteById(id);
                System.out.println("Funcionario com ID " + id + " deletado com sucesso.");
            } else {
                System.err.println("Funcionário não encontrado com o ID: " + id);
                throw new IllegalArgumentException("Funcionário não encontrado com o ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("Erro ao deletar o funcionário: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<Funcionario> obterFuncionarioPorId(Long id) {
        try {
            Optional<Funcionario> funcionario = repofuncionario.findById(id);
            if (funcionario.isPresent()) {
                System.out.println("Funcionario encontrado: " + funcionario.get());
            } else {
                System.err.println("Funcionário não encontrado com o ID: " + id);
            }
            return funcionario;
        } catch (Exception e) {
            System.err.println("Erro ao obter o funcionário: " + e.getMessage());
            throw e;
        }
    }
}
