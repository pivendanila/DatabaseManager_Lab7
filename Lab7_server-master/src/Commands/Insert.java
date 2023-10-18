package Commands;

import Collections.Route;
import Exceptions.NotEnoughArguments;
import Exceptions.WrongArgument;
import Exceptions.WrongField;
import Run.CollectionManager;

import java.util.Locale;
import java.util.Scanner;

/**
 * Class for Inserting the element in collection by index.
 */

public class Insert implements Command{
    private String name = "insert_at";
    private int index;
    private CollectionManager manager;
    private Route route;
    public Insert(CollectionManager manager, int index, Route route) {
        this.manager = manager;
        this.index = index;
        this.route = route;

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Ser_Command execute() throws WrongArgument, NotEnoughArguments {
        manager.updateHistory(getName());
        Ser_Command answer = new Ser_Command("answer", "inserted");
        return answer;
    }

    @Override
    public String getDescription() {
        return "add a new element at a given position(index)";
    }
}
