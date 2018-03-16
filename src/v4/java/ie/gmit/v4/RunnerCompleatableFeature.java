package ie.gmit.v4;


import ie.gmit.v3.FourSquareCypher;
import ie.gmit.v3.Parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class RunnerCompleatableFeature {

    static String path = "/tmp/ramdisk";
//            static String path = "/mnt/storage/Git-Hub/FourSquareCypher/out/production/FourSquareCypher";
//        static String path = new Runner().getClass().getResource("/").toString();
    static String fileSource = path +"/text/WarAndPeace-LeoTolstoy.txt";
    //        static String fileSource = path +"/text/PoblachtNaHEireann.txt";
    static String fileEncrypted = path +"/text/encrypted.txt";
    static String fileDecrypted = path +"/text/decrypted.txt";

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start = System.nanoTime(), end, encrypt, setup;




//        BlockingDeque<String> queueRead = new LinkedBlockingDeque<>();
//        BlockingDeque<String> queueEn = new LinkedBlockingDeque<>();
//        BlockingDeque<String> queueDe = new LinkedBlockingDeque<>();
//
//        Runnable readSrc = ()-> Parser.parser(fileSource, queueRead);
//        Runnable readEn = ()-> Parser.parser(fileEncrypted, queueDe);
//
//        Charset charset = StandardCharsets.UTF_8;
////        FourSquareCypher cypher = FourSquareCypher.of();
//
        String enKey1 = "UVXETRLCYBFIOGAHNDS KMWZP";
        String enKey2 = "AXDOMBZGNK SCHLFPVRUEIYWT";
//
        FourSquareCypher cypher = FourSquareCypher.of(FourSquareCypher.ALPHABET, enKey1, enKey2);
//
//        Runnable encode = ()-> {
//            try {
//
//                String s;
//                while((s = queueRead.poll(100, TimeUnit.MILLISECONDS)) != null){
//                    queueEn.offer(s.toUpperCase(), 100, TimeUnit.MILLISECONDS);
//                }
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
////                    String st = cypher.encrypt(s.toUpperCase());
//
//
//        };


//        Runnable writeToFile = ()-> {
//            try (BufferedWriter writer = Files.newBufferedWriter(
//                    FileSystems.getDefault().getPath(fileEncrypted), charset)) {
//
//                String s;
//                while((s = queueEn.poll(100, TimeUnit.MILLISECONDS)) != null){
////                    String st = cypher.encrypt(s.toUpperCase());
//                    writer.write(s, 0, s.length());
//                    writer.newLine();
//                }
//
//
//            } catch (IOException x) {
//                System.err.format("IOException: %s%n", x);
//            }
//            catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
////            try {
////
////                Files.write(Paths.get(fileEncrypted),
////                    (Iterable<String>) queueEn.stream()
////    //                        .parallel()
////                            .map(String::toUpperCase)
////                            .map(cypher::encrypt)::iterator);
////
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//        };
//
//        Runnable writeDecoded = ()-> {
//            try (BufferedWriter writer = Files.newBufferedWriter(
//                    FileSystems.getDefault().getPath(fileDecrypted), charset)) {
//
//                String s;
//                while((s = queueDe.poll(10, TimeUnit.MILLISECONDS)) != null){
//                    String st = cypher.decrypt(s.toUpperCase());
//                    writer.write(st, 0, st.length());
//                    writer.newLine();
//                }
//
//            } catch (IOException x) {
//                System.err.format("IOException: %s%n", x);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        };
//


//        ExecutorService pool = Executors.newFixedThreadPool(4);
//        ForkJoinPool pool = new ForkJoinPool(4);

//        Consumer<String> toFile = it -> {
//            try (BufferedWriter writer = Files.newBufferedWriter(
//                    FileSystems.getDefault().getPath(fileEncrypted), charset)){
//
//                writer.write(it, 0, it.length());
//                writer.newLine();
//                System.out.println(it);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        };

        Runnable doEncrypt = () -> {
            try (BufferedReader br = Files.newBufferedReader(
                    FileSystems.getDefault().getPath(fileSource), StandardCharsets.UTF_8);
                 BufferedWriter writer = Files.newBufferedWriter(
                         FileSystems.getDefault().getPath(fileEncrypted), StandardCharsets.UTF_8)
            ){
                br.lines()
                        .parallel()
                        .map(String::toUpperCase)
                        .map(cypher::encrypt)
                        .forEachOrdered(it->{
                            try {
                                writer.write(it, 0, it.length());
                                writer.newLine();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Runnable doDecrypt = () -> {
            try (BufferedReader br = Files.newBufferedReader(
                    FileSystems.getDefault().getPath(fileEncrypted), StandardCharsets.UTF_8);
                 BufferedWriter writer = Files.newBufferedWriter(
                         FileSystems.getDefault().getPath(fileDecrypted), StandardCharsets.UTF_8)
            ){
                br.lines()
                        .parallel()
                        .map(String::toUpperCase)
                        .map(cypher::decrypt)
                        .forEachOrdered(it->{
                            try {
                                writer.write(it, 0, it.length());
                                writer.newLine();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

            } catch (IOException e) {
                e.printStackTrace();
            }
        };



        doEncrypt.run();
        doDecrypt.run();





//        new Thread(readSrc).start();
////        readSrc.run();
//        encode.run();
//        writeToFile.run();

//        pool.submit(readSrc);
//        pool.submit(encode);
//        pool.submit(writeToFile).get();

//        pool.shutdown();

//        new Thread(readSrc).start();
//        new Thread(encode).start();
//        writeToFile.run();


//        CompletableFuture
//                .runAsync(readSrc, pool)
//                .thenRunAsync(encode, pool)
//                .thenRunAsync(writeToFile, pool)
//                .get();


//
//        setup = System.nanoTime();
//        System.out.println("    Setup done in: "+ (setup -start)
//                +String.format(" - %.3f sec", (setup -start) /1_000_000_000f));
//
//
//        CompletableFuture
//                .runAsync(readSrc, pool)
//                .thenRunAsync(writeEncoded, pool)
//                .get();
//
//
//        encrypt = System.nanoTime();
//        System.out.println("Encryption done in: "+ (encrypt -setup)
//                +String.format(" - %.3f sec", (encrypt -setup) /1_000_000_000f));
//
//
//
//
//        CompletableFuture
//                .runAsync(readEn, pool)
//                .thenRunAsync(writeDecoded, pool)
//                .get();
//

        end = System.nanoTime();
//        System.out.println("Decryption done in: "+ (end -encrypt)
//                +String.format(" - %.3f sec", (end -encrypt) /1_000_000_000f));
        System.out.println("Total time: "+ (end -start)
                +String.format(" - %.3f sec", (end -start) /1_000_000_000f));

    }

    static void toFile(String it){
        try (BufferedWriter writer = Files.newBufferedWriter(
                FileSystems.getDefault().getPath(fileEncrypted), StandardCharsets.UTF_8)){

            writer.write(it, 0, it.length());
            writer.newLine();
            System.out.println(it);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
