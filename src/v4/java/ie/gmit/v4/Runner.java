package ie.gmit.v4;

import ie.gmit.v3.Cipher;
import ie.gmit.v3.Menu;

public class Runner {

    public static void main(String[] args) {

        String fileSource = "", fileEncrypted = "", fileDecrypted = "";

        Cipher cipher = FourSquareCipher.of();
        Menu.of(cipher).show();

//        Runnable encrypt = () -> Cipher.fileToFile(fileSource, fileEncrypted,  String::toUpperCase, cipher::encrypt );
//        Runnable deDecrypt = () -> Cipher.fileToFile(fileEncrypted, fileDecrypted, String::toUpperCase, cipher::decrypt);
    }


}
