package ie.gmit.sw;

import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * https://en.wikipedia.org/wiki/Four-square_cipher
 */
public class Runner {
    public static void main(String...args) throws Exception {

        Runner runner = new Runner();

//        FourSquareCypher cipher = FourSquareCypher.of(); // j to i and q to k and space
        String  abc = " ABCDEFGHIKLMNOPRSTUVWXYZ";
        String key1 = "GIVFWMCYZKUXTEP ABDHLNORS";
        String key2 = "TFWXPSKEOULGNH ABCDIMRVYZ";
        FourSquareCypher cipher = FourSquareCypher.of(key1, key2, abc);

//        Stream<String> stream = new Parser().fromFile("/text/PoblachtNaHEireann.txt");
        Stream<String> streamToEncrypt = new Parser().fromFile("/mnt/storage/Git-Hub/FourSquareCypher/out/production/FourSquareCypher/text/WarAndPeace-LeoTolstoy.txt");

        System.out.println(cipher.toString());

//        URI uri = runner.getClass().getResource("").toURI();

        long start, end, encript;
        System.out.println("Starting encryption...");
        start = System.nanoTime();

        long lines = 0;
//        cipher.incript(stream)
////                .forEach(System.out::println);
//                .count();

//        // https://stackoverflow.com/a/32054350/5322506
//        Files.write(Paths.get("/tmp/encrypted.txt"),
//                (Iterable<String>) IntStream.range(0, 5000).mapToObj(String::valueOf)::iterator);

        Files.write(Paths.get("/mnt/storage/Git-Hub/FourSquareCypher/out/production/FourSquareCypher/text/encrypted.txt"),
                (Iterable<String>) cipher.incript(streamToEncrypt)::iterator);

        encript = System.nanoTime();
        System.out.println("Encryption don in: "+ (encript -start));

        Stream<String> streamToDecrypt = new Parser().fromFile("/mnt/storage/Git-Hub/FourSquareCypher/out/production/FourSquareCypher/text/encrypted.txt");


        Files.write(Paths.get("/mnt/storage/Git-Hub/FourSquareCypher/out/production/FourSquareCypher/text/decrypted.txt"),
                (Iterable<String>) cipher.decript(streamToDecrypt)::iterator);

        end = System.nanoTime();
        System.out.println("Decryption done in: "+ (end -encript));
        System.out.println("Total time: "+ (end -start) +" nsec");

    }


}
