package com.smartpoke.api.common.utils;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    static public Double getDouble(String input) {
        if (StringUtils.hasText(input)) {
            Scanner scanner = new Scanner(input);
            while (scanner.hasNext()) {
                if (scanner.hasNextDouble()) {
                    return scanner.nextDouble();
                } else {
                    scanner.next();
                }
            }
        }
        return null;
    }

    static public Double getDouble(String input, Double defaultValue) {
        Double value = getDouble(input);
        return value != null ? value : defaultValue;
    }

    static public Integer getInt(String input, Integer defaultValue) {
        Integer value = getInt(input);
        return value != null ? value : defaultValue;
    }

    static public Integer getInt(String input) {
        if (StringUtils.hasText(input)) {
            Scanner scanner = new Scanner(input);
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    return scanner.nextInt();
                } else {
                    scanner.next();
                }
            }
        }
        return null;
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

    public static int sumTimes(String text) {
        Pattern pattern = Pattern.compile("(\\d+)\\s*(horas|h|minutos|min|mins)\\s+");
        Matcher matcher = pattern.matcher(text);

        int totalMinutes = 0;
        while (matcher.find()) {
            int number = Integer.parseInt(matcher.group(1));
            String unit = matcher.group(2);

            if (unit.equalsIgnoreCase("horas") || unit.equalsIgnoreCase("h")) {
                totalMinutes += number * 60;
            } else if (unit.equalsIgnoreCase("minutos") || unit.equalsIgnoreCase("min") || unit.equals("'")) {
                totalMinutes += number;
            }
        }

        return totalMinutes;
    }
}
