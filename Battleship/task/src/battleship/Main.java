package battleship;

import static battleship.BattleshipGame.*;

public class Main {

    public static void main(String[] args) {
        String[][] matrix = getShipMatrix();
        createField(matrix);
        //getCoordinatesOfAircraftCarrier();
        //addAircraftCarrierHorizontal(5, 2, 6, matrix);
        createField(addShipHorizontal(5, 2, 6, matrix));
        createField(addShipVertical(1, 8, 9, matrix));
        convertCoordinatesToInt("J10", "J8");
        convertCoordinatesToInt("B9", "D9");
    }
}
