import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AdventOfCodeDayOne {
    //print out banana

    //function that takes int as param and returns string of it as a word (intAsString(1) returns "one")
    public static String intAsString(int number) {
        switch (number) {
            case 0:
                return "zero";
            case 1:
                return "one";
            case 2:
                return "two";
            case 3:
                return "three";
            case 4:
                return "four";
            case 5:
                return "five";
            case 6:
                return "six";
            case 7:
                return "seven";
            case 8:
                return "eight";
            case 9:
                return "nine";
            default:
                return "error";
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/marti/Desktop/AdventOfCode/inputDayOne.txt"))) {
            String line;
            int totalSum = 0;
            boolean foundOnLeft = false;
            boolean foundOnRight = false;
            while ((line = reader.readLine()) != null) {
                foundOnLeft = false;
                foundOnRight = false;
                //print out the line
                System.out.println(line);
                
                //iterate through the letters in the line
                int oldTotalSum = totalSum;

                int lineLength = line.length();

                //logic for finding the first digit number from the left and right
                int FirstWholeNumberIndexFromLeft = 0;
                int FirstWholeNumberIndexFromRight = 0;
                int FirstWholeNumberFromLeft = 0;
                int FirstWholeNumberFromRight = 0;
                for (int i = 0; i < lineLength; i++) {
                    //if the letter is a number
                    if (Character.isDigit(line.charAt(i)) && !foundOnLeft) {
                        foundOnLeft = true;
                        //totalSum += 10 * Character.getNumericValue(line.charAt(i));
                        FirstWholeNumberIndexFromLeft = i;
                        FirstWholeNumberFromLeft = Character.getNumericValue(line.charAt(i));
                    }

                    if (Character.isDigit(line.charAt(lineLength - i - 1)) && !foundOnRight) {
                        foundOnRight = true;
                        //totalSum += Character.getNumericValue(line.charAt(lineLength - i - 1));
                        FirstWholeNumberIndexFromRight = lineLength - i - 1;
                        FirstWholeNumberFromRight = Character.getNumericValue(line.charAt(lineLength - i - 1));
                    }
                }
                
                //logic for finding the first word number from the left and right
                int FirstWordNumberIndexFromLeft = Integer.MAX_VALUE;
                int FirstWordNumberIndexFromRight = -1;
                int FirstWordNumberFromLeft = 0;
                int FirstWordNumberFromRight = 0;
                for(int j = 0; j < 10; j++){
                    String wordNumber = intAsString(j);
                    if(line.contains(wordNumber)){
                        if(line.indexOf(wordNumber) < FirstWordNumberIndexFromLeft){
                            FirstWordNumberIndexFromLeft = line.indexOf(wordNumber);
                            FirstWordNumberFromLeft = j;
                        }
                        if(line.lastIndexOf(wordNumber) > FirstWordNumberIndexFromRight){
                            FirstWordNumberIndexFromRight = line.lastIndexOf(wordNumber);
                            FirstWordNumberFromRight = j;
                        }
                    }
                }
                
                if(FirstWordNumberIndexFromLeft < FirstWholeNumberIndexFromLeft){
                    totalSum += 10 * FirstWordNumberFromLeft;
                } else {
                    totalSum += 10 * FirstWholeNumberFromLeft;
                }

                if(FirstWordNumberIndexFromRight > FirstWholeNumberIndexFromRight){
                    totalSum += FirstWordNumberFromRight;
                } else {
                    totalSum += FirstWholeNumberFromRight;
                }
                System.out.println(totalSum - oldTotalSum);
            }
            System.out.println(totalSum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


