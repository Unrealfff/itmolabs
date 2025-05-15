package org.unrealfff.data.unit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class Route {
    /**
     * Route - main data unit
     */
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location from; //Поле может быть null
    private Location to; //Поле не может быть null
    private Float distance; //Поле может быть null, Значение поля должно быть больше 1

    public Route(Integer id, String name, Coordinates coordinates, LocalDate creationDate, Location from,
                 Location to, Float distance) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Route(String[] data) {
        this.id = Integer.parseInt(data[0]);
        this.name = data[1];
        this.coordinates = new Coordinates(Long.parseLong(data[2]), Long.parseLong(data[3]));
        this.creationDate = LocalDate.parse(data[4]);
        if(data[5].isEmpty()) {
            this.from = null;
        }
        else{
            this.from = new Location(Integer.parseInt(data[5]), Double.parseDouble(data[6]), Double.parseDouble(data[7]), data[8]);
        }
        this.to = new Location(Integer.parseInt(data[9]), Double.parseDouble(data[10]), Double.parseDouble(data[11]), data[12]);
        if (data.length == 14) {
            if (!data[13].isEmpty()) {
                this.distance = Float.parseFloat(data[13]);
            }
            else{
                this.distance = null;
            }
        }
        else {
            this.distance = null;
        }
    }

    public Integer getId() {
        return this.id;
    }

    public boolean equals(Route route) {
        return this.id.equals(route.getId());
    }

    public boolean equals(Integer id) {
        return this.id.equals(id);
    }

    public ArrayList<String> data() {
        ArrayList<String> data = new ArrayList<>();
        data.add(this.id.toString());
        data.add(this.name);
        data.add(this.coordinates.getX().toString());
        data.add(this.coordinates.getY().toString());
        data.add(this.creationDate.toString());
        if (this.from != null) {
            data.add(this.from.getX().toString());
            data.add(String.valueOf(this.from.getY()));
            data.add(this.from.getZ().toString());
            data.add(this.from.getName());
        }
        else{
            for(int i = 0; i < 4; i++) {
                data.add("");
            }
        }
        data.add(this.to.getX().toString());
        data.add(String.valueOf(this.to.getY()));
        data.add(this.to.getZ().toString());
        data.add(this.to.getName());
        if(this.distance != null) {
            data.add(this.distance.toString());
        }
        else{
            data.add("");
        }
        return data;
    }

    public String toString() {
        String res;
        if(this.from != null) {
            res = this.id.toString() + "," + this.name + "," + this.coordinates.getX().toString() + "," +
                    this.coordinates.getY().toString() + "," + this.creationDate.toString() + "," +
                    this.from.getX().toString() + "," + this.from.getY() + "," + this.from.getZ().toString() + "," +
                    this.from.getName() + "," + this.to.getX().toString() + "," + this.to.getY() + "," +
                    this.to.getZ().toString() + "," + this.to.getName() + ",";
        }
        else {
            res = this.id.toString() + "," + this.name + "," + this.coordinates.getX().toString() + "," +
                    this.coordinates.getY().toString() + "," + this.creationDate.toString() + "," + "," + "," + "," + "," +
                    this.to.getX().toString() + "," + this.to.getY() + "," + this.to.getZ().toString() + "," +
                    this.to.getName() + ",";
        }
        if (this.distance != null) {
            res += this.distance.toString();
        }
        return res;
    }

    public void update(Route route) {
        this.name = route.getName();
        this.coordinates = route.getCoordinates();
        this.creationDate = route.getCreationDate();
        this.from = route.getFrom();
        this.to = route.getTo();
        this.distance = route.getDistance();
    }

    public String show() {
        String res;
        String fields = "id - %s %n" +
                "name - %s %n" +
                "cord_x - %s %n" +
                "cord_y - %s %n" +
                "creationDate - %s %n" +
                "from_x - %s %n" +
                "from_y - %s %n" +
                "from_z - %s %n" +
                "from_name - %s %n" +
                "to_x - %s %n" +
                "to_y - %s %n" +
                "to_z - %s %n" +
                "to_name - %s %n" +
                "distance - %s %n";
        String tmpDistance;
        if (this.distance != null) {
            tmpDistance = distance.toString();
        }
        else{
            tmpDistance = "";
        }
        if(this.from != null) {
            res = String.format(fields, this.id, this.name, this.coordinates.getX(), this.coordinates.getY(),
                    this.creationDate.toString(), this.from.getX(), this.from.getY(), this.from.getZ(), this.from.getName(),
                    this.to.getX(), this.to.getY(), this.to.getZ(), this.to.getName(), tmpDistance);
        }
        else {
            res = String.format(fields, this.id, this.name, this.coordinates.getX(), this.coordinates.getY(),
                    this.creationDate.toString(), "", "", "", "",
                    this.to.getX(), this.to.getY(), this.to.getZ(), this.to.getName(), tmpDistance);
        }
        return res;
    }

    public Float getDistance() {
        return distance;
    }

    static public Route valueOf(String data) {
        String[] matData = data.split(",");
        return new Route(matData);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Location getFrom() {
        return from;
    }
    public Location getTo() {
        return to;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}