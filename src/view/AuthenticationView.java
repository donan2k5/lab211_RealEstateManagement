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

    public void displayRegisterSuccessfully() {
        System.out.println("User registered successfully");
    }
}
