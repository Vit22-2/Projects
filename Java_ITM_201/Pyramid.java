import java.util.Scanner;

public class Pyramid {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size: ");
        int size = sc.nextInt();
        for (int i = 0; i < size; i++) {
            System.out.println();
            for (int j = 1; j < size - i; j++) {
                System.out.print("#");
            }
            for (int k = 0; k < i + i + 1; k++) {
                System.out.print("*");
            }
        }
    }
}