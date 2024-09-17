package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Departamento;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DepartamentoService implements DepartamentoServiceInterface {

    private final DepartamentoRepository repodepartamento;

    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.repodepartamento = departamentoRepository;
    }

    @Override
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

    @Override
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

    @Override
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
}
