package tienda.controllers.impl;

import tienda.config.Paths;
import tienda.controllers.OrderController;
import tienda.models.Order;
import tienda.repositories.OrderRepository;
import tienda.utils.OrderCourierDispatcher;

import io.javalin.http.Context;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.NotFoundResponse;
//import org.bson.types.ObjectId;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpStatus;

public class OrderControllerImpl implements OrderController {
    private static final String ID = "id";

    private OrderRepository orderRepository;

    public OrderControllerImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void create(Context context) {
        Order order = context.bodyAsClass(Order.class);

        if (order.getId() != null) {
            throw new BadRequestResponse(String.format("Unable to create a new order with existing id: %s", order));
        }

        OrderCourierDispatcher orderCourierDispatcher = new OrderCourierDispatcher(order);
        String bestCourier = orderCourierDispatcher.getBestCourier();
        order.setCourier(bestCourier);

        orderRepository.create(order);
        context.status(HttpStatus.CREATED_201)
                .header(HttpHeader.LOCATION.name(), Paths.formatPostLocation(order.getId().toString()));

    }

    public void find(Context context) {
        String id = context.pathParam(ID);
        Order order = orderRepository.find(id);

        if (order == null) {
            throw new NotFoundResponse(String.format("A order with id '%s' is not found", id));
        }

        context.json(order);

    }

    public void findAll(Context context) {
        context.json(orderRepository.findAll());
    }
    
    @Override
    public void delete(Context context) {
        orderRepository.delete(context.pathParam(ID));

    }


    @Override
    public void update(Context context) {
        Order order = context.bodyAsClass(Order.class);
        String id = context.pathParam(ID);

        if (order.getId() != null && !order.getId().toString().equals(id)) {
            throw new BadRequestResponse("Id update is not allowed");
        }

        orderRepository.update(order, id);

    }
}