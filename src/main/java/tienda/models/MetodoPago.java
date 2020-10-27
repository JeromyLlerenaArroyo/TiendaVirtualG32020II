package tienda.models;

public class MetodoPago {

    public void pagarPedido(Pedido order){

        /* paying */
        System.out.println("Pagando con el metodo del Banco | total: "+ order.getMontoTotal());
    }

}
