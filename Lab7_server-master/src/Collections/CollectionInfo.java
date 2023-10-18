package Collections;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Класс для хранения данных о коллекции
 *
 */


public class CollectionInfo implements Serializable {

    private final Object type;
    private final LocalDateTime initializationDate;
    private final Integer countElements;

    public CollectionInfo(Object type, LocalDateTime initializationDate, Integer countElements) {
        this.type = type;
        this.initializationDate = initializationDate;
        this.countElements = countElements;
    }


    /**
     * Вывод информации о коллекции
     * @return
     */
    @Override
    public String toString() {
        String result = "";

        //Вывод даты в таком формате
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        result += String.format("Тип: %s\n", type);
        result += String.format("Дата инициализации: %s\n", initializationDate.format(format));
        result += String.format("Количество элементов: %d", countElements);

        return result;
    }
}
