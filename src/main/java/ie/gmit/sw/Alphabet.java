package ie.gmit.sw;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Alphabet {

    public static final int DEFAULT_LENGTH = 25;
    public static final String STANDARD_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private boolean isStandard = false;
    int matrixSize;
    char[] alphabet;
    private Map<Character, Position> mapAlphabet = new HashMap<>();

    private Alphabet(){
        this.alphabet = STANDARD_ALPHABET.replace("J", "").toCharArray();
        this.isStandard = true;
        this.matrixSize = (int)Math.sqrt(DEFAULT_LENGTH);
    }

    private Alphabet(String alphabet){
        String str = MyUtils.createUniqueString(alphabet, ""); // remove duplicates
        int sqNum = (int)Math.sqrt(str.length()); // get square of that strings length
        str = str.substring(0, (sqNum * sqNum)); // trim string to square

        this.alphabet = str.toCharArray();
        this.matrixSize = sqNum;
    }

    public static Alphabet of(){
        return new Alphabet();
    }

    public static Alphabet of(String alphabet){
        return new Alphabet(alphabet);
    }

    public char get(Position position){
        if(isStandard){ return Alphabet.getFromStandard(position); }

        int i = position.X * (int)Math.sqrt(alphabet.length) +position.Y;
        return this.alphabet[i];
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
     * @param position position in matrix of the letter.
     * @return letter from matrix at position (x,y) or "space" if not valid X or Y.
     */
    static char getFromStandard(Position position){
        int x = position.X, y = position.Y;
        if(0 > x || x > 4 || 0 > y || y > 4){ return ' '; } // if X or Y are off limits
        return (char)(5 * x + y + 'A' + (x > 1 || x == 1 && y == 4 ? 1 : 0));
    }

    Optional<Position> get(char letter){
        if( isStandard ){ return Alphabet.getFromStandard(letter); }
        return Optional.ofNullable(this.mapAlphabet.get(letter));
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
    static Optional<Position> getFromStandard(char letter){
        char ch = Character.toUpperCase(letter);
        if( !Character.isLetter(letter) || ch == 'J'){ return Optional.empty(); }

        ch -= 'A' + (ch > 'J' ? 1 : 0);
        return Optional.of( Position.of(ch / 5, ch % 5) );
    }


}
