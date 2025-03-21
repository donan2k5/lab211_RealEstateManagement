package view;

import model.User;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;

public class AuthenticationView {

    private final Validation validation = new Validation();
    private final UserRepository userRepository = UserRepositoryImpl.getInstance();

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

    public String getGenDer() {
        int choice = 0;
        System.out.println("Select gender:");
        while (true) {
            System.out.println("1. Male");
            System.out.println("2. Female");
            choice = validation.getValidInteger("Enter your choice: ");
            switch (choice) {
                case 1 -> {
                    return "Male";
                }
                case 2 -> {
                    return "Female";
                }
                default ->
                    System.out.println("Invalid choice, pls try again.");
            }
        }
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
        String lastName = validation.getStringRegex(
                "Enter last name: ",
                "^\\S+$",
                "INVALID pattern, enter again: "
        );
        String firstName = validation.getStringRegex(
                "Enter first name: ",
                "^\\S+$",
                "INVALID pattern, enter again: "
        );
        String email = validation.getStringRegex(
                "Enter email name: ",
                "^^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
                "INVALID pattern, enter again: "
        );
        String phone = validation.getStringRegex("Enter phone number: ", "^0\\d{9}$", "Invalid input, try again!");
        String gender = this.getGenDer();
        return new User.UserBuilder()
                .username(username)
                .password(password)
                .lastName(lastName)
                .firstName(firstName)
                .email(email)
                .gender(gender)
                .roleId(1)
                .isDelete(0)
                .phone(phone)
                .build();
    }

    public void displayRegisterSuccessfully() {
        System.out.println("User registered successfully");
    }
}
