package repositories;
import java.sql.*;
import java.util.Scanner;

import entities.User;

public class UserRepository {
    private Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public void addUser(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO users (username, age, balance) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setDouble(3, user.getBalance());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                System.out.println("User added successfully!");
            } else {
                System.out.println("Failed to add user. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCashToUserrep(double additionalCost, User user) {
        try {
            // Проверяем наличие пользователя с заданным именем в базе данных
            boolean userExists = false;
            try (PreparedStatement checkUserStatement = connection.prepareStatement(
                    "SELECT COUNT(*) FROM users WHERE username = ?")) {
                checkUserStatement.setString(1, user.getName());
                ResultSet resultSet = checkUserStatement.executeQuery();
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    if (count > 0) {
                        userExists = true;
                    }
                }
            }

            if (userExists) {
                try (PreparedStatement updateBalanceStatement = connection.prepareStatement(
                        "UPDATE users SET balance = balance + ? WHERE username = ?")) {
                    updateBalanceStatement.setDouble(1, additionalCost);
                    updateBalanceStatement.setString(2, user.getName());
                    int rowsAffected = updateBalanceStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Balance replenished");
                    } else {
                        System.out.println("Transaction error");
                    }
                }
            } else {
                System.out.println("User with name " + user.getName() + " does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllUsers() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                int age = resultSet.getInt("age");
                double balance = resultSet.getDouble("balance");

                System.out.println("ID: " + id + ", Username: " + username + ", Age: " + age + ", Balance: " + balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
