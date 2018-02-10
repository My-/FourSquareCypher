package ie.gmit.sw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bigram {

    private char[] bigram = new char[2];

    private Bigram(){ }

    private Bigram(char ch0, char ch1) {
        this.bigram = new char[]{ch0, ch1};
    }

    public static Bigram of(Bigram bigram){
        return new Bigram(bigram.get(0), bigram.get(1));
    }
    public static Bigram of(char[] charr){
        return new Bigram(charr[0], charr[1]);
    }
    public static Bigram of(char ch0, char ch1){
        return new Bigram(ch0, ch1);
    }

    public static List<Bigram> toBigrams(String str, Alphabet alphabet){
        char[] chArr = str.toCharArray();
        List<Bigram> R = new ArrayList<>();
        int bigramFilled = 0;
        char[] tempBigram = new char[2];

        for(int i = 0; i < chArr.length; i++){  // For each letter...

            if( alphabet.contains(chArr[i]) ){  // if exist in alphabet...
                 tempBigram[bigramFilled] = chArr[i];
                 bigramFilled++;
            }else if( alphabet.contains(Character.toUpperCase(chArr[i])) ){
                tempBigram[bigramFilled] = Character.toUpperCase(chArr[i]);
                bigramFilled++;
            }else if( alphabet.contains(Character.toLowerCase(chArr[i])) ){
                tempBigram[bigramFilled] = Character.toLowerCase(chArr[i]);
                bigramFilled++;
            }else{
//                System.out.println("Letter not in alphabet: "+ chArr[i]);
            }

            if(bigramFilled > 1){          // if has two valid letters...
                R.add(Bigram.of(tempBigram));
                bigramFilled = 0;
            }
        }

        if( bigramFilled == 1 ){   // if has one letter out of two...
            tempBigram[bigramFilled] = ' ';  // ...add space
            R.add(Bigram.of(tempBigram));
        }

        return R;
    }

//    public static StringBuilder toStringBuilder()

    public char get(int n){
        if(0 > n || n > 1 ){ throw new IllegalArgumentException("Wrong parameter ("+ n +")"); }
        return this.bigram[n];
    }

    @Override
    public String toString() {
        return String.valueOf(bigram);
    }
}
