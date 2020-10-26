package tienda.models;

public class PaymentMethod {
    private String entity;
    private String codeEntity;

    public void payOrder(Order order){
        Double price = order.calculateTotalOrder();
        /* paying */
        System.out.println("Paying with Bank Method | total: "+price);
    }

}
