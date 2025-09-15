import java.util.Scanner;

public class Diamond {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size: ");
        int size = sc.nextInt();
        for (int i = 0; i < size; i++) {
            System.out.println();
            for (int j = 1; j < size - i; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < i + i + 1; k++) {
                System.out.print("*");
            }
        }
        for (int i = 1; i < size ; i++) {
            System.out.println();
            for (int j = 1; j < i + 1; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < 2 * (size - i - 1) + 1 ; k++) {
                System.out.print("*");
            }
        }

    }
}