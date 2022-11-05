package battleship;

public enum Ship {
    AIRCRAFT_CARRIER("Aircraft Carrier", 5),
    BATTLESHIP("Battleship", 4),
    SUBMARINE("Submarine", 3),
    CRUISER("Cruiser", 3),
    DESTROYER("Destroyer", 2);

    private final int shipLength;
    private final String shipTitle;

    public int getShipLength() {
        return shipLength;
    }

    public String getShipTitle() {
        return shipTitle;
    }

    Ship(String shipTitle, int shipLength) {
        this.shipLength = shipLength;
        this.shipTitle = shipTitle;
    }
}
