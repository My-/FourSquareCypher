package ie.gmit.v3;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.concurrent.*;

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


        BlockingDeque<String> queueEn = new LinkedBlockingDeque<>();
        BlockingDeque<String> queueDe = new LinkedBlockingDeque<>();

        Charset charset = StandardCharsets.UTF_8;
//        FourSquareCypher cypher = FourSquareCypher.of();

        String enKey1 = "UVXETRLCYBFIOGAHNDS KMWZP";
        String enKey2 = "AXDOMBZGNK SCHLFPVRUEIYWT";

        FourSquareCypher cypher = FourSquareCypher.of(FourSquareCypher.ALPHABET, enKey1, enKey2);



//        FourSquareCypher cypher = FourSquareCypher.of();
//        Charset charset = StandardCharsets.UTF_8;

        setup = System.nanoTime();
        System.out.println("    Setup done in: "+ (setup -start)
                +String.format(" - %.3f sec", (setup -start) /1_000_000_000f));

        Parser.parser(fileSource, queueEn);




//        Files.write(Paths.get(fileEncrypted),
//                (Iterable<String>) queueEn.stream()
////                        .parallel()
//                        .map(String::toUpperCase)
//                        .map(cypher::encrypt)::iterator);


        try (BufferedWriter writer = Files.newBufferedWriter(FileSystems.getDefault().getPath(fileEncrypted), charset)) {

            String s;
            while((s = queueEn.poll(10, TimeUnit.MILLISECONDS)) != null){
                String st = cypher.encrypt(s.toUpperCase());
//                String st = s.toUpperCase();
//                System.out.println(st);
//                queueDe.offer(st);
                writer.write(st, 0, st.length());
                writer.newLine();
            }


        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        encrypt = System.nanoTime();
        System.out.println("Encryption done in: "+ (encrypt -setup)
                +String.format(" - %.3f sec", (encrypt -setup) /1_000_000_000f));




        Parser.parser(fileEncrypted, queueDe);

        try (BufferedWriter writer = Files.newBufferedWriter(FileSystems.getDefault().getPath(fileDecrypted), charset)) {

            String s;
            while((s = queueDe.poll(10, TimeUnit.MILLISECONDS)) != null){
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        end = System.nanoTime();
        System.out.println("Decryption done in: "+ (end -encrypt)
                +String.format(" - %.3f sec", (end -encrypt) /1_000_000_000f));
        System.out.println("Total time: "+ (end -start)
                +String.format(" - %.3f sec", (end -start) /1_000_000_000f));

    }
}
