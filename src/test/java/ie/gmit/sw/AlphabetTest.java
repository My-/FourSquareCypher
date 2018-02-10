package ie.gmit.sw;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class AlphabetTest {

    @Test
    @DisplayName("Get position")
    void object_test() {
        String  abc = " ABCDEFGHIKLMNOPRSTUVWXYZ";
        Alphabet abc1 = Alphabet.of(abc);
        Alphabet abc2 = Alphabet.of();

//        System.out.println(abc +" = \n"+ abc1 +" = \n"+ abc2);

        assertAll(
                ()-> assertEquals(abc, abc1.toString(), "vs custom string"),
                ()-> assertEquals(abc, abc2.toString(), "vs default"),
                ()-> assertEquals(abc1.toString(), abc2.toString(), "Custom vs default")
        );
    }

    @Test
    @DisplayName("Get position")
    void get() {
        String  abc = " ABCDEFGHIKLMNOPRSTUVWXYZ";
        Alphabet abc1 = Alphabet.of();
        Alphabet abc2 = Alphabet.of(abc);

        IntStream.range(0, 2).forEach(
                it->{
                    Alphabet a = it == 0 ? abc1 : abc2;

                    assertAll(
                            ()-> assertEquals(Position.of(0,0), a.get(' ').get(),
                                    String.format("testing: %s", a)),
                            ()-> assertEquals(Position.of(0,4), a.get('D').get(),
                                    String.format("testing: %s", a)),
                            ()-> assertEquals(Position.of(1,4), a.get('I').get(),
                                    String.format("testing: %s", a)),
                            ()-> assertEquals(Position.of(2,0), a.get('K').get(),
                                    String.format("testing: %s", a)),
                            ()-> assertEquals(Position.of(3,0), a.get('P').get(),
                                    String.format("testing: %s", a)),
                            ()-> assertEquals(Position.of(3,1), a.get('R').get(),
                                    String.format("testing: %s", a)),
                            ()-> assertEquals(Position.of(4,4), a.get('Z').get(),
                                    String.format("testing: %s", a)),

                            ()-> assertNotEquals(
                                    Position.of(2,2),
                                    a.get('A').get(),
                                    "(2,2) != "+ a.get('A').get()),
                            ()-> assertFalse( a.get('J').isPresent() )
                    );

                }

        );


    }

    @Test
    @DisplayName("Get letter")
    void get1() {
        String  abc = " ABCDEFGHIKLMNOPRSTUVWXYZ";
        Alphabet abc1 = Alphabet.of();
        Alphabet abc2 = Alphabet.of(abc);

        assertAll(
                ()-> assertEquals(' ', abc1.get(Position.of(0,0))),
                ()-> assertEquals('D', abc1.get(Position.of(0,4))),
                ()-> assertEquals('H', abc1.get(Position.of(1,3))),
                ()-> assertEquals('I', abc1.get(Position.of(1,4))),
                ()-> assertEquals('K', abc1.get(Position.of(2,0))),
                ()-> assertEquals('O', abc1.get(Position.of(2,4))),
                ()-> assertEquals('Z', abc1.get(Position.of(4,4))),

                ()-> {
                    int x = 2, y = 2; // 'H'
                    assertFalse('Z' == abc1.get(Position.of(x,y)), "Z != "+ abc1.get(Position.of(x,y)));
                }
        );

        assertAll(
                ()-> assertEquals(' ', abc2.get(Position.of(0,0))),
                ()-> assertEquals('D', abc2.get(Position.of(0,4))),
                ()-> assertEquals('H', abc2.get(Position.of(1,3))),
                ()-> assertEquals('I', abc2.get(Position.of(1,4))),
                ()-> assertEquals('K', abc2.get(Position.of(2,0))),
                ()-> assertEquals('O', abc2.get(Position.of(2,4))),
                ()-> assertEquals('Z', abc2.get(Position.of(4,4))),

                ()-> {
                    int x = 2, y = 2; // 'H'
                    assertFalse('Z' == abc2.get(Position.of(x,y)), "Z != "+ abc2.get(Position.of(x,y)));
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