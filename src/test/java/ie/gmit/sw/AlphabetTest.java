package ie.gmit.sw;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class AlphabetTest {


    @Test
    @DisplayName("Get position")
    void get() {
        Alphabet abc = Alphabet.of();
        Alphabet bca = Alphabet.of("QWERTYUIOPASDFGHJKLZXCVBNM");

        assertAll(
                ()-> assertEquals(Position.of(0,0), abc.get('A').get()),
                ()-> assertEquals(Position.of(0,4), abc.get('E').get()),
                ()-> assertEquals(Position.of(1,3), abc.get('I').get()),
                ()-> assertEquals(Position.of(1,4), abc.get('K').get()),
                ()-> assertEquals(Position.of(2,0), abc.get('L').get()),
                ()-> assertEquals(Position.of(2,4), abc.get('P').get()),
                ()-> assertEquals(Position.of(4,4), abc.get('Z').get()),

                ()-> assertNotEquals(
                        Position.of(2,2),
                        abc.get('A').get(),
                        "(2,2) != "+ abc.get('A').get()),
                ()-> assertFalse( abc.get('J').isPresent() )
        );
    }

    @Test
    @DisplayName("Get letter")
    void get1() {
        Alphabet abc = Alphabet.of();

        assertAll(
                ()-> assertEquals('A', abc.get(Position.of(0,0))),
                ()-> assertEquals('E', abc.get(Position.of(0,4))),
                ()-> assertEquals('I', abc.get(Position.of(1,3))),
                ()-> assertEquals('K', abc.get(Position.of(1,4))),
                ()-> assertEquals('L', abc.get(Position.of(2,0))),
                ()-> assertEquals('P', abc.get(Position.of(2,4))),
                ()-> assertEquals('Z', abc.get(Position.of(4,4))),
                ()-> assertEquals(' ', abc.get(Position.of(-1,4))),
                ()-> assertEquals(' ', abc.get(Position.of(0,5))),

                ()-> {
                    int x = 2, y = 2; // 'H'
                    assertFalse('Z' == abc.get(Position.of(x,y)), "Z != "+ abc.get(Position.of(x,y)));
                }
        );
    }

    @Test
    void contains() {
        Alphabet abc = Alphabet.of();
        char[] chArr = abc.toString().toCharArray();
        System.out.println(abc.toString());

        assertAll(
                ()-> IntStream.range(0, chArr.length)
                        .forEach(it-> assertTrue(abc.contains(chArr[it]), "problem with: "+ chArr[it] +" "+ it)),
                ()-> assertTrue(abc.contains('A')),
                ()-> assertTrue(abc.contains('D')),
                ()-> assertTrue(abc.contains('Z')),
                ()-> assertTrue(abc.contains(' '))

        );
    }
}