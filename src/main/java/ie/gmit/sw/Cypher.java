package ie.gmit.sw;

import java.util.Queue;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;

public class Cypher {


    /**
     * Generates keyword using given word.
     *
     * @param words
     * @return
     */
    static String generateKeyword(String...words){
        String key = "THEQUICKBROWNFXJMPSVLAZYDG";
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        return createKeyword(key, words);
    }

//    private static String cleanKeyword_v1(String...words){
//        return String
//                .join("", words)
//                .toUpperCase()
//                .replaceAll("J", "I")
//                .replaceAll("[^A-Z]", "") // all except letters. https://regex101.com/
//                .codePoints() // https://stackoverflow.com/a/36878434/5322506
//                .distinct()
//                .mapToObj(it -> String.valueOf((char)it))
//                .collect(Collectors.joining());
//    }

    /**
     * Method creates unique, 25 character long keyword.
     * @param alphabet will be used to fill missing letters in keyword (order it appears matters).
     * @param keyWords will be used FIRST for generating keyword.
     * @return unique 25 character long created keyword.
     */
    static String createKeyword(String alphabet, String...keyWords ){

        IntUnaryOperator replace_J_To_I = ch -> ch == 'J' ? 'I' : ch;
        IntFunction<String> toCharacter = it -> String.valueOf((char)it);

        return (String.join("", keyWords) + alphabet)
                    .codePoints() // https://stackoverflow.com/a/36878434/5322506
                    .filter(Character::isLetter)
                    .map(Character::toUpperCase)
                    .map(replace_J_To_I)
                    .distinct()
                    .mapToObj(toCharacter)
                    .collect(Collectors.joining());
    }

    static String toDigraphs(String s){
        Queue<char[]> diagraphs = // https://stackoverflow.com/a/25441208/5322506
    }
}
