import java.util.Scanner;
import static java.lang.Character.*;

public class PasswordValidation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Welcome!!\n");
        String password;
        int choice;
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        //If password does not meet criteria re-prompts user
        do {
            System.out.print("Enter password: ");
            password = sc.nextLine();
            if (val_char(password) || val_length(password)) {
                System.out.println("Invalid password");
            }
            if (val_length(password)) {
                System.out.println("Password must be at least 8 characters!");
            }
            if (val_char(password) ){
                System.out.println("Password must contain at least one digit, uppercase and special character!");
            }
        }
        while (val_char(password) || val_length(password));

        //Re-prompts user until choice is 2
        do {
            System.out.print("1. Display Password Strength\n");
            System.out.print("2. Exit\n");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            if (choice == 1) {
                System.out.println(val_strength(password));
            }
        } while (choice != 2);
        System.out.println("Exiting...");
    }

    //Method to determine pass length
    public static boolean val_length (String password) {
        //If password length is VALID return FALSE, if INVALID return TRUE
        if (password.length() >= 8) {
            return false;
        } return true;
    }

    //Method to determine criteria
    public static boolean val_char(String password) {
        boolean upper = false; boolean digit = false; boolean special = false;
        for (int i = 0; i < password.length(); i++) {
            if (isUpperCase(password.charAt(i))) {
                upper = true;
            } else if (isDigit(password.charAt(i))) {
                digit = true;
            } else if (isPunct(password.charAt(i))) {
                special = true;
            }
            //If one of the criteria is not met return TRUE, else FALSE
        } return !(upper && digit && special);
    }

    //Method to determine pass strength
    public static String val_strength (String pass) {
        if (pass.length() >= 10 && pass.length() <= 12) {
            return "Medium";
        }
        if (pass.length() > 12) {
            return "Strong";
        } return "Weak";
    }

    //Method to determine special characters
    public static boolean isPunct(char ch) {
        return !isLetterOrDigit(ch);
    }
}
