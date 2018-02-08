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
//        cypher.key_one = CrypticKey.of(keyword_one, alphabet);
//        cypher.key_two = CrypticKey.of(keyword_two, alphabet);

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
//        cypher.key_one = CrypticKey.of(keyword_one);
//        cypher.key_two = CrypticKey.of(keyword_two);

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


    public Bigram incript(Bigram bigram){
        Position[] pos = getPositions(bigram);
        int x1 = pos[0].X, y1 = pos[0].Y, x2 = pos[1].X, y2 = pos[1].Y;
        char ch1 = key_one.get(Position.of(x1, y2));
        char ch2 = key_two.get(Position.of(x2, y1));

        return Bigram.of(new char[]{ch1, ch2});
    }

    static Position[] getPositions(Bigram bigram){
        Position pos[] = new Position[2];

//        if( Cypher.get(bigram.get(0)).isPresent()
//                && Cypher.get(bigram.get(1)).isPresent() ) {
//            pos[0] = Cypher.get(bigram.get(0)).get();
//            pos[1] = Cypher.get(bigram.get(1)).get();
//        }else{
//            throw new RuntimeException("Wrong Bigram: "+ bigram);
//        }

        return pos;
    }
}
