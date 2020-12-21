package ua.lviv.iot.controller.implementation;

import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.model.entity.CardType;
import ua.lviv.iot.service.implementation.CardTypeService;
import ua.lviv.iot.service.Service;

import java.sql.SQLException;
import java.util.InputMismatchException;

public final class CardTypeController extends AbstractController<CardType> {
    private final CardTypeService cardTypeService = new CardTypeService();
    @Override
    public Service<CardType> getService() {
        return cardTypeService;
    }

    @Override
    public void create() {
        CardType cardType = new CardType();
        try {
            System.out.println("Enter cardType: ");
            cardType.setName(INPUT.nextLine());
            super.create(cardType);
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
            CardType entity = getService().findById(id);
            if (entity != null) {
                CardType updatedEntity = new CardType();
                System.out.println("Write new name: ");
                updatedEntity.setName(INPUT.next());

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
