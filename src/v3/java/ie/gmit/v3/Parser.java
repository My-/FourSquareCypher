package ie.gmit.v3;

import java.io.*;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Parser {

    public static BlockingDeque<String> queue = new LinkedBlockingDeque<>();

    public static void parser(String path) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(path)));

        String s;
        while((s = br.readLine()) != null ){
            queue.offer(s);
        }
    }
}
