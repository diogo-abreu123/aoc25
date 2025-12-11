package com.aoc2025;

import com.aoc2025.days.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AdventOfCode {
    public static void main(String[] args) throws Exception {
        Day day = new DaySix(readInput("d6.txt"));
        System.out.println("Part 1: " + day.partOne());
        System.out.println("Part 2: " + day.partTwo());
    }

    static List<String> readInput(String filename) throws IOException{
        List<String> input = new ArrayList<>();
        try {
            InputStream is = AdventOfCode.class.getClassLoader().getResourceAsStream(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String currentLine = br.readLine();
            while (currentLine != null) {
                input.add(currentLine);
                currentLine = br.readLine();
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return input;
    }
}