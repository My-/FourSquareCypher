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

    @Test
    @DisplayName("char Speed")
    void get_letter_speed() {
        int i = Integer.MAX_VALUE;
        CrypticKey ck = CrypticKey.of();
        long start = System.nanoTime();

        while(--i > 0){
            int x = i % 5, y = i%5;
//            ck.get(x, y);
            ck.get(Position.of(x,y));
        }

        long end = System.nanoTime();
        System.out.println("Total time: "+ (end -start));
        System.out.println(String.format("Running time: %.3f sec", (end -start) /1_000_000_000f));
    }

    @Test
    @DisplayName("position speed")
    void get_position_speed() {
        int i = Integer.MAX_VALUE;
        CrypticKey ck = CrypticKey.of();
        long start = System.nanoTime();

        while(--i > 0){
            char ch = (char)(i%8 +65);
            ck.get(ch);
        }

        long end = System.nanoTime();
        System.out.println("Total time: "+ (end -start));
        System.out.println(String.format("Running time: %.3f sec", (end -start) /1_000_000_000f));
    }

}