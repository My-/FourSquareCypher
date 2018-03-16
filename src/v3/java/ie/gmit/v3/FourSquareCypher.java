package ie.gmit.v3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FourSquareCypher implements Cipher{

    public final static String ALPHABET = " ABCDEFGHIKLMNOPRSTUVWXYZ";

    char[] abc;
    int[] enKey1, enKey2;
    char[][][] deBigrams;
    char[][][] enBigrams;

    char[][][] enBigrams265;
    char[][][] deBigrams265;


    Map<Integer, Integer> indexList;

    private FourSquareCypher(String alphabet){
        this.abc = alphabet.toCharArray();
//        this.indexList = create_indexList(this.abc);
        this.enKey1 = generateRandomKey(this.abc.length);
        this.enKey2 = generateRandomKey(this.abc.length);
        this.deBigrams = new char[abc.length][abc.length][];
        this.enBigrams = new char[abc.length][abc.length][];
        createBigrams(this.abc);
//        this.deBigrams = createDeBigrams(this.abc);
    }

    private Map<Integer, Integer> create_indexList(char[] abc) {
        Map<Integer, Integer> R = new HashMap<>(abc.length);

        final int limit = abc.length;
        for (int i = 0; i < limit; i++) {
            R.put((int)abc[i], i);
        }
        return R;
    }

    public static FourSquareCypher of(){
        return new FourSquareCypher(FourSquareCypher.ALPHABET);
    }

    public static FourSquareCypher of(String alphabet){
        return new FourSquareCypher(alphabet);
    }

    public static FourSquareCypher of(String alphabet, String enKey1, String enKey2){
        FourSquareCypher cypher = new FourSquareCypher(alphabet);
        cypher.enKey1 = enKey1.codePoints().map(FourSquareCypher::getIndexDefaultABC).toArray();
        cypher.enKey2 = enKey2.codePoints().map(FourSquareCypher::getIndexDefaultABC).toArray();
        cypher.deBigrams = new char[cypher.abc.length][cypher.abc.length][];
        cypher.enBigrams = new char[cypher.abc.length][cypher.abc.length][];
//        cypher.deBigrams = cypher.createDeBigrams(cypher.abc);
        cypher.createBigrams(cypher.abc);
        cypher.indexList = cypher.create_indexList(cypher.abc);
        return cypher;
    }


    static int[] generateRandomKey(int length){
        return new Random(System.nanoTime())
                .ints(0, length)
                .distinct()
                .limit(length)
                .toArray();
    }

    char[][][] createDeBigrams(char[] abc) {
        char[][][] R = new char[abc.length][abc.length][];
        final int limit = abc.length;

        for(int x = 0; x < limit; x++){
            for(int y = 0; y < limit; y++){
                char[] encoded = encrypt(abc[x], abc[y]);
                int enX = getIndexDefaultABC(encoded[0]);
                int enY = getIndexDefaultABC(encoded[1]);
                R[enX][enY] = new char[]{abc[x], abc[y]};
            }
        }
        return R;
    }

    void createBigrams(char[] abc) {
        final int limit = abc.length;

        for(int x = 0; x < limit; x++){
            for(int y = 0; y < limit; y++){

                char[] encoded = encrypt_v1(abc[x], abc[y]);
                int enX = getIndexDefaultABC(encoded[0]);
                int enY = getIndexDefaultABC(encoded[1]);

                this.deBigrams[enX][enY] = new char[]{abc[x], abc[y]};
                this.deBigrams[enX][enY] = new char[]{abc[x], abc[y]};
                this.enBigrams[x][y] = encoded;
            }
        }
    }

    @Override
    public String encrypt(String s){
        StringBuilder sb = new StringBuilder();

        final int limit = s.length();
        for(int i = 1; i < limit; i+=2){
            char ch1 = s.charAt(i -1), ch2 = s.charAt(i);
            char[] chArr = encrypt(ch1, ch2);
            sb.append(chArr);
        }

        if(limit % 2 != 0){
            char[] chArr = encrypt(s.charAt(s.length() -1), ' ');
            sb.append(chArr);
        }
        
        return sb.toString();
    }

    @Override
    public String decrypt(String s){
        StringBuilder sb = new StringBuilder();

        final int limit = s.length();
        for(int i = 1; i < limit; i+=2){
            char ch1 = s.charAt(i -1), ch2 = s.charAt(i);
            char[] chArr = decrypt(ch1, ch2);
            sb.append(chArr);
        }

        return sb.toString();
    }

    char[] encrypt256(char ch1, char ch2){
        return this.enBigrams265[ch1][ch2];
    }

    char[] decrypt256(char ch1, char ch2){
        return this.deBigrams265[ch1][ch2];
    }

    char[] encrypt_v1(char ch1, char ch2) {
        int i1 = getIndexDefaultABC(ch1), i2 = getIndexDefaultABC(ch2);
        int c1 = enKey1[(i1 / 5) * 5 + i2 % 5], c2 = enKey2[(i2 / 5) * 5 + i1 % 5];
        return new char[]{abc[c1], abc[c2]};
    }

    char[] encrypt(char ch1, char ch2) {
        int i1 = getIndexDefaultABC(ch1), i2 = getIndexDefaultABC(ch2);
//        int c1 = enKey1[(i1 / 5) * 5 + i2 % 5], c2 = enKey2[(i2 / 5) * 5 + i1 % 5];
//        return new char[]{abc[c1], abc[c2]};
        return this.enBigrams[i1][i2];
    }

    char[] decrypt(char ch1, char ch2) {
        int i1 = getIndexDefaultABC(ch1), i2 = getIndexDefaultABC(ch2);
//        int c1 = deKey1[(i1 / 5) * 5 + i2 % 5], c2 = deKey2[(i2 / 5) * 5 + i1 % 5];
//        return new char[]{abc[c1], abc[c2]};
        return this.deBigrams[i1][i2];
    }

    static int getIndexDefaultABC(int i) {
        if( 'A' <= i && i < 'J'){ return i -64; }
        if( 'J' < i && i < 'Q'){ return i -65; }
        if( 'Q' < i && i <= 'Z'){ return i -66; }
        if( i == 'J' ){ return 'I' -64; }
        if( i == 'Q' ){ return 'K' -65; }

        return 0; // ' ' (space)
    }



//    int getIndexDefaultABC(int i) {
//        if(!indexList.containsKey(i)) {
////            System.out.println((char)i);
//            return 0;
//        }
//        return indexList.get(i);
//    }

    @Override
    public String toString() {
        return "FourSquareCypher{" +
                "  \n   abc=" + Arrays.toString(abc) +
                ", \nenKey1=[" + IntStream.of(enKey1).mapToObj(it-> abc[it] +"").collect(Collectors.joining(", ")) +
                "], \nenKey2=[" + IntStream.of(enKey2).mapToObj(it-> abc[it] +"").collect(Collectors.joining(", ")) +
//                "], \ndeKey1=[" + IntStream.of(deKey1).mapToObj(it-> abc[it] +"").collect(Collectors.joining(", ")) +
//                "], \ndeKey2=[" + IntStream.of(deKey2).mapToObj(it-> abc[it] +"").collect(Collectors.joining(", ")) +
                '}';
    }
}
