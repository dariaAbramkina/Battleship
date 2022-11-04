package battleship;

import java.util.List;
import java.util.Scanner;

public class BattleshipGame {

    public static void createField(String[][] matrix) {
        char rowLetter = 'A';
        int rowsCount = 11;
        int columnsCount = 11;
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                if (i == 0 && j == 0) {
                    System.out.print(" ");
                } else {
                    if (i == 0) {
                        System.out.print(j + " ");
                    } else {
                        if (j == 0) {
                            System.out.print(rowLetter + " ");
                            rowLetter++;
                        } else {
                            System.out.print(matrix[i - 1][j - 1] + " ");
                        }

                    }

                }

            }
            System.out.println();
        }
    }


    public static String[][] getShipMatrix() {
        int rowsCount = 10;
        int columnsCount = 10;
        String[][] matrix = new String[rowsCount][columnsCount];
        for (int i = 0; i < rowsCount; i++)
            for (int j = 0; j < columnsCount; j++) {
                matrix[i][j] = "~";
            }
        return matrix;

    }

    public static List<String> getCoordinatesOfAircraftCarrier() {
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String firstPosition = scanner.next();
        String lastPosition = scanner.next();
        return List.of(firstPosition, lastPosition);
    }

    public static String[][] addShipHorizontal(int x, int startPosition, int endPosition, String[][] matrix) {
        for (int j = startPosition; j <= endPosition; j++) {
            matrix[x][j] = "O";
        }
        return matrix;
    }

    public static String[][] addShipVertical(int y, int startPosition, int endPosition, String[][] matrix) {
        for (int i = startPosition; i <= endPosition; i++) {
            matrix[i][y] = "O";
        }
        return matrix;
    }


    public enum Columns {
        A('A', 0),
        B('B', 1),
        C('C', 2),
        D('D', 3),
        E('E', 4),
        F('F', 5),
        G('G', 6),
        H('H', 7),
        I('I', 8),
        J('J', 9);
        public final int rowValue;
        public final char rowLetter;

        private Columns(char rowLetter, int rowValue) {
            this.rowValue = rowValue;
            this.rowLetter = rowLetter;
        }

        public static Columns valueOfLetter(char columnLetter) {
            for (Columns e : values()) {
                if (e.rowLetter == columnLetter) {
                    return e;
                }
            }
            return null;
        }


    }

    public static Coordinates convertCoordinatesToInt(String firstCoordinate, String secondCoordinate) {
        Coordinates coordinates = new Coordinates();
        char firstLetter = firstCoordinate.charAt(0);
        char secondLetter = secondCoordinate.charAt(0);
        int firstColumnValue = Integer.parseInt(firstCoordinate.substring(1));
        int secondColumnValue = Integer.parseInt(secondCoordinate.substring(1));
        int firstLetterInt = Columns.valueOfLetter(firstCoordinate.charAt(0)).rowValue;
        int secondLetterInt = Columns.valueOfLetter(secondCoordinate.charAt(0)).rowValue;
        if (firstLetter == secondLetter) {
            coordinates.setVertical(false);
            coordinates.setStartPoint(Columns.valueOfLetter(firstCoordinate.charAt(0)).rowValue);
            if (firstColumnValue > secondColumnValue) {
                coordinates.setStartCoordinate(secondColumnValue);
                coordinates.setEndCoordinate(firstColumnValue);
            } else {
                coordinates.setStartCoordinate(firstColumnValue);
                coordinates.setEndCoordinate(secondColumnValue);
            }
        } else if (firstColumnValue == secondColumnValue) {
            coordinates.setVertical(true);
            coordinates.setStartPoint(firstColumnValue);
            if (firstLetterInt > secondLetterInt) {
                coordinates.setStartCoordinate(secondLetterInt);
                coordinates.setEndCoordinate(firstLetterInt);
            } else {
                coordinates.setStartCoordinate(firstLetterInt);
                coordinates.setEndCoordinate(secondLetterInt);
            }
        }
        return coordinates;
    }
}
