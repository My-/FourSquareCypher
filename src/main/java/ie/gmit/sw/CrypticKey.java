package ie.gmit.sw;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CrypticKey {

    private Map<Character, Position> mapKey = Collections.synchronizedMap(new HashMap<>(25));
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

    public static CrypticKey of(String keyword){
        return new CrypticKey(keyword, MyUtils.ABC);
    }

    public static CrypticKey of(){
        return new CrypticKey(MyUtils.createRandomString(15), MyUtils.ABC);
    }

    public Optional<Position> get(char letter){
        return Optional.ofNullable(this.mapKey.get(letter));
    }

    public char get(Position position){
        int i = position.X * 5 + position.Y;
        return  this.charKey[i];
    }

}

enum Key {ONE, TWO}