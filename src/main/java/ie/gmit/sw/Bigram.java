package ie.gmit.sw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bigram {

    private char[] bigram = new char[2];

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

    public static List<Bigram> toBigrams(String str){
        char[] chArr = str.toCharArray();
        int size = chArr.length % 2 == 0 ? chArr.length / 2 : chArr.length / 2 +1;
        List<Bigram> R = new ArrayList<>(size);

        for(int i = 0; i < chArr.length; i++){
            if( i % 2 == 0 ){ R.add(Bigram.of(chArr[i], i +1 < chArr.length ? chArr[i +1] : ' ')) ; } // add space to end if string is odd length
        }

        return R;
    }

    public char get(int n){
        if(0 > n || n > 1 ){ throw new IllegalArgumentException("Wrong parameter ("+ n +")"); }
        return this.bigram[n];
    }

    @Override
    public String toString() {
        return "Bigram{" + Arrays.toString(bigram) +'}';
    }
}
