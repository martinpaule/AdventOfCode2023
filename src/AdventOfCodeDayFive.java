import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AdventOfCodeDayFive {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/marti/Desktop/AdventOfCode/inputDayFive.txt"))) {
            
            String line;
            //array of strinfs
            String[] AllLines;

            //store all lines in an array, where each element is a line
            AllLines = reader.lines().toArray(String[]::new);

            //note down seed numbers as longs
            String[] seedStrings = AllLines[0].replaceAll("seeds: ", "").split(" ");
            long [] seedsAsLongs = new long[seedStrings.length];
            for(int i = 0; i < seedStrings.length; i++) {
                seedsAsLongs[i] = Long.parseLong(seedStrings[i]);
            }



            ArrayList<long[]> seedRanges = new ArrayList<>();
            ArrayList<long[]> movedSeedRanges = new ArrayList<>();


            for(int t = 0; t < seedsAsLongs.length;t+=2) {
                seedRanges.add(new long[] {seedsAsLongs[t], seedsAsLongs[t+1]});
            }
            for(int t = 0; t < seedRanges.size(); t++) {
                System.out.println(Arrays.toString(seedRanges.get(t)));
            }


            for(int i = 2; i < AllLines.length; i++) {
                
                //ignore empty spacing lines
                if(AllLines[i].length() == 0) {
                    continue;
                }
                String[] lineAsArray = AllLines[i].split(" ");
                if(Character.isDigit(lineAsArray[0].charAt(0))) {
                    //make along array of the line to use in comparisons
                    //the destination range start, the source range start, and the range length.
                    long[] mapLineAsLongs = new long[lineAsArray.length];
                    for(int j = 0; j < lineAsArray.length; j++) {
                        mapLineAsLongs[j] = Long.parseLong(lineAsArray[j]);
                    }
                    
                    //look through the seeds, ignoring the already moved seeds and see where they are moved into 
                    for(int j = 0; j < seedRanges.size(); j++) {
                        
                        //end & start is inclusive
                        long seedStartIndex = seedRanges.get(j)[0];
                        long seedEndIndex = seedRanges.get(j)[0] + seedRanges.get(j)[1] - 1;
                        
                        //end & start is inclusive
                        long mapStartIndex = mapLineAsLongs[1];
                        long mapEndIndex = mapLineAsLongs[1] + mapLineAsLongs[2] - 1;
                        
                        //check if there is a range overlap

                        //no overlap, continue
                        if(seedStartIndex > mapEndIndex || seedEndIndex < mapStartIndex) {
                            continue;
                        }
                        
                        //exact overlap or seed is contained in map
                        if(seedStartIndex >= mapStartIndex && seedEndIndex <= mapEndIndex) {
                            
                            long offsetFromStart = mapLineAsLongs[0] - mapLineAsLongs[1];
                            movedSeedRanges.add(new long[] {seedRanges.get(j)[0] + offsetFromStart, seedRanges.get(j)[1]});
                            seedRanges.remove(j);
                            j--;
                            continue;
                        }

                        //map is contained in seed
                        if(mapStartIndex >= seedStartIndex && mapEndIndex <= seedEndIndex) {
                            long extraSeedsOnLeft = mapStartIndex - seedStartIndex;
                            long extraSeedsOnRight = seedEndIndex - mapEndIndex;

                            if(extraSeedsOnLeft > 0) {
                                seedRanges.add(new long[] {seedStartIndex, extraSeedsOnLeft});
                            }
                            if(extraSeedsOnRight > 0) {
                                seedRanges.add(new long[] {mapEndIndex + 1, extraSeedsOnRight});
                            }
                            movedSeedRanges.add(new long[] {mapLineAsLongs[0], mapLineAsLongs[2]});

                            seedRanges.remove(j);
                            j--;
                            continue;
                        }

                        if(seedStartIndex >= mapStartIndex && seedStartIndex <= mapEndIndex && seedEndIndex >= mapEndIndex) {
                            long extraSeedsOnRight = seedEndIndex - mapEndIndex;
                            long offsetFromStart = mapLineAsLongs[0] - mapLineAsLongs[1];

                            if(extraSeedsOnRight > 0) {
                                seedRanges.add(new long[] {mapEndIndex + 1, extraSeedsOnRight});
                            }
                            movedSeedRanges.add(new long[] {seedStartIndex + offsetFromStart, mapEndIndex - seedStartIndex + 1 });

                            seedRanges.remove(j);
                            j--;
                            continue;
                        }

                        if(seedEndIndex >= mapStartIndex && seedEndIndex <= mapEndIndex && seedStartIndex <= mapStartIndex) {
                            long extraSeedsOnLeft = mapStartIndex - seedStartIndex;
                            long offsetFromStart = mapLineAsLongs[0] - mapLineAsLongs[1];

                            if(extraSeedsOnLeft > 0) {
                                seedRanges.add(new long[] {seedStartIndex, extraSeedsOnLeft});
                            }
                            movedSeedRanges.add(new long[] {mapStartIndex + offsetFromStart, seedEndIndex - mapStartIndex + 1 });

                            seedRanges.remove(j);
                            j--;
                            continue;
                        }

                    }
                }else{
                    //remaining seedRanges stay in same index so we can just add them to the movedSeedRanges
                    for(int j = 0; j < movedSeedRanges.size(); j++) {
                        seedRanges.add(movedSeedRanges.get(j));
                    }
                    movedSeedRanges.clear();

                }

            
            }
            for(int j = 0; j < movedSeedRanges.size(); j++) {
                seedRanges.add(movedSeedRanges.get(j));
            }
            movedSeedRanges.clear();

            long smallestSeed = Long.MAX_VALUE;
            for(int i = 0; i < seedRanges.size(); i++) {
                if(seedRanges.get(i)[0] < smallestSeed) {
                    smallestSeed = seedRanges.get(i)[0];
                }
            }
            System.out.println(smallestSeed);

            //PART ONE
            /*

            //important to keep track if a seed has been moved already, so we don't move it twice
            boolean[] hasSeedBeenMoved = new boolean[seedsAsLongs.length];
            for(int i = 0; i < hasSeedBeenMoved.length; i++) {
                hasSeedBeenMoved[i] = false;
            }
            for(int i = 2; i < AllLines.length; i++) {
                
                //ignore empty spacing lines
                if(AllLines[i].length() == 0) {
                    continue;
                }
                String[] lineAsArray = AllLines[i].split(" ");
                if(Character.isDigit(lineAsArray[0].charAt(0))) {
                    //make along array of the line to use in comparisons
                    //the destination range start, the source range start, and the range length.
                    long[] mapLineAsLongs = new long[lineAsArray.length];
                    for(int j = 0; j < lineAsArray.length; j++) {
                        mapLineAsLongs[j] = Long.parseLong(lineAsArray[j]);
                    }
                    
                    //look through the seeds, ignoring the already moved seeds and see where they are moved into 
                    for(int j = 0; j < seedsAsLongs.length; j++) {
                        if(hasSeedBeenMoved[j] == false && seedsAsLongs[j] >= mapLineAsLongs[1] && seedsAsLongs[j] < mapLineAsLongs[1] + mapLineAsLongs[2]){
                            hasSeedBeenMoved[j] = true;
                            long offsetFromStart = seedsAsLongs[j] - mapLineAsLongs[1];
                            seedsAsLongs[j] = mapLineAsLongs[0] + offsetFromStart;
                        }
                    }
                }else{
                    //reset the hasSeedBeenMoved array, since we are starting a new map
                    for(int n = 0; n < hasSeedBeenMoved.length; n++) {
                        hasSeedBeenMoved[n] = false;
                    }
                }

            
            }
            //find smallest seed
            long smallestSeed = Long.MAX_VALUE;
            for(int i = 0; i < seedsAsLongs.length; i++) {
                if(seedsAsLongs[i] < smallestSeed) {
                    smallestSeed = seedsAsLongs[i];
                }
            }
            System.out.println(smallestSeed);
            */
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
