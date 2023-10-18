package Collections;

import java.io.Serializable;

/**
 * Класс Route
 */

public class Location implements Serializable {

    private Integer x;
    private Integer y;
    private String name;

    public Integer getX() {
        return x;
    }
    public String getName() {
        return name;
    }
    public Integer getY() {
        return y;
    }

    /**
     * Конструктор для глубокого копирования данных
     */

    public Location(String to) {
        this.setName(to.split(" ")[0]);
        this.setX(Integer.valueOf(to.split(" ")[1]));
        this.setY(Integer.valueOf(to.split(" ")[2]));
    }
    public String toString(){
        String out = this.getName()+" "+this.getX().toString()+" "+this.getY().toString();
        return out;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setY(Integer y) {
        this.y = y;
    }
    public void setX(Integer x) {
        this.x = x;
    }
}
