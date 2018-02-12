package ie.gmit.sw;

import java.util.Arrays;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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


//    public static final IntFunction<String> toCharacter = it -> String.valueOf((char)it);
    public static final IntFunction<String> toCharacter = it -> "" +(char)it;


    public static Collector<Integer, ?, String> charsToString() {
        return Collector.of(
                StringBuilder::new,
                StringBuilder::appendCodePoint,
                StringBuilder::append,
                StringBuilder::toString);
    }


    static String createRandomCharacterString(int length,
                                              IntPredicate filter,
                                              IntUnaryOperator mapper){
        return new Random(System.nanoTime())
                .ints(' ', '~' +1)
                .filter(filter)
                .map(mapper)
                .limit(length)
                // v1 (8.1-8.5 #1M*50char)
//                .mapToObj(toCharacter)
//                .collect(Collectors.joining());
                // v2 (6.8-7.2 #1M*50char)
//                .boxed()
//                .collect(charsToString());
                // v3 (6.4-6.5 #1M*50char)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
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
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }

    /**
     *
     * @param str
     * @param filter
     * @param mapper
     * @return
     */
    static String modifyString(String string,
                               IntPredicate filter,
                               IntUnaryOperator mapper){

        return modifyString(string, filter, mapper, it-> true, it-> it);
    }

    static String modifyString(String string,
                               IntPredicate filter1,
                               IntUnaryOperator mapper1,
                               IntPredicate filter2,
                               IntUnaryOperator mapper2){

        return string.codePoints()
                .filter(filter1)
                .map(mapper1)
                .filter(filter2)
                .map(mapper2)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }


    public static String charStreamToString(IntStream codePoints){
        return codePoints
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }

    // https://stackoverflow.com/a/12297231/5322506
    public static String[] splitStringEvery(String s, int interval) {
        int arrayLength = (int) Math.ceil(((s.length() / (double)interval)));
        String[] result = new String[arrayLength];

        if( s.length() < interval ){ // handle empty string
            return new String[]{s};
        }

        int j = 0;
        int lastIndex = result.length - 1;
        for (int i = 0; i < lastIndex; i++) {
            result[i] = s.substring(j, j + interval);
            j += interval;
        } //Add the last bits.
//        System.out.println(s +" => result: "+ result.length +", last index: "+ lastIndex);
        result[lastIndex] = s.substring(j);

        return result;
    }

    /**
     * Method splits stream element (String) in to smaller elements and joins (flatMap's) them back to stream.
     *
     * @param stream input stream.
     * @param interval element splitting interval.
     * @param mapperBeforeSplit can be use to change/filter stream element/
     * @return flatten stream.
     */
    public static Stream<String> splitStringEvery(Stream<String> stream,
                                                  final int interval,
                                                  UnaryOperator<String> mapperBeforeSplit){
        return stream
                .map(mapperBeforeSplit)
                .map(line-> MyUtils.splitStringEvery(line, interval))
                .flatMap(Arrays::stream);
    }


}
