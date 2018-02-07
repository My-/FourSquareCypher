package ie.gmit.sw;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CypherTest {
    @Test
    void makeKeywordData() {
        String s = "HELOFURSTAPQICKBWNXMVZYDG";
        Map<Character, Position> keywordData = Collections.synchronizedMap(new HashMap<>(25));

        assertAll(
                ()-> {
                    char[] key = s.toCharArray();

                    Cypher.createKey(key, keywordData);

                    for( int i = 0; i < key.length; i++){
                        int x = i / 5, y = i % 5;
                        Position expected = Position.of(x, y);
                        Position actual = keywordData.get(key[i]);
                        assertEquals(expected, actual, String.format("Expected: %s, but was: %s", expected, actual));
                    }
                },
                ()-> assertThrows(IllegalArgumentException.class, ()-> Cypher.createKey((s +"l").toCharArray(), keywordData)),
                ()-> assertThrows(IllegalArgumentException.class, ()-> Cypher.createKey(("hello").toCharArray(), keywordData)),
                ()-> {
                    Map<Character, Position> map = new HashMap<>();
                    assertThrows(
                            IllegalArgumentException.class,
                            ()-> Cypher.createKey(("MMMMMMMMMMMMMMMMMMMMMMMMM").toCharArray(), map),
                            "data: "+ keywordData);
                }
        );
    }

    @Test
    void getAlphabetLettersPosition() {
        assertAll(
                ()-> assertEquals(Position.of(0,0), Cypher.getPosition_Alphabet('A').get()),
                ()-> assertEquals(Position.of(0,4), Cypher.getPosition_Alphabet('E').get()),
                ()-> assertEquals(Position.of(1,3), Cypher.getPosition_Alphabet('I').get()),
                ()-> assertEquals(Position.of(1,4), Cypher.getPosition_Alphabet('K').get()),
                ()-> assertEquals(Position.of(2,0), Cypher.getPosition_Alphabet('L').get()),
                ()-> assertEquals(Position.of(2,4), Cypher.getPosition_Alphabet('P').get()),
                ()-> assertEquals(Position.of(4,4), Cypher.getPosition_Alphabet('Z').get()),

                ()-> assertNotEquals(
                        Position.of(2,2),
                        Cypher.getPosition_Alphabet('A').get(),
                        "(2,2) != "+ Cypher.getPosition_Alphabet('A').get()),
                ()-> assertFalse( Cypher.getPosition_Alphabet('J').isPresent() )
        );
    }

    @Test
    void getLetter() {
        assertAll(
                ()-> assertEquals('A', Cypher.getLetter_Alphabet(0,0)),
                ()-> assertEquals('E', Cypher.getLetter_Alphabet(0,4)),
                ()-> assertEquals('I', Cypher.getLetter_Alphabet(1,3)),
                ()-> assertEquals('K', Cypher.getLetter_Alphabet(1,4)),
                ()-> assertEquals('L', Cypher.getLetter_Alphabet(2,0)),
                ()-> assertEquals('P', Cypher.getLetter_Alphabet(2,4)),
                ()-> assertEquals('Z', Cypher.getLetter_Alphabet(4,4)),
                ()-> assertEquals(' ', Cypher.getLetter_Alphabet(-1,4)),
                ()-> assertEquals(' ', Cypher.getLetter_Alphabet(0,5)),

                ()-> {
                    int x = 2, y = 2; // 'H'
                    assertFalse('Z' == Cypher.getLetter_Alphabet(x,y), "Z != "+ Cypher.getLetter_Alphabet(x,y));
                }
        );

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