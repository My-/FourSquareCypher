package ie.gmit.v3;

import ie.gmit.v4.SpeedTest;

import java.util.Scanner;
import java.util.function.UnaryOperator;


/**
 *
 *
 * @by Mindugas Sharskus
 * @date 18/03/2018
 */
public class Menu {

    static Scanner in = new Scanner(System.in);


    private Cipher cipher;
    private CipherTypes cipherType;
    private UnaryOperator<String> prepare = String::toUpperCase;
    private String inFile = "/tmp/ramdisk/text/" + SpeedTest.FILE_BIG;
    private String enFile = "/tmp/ramdisk/text/" +SpeedTest.FILE_DECRYPTED;
    private String deFile = "/tmp/ramdisk/text/" +SpeedTest.FILE_ENCRYPTED;
//    private String cipherSettings = "";

     String BANNER ;

    String BANNER_SETUP;

    /**
     * Private constructor used for factory method.
     *
     * @param cipher
     */
    private Menu(Cipher cipher){
        this.cipher = cipher;
        cipherType = CipherTypes.typeOf(cipher); // TODO: fix it
        BANNER = "\n" +
                "===============================\n" +
                String.format("=   %-26s=\n", cipherType.toString()) +
                "===============================\n";

        BANNER_SETUP = BANNER +
                "=   SETUP                     =\n"+
                "===============================\n";
    }

    /**
     * Factory method for Menu class.
     *
     * @param cipher is used to encrypt/decrypt and setup of cipher (mutates).
     * @return Menu class instance.
     */
    public static Menu of(Cipher cipher){
        return new Menu(cipher);
    }

    /**
     * Starting point of Menu class. Shows Main menu.
     */
    public void show(){
        Menu.mainMenu(this);
    }

    /**
     * This method handles Main menu.
     *
     * @param menu Menu instance (dependency injection).
     */
    static void mainMenu(Menu menu){
        String s = menu.BANNER +
                "\n"+
                "[1] - Setup\n"+
                "[2] - Encrypt\n"+
                "[3] - Decrypt\n"+
                "-------------------\n"+
                "File: "+ menu.inFile +" \n"+
                "Cipher: "+ menu.cipherType +"\n"+
                "-------------------\n"+
                "[0] - Exit\n"+
                "\nEnter your choice: ";
        int choice;

        do{
            System.out.println(s);
            choice = in.nextInt();
            mainMenu_Do(choice, menu);
        }while(choice != 0);
    }

    /**
     * This method is "mainMenu()" method logic
     *
     * @param choice menu choice.
     * @param menu Menu instance (dependency injection).
     */
    private static void mainMenu_Do(int choice, Menu menu) {
        switch (choice){
            case 1: setupMainMenu(menu); break;
            case 2: xCryptionMainMenu(CipherAction.ENCRYPT, menu); break;
            case 3: xCryptionMainMenu(CipherAction.DECRYPT, menu); break;
            default:
                System.out.println("Unknown choice: "+ choice);
        }
    }

    /**
     * This method handles Setup menu.
     *
     * @param menu Menu instance (dependency injection).
     */
    static void setupMainMenu(Menu menu){
        String s = menu.BANNER_SETUP+
                "\n"+
                "[1] - Files:\n\t - Input: "+ menu.inFile +
                "\n\t - Encrypted: "+ menu.enFile +
                "\n\t - Decrypted: "+menu.deFile+"\n"+
                menu.cipherType.getSetupMenu() +
                "-------------------\n"+
                "[0] - Go back\n\n";

        int choice;
        do{
            System.out.println(s);
            choice = in.nextInt();
            setupMainMenu_Do(choice, menu);
        }while (choice != 0);
    }

    /**
     * This method is "setupMainMenu()" method logic.
     *
     * @param choice menu choice.
     * @param menu Menu instance (dependency injection).
     */
    private static void setupMainMenu_Do(int choice, Menu menu) { // TODO: exit from entering file
        String s;
        if( choice == 1 ){
            System.out.println("Enter files full path:\n\t Encryption input file: ");
            s = in.next();
            if( !s.isEmpty() ){ menu.inFile = s; }
            System.out.println("\n\t Encryption output file (Decryption input file): ");
            s = in.next();
            if( !s.isEmpty() ){ menu.enFile = s; }
            System.out.println("\n\t Decryption output file: ");
            s = in.next();
            if( !s.isEmpty() ){ menu.deFile = s; }
        }
        else if( choice != 0 ){
            menu.cipherType.setupMenu(choice, menu);
        }
    }

    /**
     * This method handles Encryption and Decryption menus.
     *
     * @param cipherAction action (encrypt/decrypt).
     * @param menu Menu instance (dependency injection).
     */
    static void xCryptionMainMenu(CipherAction cipherAction, Menu menu){
        String s = menu.BANNER +
                String.format("=   %-26s=\n", cipherAction)+
                "===============================\n"+
                "\n"+
                " File:  "+ menu.inFile +"\n"+
                " Cipher: "+ menu.cipherType +"\n"+
                menu.cipher.getCipherSettings() +"\n"+
                "-------------------\n"+
                String.format("[1] - %s\n", cipherAction)+
                "-------------------\n"+
                "[0] - Go back\n";

        int choice;
        System.out.println(s);

        do{
            choice = in.nextInt();
            xCrypticMenu_Do(choice, cipherAction, menu);
        }while(choice != 0);
    }

    /**
     * This method is "xCrypticMenu()" method logic.
     *
     * @param choice menu choice.
     * @param cipherAction action (encrypt/decrypt).
     * @param menu Menu instance (dependency injection).
     */
    private static void xCrypticMenu_Do(int choice, CipherAction cipherAction, Menu menu) {
        if( choice == 1 ){
            long start = System.nanoTime();
            System.out.print(cipherAction +" started... ");

            switch (cipherAction){
                case ENCRYPT:
                    Cipher.fileToFile(menu.inFile, menu.enFile, menu.prepare, menu.cipher::encrypt);
                    break;
                case DECRYPT:
                    Cipher.fileToFile(menu.inFile, menu.deFile, menu.prepare, menu.cipher::decrypt);
                    break;
            }

            System.out.println( String.format("done (%.3f ms).", (System.nanoTime() -start) /1_000_000f) );
        }
        else if(choice != 0){ System.out.println("Unknown choice: "+ choice); }
    }

    /**
     * Gets Cipher instance
     * @return Cipher instance.
     */
    public Cipher getCipher() {
        return cipher;
    }
}


/**
 * Enumeration for choosing cipher action.
 */
enum CipherAction{
    ENCRYPT("Encrypt"),
    DECRYPT("Decrypt");

    private String s;

    CipherAction(String s){
        this.s = s;
    }

    @Override
    public String toString(){
        return this.s;
    }
}
