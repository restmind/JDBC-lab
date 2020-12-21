package ua.lviv.iot.service.implementation;

import ua.lviv.iot.DAO.GeneralDAO;
import ua.lviv.iot.DAO.implementation.CardServiceDaoImpl;
import ua.lviv.iot.model.entity.CardService;
import ua.lviv.iot.service.AbstractService;

public final class CardServiceService extends AbstractService<CardService> {
    @Override
    public GeneralDAO<CardService> getGeneralDAO() {
        return new CardServiceDaoImpl();
    }
}
