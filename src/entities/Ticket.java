package entities;
public class Ticket {
    private static int ticketIdCounter = 1;
    private int id;
    private String movieName;

    private int price;
    public Ticket(String movieName, int price) {
        this.id = ticketIdCounter++;
        this.movieName = movieName;
        this.price = price;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getPrice() {
        return price;
    }
}