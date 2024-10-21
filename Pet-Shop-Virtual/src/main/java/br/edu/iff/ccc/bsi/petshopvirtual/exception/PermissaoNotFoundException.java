package br.edu.iff.ccc.bsi.petshopvirtual.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PermissaoNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PermissaoNotFoundException() {
        super("Permissão não encontrada");
    }

    public PermissaoNotFoundException(String mensagem) {
        super(mensagem);
    }
}
