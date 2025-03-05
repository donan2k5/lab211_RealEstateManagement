package view;

import java.util.Scanner;

public class Validation {

    private final Scanner sc = new Scanner(System.in);

    public String getStringRegex(String msg, String regex, String enterAgain) {
        System.out.print(msg);
        while (true) {
            try {
                String string = sc.nextLine().trim();
                if (string.isBlank() || !string.matches(regex)) {
                    throw new NumberFormatException();
                }
                return string;
            } catch (NumberFormatException e) {
                System.out.print(enterAgain);
            }
        }
    }
    

    public int getValidInteger(String message) {
        int number;
        while (true) {
            try {
                System.out.print(message);
                String input = sc.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Input cannot be empty. Please enter a number.");
                    continue;
                }

                number = Integer.parseInt(input);
                if (number <= 0) {
                    System.out.println("Please enter a positive integer.");
                    continue;
                }

                return number;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    public int checkValidInt(String me, String error, Integer min, Integer max) {
        while (true) {
            System.out.print(me);
            try {
                int temp = Integer.parseInt(sc.nextLine().trim());
                if (temp < min || temp > max) {
                    throw new Exception();
                }
                return temp;
            } catch (Exception e) {
                System.out.println("INVALID");
            }
        }
    }

    public double getValidDouble(String msg) {
        double number = 0;
        boolean isValid = false;

        while (!isValid) {
            System.out.print(msg);
            String input = sc.nextLine();

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter a valid number.");
                continue;
            }

            try {
                number = Double.parseDouble(input);
                if (number > 0) {
                    number = Math.round(number * 100.0) / 100.0;
                    isValid = true;
                } else {
                    System.out.println("The number must be a positive number. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid real number.");
            }
        }
        return number;
    }

    public double checkValidDouble(String me, String error, double min, double max) {
        while (true) {
            System.out.print(me);
            try {
                double temp = Double.parseDouble(sc.nextLine().trim());
                if (temp < min || temp > max) {
                    throw new Exception();
                }
                return temp;
            } catch (Exception e) {
                System.out.println("INVALID");
            }
        }
    }

}
