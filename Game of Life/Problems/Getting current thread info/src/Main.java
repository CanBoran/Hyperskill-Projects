public static void printCurrentThreadInfo() {
    Thread tr = new Thread();
        System.out.println("name: " + tr.currentThread().getName());
        System.out.println("priority: " + tr.currentThread().getPriority());

        }