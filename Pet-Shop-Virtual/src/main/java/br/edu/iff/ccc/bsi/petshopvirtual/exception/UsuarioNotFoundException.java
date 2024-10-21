package br.edu.iff.ccc.bsi.petshopvirtual.exception;
public class UsuarioNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public UsuarioNotFoundException() {
        super("Usuario não encontrada");
    }
    public UsuarioNotFoundException(String mensagem) {
        super(mensagem);
    }
}
