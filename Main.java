import repositories.*;
import controllers.*;

import java.sql.*;
import java.util.Scanner;
import entities.User;

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
                                case 1: {

                                    break;
                                }
                                case 2: {
                                    movieController.addMovie(scanner);
                                    break;
                                }
                                case 3: {
//
//                                    break;
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
                        TicketController.run();
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







//while (true) {
//        System.out.println("Choose movie options:");
//                            System.out.println("1) To buy Ticket.");
//                            System.out.println("2) To add a movie.");
//                            System.out.println("3) To display a user's tickets list.");
//                            System.out.println("4) Exit ticket options.");
//
//int ticketOption = scanner.nextInt();
//                            scanner.nextLine();
//
//                            switch (ticketOption) {
//        case 1: {
//
//        break;
//        }
//        case 2: {
////
////                                    break;
//        }
//        case 3: {
////
////                                    break;
//        }
//        case 4: {
////
////                                    break;
//        }
//default: {
//        System.out.println("Invalid option. Please try again.");
//                                    break;
//                                            }
//                                            }
//                                            }


//try {
//whileLoop : while (true) {
//        System.out.println("Choose available functions:");
//                System.out.println("1) To show all Movie");
//                System.out.println("2) To add а Movie");
//                System.out.println("3) To delete а Movie");
//                System.out.println("4) Exit");
//            }
//int choice = scanner.nextInt();
//            scanner.nextLine();
//            switch (choice) {
//        case 1: {
//        userController.addUser(scanner);
//                    break;
//                            }
//                            case 2: {
//                            userController.getUserInputPrice();
//                    break;
//                            }
//                            case 3: {
//
//                            }
//                            case 4: {
//
//                            }
//                            }
//                            }
//
//                            } catch (ClassNotFoundException | SQLException e) {
//        e.printStackTrace();
//    }


//public class Main {
//    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/IT-2307";
//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "111";
//
//    public static void main(String[] args) {
//        try {
//            Class.forName("org.postgresql.Driver");
//
//            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
//
//            createUsersTable(connection);
//            createMovieTable(connection);
//
//            UserRepository userRepository = new UserRepository(connection);
//            UserController userController = new UserController(userRepository);
//            runUserManagementApp(userController);
//
//            MovieRepository movieRepository = new MovieRepository(connection);
//            MovieController MovieController = new MovieController(movieRepository);
//            runUserManagementApp(userController);
//
//            connection.close();
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    CinemaSystem cinemaSystem = new CinemaSystem();
//
//    private static void createUsersTable(Connection connection) throws SQLException {
//        try (Statement statement = connection.createStatement()) {
//            String createTableQuery = "CREATE TABLE IF NOT EXISTS users ("
//                    + "id SERIAL PRIMARY KEY,"
//                    + "username VARCHAR(50) NOT NULL,"
//                    + "age INT NOT NULL,"
//                    + "balance DOUBLE PRECISION NOT NULL)";
//
//            statement.executeUpdate(createTableQuery);
//        }
//    }
//    private static void createMovieTable(Connection connection) throws SQLException {
//        try (Statement statement = connection.createStatement()) {
//            String createTableQuery = "CREATE TABLE IF NOT EXISTS movie ("
//                    + "id SERIAL PRIMARY KEY,"
//                    + "moviename VARCHAR(50) NOT NULL,"
//                    + "author VARCHAR(50) NOT NULL,"
//                    + "genre VARCHAR(50) NOT NULL,"
//                    + "agerestriction INT NOT NULL,"
//                    + "cost INT NOT NULL,";
//
//            statement.executeUpdate(createTableQuery);
//        }
//    }
//
//    private static void runUserManagementApp(UserController userController) {
//        CinemaSystem cinemaSystem = new CinemaSystem();
//        try (Scanner scanner = new Scanner(System.in)) {
//            while (true) {
//                System.out.println("Choose available functions:");
//                System.out.println("1) To add a new user.");
//                System.out.println("2) To add cash to a user.");
//                System.out.println("3) To show all available movies.");
//                System.out.println("6) To delete a Movie");
//                System.out.println("7) To add а Movie");
//                System.out.println("8) To display a user's tickets list.");
//                System.out.println("7) To upgrade to Premium User.");
//
//                try {
//                    int choice = Integer.parseInt(scanner.nextLine());
//                    switch (choice) {
//                        case 1:
//                            userController.addUser(scanner);
//                            break;
//                        case 2:
//                            System.out.print("Enter user name: ");
//                            String cashUserName = scanner.nextLine();
//                            System.out.print("Enter amount to add: ");
//                            int amount = Integer.parseInt(scanner.nextLine());
//                            cinemaSystem.addCashToUser(cashUserName, amount);
//                            break;
//                        case 3:
//                            cinemaSystem.showAllMovies();
//                            break;
//                        case 4:
//                            System.out.print("Enter user name: ");
//                            String movieUserName = scanner.nextLine();
//                            System.out.print("Enter movie name: ");
//                            String movieName5 = scanner.nextLine();
//                            cinemaSystem.BuyTicket(movieUserName, movieName5);
//                            break;
//                        case 5:
//                            System.out.print("Enter user name: ");
//                            String movieUserName6 = scanner.nextLine();
//                            System.out.print("Enter movie name: ");
//                            String movieName6_1 = scanner.nextLine();
//                            cinemaSystem.CancelTicket(movieUserName6, movieName6_1);
//                            break;
//                        case 6:
//                            TicketController.run();
//                            break;
//                        case 7:
//                            MovieController.getAllMovie();
//                            break;
//                        case 420:
//                            System.out.print("Enter user name: ");
//                            String upgradeUserName420 = scanner.nextLine();
//                            cinemaSystem.upgradeUserToDeveloper(upgradeUserName420);
//                            break;
//                        default:
//                            System.out.println("Invalid choice. Please enter a valid option from 1 to 8.");
//                            break;
//                    }
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid input. Please enter a valid number.");
//                    scanner.nextLine();
//                }
//            }
//        }
//    }
//}

