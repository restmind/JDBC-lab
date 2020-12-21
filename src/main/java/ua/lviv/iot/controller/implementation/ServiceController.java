package ua.lviv.iot.controller.implementation;

import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.model.entity.Service;
import ua.lviv.iot.service.implementation.ServiceService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.InputMismatchException;

public final class ServiceController extends AbstractController<Service> {
    private final ServiceService serviceService = new ServiceService();

    @Override
    public ua.lviv.iot.service.Service<Service> getService() {
        return serviceService;
    }

    @Override
    public void create() {
        Service service = new Service();
        try {
            System.out.println("Enter name of service: ");
            service.setName(INPUT.nextLine());
            System.out.println("Enter price of service: ");
            service.setPrice(new BigDecimal(Integer.parseInt(INPUT.nextLine())));
            super.create(service);
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
            Service entity = getService().findById(id);
            if (entity != null) {
                Service updatedEntity = new Service();
                System.out.println("Write new name: ");
                updatedEntity.setName(INPUT.next());
                System.out.println("Write new price: ");
                updatedEntity.setPrice(new BigDecimal(Integer.parseInt(INPUT.next())));

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
