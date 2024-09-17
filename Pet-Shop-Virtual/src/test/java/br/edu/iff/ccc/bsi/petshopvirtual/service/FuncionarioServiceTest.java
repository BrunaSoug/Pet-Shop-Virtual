package br.edu.iff.ccc.bsi.petshopvirtual.service;

import br.edu.iff.ccc.bsi.petshopvirtual.entities.Departamento;
import br.edu.iff.ccc.bsi.petshopvirtual.entities.Funcionario;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.DepartamentoRepository;
import br.edu.iff.ccc.bsi.petshopvirtual.repository.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FuncionarioServiceTest {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private DepartamentoRepository departamentoRepository;

    @BeforeEach
    public void limparDados() {
        funcionarioRepository.deleteAll();
        departamentoRepository.deleteAll();
    }
    @Autowired
    private FuncionarioService funcionarioService;

    @Test
    void testSalvarFuncionario() {
    	Departamento departamento = new Departamento("Relações Huamanas");
    	departamento = departamentoRepository.save(departamento);
        Funcionario funcionario = new Funcionario("12345678900", "João Silva", "joao@exemplo.com", "11999999999", "Rua A, 123", 1500.00, "Relações Humanas", departamento, LocalDate.now());
        Funcionario savedFuncionario = funcionarioService.salvarFuncionario(funcionario);
        assertNotNull(savedFuncionario.getId(), "O ID do funcionário salvo deve ser gerado.");
        assertEquals(funcionario.getNome(), savedFuncionario.getNome(), "Os nomes devem corresponder.");
        System.out.println("testSalvarFuncionario passou. Amém!");
    }

    @Test
    public void testDeletarFuncionario() {
    	Departamento departamento = new Departamento("Tecnologia");
    	departamento = departamentoRepository.save(departamento);
        Funcionario funcionario = new Funcionario("12345678900", "João Silva", "joao@exemplo.com", "11999999999", "Rua A, 123", 1500.00, "Cara do TI", departamento, LocalDate.now());
        Funcionario savedFuncionario = funcionarioService.salvarFuncionario(funcionario);

        funcionarioService.deletarFuncionario(savedFuncionario.getId());

        Optional<Funcionario> deletedFuncionario = funcionarioRepository.findById(savedFuncionario.getId());
        assertFalse(deletedFuncionario.isPresent());

        System.out.println("testDeletarFuncionario passou. Amém!");
    }

    @Test
    public void testObterFuncionarioPorId() {
    	Departamento departamento = new Departamento("Funcionario");
    	departamento = departamentoRepository.save(departamento);
        Funcionario funcionario = new Funcionario("12345678900", "João Silva", "joao@exemplo.com", "11999999999", "Rua A, 123", 1500.00, "Vendedor", departamento, LocalDate.now());
        Funcionario savedFuncionario = funcionarioService.salvarFuncionario(funcionario);

        Optional<Funcionario> foundFuncionario = funcionarioService.obterFuncionarioPorId(savedFuncionario.getId());

        assertTrue(foundFuncionario.isPresent());
        assertEquals(savedFuncionario.getId(), foundFuncionario.get().getId());

        System.out.println("testObterFuncionarioPorId passou. Amém!");
    }
}
