package ua.lviv.iot.service.implementation;

import ua.lviv.iot.DAO.GeneralDAO;
import ua.lviv.iot.DAO.implementation.ClientCardDaoImpl;
import ua.lviv.iot.model.entity.ClientCard;
import ua.lviv.iot.service.AbstractService;

public final class ClientCardService extends AbstractService<ClientCard> {
    @Override
    public GeneralDAO<ClientCard> getGeneralDAO() {
        return new ClientCardDaoImpl();
    }
}
