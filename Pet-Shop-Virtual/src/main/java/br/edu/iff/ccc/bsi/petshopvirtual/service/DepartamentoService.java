package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Departamento;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    private DepartamentoRepository repodepartamento;

    public Departamento salvarDepartamento(Departamento departamento) {
        try {
            if (departamento == null) {
                throw new IllegalArgumentException("Departamento não pode ser nulo");
            }
            return repodepartamento.save(departamento);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o departamento: " + e.getMessage(), e);
        }
    }

    public Optional<Departamento> obterDepartamentoPorId(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("ID não pode ser nulo");
            }
            return repodepartamento.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter o departamento por ID: " + e.getMessage(), e);
        }
    }

    public void deletarDepartamento(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("ID não pode ser nulo");
            }
            if (!repodepartamento.existsById(id)) {
                throw new IllegalArgumentException("Departamento com ID " + id + " não encontrado");
            }
            repodepartamento.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar o departamento: " + e.getMessage(), e);
        }
    }

    public Departamento atualizarDepartamento(Long id, Departamento departamentoAtualizado) {
        if (id == null || departamentoAtualizado == null) {
            throw new IllegalArgumentException("ID e departamento não podem ser nulos");
        }


        if (!repodepartamento.existsById(id)) {
            throw new IllegalArgumentException("Departamento com ID " + id + " não encontrado");
        }

        departamentoAtualizado.setId(id); 
        return repodepartamento.save(departamentoAtualizado);
    }

    public List<Departamento> obterTodosDepartamentos() {
        try {
            return repodepartamento.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todos os departamentos: " + e.getMessage(), e);
        }
    }

    public List<Departamento> buscarDepartamentosPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio");
        }
        try {
            return repodepartamento.findByNomeContainingIgnoreCase(nome);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar departamentos por nome: " + e.getMessage(), e);
        }
    }
}
