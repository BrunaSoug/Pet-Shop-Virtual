package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Permissao;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao salvar(Permissao permissao) {
        return permissaoRepository.save(permissao);
    }

    public Optional<Permissao> buscarPorId(Long id) {
        return permissaoRepository.findById(id);
    }

    public List<Permissao> listarTodas() {
        return permissaoRepository.findAll();
    }

    public void deletarPorId(Long id) {
        permissaoRepository.deleteById(id);
    }
}
