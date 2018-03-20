import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class LamportExampleThread implements Runnable{


    private volatile boolean[] choosing;
    private volatile int[] threadNum;
    private static AtomicInteger instanceCount = new AtomicInteger(0);
    private int instanceNum;
    private static Object lock = new Object();


    public LamportExampleThread(boolean[] choosing, int[] threadNum){
        this.choosing = choosing;
        this.threadNum = threadNum;
        instanceNum = instanceCount.incrementAndGet();
        instanceNum--;
    }

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + " has been initialized.");
        while(true){
            choosing[instanceNum] = true;
            System.out.println(Thread.currentThread().getName() + " is choosing number. " + threadNum[instanceNum]);
            synchronized (lock) {
                IntStream.of(threadNum).max().ifPresent(maxInt -> threadNum[instanceNum] = maxInt + 1);
                System.out.println(Thread.currentThread().getName() + " just chose num " + threadNum[instanceNum] + ".");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            choosing[instanceNum] = false;
            for(int j = 0; j < instanceCount.get(); j++) {
                while (choosing[j]) {}
                while ((threadNum[j] > 0 && (threadNum[j]<threadNum[instanceNum])) ||
                        ((threadNum[j] == threadNum[instanceNum]) && (j<instanceNum))){}
            }
            System.out.println(Thread.currentThread().getName() + " is being served.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " is served.");
        }
    }
}
