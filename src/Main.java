
public class Main {

    public static void main(String Args[]) {

        int n = 5;
        int numberOfExample = 4;
        boolean choosing[] = new boolean[n];
        int threadNum[] = new int[n];

        if (numberOfExample == 1) {
            //Logic Variable example
            Thread thread1 = new Thread(new LogicVarExampleThread());
            Thread thread2 = new Thread(new LogicVarExampleThread());
            thread1.start();
            thread2.start();
        } else if (numberOfExample == 2) {
            //Switch example Thread
            Thread thread1 = new Thread(new SwitchingExampleThread());
            Thread thread2 = new Thread(new SwitchingExampleThread());
            thread1.start();
            thread2.start();
        } else if (numberOfExample == 3) {
            //Petterson example Thread
            Thread thread1 = new Thread(new PettersonExampleThread());
            Thread thread2 = new Thread(new PettersonExampleThread());
            thread1.start();
            thread2.start();
        }
        else if(numberOfExample == 4){
            //Lamport example Thread
            for (int i = 0; i < n; i++){
                choosing[i] = false;
                threadNum[i]=0;
            }
            Thread thread1 = new Thread(new LamportExampleThread(choosing, threadNum));
            Thread thread2 = new Thread(new LamportExampleThread(choosing, threadNum));
            Thread thread3 = new Thread(new LamportExampleThread(choosing, threadNum));
            Thread thread4 = new Thread(new LamportExampleThread(choosing, threadNum));
            Thread thread5 = new Thread(new LamportExampleThread(choosing, threadNum));
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
            thread5.start();
        }
    }
}
