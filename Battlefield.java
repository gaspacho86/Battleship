package Battleship;

class Battlefield {
    protected final String NAME_PLAYER;
    protected final char[][] BATTLEFIELD = new char[12][12];
    protected final Ships[] SHIPS;
    private int numberShipsInBattlefield;

    public Battlefield(String namePlayer, Ships[] SHIPS) {
        this.NAME_PLAYER = namePlayer;
        this.SHIPS = SHIPS;
    }

    String getNAME_PLAYER() {
        return this.NAME_PLAYER;
    }

    char[][] getBATTLEFIELD() {
        return BATTLEFIELD;
    }

    Ships[] getSHIPS() {
        return this.SHIPS;
    }

    Ships getPlaceableShip() {
        return this.SHIPS[this.numberShipsInBattlefield];
    }

    int getNumberShipsInBattlefield() {
        return numberShipsInBattlefield;
    }

    void createBattlefield() {
        for (int i = 1; i < BATTLEFIELD.length - 1; i++) {
            for (int j = 1; j < BATTLEFIELD.length - 1; j++) {
                BATTLEFIELD[i][j] = '~';
            }
        }
    }

    void printBattlefield(boolean fogOfWar, boolean separatorON) {
        System.out.println(separatorON ? "  1 2 3 4 5 6 7 8 9 10" : "\n  1 2 3 4 5 6 7 8 9 10");
        for (int i = 1; i < BATTLEFIELD.length - 1; i++) {
            System.out.print((char) ('@' + i) + " ");
            for (int j = 1; j < BATTLEFIELD.length - 1; j++) {
                if (fogOfWar && BATTLEFIELD[i][j] != 'X' && BATTLEFIELD[i][j] != 'M') {
                    System.out.print(j == BATTLEFIELD.length - 2 ? '~' + "\n" : '~' + " ");
                } else {
                    System.out.print(j == BATTLEFIELD.length - 2 ? BATTLEFIELD[i][j] + "\n" : BATTLEFIELD[i][j] + " ");
                }
            }
        }
        System.out.println(fogOfWar ? "---------------------" : "");
    }

    void placeShipOnBattlefield() {
        for (int i = 0; i < getPlaceableShip().getDeckCoordinates()[0].length; i++) {
            BATTLEFIELD[getPlaceableShip().getDeckCoordinates()[0][i]][getPlaceableShip().getDeckCoordinates()[1][i]] = 'O';
        }
        this.numberShipsInBattlefield++;
    }

    void openFire() {
        int[] x = {Missile.getShotCoordinates()[0], Missile.getShotCoordinates()[1]};

        Missile.setMissileHitTheTarget(BATTLEFIELD[x[0]][x[1]] == 'O');
        BATTLEFIELD[x[0]][x[1]] = Missile.isMissileHitTheTarget() || BATTLEFIELD[x[0]][x[1]] == 'X' ? 'X' : 'M';
    }

    boolean trackingShipsDamage() {
        for (Ships ship : SHIPS) {
            for (int i = 0; i < ship.getDeckCoordinates()[0].length; i++) {
                if (Missile.isMissileHitTheTarget()
                        && Missile.getShotCoordinates()[1] == ship.getDeckCoordinates()[1][i]
                        && Missile.getShotCoordinates()[0] == ship.getDeckCoordinates()[0][i]) {
                    ship.subtractSize();
                }
                if (ship.getSize() == 0 && !ship.isSunkenShip()) {
                    this.numberShipsInBattlefield--;
                    ship.setShipSank();
                    return true;
                }
            }
        }
        return false;
    }
}
