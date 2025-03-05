/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import view.Validation;

/**
 *
 * @author PC
 */
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
