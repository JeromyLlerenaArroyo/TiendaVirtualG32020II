package tienda;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.codecs.configuration.CodecRegistries;

class Order {
    private String id;
}
class Customer {
    private String id;
}

public class Main {
    private final MongoClient mongoClient;
    private String orderId;
    private String customerID;
    private String product_1;
    private String product_2;
    private String product_3;
    private String customerName;
    private String customerAddress;
    private Double price;
    private String courier;

    private final MongoCollection<Order> orders;
    private final MongoCollection<Customer> customers;

    public Main(MongoCollection<Order> orders, MongoCollection<Customer> customers) {
        this.orders = orders;
        this.customers = customers;
        this.mongoClient = new MongoClient( "localhost" , 27017 );
    }

    public MongoDatabase getDatabase() {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(
                        PojoCodecProvider.builder().automatic(true).build()
                )
        );
        MongoDatabase database = this.mongoClient.getDatabase("SRP").withCodecRegistry(pojoCodecRegistry);
        return database;
    }

    public Object createOrder(String orderID) {
        /*
        create Order object with this.attr
        */
        //order.setId(new ObjectId());
        Object order = new Object();
        orders.insertOne((Order) order);
        return order;
    }

    public Object createCustomer(String customerID) {
        /*
        create Customer object with this.attr
        */
        //customer.setId(new ObjectId());
        Object customer = new Object();
        orders.insertOne((Order) customer);
        return customer;
    }

    // from utils
    public String setCourierOrder() {
        /*
            DB GIS logic here
            Use latlon customer address and latlon main order address
        */
        String courierId = "LaFldsmdfr";
        return courierId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getProduct_1() {
        return product_1;
    }

    public void setProduct_1(String product_1) {
        this.product_1 = product_1;
    }

    public String getProduct_2() {
        return product_2;
    }

    public void setProduct_2(String product_2) {
        this.product_2 = product_2;
    }

    public String getProduct_3() {
        return product_3;
    }

    public void setProduct_3(String product_3) {
        this.product_3 = product_3;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }
}
