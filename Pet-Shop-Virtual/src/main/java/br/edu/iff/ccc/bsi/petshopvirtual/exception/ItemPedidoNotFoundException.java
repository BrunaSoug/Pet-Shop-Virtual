package br.edu.iff.ccc.bsi.petshopvirtual.exception;

public class ItemPedidoNotFoundException extends RuntimeException {
    public ItemPedidoNotFoundException() {
        super("Item de pedido não encontrado");
    }
    
    public ItemPedidoNotFoundException(String message) {
        super(message);
    }
}
