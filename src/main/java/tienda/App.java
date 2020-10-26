package tienda;

import io.javalin.Javalin;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;

import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.swagger.v3.oas.models.info.Info;
import tienda.config.DBConnectionManager;
import tienda.controllers.impl.CustomerControllerImpl;
import tienda.controllers.impl.OrderControllerImpl;
import tienda.controllers.impl.ProductControllerImpl;
import tienda.repositories.impl.CustomerRepositoryImpl;
import tienda.repositories.impl.OrderRepositoryImpl;
import tienda.repositories.impl.ProductRepositoryImpl;

public class App {

    private final DBConnectionManager manager;
    private final CustomerControllerImpl customerController;
    private final OrderControllerImpl orderController;
    private final ProductControllerImpl productController;

    public App() {
        this.manager = new DBConnectionManager();

        CustomerRepositoryImpl customerRepositoryImpl = new CustomerRepositoryImpl(this.manager.getDatabase());
        this.customerController = new CustomerControllerImpl(customerRepositoryImpl);

        ProductRepositoryImpl productRepositoryImpl = new ProductRepositoryImpl(this.manager.getDatabase());
        this.productController = new ProductControllerImpl(productRepositoryImpl);

        OrderRepositoryImpl orderRepositoryImpl = new OrderRepositoryImpl(this.manager.getDatabase());
        this.orderController = new OrderControllerImpl(orderRepositoryImpl);

    }

    public static void main(String[] args) {
        new App().startup();
    }
    public void startup() {
        Info applicationInfo = new Info()
                .version("1.0")
                .description("Demo API");
        OpenApiOptions openApi = new OpenApiOptions(applicationInfo)
                .path("/api")
                .swagger(new SwaggerOptions("/api-ui")); // endpoint for swagger-ui
        Javalin server = Javalin.create(
                config -> {
                    config.registerPlugin(new OpenApiPlugin(openApi));
                }
        ).start(7000);

        server.get("/hello", ctx -> ctx.html("Hello, Javalin!"));

        server.get("api/customer/:id", this.customerController::find);
        server.delete("api/customer/:id", this.customerController::delete);
        server.get("api/customers", this.customerController::findAll);
        server.post("api/customer", this.customerController::create);

        server.get("api/product/:id", this.productController::find);
        server.delete("api/product/:id", this.productController::delete);
        server.get("api/products", this.productController::findAll);
        server.post("api/product", this.productController::create);

        server.get("api/order/:id", this.orderController::find);
        server.delete("api/order/:id", this.orderController::delete);
        server.get("api/orders", this.orderController::findAll);
        server.post("api/order", this.orderController::create);

        server.post("api/order/pay/:id", this.orderController::pay);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            this.manager.closeDatabase();
            server.stop();
        }));
    }
}
