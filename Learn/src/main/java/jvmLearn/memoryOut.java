package jvmLearn;

public class memoryOut {

    public static void main(String[] args) {
        while (true) {
            new Thread(new TestRun2()).start();
        }
    }

}


class TestRun2 implements Runnable {

    public void run() {
        while (true) {

        }
    }
}