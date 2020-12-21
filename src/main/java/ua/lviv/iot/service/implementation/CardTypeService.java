package ua.lviv.iot.service.implementation;


import ua.lviv.iot.DAO.GeneralDAO;
import ua.lviv.iot.DAO.implementation.CardTypeDaoImpl;
import ua.lviv.iot.model.entity.CardType;
import ua.lviv.iot.service.AbstractService;

public final class CardTypeService extends AbstractService<CardType> {
    @Override
    public GeneralDAO<CardType> getGeneralDAO() {
        return new CardTypeDaoImpl();
    }
}
