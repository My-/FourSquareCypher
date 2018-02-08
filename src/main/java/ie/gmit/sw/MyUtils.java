package ie.gmit.sw;

import java.util.Random;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;

public class MyUtils {

    public static final String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    public static final IntUnaryOperator replace_J_To_I = ch -> ch == 'J' ? 'I' : ch;
    public static final IntFunction<String> toCharacter = it -> String.valueOf((char)it);


    static String createRandomString(int length){
        return new Random(System.nanoTime())
                .ints('A', 'Z' +1)
                .map(replace_J_To_I)
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
     * @param keyword will be used FIRST for generating keyword.
     * @param alphabet will be used to fill missing letters in keyword (order it appears matters).
     * @return unique character string.
     */
    static String createUniqueString(String keyword, String alphabet ){
        return (keyword + alphabet)
                .codePoints() // https://stackoverflow.com/a/36878434/5322506
                .filter(Character::isLetter)
                .map(Character::toUpperCase)
                .map(replace_J_To_I)
                .distinct()
                .mapToObj(toCharacter)
                .collect(Collectors.joining());
    }

}
