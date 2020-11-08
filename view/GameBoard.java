package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Random;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.ButtonClickListener;
import controller.KeyController;
import controller.TimerListener;
import model.Food;
import model.Snake;
import model.observerPattern.SnakeObserver;

public class GameBoard {
    
    public static final int WIDTH = 600; //Since we will be using cells, we must make sure the canvas can fit the cells. 
    public static final int HEIGHT = 400; //Our cells are 20 x 20, so the width and height of the canvas must be a multiple of 20
    public static final int FPS = 4; //frames per second
    public static final int DELAY = 1000 / FPS; // milliseconds, timespan for each action
    public static final int CELL_SIZE = 20;

    private JFrame window;
    private MyCanvas canvas;
    private JButton startButton = new JButton("Start");
    private JButton stopButton = new JButton("Stop");
    private JButton exitButton = new JButton("Exit");
    private JLabel scoreDisplay = new JLabel();
    private int score;
    private Timer timer;
    private boolean gameOver;

    private Snake snake = new Snake();

    public GameBoard(JFrame window){
        this.window = window;
    }

    public void init(){

        Container cp = window.getContentPane();

        canvas = new MyCanvas(this, WIDTH, HEIGHT);
        cp.add(BorderLayout.CENTER, canvas);

        JPanel southPanel = new JPanel();
        southPanel.add(startButton);
        southPanel.add(stopButton);
        southPanel.add(exitButton);
        cp.add(BorderLayout.SOUTH, southPanel);

        JPanel northPanel = new JPanel();
        JLabel label = new JLabel("Score: ");
        scoreDisplay.setText("" + score);
        northPanel.add(label);
        northPanel.add(scoreDisplay);
        cp.add(BorderLayout.NORTH, northPanel);

        Text t1 = new Text("Click <Start> to play", 100, 100);
        t1.color = Color.yellow;
        canvas.getFigures().add(t1);

        ButtonClickListener buttonListener = new ButtonClickListener(this);
        startButton.addActionListener(buttonListener);
        stopButton.addActionListener(buttonListener);
        exitButton.addActionListener(buttonListener);

        KeyController keyContoller = new KeyController(this);
        canvas.addKeyListener(keyContoller);
        canvas.requestFocusInWindow(); // allows canvas to respond and use key events or mouse events
        canvas.setFocusable(true);

        //disable focusable in all other components, otherwise if you click other buttons or keys, the focus is lost and the movement keys will not work on the canvas
        startButton.setFocusable(false);
        stopButton.setFocusable(false);
        exitButton.setFocusable(false);
        label.setFocusable(false);
        scoreDisplay.setFocusable(false);

        SnakeObserver observer = new SnakeObserver(this);
        snake.addSnakeListener(observer);

        timer = new Timer(DELAY, new TimerListener(this));
        timer.start(); // every DELAY, the action performed method in our listener will be called

    }
    public void createFood(){ //Create food at a location that is not the same as our snake
        Random random = new Random();
        int xloc, yloc;
        do {
            xloc = random.nextInt(GameBoard.WIDTH /GameBoard.CELL_SIZE) * GameBoard.CELL_SIZE;
            yloc = random.nextInt(GameBoard.HEIGHT /GameBoard.CELL_SIZE) * GameBoard.CELL_SIZE;
        } while(xloc == snake.x && yloc == snake.y); // will repeat the randomization if we end up at the same location as the snake

        Food food = new Food(xloc, yloc, Color.pink);
        canvas.getFigures().add(food);// adds to our game element array list on canvas
    }

    public MyCanvas getCanvas() {
        return canvas;
    }
    public Snake getSnake() {
        return snake;
    }
    public JButton getStartButton() {
        return startButton;
    }
    public JButton getExitButton() {
        return exitButton;
    }
    public JButton getStopButton() {
        return stopButton;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public JLabel getScoreDisplay() {
        return scoreDisplay;
    }
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    public boolean isGameOver() { //use is instaed of get for booleans
        return gameOver;
    }
    public Timer getTimer() {
        return timer;
    }
}
