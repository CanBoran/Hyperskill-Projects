public class Main {

    public static void main(String[] args) {

        // create instances and start threads here
        Thread tr1 = new WorkerThread();
        tr1.setName("worker-1");
        Thread tr2 = new WorkerThread();
        tr2.setName("worker-2");

        tr1.start();
        tr2.start();
    }
}