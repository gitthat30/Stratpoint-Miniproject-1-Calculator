package stratpoint.samuelnieva;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calc = new Calculator();
        double x;
        double y;
        int c;
        int m = 0;

        System.out.println("Select mode:");
        System.out.println("1 - Simple");
        System.out.println("2 - Expression");
        System.out.print("Choice: ");

        while(m != 1 && m != 2) {
            try {
                m = sc.nextInt();
            }
            catch(Exception e) {
                sc.next();
            }
        }

        while(true) {
            if(m == 1) {
                try {
                    System.out.print("Please enter your first number: ");
                    x = sc.nextDouble();

                    System.out.print("Please enter your second number: ");
                    y = sc.nextDouble();

                    System.out.println("Please enter operation:");
                    System.out.println("1 - Addition");
                    System.out.println("2 - Subtraction");
                    System.out.println("3 - Multiplication");
                    System.out.println("4 - Division\n");
                    System.out.print("Choice: ");

                    c = sc.nextInt();

                    switch(c) {
                        case 1: //Addition
                            System.out.println(calc.addNum(x, y));
                            break;
                        case 2:
                            System.out.println(calc.subNum(x, y));
                            break;
                        case 3:
                            System.out.println(calc.mulNum(x, y));
                            break;
                        case 4:
                            if(y == 0)
                                System.out.println("Invalid input: Divided by zero");
                            else
                                System.out.println(calc.divNum(x, y));
                            break;
                        default:
                            System.out.println("Invalid input: Please enter one of the choices.");
                    }

                    System.out.println();
                }
                catch(InputMismatchException e) {
                    System.out.println("Invalid input: Input mismatch\n");
                    sc.next();
                }
            }
            else {

                System.out.println("Please enter your expression (No spaces and parentheses!):");
                String expression = sc.next();

                System.out.println("\nTotal = " + calc.calculateString(expression) + "\n");

            }

        }
    }
}