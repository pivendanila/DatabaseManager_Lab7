import Exceptions.NotEnoughArguments;
import Exceptions.WrongArgument;
import Run.CommandParser;
import Run.Connection;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Main {
    public static void main(String [] args) throws IOException, WrongArgument, NotEnoughArguments, ClassNotFoundException, SQLException, NoSuchAlgorithmException {
        Connection c = new Connection(1063);
        CommandParser parser = new CommandParser();

        c.InputMode(parser);
    }
}