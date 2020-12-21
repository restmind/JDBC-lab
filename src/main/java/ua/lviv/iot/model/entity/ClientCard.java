package ua.lviv.iot.model.entity;

import java.util.Objects;

public final class ClientCard {
    private int clientId;
    private int cardTypeId;

    public ClientCard(final int clientId, final int cardTypeId) {
        this.clientId = clientId;
        this.cardTypeId = cardTypeId;
    }

    public ClientCard() {
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(final int clientId) {
        this.clientId = clientId;
    }

    public int getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(final int cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    @Override
    public String toString() {
        return clientId + " " + cardTypeId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientCard that = (ClientCard) o;
        return clientId == that.clientId &&
                cardTypeId == that.cardTypeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, cardTypeId);
    }
}
