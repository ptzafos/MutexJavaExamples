import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.stream.IntStream;

public class LamportExampleThread implements Runnable{


    private volatile boolean[] choosing;
    private volatile int[] threadNum;
    private static AtomicInteger instanceCount = new AtomicInteger(0);
    private int instanceNum;
    private static Integer lock = new Integer(0);


    public LamportExampleThread(boolean[] choosing, int[] threadNum){
        this.choosing = choosing;
        this.threadNum = threadNum;
        instanceNum = instanceCount.incrementAndGet();
        instanceNum--;
    }



    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + " has been initialized.");
        choosing[instanceNum] = true;
        synchronized (lock) {
            IntStream.of(threadNum).max().ifPresent(maxInt -> threadNum[instanceNum] = maxInt + 1);
        }
        for(int j = 0; j < instanceCount.get(); j++) {

        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " just chose num " + threadNum[instanceNum]);
        choosing[instanceNum] = false;
        for(int j = 0; j < instanceCount.get(); j++) {
//            System.out.println(Thread.currentThread().getName() + " is waiting for other thread " + j + " to choose.");
            while (choosing[j]) {}
//            System.out.println(Thread.currentThread().getName() + " is waiting for other thread " + j + " to execute.");
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
        threadNum[instanceNum] = 0;
    }
}
