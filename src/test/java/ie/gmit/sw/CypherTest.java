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

                    Cypher.makeKeywordData(key, keywordData);

                    for( int i = 0; i < key.length; i++){
                        int x = i / 5, y = i % 5;
                        Position expected = Position.of(x, y);
                        Position actual = keywordData.get(key[i]);
                        assertEquals(expected, actual, String.format("Expected: %s, but was: %s", expected, actual));
                    }
                },
                ()-> assertThrows(IllegalArgumentException.class, ()-> Cypher.makeKeywordData((s +"l").toCharArray(), keywordData)),
                ()-> assertThrows(IllegalArgumentException.class, ()-> Cypher.makeKeywordData(("hello").toCharArray(), keywordData)),
                ()-> {
                    Map<Character, Position> map = new HashMap<>();
                    assertThrows(
                            IllegalArgumentException.class,
                            ()-> Cypher.makeKeywordData(("MMMMMMMMMMMMMMMMMMMMMMMMM").toCharArray(), map),
                            "data: "+ keywordData);
                }
        );
    }

    @Test
    void getAlphabetLettersPosition() {
        assertAll(
                ()-> assertEquals(Position.of(0,0), Cypher.getAlphabetLettersPosition('A').get()),
                ()-> assertEquals(Position.of(0,4), Cypher.getAlphabetLettersPosition('E').get()),
                ()-> assertEquals(Position.of(1,3), Cypher.getAlphabetLettersPosition('I').get()),
                ()-> assertEquals(Position.of(1,4), Cypher.getAlphabetLettersPosition('K').get()),
                ()-> assertEquals(Position.of(2,0), Cypher.getAlphabetLettersPosition('L').get()),
                ()-> assertEquals(Position.of(2,4), Cypher.getAlphabetLettersPosition('P').get()),
                ()-> assertEquals(Position.of(4,4), Cypher.getAlphabetLettersPosition('Z').get()),

                ()-> assertNotEquals(
                        Position.of(2,2),
                        Cypher.getAlphabetLettersPosition('A').get(),
                        "(2,2) != "+ Cypher.getAlphabetLettersPosition('A').get()),
                ()-> assertFalse( Cypher.getAlphabetLettersPosition('J').isPresent() )
        );
    }

    @Test
    void getLetter() {
        assertAll(
                ()-> assertEquals('A', Cypher.getAlphabetLetter(0,0)),
                ()-> assertEquals('E', Cypher.getAlphabetLetter(0,4)),
                ()-> assertEquals('I', Cypher.getAlphabetLetter(1,3)),
                ()-> assertEquals('K', Cypher.getAlphabetLetter(1,4)),
                ()-> assertEquals('L', Cypher.getAlphabetLetter(2,0)),
                ()-> assertEquals('P', Cypher.getAlphabetLetter(2,4)),
                ()-> assertEquals('Z', Cypher.getAlphabetLetter(4,4)),
                ()-> assertEquals(' ', Cypher.getAlphabetLetter(-1,4)),
                ()-> assertEquals(' ', Cypher.getAlphabetLetter(0,5)),

                ()-> {
                    int x = 2, y = 2; // 'H'
                    assertFalse('Z' == Cypher.getAlphabetLetter(x,y), "Z != "+ Cypher.getAlphabetLetter(x,y));
                }
        );

    }

    @Test
    void generateKeyword() {


        String[] keyword = new String[]{"hello", "four star","   $uper 5t@r"};
        String key = "THEQUICKBROWNFOXJUMPSOVERLAZYDOG";
        String ab = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

//        System.out.println(Cypher.generateKeyword(keyword));
        assertEquals("HELOFURSTAPQICKBWNXMVZYDG", Cypher.generateKeyword(true, keyword));
    }

}