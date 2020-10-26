package tienda.controllers.impl;

import tienda.config.Paths;
//import tienda.config.Paths;
import tienda.controllers.ProductController;
import tienda.models.Product;
import tienda.repositories.ProductRepository;
import io.javalin.http.Context;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.NotFoundResponse;
//import org.bson.types.ObjectId;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpStatus;


public class ProductControllerImpl implements ProductController {
    private static final String ID = "id";

    private ProductRepository ProductRepository;

    public ProductControllerImpl(ProductRepository postRepository) {
        this.ProductRepository = postRepository;
    }

    @Override
    public void create(Context context) {

        Product Product = context.bodyAsClass(Product.class);
        System.out.println("Producto: " + Product);

        //if (Product.getId() != null) {
        //    throw new BadRequestResponse(String.format("Unable to create a new post with existing id: %s", Product));
        //}

        ProductRepository.create(Product);
        context.status(HttpStatus.CREATED_201)
                .header(HttpHeader.LOCATION.name(), Paths.formatPostLocation(Product.getId().toString()));

    }

    @Override
    public void delete(Context context) {
        ProductRepository.delete(context.pathParam(ID));

    }

    @Override
    public void find(Context context) {
        String id = context.pathParam(ID);
        Product Product = ProductRepository.find(id);

        if (Product == null) {
            throw new NotFoundResponse(String.format("A Product with id '%s' is not found", id));
        }

        context.json(Product);

    }

    @Override
    public void findAll(Context context) {
        context.json(ProductRepository.findAll());
    }

    @Override
    public void update(Context context) {
        Product Product = context.bodyAsClass(Product.class);
        String id = context.pathParam(ID);

        if (Product.getId() != null && !Product.getId().toString().equals(id)) {
            throw new BadRequestResponse("Id update is not allowed");
        }

        ProductRepository.update(Product, id);

    }

}