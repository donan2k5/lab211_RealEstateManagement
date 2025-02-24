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
