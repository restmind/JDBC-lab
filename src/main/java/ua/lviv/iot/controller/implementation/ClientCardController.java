package ua.lviv.iot.controller.implementation;

import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.model.entity.ClientCard;
import ua.lviv.iot.service.Service;
import ua.lviv.iot.service.implementation.ClientCardService;

import java.sql.SQLException;
import java.util.InputMismatchException;

public final class ClientCardController extends AbstractController<ClientCard> {
    private final ClientCardService clientCardService = new ClientCardService();

    @Override
    public Service<ClientCard> getService() {
        return clientCardService;
    }

    @Override
    public void create() {
        ClientCard clientCard = new ClientCard();
        try {
            System.out.println("Enter your client id: ");
            clientCard.setClientId(Integer.parseInt(INPUT.nextLine()));

            System.out.println("Enter your card type id: ");
            clientCard.setCardTypeId(Integer.parseInt(INPUT.nextLine()));
            super.create(clientCard);
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
            ClientCard entity = getService().findById(id);
            if (entity != null) {
                ClientCard updatedEntity = new ClientCard();
                System.out.println("Enter your new client id: ");
                updatedEntity.setClientId(Integer.parseInt(INPUT.next()));

                System.out.println("Enter your new card type id: ");
                updatedEntity.setCardTypeId(Integer.parseInt(INPUT.next()));
                super.update(id, updatedEntity);
            } else {
                System.out.println("Oops...such object does not exist!\n");
            }
        } catch (SQLException | SecurityException | IllegalArgumentException e1) {
            System.out.println("Oops...something went wrong\n" + e1.getMessage());
        }
    }
}
