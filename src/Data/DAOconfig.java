package Data;

public class DAOconfig {
    private static final String USERNAME = "jfc"; //TODO: alterar
    private static final String PASSWORD = "jfc"; //TODO: alterar
    private static final String DATABASE = "DSS_Project";
    private static final String DRIVER = "jdbc:mysql";
    static final String URL = DRIVER+"://localhost:3306/"+DATABASE;
    static final String CREDENTIALS = "?user="+USERNAME+"&password="+PASSWORD;
}
