package entities;
import java.util.ArrayList;
import java.util.Scanner;
public class User {
    private String name;
    private int age;
    private double balance;

    private ArrayList<Ticket> orderHistory;
    private double discount;

    public User(String name, int age, double balance, double discount) {
        this.name = name;
        this.age = age;
        this.balance = balance;
        this.orderHistory = new ArrayList<Ticket>();
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public double getBalance() {
        return balance;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<Ticket> getOrderHistory() {
        return orderHistory;
    }

    public void addToOrderHistory(Ticket ticket) {
        orderHistory.add(ticket);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void upgradeUserType() {
        System.out.println("User type remains unchanged.");
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}


