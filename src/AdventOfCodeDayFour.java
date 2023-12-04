import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class AdventOfCodeDayFour {
    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/marti/Desktop/AdventOfCode/inputDayFour.txt"))) {
           
            String line;
            //int totalSumOfPoints = 0;

            int[] cardAmounts = new int[211];

            for(int i = 0; i < cardAmounts.length; i++) {
                cardAmounts[i] = 1;
            }

            int cardProcessedIndex = 0;
            while ((line = reader.readLine()) != null) {

                String modifLine = line.replaceAll("   ", " ");
                modifLine = modifLine.replaceAll("  ", " ");


                String[] lineAsArray = modifLine.split(" ");
                System.out.println(Arrays.toString(lineAsArray));
                //idx 0 is "Card", idx 1 is Card Num, idx 2 to 11 (inclusive) is the winning number, idx 12 till end is my nums
                String[] winningNumbers = Arrays.copyOfRange(lineAsArray, 2, 12);
                //System.out.println(Arrays.toString(winningNumbers));
                String[] myNumbers = Arrays.copyOfRange(lineAsArray, 13, lineAsArray.length);
                //System.out.println(Arrays.toString(myNumbers));

                int matches = 0;

                for(int i = 0; i < winningNumbers.length; i++) {
                    for(int j = 0; j < myNumbers.length; j++) {
                        if(winningNumbers[i].equals(myNumbers[j])) {
                            matches++;
                            //System.out.println("Match found: " + winningNumbers[i]);
                        }
                    }
                }
                
                for(int i = 1; i <= matches; i++) {
                    if(cardProcessedIndex + i < cardAmounts.length) {
                        cardAmounts[cardProcessedIndex + i]+= cardAmounts[cardProcessedIndex];
                    }
                }

                cardProcessedIndex++;

                /*if(matches > 0){

                    totalSumOfPoints += Math.pow(2, matches-1);
                    System.out.println("Added " + Math.pow(2, matches-1) + " points to total sum of points");
                }*/
            }

            int totalCards = 0;
            for(int i = 0; i < cardAmounts.length; i++) {
                totalCards += cardAmounts[i];
            }
            System.out.println(totalCards);
            //System.out.println(totalSumOfPoints);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
