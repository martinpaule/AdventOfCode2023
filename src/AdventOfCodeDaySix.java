import java.util.Arrays;


public class AdventOfCodeDaySix {



    //int[] raceRecordDistances = {219, 1012, 1365, 1089};

    //main
    public static void main(String[] args) {

        //task one
        /*int[] raceTimes = {40, 81, 77, 72};
        int[] raceRecordDistances = {219, 1012, 1365, 1089};

        int errorMarginsMultiplied = 1;
        
        for(int i = 0; i < raceTimes.length; i++) {
            int errorMarginsInThisRace = 0;
            System.out.println(raceTimes[i] + "SECOND RACE----------------------------------------");

            for(int j = 0; j <= raceTimes[i];j++){
                int boatSpeed = j;
                int timeleft = raceTimes[i] - j;
                int distanceTravelled = boatSpeed * timeleft;
                if(distanceTravelled > raceRecordDistances[i]) {
                    errorMarginsInThisRace++;
                    System.out.println("SPEED: " + boatSpeed + " TIMELEFT: " + timeleft + " DISTANCE: " + distanceTravelled + "WIN");
                }else{
                    System.out.println("SPEED: " + boatSpeed + " TIMELEFT: " + timeleft + " DISTANCE: " + distanceTravelled + "LOSE");
                }
            }
            errorMarginsMultiplied *= errorMarginsInThisRace;
        }
        System.out.println("ERROR MARGINS MULTIPLIED: " + errorMarginsMultiplied);*/

        //task two
        long racetime = 40817772L;
        long raceRecordDistance = 219101213651089L;
        //dis = n*(time-n)
        long waysToWin = 0;
        for(long i = 0; i <= racetime; i++) {
            long distanceTravelled = i * (racetime - i);
            if(distanceTravelled > raceRecordDistance) {
                waysToWin++;
            }
        }
        System.out.println("WAYS TO WIN: " + waysToWin);
    }
}
