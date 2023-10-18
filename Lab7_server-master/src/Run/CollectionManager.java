package Run;

import Collections.Location;
import Collections.Route;

import java.sql.*;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.RecursiveTask;

/**
 * Class of Manager working with collection of Space Marines.
 */
public class CollectionManager {

    static final String DB_URL = SQL_connect.DB_URL.title;
    static final String USER = SQL_connect.USER.title;
    static final String PASS = SQL_connect.PASS.title;
    final LinkedList<Route> routes;
    final private String type;
    final private java.time.ZonedDateTime date;
    private int number_of_routes = -1;
    private String history = "";


    public CollectionManager() {
        this.routes = new LinkedList<>();
        this.type = String.valueOf(routes.getClass());
        this.date = java.time.ZonedDateTime.now();
    }


    /**
     * Method of updating History of commands.
     */
    public void updateHistory(String name){
        this.history += name+" ";
    }
    /**
     * getter for History
     */
    public String getHistory() {
        return history;
    }

    /**
     * Method of clearing the collection.
     */
    public void clear() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }
        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection = null;
        Statement statement = null;
        String truncate = "TRUNCATE ROUTES";
        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            statement.executeUpdate(truncate);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
    }

    /**
     * Method of removing element by Index.
     */
    public void remove_at_index(String index) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }
        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection = null;
        Statement statement = null;
        String delete = "DELETE FROM ROUTES WHERE INDEX="+index+";";
        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            statement.executeUpdate(delete);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
    }


    /**
     * Method of inserting element by index to the collection.
     */
    public void add(Route route, String login) throws SQLException {
        System.out.println(route);
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }
        Connection connection = null;
        Statement statement = null;
        String count = "SELECT * FROM ROUTES";
        int i = 0;
        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            ResultSet cnt = statement.executeQuery(count);
            while (cnt.next()){
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
        System.out.println("PostgreSQL JDBC Driver successfully connected");

        String insert = "INSERT INTO ROUTES"
                + "(ID, INDEX, NAME, COORDINATES, DATE, FROM_POINT, TO_POINT, DISTANCE, OWNER) " + "VALUES"+
                "("+"nextval('my_sequence')"+","+i+",'"+route.getName()+"','"+route.getCoordinates()+"','"
                +route.getCreationDate()+"','" +route.getTo()+"','"+route.getFrom()
                +"',"+route.getDistance()+",'"+login+"')";
        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            statement.executeUpdate(insert);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
    }
    public void add(Route route, String id, String login) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }
        Connection connection = null;
        Statement statement = null;
        String count = "SELECT * FROM ROUTES";
        int i = 0;
        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            ResultSet cnt = statement.executeQuery(count);
            while (cnt.next()){
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
        System.out.println("PostgreSQL JDBC Driver successfully connected");

        String insert = "INSERT INTO ROUTES"
                + "(ID, INDEX, NAME, COORDINATES, DATE, FROM_POINT, TO_POINT, DISTANCE, OWNER) " + "VALUES"+
                "("+"nextval('my_sequence')"+","+i+",'"+route.getName()+"','"+route.getCoordinates()+"','"
                +route.getCreationDate()+"','" +route.getTo()+"','"+route.getFrom()
                +"',"+route.getDistance()+",'"+login+"')";
        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            statement.executeUpdate(insert);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
    }

    /**
     * Method of displaying content of the collection.
     */
    public String show() {
        int id = 0;
        int index = 0;
        String name;
        String coordinates;
        String creationdate;
        String to;
        String from;
        double distance;
        String owner;


        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection = null;
        Statement statement = null;
        String getinfo = "SELECT * FROM ROUTES";
        String result = "";
        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getinfo);
            while (rs.next()){
                id = rs.getInt("ID");
                index = rs.getInt("INDEX");
                name = rs.getString("NAME");
                to = rs.getString("TO_POINT");
                from = rs.getString("FROM_POINT");
                coordinates = rs.getString("COORDINATES");
                creationdate = rs.getString("DATE");
                distance = rs.getDouble("DISTANCE");
                owner = rs.getString("OWNER");
                result += "Route ID: " + id + "\nRoute #" + index + "\nName: " + name + "\n" + "Coordinates: " + coordinates + "\n"
                        + "To: " + to + "\n" + "From: " + from + "\n" + "Distance: " + distance + "\n"
                        + "CreationDate " + creationdate + "\n" + "Owner: " + owner + "\n" + "\n";

            }
            if(result.replaceAll("\n","").length()==0){
                result = "Collection is empty";
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
        return result;
    }

}