package ie.gmit.sw;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Alphabet {

    public static final int DEFAULT_LENGTH = 25;
    public static final String STANDARD_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Flag to check if alphabet is standard;
     */
    private boolean isStandard = false;

    /**
     * Square matrix (n*n) size (n).
     */
    int matrixSize;

    /**
     * Alphabet of unique characters.
     */
    char[] alphabet;

    /**
     * Alphabet data same as <i>char[] alphabet</i>. Letter is mapped to position of that letter.
     */
    private Map<Character, Position> mapAlphabet = new HashMap<>();

    /**
     * Constructor for creating standard alphabet.
     */
    private Alphabet(){
        this.alphabet = STANDARD_ALPHABET.replace("J", "").toCharArray();
        this.isStandard = true;
        this.matrixSize = (int)Math.sqrt(DEFAULT_LENGTH);
    }

    /**
     * Constructor for creating custom alphabet.
     * @param alphabet custom alphabet data.
     */
    private Alphabet(String alphabet){
        String str = MyUtils.createUniqueString(alphabet, ""); // remove duplicates
        int sqNum = (int)Math.sqrt(str.length()); // get square of that strings length
        str = str.substring(0, (sqNum * sqNum)); // trim string to square

        this.alphabet = str.toCharArray();
        this.matrixSize = sqNum;
    }

    /**
     * <hr>
     * <p>Factory method creates standard alphabet.</p>
     * <hr>
     * @return new instance of Alphabet class with standard alphabet.
     */
    public static Alphabet of(){
        return new Alphabet();
    }

    /**
     * <hr>
     * <p>Factory method creates custom alphabet.</p>
     * <hr>
     * @param alphabet should be used for creating object.
     * @return new instance of Alphabet class with custom alphabet.
     */
    public static Alphabet of(String alphabet){
        return new Alphabet(alphabet);
    }

    /**
     * <hr>
     * <p>Method gets letter from standard alphabet.</p>
     * <p>Alphabet letters are in square (n*n) matrix.</p>
     * <hr>
     * <i>Time complexity: <b>O(1)</b> - constant, <br> Space complexity: <b>O(0)</b> - none.</i>
     * @param position position in matrix of the letter.
     * @return letter from matrix at position (x,y) or "space" if not valid X or Y.
     */
    public char get(Position position){
        if(isStandard){ return Alphabet.getFromStandard(position); }

        int i = position.X * (int)Math.sqrt(alphabet.length) +position.Y;
        return this.alphabet[i];
    }

    /**
     * <hr>
     * <p>Method gets letter from standard alphabet.</p>
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
     * @param position position in matrix of the letter.
     * @return letter from matrix at position (x,y) or "space" if not valid X or Y.
     */
    static char getFromStandard(Position position){
        int x = position.X, y = position.Y;
        if(0 > x || x > 4 || 0 > y || y > 4){ return ' '; } // if X or Y are off limits
        return (char)(5 * x + y + 'A' + (x > 1 || x == 1 && y == 4 ? 1 : 0));
    }

    /**
     * <hr>
     * <p>Method gets position of the letter in alphabet.</p>
     * <p><strong>NOTE: Here is no letter 'J' in standard alphabet. </strong></p>
     * <hr>
     * <i>Time complexity: <b>O(1)</b> - constant</i>
     * @param letter letters position we look in matrix.
     * @return Position of given letter or Optional.empty() if not found.
     */
    Optional<Position> get(char letter){
        if( isStandard ){ return Alphabet.getFromStandard(letter); }
        return Optional.ofNullable(this.mapAlphabet.get(letter));
    }

    /**
     * <hr>
     * <p>Method gets position of the letter from standard alphabet.</p>
     * <p><i>Example of standard alphabet:</i></p>
     * <ul style="list-style-type:none">
     *      <li>A B C D E</li>
     *      <li>F G H I K</li>
     *      <li>L M N O P</li>
     *      <li>Q R S T U</li>
     *      <li>V W X Y Z</li>
     * </ul>
     * <p><strong>NOTE: Here is no letter 'J' in standard alphabet. </strong></p>
     * <hr>
     * <i>Time complexity: <b>O(1)</b> - constant, <br> Space complexity: <b>O(0)</b> - none.</i>
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
