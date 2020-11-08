package model;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Graphics;

public abstract class GameElement {

    public int x;
    public int y;
    public Color color;
    public boolean filled;

    public GameElement(){
        this(0, 0);
    }

    public GameElement(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean collideWith(GameElement another){ //collision detection basically detects cell by cell, recall our 20 x 20 cells. If the gameElement is touching the game element passed into the function, we have a collision
        if (this.x == another.x && this.y == another.y){
            return true;
        }else{
            return false;
        }
    }
    
    public abstract void render(Graphics2D g2);
    public abstract void move();


}
