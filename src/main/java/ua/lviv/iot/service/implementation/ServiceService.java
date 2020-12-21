package ua.lviv.iot.service.implementation;

import ua.lviv.iot.DAO.GeneralDAO;
import ua.lviv.iot.DAO.implementation.ServiceDaoImpl;
import ua.lviv.iot.model.entity.Service;
import ua.lviv.iot.service.AbstractService;

public class ServiceService extends AbstractService<Service> {
    @Override
    public GeneralDAO<Service> getGeneralDAO() {
        return new ServiceDaoImpl();
    }
}
