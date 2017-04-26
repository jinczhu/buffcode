package com.example.matt.allears;

/**
 * Created by Josh Voskamp.
 */

public class Alphabet{
    private String code, letter;
    Alphabet(String letter, String code){
        this.letter = letter;
        this.code = code;
    }
    public String getLetter() {
        return letter;
    }
    public String getCode() {
        return code;
    }
    public int compareLetters(Alphabet b) {
        return this.getLetter().compareTo(b.getLetter());
    }
    public int compareCodes(Alphabet b) {
        return this.getCode().compareTo(b.getCode());
    }
}