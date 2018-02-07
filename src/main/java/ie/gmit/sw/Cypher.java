package ie.gmit.sw;

import java.util.*;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;

public class Cypher {

    private static final String KEY = "THEQUICKBROWNFXJMPSVLAZYDG";
    private static final String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final IntUnaryOperator replace_J_To_I = ch -> ch == 'J' ? 'I' : ch;
    private static final IntFunction<String> toCharacter = it -> String.valueOf((char)it);

    private Map<Character, Position> keywordData = Collections.synchronizedMap(new HashMap<>(25));
    private char[] keyword;

    private Cypher(){ }



    /**
     * <hr>
     * Fills keywordData from given char[];
     * <hr>
     * <i>Time complexity: <b>O(n)</b> - linear, <br> TODO: Space complexity: <b>O(1)</b> - ??.</i>
     *
     * @param keyword is data which should be filled to keywordData field.
     * @param keywordData will be filed with data from keyword.
     */
    static void makeKeywordData(char[] keyword, Map<Character, Position> keywordData){
        if( keyword.length != 25 ){ throw new IllegalArgumentException("Invalid keyword length: "+ keyword.length); }
        for( int i = 0; i < keyword.length; i++){ keywordData.putIfAbsent(keyword[i], Position.of(i / 5, i % 5)); }
        if( keywordData.size() != 25 ){ throw new IllegalArgumentException("Invalid keyword. Probably duplicates: "+ keyword); }
    }


    /**
     * <hr>
     * Generates keyword using given word(s).
     * <hr>
     * <i>Time complexity: <b>O(n)</b> - linear, <br> TODO: Space complexity: <b>O(?)</b> - ??.</i>
     *
     * @param words is words which will be used to generate keyword. Keyword will start with it.
     * @param useKey uses special key (mixed letters), if false uses alphabet (ordered letters). Keyword will end with it.
     * @return generated keyword.
     */
    static String generateKeyword(boolean useKey, String...words){
        return createKeyword(useKey ? KEY : ABC, words);
    }

//    private static String cleanKeyword_v1(String...words){
//        return String
//                .join("", words)
//                .toUpperCase()
//                .replaceAll("J", "I")
//                .replaceAll("[^A-Z]", "") // all except letters. https://regex101.com/
//                .codePoints() // https://stackoverflow.com/a/36878434/5322506
//                .distinct()
//                .mapToObj(it -> String.valueOf((char)it))
//                .collect(Collectors.joining());
//    }

    /**
     * <hr>
     * Method creates unique, 25 character long keyword.
     * <hr>
     * <i>Time complexity: <b>O(n)</b> - linear, <br> TODO: Space complexity: <b>O(?)</b> - ??.</i>
     *
     * @param alphabet will be used to fill missing letters in keyword (order it appears matters).
     * @param keyWords will be used FIRST for generating keyword.
     * @return unique 25 character long created keyword.
     */
    static String createKeyword(String alphabet, String...keyWords ){
        return (String.join("", keyWords) + alphabet)
                    .codePoints() // https://stackoverflow.com/a/36878434/5322506
                    .filter(Character::isLetter)
                    .map(Character::toUpperCase)
                    .map(replace_J_To_I)
                    .distinct()
                    .mapToObj(toCharacter)
                    .collect(Collectors.joining());
    }



    /**
     * <hr>
     * <p>Method gets letter from alphabet matrix.</p>
     * <ul style="list-style-type:none">
     *      <li>A B C D E</li>
     *      <li>F G H I K</li>
     *      <li>L M N O P</li>
     *      <li>Q R S T U</li>
     *      <li>V W X Y Z</li>
     * </ul>
     * <p><strong>NOTE: Here is no letter 'J' in Alphabet.</strong></p>
     * <hr>
     * <i>Time complexity: <b>O(1)</b> - constant, <br> Space complexity: <b>O(0)</b> - none.</i>
     *
     * @param x letters X position in Alphabet matrix starting from 0 ("zero").
     * @param y letters Y position in Alphabet matrix starting from 0 ("zero").
     * @return letter from matrix at position (x,y) or "space" if not valid X or Y.
     */
    static char getAlphabetLetter(int x, int y){
        if(0 > x || x > 4 || 0 > y || y > 4){ return ' '; } // if X or Y are off limits
        return (char)(5 * x + y + 65 + (x > 1 || x == 1 && y == 4 ? 1 : 0));
    }

    /**
     * <hr>
     * <p>Method gets given letter Position (x,y) from alphabet matrix.</p>
     * <ul style="list-style-type:none">
     *      <li>A B C D E</li>
     *      <li>F G H I K</li>
     *      <li>L M N O P</li>
     *      <li>Q R S T U</li>
     *      <li>V W X Y Z</li>
     * </ul>
     * <p><strong>NOTE: Here is no letter 'J' in Alphabet.</strong></p>
     * <hr>
     * <i>Time complexity: <b>O(1)</b> - constant, <br> Space complexity: <b>O(0)</b> - none.</i>
     *
     * @param letter letters position we look in matrix.
     * @return Position of given letter or Optional.empty() if not found.
     */
    static Optional<Position> getAlphabetLettersPosition(char letter){
        char ch = Character.toUpperCase(letter);
        if( !Character.isLetter(letter) || ch == 'J'){ return Optional.empty(); }

        ch -= 'A' + (ch > 'J' ? 1 : 0);
        return Optional.of( Position.of(ch / 5, ch % 5) );
    }

//    static Bigram incript(Bigram bigram){
//        char ch1 = bigram.get(1), ch2 = bigram.get(2);
//
//        return bigram;
//    }
}
