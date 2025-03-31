package ex2;

import java.util.Scanner;
import java.util.Random;

public class Main_EX2_PM_2_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("please enter k:");
        int k = scanner.nextInt();
        int size = k + 2;

        char[][] array = new char[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 || i == size - 1 || j == 0 || j == size - 1) {
                    array[i][j] = '*';
                } else {
                    array[i][j] = ' ';
                }
            }
        }

        int maxEmpty = k * k;
        int c;

        while (true) {
            System.out.print("please enter max dot (maximum:" + maxEmpty + "): ");
            c = scanner.nextInt();
            if (c <= maxEmpty) break;
            System.out.println("the entered number is incorrect");
        }

        int placed = 0;
        while (placed < c) {
            int i = random.nextInt(k) + 1;
            int j = random.nextInt(k) + 1;
            if (array[i][j] == ' ') {
                array[i][j] = '.';
                placed++;
            }
        }

        int x = 1;
        int y = 1;
        array[x][y] = 'X';

        scanner.nextLine();

        while (true) {
            System.out.print("your move(w/a/s/d/q)");
            String input = scanner.nextLine();

            if (input.length() != 1) {
                System.out.println("just enter a character");
                continue;
            }

            char dir = input.charAt(0);
            int newX = x;
            int newY = y;

            if (dir == 'q') {
                System.out.println("exit");
                break;
            } else if (dir == 'w') newX--;
            else if (dir == 's') newX++;
            else if (dir == 'a') newY--;
            else if (dir == 'd') newY++;
            else {
                System.out.println("invalid character");
                continue;
            }

            if (array[newX][newY] == '*') {
                System.out.println("hitting the game wall");
            } else {
                array[x][y] = ' ';
                x = newX;
                y = newY;
                array[x][y] = 'X';

                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        System.out.print(array[i][j] + " ");
                    }
                    System.out.println();
                }
            }
        }
    }
}