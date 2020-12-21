package ua.lviv.iot.controller.implementation;

import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.model.entity.Gym;
import ua.lviv.iot.service.implementation.GymService;
import ua.lviv.iot.service.Service;

import java.sql.SQLException;
import java.util.InputMismatchException;

public final class GymController extends AbstractController<Gym> {
    private final GymService gymService = new GymService();

    @Override
    public Service<Gym> getService() {
        return gymService;
    }

    @Override
    public void create() {
        Gym gym = new Gym();
        try {
            System.out.println("Enter gym location: ");
            gym.setLocation(INPUT.nextLine());
            super.create(gym);
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
            Gym entity = getService().findById(id);
            if (entity != null) {
                Gym updatedEntity = new Gym();
                System.out.println("Write new location: ");
                updatedEntity.setLocation(INPUT.next());

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


