package ie.gmit.sw;

import java.util.stream.Stream;

/**
 * https://en.wikipedia.org/wiki/Four-square_cipher
 */
public class Runner {
    public static void main(String...args) throws Exception {
        FourSquareCypher cipher = FourSquareCypher.of(); // j to i and q to k and space

        Stream<String> stream = new Parser().fromFile("/text/PoblachtNaHEireann.txt");
//        Stream<String> stream = new Parser().fromFile("/text/");

        System.out.println(cipher.toString());

        cipher.incript(stream)
                .forEach(System.out::println);

    }


}
