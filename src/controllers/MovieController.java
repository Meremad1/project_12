package controllers;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import entities.Movie;
import entities.User;
import repositories.MovieRepository;
import repositories.UserRepository;

public class MovieController {
    private ArrayList<Movie> movies;


    private static MovieRepository movieRepository;


    public void addMoviecon(Scanner scanner) {
        System.out.print("Enter moviename: ");
        String moviename = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter ageRestriction: ");
        int ageRestriction = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter cost: ");
        double cost = Double.parseDouble(scanner.nextLine());

        Movie newMovie = new Movie(moviename, author, genre, ageRestriction, cost);
        movieRepository.addMovie(newMovie);
    }

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public static void getAllMovie() {
        movieRepository.getAllMovie();
    }

}
