package Run;

import Collections.Location;
import Collections.Route;
import Commands.Ser_Command;
import Exceptions.NotEnoughArguments;
import Exceptions.WrongArgument;
import Exceptions.WrongField;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CLI_Manager {
    private final Communicator communicator;
    private String Login;

    public CLI_Manager(Communicator communicator){
        this.communicator = communicator;

    }

    private void Login() throws IOException, ClassNotFoundException, WrongArgument, NotEnoughArguments, InterruptedException {
        Ser_Command answer;
        while(true){
            Scanner in = new Scanner(System.in);
            System.out.println("Введите логин:");
            String login = in.nextLine();
            System.out.println("Введите пароль:");
            String password = in.nextLine();
            answer = new Ser_Command("logging", login+','+password);
            String state = this.communicator.Send(answer, "logging");
            this.communicator.Close();
            if(state.equals("OK")){
                System.out.println("Добро пожаловать!");
                Login = login;
                break;
            }
            else{
                System.out.println("Неверные данные. Попробуйте еще раз.");
            }
        }
    }
    private void Register() throws IOException, ClassNotFoundException, WrongArgument, NotEnoughArguments, InterruptedException {
        Ser_Command answer;
        while(true){
            Scanner in = new Scanner(System.in);
            System.out.println("Введите логин:");
            String login = in.nextLine();
            System.out.println("Введите пароль:");
            String password1 = in.nextLine();
            System.out.println("Введите пароль:");
            String password2 = in.nextLine();
            while(!password1.equals(password2)){
                System.out.println("Пароли не совпадают. Попробуйте еще раз:");
                password2 = in.nextLine();
            }
            answer = new Ser_Command("register", login+','+password2);
            String state = this.communicator.Send(answer, "registering");
            this.communicator.Close();
            Login();
            break;
        }
    }

    public void interactive_mod() throws IOException, InterruptedException, WrongArgument, NotEnoughArguments, ClassNotFoundException {
        System.out.println("Войдите в учетную запись или создайте ее (login/register)");
        Scanner in = new Scanner(System.in);
        String inp = in.nextLine();
        while (!inp.equals("login") && !inp.equals("register")){
            inp = in.nextLine();
        }
        while (true) {
            if (inp.equals("login")) {
                Login();
                break;
            } else if (inp.equals("register")) {
                Register();
                break;
            } else System.out.println("Wrong command.");
        }
        System.out.println("Interective Mod entered");
        Ser_Command serCommand = null;
        while (true) {
            System.out.println("\nEnter the command");
            serCommand = parseInput();
            if(serCommand != null && this.communicator != null) {
                this.communicator.Send(serCommand, Login);
            }
        }

    }
    private Ser_Command parseInput() throws IOException {
        Ser_Command serCommand = null;
        try {
            Scanner commandReader = new Scanner(System.in);
            String line = commandReader.nextLine();
            if(line.equals("help")){
                System.out.println("add: add a new element to the collection\n" +
                        "remove_key: remove the element at the given position in the collection (index)\n" +
                        "show: print all elements of the collection in string representation\n" +
                        "clear: clear collection\n" +
                        "history: print the last 8 commands (without their arguments)\n" +
                        "help: display help on available commands\n" +
                        "exit: terminate program");
            }
            if(line.equals("log_out")){
                System.out.println("You were logged out!");
                interactive_mod();
            }
            else if(line.equals("show")){
                serCommand = new Ser_Command(line);
            }
            else if(line.equals("add")){
                serCommand = new Ser_Command(line, Login, parseElement());
            }
            else if(line.equals("update_id")){
                System.out.println("Enter Route id.");
                int id;
                while (true) {
                    commandReader = new Scanner(System.in);
                    try {
                        id = Integer.parseInt(commandReader.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong format.");
                    }
                }
                serCommand = new Ser_Command("update_at", Login, parseElement(), String.valueOf(id));
            }
            else if(line.equals("clear")){
                serCommand = new Ser_Command(line);
            }
            else if(line.equals("exit")){
                System.exit(0);
            }
            else if(line.equals("remove_key")){
                System.out.println("Enter Route index.");
                String index;
                while (true) {
                    commandReader = new Scanner(System.in);
                    try {
                        index = commandReader.nextLine();
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong format.");
                    }
                }
                serCommand = new Ser_Command("remove_at", index);
            }
            else if(line.equals("history")){
                serCommand = new Ser_Command(line);
            }
            else{
                System.out.println("Wrong command. Use help.");
            }
        } catch (NoSuchElementException ignored){

        } catch (WrongArgument e) {
            throw new RuntimeException(e);
        } catch (NotEnoughArguments e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return serCommand;
    }

    public Route parseElement(){
        Route route = new Route();
        while (true) {
            Scanner commandReader = new Scanner(System.in);
            try {
                System.out.println("Enter a Route name");
                route.setName(commandReader.nextLine());
                break;
            } catch (WrongField e) {
                System.out.println("Wrong name. " + e.getMessage());
            }
        }
        while (true) {
            Scanner commandReader = new Scanner(System.in);
            try {
                System.out.println("Enter Coordinates");
                route.setCoordinates(commandReader.nextLine());
                break;

            }catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
                System.out.println("Wrong coordinates. " + e.getMessage());
            }
        }
        while (true) {
            Scanner commandReader = new Scanner(System.in);
            try {
                System.out.println("Enter a Point To Name and Coordinates");
                route.setTo(commandReader.nextLine());
                break;
                } catch (WrongField | NumberFormatException e) {
                System.out.println("Wrong Point. " + e.getMessage());
            }
        }

        while (true) {
            Scanner commandReader = new Scanner(System.in);
            try {
                System.out.println("Enter a Point From Name and Coordinates");
                route.setFrom(commandReader.nextLine());
                break;
            } catch (WrongField | NumberFormatException e) {
                System.out.println("Wrong Point. " + e.getMessage());
            }
        }
        while (true) {
            Scanner commandReader = new Scanner(System.in);
            try {
                System.out.println("Enter Route Distance");
                route.setDistance(Double.parseDouble(commandReader.nextLine()));
                break;
            } catch (WrongField | NumberFormatException e) {
                System.out.println("Wrong Point. " + e.getMessage());
            }
        }

        return route;
    }

}
