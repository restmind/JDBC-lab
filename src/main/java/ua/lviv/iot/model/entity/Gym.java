package ua.lviv.iot.model.entity;

import java.util.Objects;

public final class Gym {
    private Integer id;
    private String location;

    public Gym(final Integer id, final String location) {
        this.id = id;
        this.location = location;
    }

    public Gym() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return id + " " + location;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gym gym = (Gym) o;
        return Objects.equals(id, gym.id) &&
                Objects.equals(location, gym.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location);
    }
}
