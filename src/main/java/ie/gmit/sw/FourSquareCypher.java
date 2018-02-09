package ie.gmit.sw;

import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FourSquareCypher {

    private CharacterKey alphabet;
    private CharacterKey key_one;
    private CharacterKey key_two;


    /**
     * Default constructor
     */
    private FourSquareCypher(){ }

    /**
     * <hr>
     * Factory method for creating new FourSquareCypher instance.
     * <hr>
     * <i>TODO:Time complexity: <b>O(?)</b> - ?, <br>  Space complexity: <b>O(1)</b> - ??.</i>
     *
     * @param keyword_one will be used for creating cyphers key_one.
     * @param keyword_two will be used for creating cyphers key_two.
     * @param alphabet will be used to populate missing letters (order maters).
     * @return new FourSquareCypher instance
     */
    public static FourSquareCypher of(String keyword_one, String keyword_two, String alphabet){
        FourSquareCypher cypher = new FourSquareCypher();
        cypher.key_one = CrypticKey.of(keyword_one, Alphabet.of(alphabet));
        cypher.key_two = CrypticKey.of(keyword_two, Alphabet.of(alphabet));
        cypher.alphabet = Alphabet.of(alphabet);

        return cypher;
    }

    /**
     * <hr>
     * Factory method for creating new FourSquareCypher instance.
     * Uses normal alphabet.
     * <hr>
     * <i>TODO:Time complexity: <b>O(?)</b> - ?, <br>  Space complexity: <b>O(1)</b> - ??.</i>
     *
     * @param keyword_one will be used for creating cyphers key_one.
     * @param keyword_two will be used for creating cyphers key_two.
     * @return new FourSquareCypher instance
     */
    public static FourSquareCypher of(String keyword_one, String keyword_two){
        FourSquareCypher cypher = new FourSquareCypher();
        cypher.key_one = CrypticKey.of(keyword_one, Alphabet.of());
        cypher.key_two = CrypticKey.of(keyword_two, Alphabet.of());
        cypher.alphabet = Alphabet.of();

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
    public static FourSquareCypher of(){
        FourSquareCypher cypher = new FourSquareCypher();
        cypher.key_one = CrypticKey.of();
        cypher.key_two = CrypticKey.of();
        cypher.alphabet = Alphabet.of();

        return cypher;
    }

    public Stream<String> incript(Stream<String> stream){
        return applyOn(stream, this::incript);
    }

    public Stream<String> decript(Stream<String> stream){
        return applyOn(stream, this::decript);
    }

    public static Stream<String> applyOn(Stream<String> stream, UnaryOperator<Bigram> operation){
        return stream.map(
                st -> Bigram.toBigrams(st).stream()
                        .map(operation)
                        .map(Bigram::toString)
                        .collect(Collectors.joining()) );
    }

    private Bigram incript(Bigram bigram){
        return getFrom(bigram, key_one, key_two);
    }

    private Bigram decript(Bigram bigram){
        return getFrom(bigram, alphabet, alphabet);
    }

    private Bigram getFrom(Bigram bigram, CharacterKey abc1, CharacterKey abc2){
        Position[] pos = getPositions(bigram, abc1, abc2);
        int x1 = pos[0].X, y1 = pos[0].Y, x2 = pos[1].X, y2 = pos[1].Y;
        char ch1 = abc1.get(Position.of(x1, y2));
        char ch2 = abc2.get(Position.of(x2, y1));

        return Bigram.of(new char[]{ch1, ch2});
    }

    private static Position[] getPositions(Bigram bigram, CharacterKey abc1, CharacterKey abc2){
        Position pos[] = new Position[2];

        if( abc1.get(bigram.get(0)).isPresent() && abc2.get(bigram.get(1)).isPresent() ){
            pos[0] = abc1.get(bigram.get(0)).get();
            pos[1] = abc2.get(bigram.get(1)).get();
        }else{
            throw new RuntimeException("Wrong Bigram: "+ bigram);
        }

        return pos;
    }

    @Override
    public String toString() {
        return "FourSquareCypher{" +
                "alphabet=" + alphabet +
                ", key_one=" + key_one +
                ", key_two=" + key_two +
                '}';
    }


    /**
     * For testing purposes
     * @return
     */
    String showAlphabet(){
        return alphabet.toString();
    }

    /**
     * For testing purposes
     * @return
     */
    String showKey1(){
        return key_one.toString();
    }

    /**
     * For testing purposes
     * @return
     */
    String showKey2(){
        return key_two.toString();
    }
}
