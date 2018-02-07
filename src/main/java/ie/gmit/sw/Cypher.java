package ie.gmit.sw;

import java.util.*;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;

public class Cypher {

    private CrypticKey key_one;
    private CrypticKey key_two;


    private Cypher(){ }

    /**
     * <hr>
     * Factory method for creating new Cypher instance.
     * <hr>
     * <i>TODO:Time complexity: <b>O(?)</b> - ?, <br>  Space complexity: <b>O(1)</b> - ??.</i>
     *
     * @param keyword_one will be used for creating cyphers key_one.
     * @param keyword_two will be used for creating cyphers key_two.
     * @param alphabet will be used to populate missing letters (order maters).
     * @return new Cypher instance
     */
    public static Cypher of(String keyword_one, String keyword_two, String alphabet){
        Cypher cypher = new Cypher();
        cypher.key_one = createKey(keyword_one, alphabet);
        cypher.key_two = createKey(keyword_two, alphabet);

        return cypher;
    }

    /**
     * <hr>
     * Factory method for creating new Cypher instance.
     * Uses normal alphabet.
     * <hr>
     * <i>TODO:Time complexity: <b>O(?)</b> - ?, <br>  Space complexity: <b>O(1)</b> - ??.</i>
     *
     * @param keyword_one will be used for creating cyphers key_one.
     * @param keyword_two will be used for creating cyphers key_two.
     * @return new Cypher instance
     */
    public static Cypher of(String keyword_one, String keyword_two){
        return Cypher.of(keyword_one, keyword_two, ABC);
    }

    /**
     * <hr>
     * Factory method for creating new Cypher instance.
     * Uses normal alphabet.
     * <hr>
     * <i>TODO:Time complexity: <b>O(?)</b> - ?, <br>  Space complexity: <b>O(1)</b> - ??.</i>
     *
     * @return new Cypher instance
     */
    public static Cypher of(){
        return Cypher.of(createRandonString(25), createRandonString(25), ABC);
    }

    /**
     * <hr>
     * Fills key_one from given char[];
     * <hr>
     * <i>Time complexity: <b>O(n)</b> - linear, <br> TODO: Space complexity: <b>O(1)</b> - ??.</i>
     * 
     * @param keyword is data which should be filled to key_one field.
     * @param key will be filed with data from keyword.
     */
    static void createKey(char[] keyword, Map<Character, Position> key){
        if( keyword.length != 25 ){ throw new IllegalArgumentException("Invalid keyword length: "+ keyword.length); }
        for( int i = 0; i < keyword.length; i++){ key.putIfAbsent(keyword[i], Position.of(i / 5, i % 5)); }
        if( key.size() != 25 ){ throw new IllegalArgumentException("Invalid keyword. Probably duplicates: "+ keyword); }
    }






//    /**
//     * <hr>
//     * Generates keyword using given word(s).
//     * <hr>
//     * <i>Time complexity: <b>O(n)</b> - linear, <br> TODO: Space complexity: <b>O(?)</b> - ??.</i>
//     *
//     * @param keyword is word which will be used to generate keyword. Keyword will start with it.
//     * @param useSpecialAlphabet uses special key (mixed letters), if false uses alphabet (ordered letters). Keyword will end with it.
//     * @return generated keyword.
//     */
//    static String generateKey(boolean useSpecialAlphabet, String keyword){
//        return createUniqueString(useSpecialAlphabet ? ABC_MIX : ABC, keyword);
//    }

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
    static char getLetter_Alphabet(int x, int y){
        if(0 > x || x > 4 || 0 > y || y > 4){ return ' '; } // if X or Y are off limits
        return (char)(5 * x + y + 65 + (x > 1 || x == 1 && y == 4 ? 1 : 0));
    }
    static char getLetter_Alphabet(Position pos){
        return getLetter_Alphabet(pos.X, pos.Y);
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
    static Optional<Position> getPosition_Alphabet(char letter){
        char ch = Character.toUpperCase(letter);
        if( !Character.isLetter(letter) || ch == 'J'){ return Optional.empty(); }

        ch -= 'A' + (ch > 'J' ? 1 : 0);
        return Optional.of( Position.of(ch / 5, ch % 5) );
    }
    



    public Bigram incript(Bigram bigram){
        Position pos1,pos2;
        char ch1, ch2;
        int x1, x2, y1, y2;

        if( Cypher.getPosition_Alphabet(bigram.get(1)).isPresent()
                && Cypher.getPosition_Alphabet(bigram.get(2)).isPresent() ) {
            pos1 = Cypher.getPosition_Alphabet(bigram.get(1)).get();
            pos2 = Cypher.getPosition_Alphabet(bigram.get(2)).get();
        }else{
            throw new RuntimeException("Wrong Bigram: "+ bigram);
        }

        x1 = pos1.X; y1 = pos1.Y; x2 = pos2.X; y2 = pos2.Y;

        ch1 = getLetterFromKey(Position.of(x1, y2), CrypticKey.ONE);
        ch2 = getLetterFromKey(Position.of(x2, y1), CrypticKey.TWO);

        return Bigram.of(new char[]{ch1, ch2});
    }
}
