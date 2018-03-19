package ie.gmit.v3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.function.UnaryOperator;


/**
 *  <p>Cipher interface has two abstract methods:</p>
 *  <ul>
 *      <li>encrypt</li>
 *      <li>decrypt</li>
 *  </ul>
 *  <p>Thous methods takes and returns <b><i>String</i></b> as parameters.</p>
 *  <br>
 *
 * @by Mindugas Sharskus
 * @date 18/03/2018
 */
public interface Cipher{

    /**
     * Method to be use for encrypting string.
     *
     * @param s string to be encrypted.
     * @return encrypted string.
     */
    String encrypt(String s);

    /**
     * Method to be use for decrypting string.
     *
     * @param s string to be decrypted.
     * @return decrypted string.
     */
    String decrypt(String s);

    /**
     * Gets Cipher settings as String. Override if needed.
     *
     * @return Cipher settings as string.
     */
    default String getCipherSettings(){
        return "";
    }

    /**
     *  This method opens input file. On each line apply maps function 'beforeAction'
     *  and then maps function 'action'. And then saves lines to output file.
     *
     * @param inFile    path of input file
     * @param outFile   path of output file
     * @param action    Action to be performed on each line
     */
    static void fileToFile(String inFile, String outFile,
                                  UnaryOperator<String> beforeAction,
                                  UnaryOperator<String> action){

        try (BufferedReader br = Files.newBufferedReader(
                FileSystems.getDefault().getPath(inFile), StandardCharsets.UTF_8);
             BufferedWriter writer = Files.newBufferedWriter(
                     FileSystems.getDefault().getPath(outFile), StandardCharsets.UTF_8)
        ){
            br.lines()
                    .parallel()
                    .map(beforeAction)
                    .map(action)
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
    }

}

