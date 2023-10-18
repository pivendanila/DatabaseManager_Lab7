package Run;

import Commands.Ser_Command;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

public class Communicator {

    Socket clientSocket;
    ObjectOutputStream obj_out;
    ObjectInputStream answer_in;
    private final String address;
    private final int port;

    public Communicator(String address, int port) throws IOException {
        this.address = address;
        this.port = port;
    }

    public void connect() throws IOException {
            this.clientSocket = new Socket(this.address, this.port);
            this.obj_out = new ObjectOutputStream(clientSocket.getOutputStream());
            this.answer_in = new ObjectInputStream(clientSocket.getInputStream());
    }

    public String Send(Ser_Command serCommand, String login) throws IOException, InterruptedException {
        String answer = "something wrong";
        Ser_Command request = serCommand;
        try {
            if(serCommand.getRoute() == null) {
                String argument = serCommand.getArgument() + '!' + login;
                request = new Ser_Command(serCommand.getName(), argument);
            }
            this.connect();
            this.obj_out.writeObject(request);
            obj_out.flush();
            answer = Read();
            System.out.println(answer);
            Close();

        } catch (ConnectException e) {
            System.out.println("Сервер не запущен");
        }
        return answer;
    }
    public String Read(){
        Ser_Command answer = new Ser_Command("error", "something wrong");
        try {
            answer = (Ser_Command) this.answer_in.readObject();
            System.out.println();
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return answer.getArgument();
    }
    public void Close() throws IOException {
        this.obj_out.close();
        this.answer_in.close();
        this.clientSocket.close();
    }
}
