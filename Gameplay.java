package Battleship;

import java.util.Locale;
import java.util.Scanner;

class Gameplay extends GameRules {
    static Scanner scanner = new Scanner(System.in);

    static void placementOfShips(Battlefield myBattlefield) {
        System.out.println("\n" + myBattlefield.getNAME_PLAYER() + ", place your ships on the game field");
        myBattlefield.createBattlefield();
        myBattlefield.printBattlefield(false, false);

        while (myBattlefield.getNumberShipsInBattlefield() != myBattlefield.getSHIPS().length) {
            System.out.printf("Enter the coordinates of the %s (%d cells):\n\n",
                    myBattlefield.getPlaceableShip().getName(), myBattlefield.getPlaceableShip().getSize());

            while (true) {
                try {
                    myBattlefield.getPlaceableShip().setDeckCoordinates(scanner.nextLine().toUpperCase(Locale.ROOT).split(" "));
                    checkShipPlacementRules(myBattlefield);
                    myBattlefield.placeShipOnBattlefield();
                    myBattlefield.printBattlefield(false, false);
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage().contains("Error") ? e.getMessage() : error.getMessage());
                }
            }
        }
        passTheMoveToAnotherPlayer();
    }

    static void startShootingSystem(Battlefield myBattlefield, Battlefield enemyBattlefield) {

        enemyBattlefield.printBattlefield(true, false);
        myBattlefield.printBattlefield(false, true);

        System.out.println(myBattlefield.getNAME_PLAYER() + ", it's your turn:\n");

        while (true) {
            try {
                Missile.setShotCoordinates((scanner.nextLine().toUpperCase(Locale.ROOT)));
                checkCoordinatesOfShot();
                enemyBattlefield.openFire();
                if (enemyBattlefield.trackingShipsDamage()) {
                    if (enemyBattlefield.getNumberShipsInBattlefield() == 0) {
                        System.out.println("You sank the last ship. You won. Congratulations!");
                        Main.gameOver = true;
                        return;
                    } else {
                        System.out.println("\nYou sank a ship!");
                    }
                } else if (Missile.isMissileHitTheTarget()) {
                    System.out.println("\nYou hit a ship!");
                } else {
                    System.out.println("\nYou missed!");
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage().contains("Error") ? e.getMessage() : error.getMessage());
            }
        }
        passTheMoveToAnotherPlayer();
    }

    protected static void passTheMoveToAnotherPlayer() {
        System.out.println("Press Enter and pass the move to another player");
        while (true) {
            try {
                checkTheRulesForMovingToAnotherPlayer(scanner.nextLine());
                for (int i = 0; i < 50; i++) System.out.println("\n");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

//    public static void promptEnterKey() {
//        System.out.println("Press Enter and pass the move to another player");
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
