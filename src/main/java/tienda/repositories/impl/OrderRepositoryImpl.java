package tienda.repositories.impl;

import tienda.models.Order;
import tienda.repositories.OrderRepository;

import java.util.List;
import java.util.LinkedList;

//import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import org.bson.types.ObjectId;

public class OrderRepositoryImpl implements OrderRepository {

    private static final String COLLECTION_NAME = "Orders";
    private static final FindOneAndReplaceOptions UPDATE_OPTIONS = new FindOneAndReplaceOptions()
            .returnDocument(ReturnDocument.AFTER);

    private final MongoCollection<Order> Orders;

    public OrderRepositoryImpl(MongoDatabase database) {
        this.Orders = database.getCollection(COLLECTION_NAME, Order.class);
    }

    @Override
    public void create(Order Order) {
        System.out.println("entrando: " + Order);
        Order.setId((new ObjectId()).toString());
        Orders.insertOne(Order);
    }

    @Override
    public void delete(String id) {
        Orders.deleteOne(new Document("_id", id));
    }

    @Override
    public Order find(String id) {
        //System.out.println("_id: " + id);
        return Orders.find(eq("_id", id)).first();
    }

    @Override
    public List<Order> findAll() {
        List<Order> result = new LinkedList<>();

        for (Order Order : Orders.find()) {
            //System.out.println("Order: " + Order);
            result.add(Order);
        }

        return result;
    }

    @Override
    public Order update(Order post, String id) {
        return Orders.findOneAndReplace(new Document("_id", id), post, UPDATE_OPTIONS);
    }
    
}