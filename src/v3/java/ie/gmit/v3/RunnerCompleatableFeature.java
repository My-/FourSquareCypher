package ie.gmit.v3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.concurrent.*;

public class RunnerCompleatableFeature {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

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

        Runnable readSrc = ()-> Parser.parser(fileSource, queueEn);
        Runnable readEn = ()-> Parser.parser(fileEncrypted, queueDe);

        Charset charset = StandardCharsets.UTF_8;
//        FourSquareCypher cypher = FourSquareCypher.of();

        String enKey1 = "UVXETRLCYBFIOGAHNDS KMWZP";
        String enKey2 = "AXDOMBZGNK SCHLFPVRUEIYWT";

        FourSquareCypher cypher = FourSquareCypher.of(FourSquareCypher.ALPHABET, enKey1, enKey2);




        Runnable writeEncoded = ()-> {
            try (BufferedWriter writer = Files.newBufferedWriter(
                    FileSystems.getDefault().getPath(fileEncrypted), charset)) {

                String s;
                while((s = queueEn.poll(10, TimeUnit.MILLISECONDS)) != null){
                    String st = cypher.encrypt(s.toUpperCase());
                    writer.write(st, 0, st.length());
                    writer.newLine();
                }


            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

//            try {
//
//                Files.write(Paths.get(fileEncrypted),
//                    (Iterable<String>) queueEn.stream()
//    //                        .parallel()
//                            .map(String::toUpperCase)
//                            .map(cypher::encrypt)::iterator);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        };

        Runnable writeDecoded = ()-> {
            try (BufferedWriter writer = Files.newBufferedWriter(
                    FileSystems.getDefault().getPath(fileDecrypted), charset)) {

                String s;
                while((s = queueDe.poll(10, TimeUnit.MILLISECONDS)) != null){
                    String st = cypher.decrypt(s.toUpperCase());
                    writer.write(st, 0, st.length());
                    writer.newLine();
                }

            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

//        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ForkJoinPool pool = new ForkJoinPool();

        setup = System.nanoTime();
        System.out.println("    Setup done in: "+ (setup -start)
                +String.format(" - %.3f sec", (setup -start) /1_000_000_000f));


        CompletableFuture
                .runAsync(readSrc, pool)
                .thenRunAsync(writeEncoded, pool)
                .get();


        encrypt = System.nanoTime();
        System.out.println("Encryption done in: "+ (encrypt -setup)
                +String.format(" - %.3f sec", (encrypt -setup) /1_000_000_000f));




        CompletableFuture
                .runAsync(readEn, pool)
                .thenRunAsync(writeDecoded, pool)
                .get();

//        executorService.shutdown();

        end = System.nanoTime();
        System.out.println("Decryption done in: "+ (end -encrypt)
                +String.format(" - %.3f sec", (end -encrypt) /1_000_000_000f));
        System.out.println("Total time: "+ (end -start)
                +String.format(" - %.3f sec", (end -start) /1_000_000_000f));

    }
}
