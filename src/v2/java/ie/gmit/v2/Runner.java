package ie.gmit.v2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Runner {
    public static void main(String[] args)  throws Exception{
        long start = System.nanoTime(), end, encrypt;

        String  abc = " ABCDEFGHIKLMNOPRSTUVWXYZ";
        String key1 = "GIVFWMCYZKUXTEP ABDHLNORS";
        String key2 = "TFWXPSKEOULGNH ABCDIMRVYZ";
        String path = "/tmp/ramdisk";
//        String path = "/mnt/storage/Git-Hub/FourSquareCypher/out/production/FourSquareCypher";
//        String path = new Runner().getClass().getResource("/").toString();
        String fileSource = path +"/text/WarAndPeace-LeoTolstoy10.txt";
        String fileEncrypted = path +"/text/encrypted.txt";
        String fileDecrypted = path +"/text/decrypted.txt";

        FourSquareCypher cipher = FourSquareCypher.of();
//        FourSquareCypher cipher = FourSquareCypher.of(key1, key2, abc);
//        Stream<String> streamToEncrypt = new Parser().fromFile(fileSource);


//        System.out.println(cipher.toString());
        System.out.println("Starting encryption...");


        try (DataInputStream in = new DataInputStream(new FileInputStream(fileSource)) ){
            Stream<String> streamToEncrypt =
                    new BufferedReader(new InputStreamReader(in))
                            .lines()

                    ;

            Files.write(Paths.get(fileEncrypted),
                    (Iterable<String>) streamToEncrypt::iterator);
        }

//        Files.write(Paths.get(fileEncrypted),
//                (Iterable<String>) cipher.incript(streamToEncrypt)::iterator);

        encrypt = System.nanoTime();
        System.out.println("Encryption don in: "+ (encrypt -start));

        try (DataInputStream in = new DataInputStream(new FileInputStream(fileEncrypted)) ){
            Stream<String> streamToEncrypt = new BufferedReader(new InputStreamReader(in)).lines();
            Files.write(Paths.get(fileDecrypted),
                    (Iterable<String>) streamToEncrypt::iterator);
        }


        end = System.nanoTime();
        System.out.println("Decryption done in: "+ (end -encrypt));
        System.out.println("Total time: "+ (end -start));
        System.out.println(String.format("Running time: %.3f sec", (end -start) /1_000_000_000f));
    }
}
