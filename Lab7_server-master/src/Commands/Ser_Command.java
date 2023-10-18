package Commands;

import Collections.Route;

import java.io.Serializable;

public class Ser_Command implements Serializable {

    private String name = null;
    private int index;
    private Route route;
    private String argument = null;
    private String login = null;
    private String id = null;

    public Ser_Command(String name){
        this.name = name;
    }
    public Ser_Command(String name, int index){
        this.name = name;
        this.index = index;
    }
    public Ser_Command(String name, String argument){
        this.name = name;
        this.argument = argument;
    }
    public Ser_Command(String name, int index, Route route){
        this.name = name;
        this.index = index;
        this.route = route;
    }
    public Ser_Command(String name, String login, Route route){
        this.name = name;
        this.route = route;
        this.login = login;
    }
    public Ser_Command(String name, String login, Route route, String id){
        this.name = name;
        this.route = route;
        this.login = login;
        this.id = id;
    }


    public String getName() {
        return this.name;
    }
    public int getIndex() {
        return this.index;
    }
    public String getLogin() {
        return this.login;
    }
    public Route getRoute() {
        return this.route;
    }
    public String getId() {
        return this.id;
    }

    public String getArgument() {
        return this.argument;
    }
}
