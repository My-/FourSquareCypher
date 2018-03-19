package ie.gmit.v3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class RunnerThreads {
    public static void main(String[] args) throws IOException {

        long start = System.nanoTime(), end, encrypt, setup;

        String path = "/tmp/ramdisk";
//        String path = "/mnt/storage/Git-Hub/FourSquareCypher/out/production/FourSquareCypher";
//        String path = new Runner().getClass().getResource("/").toString();
        String fileSource = path +"/text/WarAndPeace-LeoTolstoy.txt";
//        String fileSource = path +"/text/PoblachtNaHEireann.txt";
        String fileEncrypted = path +"/text/encrypted.txt";
        String fileDecrypted = path +"/text/decrypted.txt";


        BlockingDeque<String> queueLine = new LinkedBlockingDeque<>();
        BlockingDeque<String> queueEn = new LinkedBlockingDeque<>();
        BlockingDeque<String> queueDe = new LinkedBlockingDeque<>();

        Runnable readSrc = ()-> ie.gmit.v3.Parser.parser(fileSource, queueEn);
        Runnable readEn = ()-> Parser.parser(fileEncrypted, queueDe);

        Charset charset = StandardCharsets.UTF_8;
//        FourSquareCypher cypher = FourSquareCypher.of();

        String enKey1 = "UVXETRLCYBFIOGAHNDS KMWZP";
        String enKey2 = "AXDOMBZGNK SCHLFPVRUEIYWT";

        FourSquareCypher cypher = FourSquareCypher.of(FourSquareCypher.ALPHABET, enKey1, enKey2);

//        ForkJoinPool pool = new ForkJoinPool(4);

        Runnable writeEncoded = ()-> {
            try (BufferedWriter writer = Files.newBufferedWriter(
                    FileSystems.getDefault().getPath(fileEncrypted), charset)) {

                String s;
                while((s = queueEn.poll(10, TimeUnit.MILLISECONDS)) != null){
                    String st = cypher.encrypt(s.toUpperCase());
//                    String st = s.toUpperCase();
                    //                String st = s.toUpperCase();
                    //                System.out.println(st);
                    //                queueDe.offer(st);
                    writer.write(st, 0, st.length());
                    writer.newLine();
                }

//                queueEn.stream()
////                        .parallel()
//                        .map(String::toUpperCase)
//                        .map(cypher::encrypt)
//                        .forEachOrdered(it->{
//                            try {
//                                writer.write(it, 0, it.length());
//                                writer.newLine();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        });


            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        };


        Runnable writeDecoded = ()-> {
            try (BufferedWriter writer = Files.newBufferedWriter(
                    FileSystems.getDefault().getPath(fileDecrypted), charset)) {

                String s;
                while((s = queueDe.poll(10, TimeUnit.MILLISECONDS)) != null){
                    String st = cypher.decrypt(s);
//                    String st = s.toLowerCase();
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

//            queueDe.stream()
////                    .parallel()
//                    .map(cypher::decrypt)
//                    .forEachOrdered(it->{
//                        try {
//                            writer.write(it, 0, it.length());
//                            writer.newLine();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    });

            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

//        FourSquareCypher cypher = FourSquareCypher.of();
//        Charset charset = StandardCharsets.UTF_8;

        setup = System.nanoTime();
        System.out.println("    Setup done in: "+ (setup -start)
                +String.format(" - %.3f sec", (setup -start) /1_000_000_000f));

//        Parser.parser(fileSource, queueEn);
        new Thread(readSrc).start();
//        new Thread(readSrc).show();
//        readSrc.show();


//        CompletableFuture cf = CompletableFuture.runAsync(writeEncoded);
//        new Thread(writeEncoded).start();
//        new Thread(writeEncoded).show();
        writeEncoded.run();

//        Files.write(Paths.get(fileEncrypted),
//                (Iterable<String>) queueEn.stream()
////                        .parallel()
//                        .map(String::toUpperCase)
//                        .map(cypher::encrypt)::iterator);


//        try (BufferedWriter writer = Files.newBufferedWriter(FileSystems.getDefault().getPath(fileEncrypted), charset)) {
//
//            String s;
//            while((s = queueEn.poll(10, TimeUnit.MILLISECONDS)) != null){
//                String st = cypher.encrypt(s.toUpperCase());
////                String st = s.toUpperCase();
////                System.out.println(st);
////                queueDe.offer(st);
//                writer.write(st, 0, st.length());
//                writer.newLine();
//            }
//
//
//        } catch (IOException x) {
//            System.err.format("IOException: %s%n", x);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        encrypt = System.nanoTime();
        System.out.println("Encryption done in: "+ (encrypt -setup)
                +String.format(" - %.3f sec", (encrypt -setup) /1_000_000_000f));

//        Parser.queue.clear();

//        cf.thenRun(readEn);
//        new Thread(readEn).show();
//        readEn.show();
        new Thread(readEn).start();

//        new Thread(writeDecoded).start();
//        new Thread(writeDecoded).show();
        writeDecoded.run();
//        Parser.parser(fileEncrypted, queueDe);

//        try (BufferedWriter writer = Files.newBufferedWriter(FileSystems.getDefault().getPath(fileDecrypted), charset)) {
//
//            String s;
//            while((s = queueDe.poll(10, TimeUnit.MILLISECONDS)) != null){
//                String st = cypher.decrypt(s.toUpperCase());
////                System.out.println(st);
//                writer.write(st, 0, st.length());
//                writer.newLine();
//            }
//
////            for( String s : queueDe ){
////                String st = cypher.decrypt(s.toUpperCase());
//////                System.out.println(st);
////                writer.write(st, 0, st.length());
////                writer.newLine();
////            }
//
//        } catch (IOException x) {
//            System.err.format("IOException: %s%n", x);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        end = System.nanoTime();
        System.out.println("Decryption done in: "+ (end -encrypt)
                +String.format(" - %.3f sec", (end -encrypt) /1_000_000_000f));
        System.out.println("Total time: "+ (end -start)
                +String.format(" - %.3f sec", (end -start) /1_000_000_000f));

    }
}
