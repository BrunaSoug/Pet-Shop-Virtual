package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Departamento;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.DepartamentoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DepartamentoServiceTest {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private DepartamentoService departamentoService;

    @BeforeEach
    public void setUp() {
        Departamento departamento1 = new Departamento("Vendas");
        Departamento departamento2 = new Departamento("Atendimento ao Cliente");
        Departamento departamento3 = new Departamento("Logística");
        Departamento departamento4 = new Departamento("Administração");
        departamentoRepository.save(departamento1);
        departamentoRepository.save(departamento2);
        departamentoRepository.save(departamento3);
        departamentoRepository.save(departamento4);
    }

    @AfterEach
    public void tearDown() {
        departamentoRepository.deleteAll();
    }

    @Test
    public void testSalvarDepartamento() {
        Departamento departamento = new Departamento("Saúde Animal");
        Departamento resultado = departamentoService.salvarDepartamento(departamento);

        assertNotNull(resultado, "O departamento retornado não deve ser nulo");
        assertEquals(departamento.getNome(), resultado.getNome(), "O nome do departamento retornado está incorreto");
        System.out.println("Teste de salvar departamento passou");
    }

    @Test
    public void testObterDepartamentoPorId() {
        Departamento departamento = new Departamento("Cuidados Especiais");
        departamento = departamentoService.salvarDepartamento(departamento);

        Optional<Departamento> resultado = departamentoService.obterDepartamentoPorId(departamento.getId());
        assertTrue(resultado.isPresent(), "O departamento não foi encontrado");
        assertEquals(departamento.getNome(), resultado.get().getNome(), "O nome do departamento retornado está incorreto");
        System.out.println("Teste de obter departamento por ID passou");
    }

    @Test
    public void testDeletarDepartamento() {
        Departamento departamento = new Departamento("Produtos");
        departamento = departamentoService.salvarDepartamento(departamento);

        departamentoService.deletarDepartamento(departamento.getId());
        Optional<Departamento> resultado = departamentoService.obterDepartamentoPorId(departamento.getId());
        assertFalse(resultado.isPresent(), "O departamento deveria ter sido deletado");
        System.out.println("Teste de deletar departamento passou");
    }
}
