package entities;
public class Movie {
    private static int movieIdCounter = 1;
    private int id;
    private String movieName;
    private String genre;
    private int ageRestriction;
    private String author;

    public int cost;

    public Movie(int id, String movieName, String author, String genre, int ageRestriction, int cost) {
        this.id = movieIdCounter++;
        this.movieName = movieName;
        this.genre = genre;
        this.ageRestriction = ageRestriction;
        this.author = author;
        this.cost = cost;
    }

    public Movie(String moviename, String author, String genre, int ageRestriction, double cost) {
    }


    public int getAgeRestriction() {
        return ageRestriction;
    }

    public String getMovieNames() {
        return id + ") " + movieName;
    }

    public String getMovieInfo() {
        return id + ") " + movieName + " (" + ageRestriction + "+) - " + genre + " by " + author + " - Cost: $" + cost;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getCost() {
        return cost;
    }

    public String getGenre() {
        return genre;
    }


    public String getAuthor() {
        return author;
    }
}
