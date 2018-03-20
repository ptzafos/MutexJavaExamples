import java.util.concurrent.atomic.AtomicInteger;

public class PettersonExampleThread implements Runnable{

    private static volatile boolean[] lock = new boolean[]{false,false};
    private static AtomicInteger turn = new AtomicInteger(0);
    private static AtomicInteger instanceCount = new AtomicInteger(0);
    private int instanceNum;

    public PettersonExampleThread(){
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
                lock[instanceNum] = true;
                turn.set((instanceNum+1)%2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " has reached critical section and attempts to enter.");
            while(lock[(instanceNum+1)%2] && (turn.get()==(instanceNum+1)%2)){}
            System.out.println(Thread.currentThread().getName() + " entered critical section.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finished critical section and released lock.");
            lock[instanceNum] = false;
        }
    }
}
