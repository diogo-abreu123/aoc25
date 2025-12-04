package com.aoc2025;

import java.util.List;

public abstract class Day {

    private List<String> input;


    public Day(List<String> input){
        this.input = input;
    }

    public List<String> getInput() {
        return input;
    }

    public void setInput(List<String> input) {
        this.input = input;
    }

    public abstract int partOne();

    public abstract int partTwo();
}
