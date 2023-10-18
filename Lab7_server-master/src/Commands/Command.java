package Commands;

import Exceptions.NotEnoughArguments;
import Exceptions.WrongArgument;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Base interface of Commands
 */
public interface Command {
    String getName();
    Ser_Command execute() throws WrongArgument, NotEnoughArguments, IOException, SQLException;
    String getDescription();
}