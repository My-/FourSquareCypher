package ie.gmit.sw;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class MyUtilsTest {
    @Test
    void createRandomString() {
        Set<String> s = new HashSet<>();

        assertAll(
                ()->  IntStream.range(0, 10).forEach(it-> assertTrue(
                        s.add(MyUtils.createRandomCharacterString(10, MyUtils.noFilter, MyUtils.doNothing))))
        );
//        System.out.println(s);

        long start = System.nanoTime();

        for(int i = 1_000_000; i > 0; i--){
//            System.out.println(i);
            MyUtils.createRandomCharacterString(50, it-> true, it-> it);
        }

        System.out.println(String.format("Running time: %.3f", (System.nanoTime() -start) /1_000_000_000f));
    }

    @Test
    void createUniqueString() {
        String s1 = "ASDFG", s2 = "FGHJKL";
        String s = MyUtils.createUniqueString(s1, s2, MyUtils.noFilter, MyUtils.doNothing);
        Set<Character> set = new HashSet<>();

        for(char ch : s.toCharArray() ){
            assertTrue(set.add(ch));
        }
    }

}