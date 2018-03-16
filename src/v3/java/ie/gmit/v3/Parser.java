package ie.gmit.v3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class Parser {


    public static void parser(String path, BlockingDeque<String> queue) {

//        try (BufferedReader br = new BufferedReader(
//                new InputStreamReader(new FileInputStream(path)))){
        try (BufferedReader br = Files.newBufferedReader(
                FileSystems.getDefault().getPath(path), StandardCharsets.UTF_8) ){

            String s;
            while((s = br.readLine()) != null ){
                queue.offer(s, 100, TimeUnit.MILLISECONDS);
//                queue.put(s);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static CompletableFuture<String> reader(String path){


//
//        try ( BufferedReader br = Files.newBufferedReader(
//                FileSystems.getDefault().getPath(path), StandardCharsets.UTF_8) ){
//
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return CompletableFuture.supplyAsync(() -> {
            String s = null;

            try ( BufferedReader br = Files.newBufferedReader(
                    FileSystems.getDefault().getPath(path), StandardCharsets.UTF_8) ){

                s = br.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return s;
        });
    }
}

