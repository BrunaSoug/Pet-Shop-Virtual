package br.edu.iff.ccc.bsi.petshopvirtual.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PessoaNotFoundException extends RuntimeException {
     private static final long serialVersionUID = 1L;

    public PessoaNotFoundException() {
        super("Pessoa não encontrada");
    }

    public  PessoaNotFoundException(String mensagem) {
        super(mensagem);
    }
}
