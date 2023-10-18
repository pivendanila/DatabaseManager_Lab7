package Collections;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


/**
 * Класс Route
 */

public class Route implements Comparable<Route>, Serializable {

    private int id;
    private String name;
    private Coordinates coordinates;
    private Date creationDate;
    private Location from;
    private Location to;
    private double distance;

    /**
     * Конструктор для глубокого копирования данных
     */
    public Route() {
        setCreationDate();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = new Coordinates(coordinates);
    }

    public void setCreationDate() {
        this.creationDate = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm:ss z").get2DigitYearStart();
    }

    public String getName() {
        return name;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setFrom(String to) {
        this.from = new Location(to);
    }

    public void setTo(String to) {
        this.to = new Location(to);
    }

    public int getId() {
        return id;
    }

    public String getCoordinates() {
        return coordinates.toString();
    }

    public String getCreationDate() {
        return creationDate.toString();
    }

    public double getDistance() {
        return distance;
    }

    public String getFrom() {
        return to.toString();
    }

    public String getTo() {
        return to.toString();
    }

    /**
     * Вывод информации
     * @return
     */

    @Override
    public String toString() {
        String result = "";
        var dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        result += String.format("Id: %d\n", getId());
        result += String.format("Name: %s\n", getName());
        result += String.format("Coordinates: {X: %d, Y: %d}\n", coordinates.getX(), coordinates.getY());
        result += String.format("CreationDate: %s\n", dateFormat.format(creationDate));

        if (Objects.isNull(from)) {
            result += String.format("From: null\n");
        } else {
            result += String.format("%-5s {X: %d, Y: %d, Name: %s}\n", "From:", from.getX(), from.getY(), from.getName());
        }

        if (Objects.isNull(to)) {
            result += String.format("To: null\n");
        } else {
            result += String.format("%-5s {X: %d, Y: %d, Name: %s}\n", "To:", to.getX(), to.getY(), to.getName());
        }

        int distance_int = (int) distance;
        result += String.format("Distance: %d", distance_int);

        return result;
    }

    /**
     * Сравнение двух Route
     * @param o Другой объект для сравнения
     * @return Результат сравнения
     */
    @Override
    public int compareTo(Route o) {
        return this.getId() - o.getId();
    }
}
