package view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MyCanvas extends JPanel{

   
    private GameBoard gameBoard;
    
    public MyCanvas(GameBoard gameBoard, int width, int height){
        this.gameBoard = gameBoard;
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK); //color of canvas
    }


@Override
public void paintComponent(Graphics g){
    super.paintComponent(g);

    Graphics g2 = (Graphics2D) g;
}

}