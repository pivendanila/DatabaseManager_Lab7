package Run;

import Commands.Ser_Command;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

public class ParseTask implements Callable<Ser_Command> {
    Ser_Command input;
    ObjectInputStream obj_in;
    ObjectOutputStream answer_out;
    Ser_Command answer;
    Socket client;

    public ParseTask(Ser_Command input, ObjectInputStream obj_in, ObjectOutputStream answer_out, Socket client){
        this.input = input;
        this.obj_in = obj_in;
        this.answer_out = answer_out;
        this.client = client;
    }
    @Override
    public Ser_Command call() throws Exception {
        CommandParser parser = new CommandParser();
        this.answer = parser.parse(this.input);
        System.out.println("Message parsed");
        return this.answer;
    }
}
