package ie.gmit.v3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Runner {

    public static void main(String[] args) throws IOException {

        long start = System.nanoTime(), end, encrypt;

        String path = "/tmp/ramdisk";
//        String path = "/mnt/storage/Git-Hub/FourSquareCypher/out/production/FourSquareCypher";
//        String path = new Runner().getClass().getResource("/").toString();
        String fileSource = path +"/text/WarAndPeace-LeoTolstoy10.txt";
//        String fileSource = path +"/text/PoblachtNaHEireann.txt";
        String fileEncrypted = path +"/text/encrypted.txt";
        String fileDecrypted = path +"/text/decrypted.txt";

        Runnable read = ()-> Parser.parser(fileSource);

        Parser.parser(fileSource);
//        new Thread(read);
        Cypher cypher = Cypher.of();

//        String s = cypher.encrypt( Parser.queue.poll() );

        Charset charset = StandardCharsets.UTF_8;
        Path file = FileSystems.getDefault().getPath(fileEncrypted);

        try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {

            for( String s : Parser.queue ){
                String st = cypher.encrypt(s.toUpperCase());
//                System.out.println(st);
                writer.write(st, 0, st.length());
                writer.newLine();
            }

        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        encrypt = System.nanoTime();

        Parser.queue.clear();
        Parser.parser(fileEncrypted);
        file = FileSystems.getDefault().getPath(fileDecrypted);

        try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {

            for( String s : Parser.queue ){
                String st = cypher.decrypt(s.toUpperCase());
//                System.out.println(st);
                writer.write(st, 0, st.length());
                writer.newLine();
            }

        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        end = System.nanoTime();
        System.out.println("Decryption done in: "+ (end -encrypt));
        System.out.println("Total time: "+ (end -start));
        System.out.println(String.format("Running time: %.3f sec", (end -start) /1_000_000_000f));

    }
}
