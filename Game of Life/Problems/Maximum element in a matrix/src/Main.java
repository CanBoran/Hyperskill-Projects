import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n =  sc.nextInt();
        int m = sc.nextInt();

        //int[][] arr = new int[n][m];

        int smallI = 0;
        int smallJ = 0;
        int smallest = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                //arr[i][j] = sc.nextInt();
                int curr = sc.nextInt();
                if(curr > smallest) {
                    smallI = i;
                    smallJ = j;
                    smallest = curr;
                }
            }
        }

        System.out.println(smallI + " " + smallJ);
    }
}