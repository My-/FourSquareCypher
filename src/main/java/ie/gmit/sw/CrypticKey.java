package ie.gmit.sw;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CrypticKey {

    private Map<Character, Position> mapKey;
    private char[] charKey;

    private CrypticKey(String keyword, String alphabet){
        String strKey = MyUtils.createUniqueString(keyword, alphabet);
        charKey = MyUtils.createUniqueString(strKey, MyUtils.ABC).toCharArray(); // fill missing characters if any

        if( strKey.length() != 25 ){ throw new IllegalArgumentException("Invalid keyword length: "+ strKey.length()); }
        for( int i = 0; i < strKey.length(); i++){ mapKey.putIfAbsent(strKey.charAt(i), Position.of(i / 5, i % 5)); }
        if( mapKey.size() != 25 ){
            throw new RuntimeException("We should not be here. Key is wrong length.");
        }
    }

    public static CrypticKey of(String keyword, String alphabet){
        return new CrypticKey(keyword, alphabet);
    }

    static Map<Character, Position> createKey(String keyword, String alphabet){
        Map<Character, Position> key = Collections.synchronizedMap(new HashMap<>(25));

        String strKey = MyUtils.createUniqueString(keyword, alphabet);
        strKey = MyUtils.createUniqueString(strKey, MyUtils.ABC); // fill missing characters if any

        if( strKey.length() != 25 ){ throw new IllegalArgumentException("Invalid keyword length: "+ strKey.length()); }
        for( int i = 0; i < strKey.length(); i++){ key.putIfAbsent(strKey.charAt(i), Position.of(i / 5, i % 5)); }
        if( key.size() != 25 ){
            throw new RuntimeException("We should not be here. Key is wrong length.");
        }

        return key;
    }

    Optional<Position> getPosition_Key(char letter, CrypticKey key){
        Optional<Position> R = Optional.empty();
        switch (key){
            case ONE: R = Optional.ofNullable(this.key_one.get(letter)); break;
            case TWO: R = Optional.ofNullable(this.key_two.get(letter)); break;
        }
        return R;
    }

    char getLetterFromKey(Position position, CrypticKey key){
        int i = position.X * 5 + position.Y;
        return key == CrypticKey.ONE ? this.charKey_one[i] : this.charKey_two[i];
    }

}

enum Key{ONE, TWO}