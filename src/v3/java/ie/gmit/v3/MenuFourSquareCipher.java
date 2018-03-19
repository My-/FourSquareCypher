package ie.gmit.v3;

import ie.gmit.v4.FourSquareCipher;

import java.util.Arrays;

public class MenuFourSquareCipher{


    private static String abc = "...";
    private static String key1 = "...";
    private static String key2 = "...";

    public static String setupMenu = ""+
            "[2] - Key 1: "+ key1 +"\n"+    // TODO: fix keys are no
            "[3] - Key 2: "+ key2 +"\n"+
            "[4] - Alphabet: "+ abc +"\n";

    private FourSquareCipher fsCipher;

    MenuFourSquareCipher(Cipher cipher){
        if( cipher instanceof FourSquareCipher ){
            this.fsCipher = (FourSquareCipher) cipher;
            abc = this.fsCipher.getAbc();
            key1 = this.fsCipher.getEnKey1();
            key2 = this.fsCipher.getEnKey2();
        }
        else{ System.out.println("Not instance of: FourSquareCipher"); }
    }

    public static MenuFourSquareCipher of(Cipher cipher){
        return new MenuFourSquareCipher(cipher);
    }

     static void setupMenu(int choice, Menu menu){
         MenuFourSquareCipher cipherMenu = MenuFourSquareCipher.of(menu.getCipher());

         switch (choice){
             case 1: break; // NOTE: [1] is file menu handled by Menu class
             case 2: keySetupMenu(1, cipherMenu.fsCipher, menu); break;
             case 3: keySetupMenu(2, cipherMenu.fsCipher, menu) ; break;
             case 4: abcSetupMenu(cipherMenu.fsCipher, menu); break;
         }
    }

    private static void keySetupMenu(int key, FourSquareCipher cipher, Menu menu) {
        String s = menu.BANNER_SETUP+
                String.format("=   Key %d                      =\n", key)+
                "===============================\n"+
                "\n"+
                "[1] - new random key\n"+
                "[enter key] -  to change key"+
                "-------------------\n"+
                "[0] - Go back\n"+
                "\nEnter choice: ";
        System.out.println(s);

        String nKey = Menu.in.next();

        while(!cipher.changeKey(key, nKey)){
            if( nKey.isEmpty() ){ return; } //if new key is empty cancel
            if( nKey.trim().equals("0") ){ return; } // if go back
            if( nKey.trim().equals("1") ){ // random key
                cipher.changeKey(key);
                switch (key){
                    case 1: key1 = cipher.getEnKey1(); return;
                    case 2: key2 = cipher.getEnKey2(); return;
                }
            }
            System.out.println("Wrong key format! Enter new key: ");
            nKey = Menu.in.next();
        }
    }

    private static void abcSetupMenu(FourSquareCipher cipher, Menu menu) {
        String s = menu.BANNER_SETUP+
                "=   Alphabet                  =\n"+
                "===============================\n"+
                "\n"+
                "[1] - Standard alphabet: "+ FourSquareCipher.ALPHABET +"\n"+
                "[2] - Full ASCII\n"+
                "-------------------\n"+
                "[0] - Go back\n"+
                "\nEnter choice: ";
        System.out.println(s);
        int choice;
        do{
            choice = Menu.in.nextInt();
            // TODO: ascii support
        }while (choice != 0);
    }


}
