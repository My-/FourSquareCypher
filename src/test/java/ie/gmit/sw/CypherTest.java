package ie.gmit.sw;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class CypherTest {
    @Test
    void generateKeyword() {


        String[] keyword = new String[]{"hello", "four star","   $uper 5t@r"};
        String key = "THEQUICKBROWNFOXJUMPSOVERLAZYDOG";
        String ab = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

//        System.out.println(Cypher.generateKeyword(keyword));
        assertEquals("HELOFURSTAPQICKBWNXMVZYDG", Cypher.generateKeyword(keyword));
    }

}