class StringProcessor extends Thread {

    final Scanner scanner = new Scanner(System.in); // use it to read string from the standard input

    @Override
    public void run() {
        while (scanner.hasNext()) {
            String str = scanner.next();
            if ( str.toUpperCase().equals(str)) {
                System.out.println("FINISHED");
            } else {
                System.out.println(str.toUpperCase());
            }
        }
    }
}