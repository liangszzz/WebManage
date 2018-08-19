package jvmLearn;

/**
 * Exception in thread "main" java.lang.StackOverflowError
 */
public class stackOver {

    public static void main(String[] args){
        new TestRun().add();
    }
}

class TestRun{

    private int i=0;

    public void add(){
        i++;
        add();
    }
}