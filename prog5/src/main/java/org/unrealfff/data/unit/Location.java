package org.unrealfff.data.unit;

public class Location {
    /**
     * Location - a secondary data unit
     */
    private Integer x; //Поле не может быть null
    private double y;
    private Double z; //Поле не может быть null
    private String name; //Строка не может быть пустой, Поле может быть null

    public Location(Integer x, double y, Double z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public Integer getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public Double getZ() {
        return this.z;
    }
    public String getName() {
        return this.name;
    }
}