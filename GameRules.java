package Battleship;

class GameRules {
    protected static Exception error = new Exception("\nError! Wrong input format. Try again:\n");

    protected static void checkShipPlacementRules(Battlefield myBattlefield) {
        int[][] deck = myBattlefield.getPlaceableShip().getDeckCoordinates();

        if (!myBattlefield.getPlaceableShip().isCorrectDeckPosition()
                || deck[0][deck[0].length - 1] > 10
                || deck[1][deck[0].length - 1] > 10) {
            throw new IllegalArgumentException("\nError! Wrong ship location! Try again:\n");
        } else if (myBattlefield.getPlaceableShip().getSize() != deck[0].length) {
            throw new IllegalArgumentException(String.format("\nError! Wrong length of the %s! Try again:\n",
                    myBattlefield.getPlaceableShip().getName()));
        } else {
            for (int i = 0; i < deck[0].length; i++) {
                for (int j = deck[0][i] - 1; j <= deck[0][i] + 1; j++) {
                    for (int l = deck[1][i] - 1; l <= deck[1][i] + 1; l++) {
                        if (myBattlefield.getBATTLEFIELD()[j][l] == 'O') {
                            throw new IllegalArgumentException("\nError! You placed it too close to another one. Try again:\n");
                        }
                    }
                }
            }
        }
    }

    protected static void checkCoordinatesOfShot() {
        if (Missile.getShotCoordinates()[0] > 10 || Missile.getShotCoordinates()[1] > 10) {
            throw new IllegalArgumentException("\nError! You entered the wrong coordinates! Try again:\n");
        }
    }

    protected static void checkTheRulesForMovingToAnotherPlayer(String input) {
        if (!input.trim().isEmpty()) {
            throw new IllegalArgumentException("Error! For pass the move to another player press Enter\n");
        }
    }
}
