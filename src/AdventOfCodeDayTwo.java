import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class AdventOfCodeDayTwo {
    
    //main function
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/marti/Desktop/AdventOfCode/inputDayTwo.txt"))) {
            String line;
           
            int sumOfValidGames = 0;

            while ((line = reader.readLine()) != null) {

                //remove all commas from the line
                line = line.replace(",", "");
                line = line.replace(":", "");
                line = line.replace(";", "");

                //split the line into an array of strings, separated by a space
                String[] splitLine = line.split(" ");
                //print out the line
                //System.out.println(Arrays.toString(splitLine));
                //boolean validGame = true;
                int biggestRed = 0;
                int biggestGreen = 0;
                int biggestBlue = 0;
                for(int i = 2; i < splitLine.length - 1; i+= 2) {
                    //int limit = 0;
                    switch (splitLine[i+1]) {
                        case "red":
                            //limit = 12;
                            if(Integer.valueOf(splitLine[i]) > biggestRed) {
                                biggestRed = Integer.valueOf(splitLine[i]);
                            }
                            break;
                        case "green":
                            //limit = 13;
                            if(Integer.valueOf(splitLine[i]) > biggestGreen) {
                                biggestGreen = Integer.valueOf(splitLine[i]);
                            }
                            break;
                        case "blue":
                            //limit = 14;
                            if(Integer.valueOf(splitLine[i]) > biggestBlue) {
                                biggestBlue = Integer.valueOf(splitLine[i]);
                            }
                            break;
                        default:
                            break;
                    }

                    /*if the number is greater than the limit, the game is invalid
                    if( Integer.valueOf(splitLine[i]) >limit) {
                        validGame = false;
                        //break out of for loop
                        i = splitLine.length+10;
                    }*/
                }
                //if(validGame) {
                //    sumOfValidGames += Integer.valueOf(splitLine[1]);
                //}
                sumOfValidGames += biggestRed * biggestGreen * biggestBlue;
            }
            System.out.println(sumOfValidGames);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
