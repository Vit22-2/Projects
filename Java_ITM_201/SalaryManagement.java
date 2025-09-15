import java.util.Scanner;
public class SalaryManagement {
    public static void main(String[] args) {
        //Declaring variables
        int emp1_id = 0, emp2_id = 0, emp3_id = 0;
        char emp1_char1 = ' '; char emp1_char2 = ' '; char emp1_char3 = ' ';
        char emp2_char1 = ' '; char emp2_char2 = ' '; char emp2_char3 = ' ';
        char emp3_char1 = ' '; char emp3_char2 = ' '; char emp3_char3 = ' ';
        double emp1_salary = 0, emp2_salary = 0, emp3_salary = 0;
        double emp1_bonus = 0, emp2_bonus = 0, emp3_bonus = 0;
        Scanner sc = new Scanner(System.in);
        employee(emp1_id, emp2_id, emp3_id,
                emp1_char1, emp1_char2, emp1_char3,
                emp2_char1, emp2_char2, emp2_char3,
                emp3_char1, emp3_char2, emp3_char3,
                emp1_salary, emp2_salary, emp3_salary,
                emp1_bonus, emp2_bonus, emp3_bonus, sc);
    }
    //Setting up a function in order to recall later
    static void employee (int emp1_id, int emp2_id, int emp3_id,
                          char emp1_char1, char emp1_char2,char emp1_char3,
                          char emp2_char1, char emp2_char2, char emp2_char3,
                          char emp3_char1, char emp3_char2, char emp3_char3,
                          double emp1_salary, double emp2_salary, double emp3_salary,
                          double emp1_bonus, double emp2_bonus, double emp3_bonus, Scanner sc) {
        int choice;
        System.out.println("1.Add employee\n2.Calculate salary\n3.Display employee records\n4.Search\n5.Exit");
        System.out.print("Enter choice: ");
        choice = sc.nextInt();
        switch (choice) {
            //Adding Employee details
            case 1:
                System.out.print("Enter employee ID: ");
                int id = sc.nextInt();
                System.out.print("Enter employee name first character: ");
                char char1 = sc.next().charAt(0);
                System.out.print("Enter employee name second character: ");
                char char2 = sc.next().charAt(0);
                System.out.print("Enter employee name third character: ");
                char char3 = sc.next().charAt(0);
                System.out.print("Enter employee salary: ");
                double salary = sc.nextDouble();
                //Validate salary greater or equal to 0
                if (salary < 0) {
                    System.out.println("Invalid salary");
                    break;
                }
                //Assigning values to variables based on inputs
                if (emp1_id == 0) {
                    emp1_id = id;
                    emp1_char1 = char1;
                    emp1_char2 = char2;
                    emp1_char3 = char3;
                    emp1_salary = salary;
                    System.out.print("Registration Complete\n");
                } else if (emp2_id == 0) {
                    emp2_id = id;
                    emp2_char1 = char1;
                    emp2_char2 = char2;
                    emp2_char3 = char3;
                    emp2_salary = salary;
                    System.out.print("Registration Complete\n");
                } else if (emp3_id == 0) {
                    emp3_id = id;
                    emp3_char1 = char1;
                    emp3_char2 = char2;
                    emp3_char3 = char3;
                    emp3_salary = salary;
                    System.out.print("Registration Complete\n");
                } else {
                    System.out.println("Employee Maxed");
                }
                break;
            //Calculate bonus based on rating
            case 2:
                System.out.print("Enter employee ID: ");
                int calcID = sc.nextInt();
                System.out.print("Enter employee rating (1-5): ");
                int rating = sc.nextInt();
                //Switch case to assign bonus based on rating
                double bonus = switch (rating) {
                    case 5 -> 0.2;
                    case 4 -> 0.15;
                    case 3 -> 0.1;
                    default -> 0.0;
                };
                //Calculating bonus based on rating
                if (calcID == emp1_id) {
                    emp1_bonus = bonus * emp1_salary;
                    System.out.printf("Employee Bonus: %.2f\n", emp1_bonus);
                } else if (calcID == emp2_id) {
                    emp2_bonus = bonus * emp2_salary;
                    System.out.printf("Employee Bonus: %.2f\n", emp2_bonus);
                } else if (calcID == emp3_id) {
                    emp3_bonus = bonus * emp3_salary;
                    System.out.printf("Employee Bonus: %.2f\n", emp3_bonus);
                } else {
                    System.out.println("Employee Not Found");
                }
                break;
            //Employee Records
            case 3:
                System.out.print("Employee Records\n");
                if (emp1_id == 0 && emp2_id == 0 && emp3_id == 0) {
                    System.out.print("Empty\n");
                } else {
                    System.out.printf("Employee ID: %d, Employee Name: %c%c%c, Employee Salary: %.2f, Bonus: %.2f\n",
                            emp1_id, emp1_char1, emp1_char2, emp1_char3, emp1_salary, emp1_bonus);
                    System.out.printf("Employee ID: %d, Employee Name: %c%c%c, Employee Salary: %.2f, Bonus: %.2f\n",
                            emp2_id, emp2_char1, emp2_char2, emp2_char3, emp2_salary, emp2_bonus);
                    System.out.printf("Employee ID: %d, Employee Name: %c%c%c, Employee Salary: %.2f, Bonus: %.2f\n",
                            emp3_id, emp3_char1, emp3_char2, emp3_char3, emp3_salary, emp3_bonus);
                } break;
            //Search for employee using ID
            case 4:
                System.out.print("Search employee with ID: ");
                int searchID = sc.nextInt();
                if (searchID == emp1_id) {
                    System.out.printf("Employee ID: %d, Employee Name: %c%c%c, Employee Salary: %.2f, Bonus: %.2f\n",
                            emp1_id, emp1_char1, emp1_char2, emp1_char3, emp1_salary, emp1_bonus);
                } else if (searchID == emp2_id) {
                    System.out.printf("Employee ID: %d, Employee Name: %c%c%c, Employee Salary: %.2f, Bonus: %,2f\n",
                            emp2_id, emp2_char1, emp2_char2, emp2_char3, emp2_salary, emp2_bonus);
                } else if (searchID == emp3_id) {
                    System.out.printf("Employee ID: %d, Employee Name: %c%c%c, Employee Salary: %.2f, Bonus: %.2f\n",
                            emp3_id, emp3_char1, emp3_char2, emp3_char3, emp3_salary, emp3_bonus);
                } else {
                    System.out.println("Employee Not Found");
                }
                break;
            //Exit Program
            case 5:
                System.out.print("Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid choice");
        //Calling function inside itself to make it recursive
        } employee(emp1_id, emp2_id, emp3_id,
                emp1_char1, emp1_char2, emp1_char3,
                emp2_char1, emp2_char2, emp2_char3,
                emp3_char1, emp3_char2, emp3_char3,
                emp1_salary, emp2_salary, emp3_salary,
                emp1_bonus, emp2_bonus, emp3_bonus, sc);
    }
}