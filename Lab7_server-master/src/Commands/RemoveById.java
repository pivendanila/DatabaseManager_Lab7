package Commands;

import Exceptions.NotEnoughArguments;
import Exceptions.WrongArgument;
import Run.CollectionManager;
import java.sql.*;

/**
 * Class for Removing element by Id.
 */
public class RemoveById implements Command{
    private String name = "remove_by_id";
    private CollectionManager manager;
    private String id;
    private String login;
    static final String DB_URL = "jdbc:postgresql://pg/studs";
    static final String USER = "s368529";
    static final String PASS = "KGi8RiF9B6KM6BVu";



    public RemoveById(CollectionManager manager, String id, String login) {
        this.manager=manager;
        this.id = id;
        this.login = login;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Ser_Command execute() throws WrongArgument, NotEnoughArguments {
        Ser_Command answer = new Ser_Command("error", "You can't edit foreign elements");
        int index = Integer.parseInt(this.id);
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection = null;
        Statement statement = null;
        String insert = "SELECT INDEX FROM ROUTES WHERE OWNER='"+this.login+"'";
        boolean state = false;
        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(insert);
            while (rs.next()){
                if(rs.getInt("INDEX") == index){
                    state=true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
        if(state) {
            manager.updateHistory(getName());
            manager.remove_at_index(this.id);
            answer = new Ser_Command("answer", "removed");
        }
        return answer;
    }

    @Override
    public String getDescription() {
        return "remove element from collection by its id";
    }
}
