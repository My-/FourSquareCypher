package ie.gmit.v2;

import ie.gmit.sw.MutableBool;
import ie.gmit.sw.MutableCharacter;
import ie.gmit.sw.MyUtils;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FourSquareCypher {

    public static final String DEFAULT_ABC = " ABCDEFGHIKLMNOPRSTUVWXYZ";

    public static boolean strictMode;

    private char[] abc;
    private int [] key1;
    private int [] key2;
    private Map<Integer, Integer> keyMap1; // value to index
    private Map<Integer, Integer> keyMap2; // value to index

    private FourSquareCypher(String keyword_one, String keyword_two, String alphabet){
        this.abc = MyUtils.uniqueSquareVector(alphabet.toCharArray(), strictMode);
        this.key1 = generateKey(keyword_one, this.abc);
        this.key2 = generateKey(keyword_two, this.abc);
        this.keyMap1 = createMap(this.key1);
        this.keyMap2 = createMap(this.key2);
    }

    public static FourSquareCypher of(String keyword_one, String keyword_two, String alphabet){
        return new FourSquareCypher(keyword_one, keyword_two, alphabet);
    }

    public static FourSquareCypher of(){
        return new FourSquareCypher(
                // random keyword_one
                MyUtils.createRandomCharacterString(DEFAULT_ABC.length(),
                        MyUtils.upperCaseLettersOnly.or(Character::isSpaceChar),
                        MyUtils.replace_J_Q_To_I_K),
                // random keyword_two
                MyUtils.createRandomCharacterString(DEFAULT_ABC.length(),
                        MyUtils.upperCaseLettersOnly.or(Character::isSpaceChar),
                        MyUtils.replace_J_Q_To_I_K),
                DEFAULT_ABC
        );
    }

    public Stream<String> encrypt(Stream<String> stream){
        final MutableBool firsElement = MutableBool.of(true);
        final MutableCharacter ch = MutableCharacter.of();

        return stream
                .map(it->it.split(""))
                .flatMap(Arrays::stream)
                .map(String::chars)
                .flatMapToInt(it->it)
                .filter(it->{
                    if( firsElement.get() ){ ch.changeTo((char)it); return firsElement.togle(); }
                    return firsElement.togle();
                })
                .map(it-> ch.get() << 16 | it)
                .map(this::encrypt)
                .mapToObj(String::valueOf)
                ;
    }

    public Stream<String> decrypt(Stream<String> stream){

        return stream
//                .map(String::chars)
                .mapToInt(Integer::parseInt)
//                .flatMapToInt(it->it)
                .map(this::decrypt)
                .mapToObj(it->{
                    int one = it >> 16;
                    int two = it & 0b1111_1111;
                    return one+""+two;
                })
                ;
    }


    int encrypt(int bigram){
        int one = bigram >> 16;
        int two = bigram & 0b1111_1111;
        int index1 = getIndexDefaultABC(one);
        int index2 = getIndexDefaultABC(two);
        one += key1[index1];
        two += key2[index2];

        return one << 16 | two;
    }

    int decrypt(int bigram){
        int one = bigram >> 16;
        int two = bigram & 0b1111_1111;
        System.out.println((char)one +" "+(char)two);
        int index1 = keyMap1.get(one);
        int index2 = keyMap2.get(two);
        one -= key1[index1];
        two -= key2[index2];

        return one << 16 | two;
    }

    int[] encrypt(final int[] bigram){
        int index1 = getIndexDefaultABC(bigram[0]);
        int index2 = getIndexDefaultABC(bigram[1]);

        return new int[]{bigram[0] +key1[index1], bigram[1] +key2[index2]};
    }

    int[] decrypt(final int[] bigram){
        int index1 = keyMap1.get(bigram[0]);
        int index2 = keyMap1.get(bigram[1]);

        return new int[]{bigram[0] -key1[index1], bigram[1] -key2[index2]};
    }

    int getIndexDefaultABC(int i) {
        if( 'A' <= i && i < 'J'){ return i -64; }
        if( 'J' < i && i < 'Q'){ return i -65; }
        if( 'Q' < i && i <= 'Z'){ return i -66; }

        return 0; // ' ' (space)
    }

    int[] getPos(int i, char[] abc) {
        if( i == ' ' ){ return new int[]{0, 0}; }
        if( 'A' <= i && i < 'J'){ return new int[]{(i -64)/5, (i -64)%5}; }
        if( 'J' < i && i < 'Q'){ return new int[]{(i -65)/5, (i -65)%5}; }
        if( 'Q' < i && i <= 'Z'){ return new int[]{(i -66)/5, (i -66)%5}; }

        throw new IllegalArgumentException("Unsupported character: "+ (char)i +":"+ i);
    }

    static int[] generateKey(String keyword, char[] abc) {
        Set<Character> tmpKey = new LinkedHashSet<>();
        int[] key = new int[abc.length];

        for(char ch : keyword.toCharArray()){ tmpKey.add(ch); }
        for(char ch : abc ){ tmpKey.add(ch); }

        if( abc.length != tmpKey.size() ) {
            throw new RuntimeException("Should be even: " + abc.length + "==" + tmpKey.size());
        }

        char[] charKey = MyUtils.copyfrom(tmpKey, 0, tmpKey.size());
        for(int i = 0; i < abc.length; i++){
            key[i] = charKey[i] -abc[i];
        }

        return key;
    }

    Map<Integer, Integer> createMap(int[] key) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < key.length; i++){
            map.put(key[i]+abc[i], i);
        }
        return map;
    }


    @Override
    public String toString() {
        return "FourSquareCypher{" +
                "\nabc=" + Arrays.toString(abc) +
                ", \nkey1=" + Arrays.toString(key1) +
                ", \nkey2=" + Arrays.toString(key2) +
                ", \nkeyMap1=" + keyMap1 +
                ", \nkeyMap2=" + keyMap2 +
                '}';
    }
}
