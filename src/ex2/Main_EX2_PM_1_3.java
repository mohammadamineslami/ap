package ex2;

import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_1_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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

        int c;
        int emptyCells = (size - 2) * (size - 2);
        Random random = new Random();

        do {
            System.out.print("Enter the number of dots (c): ");
            c = scanner.nextInt();
            if (c > emptyCells) {
                System.out.println("Error: c is greater than the available empty spaces. Try again.");
            }
        } while (c > emptyCells);

        int placed = 0;
        while (placed < c) {
            int row = random.nextInt(size - 2) + 1;
            int col = random.nextInt(size - 2) + 1;
            if (array[row][col] != '.') {
                array[row][col] = '.';
                placed++;
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
