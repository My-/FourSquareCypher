package ie.gmit.v3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

class CypherTest {

    @Test
    void genetateKey_v2() {

//        int i = 0;
//        while(++i < 100){
//            System.out.println(Arrays.toString(Cypher.genetateKey_v2(25)));
//        }

        IntStream.range(0,100)
                .parallel()
                .forEach(
                        it-> System.out.println(Arrays.toString(Cypher.generateRandomKey(25)))
                );


    }

    @Test
    void encrypt() {
        String enKey1 = "UVXETRLCYBFIOGAHNDS KMWZP";
        String enKey2 = "AXDOMBZGNK SCHLFPVRUEIYWT";
        String deKey1 = "UOIGSCKNPLVFWRMZETD AXBHY";
        String deKey2 = "K EMBVPGNWIODHCRTLZUSYAXF";

        Cypher cypher = Cypher.of(Cypher.alphabet, enKey1, enKey2, deKey1, deKey2);
        System.out.println(cypher);

//        System.out.println(cypher.encrypt(s));

        char[] en = cypher.encrypt('A','C');
        System.out.println(en[0]+ ","+en[1]);
        char[] de = cypher.decrypt(en[0], en[1]);
        System.out.println(de[0] +","+de[1]);
    }

    @Test
    void of() {
        String enKey1 = "UVXETRLCYBFIOGAHNDS KMWZP";
        String enKey2 = "AXDOMBZGNK SCHLFPVRUEIYWT";
        String deKey1 = "UOIGSCKNPLVFWRMZETD AXBHY";
        String deKey2 = "K EMBVPGNWIODHCRTLZUSYAXF";

        Cypher cypher = Cypher.of(Cypher.alphabet, enKey1, enKey2, deKey1, deKey2);

        System.out.println(cypher);
    }
}