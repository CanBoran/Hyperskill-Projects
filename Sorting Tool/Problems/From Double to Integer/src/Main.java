import java.util.*;

public class Main {

    public static int convert(Double val) {
        if (val.isNaN()) {
            return 0;
        } else if (Objects.equals(Double.POSITIVE_INFINITY,val)) {
            return Integer.MAX_VALUE;
        } else if (Objects.equals(Double.NEGATIVE_INFINITY, val)) {
            return Integer.MIN_VALUE;
        } else {
            return val.intValue();
        }
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Double doubleVal = new Double(scanner.nextDouble() / scanner.nextDouble());
        System.out.println(convert(doubleVal));
    }
}