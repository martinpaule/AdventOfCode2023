import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


public class AdventOfCodeDayThree {


    public static int getNumberFromString(String string,int index) {

        if(!Character.isDigit(string.charAt(index))) {
            return -1;
        }

        int retnum = 0;
        int rightMostIndex = index;
        boolean keepSearching = true;
        //find the right most index of the number
        while(keepSearching) {
            if(rightMostIndex == string.length()-1) {
                keepSearching = false;
                break;
            }
            if(!Character.isDigit(string.charAt(rightMostIndex+1))) {
                keepSearching = false;
                break;
            }
            rightMostIndex++;
        }
        keepSearching = true;
        int decimalValue = 1;
        int numConstructIndex = rightMostIndex;
        //construct the number
        while(keepSearching) {
            retnum += decimalValue * Character.getNumericValue(string.charAt(numConstructIndex));
            decimalValue *= 10;
            if(numConstructIndex == 0) {
                keepSearching = false;
                break;
            }
            if(!Character.isDigit(string.charAt(numConstructIndex-1))) {
                keepSearching = false;
                break;
            }
            numConstructIndex--;
        }

        return retnum;
    }
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/marti/Desktop/AdventOfCode/inputDayThree.txt"))) {
            
            String line;
            //array of strinfs
            String[] AllLines;

            //store all lines in an array, where each element is a line
            AllLines = reader.lines().toArray(String[]::new);
            //print out the array

            int totalSum = 0;
            int totalEnginePartSum = 0;
            for(int i = 0; i < AllLines.length; i++) {
                boolean validNumber = false;
                boolean readingNumber = false;
                int decimalValue = 1;
                int numberSum = 0;

                //debug out line
                System.out.println(" ");
                System.out.println(AllLines[i]);

                for(int j = AllLines[i].length()-1; j >=0; j--) {

                    //part two task:
                    if(AllLines[i].charAt(j) == '*'){
                        int adjacentNumbers = 0;
                        int adjacentNumbersProduct = 1;
                        
                        boolean newNumber = true;
                        //iterate through all adjacent numbers, making sure to not go out of bounds and not count the same number twice+ times
                        for(int y_o = -1; y_o <= 1; y_o++){
                            for(int x_o = -1;x_o <= 1; x_o++){

                                //out of bounds check
                                if((x_o == 0 && y_o == 0) || (i == 0 && y_o == -1) || (i == AllLines.length-1 && y_o == 1) || (j == 0 && x_o == -1) || (j == AllLines[i].length()-1 && x_o == 1)) {
                                    newNumber = true;
                                    continue;
                                }
                                //if the character is not a number
                                if(getNumberFromString(AllLines[i+y_o],j+x_o)== -1){
                                    newNumber = true;
                                }else{
                                    //if the character is a new number add it up
                                    if(newNumber){
                                        adjacentNumbers++;
                                        adjacentNumbersProduct *= getNumberFromString(AllLines[i+y_o],j+x_o);
                                        System.out.print(getNumberFromString(AllLines[i+y_o],j+x_o) + " ");
                                        newNumber = false;
                                    }
                                }
                                
                            }
                            newNumber = true;
                        }
                        //engine parts have EXACTLY two adjacent numbers
                        if(adjacentNumbers == 2){
                            System.out.print("adNumProduct: " + adjacentNumbersProduct + " ");
                            totalEnginePartSum += adjacentNumbersProduct;
                        }
                    }
                    
                    //part one task:
                    //if the character is a number
                    if(Character.isDigit(AllLines[i].charAt(j))) {
                        //if we are not reading a number
                        if(!readingNumber){
                            readingNumber = true;   
                            decimalValue = 1;
                        }
                        numberSum += decimalValue * Character.getNumericValue(AllLines[i].charAt(j));
                        decimalValue *= 10;

                        //valid number check
                        for(int x_offs = -1; x_offs <= 1; x_offs++) {
                            for(int y_offs = -1; y_offs <= 1; y_offs++) {
                                if((x_offs == 0 && y_offs == 0) || (i == 0 && y_offs == -1) || (i == AllLines.length-1 && y_offs == 1) || (j == 0 && x_offs == -1) || (j == AllLines[i].length()-1 && x_offs == 1)) {
                                    continue;
                                }

                                if(!Character.isDigit(AllLines[i+y_offs].charAt(j+x_offs)) && AllLines[i+y_offs].charAt(j+x_offs) != '.') {
                                    validNumber = true;
                                }
                            }
                        }


                    }else{
                        //if we are reading a number
                        if(readingNumber && validNumber) {
                            totalSum += numberSum;
                            //print out numberSum
                            //System.out.print(numberSum + " ");
                        }
                        readingNumber = false;
                        validNumber = false;
                        numberSum = 0;

                    }

                    //fix for including the cases of the line starting with a number
                    if(j == 0 && readingNumber && validNumber) {
                        totalSum += numberSum;
                        //print out numberSum
                        //System.out.print(numberSum + " ");
                    }
                }
            }
            //System.out.println(totalSum);
            System.out.println(totalEnginePartSum);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
