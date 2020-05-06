package life;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Universe extends JPanel {

    final private int N;
    final private int M;
    final private int RECT_WIDTH = 20;
    final private int RECT_HEIGHT = RECT_WIDTH;
    private boolean world[][];
    private Random random;
    private int generation = 0;
    private int alive = 0;

    public Universe(int size) {
        N = size;
        M = size;
        world = new boolean[N][M];
        random = new Random();

        // Populate the universe
        for ( int i = 0; i < N; i++ ) {
            for ( int j = 0; j < M; j++ ) {
                world[i][j] = random.nextBoolean();
                alive += world[i][j]? 1 : 0;
            }
        }
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public boolean getCell(int n, int m) {
        return world[n][m];
    }

    public int getAlive() {
        return alive;
    }

    public Dimension getPreferredSize() {
        return new Dimension(10 * 20, 200);
    }

    public boolean[][] getWorld(){
        return world;
    }

    public int getGeneration() {
        return generation;
    }

    public void nextWorld() {
        boolean[][] newWorld = new boolean[M][N];
        alive = 0;
        for ( int i = 0; i < N; i++ ) {
            for ( int j = 0; j < M; j++ ) {
                newWorld[i][j] = isAlive(i,j);
                alive += newWorld[i][j]? 1 : 0;
            }
        }
        generation++;
        world = newWorld;
    }

    // NW  N  NE
    // W   X   E
    // SW  S  SE
    private boolean isAlive(int n, int m) {

        int sum = 0;

        int[] offsetNW = new int[]{n - 1, m - 1};
        int[] offsetN = new int[]{n - 1, m};
        int[] offsetNE = new int[]{n - 1, m + 1};
        int[] offsetW = new int[]{n, m - 1};
        int[] offsetE = new int[]{n, m + 1};
        int[] offsetSW = new int[]{n + 1, m - 1};
        int[] offsetS = new int[]{n + 1, m};
        int[] offsetSE = new int[]{n + 1, m + 1};

        if (n == 0 && m == 0) {
            offsetNW[0] = N - 1;
            offsetN[0] = N - 1;
            offsetNE[0] = N - 1;
            offsetNW[1] = M - 1;
            offsetW[1] = M - 1;
            offsetSW[1] = M - 1;
        } else if (n + 1 == N && m == 0) {
            offsetSW[0] = 0;
            offsetS[0] = 0;
            offsetSE[0] = 0;
            offsetNW[1] = M - 1;
            offsetW[1] = M - 1;
            offsetSW[1] = M - 1;
        } else if (n == 0 && m + 1 == M) {
            offsetNW[0] = N - 1;
            offsetN[0] = N - 1;
            offsetNE[0] = N - 1;
            offsetNE[1] = 0;
            offsetE[1] = 0;
            offsetSE[1] = 0;
        } else if (n + 1 == N && m + 1 == M) {
            offsetNE[1] = 0;
            offsetE[1] = 0;
            offsetSE[1] = 0;
            offsetSE[0] = 0;
            offsetS[0] = 0;
            offsetSW[0] = 0;
        } else if (n == 0) {
            offsetNW[0] = N - 1;
            offsetN[0] = N - 1;
            offsetNE[0] = N - 1;
        } else if (m == 0) {
            offsetNW[1] = M - 1;
            offsetW[1] = M - 1;
            offsetSW[1] = M - 1;
        } else if (n + 1 == N) {
            offsetSW[0] = 0;
            offsetS[0] = 0;
            offsetSE[0] = 0;
        } else if (m + 1 == M) {
            offsetNE[1] = 0;
            offsetE[1] = 0;
            offsetSE[1] = 0;
        }
        sum += world[offsetNW[0]][offsetNW[1]] ? 1 : 0;  // NW
        sum += world[offsetN[0]][offsetN[1]] ? 1 : 0;      // N
        sum += world[offsetNE[0]][offsetNE[1]] ? 1 : 0;  // NE
        sum += world[offsetW[0]][offsetW[1]] ? 1 : 0;      // W
        sum += world[offsetE[0]][offsetE[1]] ? 1 : 0;      // E
        sum += world[offsetSW[0]][offsetSW[1]] ? 1 : 0;  // SW
        sum += world[offsetS[0]][offsetS[1]] ? 1 : 0;      // S
        sum += world[offsetSE[0]][offsetSE[1]] ? 1 : 0;  // SE

        if (sum == 2 && world[n][m]) {
            return true;
        } else if (sum == 3) {
            return true;
        } else {
            return false;
        }
    }

    public void printUniverse() {
        for(int i = 0; i < world.length; i++) {
            for(int j = 0; j < world.length; j++) {
                if(world[i][j]) {
                    System.out.print('O');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.printf("\n");
        }
    }
}
