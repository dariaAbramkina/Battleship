package battleship;

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

