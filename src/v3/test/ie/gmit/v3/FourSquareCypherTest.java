package ie.gmit.v3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FourSquareCypherTest {

    @Test
    void genetateKey_v2() {

//        int i = 0;
//        while(++i < 100){
//            System.out.println(Arrays.toString(FourSquareCypher.genetateKey_v2(25)));
//        }

        IntStream.range(0,100)
                .parallel()
                .forEach(
                        it-> System.out.println(Arrays.toString(FourSquareCypher.generateRandomKey(25)))
                );


    }

    @Test
    void encrypt_decrypt_test() {
        String enKey1 = "UVXETRLCYBFIOGAHNDS KMWZP";
        String enKey2 = "AXDOMBZGNK SCHLFPVRUEIYWT";

        FourSquareCypher cypher = FourSquareCypher.of(FourSquareCypher.ALPHABET, enKey1, enKey2);

        assertAll(
                ()-> assertEquals("AB", cypher.decrypt(cypher.encrypt("AB"))),
                ()-> assertEquals("BB", cypher.decrypt(cypher.encrypt("BB"))),
                ()-> assertEquals("GH", cypher.decrypt(cypher.encrypt("GH"))),
                ()-> assertEquals("  ", cypher.decrypt(cypher.encrypt("QJ")))

        );
//        System.out.println(cypher);

//        char[] en = cypher.encrypt('G','K');
//        System.out.println(en[0]+ ","+en[1]);
//        char[] de = cypher.decrypt(en[0], en[1]);
//        System.out.println(de[0] +","+de[1]);
    }

    @Test
    void of() {
        String enKey1 = "UVXETRLCYBFIOGAHNDS KMWZP";
        String enKey2 = "AXDOMBZGNK SCHLFPVRUEIYWT";
        String deKey1 = "UOIGSCKNPLVFWRMZETD AXBHY";
        String deKey2 = "K EMBVPGNWIODHCRTLZUSYAXF";

        FourSquareCypher cypher = FourSquareCypher.of(FourSquareCypher.ALPHABET, enKey1, enKey2);

        System.out.println(cypher);


    }
}