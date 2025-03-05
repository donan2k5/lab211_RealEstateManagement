package view;

import model.User;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;

public class AuthenticationView {

    private final Validation validation = new Validation();
    private final UserRepository userRepository = new UserRepositoryImpl();

    public User getUserNamePassword() {
        String username = validation.getStringRegex("Enter username: ", "^[a-zA-Z0-9]+$", "Username exist/wrong pattern, enter again: ");
        String password = validation.getStringRegex("Enter password: ", "^\\S+$", "INVALID pattern, enter again: ");
        return new User.UserBuilder()
                .username(username)
                .password(password)
                .build();
    }

    public void displayLoginSucessfully() {
        System.out.println("Login successfully");
    }

    public void displayLoginError() {
        System.out.println("Wrong username or password");
    }

    public void displayLogout(String username) {
        System.out.println("Logging out " + username);
    }

    public User getUserDetails() {
        String username = "";
        while (true) {
            username = validation.getStringRegex(
                    "Enter username: ",
                    "^[a-zA-Z0-9]+$",
                    "Username exists/wrong pattern, enter again: "
            );
            if (userRepository.existsByUsername(username)) {
                System.out.println("Username already exists");
                continue;
            }
            break;
        }
        String password = validation.getStringRegex(
                "Enter password: ",
                "^\\S+$",
                "INVALID pattern, enter again: "
        );

        return new User.UserBuilder()
                .username(username)
                .password(password)
                .build();
    }

    public User updateUserDetails(User user) {
        System.out.println("Choose field to update:");
        System.out.println("1. Phone");
        System.out.println("2. Email");
        System.out.println("3. Gender");
        System.out.println("4. Last Name");
        System.out.println("5. First Name");
        int choice = validation.checkValidInt("Enter choice (1-5): ", "Invalid choice, try again", 1, 5);
        
        String newValue = validation.getStringRegex("Enter new value: ", "^[\\w\\s]+$", "Invalid input, try again");
        
        switch (choice) {
            case 1 -> user.setPhone(newValue);
            case 2 -> user.setEmail(newValue);
            case 3 -> user.setGender(newValue);
            case 4 -> user.setLastName(newValue);
            case 5 -> user.setFirstName(newValue);
        }
        
        return user;
    }

    public void displayRegisterSuccessfully() {
        System.out.println("User registered successfully");
    }
}
