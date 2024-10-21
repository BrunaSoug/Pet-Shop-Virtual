package br.edu.iff.ccc.bsi.petshopvirtual.exception;

public class PedidoNotFoundException extends RuntimeException {
    public PedidoNotFoundException() {
        super("Pedido não encontrado");
    }
    
    public PedidoNotFoundException(String message) {
        super(message);
    }
}
