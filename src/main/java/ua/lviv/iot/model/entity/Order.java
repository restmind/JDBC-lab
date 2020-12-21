package ua.lviv.iot.model.entity;

public final class Order {
    private int id;
    private int clientId;
    private int serviceId;
    private int gymId;

    public Order(final int id, final int clientId, final int serviceId, final int gymId) {
        this.id = id;
        this.clientId = clientId;
        this.serviceId = serviceId;
        this.gymId = gymId;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(final int clientId) {
        this.clientId = clientId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(final int serviceId) {
        this.serviceId = serviceId;
    }

    public int getGymId() {
        return gymId;
    }

    public void setGymId(final int gymId) {
        this.gymId = gymId;
    }

    @Override
    public String toString() {
        return id + " " + clientId + " " + serviceId + " " + gymId;
    }
}
