package ie.gmit.sw;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Stream;

public class Parser {

    public Stream<String> fromFile(String fileName) throws Exception {
        Stream<String> R = Stream.empty();
//        URL url = getClass().getResource(fileName);

        DataInputStream in = new DataInputStream(new FileInputStream(fileName));
//        DataInputStream in = new DataInputStream(new FileInputStream(url.getPath()));
        R = new BufferedReader(new InputStreamReader(in)).lines();

//        // print files at url
//        try (Stream<Path> paths = Files.walk(Paths.get(url.getPath()))) {
//            paths.filter(Files::isRegularFile).forEach(System.out::println);
//        }

//        try (DataInputStream in = new DataInputStream(new FileInputStream(url.getPath())) ){
//            R = new BufferedReader(new InputStreamReader(in)).lines();
//        }



//        catch (Exception e) {
//            throw new Exception("[ERROR] Encountered a problem reading the dictionary. \n" + e.getMessage());
//        }

        return R;
    }


    // https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
    public static BufferedReader openURL(String urlName) throws Exception {

        URL oracle = new URL(urlName);

        return new BufferedReader(new InputStreamReader(oracle.openStream()));

//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(oracle.openStream()));

//        String inputLine;
//        while ((inputLine = in.readLine()) != null)
//            System.out.println(inputLine);
//        in.close();
    }

    static String toDigraphs(String s){

        Queue<char[]> diagraphs;// https://stackoverflow.com/a/25441208/5322506

        return "Parser";
    }

}
