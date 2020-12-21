package ua.lviv.iot.controller.implementation;

import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.model.entity.Client;
import ua.lviv.iot.service.implementation.ClientService;
import ua.lviv.iot.service.Service;

import java.sql.SQLException;
import java.util.InputMismatchException;

public final class ClientController extends AbstractController<Client> {
    private final ClientService clientService = new ClientService();

    @Override
    public Service<Client> getService() {
        return clientService;
    }

    @Override
    public void create() {
        Client client = new Client();
        try {
            System.out.println("Enter your name: ");
            client.setName(INPUT.nextLine());
            System.out.println("Enter your surname: ");
            client.setSurname(INPUT.nextLine());
            System.out.println("Enter your email: ");
            client.setEmail(INPUT.nextLine());
            super.create(client);
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
            Client entity = getService().findById(id);
            if (entity != null) {
                Client updatedEntity = new Client();
                System.out.println("Write new name: ");
                updatedEntity.setName(INPUT.next());
                System.out.println("Write new surname: ");
                updatedEntity.setSurname(INPUT.next());
                System.out.println("Write new email: ");
                updatedEntity.setEmail(INPUT.next());

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
