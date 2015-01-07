import java.sql.*;

public class UserRepository {
    private Connection databaseConnection;

    public UserRepository(Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public String getUser(String input) throws SQLException, NoUserException {
        String query = "select * from users where username = '" + input + "'";
        System.out.println("\n(DEBUG) Query is: " + query + "\n");
        Statement statement = databaseConnection.createStatement();
        ResultSet results = statement.executeQuery(query);
        return parseResults(results);
    }

    private String parseResults(ResultSet results) throws SQLException, NoUserException {
        String user = "";

        while (results.next()) {
            String username = (String) results.getObject("username");
            user = user.concat(username);
            user = user.concat("  ");
        }

        if (user.isEmpty()) {
            throw new NoUserException();
        }

        return user;
    }
}
