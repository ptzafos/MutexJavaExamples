import java.util.concurrent.atomic.AtomicLong;

public class SwitchingExampleThread implements Runnable{

    private static AtomicLong turn = new AtomicLong(0);
    private static AtomicLong instanceCount = new AtomicLong(0);
    private long instanceNum;
    public SwitchingExampleThread(){
       instanceNum = instanceCount.incrementAndGet();
       instanceNum--;
    }

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + " has been initialized.");
        while(true){
            try {
                System.out.println(Thread.currentThread().getName() + " executing non critical section.");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(turn.get() != instanceNum){}
            System.out.println(Thread.currentThread().getName() + " entered critical section.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finished critical section and released lock.");
            turn.compareAndSet(instanceNum, (instanceNum + 1) % instanceCount.get());
        }
    }
}
