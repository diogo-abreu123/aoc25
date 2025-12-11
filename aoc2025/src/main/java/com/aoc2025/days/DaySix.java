package com.aoc2025.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.aoc2025.Day;

public class DaySix extends Day{

    public DaySix(List<String> input) {
        super(input);
    }

    @Override
    public long partOne() {
        List<Operation> operations = new ArrayList<>();
        long total = 0;

        ArrayList<String> firstLine = new ArrayList<>(Arrays.asList(getInput().get(0).split(" ")));
        for (int i = 0; i < firstLine.size(); i++) {
            if(firstLine.get(i) == ""){
                firstLine.remove(i);
                i--;
            }
        }

        for (int i = 0; i < firstLine.size(); i++) {
            operations.add(new Operation());
        }

        for(String line: getInput()){
            ArrayList<String> cleanLine = new ArrayList<>(Arrays.asList(line.split(" ")));
            for (int i = 0; i < cleanLine.size(); i++) {
                if(cleanLine.get(i) == ""){
                    cleanLine.remove(i);
                    i--;
                }
            }

            for (int i = 0; i < cleanLine.size(); i++) {
                if("+*".contains(cleanLine.get(i))){
                    operations.get(i).setOperation(cleanLine.get(i).charAt(0));
                } else {
                    operations.get(i).addOperand(Long.parseLong(cleanLine.get(i)));
                }
            }
        }

        for (Operation operation : operations) {
            total += operation.calculate();
        }

        return total;
    }

    @Override
    public long partTwo() {
        long total = 0;
        int i = 0;

        while (i < getInput().get(4).length()) {
            int distance = distanceToNextSign(getInput().get(4), i);
            List<Long> operands = new ArrayList<>();

            for (int j = 0; j < distance - 1; j++) {
                String operand = ("" + getInput().get(0).charAt(i + j) + getInput().get(1).charAt(i + j) + getInput().get(2).charAt(i + j) + getInput().get(3).charAt(i + j)).replaceAll("\\s+","");
                if("".equals(operand)){
                    operands.add(0L);
                } else {
                    operands.add(Long.parseLong(operand));
                }
            }

            Operation operation = new Operation(operands, getInput().get(4).charAt(i));

            System.out.println(operation);

            total += operation.calculate();
            i += distance;
        }

        return total;
    }

    private int distanceToNextSign(String operationsString, int position){
        int distance = 1;

        while((position + distance) < operationsString.length() && operationsString.charAt(position + distance) == ' '){
            distance++;
        }

        return distance;
    }

    private class Operation{
        private List<Long> operands;
        private char operation;

        public Operation(){
            operands = new ArrayList<>();
            operation = '+';
        }

        public Operation(List<Long> operands, char operation){
            this.operands = operands;
            this.operation = operation;
        }

        public long calculate(){
            long total = 0;

            switch (operation){
                case '+': 
                    total = 0;
                    break;
                case '*':
                    total = 1;
                    break;
                
            }

            for (Long operand : operands) {
                switch (operation){
                    case '+': 
                        total += operand;
                        break;
                    case '*':
                        total *= operand;
                    
                }
            }

            return total;
        }

        public void addOperand(long operand) {
            this.operands.add(operand);
        }

        public void setOperation(char operation) {
            this.operation = operation;
        }

        @Override
        public String toString() {
            return "Operation [operands=" + operands + ", operation=" + operation + "]";
        }
    }
}
