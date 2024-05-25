package com.legoaggelos.util;

import java.util.Arrays;

public class DateFormattingUtils {
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
