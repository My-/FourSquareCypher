package ie.gmit.sw;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class oldStuff {

//    /**
//     * <hr>
//     * <p>Method gets letter from alphabet matrix.</p>
//     * <ul style="list-style-type:none">
//     *      <li>A B C D E</li>
//     *      <li>F G H I K</li>
//     *      <li>L M N O P</li>
//     *      <li>Q R S T U</li>
//     *      <li>V W X Y Z</li>
//     * </ul>
//     * <p><strong>NOTE: Here is no letter 'J' in Alphabet.</strong></p>
//     * <hr>
//     * <i>Time complexity: <b>O(1)</b> - constant, <br> Space complexity: <b>O(0)</b> - none.</i>
//     *
//     * @param x letters X position in Alphabet matrix starting from 0 ("zero").
//     * @param y letters Y position in Alphabet matrix starting from 0 ("zero").
//     * @return letter from matrix at position (x,y) or "space" if not valid X or Y.
//     */
//    static char get(int x, int y){
//        if(0 > x || x > 4 || 0 > y || y > 4){ return ' '; } // if X or Y are off limits
//        return (char)(5 * x + y + 'A' + (x > 1 || x == 1 && y == 4 ? 1 : 0));
//    }
//    static char get(Position pos){
//        return get(pos.X, pos.Y);
//    }


//    /**
//     * <hr>
//     * <p>Method gets given letter Position (x,y) from alphabet matrix.</p>
//     * <ul style="list-style-type:none">
//     *      <li>A B C D E</li>
//     *      <li>F G H I K</li>
//     *      <li>L M N O P</li>
//     *      <li>Q R S T U</li>
//     *      <li>V W X Y Z</li>
//     * </ul>
//     * <p><strong>NOTE: Here is no letter 'J' in Alphabet.</strong></p>
//     * <hr>
//     * <i>Time complexity: <b>O(1)</b> - constant, <br> Space complexity: <b>O(0)</b> - none.</i>
//     *
//     * @param letter letters position we look in matrix.
//     * @return Position of given letter or Optional.empty() if not found.
//     */
//    static Optional<Position> get(char letter){
//        char ch = Character.toUpperCase(letter);
//        if( !Character.isLetter(letter) || ch == 'J'){ return Optional.empty(); }
//
//        ch -= 'A' + (ch > 'J' ? 1 : 0);
//        return Optional.of( Position.of(ch / 5, ch % 5) );
//    }

//    //  https://stackoverflow.com/a/42232129/5322506
////        BufferedReader br = null;
//    BufferedWriter bw = null;
//
//        try {
////            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("origin.txt"))));
//        bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("target.txt"))));
//
//        int i;
//        do {
//            i = br.read();
//            if (i != -1) {
//                bw.write(i);
//            }
//        } while (i != -1);
//
//    } catch (IOException e) {
//        System.err.println("error during copying: "+ e.getMessage());
//    } finally {
//        try {
//            if (br != null) br.close();
//            if (bw != null) bw.close();
//        } catch (IOException e) {
//            System.err.println("error during closing: "+ e.getMessage());
//        }
//    }

//    public static void main(String...args) throws Exception {
//
//        long start, end, encrypt;
//        start = System.nanoTime();
//
//        Runner runner = new Runner();
//
////        FourSquareCypher cipher = FourSquareCypher.of(); // j to i and q to k and space
//        String  abc = " ABCDEFGHIKLMNOPRSTUVWXYZ";
//        String key1 = "GIVFWMCYZKUXTEP ABDHLNORS";
//        String key2 = "TFWXPSKEOULGNH ABCDIMRVYZ";
//        FourSquareCypher cipher = FourSquareCypher.of(key1, key2, abc);
//
////        Stream<String> stream = new Parser().fromFile("/text/PoblachtNaHEireann.txt");
//        Stream<String> streamToEncrypt = new Parser().fromFile("/mnt/storage/Git-Hub/FourSquareCypher/out/production/FourSquareCypher/text/WarAndPeace-LeoTolstoy.txt");
//
//        System.out.println(cipher.toString());
//
////        URI uri = runner.getClass().getResource("").toURI();
//
//
//        System.out.println("Starting encryption...");
//
//
//        long lines = 0;
////        cipher.incript(stream)
//////                .forEach(System.out::println);
////                .count();
//
////        // https://stackoverflow.com/a/32054350/5322506
////        Files.write(Paths.get("/tmp/encrypted.txt"),
////                (Iterable<String>) IntStream.range(0, 5000).mapToObj(String::valueOf)::iterator);
//
//        Files.write(Paths.get("/mnt/storage/Git-Hub/FourSquareCypher/out/production/FourSquareCypher/text/encrypted.txt"),
//                (Iterable<String>) cipher.incript(streamToEncrypt)::iterator);
//
//        encrypt = System.nanoTime();
//        System.out.println("Encryption don in: "+ (encrypt -start));
//
//        Stream<String> streamToDecrypt = new Parser().fromFile("/mnt/storage/Git-Hub/FourSquareCypher/out/production/FourSquareCypher/text/encrypted.txt");
//
//
//        Files.write(Paths.get("/mnt/storage/Git-Hub/FourSquareCypher/out/production/FourSquareCypher/text/decrypted.txt"),
//                (Iterable<String>) cipher.decript(streamToDecrypt)::iterator);
//
//        end = System.nanoTime();
//        System.out.println("Decryption done in: "+ (end -encrypt));
//        System.out.println("Total time: "+ (end -start) +" nsec");
//
//    }

}
