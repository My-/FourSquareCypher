package ie.gmit.sw;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    void encrypt_Bigram() {
        String  abc = " ABCDEFGHIKLMNOPRSTUVWXYZ";
        String key1 = "GIVFWMCYZKUXTEP ABDHLNORS";
        String key2 = "TFWXPSKEOULGNH ABCDIMRVYZ";
        String testFile = "/text/PoblachtNaHEireann.txt";

        FourSquareCypher cipher = FourSquareCypher.of( key1, key2, abc);

        assertAll(
                ()-> assertEquals(Bigram.of('V', 'F'), cipher.encrypt(Bigram.of('A', 'B')), "Encrypt"),
                ()-> assertEquals(Bigram.of('A', 'B'), cipher.decrypt(Bigram.of('V', 'F')), "Decrypt")
        );


    }

    @Test
    void decrypt_Bigram() {
    }

    @Test
    void encrypt_Bigram_speed() {
        Random rn = new Random();
        String  abc = " ABCDEFGHIKLMNOPRSTUVWXYZ";
        String key1 = "GIVFWMCYZKUXTEP ABDHLNORS";
        String key2 = "TFWXPSKEOULGNH ABCDIMRVYZ";
        FourSquareCypher cipher = FourSquareCypher.of( key1, key2, abc);
        long start = System.nanoTime();

        IntStream.range(0, 10_000_000)//Integer.MAX_VALUE)
                .mapToObj(it-> Bigram.of((char)(rn.nextInt(8) +'A'), (char)(rn.nextInt(8) +'A')))
                .map(cipher::encrypt)
//                .map(Bigram::toString)
//                .map(Bigram::of)
                .map(cipher::decrypt)
//                .map(Bigram::toString)
                .collect(Collectors.toList());

        long end = System.nanoTime();
        System.out.println(" Total time: "+ (end -start));
        System.out.println(String.format("Running time: %.3f sec", (end -start) /1_000_000_000f));
    }
}
