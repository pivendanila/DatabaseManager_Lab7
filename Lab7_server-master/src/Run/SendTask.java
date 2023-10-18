package Run;

import Commands.Ser_Command;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SendTask implements Runnable {
    private Ser_Command answer;
    private ObjectOutputStream answer_out;
    private ObjectInputStream obj_in;
    Socket client;


    public SendTask(Ser_Command answer, ObjectInputStream obj_in, ObjectOutputStream answer_out, Socket client){
        this.answer = answer;
        this.obj_in = obj_in;
        this.answer_out = answer_out;
        this.client = client;
    }
    private void close() throws IOException {
        this.obj_in.close();
        this.answer_out.close();
        this.client.close();
    }

    @Override
    public void run() {
        try {
            System.out.println("Task added");
            answer_out.writeObject(answer);
            close();
            System.out.println("Message sended");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
