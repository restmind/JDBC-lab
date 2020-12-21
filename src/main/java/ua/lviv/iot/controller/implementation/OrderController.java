package ua.lviv.iot.controller.implementation;

import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.model.entity.Order;
import ua.lviv.iot.service.Service;
import ua.lviv.iot.service.implementation.OrderService;

import java.sql.SQLException;
import java.util.InputMismatchException;

public final class OrderController extends AbstractController<Order> {
    private final OrderService orderService = new OrderService();

    @Override
    public Service<Order> getService() {
        return orderService;
    }

    @Override
    public void create() {
        Order order = new Order();
        try {
            System.out.println("Enter client id: ");
            order.setClientId(Integer.parseInt(INPUT.nextLine()));
            System.out.println("Enter service id: ");
            order.setServiceId(Integer.parseInt(INPUT.nextLine()));
            System.out.println("Enter your gym id: ");
            order.setGymId(Integer.parseInt(INPUT.nextLine()));
            super.create(order);
        } catch (InputMismatchException e) {
            System.out.println("Your input is not valid! Please, follow constraints!\n");
            INPUT.next();
        }
    }

    @Override
    public void update() {
        System.out.println("Enter id of entity you want to update: ");
        Integer id = Integer.parseInt(INPUT.next());
        try {
            Order entity = getService().findById(id);
            if (entity != null) {
                Order updatedEntity = new Order();
                System.out.println("Write client id: ");
                updatedEntity.setClientId(Integer.parseInt(INPUT.next()));
                System.out.println("Write service id: ");
                updatedEntity.setServiceId(Integer.parseInt(INPUT.next()));
                System.out.println("Write gym id: ");
                updatedEntity.setGymId(Integer.parseInt(INPUT.next()));

                updatedEntity.setId(id);
                super.update(id, updatedEntity);
            } else {
                System.out.println("Oops...such object does not exist!\n");
            }
        } catch (SQLException | SecurityException | IllegalArgumentException e1) {
            System.out.println("Oops...something went wrong\n" + e1.getMessage());
        }
    }
}
