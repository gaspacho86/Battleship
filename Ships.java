package Battleship;

enum Ships {
    SHIP_P1_01("Aircraft Carrier", 5, null, false),
    SHIP_P1_02("Battleship", 4, null, false),
    SHIP_P1_03("Submarine", 3, null, false),
    SHIP_P1_04("Cruiser", 3, null, false),
    SHIP_P1_05("Destroyer", 2, null, false),
    SHIP_P2_01("Aircraft Carrier", 5, null, false),
    SHIP_P2_02("Battleship", 4, null, false),
    SHIP_P2_03("Submarine", 3, null, false),
    SHIP_P2_04("Cruiser", 3, null, false),
    SHIP_P2_05("Destroyer", 2, null, false);

    private final String NAME;
    private int size;
    private int[][] deckCoordinates;
    private boolean sunkenShip;
    private boolean correctDeckPosition;

    Ships(String name, int size, int[][] deckCoordinates, boolean sunkenShip) {
        this.NAME = name;
        this.size = size;
        this.deckCoordinates = deckCoordinates;
        this.sunkenShip = sunkenShip;
    }

    void setDeckCoordinates(String[] coordinates) {
        int[] row = {coordinates[0].charAt(0) - '@', coordinates[1].charAt(0) - '@'};
        int[] col = {Integer.parseInt(coordinates[0].substring(1)), Integer.parseInt(coordinates[1].substring(1))};

        this.correctDeckPosition = row[0] == row[1] || col[0] == col[1];
        this.deckCoordinates = new int[2][Math.abs((row[0] + col[0]) - (row[1] + col[1])) + 1];

        for (int i = 0; i < this.deckCoordinates[0].length; i++) {
            this.deckCoordinates[0][i] = row[0] == row[1] ? Math.min(row[0], row[1]) : Math.min(row[0], row[1]) + i;
            this.deckCoordinates[1][i] = col[0] == col[1] ? Math.min(col[0], col[1]) : Math.min(col[0], col[1]) + i;
        }
    }

    String getName() {
        return NAME;
    }

    int getSize() {
        return size;
    }

    void subtractSize() {
        this.size--;
    }

    int[][] getDeckCoordinates() {
        return deckCoordinates;
    }

    boolean isSunkenShip() {
        return sunkenShip;
    }

    void setShipSank() {
        this.sunkenShip = true;
    }

    boolean isCorrectDeckPosition() {
        return correctDeckPosition;
    }
}
