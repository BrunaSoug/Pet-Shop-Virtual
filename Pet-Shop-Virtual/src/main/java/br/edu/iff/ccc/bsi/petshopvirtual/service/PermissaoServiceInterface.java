package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Permissao;
import java.util.Optional;
import java.util.List;

public interface PermissaoServiceInterface {

	 Permissao save(Permissao permissao);
	    Optional<Permissao> findById(Long id);
	    List<Permissao> findAll();
	    void deleteById(Long id);
}
