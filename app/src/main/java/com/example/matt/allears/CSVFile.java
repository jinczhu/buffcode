package com.example.matt.allears;

/**
 * Created by Josh Voskamp.
 */

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class CSVFile {
    public static Alphabet[] readCSV(InputStream s){
        Scanner input = new Scanner(s);
        ArrayList<Alphabet> list = new ArrayList<>();
        while (input.hasNextLine()) {//keep reading while there are more lines
            String line = input.nextLine();//read the next line in the file
            String[] splits = line.split(",");
            list.add(new Alphabet(splits[0],splits[1]));//Creates Alphabet object using the letter, and its morse code
        }
        input.close();//close the scanner
        Alphabet[] small = new Alphabet[list.size()];//convert the array list into an array
        list.toArray(small);
        return small;
    }
}