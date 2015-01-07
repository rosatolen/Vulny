import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Vulny {
    public static void main(String[] args) {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Who are you looking for?");
                String input = scanner.nextLine();

                Connection connection = DriverManager.getConnection("jdbc:sqlite:/home/minerva/IdeaProjects/Vulny/data.db");
                UserRepository userRepository = new UserRepository(connection);
                String user = userRepository.getUser(input);

                System.out.println("You found...");
                System.out.println(user);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NoUserException e) {
                System.out.print("A user was not found with this name.\n");
            }
        }
    }
}