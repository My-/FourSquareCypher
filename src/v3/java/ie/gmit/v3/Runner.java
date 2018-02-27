package ie.gmit.v3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class Runner {

    public static void main(String[] args) throws IOException {

        long start = System.nanoTime(), end, encrypt, setup;

        String path = "/tmp/ramdisk";
//        String path = "/mnt/storage/Git-Hub/FourSquareCypher/out/production/FourSquareCypher";
//        String path = new Runner().getClass().getResource("/").toString();
        String fileSource = path +"/text/WarAndPeace-LeoTolstoy10.txt";
//        String fileSource = path +"/text/PoblachtNaHEireann.txt";
        String fileEncrypted = path +"/text/encrypted.txt";
        String fileDecrypted = path +"/text/decrypted.txt";

        Cypher cypher = Cypher.of();

//        String s = cypher.encrypt( Parser.queue.poll() );

        Charset charset = StandardCharsets.UTF_8;
//        Path file = FileSystems.getDefault().getPath(fileEncrypted);

            setup = System.nanoTime();
            System.out.println("    Setup done in: "+ (setup -start));

        Runnable read = ()-> Parser.parser(fileSource);
        Runnable readEn = ()-> Parser.parser(fileEncrypted);
        Runnable encode = ()-> {

            try (BufferedWriter writer = Files.newBufferedWriter(FileSystems.getDefault().getPath(fileEncrypted), charset)) {

                String s;
                while((s = Parser.queue.poll()) != null){
                    String st = cypher.encrypt(s.toUpperCase());
//                System.out.println(st);
                    writer.write(st, 0, s.length());
                    writer.newLine();
                }

            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        };

//        Parser.parser(fileSource);
        new Thread(read).start();
//        Cypher cypher = Cypher.of();
//
////        String s = cypher.encrypt( Parser.queue.poll() );
//
//        Charset charset = StandardCharsets.UTF_8;
//        Path file = FileSystems.getDefault().getPath(fileEncrypted);
//
//        setup = System.nanoTime();
//        System.out.println("    Setup done in: "+ (setup -start));
//
//        try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
//
//            String s;
//            while((s = Parser.queue.poll()) != null){
//                String st = cypher.encrypt(s.toUpperCase());
////                System.out.println(st);
//                writer.write(st, 0, s.length());
//                writer.newLine();
//            }
//
//        } catch (IOException x) {
//            System.err.format("IOException: %s%n", x);
//        }
        new Thread(encode).start();

        encrypt = System.nanoTime();
        System.out.println("Encryption don in: "+ (encrypt -setup));

        Parser.queue.clear();

        new Thread(readEn).start();
//        Parser.parser(fileEncrypted);
//        file = FileSystems.getDefault().getPath(fileDecrypted);

        try (BufferedWriter writer = Files.newBufferedWriter(FileSystems.getDefault().getPath(fileDecrypted), charset)) {

            String s;
            while((s = Parser.queue.poll()) != null){
                String st = cypher.decrypt(s.toUpperCase());
//                System.out.println(st);
                writer.write(st, 0, st.length());
                writer.newLine();
            }

//            for( String s : Parser.queue ){
//                String st = cypher.decrypt(s.toUpperCase());
////                System.out.println(st);
//                writer.write(st, 0, st.length());
//                writer.newLine();
//            }

        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        end = System.nanoTime();
        System.out.println("Decryption done in: "+ (end -encrypt));
        System.out.println("Total time: "+ (end -start));
        System.out.println(String.format("Running time: %.3f sec", (end -start) /1_000_000_000f));

    }
}
