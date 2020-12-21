package ua.lviv.iot.service.implementation;

import ua.lviv.iot.DAO.GeneralDAO;
import ua.lviv.iot.DAO.implementation.ClientDaoImpl;
import ua.lviv.iot.model.entity.Client;
import ua.lviv.iot.service.AbstractService;

public final class ClientService extends AbstractService<Client> {
    @Override
    public GeneralDAO<Client> getGeneralDAO() {
        return new ClientDaoImpl();
    }
}
