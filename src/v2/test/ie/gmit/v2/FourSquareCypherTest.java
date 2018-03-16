package ie.gmit.v2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class FourSquareCypherTest {

    @Test
    void encrypt_stream() {
        String abc = " ABCDEFGHIKLMNOPRSTUVWXYZ";
        String key1 = "GIVFWMCYZKUXTEP ABDHLNORS";
        String key2 = "TFWXPSKEOULGNH ABCDIMRVYZ";

        FourSquareCypher cipher = FourSquareCypher.of(key1, key2, abc);

        int[] arr = new int[]{' ', 'A', 'B', 'C','D','E','F','G','H','I','K','L','M','N','O','P','R','S','T','U','V','W','X','Y','Z'};

//        cipher.encrypt(Arrays.stream(arr)).filter(
//                it -> {
////                    System.out.print((char)(it >> 16));
////                    System.out.print((char)(it & 0b1111_1111));
//                    return true;
//                })
//                .map(cipher::decrypt)
//                .forEach(
//                        it->{
//                            System.out.print((char)(it >> 16));
//                            System.out.print((char)(it & 0b1111_1111));
//                        }
//                );
    }

    @Test
    void encrypt() {
        String  abc = " ABCDEFGHIKLMNOPRSTUVWXYZ";
        String key1 = "GIVFWMCYZKUXTEP ABDHLNORS";
        String key2 = "TFWXPSKEOULGNH ABCDIMRVYZ";

        FourSquareCypher cipher = FourSquareCypher.of(key1, key2, abc);

        char a = 'A', b = 'B';
        int c = a << 16 | b ,aa, bb;

        int n = cipher.encrypt(c);
        System.out.println(cipher);

        System.out.println(a +" in binary: "+ Integer.toBinaryString(a));
        System.out.println(b +" in binary: "+ Integer.toBinaryString(b));
//        n = a << 16 | b;
        System.out.println((char)n +" in binary: "+ Integer.toBinaryString(n));

        aa = n >> 16;
        bb = n & 0b1111_1111;
        System.out.println((char)aa);
        System.out.println(aa +" in binary: "+ Integer.toBinaryString(aa));
        System.out.println((char)bb);
        System.out.println(bb +" in binary: "+ Integer.toBinaryString(bb));
        System.out.println();

        int m = cipher.decrypt(n);
        aa = m >> 16;
        bb = m & 0b1111_1111;

        System.out.println((char)aa +" "+ (char)bb);
    }

    @Test
    void getIndexDefaultABC() {
        String  abc = " ABCDEFGHIKLMNOPRSTUVWXYZ";
        String key1 = "GIVFWMCYZKUXTEP ABDHLNORS";
        String key2 = "TFWXPSKEOULGNH ABCDIMRVYZ";

        FourSquareCypher cipher = FourSquareCypher.of(key1, key2, abc);

        System.out.println(cipher.getIndexDefaultABC(' '));
    }

    @Test
    void other(){
        int n = 9119;

        IntUnaryOperator squareIt = it -> (int)Math.pow(it -'0', 2);

        System.out.println(
                String.valueOf(n).codePoints()
                        .map(squareIt)
//                        .boxed()
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining())
        );




    }
}