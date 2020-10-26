package tienda.models.impl;

import tienda.models.Order;
import tienda.models.Product;
import tienda.models.interfaces.IOrderItem;

public class OrderItemInternet implements IOrderItem {
    private String idProduct;
    private Integer quantity;
    private Double price;

    public OrderItemInternet(String idProduct,Integer quantity,Double price)  {
        //this.order = order;
        //this.product = product;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public Double calculatePrice() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Order getOrder() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Double getPrice() {
        // Intenet Promocion
        Double customPrice = 0.0;
        customPrice = this.price * this.quantity * 0.85;
        return customPrice;
    }

    @Override
    public Product getProduct() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer getQuantity() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setOrder(Order order) {
        // TODO Auto-generated method stub

    }
    
}