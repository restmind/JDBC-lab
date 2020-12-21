package ua.lviv.iot.model.entity;

import java.util.Objects;

public final class CardService {
    private int id;
    private int serviceId;
    private int cardTypeId;

    public CardService(final int id, final int serviceId, final int cardTypeId) {
        this.id = id;
        this.serviceId = serviceId;
        this.cardTypeId = cardTypeId;
    }

    public CardService() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(final int serviceId) {
        this.serviceId = serviceId;
    }

    public int getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(final int cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    @Override
    public String toString() {
        return id + " " + serviceId + " " + cardTypeId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardService that = (CardService) o;
        return id == that.id
                && serviceId == that.serviceId
                && cardTypeId == that.cardTypeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serviceId, cardTypeId);
    }
}
