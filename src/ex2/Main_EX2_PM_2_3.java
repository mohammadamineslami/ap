package ex2;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_2_3 {
    private static final String SAVE_FILE = "game_save.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int k, size, x = 1, y = 1, score = 0, c = 0;
        long start;
        char[][] array;

        if (new File(SAVE_FILE).exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(SAVE_FILE))) {
                k = Integer.parseInt(br.readLine());
                size = k + 2;
                array = new char[size][size];
                for (int i = 0; i < size; i++) {
                    array[i] = br.readLine().toCharArray();
                }
                x = Integer.parseInt(br.readLine());
                y = Integer.parseInt(br.readLine());
                score = Integer.parseInt(br.readLine());
                start = Long.parseLong(br.readLine());
                System.out.println("Game loaded!");
            } catch (IOException e) {
                System.out.println("Failed to load game. Starting a new game.");
                k = initializeGame(scanner, random);
                size = k + 2;
                array = createGameBoard(k, size, random);
                start = System.currentTimeMillis();
            }
        } else {
            k = initializeGame(scanner, random);
            size = k + 2;
            array = createGameBoard(k, size, random);
            start = System.currentTimeMillis();
        }

        scanner.nextLine();
        while (true) {
            printBoard(array, size);
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
                saveGame(SAVE_FILE, k, array, x, y, score, start);
                System.out.println("Game saved. Exiting...");
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
                    new File(SAVE_FILE).delete();
                    break;
                }
            }
        }
        scanner.close();
    }

    private static int initializeGame(Scanner scanner, Random random) {
        System.out.print("Please enter k: ");
        return scanner.nextInt();
    }

    private static char[][] createGameBoard(int k, int size, Random random) {
        char[][] array = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = (i == 0 || i == size - 1 || j == 0 || j == size - 1) ? '*' : ' ';
            }
        }
        int maxEmpty = k * k, placed = 0;
        while (placed < maxEmpty / 4) {
            int i = random.nextInt(k) + 1;
            int j = random.nextInt(k) + 1;
            if (array[i][j] == ' ') {
                array[i][j] = '.';
                placed++;
            }
        }
        array[1][1] = 'X';
        return array;
    }

    private static void printBoard(char[][] array, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void saveGame(String filename, int k, char[][] array, int x, int y, int score, long start) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println(k);
            for (char[] row : array) {
                pw.println(new String(row));
            }
            pw.println(x);
            pw.println(y);
            pw.println(score);
            pw.println(start);
        } catch (IOException e) {
            System.out.println("Failed to save game.");
        }
    }
}
