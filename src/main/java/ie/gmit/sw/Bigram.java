package ie.gmit.sw;

public class Bigram {

    private char[] bigram = new char[2];

    Bigram(char[] bigram) {
        this.bigram = bigram;
    }
    Bigram(Bigram bigram) {
        this.bigram[0] = bigram.bigram[0];
        this.bigram[1] = bigram.bigram[1];
    }

    public static Bigram of(Bigram bigram){
        return new Bigram(bigram);
    }
    public static Bigram of(char[] bigram){
        return new Bigram(bigram);
    }

    public char get(int n){
        return this.bigram[n -1];
    }
}
