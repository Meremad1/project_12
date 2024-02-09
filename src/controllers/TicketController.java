package controllers;

import repositories.TicketRepository;

import java.util.Scanner;

public class TicketController {

    private TicketRepository ticketRepository;

    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Buy Ticket");
            System.out.println("2. Delete Ticket");
            System.out.println("3. Show All Tickets");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter user name: ");
                    String userName = scanner.nextLine();
                    System.out.print("Enter movie name: ");
                    String movieName = scanner.nextLine();
                    ticketRepository.buyTicket(userName, movieName);
                    break;
                case 2:
                    System.out.print("Enter movie name to delete ticket: ");
                    String deleteMovieName = scanner.nextLine();
                    ticketRepository.deleteTicket(deleteMovieName);
                    break;
                case 3:
                    ticketRepository.showAllTickets();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }
}
