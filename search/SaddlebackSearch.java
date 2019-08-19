package search;

import java.util.Scanner;

/**
 * Saddleback Search is applied to a sorted 2D array
 * (elements are sorted across every row and column, assuming ascending order).
 * We start from left bottom to right top (or opposite) to find an element.
 * <p>
 * Average performance	O(m+n)
 * 
 * @see Search 
 */
public class SaddlebackSearch implements Search {

    @Override
    public <T extends Comparable<T>> int[] find(T[][] array, T value) {
        return search(array, value, array.length - 1, 0);
    }

    /**
     * 
     * @param <T>    - any comparable type
     * @param array  - a 2D array where the element should be found
     * @param value  - an element to be found
     * @param row    - current row
     * @param column - current column
     * @return first found location of the element
     */
    private <T extends Comparable<T>> int[] search(T[][] array, T value, int row, int column) {
        int ans[] = { -1, -1 };
        if (row >= array.length || column >= array[0].length) {
            return ans;
        }

        if (array[row][column].compareTo(value) > 0) {
            return search(array, value, row - 1, column);
        } else if (array[row][column].compareTo(value) < 0) {
            return search(array, value, row, column + 1);
        } else {
            ans[0] = row;
            ans[1] = column;
            return ans;
        }
    }

    /**
     * Drive program.
     * 
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        /**
        Input:
        5 5 
        -10 -5 -3 4 9
        -6 -2 0 5 10
        -4 -1 1 6 12
        2 3 7 8 13
        100 120 130 140 150
        140 
        Output:
        4 3
        */
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        Integer integers[][] = new Integer[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                integers[i][j] = scanner.nextInt();
            }
        }
        Integer integer = scanner.nextInt();
        SaddlebackSearch saddlebackSearch = new SaddlebackSearch();
        int ans[] = saddlebackSearch.find(integers, integer);
        System.out.println(ans[0] + " " + ans[1]);

        /**
        Input:
        4 6
        a b c d e f
        g h i j k l
        m n o p q r
        s t u v w x
        o
        Output:
        2 2
        */
        row = scanner.nextInt();
        column = scanner.nextInt();
        scanner.nextLine();
        String strings[][] = new String[row][column];
        for (int i = 0; i < row; i++) {
            strings[i] = scanner.nextLine().split(" ");
        }
        String string = scanner.nextLine();
        ans = saddlebackSearch.find(strings, string);
        System.out.println(ans[0] + " " + ans[1]);

        scanner.close();
    }
}