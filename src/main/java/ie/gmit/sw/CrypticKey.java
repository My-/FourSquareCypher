package ie.gmit.sw;

import java.util.*;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;

public class CrypticKey implements CharacterKey{

//    private Map<Character, Position> mapKey = Collections.synchronizedMap(new HashMap<>(25));
    private Map<Character, Position> mapKey = new HashMap<>();
    private char[] charKey;
    private int matrixSize;

    private  CrypticKey(){ }

    private CrypticKey(String keyword, Alphabet alphabet){
        this.charKey = MyUtils.createUniqueString(
                keyword, alphabet.toString(), MyUtils.noFilter, MyUtils.doNothing).toCharArray();
        this.matrixSize = alphabet.getMatrixSize();
        for( int i = 0; i < charKey.length; i++){ mapKey.putIfAbsent(charKey[i], Position.of(i / 5, i % 5)); }
    }


    /**
     * Factory method to create key from given keyword and given alphabet.
     * NOTE: parameter order maters.
     * @param keyword will appear first.
     * @param alphabet will be used to fill missing letters.
     * @return new instance of CrypticKey class.
     */
    public static CrypticKey of(String keyword, Alphabet alphabet){
        return new CrypticKey(keyword, alphabet);
    }

    /**
     * Factory method to create random key from standard alphabet.
     * @return new instance of random CrypticKey class.
     */
    public static CrypticKey of(){
        IntPredicate filter = MyUtils.upperCaseLettersOnly.or(MyUtils.space);
        IntUnaryOperator mapper = MyUtils.replace_J_To_I.andThen(MyUtils.replace_Q_To_K);

        return new CrypticKey(MyUtils.createRandomCharacterString(15, filter, mapper), Alphabet.of());
    }

    /**
     * Gets position of the given letter.
     * @param letter given letter.
     * @return position of the given letter.
     */
    @Override
    public Optional<Position> get(char letter){
        return Optional.ofNullable(this.mapKey.get(letter));
    }

    /**
     * Gets letter from given position.
     * @param position given position.
     * @return character at given position.
     */
    @Override
    public char get(Position position){
        int i = position.getX() * matrixSize + position.getY();
        return  this.charKey[i];
    }


    public char get(int x, int y){
        int i = x * matrixSize + y;
        return  this.charKey[i];
    }

    // TODO: Use only for debugging/testing.
    String showKey() {
        return String.valueOf(charKey);
    }

    @Override
    public String toString() {
        return this.showKey();
    }
}