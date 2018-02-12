package ie.gmit.sw;

import java.util.Formatter;

public class UI {

   public static void help(){
       prln("Usage: [options] ");
       prln();
       prln("Where option includes:");
       prln("\t-encrypt | -e [mode]");
       prlf("%10s encrypt file <file-to-encrypt>.", "");
       /*
        -encrypt | -ec <file-name>
                    encrypts given file. file will be overwritten if output file parameter not given.

        -decrypt | -dc <file-name>
                    decrypts given file. file will be overwritten if output file parameter not given.

        -test <file-name>
                    run in test mode. encrypt and decrypt <file-name>.
                    file will be overwritten if output file parameter not given.

        -output | -o <file-name>
                    output to file <file-name>

        -alphabet | -abc <alphabet-as-string>
                    alphabet should be used for letter encryption and key verification
                    letter order matters.

        -key | -keys | -k [<key> | <key-one> <key-two>]
                    key or keys should be used for encryption
                    should be unique characters

        -generate-key [ <phrase> <length> | <length> ]
                    generates unique random key of given <length> (should be square number)
                    from <phrase> if <phrase> is given. missing characters will be filed automatically

        -verbose    runs in verbose mode
        -help       shows this message

        -
       */

   }

   private static void pr(String s){
       System.out.print(s);
   }
   private static void prln(String s){
       System.out.println(s);
   }
   private static void prln(){
       System.out.println();
   }
   private static void prlf(String format, Object ... args){
       System.out.println(new Formatter().format(format, args).toString());
   }
}
