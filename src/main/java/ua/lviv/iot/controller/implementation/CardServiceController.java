package ua.lviv.iot.controller.implementation;

import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.model.entity.CardService;
import ua.lviv.iot.service.Service;
import ua.lviv.iot.service.implementation.CardServiceService;

import java.sql.SQLException;
import java.util.InputMismatchException;

public final class CardServiceController extends AbstractController<CardService> {
    private final CardServiceService cardServiceService = new CardServiceService();

    @Override
    public Service<CardService> getService() {
        return cardServiceService;
    }

    @Override
    public void create() {
        CardService cardService = new CardService();
        try {
            System.out.println("Enter your service id: ");
            cardService.setServiceId(Integer.parseInt(INPUT.nextLine()));

            System.out.println("Enter your card type id: ");
            cardService.setCardTypeId(Integer.parseInt(INPUT.nextLine()));
            super.create(cardService);
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
            CardService entity = getService().findById(id);
            if (entity != null) {
                CardService updatedEntity = new CardService();
                System.out.println("Write new service id: ");
                updatedEntity.setServiceId(Integer.parseInt(INPUT.next()));
                System.out.println("Write new type card id: ");
                updatedEntity.setCardTypeId(Integer.parseInt(INPUT.next()));

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
