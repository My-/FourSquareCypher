package ie.gmit.sw;

import java.util.Random;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;

public class MyUtils {

    public static final String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    public static final IntUnaryOperator doNothing = it -> it;
    public static final IntUnaryOperator replace_J_To_I = it -> it == 'J' || it == 'j' ? it +1 : it; // j +1 = i
    public static final IntUnaryOperator replace_Q_To_K = it -> it == 'Q' || it == 'q' ? it -6 : it; // q -6 = k

    public static final IntPredicate noFilter = it -> true;
    public static final IntPredicate space = it -> it == ' ';
    public static final IntPredicate lettersOnly = Character::isLetter;
    public static final IntPredicate upperCaseLettersOnly = Character::isUpperCase;
    public static final IntPredicate lowerCaseLettersOnly = Character::isLowerCase;


    public static final IntFunction<String> toCharacter = it -> String.valueOf((char)it);


    static String createRandomCharacterString(int length,
                                              IntPredicate filter,
                                              IntUnaryOperator mapper){
        return new Random(System.nanoTime())
                .ints(' ', '~' +1)
                .filter(filter)
                .map(mapper)
                .distinct()
                .limit(length)
                .mapToObj(toCharacter)
                .collect(Collectors.joining());
    }

    /**
     * <hr>
     * Method takes two strings and makes out of them one unique character string.
     * <p><strong>NOTE: Here is no letter 'J' in generated string.</strong></p>
     * <hr>
     * <i>Time complexity: <b>O(n)</b> - linear, <br> TODO: Space complexity: <b>O(?)</b> - ??.</i>
     *
     * @param str1 will be used FIRST for generating keyword.
     * @param str2 will be used to fill missing letters in keyword (order it appears matters).
     * @return unique character string.
     */
    static String createUniqueString(String str1,
                                     String str2,
                                     IntPredicate filter,
                                     IntUnaryOperator mapper ){
        return (str1 + str2)
                .codePoints() // https://stackoverflow.com/a/36878434/5322506
                .filter(filter)
                .map(mapper)
                .distinct()
                .mapToObj(toCharacter)
                .collect(Collectors.joining());
    }



}
