package tienda.repositories.impl;

import tienda.models.Product;
import tienda.repositories.ProductRepository;

import java.util.List;
import java.util.LinkedList;

//import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

public class ProductRepositoryImpl implements ProductRepository {

    private static final String COLLECTION_NAME = "Products";
    private static final FindOneAndReplaceOptions UPDATE_OPTIONS = new FindOneAndReplaceOptions()
            .returnDocument(ReturnDocument.AFTER);

    private final MongoCollection<Product> Products;

    public ProductRepositoryImpl(MongoDatabase database) {
        this.Products = database.getCollection(COLLECTION_NAME, Product.class);
    }

    @Override
    public void create(Product Product) {
        System.out.println("entrando: " + Product);
        //Product.setCodigo((new ObjectId()).toString());
        Products.insertOne(Product);
    }

    @Override
    public void delete(String id) {
        Products.deleteOne(new Document("_id", id));
    }

    @Override
    public Product find(String id) {
        //System.out.println("_id: " + id);
        return Products.find(eq("_id", id)).first();
    }

    @Override
    public List<Product> findAll() {
        List<Product> result = new LinkedList<>();

        for (Product Product : Products.find()) {
            //System.out.println("Product: " + Product);
            result.add(Product);
        }

        return result;
    }

    @Override
    public Product update(Product post, String id) {
        return Products.findOneAndReplace(new Document("_id", id), post, UPDATE_OPTIONS);
    }

}
