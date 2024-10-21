package br.edu.iff.ccc.bsi.petshopvirtual.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProdutoNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ProdutoNotFoundException() {
        super("Produto não encontrada");
    }
    public ProdutoNotFoundException(String mensagem) {
        super(mensagem);
    }
}
