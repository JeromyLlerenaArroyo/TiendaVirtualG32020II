package tienda.models;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.uuid.Generators;
import tienda.models.interfaces.IOrderItem;

import javax.validation.constraints.Null;


public class Order {
    private String id;
    private Double price;
    private String address;
    private String customer;

    private List<IOrderItem> orderItems;


    public List<IOrderItem> getOrderItems() {
        //List<IOrderItem> ordersItems = new ArrayList<>();
        /*
            Get logic, ORM, SQL
        */
        return orderItems;
    }

    public void setOrderItems(List<IOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Double calculateTotalOrder() {
        List<IOrderItem> ordersItems = this.getOrderItems();

        Double totalPrice = 0.0;

        for (IOrderItem item : ordersItems) {
            totalPrice += item.getPrice();
        }

        return totalPrice;
    }

    public void pay(PaymentMethod paymentMethod){
        System.out.println("Paying order "+getId());
        paymentMethod.payOrder(this);
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}