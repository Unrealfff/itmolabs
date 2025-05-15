package org.unrealfff.data.unit;

import java.time.LocalDate;

public class RouteFactory {
    /**
     *buils routes
     */
    Integer id;
    public RouteFactory(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return this.id++;
    }
    public Route build(String name, Coordinates coordinates, Location from, Location to, Float distance) {
        return new Route(this.getId(), name, coordinates, LocalDate.now(), from, to, distance);
    }
}
