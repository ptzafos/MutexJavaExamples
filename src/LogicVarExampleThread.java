import java.util.concurrent.atomic.AtomicBoolean;

public class LogicVarExampleThread implements Runnable {

    //In order to be volatile and atomic
    private static AtomicBoolean lock = new AtomicBoolean(false);

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
            System.out.println(Thread.currentThread().getName() + " has reached critical section and attempts to enter.");
            while(lock.get()){}
            lock.compareAndSet(false, true);
            System.out.println(Thread.currentThread().getName() + " entered critical section.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finished critical section and released lock.");
            lock.compareAndSet(true, false);
        }
    }
}
