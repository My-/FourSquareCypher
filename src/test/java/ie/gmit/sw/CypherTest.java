package ie.gmit.sw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CypherTest {
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
        assertEquals("HELOFURSTAPQICKBWNXMVZYDG", Cypher.generateKeyword(keyword));
    }

}