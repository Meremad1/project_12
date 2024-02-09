import java.util.ArrayList;
import entities.*;
import controllers.*;

class CinemaSystem {
    private ArrayList<User> users;
    private ArrayList<Movie> movies;
    private ArrayList<Ticket> soldTickets;

    public CinemaSystem() {
        this.users = new ArrayList<>();
        this.movies = new ArrayList<>();
        this.soldTickets = new ArrayList<>();
//        initializeMovies();
    }

//    private void initializeMovies() {
//        Movie("Prisoners", "Drama", 18, "Denis Villeneuve", 500));
//        Movie("The Hunger Games", "Action", 12, "Gary Ross", 300));
//        Movie("Batman", "Action", 13, "Tim Burton", 600));
//        Movie("Inception", "Sci-Fi", 15, "Christopher Nolan", 400));
//        Movie("Joker", "Drama", 18, "Todd Phillips", 700));
//        Movie("Star Wars - Revenge of the Sith", "Sci-Fi", 12, "George Lucas", 1000));
//        Movie("I Want to Eat Your Pancreas", "Animation", 15, "Shinichirô Ushijima", 200));
//        Movie("Kung Fu Panda", "Animation", 7, "Mark Osborne, John Stevenson", 0));
//    }

    public void addUser(User user) {

        users.add(user);
        System.out.println("User added successfully.");
    }

    public void addCashToUser(String userName, int amount) {
        User user = getUserByName(userName);
        if (user != null) {
            user.setBalance(user.getBalance() + amount);
            System.out.println("Cash added!\nNew balance: $" + user.getBalance());
        } else {
            System.out.println("Invalid user name.");
        }
    }

    public void showAllMovies() {
        if (movies.isEmpty()) {
            System.out.println("No movies available.");
        } else {
            System.out.println("Available Movies:");
            for (Movie movie : movies) {
                System.out.println(movie.getMovieNames());
            }
        }
    }

    public void displayMoviesWithDetails() {
        if (movies.isEmpty()) {
            System.out.println("No movies available.");
        } else {
            System.out.println("All Movies with Details:");
            for (Movie movie : movies) {
                System.out.println(movie.getMovieInfo());
            }
        }
    }

    public void BuyTicket(String userName, String movieName) {
        User user = getUserByName(userName);

        if (user != null) {
            Movie selectedMovie = getMovieByName(movieName);

            if (selectedMovie != null && isValidMovieName(movieName)) {
                if (user.getAge() >= selectedMovie.getAgeRestriction()) {
                    double discount = user.getDiscount();
                    int movieCost = selectedMovie.getCost();
                    int discountedAmount = (int) (movieCost * (1 - discount));

                    if (user.getBalance() >= discountedAmount) {
                        user.setBalance(user.getBalance() - discountedAmount);
                        user.addToOrderHistory(new Ticket(selectedMovie.getMovieName(), discountedAmount));
                        System.out.println("Movie's ticket added to the user's basket successfully! Remaining balance: $" + user.getBalance());
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
    }
    public void CancelTicket(String userName, String movieName) {
        User user = getUserByName(userName);
        if (user != null) {
            Movie selectedMovie = getMovieByName(movieName);
            if (selectedMovie != null) {
                Ticket canceledTicket = user.getOrderHistory().stream()
                        .filter(ticket -> ticket.getMovieName().equalsIgnoreCase(movieName))
                        .findFirst()
                        .orElse(null);

                if (canceledTicket != null) {
                    user.getOrderHistory().remove(canceledTicket);
                    user.setBalance(user.getBalance() + canceledTicket.getPrice());
                    System.out.println("Ticket canceled and Cash refunded.\nNew balance: $" + user.getBalance());
                } else {
                    System.out.println("Ticket not found in the user's basket.");
                }
            } else {
                System.out.println("Invalid movie name.");
            }
        } else {
            System.out.println("Invalid user name.");
        }
    }

    public void displayUserMovies(String userName) {
        User user = getUserByName(userName);
        if (user != null) {
            if (!user.getOrderHistory().isEmpty()) {
                System.out.println("User's Tickets:");
                for (Ticket ticket : user.getOrderHistory()) {
                    System.out.println(ticket.getMovieName());
                }
            } else {
                System.out.println("User has no tickets.");
            }
        } else {
            System.out.println("Invalid user name.");
        }
    }

    public void upgradeUserType(String userName) {
        User user = getUserByName(userName);
        if (user != null) {
            user.upgradeUserType();
        } else {
            System.out.println("Invalid user name.");
        }
    }

    public void upgradeUserToDeveloper(String userName) {
        User user = getUserByName(userName);

        if (user != null && user instanceof PremiumUser) {
            PremiumUser premiumUser = (PremiumUser) user;

            if (premiumUser.getDiscount() == 1) {
                System.out.println("User is already a Developer User.");
            } else {
                int userIndex = users.indexOf(premiumUser);

                if (userIndex != -1) {

                    DeveloperUser developerUser = new DeveloperUser(premiumUser.getName(), premiumUser.getAge(), premiumUser.getBalance());
                    developerUser.getOrderHistory().addAll(premiumUser.getOrderHistory());
                    users.set(userIndex, developerUser);

                    System.out.println("User upgraded to Developer successfully!");
                } else {
                    System.out.println("Error upgrading user. User not found in the list.");
                }
            }
        } else {
            System.out.println("Invalid user or user type not eligible for upgrade.");
        }
    }







    private Movie getMovieByName(String movieName) {
        for (Movie movie : movies) {
            if (movie.getMovieName().equalsIgnoreCase(movieName)) {
                return movie;
            }
        }
        return null;
    }

    public User getUserByName(String userName) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(userName)) {
                return user;
            }
        }
        return null;
    }

    private boolean isValidMovieName(String movieName) {
        return movies.stream().anyMatch(movie -> movie.getMovieName().equalsIgnoreCase(movieName));
    }
}