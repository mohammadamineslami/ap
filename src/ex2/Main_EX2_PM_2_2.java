package ex2;

import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_2_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Please enter k: ");
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
            System.out.print("Please enter max dot (maximum: " + maxEmpty + "): ");
            c = scanner.nextInt();
            if (c <= maxEmpty) break;
            System.out.println("The entered number is incorrect");
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
        int score = 0;
        long start = System.currentTimeMillis();

        scanner.nextLine();
        while (true) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(array[i][j] + " ");
                }
                System.out.println();
            }

            System.out.print("Your move (w/a/s/d/q): ");
            String input = scanner.nextLine();

            if (input.length() != 1) {
                System.out.println("Just enter a character");
                continue;
            }

            char dir = input.charAt(0);
            int newX = x;
            int newY = y;

            if (dir == 'q') {
                System.out.println("Exit");
                break;
            } else if (dir == 'w') newX--;
            else if (dir == 's') newX++;
            else if (dir == 'a') newY--;
            else if (dir == 'd') newY++;
            else {
                System.out.println("Invalid character");
                continue;
            }

            if (array[newX][newY] == '*') {
                System.out.println("Hitting the game wall");
            } else {
                if (array[newX][newY] == '.') {
                    score++;
                }
                array[x][y] = ' ';
                x = newX;
                y = newY;
                array[x][y] = 'X';

                if (score == c) {
                    long finish = System.currentTimeMillis();
                    long timeElapsed = finish - start;
                    System.out.println("Game over! Score: " + score + ", Time: " + timeElapsed + "ms");
                    break;
                }
            }
        }
        scanner.close();
    }
}
