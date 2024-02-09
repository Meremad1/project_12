package repositories;

import entities.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
    private Connection connection;

    public MovieRepository(Connection connection) {
        this.connection = connection;
    }

    public void addMovie(Movie newMovie) {
        try {
            String sql = "INSERT INTO movie (moviename, author, genre, agerestriction, cost) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, newMovie.getMovieName());
                preparedStatement.setString(2, newMovie.getAuthor());
                preparedStatement.setString(3, newMovie.getGenre());
                preparedStatement.setInt(4, newMovie.getAgeRestriction());
                preparedStatement.setInt(5, newMovie.getCost());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("The movie has been successfully added!");
                } else {
                    System.out.println("Could not add the movie. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Movie> getAllMovie() {
        List<Movie> movies = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM movie");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String movieName = resultSet.getString("moviename");
                String author = resultSet.getString("author");
                int ageRestriction = resultSet.getInt("agerestriction");
                String genre = resultSet.getString("genre");
                int cost = resultSet.getInt("cost");
                Movie movie = new Movie(id, movieName, author, genre, ageRestriction, cost);
                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }

    public void deleteMovie(String movieNameToDelete) {
        try {
            String deleteQuery = "DELETE FROM movie WHERE moviename = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, movieNameToDelete);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Movie '" + movieNameToDelete + "' has been deleted successfully.");
                } else {
                    System.out.println("No movie found with the name '" + movieNameToDelete + "'.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
