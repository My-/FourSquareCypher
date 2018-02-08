package ie.gmit.sw;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CypherTest {
    @Test
    void makeKeywordData() {
//        String s = "HELOFURSTAPQICKBWNXMVZYDG";
//        Map<Character, Position> keywordData = Collections.synchronizedMap(new HashMap<>(25));
//
//        assertAll(
//                ()-> {
//                    char[] key = s.toCharArray();
//
//                    Cypher.createKey(key, keywordData);
//
//                    for( int i = 0; i < key.length; i++){
//                        int x = i / 5, y = i % 5;
//                        Position expected = Position.of(x, y);
//                        Position actual = keywordData.get(key[i]);
//                        assertEquals(expected, actual, String.format("Expected: %s, but was: %s", expected, actual));
//                    }
//                },
//                ()-> assertThrows(IllegalArgumentException.class, ()-> Cypher.createKey((s +"l").toCharArray(), keywordData)),
//                ()-> assertThrows(IllegalArgumentException.class, ()-> Cypher.createKey(("hello").toCharArray(), keywordData)),
//                ()-> {
//                    Map<Character, Position> map = new HashMap<>();
//                    assertThrows(
//                            IllegalArgumentException.class,
//                            ()-> Cypher.createKey(("MMMMMMMMMMMMMMMMMMMMMMMMM").toCharArray(), map),
//                            "data: "+ keywordData);
//                }
//        );
    }

    @Test
    void getAlphabetLettersPosition() {
//        assertAll(
//                ()-> assertEquals(Position.of(0,0), Cypher.get('A').get()),
//                ()-> assertEquals(Position.of(0,4), Cypher.get('E').get()),
//                ()-> assertEquals(Position.of(1,3), Cypher.get('I').get()),
//                ()-> assertEquals(Position.of(1,4), Cypher.get('K').get()),
//                ()-> assertEquals(Position.of(2,0), Cypher.get('L').get()),
//                ()-> assertEquals(Position.of(2,4), Cypher.get('P').get()),
//                ()-> assertEquals(Position.of(4,4), Cypher.get('Z').get()),
//
//                ()-> assertNotEquals(
//                        Position.of(2,2),
//                        Cypher.get('A').get(),
//                        "(2,2) != "+ Cypher.get('A').get()),
//                ()-> assertFalse( Cypher.get('J').isPresent() )
//        );
    }

    @Test
    void getLetter() {
//        assertAll(
//                ()-> assertEquals('A', Cypher.get(0,0)),
//                ()-> assertEquals('E', Cypher.get(0,4)),
//                ()-> assertEquals('I', Cypher.get(1,3)),
//                ()-> assertEquals('K', Cypher.get(1,4)),
//                ()-> assertEquals('L', Cypher.get(2,0)),
//                ()-> assertEquals('P', Cypher.get(2,4)),
//                ()-> assertEquals('Z', Cypher.get(4,4)),
//                ()-> assertEquals(' ', Cypher.get(-1,4)),
//                ()-> assertEquals(' ', Cypher.get(0,5)),
//
//                ()-> {
//                    int x = 2, y = 2; // 'H'
//                    assertFalse('Z' == Cypher.get(x,y), "Z != "+ Cypher.get(x,y));
//                }
//        );

    }

    @Test
    void generateKeyword() {
//        String key = "THEQUICKBROWNFOXJUMPSOVERLAZYDOG";
//        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//
////        System.out.println(Cypher.generateKey(keyword));
//        assertEquals("HELOFURSTAPQICKBWNXMVZYDG", Cypher.generateKey(true, key));
    }

}