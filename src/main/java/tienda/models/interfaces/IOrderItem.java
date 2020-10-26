package tienda.models.interfaces;

import tienda.models.*;

public interface IOrderItem {

    public Double calculatePrice();

    public Order getOrder();

    public void setOrder(Order order);

    public Product getProduct();

    public Integer getQuantity();

    public Double getPrice();

}