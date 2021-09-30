package Battleship;

class Missile {
     private static int[] shotCoordinates;
     private static boolean missileHitTheTarget;

     static void setShotCoordinates(String coordinates) {
         shotCoordinates = new int[]{coordinates.charAt(0) - '@', Integer.parseInt(coordinates.substring(1))};
     }

     static int[] getShotCoordinates() {
         return shotCoordinates;
     }

     static void setMissileHitTheTarget(boolean newValue) {
         missileHitTheTarget = newValue;
     }

     static boolean isMissileHitTheTarget() {
         return missileHitTheTarget;
     }
 }
