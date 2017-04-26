package com.example.matt.allears;

/**
 * Created by Josh Voskamp.
 */

public class Searches {
    private static final int LETTER = 0;
    //Implements BinarySearch which can be used for searching for a letter or morse code depending on they type entered
    public static int BinarySearch(String key, Alphabet[] a, int type) {
        int low = 0;
        int high = a.length - 1;
        int mid;
        while( low <= high )
        {
            mid = ( low + high ) / 2;
            int cmp = Compare(key, a[mid], type);
            if(cmp < 0 ){
                low = mid + 1;
            }else if(cmp > 0 ){
                high = mid - 1;
            }else{
                return mid;
            }
        }
        return -1;
    }
    //Returns the correct comparison value depending on which type was selected
    private static int Compare(String s, Alphabet a, int type){
        if(type == LETTER){
            return a.getLetter().compareTo(s);
        }else{
            return a.getCode().compareTo(s);
        }
    }
}