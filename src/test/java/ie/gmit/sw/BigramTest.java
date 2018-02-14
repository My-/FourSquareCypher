package ie.gmit.sw;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class BigramTest {

    @Test
    void get() {
        Random rn = new Random();
        long start = System.nanoTime();

        for( int i = Integer.MAX_VALUE; i > 0; i--){
            char ch1 = (char)(rn.nextInt(8)+'A'),
                    ch2 = (char)(rn.nextInt(8)+'A'),
                    ch3, ch4;

            Bigram b = Bigram.of(ch1, ch2);
            ch3 = b.get(0);
            ch4 = b.get(1);


        }

        long end = System.nanoTime();
        System.out.println(" Total time: "+ (end -start));
        System.out.println(String.format("Running time: %.3f sec", (end -start) /1_000_000_000f));
    }
}