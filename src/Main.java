import repositories.*;
import controllers.*;

import java.sql.*;
import java.util.Scanner;
import entities.User;
import entities.Movie;
import java.util.List;

public class Main {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "111";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            UserRepository userRepository = new UserRepository(connection);
            UserController userController = new UserController(userRepository);

            MovieRepository movieRepository = new MovieRepository(connection);
            MovieController movieController = new MovieController(movieRepository);

            TicketRepository ticketRepository = new TicketRepository(connection);
            TicketController ticketController = new TicketController(ticketRepository);

            createMovieTable(connection);
            createTicketTable(connection);
            createUsersTable(connection);

            while (true) {
                System.out.println("Choose available functions:");
                System.out.println("1) To add a new user.");
                System.out.println("2) To add cash to a user.");
                System.out.println("3) Movie Options");
                System.out.println("4) Ticket option");
                System.out.println("5) Exit");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1: {
                        userController.addUser(scanner);
                        break;
                    }
                    case 2: {
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        User user = new User(username, 0, 0, 0);
                        userController.addCashToUser(scanner, user);
                        break;
                    }
                    case 3: {
                        // Movie options
                        while (true) {
                            System.out.println("Choose movie options:");
                            System.out.println("1) To show all movies.");
                            System.out.println("2) To add a movie.");
                            System.out.println("3) To delete a movie.");
                            System.out.println("4) Exit movie options.");

                            int movieOption = scanner.nextInt();
                            scanner.nextLine(); // Consume newline character

                            switch (movieOption) {
                                case 1:
                                    {List<Movie> allMovies = movieRepository.getAllMovie();
                                    for (Movie movie : allMovies) {
                                        System.out.println(movie.getMovieInfo());
                                    }
                                    break;
                                }
                                case 2: {
                                    movieController.addMoviecon(scanner);
                                    break;
                                }
                                case 3: {
                                    System.out.print("Enter the name of the movie to delete: ");
                                    String movieNameToDelete = scanner.nextLine();
                                    movieRepository.deleteMovie(movieNameToDelete);
                                    break;
                                }
                                case 4: {

                                    break;
                                }
                                default: {
                                    System.out.println("Invalid option. Please try again.");
                                    break;
                                }
                            }
                        }
                    }
                    case 4: {
                        ticketController.run();
                        break;
                    }
                    case 5: {
                        return;
                    }
                    default: {
                        System.out.println("Invalid option. Please try again.");
                        break;
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createMovieTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS movie ("
                    + "id_movie SERIAL PRIMARY KEY,"
                    + "moviename VARCHAR(50) NOT NULL,"
                    + "author VARCHAR(50) NOT NULL,"
                    + "genre VARCHAR(50) NOT NULL,"
                    + "agerestriction INT NOT NULL,"
                    + "cost INT NOT NULL)";

            statement.executeUpdate(createTableQuery);
        }
    }

    private static void createTicketTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS ticket ("
                    + "id_ticket SERIAL PRIMARY KEY,"
                    + "moviename VARCHAR(50) NOT NULL,"
                    + "cost INT NOT NULL)";

            statement.executeUpdate(createTableQuery);
        }
    }

    private static void createUsersTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS users ("
                    + "id_user SERIAL PRIMARY KEY,"
                    + "username VARCHAR(50) NOT NULL,"
                    + "age INT NOT NULL,"
                    + "balance DOUBLE PRECISION NOT NULL)";

            statement.executeUpdate(createTableQuery);
        }
    }
}



