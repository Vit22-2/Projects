import java.util.Scanner;
public class StudentInformationPortal {
    //Initialize variables
    private static final Scanner sc = new Scanner(System.in);
    private static final int max_subjects = 5;
    private static final int max_students = 5;
    private static final int[] id = new int[max_students];
    private static final int[][] sub_marks = new int[max_students][max_subjects];
    private static final int[] total = new int[max_students];
    private static final String[] name = new String[max_students];
    private static final double[] avg = new double[max_students];
    private static final char[] grade = new char[max_students];
    private static int count = 0;

    public static void main(String[] args) {
        int choice;
        do {
            System.out.print("Welcome to the Student Information Portal!\n");
            System.out.print("1. Add Student Record\n2. Display All Records\n3. Search Student Record\n4. Show Top Performer\n5. Exit\n");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    add_records();
                    break;
                case 2:
                    if (count == 0) {
                        System.out.print("Student Record Empty!\n");
                        break; }
                    display_all_records();
                    break;
                case 3:
                    System.out.print("Enter Student Name: ");
                    sc.nextLine();
                    String search_name = sc.nextLine();
                    if (count == 0) {
                    System.out.print("Student Record Empty!\n");
                    break; }
                    search_records(search_name);
                    break;
                case 4:
                    if (count == 0) {
                        System.out.print("Student Record Empty!\n");
                        break; }
                    top_performer();
                    break;
                case 5:
                    System.exit(0);
            }
        } while (choice != 0);
        System.exit(0); }

    public static void add_records() {
        //While id[count] empty keep prompting
        do {
            System.out.print("Enter Student ID: ");
            int temp = sc.nextInt();
            sc.nextLine();
            //If ID unique, assign to id[count]
            if (id_checker(temp, count, id)) {
                id[count] = temp;
            } else {
                System.out.print("ID Taken!\n");
            }
        } while (id[count] == 0);
        //Assign inputs to name and sub_marks
        System.out.print("Enter Student Name: ");
        name[count] = sc.nextLine();
        for (int i = 0; i < max_subjects; i++) {
            System.out.printf("Enter Subject %d: ", i + 1);
            sub_marks[count][i] = sc.nextInt();
            total[count] += sub_marks[count][i];
            avg[count] = (float) (total[count]) / (float) (sub_marks[i].length);
            grade[count] = assign_grade(avg[count]);
        } count++; //Increment count for each student
    }

    public static void display_all_records() {
        //Print all student info
        print_header();
        for (int i = 0; i < count; i++) {
            print_value(i); }
    }

    public static void search_records(String search) {
        //Compare search_name to name[]
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (name[i].equalsIgnoreCase(search)) {
                if (!found) {
                    print_header();
                } print_value(i);
                found = true;
            } else { System.out.print("Invalid Student Name\n"); }
        }
    }

    public static void top_performer() {
        double max = avg[0];
        int index = 0;
        //Find max avg
        for (int i = 0; i < avg.length-1; i++) {
            if (avg[i] > max) {
                max = avg[i];
            }
        } print_header();
        //Find students whose avg is equal to max and print info
        for (int i = 0; i < max_subjects; i++) {
            if (avg[i] == max) {
                index = i;
                print_value(index);
            }
        }
    }

    public static void print_header() {
        System.out.printf("%-6s %-15s", "ID", "Name");
        for (int i = 1; i <= max_subjects; i++) {
            System.out.printf("%s%-5d", "Sub", i);
        }
        System.out.printf("%-9s %-6s %s\n", "Total", "Avg", "Grade");
        int amount = 40 + (9)*max_subjects;
        for (int i = 1; i <= amount; i++) {
            System.out.print("-");
        } System.out.println();
    }

    public static void print_value (int index) {
        System.out.printf("%-6d %-15s", id[index], name[index]);
        for (int i = 0; i < max_subjects; i++) {
            System.out.printf("%-7d ", sub_marks[index][i]);
        }
        System.out.printf("%-9d %-6.1f %c\n", total[index], avg[index], grade[index]);
    }

    public static boolean id_checker (int temp, int count, int[] id) {
        //Check input ID (temp) with id[]
        for (int i = 0; i <= count; i++) {
            if (temp == id[i]) {
                return false;
            }
        } return true;
    }

    public static char assign_grade (double avg) {
        //Assign grade depending on avg
        char grade = ' ';
        if (avg >= 90) {
            grade = 'A';
        }
        else if (avg >= 80) {
            grade = 'B';
        }
        else if (avg >= 70) {
            grade = 'C';
        }
        else if (avg >= 60) {
            grade = 'D';
        }
        else if (avg < 60) {
            grade = 'F';
        } return grade;
    }
}
