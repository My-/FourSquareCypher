package ie.gmit.sw;

import java.util.Objects;

public class MutableCharacter {

    private char ch;

    private MutableCharacter(){ }
    private MutableCharacter(char ch){
        this.ch = ch;
    }

    public static MutableCharacter of(){
        return new MutableCharacter();
    }
    public static MutableCharacter of(char ch){
        return new MutableCharacter(ch);
    }

    public MutableCharacter changeTo(char ch){
        this.ch = ch;
        return this;
    }
    public char get() {
        return this.ch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MutableCharacter that = (MutableCharacter) o;
        return ch == that.ch;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ch);
    }

    @Override
    public String toString() {
        return String.valueOf(this.ch);
    }
}
