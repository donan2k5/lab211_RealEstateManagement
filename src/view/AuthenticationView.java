/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.User;

/**
 *
 * @author PC
 */
public class AuthenticationView {

    private final Validation validation = new Validation();

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
        String username = validation.getStringRegex(
                "Enter username: ",
                "^[a-zA-Z0-9]+$",
                "Username exists/wrong pattern, enter again: "
        );

        String password = validation.getStringRegex(
                "Enter password: ",
                "^\\S+$",
                "INVALID pattern, enter again: "
        );

        String lastName = validation.getStringRegex(
                "Enter last name: ",
                "^[A-Z][a-zA-Z]+$",
                "Invalid last name (Must start with uppercase and contain only letters), enter again: "
        );

        String firstName = validation.getStringRegex(
                "Enter first name: ",
                "^[A-Z][a-zA-Z]+$",
                "Invalid first name (Must start with uppercase and contain only letters), enter again: "
        );

        String phone = validation.getStringRegex(
                "Enter phone number: ",
                "^(0[1-9][0-9]{8})$",
                "Invalid phone number (Must start with 0 and have exactly 10 digits), enter again: "
        );

        String email = validation.getStringRegex(
                "Enter email: ",
                "^[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*\\.[a-zA-Z]{2,}$",
                "Invalid email format, enter again: "
        );

        String gender = validation.getStringRegex(
                "Enter gender (Male/Female/Other): ",
                "^(Male|Female|Other)$",
                "Invalid gender (Only 'Male', 'Female', or 'Other' allowed), enter again: "
        );
        return new User.UserBuilder()
                .username(username)
                .password(password)
                .lastName(lastName)
                .firstName(firstName)
                .phone(phone)
                .email(email)
                .gender(gender)
                .build();

    }

    public void displayRegisterSuccessfully() {
        System.out.println("User registered successfully");
    }
        
}
