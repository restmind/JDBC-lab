package ua.lviv.iot.service.implementation;

import ua.lviv.iot.DAO.GeneralDAO;
import ua.lviv.iot.DAO.implementation.OrderDaoImpl;
import ua.lviv.iot.model.entity.Order;
import ua.lviv.iot.service.AbstractService;

public final class OrderService extends AbstractService<Order> {
    @Override
    public GeneralDAO<Order> getGeneralDAO() {
        return new OrderDaoImpl();
    }
}
