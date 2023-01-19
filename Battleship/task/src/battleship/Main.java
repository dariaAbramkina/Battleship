package battleship;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Field field1 = new Field();
        Field field2 = new Field();
        System.out.println("Player 1, place your ships on the game field");
        field1.printField();
        for (Ship ship : Ship.values()) {
            field1.enterCoordinatesForShip(ship);
            field1.printField();
        }
        System.out.println("Press Enter and pass the move to another player\n" +
                "...");
        scanner.nextLine();
        System.out.println("Player 2, place your ships to the game field");
        field2.printField();
        for (Ship ship : Ship.values()) {
            field2.enterCoordinatesForShip(ship);
            field2.printField();
        }
        System.out.println("Press Enter and pass the move to another player\n" +
                "...");
        while (!field1.areAllShipsHit() || !field2.areAllShipsHit()) {
            scanner.nextLine();
            field2.printEmptyField();
            System.out.println("---------------------");
            field1.printField();
            System.out.println("Player 1, it's your turn:");
            field2.takeShoot();
            System.out.println("Press Enter and pass the move to another player\n" +
                    "...");
            scanner.nextLine();
            field1.printEmptyField();
            System.out.println("---------------------");
            field2.printField();
            System.out.println("Player 2, it's your turn:");
            field1.takeShoot();
        }
    }
}
