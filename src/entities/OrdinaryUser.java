package entities;

import java.util.Scanner;

public class OrdinaryUser extends User {
    public OrdinaryUser(String name, int age, int balance) {
        super(name, age, balance, 25);
    }

    @Override
    public void upgradeUserType() {
        System.out.println("Upgrade to Developer User for $1000? (yes/no)");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().toLowerCase();
        if (response.equals("yes")) {
            setBalance(getBalance() - 1000);
            setDiscount(0.25);
            System.out.println("Upgrade to Developer User successful! New balance: $" + getBalance());
        } else {
            System.out.println("Upgrade cancelled. Your account remains as a Premium User.");
        }
    }

}
