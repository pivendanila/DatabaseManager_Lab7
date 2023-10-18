import Exceptions.NotEnoughArguments;
import Exceptions.WrongArgument;
import Run.CLI_Manager;
import Run.Communicator;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, WrongArgument, NotEnoughArguments, ClassNotFoundException {
        Communicator c = new Communicator("localhost", 1063);
        CLI_Manager cl = new CLI_Manager(c);
        cl.interactive_mod();
    }
}