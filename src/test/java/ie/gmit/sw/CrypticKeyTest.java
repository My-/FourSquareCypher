package ie.gmit.sw;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class CrypticKeyTest {


    @Test
    @DisplayName("position")
    void get_position() {
        CrypticKey key = CrypticKey.of();
        String s = key.showKey();
        int matrixLength = (int) Math.sqrt(s.length());

        assertAll(
                ()-> IntStream.range(0, s.length())
                        .forEach(i->{
                            int x = i / matrixLength, y = i % matrixLength;
                            assertEquals(Position.of(x,y), key.get(s.charAt(i)).get());
                        })
        );

//        for(int i = 0; i++ < 10;){ System.out.println(CrypticKey.of().showKey()); }
    }

    @Test
    @DisplayName("char")
    void get_letter() {
        CrypticKey key = CrypticKey.of();
        String s = key.showKey();
        int matrixLength = (int) Math.sqrt(s.length());

        assertAll(
                ()->
                    IntStream.range(0, s.length())
                            .forEach(i->{
                                int x = i / matrixLength, y = i % matrixLength;
                                assertEquals(s.charAt(i), key.get(Position.of(x,y)));
                            })
        );
    }

}