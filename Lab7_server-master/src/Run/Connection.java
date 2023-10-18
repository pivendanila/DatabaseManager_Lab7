package Run;

import Commands.*;
import Exceptions.NotEnoughArguments;
import Exceptions.WrongArgument;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.concurrent.*;

import static Run.Connection.ThreadManager.newRead;

public class Connection {
    private int port;
    Socket client;
    ServerSocket server = null;

    static class ThreadManager {
        static ForkJoinPool ReadPool = new ForkJoinPool(4);
        static ForkJoinPool ParsePool = new ForkJoinPool(4);
        static ExecutorService SendPool = Executors.newCachedThreadPool();

        public static void newRead(ServerSocket server) throws ExecutionException, InterruptedException, IOException {
            //
            Socket client = server.accept();
            ObjectInputStream obj_in = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream answer_out = new ObjectOutputStream(client.getOutputStream());
            Future<Ser_Command> future = ReadPool.submit(new ReadTask(server, client, obj_in, answer_out));
            Ser_Command input = future.get();
            newParse(input, obj_in, answer_out, client);
        }
        public static void newParse(Ser_Command input, ObjectInputStream obj_in, ObjectOutputStream answer_out, Socket client) throws ExecutionException, InterruptedException {
            //
            Future<Ser_Command> future = ParsePool.submit(new ParseTask(input, obj_in, answer_out, client));
            Ser_Command answer = future.get();
            newSend(answer, obj_in, answer_out, client);
        }
        public static void newSend(Ser_Command answer, ObjectInputStream obj_in, ObjectOutputStream answer_out, Socket client){
            //
            System.out.println(answer.getName());
            SendPool.submit(new SendTask(answer, obj_in, answer_out, client));
        }
    }

    public Connection(int port) throws IOException {
        this.port = port;
        this.server = new ServerSocket(this.port);

    }
    /*
    public void connect() throws IOException {
        this.client = this.server.accept();
        this.obj_in = new ObjectInputStream(client.getInputStream());
        this.answer_out = new ObjectOutputStream(client.getOutputStream());
    }
     */

    public void InputMode(CommandParser parser) throws WrongArgument, NotEnoughArguments, IOException, ClassNotFoundException, SQLException, NoSuchAlgorithmException {
        while(true) {
            try {
                Ser_Command input;
                Ser_Command answer;
                newRead(server); //create task for reading new client

                //input = parseInput();
                //answer = parser.parse(input);
                //System.out.println(answer.getArgument());
                //Send(answer);

                } catch (IOException | ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
        }

    }
    /*
    public Ser_Command parseInput() throws IOException, ClassNotFoundException {
        connect();
        return (Ser_Command) this.obj_in.readObject();
    }

    public void Send(Ser_Command answer) throws IOException {
        connect();
        answer_out.writeObject(answer);
        Close();
    }
    private void Close() throws IOException {
        this.obj_in.close();
        this.answer_out.close();
        this.client.close();
    }
    */
}