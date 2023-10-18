package Run;

public enum SQL_connect {
    DB_URL("DataBase url"),
    USER("Username"),
    PASS("Password");

    public final String title;

    SQL_connect(String title) {
        this.title = title;
    }
}