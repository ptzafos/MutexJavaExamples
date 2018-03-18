
public class Main {

    public static void main(String Args[]){

        int numberOfExample = 1;

        if(numberOfExample == 1) {
            //Logic Variable example
            Thread thread1 = new Thread(new LogicVarExampleThread());
            Thread thread2 = new Thread(new LogicVarExampleThread());
            thread1.start();
            thread2.start();
        }
        else if(numberOfExample == 2){

        }
    }
}
