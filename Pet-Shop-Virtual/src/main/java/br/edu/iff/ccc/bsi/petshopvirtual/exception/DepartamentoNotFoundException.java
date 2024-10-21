package br.edu.iff.ccc.bsi.petshopvirtual.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DepartamentoNotFoundException extends RuntimeException {
    public DepartamentoNotFoundException() {
        super("Departamento não encontrado");
    }
    public  DepartamentoNotFoundException(String message) {
        super(message);
    }
}
