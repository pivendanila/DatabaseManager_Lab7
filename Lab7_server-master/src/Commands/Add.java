package Commands;


import Collections.Route;
import Exceptions.NotEnoughArguments;
import Exceptions.WrongArgument;
import Run.CollectionManager;
import java.sql.SQLException;

/**
 * Class for Adding new elemnts.
 */
public class Add implements Command{
    private String name = "add";
    private final CollectionManager manager;
    private Route route = null;
    private String login;
    private String id = null;
    Ser_Command answer = null;
    public Add(CollectionManager manager, Route route, String login) {
        this.manager = manager;
        this.route = route;
        this.login = login;
    }
    public Add(CollectionManager manager, Route route, String id, String login) {
        this.manager = manager;
        this.route = route;
        this.login = login;
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }


    @Override
    public Ser_Command execute() throws WrongArgument, NotEnoughArguments, SQLException {
        if(this.id == null) {
            this.manager.add(this.route, this.login);
        }
        else {
            this.manager.add(this.route, this.id, this.login);
        }
        answer = new Ser_Command("answer", "added");
        return answer;

    }

    @Override
    public String getDescription() {
        return "add a new element to the collection";
    }
}
