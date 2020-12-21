package ua.lviv.iot.service.implementation;

import ua.lviv.iot.DAO.GeneralDAO;
import ua.lviv.iot.DAO.implementation.GymDaoImpl;
import ua.lviv.iot.model.entity.Gym;
import ua.lviv.iot.service.AbstractService;

public final class GymService extends AbstractService<Gym> {
    @Override
    public GeneralDAO<Gym> getGeneralDAO() {
        return new GymDaoImpl();
    }
}
