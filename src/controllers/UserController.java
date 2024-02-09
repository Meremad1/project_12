package controllers;
import entities.*;
import repositories.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();

        System.out.print("Enter balance: ");
        double balance = scanner.nextDouble();

        User newUser = new User(username, age, balance, 0);
//        newUser.setname(username);
//        newUser.setAge(age);
//        newUser.setBalance(balance);

        userRepository.addUser(newUser);
    }


    public double getUserInputPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите значение цены: ");
        return scanner.nextDouble();
    }
    public void addCashToUser(Scanner scanner, User user) {
        double additionalCash = getUserInputPrice();
        userRepository.addCashToUserrep(additionalCash, user);
    }


    public void getAllUsers() {
        userRepository.getAllUsers();
    }
}
