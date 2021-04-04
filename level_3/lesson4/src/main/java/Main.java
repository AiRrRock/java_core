public class Main {
    private final Object mon = new Object();
    private char currentLetter = 'A';

    public static void main(String[] args) {
        Main main = new Main();
        new Thread(() -> {
            main.print('A','B',5);
        }).start();
        new Thread(() -> {
            main.print('B','C',5);
        }).start();
        new Thread(() -> {
            main.print('C','A',5);
        }).start();


    }

    public void print(char letter, char nextLetter, int numberOfRepetitions) {
        synchronized (mon) {
            try {
                for (int i = 0; i < numberOfRepetitions; i++) {
                    while (currentLetter != letter) {
                        mon.wait();
                    }
                    System.out.print (letter);
                    currentLetter = nextLetter;
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
