package ie.gmit.sw;

import java.util.Optional;

public class oldStuff {

//    /**
//     * <hr>
//     * <p>Method gets letter from alphabet matrix.</p>
//     * <ul style="list-style-type:none">
//     *      <li>A B C D E</li>
//     *      <li>F G H I K</li>
//     *      <li>L M N O P</li>
//     *      <li>Q R S T U</li>
//     *      <li>V W X Y Z</li>
//     * </ul>
//     * <p><strong>NOTE: Here is no letter 'J' in Alphabet.</strong></p>
//     * <hr>
//     * <i>Time complexity: <b>O(1)</b> - constant, <br> Space complexity: <b>O(0)</b> - none.</i>
//     *
//     * @param x letters X position in Alphabet matrix starting from 0 ("zero").
//     * @param y letters Y position in Alphabet matrix starting from 0 ("zero").
//     * @return letter from matrix at position (x,y) or "space" if not valid X or Y.
//     */
//    static char get(int x, int y){
//        if(0 > x || x > 4 || 0 > y || y > 4){ return ' '; } // if X or Y are off limits
//        return (char)(5 * x + y + 'A' + (x > 1 || x == 1 && y == 4 ? 1 : 0));
//    }
//    static char get(Position pos){
//        return get(pos.X, pos.Y);
//    }


//    /**
//     * <hr>
//     * <p>Method gets given letter Position (x,y) from alphabet matrix.</p>
//     * <ul style="list-style-type:none">
//     *      <li>A B C D E</li>
//     *      <li>F G H I K</li>
//     *      <li>L M N O P</li>
//     *      <li>Q R S T U</li>
//     *      <li>V W X Y Z</li>
//     * </ul>
//     * <p><strong>NOTE: Here is no letter 'J' in Alphabet.</strong></p>
//     * <hr>
//     * <i>Time complexity: <b>O(1)</b> - constant, <br> Space complexity: <b>O(0)</b> - none.</i>
//     *
//     * @param letter letters position we look in matrix.
//     * @return Position of given letter or Optional.empty() if not found.
//     */
//    static Optional<Position> get(char letter){
//        char ch = Character.toUpperCase(letter);
//        if( !Character.isLetter(letter) || ch == 'J'){ return Optional.empty(); }
//
//        ch -= 'A' + (ch > 'J' ? 1 : 0);
//        return Optional.of( Position.of(ch / 5, ch % 5) );
//    }

}
