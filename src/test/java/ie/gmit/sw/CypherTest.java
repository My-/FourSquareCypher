package ie.gmit.sw;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class CypherTest {
    @Test
    void getLetter() {
        assertAll(
                ()-> assertEquals('A', Cypher.getLetter(0,0)),
                ()-> assertEquals('E', Cypher.getLetter(0,4)),
                ()-> assertEquals('I', Cypher.getLetter(1,3)),
                ()-> assertEquals('K', Cypher.getLetter(1,4)),
                ()-> assertEquals('L', Cypher.getLetter(2,0)),
                ()-> assertEquals('P', Cypher.getLetter(2,4)),
                ()-> assertEquals('Z', Cypher.getLetter(4,4)),
                ()-> assertEquals(' ', Cypher.getLetter(-1,4)),
                ()-> assertEquals(' ', Cypher.getLetter(0,5)),

                ()-> {
                    int x = 2, y = 2; // 'H'
                    assertFalse('Z' == Cypher.getLetter(x,y), "Z != "+ Cypher.getLetter(x,y));
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