public static void findAndStartThread(Thread... threads) throws InterruptedException {
    // implement this method

        for(Thread t: threads) {
            if(t.getState().equals(Thread.State.NEW)){
                t.start();
                t.interrupt();
                t.join();
            }
        }
}