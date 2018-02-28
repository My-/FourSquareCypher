package ie.gmit.v3;

import java.io.*;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Parser {


    public static void parser(String path, BlockingDeque<String> queue) {

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(path)))){

            String s;
            while((s = br.readLine()) != null ){
//                queue.offer(s, 1, TimeUnit.SECONDS);
                queue.put(s);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
