package com.example.matt.allears;

/**
 * Created by Josh Voskamp.
 */

public class Sorts {
    private static final int LETTER = 0;
    //Sorts the given array, according to the given type
    public static void Sort(Alphabet[] a, int type){
        if(type == LETTER){
            QuickSort(a,0,a.length-1);//Uses QuickSort to order the Alphabet array by Letter
        }else{
            InsertionSort(a);//Uses InsertionSort to order the Alphabet array by Morse Code
        }
    }
    //QuickSort according to Letter
    private static void QuickSort(Alphabet[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        Alphabet v = a[lo];
        int i = lo;
        while (i <= gt) {
            int cmp = a[i].compareLetters(v);
            if(cmp < 0){
                swap(a, lt++, i++);
            }
            else if (cmp > 0){
                swap(a, i, gt--);
            }
            else{
                i++;
            }
        }
        QuickSort(a, lo, lt-1);
        QuickSort(a, gt+1, hi);
    }
    //InsertionSort according to Morse Code
    private static void InsertionSort ( Alphabet[] x) {
        for(int i = 0; i < x.length; i++){
            for(int j = i; j > 0 && less(x[j],x[j-1]); j-- ){
                swap(x,j,j-1);
            }
        }
    }
    //Used in InsertionSort to sort by Morse Code
    private static boolean less(Alphabet a, Alphabet b) {
        return (a.compareCodes(b) < 0);
    }
    //Helper method used to swap the index values of 2 objects within the given object array
    private static void swap(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
