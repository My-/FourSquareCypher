package ie.gmit.sw;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FourSquareCypher {

    private Alphabet alphabet;
    private CharacterKey key_one;
    private CharacterKey key_two;
    private Map<Bigram, Bigram> bigramPool = new HashMap<>();

//    private


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

     String refactor(String s){
        // v1
//        return s.toUpperCase()
//                .replaceAll("J" , "I")
//                .replaceAll("Q", "K")
//                .replaceAll("\\p{Punct}", "")
//                .replaceAll("\\p{Digit}", ""); // http://www.regular-expressions.info/posixbrackets.html

         // v2 faster 0.4 sec
         StringBuilder sb = new StringBuilder(s.toUpperCase());

         int length = s.length() -1;
         for(int i = length; i >= 0; i-- ){
            if( this.alphabet.contains(sb.charAt(i)) ){ continue; }
            sb.deleteCharAt(i);
         }
         return sb.toString();
    }

    public Stream<String> encrypt(Stream<String> stream){
        return applyOn(
                stream.map(this::refactor)
//                        .map(line-> MyUtils.splitStringEvery(line, 2))
//                        .flatMap(Arrays::stream)
//                        .map(Bigram::toBigrams)
//                        .flatMap(bigrams -> bigrams.stream().map(Bigram::toString))

                ,
                this::encrypt);
    }

    public Stream<String> decrypt(Stream<String> stream){
        return applyOn(stream, this::decrypt);
    }

//    public static Stream<String> applyOn(Stream<String> stream, UnaryOperator<Bigram> operation, Alphabet alphabet){
//        return stream.map(
//                st -> Bigram.toBigrams(st, alphabet).stream()
//                        .map(operation)
//                        .map(Bigram::toString)
//                        .collect(Collectors.joining()) );
//    }

    // http://www.oracle.com/technetwork/articles/java/architect-streams-pt2-2227132.html
//    public static Stream<String> applyOn(Stream<String> stream, UnaryOperator<Bigram> operation){
//        return stream
//                .map(Bigram::of)
//                .map(operation)
//                .map(Bigram::toString);
//    }

    public static Stream<String> applyOn(Stream<String> stream, UnaryOperator<Bigram> operation){
        return stream
                .map(Bigram::toBigrams)
                .map(bigrams -> bigrams.stream().map(operation)) // stream inside the stream
                .map(Bigram::joining);


//                .map(Bigram::of)
//                .map(operation)
//                .map(Bigram::toString);
    }

    Bigram encrypt(Bigram bigram){
        if( bigramPool.containsKey(bigram)){ return bigramPool.get(bigram); }
        Bigram b = getFrom(bigram, alphabet, alphabet, key_one, key_two);
        bigramPool.put(bigram, b);
        return b;
    }

    Bigram decrypt(Bigram bigram){

        return getFrom(bigram, key_one, key_two, alphabet, alphabet);
    }

    private Bigram getFrom(Bigram bigram, CharacterKey ch1_PosFrom, CharacterKey ch2_PosFrom, CharacterKey key1, CharacterKey key2){
        Position[] pos = getPositions(bigram, ch1_PosFrom, ch2_PosFrom);
        int x1 = pos[0].getX(), y1 = pos[0].getY(), x2 = pos[1].getX(), y2 = pos[1].getY();
        char ch1 = key1.get(Position.of(x1, y2));
        char ch2 = key2.get(Position.of(x2, y1));

        return Bigram.of(ch1, ch2);
    }

    private static Position[] getPositions(Bigram bigram, CharacterKey abc1, CharacterKey abc2){
        Position pos1, pos2;
        char ch1 = bigram.get(0), ch2 = bigram.get(1);

        if( abc1.get(ch1).isPresent() && abc2.get(ch2).isPresent() ){
            pos1 = abc1.get(ch1).get();
            pos2 = abc2.get(ch2).get();
        }else{
            throw new RuntimeException("Wrong Bigram: "+ bigram);
        }

        return new Position[]{pos1, pos2};
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
