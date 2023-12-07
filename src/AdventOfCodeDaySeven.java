import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AdventOfCodeDaySeven {

    static int getHandPrimaryTier(String Hand){

        ArrayList<String> cardTypes= new ArrayList<>();
        ArrayList<Integer> cardOccurances= new ArrayList<>();
        
        for(int i = 0; i < Hand.length(); i++){
            if(cardTypes.contains(Hand.substring(i, i+1)) == false){
                cardTypes.add(Hand.substring(i, i+1));
                cardOccurances.add(1);
            }else{
                cardOccurances.set(cardTypes.indexOf(Hand.substring(i, i+1)), cardOccurances.get(cardTypes.indexOf(Hand.substring(i, i+1))) + 1);
            }

        }

        //if contains joker
        if(cardTypes.contains("J")){

            if(cardTypes.size() == 1){ //high card
                return 7;
            }
           
            //calculate the joker
            int maxOccurance = -1;
            int maxOccuranceIndex = 0;
            for(int i = 0; i < cardOccurances.size(); i++){
                if(cardOccurances.get(i) > maxOccurance && !cardTypes.get(i).contains("J")){
                    maxOccurance = cardOccurances.get(i);
                    maxOccuranceIndex = i;
                }
            }

            if(maxOccurance != -1){
                int indexOfJoker = cardTypes.indexOf("J");
                int numberOfJokers = cardOccurances.get(indexOfJoker);
                cardOccurances.set(maxOccuranceIndex, cardOccurances.get(maxOccuranceIndex) + numberOfJokers);
                //delete jokers
                cardTypes.remove(indexOfJoker);
                cardOccurances.remove(indexOfJoker);
            }

        }


        if(cardTypes.size() == 5){ //high card
            return 1;
        }else if(cardTypes.size() == 4){ //one pair
            return 2;
        }else if(cardTypes.size() == 3){
            if(cardOccurances.contains(3)){ //three of a kind
                return 4;
            }else{ //two pair
                return 3;
            }
        }else if(cardTypes.size() == 2){
            if(cardOccurances.contains(4)){ //four of a kind
                return 6;
            }else{ //full house
                return 5;
            }
        }else if(cardTypes.size() == 1){ //five of a kind
            return 7;
        }
        
        //error return;
        return -1;
    }

    static int getCardValue(String card){
        if(card.equals("A")){
            return 14;
        }else if(card.equals("K")){
            return 13;
        }else if(card.equals("Q")){
            return 12;
        }else if(card.equals("J")){
            return 1;
        }else if(card.equals("T")){
            return 10;
        }else{
            return Integer.parseInt(card);
        }
    }

    static boolean isHandABetterThanHandB(String HandA, String HandB){
        
        if(getHandPrimaryTier(HandA) > getHandPrimaryTier(HandB)){
            return true;
        }else if(getHandPrimaryTier(HandA) < getHandPrimaryTier(HandB)){
            return false;
        }else{
            for(int i = 0; i < HandA.length(); i++){
                if(HandA.charAt(i) == HandB.charAt(i)){
                    continue;
                }
                if(getCardValue(HandA.substring(i, i+1)) > getCardValue(HandB.substring(i, i+1))){
                    return true;
                }else{
                    return false;
                }

            }
        }
        
        return false;
    }



    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/marti/Desktop/AdventOfCode/inputDaySeven.txt"))) {
            
            //array of strinfs
            String[] AllLines;

            //store all lines in an array, where each element is a line
            AllLines = reader.lines().toArray(String[]::new);

            ArrayList<String> CardsSorted = new ArrayList<>();

            CardsSorted.add(AllLines[0]);

            long totalCardValue = 0;




            for(int i = 1; i < AllLines.length; i++){
                String[] lineAsArray = AllLines[i].split(" ");
                
                for(int j = 0; j < CardsSorted.size(); j++) {
                    String[] HandAsArray = CardsSorted.get(j).split(" ");
                    if(!isHandABetterThanHandB(lineAsArray[0], HandAsArray[0])){
                        CardsSorted.add(j, AllLines[i]);
                        break;
                    }
                    //push at the back
                    if(j == CardsSorted.size() - 1){
                        CardsSorted.add(AllLines[i]);
                        break;
                    }

                }

            }

            


            for(int i = 0; i < CardsSorted.size(); i++){
                //System.out.println(CardsSorted.get(i));
                String[] HandAsArray = CardsSorted.get(i).split(" ");
                totalCardValue += (1+i) * Integer.parseInt(HandAsArray[1]);
            }
            System.out.println(totalCardValue);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
