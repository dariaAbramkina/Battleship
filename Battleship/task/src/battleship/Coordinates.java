package battleship;

public class Coordinates {
    private int startCoordinate;
    private int endCoordinate;
    private boolean isVertical;
    private int startPoint;

    public int getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(int startPoint) {
        this.startPoint = startPoint;
    }

    public Coordinates() {
    }

    public void setStartCoordinate(int startCoordinate) {
        this.startCoordinate = startCoordinate;
    }

    public void setEndCoordinate(int endCoordinate) {
        this.endCoordinate = endCoordinate;
    }

    public void setVertical(boolean vertical) {
        isVertical = vertical;
    }

    public int getStartCoordinate() {
        return startCoordinate;
    }

    public int getEndCoordinate() {
        return endCoordinate;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public Coordinates(int startCoordinate, int endCoordinate, boolean isVertical) {
        this.startCoordinate = startCoordinate;
        this.endCoordinate = endCoordinate;
        this.isVertical = isVertical;
    }
}

