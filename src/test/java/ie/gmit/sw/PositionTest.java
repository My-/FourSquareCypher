package ie.gmit.sw;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void getX() {
        Random rn = new Random();
        long start = System.nanoTime();

        for( int i = Integer.MAX_VALUE; i > 0; i--){
            int n1 = rn.nextInt(8) +'A',
                    n2 = rn.nextInt(8)+'A',
                    n3, n4;

            Position p = Position.of(n1, n2);
            n3 = p.getX();
            n4 = p.getY();


        }

        long end = System.nanoTime();
        System.out.println(" Total time: "+ (end -start));
        System.out.println(String.format("Running time: %.3f sec", (end -start) /1_000_000_000f));

    }
}