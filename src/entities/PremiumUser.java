package entities;

import java.util.Scanner;

public class PremiumUser extends User {
    public PremiumUser(String name, int age, int balance) {
        super(name, age, balance, 0.25);
    }

    @Override
    public void upgradeUserType() {
        System.out.println("Upgrade to Developer User for $1000? (yes/no)");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().toLowerCase();
        if (response.equals("yes")) {
            setBalance (getBalance() - 1000);
            System.out.println("Upgrade to Developer User successful! New balance: $" + getBalance());
        } else {
            System.out.println("Upgrade cancelled. Your account remains as a Premium User.");
        }
    }
}
