package Commands;

import Exceptions.NotEnoughArguments;
import Exceptions.WrongArgument;
import Run.CollectionManager;


/**
 * Class for clearing collection.
 *
 */
public class Clear implements Command{
    private String name = "clear";
    private final CollectionManager collectionManager;

    public Clear(CollectionManager manager) {
        this.collectionManager = manager;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Ser_Command execute() throws WrongArgument, NotEnoughArguments {
        Ser_Command answer = new Ser_Command("answer", "cleared");
        collectionManager.clear();
        return answer;
    }

    @Override
    public String getDescription() {
        return "clear collection";
    }
}
