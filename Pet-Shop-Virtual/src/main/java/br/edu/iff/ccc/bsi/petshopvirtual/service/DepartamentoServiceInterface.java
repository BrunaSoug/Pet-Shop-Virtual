package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Departamento;
import java.util.Optional;

public interface DepartamentoServiceInterface {
    Departamento salvarDepartamento(Departamento departamento);
    Optional<Departamento> obterDepartamentoPorId(Long id);
    void deletarDepartamento(Long id);
}