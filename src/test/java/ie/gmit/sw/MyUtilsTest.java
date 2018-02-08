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
                ()->  IntStream.range(0, 10).forEach(it-> assertTrue(s.add(MyUtils.createRandomString(10))))  
        );
    }

    @Test
    void createUniqueString() {
        String s1 = "ASDFG", s2 = "FGHJKL";
        String s = MyUtils.createUniqueString(s1, s2);
        Set<Character> set = new HashSet<>();

        for(char ch : s.toCharArray() ){
            assertTrue(set.add(ch));
        }
    }

}