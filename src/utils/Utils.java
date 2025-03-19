package utils;

import view.Validation;

public class Utils {

    public static final Validation validation = new Validation();

    public static boolean getAStringFormatYN(String msg, String error) {
        String check = validation.getStringRegex(msg, "^[YNyn]$", error);
        if (check.equals("Y")) {
            return true;
        }
        return false;
    }
}
