package battleship;

import java.util.Scanner;

import static java.lang.String.format;

public class Field {
    private char[][] field;

    public Field() {
        field = new char[10][10];
        for (int i = 0; i < field.length; i++)
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = '~';
            }
    }

    public void printField() {
        char rowLetter = 'A';
        System.out.println(" 1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < field.length; i++) {
            System.out.print(rowLetter++);
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(" " + field[i][j]);
            }
            System.out.println();
        }
    }

    public void enterCoordinatesForShip(Ship ship) {
        System.out.println(format("Enter the coordinates of the %s (%s cells):", ship.getShipTitle(), ship.getShipLength()));
        boolean areCoordinatesAccepted = false;
        while (!areCoordinatesAccepted) {
            System.out.print("> ");
            int[] coordinates;
            Scanner scanner = new Scanner(System.in);
            coordinates = convertCoordinatesToInt(scanner.nextLine());
            if (!areCoordinatesValid(coordinates)) {
                System.out.println("Error! Wrong ship location! Try again:");
            } else if (getLengthByCoordinates(coordinates) != ship.getShipLength()) {
                System.out.println(format("Error! Wrong length of the %s! Try again:", ship.getShipTitle()));
            } else if (isShipToCloseToAnotherOne(coordinates)) {
                System.out.println("Error! You placed it too close to another one. Try again:");
            } else {
                addShip(coordinates);
                areCoordinatesAccepted = true;
            }
        }
    }

    public void firstShoot() {
        System.out.println("Take a shot!");
        boolean areCoordinateValid = false;
        while (!areCoordinateValid) {
            System.out.print("> ");
            int[] coordinates;
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (!areCoordinatesOfShootValid(input)) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
            } else {
                coordinates = convertCoordinatesToInt(input);
                makeShoot(coordinates);
                areCoordinateValid = true;
            }
        }
    }

    public int[] convertCoordinatesToInt(String inputLine) {
        String[] input = inputLine.split(" ");
        if (input.length == 2) {
            int[] coordinates = new int[4];
            coordinates[0] = Columns.valueOfLetter(input[0].charAt(0)).rowValue;
            coordinates[1] = Integer.parseInt(input[0].substring(1)) - 1;
            coordinates[2] = Columns.valueOfLetter(input[1].charAt(0)).rowValue;
            coordinates[3] = Integer.parseInt(input[1].substring(1)) - 1;
            return coordinates;
        } else {
            int[] coordinates = new int[2];
            coordinates[0] = Columns.valueOfLetter(input[0].charAt(0)).rowValue;
            coordinates[1] = Integer.parseInt(input[0].substring(1)) - 1;
            return coordinates;
        }
    }

    public int getLengthByCoordinates(int[] coordinates) {
        if (coordinates[0] == coordinates[2])
            return Math.abs(coordinates[1] - coordinates[3]) + 1;
        else return Math.abs(coordinates[0] - coordinates[2]) + 1;
    }

    public boolean areCoordinatesValid(int[] coordinates) {
        return (coordinates[0] == coordinates[2]) || (coordinates[1] == coordinates[3]);
    }

    public void addShip(int[] coordinates) {
        if (coordinates[1] == coordinates[3]) {
            for (int i = Math.min(coordinates[0], coordinates[2]); i <= Math.max(coordinates[0], coordinates[2]); i++) {
                field[i][coordinates[1]] = 'O';
            }
        } else {
            for (int j = Math.min(coordinates[1], coordinates[3]); j <= Math.max(coordinates[1], coordinates[3]); j++) {
                field[coordinates[0]][j] = 'O';
            }
        }
    }

    public boolean isShipToCloseToAnotherOne(int[] coordinates) {
        int result = 0;

        if (coordinates[1] == coordinates[3]) {
            if (coordinates[0] == 0) {
                for (int i = coordinates[0]; i < coordinates[2] + 1; i++)
                    for (int j = coordinates[1]; j < coordinates[1] + 1; j++) {
                        if (field[i][j] == 'O') {
                            result++;
                        }
                    }
            } else if (coordinates[1] == field.length - 1 || Math.max(coordinates[0], coordinates[2]) == field.length - 1) {
                for (int i = Math.min(coordinates[0], coordinates[2]) - 1; i < Math.max(coordinates[0], coordinates[2]); i++)
                    for (int j = coordinates[1] - 1; j < coordinates[1]; j++) {
                        if (field[i][j] == 'O') {
                            result++;
                        }
                    }
            } else {
                for (int i = Math.min(coordinates[0], coordinates[2]) - 1; i <= Math.max(coordinates[0], coordinates[2]) + 1; i++)
                    for (int j = coordinates[1] - 1; j <= coordinates[1] + 1; j++) {
                        if (field[i][j] == 'O') {
                            result++;
                        }
                    }
            }
        } else if (coordinates[0] == 0 ||
                Math.min(coordinates[1], coordinates[3]) == 0) {
            for (int i = coordinates[0]; i < coordinates[0] + 1; i++)
                for (int j = Math.min(coordinates[1], coordinates[3]); j < Math.max(coordinates[1], coordinates[3]) + 1; j++) {
                    if (field[i][j] == 'O')
                        result++;
                }
        } else if (coordinates[0] == field.length - 1 || Math.max(coordinates[1], coordinates[3]) == field.length - 1) {
            for (int i = coordinates[0] - 1; i < coordinates[0]; i++)
                for (int j = Math.min(coordinates[1], coordinates[3]) - 1; j < Math.max(coordinates[1], coordinates[3]); j++) {
                    if (field[i][j] == 'O')
                        result++;
                }
        } else {
            for (int i = coordinates[0] - 1; i <= coordinates[0] + 1; i++)
                for (int j = Math.min(coordinates[1], coordinates[3]) - 1; j <= Math.max(coordinates[1], coordinates[3]) + 1; j++) {
                    if (field[i][j] == 'O')
                        result++;
                }
        }
        return result != 0;
    }

    public boolean areCoordinatesOfShootValid(String inputLine) {
        boolean firstCoordinate = false;
        boolean secondCoordinate = false;
        for (Columns c : Columns.values()) {
            if (c.name().equals(inputLine.substring(0, 1))) {
                firstCoordinate = true;
            }
        }
        int coordinateTwo = Integer.parseInt(inputLine.substring(1));
        if (coordinateTwo > 0 && coordinateTwo <= 10) {
            secondCoordinate = true;
        }
        return firstCoordinate && secondCoordinate;
    }

    public void makeShoot(int[] coordinates) {
        if (field[coordinates[0]][coordinates[1]] == 'O') {
            field[coordinates[0]][coordinates[1]] = 'X';
            System.out.print("You hit a ship!");
        } else {
            field[coordinates[0]][coordinates[1]] = 'M';
            System.out.print("You missed!");
        }
    }

}
