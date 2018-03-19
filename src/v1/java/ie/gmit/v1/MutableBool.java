package ie.gmit.v1;

public class MutableBool {
    private boolean b;

    private MutableBool(boolean b){
        this.b = b;
    }

    public static MutableBool of(){
        return new MutableBool(false);
    }
    public static MutableBool of(boolean b){
        return new MutableBool(b);
    }

    public boolean get(){
        return this.b;
    }
    public MutableBool changeTo(boolean b){
        this.b = b;
        return this;
    }

    public boolean getThenTogle(){
        this.b = !b;
        return !b;
    }

    public boolean togle(){
        this.b = !b;
        return b;
    }
}
