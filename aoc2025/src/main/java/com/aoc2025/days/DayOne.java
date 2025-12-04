package com.aoc2025.days;

import com.aoc2025.Day;
import java.util.List;

public class DayOne extends Day{

    private int currentPos;

    public DayOne(List<String> input) {
        super(input);
        currentPos = 50;
    }

    @Override
    public long partOne() {
        int zeroCount = 0;

        for(String line: getInput()){
            char direction = line.charAt(0);
            int distance = Integer.parseInt(line.substring(1));

            if(direction == 'L'){
                currentPos = (currentPos - distance) % 100;
                if (currentPos < 0) currentPos = 100 + currentPos;
            } else if(direction == 'R'){
                currentPos = (currentPos + distance) % 100;
            }

            if(currentPos == 0){
                zeroCount++;
            }
        }

        return zeroCount;
    }

    @Override
    public long partTwo() {
        int zeroCount = 0;
        currentPos = 50;
        
        for(String line: getInput()){
            char direction = line.charAt(0);
            int distance = Integer.parseInt(line.substring(1));

            int passBys = 0;

            if(direction == 'L'){
                if(currentPos <= distance && currentPos != 0) passBys++;
                passBys += Math.abs(currentPos - distance) / 100;
                currentPos = (currentPos - distance) % 100;
                if (currentPos < 0) currentPos += 100;
            } else if(direction == 'R'){
                passBys = Math.abs(currentPos + distance) / 100;
                currentPos = (currentPos + distance) % 100;
            }

            zeroCount += passBys;
        }

        return zeroCount;
    }
    
}
