package Run;

import Commands.Ser_Command;
import org.postgresql.gss.GSSOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;

public class ReadTask implements Callable<Ser_Command> {
    private Ser_Command input;
    private ServerSocket server;
    private Socket client;
    private ObjectOutputStream answer_out;
    private ObjectInputStream obj_in;

    public ReadTask(ServerSocket server, Socket client, ObjectInputStream obj_in, ObjectOutputStream answer_out){
        this.server = server;
        this.client = client;
        this.answer_out = answer_out;
        this.obj_in = obj_in;
    }


    @Override
    public Ser_Command call() throws Exception {
        this.input = (Ser_Command) this.obj_in.readObject();
        answer_out.flush();
        System.out.println("Message accepted");
        return this.input;
    }
}
