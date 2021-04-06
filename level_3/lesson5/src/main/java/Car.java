import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static int CURRENT_PLACE;
    private static Lock lock;


    static {
        CARS_COUNT = 0;
        CURRENT_PLACE = 0;
        lock = new ReentrantLock();
    }


    private Race race;
    private int speed;
    private String name;
    private CountDownLatch latch;
    private CyclicBarrier barrier;


    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier barrier, CountDownLatch latch) {
        this.race = race;
        this.speed = speed;
        this.latch = latch;
        this.barrier = barrier;
        CARS_COUNT++;
        this.name = "Racer #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " getting ready");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " ready");
            //The car can signal that it's ready any time(we only need to wait to start the race.
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //The cars are to start only after announcement
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        try {
            if (lock.tryLock(10000, TimeUnit.MILLISECONDS)) {
                CURRENT_PLACE++;
                if (CURRENT_PLACE == 1) {
                    System.out.println(name + " WON the race");
                } else {
                    System.out.println(name + " finished in " + CURRENT_PLACE + " place");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            latch.countDown();
        } finally {
            lock.unlock();
        }

        latch.countDown();
    }
}