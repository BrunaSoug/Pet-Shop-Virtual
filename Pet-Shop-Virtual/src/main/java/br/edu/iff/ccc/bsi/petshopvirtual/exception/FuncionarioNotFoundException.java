package br.edu.iff.ccc.bsi.petshopvirtual.exception;

public class FuncionarioNotFoundException extends RuntimeException {

    public FuncionarioNotFoundException() {
        super("Funcionário não encontrado");
    }
    public FuncionarioNotFoundException(String message) {
        super(message);
    }
}
