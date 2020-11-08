package view;

import javax.swing.JPanel;

import model.GameElement;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class MyCanvas extends JPanel{

   
    private GameBoard gameBoard;
    
    private ArrayList<GameElement> figures = new ArrayList<>(); // Anything you want to display on the screen must be a game element type
    
    public MyCanvas(GameBoard gameBoard, int width, int height){
        this.gameBoard = gameBoard;
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK); //color of canvas
    }


@Override
public void paintComponent(Graphics g){
    super.paintComponent(g);

    Graphics g2 = (Graphics2D) g;

    for(var f: figures){
        f.render((Graphics2D) g2);
    }
}

public ArrayList<GameElement> getFigures() {
    return figures;
}

}