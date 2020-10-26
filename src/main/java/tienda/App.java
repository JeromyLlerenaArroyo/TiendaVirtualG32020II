package tienda;

import io.javalin.Javalin;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;

import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.swagger.v3.oas.models.info.Info;
import tienda.config.DBConnectionManager;
import tienda.controllers.impl.CustomerControllerImpl;
import tienda.repositories.impl.CustomerRepositoryImpl;

public class App {

    private final DBConnectionManager manager;
    private final CustomerControllerImpl customerController;

    public App() {
        this.manager = new DBConnectionManager();
        
        CustomerRepositoryImpl customerRepositoryImpl = new CustomerRepositoryImpl(this.manager.getDatabase());
        this.customerController = new CustomerControllerImpl(customerRepositoryImpl);
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

        server.get("api/customer/:id", this.customerController::find);
        server.delete("api/customer/:id", this.customerController::delete);
        server.get("api/customers", this.customerController::findAll);
        server.post("api/customer", this.customerController::create);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            this.manager.closeDatabase();
            server.stop();
        }));

    }
}