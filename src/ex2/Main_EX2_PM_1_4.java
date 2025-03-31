package ex2;

import java.util.Scanner;

public class Main_EX2_PM_1_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("please enter a character(w/a/s/d): ");
            String input = scanner.nextLine();
            if (input.length() != 1) {
                System.out.println("WARNING");
                continue;
            }
            char ch = input.charAt(0);
            switch (ch) {
                case 'w':
                    System.out.println("UP");
                    break;
                case 'a':
                    System.out.println("LEFT");
                    break;
                case 's':
                    System.out.println("DOWN");
                    break;
                case 'd':
                    System.out.println("RIGHT");
                    break;
                case 'q':
                    System.out.println("EXIT");
                    return;
            }
        }
    }
}