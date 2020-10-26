package tienda.models.impl;

import tienda.models.Order;
import tienda.models.Product;
import tienda.models.interfaces.IOrderItem;

public class OrderItemPromocion implements IOrderItem {

    private Order order;
    private Product product;
    private Integer quantity;
    private Double price;

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
        Double customPrice = 0.0;
        customPrice = this.price * this.quantity;

        // promo cuarentena, primera compra
        customPrice -= 20.0;

        if (customPrice < 0 ) {
            customPrice = 0.0;
        }
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