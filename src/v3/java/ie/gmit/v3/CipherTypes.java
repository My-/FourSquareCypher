package ie.gmit.v3;

import ie.gmit.v4.FourSquareCipher;

/**
 * CipherTypes enum is used by Cipher interface default method <i>getCipherType()</i> to return Cipher type.
 * That default method and CipherTypes enum should be updated accordingly to reflect supported classes implementing Cipher.
 *
 * @by Mindugas Sharskus
 * @date 19/03/2018
 */
enum CipherTypes {
    // https://stackoverflow.com/a/2497532/5322506

    UNKNOWN("Cipher", ""),
    FOUR_SQUARE_CIPHER("Four Square Cipher", MenuFourSquareCipher.setupMenu);

    private String name;
    private String setupMenu;

    CipherTypes(String name, String setupMenu){
        this.name = name;
        this.setupMenu = setupMenu;
    }

    /**
     * Checks instance to get type of cipher.
     *
     * @return cipher type as in CipherTypes enum. CipherTypes.UNKNOWN if mach not found.
     */
    public static CipherTypes typeOf(Cipher cipher){
        if(cipher instanceof FourSquareCipher){ return CipherTypes.FOUR_SQUARE_CIPHER; }
        return CipherTypes.UNKNOWN;
    }

    /**
     * Gets menu supported setting menu view.
     *
     * @return setting menu.
     */
    public String getSetupMenu(){
        return this.setupMenu;
    }

    /**
     * Calls setting menu of appropriate class.
     *
     * @param choice menu choice.
     * @param menu Menu instance (mainly for getting Cipher instance)
     */
    public void setupMenu(int choice, Menu menu){
        MenuFourSquareCipher.setupMenu(choice, menu);
    }

    @Override
    public String toString() {
        return this.name;
    }
}