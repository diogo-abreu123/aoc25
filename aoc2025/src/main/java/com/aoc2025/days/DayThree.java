package com.aoc2025.days;

import java.util.ArrayList;
import java.util.List;

import com.aoc2025.Day;

public class DayThree extends Day{

    public DayThree(List<String> input) {
        super(input);
    }

    @Override
    public long partOne() {
        long totalJoltage = 0;

        for(String line: getInput()){
            long currentJoltage = Long.parseLong(getMaxVoltageCombo(line, 2));
            totalJoltage += currentJoltage;
        }

        return totalJoltage;
    }

    @Override
    public long partTwo() {
        long totalJoltage = 0;

        for(String line: getInput()){
            long currentJoltage = Long.parseLong(getMaxVoltageCombo(line, 12));
            totalJoltage += currentJoltage;
        }

        return totalJoltage;
    }

    private String getMaxVoltageCombo(String batteryArray, int arraySize){
        ArrayList<Integer> biggest = new ArrayList<>();

        for(int i = 0; i < arraySize; i++){
            ArrayList<Integer> currentCandidate = new ArrayList<>(biggest);

            for(int j = 0; j < batteryArray.length(); j++){
                ArrayList<Integer> newCandidate = new ArrayList<>(biggest);
                if(!newCandidate.contains(j)){
                    newCandidate.add(j);
                } else {
                    continue;
                }

                if(isNewCandidateBigger(newCandidate, currentCandidate, batteryArray)){
                    currentCandidate = new ArrayList<>(newCandidate);
                }
            }

            if(isNewCandidateBigger(currentCandidate, biggest, batteryArray)){
                biggest = new ArrayList<>(currentCandidate);
            }

        }

        biggest.sort(null);

        return buildBatteryArrayString(biggest, batteryArray);
    }

    private boolean isNewCandidateBigger(List<Integer> newCandidate, List<Integer> oldCandidate, String completeArray){
        newCandidate.sort(null);
        oldCandidate.sort(null);
        String newArray = buildBatteryArrayString(newCandidate, completeArray);
        String oldArray = buildBatteryArrayString(oldCandidate, completeArray);

        return Long.parseLong(newArray) > Long.parseLong(oldArray);
    }

    private String buildBatteryArrayString(List<Integer> batteryArray, String completeArray){
        String batteryArrayString = "";

        for(int battery: batteryArray){
            batteryArrayString += completeArray.charAt(battery);
        }

        return batteryArrayString.equals("") ? "0" : batteryArrayString;
    }
}
