package ie.gmit.v3;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Runner {

    public static void main(String[] args) throws IOException {

        long start = System.nanoTime(), end, encrypt, setup;

        String path = "/tmp/ramdisk";
//        String path = "/mnt/storage/Git-Hub/FourSquareCypher/out/production/FourSquareCypher";
//        String path = new Runner().getClass().getResource("/").toString();
        String fileSource = path +"/text/WarAndPeace-LeoTolstoy.txt";
//        String fileSource = path +"/text/PoblachtNaHEireann.txt";
        String fileEncrypted = path +"/text/encrypted.txt";
        String fileDecrypted = path +"/text/decrypted.txt";


        BlockingDeque<String> queueEn = new LinkedBlockingDeque<>();
        BlockingDeque<String> queueDe = new LinkedBlockingDeque<>();

        Runnable readSrc = ()-> Parser.parser(fileSource, queueEn);
        Runnable readEn = ()-> Parser.parser(fileEncrypted, queueDe);

        Cypher cypher = Cypher.of();
        Charset charset = StandardCharsets.UTF_8;
        Path file = FileSystems.getDefault().getPath(fileEncrypted);

        setup = System.nanoTime();
        System.out.println("    Setup done in: "+ (setup -start) +String.format(" - %.3f sec", (setup -start) /1_000_000_000f));

//        Parser.parser(fileSource, queueEn);
        new Thread(readSrc).start();

//        Files.write(Paths.get(fileEncrypted),
//                (Iterable<String>) queueEn.stream()
////                        .parallel()
//                        .map(String::toUpperCase)
//                        .map(cypher::encrypt)::iterator);


        try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {

            String s;
            while((s = queueEn.poll()) != null){
                String st = cypher.encrypt(s.toUpperCase());
//                System.out.println(st);
//                queueDe.offer(st);
                writer.write(st, 0, st.length());
                writer.newLine();
            }


        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        encrypt = System.nanoTime();
        System.out.println("Encryption don in: "+ (encrypt -setup) +String.format(" - %.3f sec", (encrypt -setup) /1_000_000_000f));

//        Parser.queue.clear();


        new Thread(readEn).run();
//        new Thread(readEn).start();
//        Parser.parser(fileEncrypted, queueDe);
        file = FileSystems.getDefault().getPath(fileDecrypted);

        try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {

            String s;
            while((s = queueDe.poll()) != null){
                String st = cypher.decrypt(s.toUpperCase());
//                System.out.println(st);
                writer.write(st, 0, st.length());
                writer.newLine();
            }

//            for( String s : queueDe ){
//                String st = cypher.decrypt(s.toUpperCase());
////                System.out.println(st);
//                writer.write(st, 0, st.length());
//                writer.newLine();
//            }

        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        end = System.nanoTime();
        System.out.println("Decryption done in: "+ (end -encrypt) +String.format(" - %.3f sec", (end -encrypt) /1_000_000_000f));
        System.out.println("Total time: "+ (end -start) +String.format(" - %.3f sec", (end -start) /1_000_000_000f));

    }
}
