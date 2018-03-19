package ie.gmit.sw;

import ie.gmit.v3.Cipher;
import ie.gmit.v3.FourSquareCypher;

import java.util.Arrays;
import java.util.Random;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;

public class FourSquareCipher implements Cipher{
    public final static String ALPHABET = " ABCDEFGHIKLMNOPRSTUVWXYZ";

    char[] abc;
    int[] enKey1, enKey2;
    char[][][] deBigrams;
    char[][][] enBigrams;
    int limit;
//    Set<Character> set;


    private FourSquareCipher(String alphabet){
        this.abc = alphabet.toCharArray();
//        this.set = new HashSet<>(Arrays.hashCode(this.abc));

        this.enKey1 = generateRandomKey(this.abc.length);
        this.enKey2 = generateRandomKey(this.abc.length);

        this.limit = abc[abc.length -1] -abc[0] +1;
        this.deBigrams = new char[limit][limit][];
        this.enBigrams = new char[limit][limit][];
//        this.deBigrams = new char[abc.length][abc.length][];
//        this.enBigrams = new char[abc.length][abc.length][];
        createBigrams(this.abc);
    }

    public static FourSquareCipher of(){
        return new FourSquareCipher(FourSquareCipher.ALPHABET);
    }

    public static FourSquareCipher of(String alphabet, String enKey1, String enKey2){
        FourSquareCipher cypher = new FourSquareCipher(alphabet);
        cypher.enKey1 = enKey1.codePoints().map(FourSquareCipher::getIndexDefaultABC).toArray();
        cypher.enKey2 = enKey2.codePoints().map(FourSquareCipher::getIndexDefaultABC).toArray();

        cypher.deBigrams = new char[cypher.limit][cypher.limit][];
        cypher.enBigrams = new char[cypher.limit][cypher.limit][];
        cypher.createBigrams(cypher.abc);
        return cypher;
    }

//    @Override
//    public Optional<FourSquareCipher> of(Cipher cipher) {
//        FourSquareCipher c = null;
//
//        if(cipher instanceof FourSquareCipher){ c = (FourSquareCipher)cipher; }
//        return Optional.ofNullable( c);
//    }

    public static int[] generateRandomKey(int length){
        return new Random(System.nanoTime())
                .ints(0, length)
                .distinct()
                .limit(length)
                .toArray();
    }

    public boolean changeKey(int keyNo){
        switch (keyNo){
            case 1: this.enKey1 = generateRandomKey(this.abc.length); return true;
            case 2: this.enKey2 = generateRandomKey(this.abc.length); return true;
        }
        return false;
    }

    public boolean changeKey(int keyNo, String newKey){
        // TODO: lock it if cryption in progress.
        if( validateKey(newKey) ){
            IntUnaryOperator toInt = FourSquareCipher::getIndexDefaultABC;

            switch ((keyNo)){
                case 1:
                    this.enKey1 = newKey.codePoints()
                            .map(toInt)
                            .toArray();
                    break;
                case 2:
                    this.enKey2 = newKey.codePoints()
                            .map(toInt)
                            .toArray();
                    break;
            }
            return true;
        }
        return false;
    }

    boolean validateKey(String key){
        IntPredicate hasChar = it -> key.contains(((char)it)+"");

        return this.abc.length == key.codePoints()
                        .distinct()
                        .count()
                &&
                String.valueOf(this.abc)
                        .codePoints()
                        .allMatch(hasChar);
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

//                System.out.println(String.format("enX: %s, enY: %s, (%s, %s)", encoded[0], encoded[1], abc[x], abc[y]));

//                this.deBigrams[encoded[0] -abc[0]][encoded[1] -abc[0]] = new char[]{abc[x], abc[y]};
//                this.enBigrams[abc[x] -abc[0]][abc[y] -abc[0]] = encoded;
            }
        }
    }

    static int getIndexDefaultABC(int i) {
        return FourSquareCypher.getIndexDefaultABC(i);
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

//        if( !this.set.contains(ch1) ){ ch1 =' '; }
//        if( !this.set.contains(ch2) ){ ch2 =' '; }
//        return this.enBigrams[ch1 -abc[0]][ch2 -abc[0]];
    }

    char[] decrypt(char ch1, char ch2) {
        int i1 = getIndexDefaultABC(ch1), i2 = getIndexDefaultABC(ch2);
//        int c1 = deKey1[(i1 / 5) * 5 + i2 % 5], c2 = deKey2[(i2 / 5) * 5 + i1 % 5];
//        return new char[]{abc[c1], abc[c2]};

        return this.deBigrams[i1][i2];
//        return this.deBigrams[ch1 -abc[0]][ch2 -abc[0]];
    }

    public String getAbc() {
        return String.valueOf(abc);
    }

    public String getEnKey1() {
        IntUnaryOperator mapToChar = it -> this.abc[it];

        return Arrays.stream(this.enKey1)
//                .peek(it-> System.out.print(it +"\t"))
                .map(mapToChar)
                .mapToObj(it-> ((char)it)+"")
//                .peek(it-> System.out.println(it))
                .collect(Collectors.joining());
    }

    public String getEnKey2() {
        return String.valueOf(enKey2);
    }

    @Override
    public String encrypt(String s){
        StringBuilder sb = new StringBuilder();

        final int limit = s.length();
        for(int i = 1; i < limit; i+=2){
            char ch1 = s.charAt(i -1), ch2 = s.charAt(i);
            char[] chArr = encrypt(ch1, ch2);

//            System.out.println(String.format("%s : (%s, %s)", Arrays.toString(chArr), ch1, ch2) );

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

    @Override
    public String getCipherSettings() {
        return "\tAlphabet: " + Arrays.toString(abc) +
                "\n\tKey 1: " + Arrays.toString(enKey1) +
                "\n\tKey 2: " + Arrays.toString(enKey2);
    }

    @Override
    public String toString() {
        return "\tAlphabet: " + Arrays.toString(abc) +
                ", \n\tKey 1: " + Arrays.toString(enKey1) +
                ", \n\tKey 2: " + Arrays.toString(enKey2) +
                ", \n\tdeBigrams=" + Arrays.deepToString(deBigrams) +
                ", \n\tenBigrams=" + Arrays.deepToString(enBigrams) +
                ", \n\tlimit=" + limit +
                '}';
    }
}
