package Commands;

import Exceptions.NotEnoughArguments;
import Exceptions.WrongArgument;
import Run.CollectionManager;

/**
 * Class for displaying the History of commands.
 */

public class History implements Command{
    private String name = "history";
    private CollectionManager manager;
    public History(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Ser_Command execute() throws WrongArgument, NotEnoughArguments {
        Ser_Command answer = new Ser_Command("answer", manager.getHistory());
        manager.updateHistory(this.name);
        return answer;
    }

    @Override
    public String getDescription() {
        return "print the last 8 commands (without their arguments)";
    }
}
