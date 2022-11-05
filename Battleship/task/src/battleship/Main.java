package battleship;

public class Main {
    public static void main(String[] args) {
        Field field = new Field();
        field.printField();

        for (Ship ship : Ship.values()) {
            field.enterCoordinatesForShip(ship);
            field.printField();
        }
    }
}
