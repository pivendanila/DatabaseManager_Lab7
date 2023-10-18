package Commands;

import Exceptions.NotEnoughArguments;
import Exceptions.WrongArgument;
import Run.CollectionManager;

/**
 * Class for showing content of collection.
 */
public class Show implements Command{
    private String name = "show";
    private final CollectionManager manager;

    public Show(CollectionManager manager) {
        this.manager = manager;
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Ser_Command execute() throws WrongArgument, NotEnoughArguments {
        Ser_Command answer = null;
        manager.updateHistory(getName());
        answer = new Ser_Command("answer", manager.show());
        return answer;
    }

    @Override
    public String getDescription() {
        return "print all elements of the collection in string representation";
    }
}
