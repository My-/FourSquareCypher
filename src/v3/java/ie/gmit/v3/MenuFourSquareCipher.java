package ie.gmit.v3;

public class MenuFourSquareCipher{


    private static String key1;
    private static String key2;

    public static String cipherSettings =""+
            "\t";

    public static String setupMenu = ""+
            "[2] - Key 1: "+ key1 +"\n"+
            "[3] - Key 2: "+ key2 +"\n";
//                    +

    //            "[4] - Cipher mode: "+ menu.cipherType +"\n";

     static void setupMenu(int choice, Menu menu){
         switch (choice){
             case 1: break; // NOTE: [1] is file menu handled by Menu class
             case 2: keySetupMenu(1); break;
             case 3: keySetupMenu(2); break;
             case 4: cipherModeSetupMenu(); break;
         }
    }


    static void cipherModeSetupMenu(){
        //TODO:
    }

    private static void setKey(int key) {
        // TODO:
    }

    private static void randomKey(int key) {
        // TODO:
    }
}
