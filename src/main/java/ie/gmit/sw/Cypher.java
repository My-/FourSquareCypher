package ie.gmit.sw;

import java.util.*;

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
        cypher.key_one = CrypticKey.of(keyword_one, alphabet);
        cypher.key_two = CrypticKey.of(keyword_two, alphabet);

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
        Cypher cypher = new Cypher();
        cypher.key_one = CrypticKey.of(keyword_one);
        cypher.key_two = CrypticKey.of(keyword_two);

        return cypher;
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
        Cypher cypher = new Cypher();
        cypher.key_one = CrypticKey.of();
        cypher.key_two = CrypticKey.of();

        return cypher;
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
    static char get(int x, int y){
        if(0 > x || x > 4 || 0 > y || y > 4){ return ' '; } // if X or Y are off limits
        return (char)(5 * x + y + 65 + (x > 1 || x == 1 && y == 4 ? 1 : 0));
    }
    static char get(Position pos){
        return get(pos.X, pos.Y);
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
    static Optional<Position> get(char letter){
        char ch = Character.toUpperCase(letter);
        if( !Character.isLetter(letter) || ch == 'J'){ return Optional.empty(); }

        ch -= 'A' + (ch > 'J' ? 1 : 0);
        return Optional.of( Position.of(ch / 5, ch % 5) );
    }
    



    public Bigram incript(Bigram bigram){
        Position[] pos = getPositions(bigram);
        int x1 = pos[0].X, y1 = pos[0].Y, x2 = pos[1].X, y2 = pos[1].Y;
        char ch1 = key_one.get(Position.of(x1, y2));
        char ch2 = key_two.get(Position.of(x2, y1));

        return Bigram.of(new char[]{ch1, ch2});
    }

    static Position[] getPositions(Bigram bigram){
        Position pos[] = new Position[2];

        if( Cypher.get(bigram.get(1)).isPresent()
                && Cypher.get(bigram.get(2)).isPresent() ) {
            pos[0] = Cypher.get(bigram.get(1)).get();
            pos[1] = Cypher.get(bigram.get(2)).get();
        }else{
            throw new RuntimeException("Wrong Bigram: "+ bigram);
        }

        return pos;
    }
}
