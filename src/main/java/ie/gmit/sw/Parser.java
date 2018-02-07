package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;
import java.util.Queue;

public class Parser {

    static Optional<BufferedReader> openFile(String fileName) throws Exception{
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            DataInputStream in = new DataInputStream(fstream);

            return Optional.of( new BufferedReader(new InputStreamReader(in)));

        } catch (Exception e) {
            throw new Exception("[ERROR] Encountered a problem reading the dictionary. " + e.getMessage());
        }
//        return Optional.empty();
    }


    // https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
    static Optional<BufferedReader> openURL(String urlName) throws Exception {

        URL oracle = new URL(urlName);

        return Optional.of( new BufferedReader(
                new InputStreamReader(oracle.openStream())));

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
