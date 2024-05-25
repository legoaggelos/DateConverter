package com.legoaggelos;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Scanner;

import static com.legoaggelos.util.DateFormattingUtils.addCharToCharArray;
import static com.legoaggelos.util.DateFormattingUtils.arrayToString;
import static com.legoaggelos.util.DateUtils.*;
import static com.legoaggelos.util.DateUtils.customZoneId;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to get the current date app thing!\nEnter now to convert the current time to another timezone and convert to convert a custom time from one timezone to an other. Type exit to exit program.");
        while(true) {
            String command = scanner.nextLine().trim();
            if(command.equalsIgnoreCase("exit")){
                return;
            }else if (command.equalsIgnoreCase("now")) {
                System.out.println("Enter timezone to convert current time to:");
                ZoneId timezone;
                while (true) {
                    try {
                        timezone = customZoneId((scanner.nextLine()));
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid Timezone! Please enter a valid one:");
                    }
                }
                ZonedDateTime now = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault()).withZoneSameInstant(timezone);
                System.out.println("The current date and time in " + timezone + " is " + convertDateTimeToString(now) + "(YYYY-MM-DD@HH-MM-SS).");
            }else if (command.equals("convert")) {
                System.out.println("Enter initial timezone:");
                ZoneId timezoneInitial;
                while (true) {
                    try {
                        timezoneInitial = customZoneId(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid Timezone! Please enter a valid one:");
                    }
                }
                System.out.println("Enter timezone to convert initial one to:");
                ZoneId timezoneNew;
                while (true) {
                    try {
                        timezoneNew = customZoneId(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid Timezone! Please enter a valid one:");
                    }
                }
                ZonedDateTime inputTime;
                System.out.println("Enter date and time in YEAR.MONTH.DAY.HOUR.MIN.SECOND");
                while (true) {
                    String[] input = scanner.nextLine().split("\\.");
                    if (input.length != 6) {
                        System.out.println("Date and time was formatted wrong. Try again.");
                        continue;
                    }
                    try {
                        inputTime = ZonedDateTime.of(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]), Integer.parseInt(input[4]), Integer.parseInt(input[5]), 0, timezoneInitial);
                        break;
                    } catch (Exception e) {
                        System.out.println("Date and time was formatted wrong. Try again.");
                    }
                }
                ZonedDateTime newTime = inputTime.withZoneSameInstant(timezoneNew);
                System.out.println("The initial date and time in " + timezoneInitial + " is " + convertDateTimeToStringNew(newTime) + "(YYYY-MM-DD@HH-MM-SS) in " + timezoneNew + ".");
            } else{
                System.out.println("Invalid command.");
            }
            System.out.println("Enter next command:");
        }
    }



}