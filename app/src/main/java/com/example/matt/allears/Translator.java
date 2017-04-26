package com.example.matt.allears;

/**
 * Created by Josh Voskamp.
 */

import java.util.ArrayList;


public class Translator {
    private Alphabet[] language;
    private static final int LETTER = 0, CODE = 1, UNIT = 100;//By modding the unit size you can increase or decrease the length of a dot/dash currently set for 100ms
    private static final int TOLERANCE = UNIT;//allows you to be off slightly in your taps
    //Creates a translator using the given Alphabet array as its language
    Translator(Alphabet[] list){
        this.language = list;
    }
    //Returns the morse code translation of the given string
    public String TextToMorseCode(String message){
        message = message.toUpperCase();//Converts all input strings to upper case(There is no case in morse code)
        String code = "";
        //loops through each character and converts it to its morse code representation
        for(int i = 0; i<message.length(); i++){
            code = code + CharToCode(message.charAt(i));
        }
        return code;
    }
    //Returns the english translation of the given string of code
    public String MorseCodeToText(String code){
        String message = "";
        String[] letter = code.split(",");
        //loops through each segment of morse code that represents a letter and finds its matching english letter
        for(String i : letter){
            message = message + CodeToChar(i);
        }
        return message;
    }
    //Returns an android vibration pattern, that represents the given string of morse code
    public long[] CodeToTime(String code){
        ArrayList<Integer> list = new ArrayList<>();
        int N;//Size
        list.add(0);//Adds a zero pause at the start
        //loops through each character in a string of morse code
        for(int i = 0; i<code.length(); i++){
            String s  = String.valueOf(code.charAt(i));
            switch (s){
                case "-":
                    list.add(3*UNIT);//Dash
                    list.add(UNIT);//Default Pause
                    break;
                case ".":
                    list.add(UNIT);//Dot
                    list.add(UNIT);//Default Pause
                    break;
                case ",":
                    N = list.size();
                    list.set(N-1, list.get(N-1)+2*UNIT);//Adds 2 UNITS for each comma onto the previous pause time
                    break;
                case " ":
                    N = list.size();
                    list.set(N-1, list.get(N-1)+4*UNIT);//Add just 4 UNITS for the space because of the comma and the default pause, total pause time is now 7 UNITS
                    i++;//To skip over the following comma
                    break;
                case "/":
                    N = list.size();
                    list.set(N-1, list.get(N-1)+7*UNIT);//Add 7 UNIT for period, because of the comma and the default pause, total pause time is now 10 UNITS
                    i = i+3;//To skip over the following commas
                    break;
            }
        }
        //Converts the ArrayList into an array so that it can be used as an android vibration pattern
        long[] pattern = new long[list.size()];
        for(int i = 0; i<list.size();i++){
            pattern[i] = list.get(i);
        }
        return pattern;
    }
    //Converts the given ArrayList of pause,tap times into morse code and returns the morse code
    public String TimesToCode(ArrayList<Integer> time){
        String code = "";
        //loops through each time in the given ArrayList
        for(int i = 0; i<time.size();){
            code = code + PausesToCode(time.get(i));//translates the pause times
            i++;
            if(i < time.size()){//prevents errors cause by the ArrayLists ending with a pause time
                code = code + TapsToCode(time.get(i));//translates the tap times
                i++;
            }
        }
        return code;
    }
    //Converts the given int of tap time into the correct morse code
    private String TapsToCode(int i){
        if(i > 0 && i <= UNIT+TOLERANCE){
            return ".";
        }else if(i > 3*UNIT-TOLERANCE && i <= 3*UNIT+TOLERANCE){
            return "-";
        }else if(i == 0){
            return "";//empty string if there is somehow a zero tap
        }else{
            return "*";//* not a recognized character
        }
    }
    private String PausesToCode(int i){
        if(i >= 3*UNIT-TOLERANCE && i < 7*UNIT-TOLERANCE){
            return ",";
        }else if(i >= 7*UNIT-TOLERANCE && i <= 7*UNIT+TOLERANCE){
            return ", ,";
        }else if(i > 7*UNIT+TOLERANCE){
            return ",/, ,";//return / and a space to allow for a period followed by a space for nicer output.
        }else{
            return "";//empty string
        }
    }
    //Converts the given char into morse code
    private String CharToCode(char t){
        String s = String.valueOf(t);
        if(s.equals(" ")){//returns a space if the given char is a space
            return " ,";
        }
        Sorts.Sort(language,LETTER);//Sorts the language according to letter for BinarySearch
        int i = Searches.BinarySearch(s,language,LETTER);//BinarySeach to find the translation of the given char
        if(i != -1){//if the binary search finds the translation return it
            return language[i].getCode() + ",";//return the translation of char t followed by a comma
        }
        if(s.equals(".") || s.equals("?") || s.equals("!")){//if there is any major punctuation
            return "/,";//return a / followed by a comma
        }
        return "*,";//unrecognized char returns * followed by a comma
    }
    //Converts the given string of morse code to english letters
    private String CodeToChar(String s){
        if(s.equals(" ")){//checks if there is a space
            return " ";//returns a space
        }
        Sorts.Sort(language,CODE);//Sorts according to code for BinarySearch
        int i = Searches.BinarySearch(s,language,CODE);//BinarySeach to find the translation of the given string
        if(i != -1){//if the binary search finds the translation return it
            return language[i].getLetter();
        }
        if(s.equals("/")){
            return ".";//return a period
        }
        return "*";//unrecognized char returns *
    }
}