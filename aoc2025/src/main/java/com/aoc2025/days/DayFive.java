package com.aoc2025.days;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.aoc2025.Day;

public class DayFive extends Day{

    public DayFive(List<String> input) {
        super(input);
    }

    @Override
    public long partOne() {
        List<Range> ranges = new ArrayList<>();
        List<Long> ids = new ArrayList<>();

        Set<Long> freshIDs = new HashSet<>();

        for(String line: getInput()){
            if(line.contains("-")){
                String[] splitRange = line.split("-");
                ranges.add(new Range(Long.parseLong(splitRange[0]),Long.parseLong(splitRange[1])));
            } else if(!line.equals("")) {
                ids.add(Long.parseLong(line));
            }
        }

        for(long id: ids){
            for(Range range: ranges){
                if(range.containsID(id)){
                    freshIDs.add(id);
                }
            }
        }

        return freshIDs.size();
    }

    @Override
    public long partTwo() {
        List<Range> ranges = new ArrayList<>();
        
        long totalFreshIDs = 0;

        for(String line: getInput()){
            if(line.contains("-")){
                String[] splitRange = line.split("-");
                ranges.add(new Range(Long.parseLong(splitRange[0]),Long.parseLong(splitRange[1])));
            }
        }

        List<Range> mergedRanges = new ArrayList<>();

        do{
            mergedRanges = new ArrayList<>();

            ranges.sort(new Comparator<Range>() {

                @Override
                public int compare(Range arg0, Range arg1) {
                    int dif = 0;

                    if((arg0.getLowEnd() - arg1.getLowEnd()) < 0){
                        dif--;
                    } else if ((arg0.getLowEnd() - arg1.getLowEnd()) > 0){
                        dif++;
                    }

                    return dif;
                }
                
            });

            for (int i = 0; i < ranges.size() - 1; i++) {
                Range currentRange = ranges.get(i);
                Range nextRange = ranges.get(i+1);
                if(rangesOverlap(currentRange, nextRange)){
                    mergedRanges.add(mergeRanges(currentRange, nextRange));
                    ranges.remove(currentRange);
                    ranges.remove(nextRange);
                }
            }

            if (mergedRanges.size() != 0) {
                ranges.addAll(mergedRanges);
            }

        }while(mergedRanges.size() != 0);

        for (int i = 0; i < ranges.size(); i++) {
            totalFreshIDs += (ranges.get(i).getHighEnd() - ranges.get(i).getLowEnd()) + 1;
        }

        return totalFreshIDs;
    }

    private class Range{
        private long lowEnd;
        private long highEnd;

        public Range(long lowEnd, long highEnd){
            this.lowEnd = lowEnd;
            this.highEnd = highEnd;
        }

        public long getLowEnd() {
            return lowEnd;
        }

        public long getHighEnd() {
            return highEnd;
        }

        public boolean containsID(long ID){
            return (ID >= lowEnd && ID <= highEnd);
        }
    }

    private boolean rangesOverlap(Range rangeA, Range rangeB){
        if(rangeB.getLowEnd() > rangeA.getHighEnd() || rangeB.getHighEnd() < rangeA.getLowEnd()){
            return false;
        }

        return true;
    }

    private Range mergeRanges(Range rangeA, Range rangeB){
        return new Range(Math.min(rangeA.getLowEnd(),rangeB.getLowEnd()), Math.max(rangeA.getHighEnd(),rangeB.getHighEnd()));
    }
    
}
