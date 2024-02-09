package entities;

public class DeveloperUser extends User {
    public DeveloperUser(String name, int age, double balance) {
        super(name, age, balance, 1);
    }

    @Override
    public void upgradeUserType() {
        // Developers are already at the highest level
        System.out.println("You are already a Developer User with a 100% discount.");
    }
}
