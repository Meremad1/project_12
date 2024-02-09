package repositories;
import entities.Movie;
import entities.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository {
    private static Connection connection;

    public TicketRepository(Connection connection) {
        this.connection = connection;
    }

    public static void buyTicket(String userName, String movieName) {
        try {
            String getUserQuery = "SELECT * FROM users WHERE LOWER(username) = LOWER(?)";
            PreparedStatement getUserStatement = connection.prepareStatement(getUserQuery);
            getUserStatement.setString(1, userName);
            ResultSet userResult = getUserStatement.executeQuery();

            if (userResult.next()) {
                int userId = userResult.getInt("id");
                int userAge = userResult.getInt("age");
                double userBalance = userResult.getDouble("balance");

                String getMovieQuery = "SELECT * FROM movie WHERE LOWER(moviename) = LOWER(?)";
                PreparedStatement getMovieStatement = connection.prepareStatement(getMovieQuery);
                getMovieStatement.setString(1, movieName);
                ResultSet movieResult = getMovieStatement.executeQuery();

                if (movieResult.next()) {
                    int movieId = movieResult.getInt("id");
                    String movieGenre = movieResult.getString("genre");
                    int movieAgeRestriction = movieResult.getInt("agerestriction");
                    double movieCost = movieResult.getDouble("cost");

                    if (userAge >= movieAgeRestriction) {
                        if (userBalance >= movieCost) {
                            double newBalance = userBalance - movieCost;
                            String updateUserQuery = "UPDATE users SET balance = ? WHERE id = ?";
                            PreparedStatement updateUserStatement = connection.prepareStatement(updateUserQuery);
                            updateUserStatement.setDouble(1, newBalance);
                            updateUserStatement.setInt(2, userId);
                            updateUserStatement.executeUpdate();

                            String addTicketQuery = "INSERT INTO ticket (moviename, price) VALUES (?, ?)";
                            PreparedStatement addTicketStatement = connection.prepareStatement(addTicketQuery);
                            addTicketStatement.setString(1, movieName);
                            addTicketStatement.setDouble(2, movieCost);
                            addTicketStatement.executeUpdate();

                            System.out.println("Movie's ticket added to the user's basket successfully! Remaining balance: $" + newBalance);
                        } else {
                            System.out.println("Unable to buy the movie's ticket. Insufficient balance.");
                        }
                    } else {
                        System.out.println("Unable to buy the movie's ticket. Age restriction not met.");
                    }
                } else {
                    System.out.println("Invalid movie name.");
                }
            } else {
                System.out.println("Invalid user name.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public static void deleteTicket(String movieName) {
        String deleteQuery = "DELETE FROM ticket WHERE LOWER(moviename) = LOWER(?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, movieName);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Ticket for movie '" + movieName + "' deleted successfully.");
            } else {
                System.out.println("No ticket found for movie '" + movieName + "'.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showAllTickets() {
        String selectQuery = "SELECT * FROM ticket";
        List<String> tickets = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String movieName = resultSet.getString("moviename");
                double price = resultSet.getDouble("price");
                tickets.add("Movie Name: " + movieName + ", Price: " + price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("All Tickets:");
        for (String ticket : tickets) {
            System.out.println(ticket);
        }
    }
}

//try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
//        preparedStatement.setString(1, movieName);
//            preparedStatement.setDouble(2, price);
//            preparedStatement.executeUpdate();
//            System.out.println("Ticket bought successfully.");
//        } catch (SQLException e) {
//        e.printStackTrace();
//        }