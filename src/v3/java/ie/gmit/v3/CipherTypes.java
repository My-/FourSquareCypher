package ie.gmit.v3;


/**
 * CipherTypes enum is used by Cipher interface default method <i>getCipherType()</i> to return Cipher type.
 * That default method and CipherTypes enum should be updated accordingly to reflect supported classes implementing Cipher.
 *
 * @by Mindugas Sharskus
 * @date 19/03/2018
 */
enum CipherTypes {
    // https://stackoverflow.com/a/2497532/5322506

    UNKNOWN("Cipher", "", ""),
    FOUR_SQUARE_CIPHER("Four Square Cipher",
            MenuFourSquareCipher.setupMenu,
            MenuFourSquareCipher.cipherSettings);

    private String name;
    private String setupMenu;
    private String cipherSettings;

    CipherTypes(String name, String setupMenu, String cipherSettings){
        this.name = name;
        this.setupMenu = setupMenu;
        this.cipherSettings = cipherSettings;
    }

    /**
     * Checks instance to get type of cipher.
     *
     * @return cipher type as in CipherTypes enum. CipherTypes.UNKNOWN if mach not found.
     */
    public static CipherTypes typeOf(Cipher cipher){
        if(cipher instanceof FourSquareCypher){ return CipherTypes.FOUR_SQUARE_CIPHER; }
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
     * Gets cipher settings.
     *
     * @return
     */
    public String getCipherSettings(){
        return this.cipherSettings;
    }

    /**
     * Calls setting menu of appropriate class.
     *
     * @param menu main menu class.
     */
    public void setupMenu(int choice, Menu menu){
        MenuFourSquareCipher.setupMenu(choice, menu);
    }

    @Override
    public String toString() {
        return this.name;
    }
}