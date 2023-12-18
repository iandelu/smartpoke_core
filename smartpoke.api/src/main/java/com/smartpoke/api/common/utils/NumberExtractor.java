package com.smartpoke.api.common.utils;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumberExtractor {

    static public List<Double> getDoubles(String input) {
        Scanner scanner = new Scanner(input);
        List<Double> numbers = new ArrayList<>();

        while (scanner.hasNext()) {
            if (scanner.hasNextDouble()) {
                numbers.add(scanner.nextDouble());
            } else {
                scanner.next();
            }
        }

        return numbers;
    }

    static public List<Integer> getInts(String input) {
        Scanner scanner = new Scanner(input);
        List<Integer> numbers = new ArrayList<>();

        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                numbers.add(scanner.nextInt());
            } else {
                scanner.next();
            }
        }

        return numbers;
    }

    static public Double getDoublePosition(String input, Integer index) {
        Scanner scanner = new Scanner(input);
        List<Double> numbers = new ArrayList<>();

        while (scanner.hasNext()) {
            if (scanner.hasNextDouble()) {
                numbers.add(scanner.nextDouble());
            } else {
                scanner.next();
            }
        }

        if (!numbers.isEmpty() && numbers.get(index) != null){
            return numbers.get(index);
        }
        return null;
    }

    static public Integer getIntPosition(String input, Integer index) {
        if (input != null && !input.isEmpty()){
            Scanner scanner = new Scanner(input);
            List<Integer> numbers = new ArrayList<>();

            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    numbers.add(scanner.nextInt());
                } else {
                    scanner.next();
                }
            }
            if (!numbers.isEmpty() && numbers.get(index) != null){
                return numbers.get(index);
            }
        }
        return null;
    }
}
