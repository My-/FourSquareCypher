package ie.gmit.sw;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Bigram {

    private char ch1;
    private char ch2;

    private Bigram(){ }

    private Bigram(char ch1, char ch2) {
        this.ch1 = ch1;
        this.ch2 = ch2;
    }

    public static Bigram of(Bigram bigram){
        return new Bigram(bigram.get(0), bigram.get(1));
    }
    public static Bigram of(char[] charr){
        return new Bigram(charr[0], charr[1]);
    }
    public static Bigram of(char ch1, char ch2){
        return new Bigram(ch1, ch2);
    }
    public static Bigram of(char ch){
        return new Bigram(ch, ' ');
    }
    public static Bigram of(String str){
        if( str.length() > 2 ){ throw new IllegalArgumentException("Given string is longer then 2 characters: "+ str); }
        if( str.length() == 0 ){ return Bigram.of(' ', ' '); }
        return str.length() == 2 ? new Bigram(str.charAt(0), str.charAt(1)) : new Bigram(str.charAt(0), ' ');
    }



    public static List<Bigram> toBigrams(String str){
        char[] chArr = prepare(str).toCharArray();
        List<Bigram> R = new ArrayList<>();

        for(int i = 1; i < chArr.length; i+=2){  // create bigrams
            R.add(Bigram.of(chArr[i -1], chArr[i]));
        }

        if( chArr.length % 2 != 0 ){   // if one letter has no pair
            R.add(Bigram.of(chArr[chArr.length -1], ' '));  // ...add space
        }

        return R;
    }

    static Stream<Bigram> toBigrams(IntStream codePoints,
                                    IntPredicate filter,
                                    IntUnaryOperator mapper){

        final MutableCharacter ch = MutableCharacter.of();
        final MutableBool firsElement = MutableBool.of();

        return codePoints
                .filter(it->{
                    if( firsElement.get() ){ ch.changeTo((char)it); firsElement.changeTo(false); return false; }
                    firsElement.changeTo(true); return true;
                })
                .filter(filter)
                .map(mapper)
//                .
                .mapToObj(it-> Bigram.of(ch.get(), (char)it));
    }



    static String prepare(String str){
        return str.codePoints()
                .filter(MyUtils.lettersOnly.or(Character::isSpaceChar))
                .map(Character::toUpperCase)
                .map(MyUtils.replace_J_To_I)
                .map(MyUtils.replace_Q_To_K)
                .mapToObj(MyUtils.toCharacter)
                .collect(Collectors.joining());
    }


    public static String joining(Stream<Bigram> bigrams){
        return bigrams
                .map(Bigram::toString)
                .collect(Collectors.joining());
    }




//    private void createPool(Alphabet abc, int posCh1, int posCh2){
//        if( posCh1 == abc.length() || posCh2 == abc.length() ){ return; }
//
//        pool.put(Bigram.of(abc.get(posCh1)), Bigram.of(abc.get(posCh2)));
//        createPool(abc, posCh1 +1, posCh2);
//        createPool(abc, posCh1 , posCh2 +1);
//
//    }


//    public static StringBuilder toStringBuilder()

    public char get(int n){
        if(0 > n || n > 1 ){ throw new IllegalArgumentException("Wrong parameter ("+ n +")"); }
        return n == 0 ? this.ch1 : this.ch2;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Bigram bigram = (Bigram) o;
//        return ch1 == bigram.ch1 &&
//                ch2 == bigram.ch2;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(ch1, ch2);
//    }

    @Override
    public String toString() {
        return ch1+""+ch2;
    }

//    public StringBuffer toStringBuffer() {
//        return new StringBuffer(ch1+""+ch2);
//    }
//
//    public StringBuilder toStringBuilder() {
//        return new StringBuilder(ch1+""+ch2);
//    }
}
