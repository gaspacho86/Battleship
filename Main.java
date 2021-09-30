package Battleship;

import java.util.Arrays;

class Main extends Gameplay {
    protected static boolean gameOver = false;


    public static void main(String[] args) {

        Ships[] shipsPlayer1 = Arrays.copyOfRange(Ships.values(), 0, 5);
        Ships[] shipsPlayer2 = Arrays.copyOfRange(Ships.values(), 5, Ships.values().length);

        Battlefield battlefieldPlayer1 = new Battlefield("Player 1", shipsPlayer1);
        Battlefield battlefieldPlayer2 = new Battlefield("Player 2", shipsPlayer2);

        placementOfShips(battlefieldPlayer1);
        placementOfShips(battlefieldPlayer2);

        while (!gameOver) {
            startShootingSystem(battlefieldPlayer1, battlefieldPlayer2);
            startShootingSystem(battlefieldPlayer2, battlefieldPlayer1);
        }
    }
}
