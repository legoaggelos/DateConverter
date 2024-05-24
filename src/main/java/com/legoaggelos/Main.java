package com.legoaggelos;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to get the current date app thing!\nEnter now to convert the current time to another timezone and convert to convert a custom time from one timezone to an other. Type exit to exit program.");
        while(true) {
            String command = scanner.nextLine();
            if(command.equals("exit")){
                return;
            }
            if (command.equals("now")) {
                System.out.println("Enter timezone to convert current time to:");
                ZoneId timezone;
                while (true) {
                    try {
                        timezone = ZoneId.of(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid Timezone! Please enter a valid one:");
                    }
                }
                ZonedDateTime now = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault()).withZoneSameInstant(timezone);
                System.out.println("The current date and time in " + timezone + " is " + convertDateTimeToString(now) + "(YYYY-MM-DD@HH-MM-SS).");
            }
            if (command.equals("convert")) {
                System.out.println("Enter initial timezone:");
                ZoneId timezoneInitial;
                while (true) {
                    try {
                        timezoneInitial = ZoneId.of(scanner.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid Timezone! Please enter a valid one:");
                    }
                }
                System.out.println("Enter timezone to convert initial one to:");
                ZoneId timezoneNew;
                while (true) {
                    try {
                        timezoneNew = ZoneId.of(scanner.nextLine());
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
                        e.printStackTrace();
                    }
                }
                ZonedDateTime newTime = inputTime.withZoneSameInstant(timezoneNew);
                System.out.println("The initial date and time in " + timezoneInitial + " is " + convertDateTimeToStringNew(newTime) + "(YYYY-MM-DD@HH-MM-SS) in " + timezoneNew + ".");
            }
            if((!command.equals("convert")||command.equals("now")||command.equals("exit"))){
                System.out.println("Invalid command.");
            }
            System.out.println("Enter next command:");
        }
    }

    public static String convertDateTimeToString(ZonedDateTime now) {
        String result = now.toString().split("\\.")[0];
        String[] removeT = result.split("T");
        return removeT[0] + "-" + removeT[1];
    }
    public static String convertDateTimeToStringNew(ZonedDateTime now) {
        String[] result = now.toString().split(":");
        String remaining = arrayToString(result).split("\\[")[0].split("\\.")[0].replace('T','@');
        char[] charArray = remaining.toCharArray();
        for (int i = 0; i <3.1; i += 3) {
            charArray=addCharToCharArray(charArray,remaining.indexOf("@")+3+i,':');
        }
        String temp = arrayToString(charArray);
        temp=temp.split("-")[0]+"-"+temp.split("-")[1]+"-"+temp.split("-")[2];
        return temp.replace(":","-");
    }
    public static String arrayToString(String[] array){
        StringBuilder string = new StringBuilder();
        Arrays.stream(array).forEach(string::append);
        return string.toString();
    }
    public static String arrayToString(char[] array){
        StringBuilder string = new StringBuilder();
        for (char c:
             array) {
            string.append(c);
        }
        return string.toString();
    }
    public static char[] addCharToCharArray(char[] array, int index, char c){
        String string = arrayToString(array);
        string=string.replace(string.substring(index), c +string.substring(index));
        return string.toCharArray();
    }
}