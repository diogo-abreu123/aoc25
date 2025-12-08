package com.aoc2025.days;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

import com.aoc2025.Day;

public class DayFour extends Day{

    public DayFour(List<String> input) {
        super(input);
    }

    @Override
    public long partOne() {
        int accessibleRolls = 0;

        for(int y = 0; y < getInput().size(); y++){
            for(int x = 0; x < getInput().get(y).length(); x++){
                Point currentPosition = new Point(x,y);

                if(getInput().get(y).charAt(x) == '@' && checkNeighbours(currentPosition, getInput()) < 4){
                    accessibleRolls++;
                }
            }
        }

        return accessibleRolls;
    }

    @Override
    public long partTwo() {
        List<String> localDiagram = new ArrayList<>(getInput());

        int totalaccessibleRolls = 0;
        int accessibleRolls = 0;

        do{
            accessibleRolls = 0;
            for(int y = 0; y < localDiagram.size(); y++){
                for(int x = 0; x < localDiagram.get(y).length(); x++){
                    Point currentPosition = new Point(x,y);

                    if(localDiagram.get(y).charAt(x) == '@' && checkNeighbours(currentPosition, localDiagram) < 4){
                        accessibleRolls++;
                        String row = localDiagram.get(y);
                        String newRow = row.substring(0, x) + "." + row.substring(x + 1);
                        localDiagram.set(y, newRow);
                    }
                }
            }

            totalaccessibleRolls += accessibleRolls;
        }while(accessibleRolls != 0);

        return totalaccessibleRolls;
    }

    private int checkNeighbours(Point position, List<String> diagram){
        int rolls = 0;

        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(i == 0 && j==0){
                    continue;
                } else{
                    if(position.x + i < 0 || 
                       position.x + i >= diagram.get(0).length() || 
                       position.y + j < 0 || 
                       position.y + j >= diagram.size()){
                        continue;
                    } else {
                        if(diagram.get(position.y + j).charAt(position.x + i) == '@'){
                            rolls++;
                        }
                    }
                }
            }
        }

        return rolls;
    }


}
