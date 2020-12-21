package ua.lviv.iot.model.entity;

import java.util.Objects;

public final class CardType {
    private Integer id;
    private String name;

    public CardType() {
    }

    public CardType(final Integer id, final String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardType cardType = (CardType) o;
        return Objects.equals(name, cardType.name) &&
                Objects.equals(id, cardType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
