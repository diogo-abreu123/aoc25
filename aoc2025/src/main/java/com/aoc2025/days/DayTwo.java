package com.aoc2025.days;

import java.util.List;

import com.aoc2025.Day;

public class DayTwo extends Day {

    public DayTwo(List<String> input) {
        super(input);
    }

    @Override
    public long partOne() {
        long invalidIdSum = 0;

        String input = getInput().get(0);
        String[] ranges = input.split(",");

        for(String range: ranges){
            String[] splitRange = range.split("-");

            String lowerLimit = splitRange[0];
            String upperLimit = splitRange[1];

            for(long i = Long.parseLong(lowerLimit); i <= Long.parseLong(upperLimit); i++){
                String currentId = "" + i;
                boolean areBothPartsEqual = currentId.substring(0,currentId.length()/2).equals(currentId.substring(currentId.length()/2));

                if(currentId.length() % 2 == 0 && areBothPartsEqual){
                    invalidIdSum += Long.parseLong(currentId);
                }
            }
        }

        return invalidIdSum;
    }

    @Override
    public long partTwo() {
        long invalidIdSum = 0;

        String input = getInput().get(0);
        String[] ranges = input.split(",");

        for(String range: ranges){
            String[] splitRange = range.split("-");

            String lowerLimit = splitRange[0];
            String upperLimit = splitRange[1];

            for(long i = Long.parseLong(lowerLimit); i <= Long.parseLong(upperLimit); i++){
                String currentId = "" + i;

                if(currentId.length() > 1 && hasPattern(currentId)){
                    invalidIdSum += Long.parseLong(currentId);
                }
            }
        }

        return invalidIdSum;
    }

    public boolean hasPattern(String word){
        boolean hasPattern = false;

        for(int i = 1; i <= word.length()/2; i++){
            String pattern = word.substring(0, i);

            if(word.length() % pattern.length() == 0){
                hasPattern = true;
                int index = 0;

                for(int j = i; j <= word.length(); j += i){
                    if(!pattern.equals(word.substring(index,j))){
                        hasPattern = false;
                        break;
                    }
                    index += i;
                }

                if(!hasPattern){
                    continue;
                } else {
                    break;
                }
            }
        }

        return hasPattern;
    }
    
}
