
public class Main {

    public static void main(String Args[]){

        int numberOfExample = 2;

        if(numberOfExample == 1) {
            //Logic Variable example
            Thread thread1 = new Thread(new LogicVarExampleThread());
            Thread thread2 = new Thread(new LogicVarExampleThread());
            thread1.start();
            thread2.start();
        }
        else if(numberOfExample == 2){
            //Switch example Thread
            Thread thread1 = new Thread(new SwitchingExampleThread());
            Thread thread2 = new Thread(new SwitchingExampleThread());
            thread1.start();
            thread2.start();
        }
    }
}
