package ie.gmit.sw;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://en.wikipedia.org/wiki/Four-square_cipher
 */
public class Runner {
    public static void main(String...args) throws Exception {

        long start = System.nanoTime(), end, encrypt;

        String  abc = " ABCDEFGHIKLMNOPRSTUVWXYZ";
        String key1 = "GIVFWMCYZKUXTEP ABDHLNORS";
        String key2 = "TFWXPSKEOULGNH ABCDIMRVYZ";
        String path = "/mnt/storage/Git-Hub/FourSquareCypher/out/production/FourSquareCypher";
//        String path = new Runner().getClass().getResource("/").toString();
        String fileSource = path +"/text/WarAndPeace-LeoTolstoy.txt";
        String fileEncrypted = path +"/text/encrypted.txt";
        String fileDecrypted = path +"/text/decrypted.txt";

        FourSquareCypher cipher = FourSquareCypher.of();
//        FourSquareCypher cipher = FourSquareCypher.of(key1, key2, abc);
        Stream<String> streamToEncrypt = new Parser().fromFile(fileSource);

        System.out.println(cipher.toString());
        System.out.println("Starting encryption...");

        Files.write(Paths.get(fileEncrypted),
                (Iterable<String>) cipher.incript(streamToEncrypt)::iterator);

        encrypt = System.nanoTime();
        System.out.println("Encryption don in: "+ (encrypt -start));

        Stream<String> streamToDecrypt = new Parser().fromFile(fileEncrypted);

        Files.write(Paths.get(fileDecrypted),
                (Iterable<String>) cipher.decript(streamToDecrypt)::iterator);

        end = System.nanoTime();
        System.out.println("Decryption done in: "+ (end -encrypt));
        System.out.println("Total time: "+ (end -start));
        System.out.println(String.format("Running time: %.3f sec", (end -start) /1_000_000_000f));
    }


}
