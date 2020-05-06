package life;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLife extends JFrame {

    private JLabel generationLabel;
    private JLabel aliveLabel;
    private JPanel universePanel;
    private final int SIZE = 100;
    private final int MARGIN = 10;
    final private int RECT_WIDTH = 8;
    final private int RECT_HEIGHT = RECT_WIDTH;
    private boolean[][] world;

    public GameOfLife() {
        super("Game of Life");

        JPanel statPanel = new JPanel();
        statPanel.setLayout(new BoxLayout(statPanel, BoxLayout.Y_AXIS));
        generationLabel = new JLabel("Generation #0");
        generationLabel.setName("GenerationLabel");
        generationLabel.setHorizontalTextPosition(MARGIN);
        aliveLabel = new JLabel("Alive: 0");
        aliveLabel.setName("AliveLabel");
        generationLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        aliveLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel buttonPanel = new JPanel();
        JToggleButton playButton = new JToggleButton();
        JToggleButton pauseButton = new JToggleButton();
        JButton resetButton = new JButton();
        playButton.setText("Play");
        pauseButton.setText("Pause");
        resetButton.setText("Reset");
        playButton.setName("PlayToggleButton");
        resetButton.setName("ResetButton");
        buttonPanel.add(playButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(resetButton);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(MARGIN ,MARGIN, 0, 0));

        statPanel.add(generationLabel);
        statPanel.add(aliveLabel);
        sidePanel.add(buttonPanel);
        sidePanel.add(statPanel);

        universePanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // draw the rectangle here
                g.setColor(Color.black); // change colour
                for ( int i = 0; i < SIZE; i++ ) {
                    for ( int j = 0; j < SIZE; j++ ) {
                        if(world[i][j]) {
                            g.drawRect(RECT_WIDTH * i + MARGIN, RECT_HEIGHT * j + MARGIN, RECT_WIDTH, RECT_HEIGHT);
                            g.fillRect(RECT_WIDTH * i + MARGIN, RECT_HEIGHT * j + MARGIN, RECT_WIDTH, RECT_HEIGHT);
                        } else {
                            g.drawRect(RECT_WIDTH * i + MARGIN, RECT_HEIGHT * j + MARGIN, RECT_WIDTH, RECT_HEIGHT);
                        }

                    }
                }

            }
        };
        setLayout(new BorderLayout());
        add(sidePanel, BorderLayout.WEST);
        add(universePanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 500);
        setVisible(true);

        GameOfLifeController golc = new GameOfLifeController(this, SIZE);
        Thread controllerThread = new Thread(golc);
        controllerThread.start();

        playButton.addActionListener(e -> {
            golc.play();
            pauseButton.setSelected(false);
        });
        pauseButton.addActionListener(e -> {
            golc.pause();
            playButton.setSelected(false);
        });
        resetButton.addActionListener(e -> {
            golc.reset();
        });
    }

    public void setGenerationLabel(int genCount) {
        generationLabel.setText("Generation #" + genCount);
    }

    public void setAliveLabel(int aliveCount) {
        aliveLabel.setText("Alive: " + aliveCount);
    }

    public void drawWorld(boolean[][] world) {
        this.world = world;
        universePanel.setPreferredSize(new Dimension(SIZE * RECT_WIDTH + 20, SIZE * RECT_HEIGHT + 20));

        universePanel.repaint();
        pack();
    }

    public Dimension getPreferedSize() {
        return new Dimension(SIZE * RECT_WIDTH + MARGIN * 2, SIZE * RECT_HEIGHT + MARGIN * 2);

    }
}

