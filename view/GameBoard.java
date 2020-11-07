package view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameBoard {
    
    public static final int WIDTH = 600; //Since we will be using cells, we must make sure the canvas can fit the cells. 
    public static final int HEIGHT = 400; //Our cells are 20 x 20, so the width and height of the canvas must be a multiple of 20

    private JFrame window;
    private MyCanvas canvas;
    private JButton startButton = new JButton("Start");
    private JButton stopButton = new JButton("Stop");
    private JButton exitButton = new JButton("Exit");
    private JLabel scoreDisplay = new JLabel();

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
        northPanel.add(label);
        northPanel.add(scoreDisplay);
        cp.add(BorderLayout.NORTH, northPanel);

    }
}
