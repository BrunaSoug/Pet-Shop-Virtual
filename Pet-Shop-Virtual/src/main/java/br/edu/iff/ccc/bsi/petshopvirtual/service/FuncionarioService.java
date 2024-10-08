package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Funcionario;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repofuncionario;

    public Funcionario salvarFuncionario(Funcionario funcionario) {
        try {
            Funcionario savedFuncionario = repofuncionario.save(funcionario);
            System.out.println("Funcionário salvo com sucesso: " + savedFuncionario);
            return savedFuncionario;
        } catch (Exception e) {
            System.err.println("Erro ao salvar o funcionário: " + e.getMessage());
            throw e;
        }
    }

    public void deletarFuncionario(Long id) {
        try {
            if (repofuncionario.existsById(id)) {
                repofuncionario.deleteById(id);
                System.out.println("Funcionário com ID " + id + " deletado com sucesso.");
            } else {
                System.err.println("Funcionário não encontrado com o ID: " + id);
                throw new IllegalArgumentException("Funcionário não encontrado com o ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("Erro ao deletar o funcionário: " + e.getMessage());
            throw e;
        }
    }

    public Optional<Funcionario> obterFuncionarioPorId(Long id) {
        try {
            Optional<Funcionario> funcionario = repofuncionario.findById(id);
            if (funcionario.isPresent()) {
                System.out.println("Funcionário encontrado: " + funcionario.get());
            } else {
                System.err.println("Funcionário não encontrado com o ID: " + id);
            }
            return funcionario;
        } catch (Exception e) {
            System.err.println("Erro ao obter o funcionário: " + e.getMessage());
            throw e;
        }
    }

    public Funcionario atualizarFuncionario(Long id, Funcionario funcionarioAtualizado) {
        if (id == null || funcionarioAtualizado == null) {
            throw new IllegalArgumentException("ID e funcionário não podem ser nulos");
        }


        if (!repofuncionario.existsById(id)) {
            throw new IllegalArgumentException("Funcionário com ID " + id + " não encontrado");
        }

        funcionarioAtualizado.setId(id); 
        return repofuncionario.save(funcionarioAtualizado);
    }

    public List<Funcionario> obterTodosFuncionarios() {
        try {
            return repofuncionario.findAll();
        } catch (Exception e) {
            System.err.println("Erro ao buscar todos os funcionários: " + e.getMessage());
            throw e;
        }
    }

    public List<Funcionario> buscarFuncionariosPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio");
        }
        try {
            return repofuncionario.findByNomeContainingIgnoreCase(nome);
        } catch (Exception e) {
            System.err.println("Erro ao buscar funcionários por nome: " + e.getMessage());
            throw e;
        }
    }
}
