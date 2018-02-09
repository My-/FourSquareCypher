package ie.gmit.sw;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class FourSquareCypherTest {

    @Test
    void object_test(){
        FourSquareCypher cipher = FourSquareCypher.of();
        String abc = cipher.showAlphabet(),
                key1 = cipher.showKey1().codePoints().sorted().mapToObj(MyUtils.toCharacter).collect(Collectors.joining()),
                key2 = cipher.showKey2().codePoints().sorted().mapToObj(MyUtils.toCharacter).collect(Collectors.joining());

//        System.out.println(String.format("\n abc: %s,\nkey1: %s,\nkey2: %s\n", abc.length(), key1.length(), key2.length()));

        assertAll(
                ()-> assertTrue(abc.length() == key1.length() && key1.length() == key2.length(),
                        String.format("Checking string lengths:\n abc: %s,\nkey1: %s,\nkey2: %s\n", abc.length(), key1.length(), key2.length())),
                ()-> assertEquals(abc, key1,
                        String.format("Checking equality:\nabc:%s,\nkey1:%s\n",abc, key1)),
                ()-> assertEquals(key2, key1,
                        String.format("Checking equality:\nkey2:%s,\nkey1:%s\n",key2, key1))

        );

    }

    @Test
    void incript() {
    }

    @Test
    void decript() {
    }
}