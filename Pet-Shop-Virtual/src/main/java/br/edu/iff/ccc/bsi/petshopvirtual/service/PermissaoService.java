package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Permissao;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissaoService implements PermissaoServiceInterface {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Override
    public Permissao save(Permissao permissao) {
        return permissaoRepository.save(permissao);
    }

    @Override
    public Optional<Permissao> findById(Long id) {
        return permissaoRepository.findById(id);
    }

    @Override
    public List<Permissao> findAll() {
        return permissaoRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        permissaoRepository.deleteById(id);
    }
}
