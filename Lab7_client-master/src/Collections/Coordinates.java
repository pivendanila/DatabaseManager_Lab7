package Collections;



import java.io.Serializable;

/**
 * Класс координат
 */

public class Coordinates implements Serializable {

    private Integer x;
    private Integer y;

    /**
     * Конструктор для глубокого копирования данных
     */
    public Coordinates(String coordinates) {
        this.setX(Integer.valueOf(coordinates.split(" ")[0]));
        this.setY(Integer.valueOf(coordinates.split(" ")[1]));
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String toString(){
        String out = this.getX().toString() + " " + this.getY().toString();
        return out;
    }
}
