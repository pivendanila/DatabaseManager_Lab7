package Run;

import Commands.*;
import Exceptions.*;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;

public class CommandParser {
    CollectionManager manager = null;
    Command cmd = null;
    Connection connection = null;
    Statement statement = null;

    static final String DB_URL = SQL_connect.DB_URL.title;
    static final String USER = SQL_connect.USER.title;
    static final String PASS = SQL_connect.PASS.title;
    static final String getinfo = "SELECT * FROM USERS";


    public CommandParser() {
        this.manager = new CollectionManager();
    }

    public Ser_Command parse(Ser_Command command) throws WrongArgument, NotEnoughArguments, IOException, SQLException, NoSuchAlgorithmException {
        Ser_Command answer = new Ser_Command("answer", "OK");
        String devider = "!";
        String user_login;
        switch (command.getName()) {
            case "logging": {
                String data = command.getArgument().split("!")[0];
                String login = data.split(",")[0];
                String password = data.split(",")[1];
                MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
                String password_hashed = Arrays.toString(sha1.digest(password.getBytes()));
                String valid = "NT";
                try {
                    connection = DriverManager
                            .getConnection(DB_URL, USER, PASS);
                    statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(getinfo);

                    while (rs.next()){
                        int state = 0;
                        String userlogin = rs.getString("LOGIN");
                        String userpassword = rs.getString("PASSWORD");
                        if(userlogin.equals(login)){
                            state++;
                        }
                        if(userpassword.equals(password_hashed)){
                            state++;
                        }
                        if(state==2){
                            valid = "OK";
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("Connection Failed");
                    e.printStackTrace();
                }
                answer = new Ser_Command("answer", valid);
                break;
            }
            case "register": {
                try {
                    Class.forName("org.postgresql.Driver");
                } catch (ClassNotFoundException e) {
                    System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
                    e.printStackTrace();
                }

                System.out.println("PostgreSQL JDBC Driver successfully connected");
                Connection connection = null;
                Statement statement = null;
                String data = command.getArgument().split("!")[0];
                String login = data.split(",")[0];
                boolean state = false;
                try {
                    connection = DriverManager
                            .getConnection(DB_URL, USER, PASS);
                    statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(getinfo);

                    while (rs.next()){
                        String userlogin = rs.getString("LOGIN");
                        if(userlogin.equals(login)){
                            state = true;
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("Connection Failed");
                    e.printStackTrace();
                }
                if (!state){
                    String insert = "INSERT INTO USERS"
                            + "(LOGIN, PASSWORD) " + "VALUES";


                    String password = data.split(",")[1];
                    MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
                    String password_hashed = Arrays.toString(sha1.digest(password.getBytes()));

                    insert += "('" + login + "', '" + password_hashed + "')";

                    try {
                        connection = DriverManager
                                .getConnection(DB_URL, USER, PASS);
                        statement = connection.createStatement();
                        statement.executeUpdate(insert);
                    } catch (SQLException e) {
                        System.out.println("Connection Failed");
                        e.printStackTrace();
                    }
                    answer = new Ser_Command("answer", "OK");
                    break;
                }
                else answer = new Ser_Command("answer", "The same login exists!");
                break;
            }
            case "add": {
                //System.out.println(command.getRoute());
                this.cmd = new Add(this.manager, command.getRoute(), command.getLogin());
                answer = cmd.execute();
                break;
            }
            case "clear": {
                this.cmd = new Clear(this.manager);
                answer = cmd.execute();
                break;
            }
            case "show": {
                this.cmd = new Show(this.manager);
                answer = cmd.execute();
                break;
            }
            case "remove_at":
                user_login = command.getLogin();
                this.cmd = new RemoveById(this.manager, command.getArgument().split(devider)[0], command.getArgument().split(devider)[1]);
                try {
                    answer = cmd.execute();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("wrong index");
                    answer = new Ser_Command("error", "wrong index");
                } catch (NullPointerException e) {
                    System.out.println(' ');
                }
                break;

            case "update_at":
                this.cmd = new RemoveById(this.manager, command.getArgument().split(devider)[0], command.getArgument().split(devider)[1]);
                answer = cmd.execute();
                user_login = command.getLogin();
                String id = command.getId();
                this.cmd = new Add(this.manager, command.getRoute(), id, user_login);
                answer = cmd.execute();
                break;
            case "history": {
                this.cmd = new History(this.manager);
                answer = cmd.execute();
                break;
            }
        }
        return answer;
    }

}