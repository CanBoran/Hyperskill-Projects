package life;

import static java.lang.Thread.interrupted;
import static java.lang.Thread.sleep;

public class GameOfLifeController implements Runnable {

    private final int size;
    private GameOfLife view;
    private Universe model;
    private boolean[][] world;
    private boolean paused = true;

    public GameOfLifeController(GameOfLife view, int size) {
        this.view = view;
        this.size = size;
        model = new Universe(size);
        view.drawWorld(model.getWorld());
    }

    public void update() {
        model.nextWorld();
        view.drawWorld(model.getWorld());
        view.setGenerationLabel(model.getGeneration());
        view.setAliveLabel(model.getAlive());
    }

    @Override
    public void run() {
        while(!interrupted()) {
            while (!paused) {
                update();
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void play() {
        paused = false;
    }

    public void pause() {
        paused = true;
    }

    public void reset() {
        model = new Universe(size);
        view.drawWorld(model.getWorld());
    }
}
