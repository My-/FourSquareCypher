package ie.gmit.v4;

import ie.gmit.v3.Cipher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FourSquareCipherTest {

    public static final String enKey1 = "UVXETRLCYBFIOGAHNDS KMWZP";
    public static final String enKey2 = "AXDOMBZGNK SCHLFPVRUEIYWT";
    static FourSquareCipher cipher = FourSquareCipher.of(FourSquareCipher.ALPHABET, enKey1, enKey2);

    @Test
    void validateKey() {

        assertAll(
                ()-> assertTrue(cipher.validateKey("AXDOMBZGNK SCHLFPVRUEIYWT")),
                ()-> assertFalse(cipher.validateKey("AXDOMBZGNK SSHLFPVRUEIYWT"))
        );


    }

    @Test
    void changeKey() {
        System.out.println(cipher.getCipherSettings());
        assertAll(
                () -> assertEquals("UVXETRLCYBFIOGAHNDS KMWZP", cipher.getEnKey1()),
                () -> cipher.changeKey(1, "AXDOMBZGNK SCHLFPVRUEIYWT"),
                () -> System.out.println(cipher.getCipherSettings()),
                ()-> assertEquals("AXDOMBZGNK SCHLFPVRUEIYWT", cipher.getEnKey1())
        );
    }
}