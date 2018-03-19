package ie.gmit.v4;


import ie.gmit.v3.Cipher;

import java.util.Arrays;
import java.util.function.Function;


/**
 * @by Mindugas Sharskus
 * @date 17/03/2018
 */
public class SpeedTest {

    public static final String FILE_SMALL = "PoblachtNaHEireann.txt";
    public static final String FILE_MEDIUM = "DeBelloGallico.txt";
    public static final String FILE_BIG = "WarAndPeace-LeoTolstoy.txt";
    public static final String FILE_BIG_10 = "WarAndPeace-LeoTolstoy10.txt";
    public static final String FILE_BIG_50 = "WarAndPeace-LeoTolstoy50.txt";
    public static final String[] FILES = new String[]
            {FILE_SMALL, FILE_MEDIUM, FILE_BIG, FILE_BIG_10,FILE_BIG_50};

    public static final String FILE_ENCRYPTED = "encrypted.txt";
    public static final String FILE_DECRYPTED = "decrypted.txt";

    public static final String enKey1 = "UVXETRLCYBFIOGAHNDS KMWZP";
    public static final String enKey2 = "AXDOMBZGNK SCHLFPVRUEIYWT";

    public static void main(String[] args) {

        String path = "/tmp/ramdisk/text/";
        String fileSource;
        String fileEncrypted = path + SpeedTest.FILE_ENCRYPTED;
        String fileDecrypted = path + SpeedTest.FILE_DECRYPTED;

        final int TESTS = 20;
        long[] enTimes = new long[TESTS];
        long[] deTimes = new long[TESTS];
        long start = System.nanoTime(), end, encryptTime, setup;
        double enAvg, deAvg;

        Cipher cypher = FourSquareCipher.of(FourSquareCipher.ALPHABET, enKey1, enKey2);

        Function<long[], Double> average = arr ->
                Arrays.stream(arr)
                        .map(it-> it /1_000)
                        .average()
                        .orElse(-1);

        setup = System.nanoTime();
        double setupTime = (setup -start) /1_000_000f;
        System.out.println(String.format("\nSetup done in: %.3f ms", setupTime));

        // warm up
        Cipher.fileToFile(path +SpeedTest.FILE_BIG, fileEncrypted,  String::toUpperCase, cypher::encrypt );
        Cipher.fileToFile(fileEncrypted, fileDecrypted,  String::toUpperCase, cypher::decrypt );
        System.out.println( String.format("Warm up: %.3f ms", (System.nanoTime() -setup) /1_000_000f) );

        for(String file : SpeedTest.FILES){
            fileSource = path + file;
            System.gc();
            System.out.println("\n\n===========  "+ file +"  ==================\n");

            for(int i = 0; i < TESTS; i++){
                setup = System.nanoTime();      // start
                Cipher.fileToFile(fileSource, fileEncrypted,  String::toUpperCase, cypher::encrypt );
                encryptTime = System.nanoTime();// encryption end
                Cipher.fileToFile(fileEncrypted, fileDecrypted,  String::toUpperCase, cypher::decrypt );
                end = System.nanoTime();        // decryption end

                enTimes[i] = encryptTime -setup;// log encryption
                deTimes[i] = end -encryptTime;  // log decryption
            }

            System.out.print("Encryption: ");
            Arrays.stream(enTimes).forEach(it->System.out.print(
                    String.format("%8.2f", it /1_000_000f) ));

            System.out.print("\nDecryption: ");
            Arrays.stream(deTimes).forEach(it->System.out.print(
                    String.format("%8.2f", it /1_000_000f) ));

            enAvg = average.apply(enTimes) /1_000f;
            deAvg = average.apply(deTimes) /1_000f;

            System.out.println("\n");
            System.out.println("Encryption average: "+ String.format("%10.3f", enAvg) +" ms");
            System.out.println("Encryption average: "+ String.format("%10.3f", deAvg) +" ms");

            System.out.println(
                    String.format("Total [setup time + encrypt average + decrypt average(Excluding warm up time]: %8.3f ms",
                            setupTime +enAvg +deAvg)
            );

        }

    }




}
