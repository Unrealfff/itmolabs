package org.unrealfff.data.unit;

public class Coordinates {
    /**
     * Coordinates - a secondary data unit
     */
    private Long x; //Максимальное значение поля: 500, Поле не может быть null
    private Long y; //Поле не может быть null

    public Coordinates(Long x, Long y) {
        this.x = x;
        this.y = y;
    }
    public Long getX() {
        return this.x;
    }
    public Long getY() {
        return this.y;
    }
}