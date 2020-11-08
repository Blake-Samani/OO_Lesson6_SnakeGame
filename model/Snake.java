package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import model.observerPattern.Observer;
import model.observerPattern.Subject;
import model.strategyPattern.SnakeMoveAliveStrategy;
import model.strategyPattern.SnakeMoveDeadStrategy;
import model.strategyPattern.SnakeMoveStrategy;
import model.strategyPattern.SnakeRenderAliveStrategy;
import model.strategyPattern.SnakeRenderDeadStrategy;
import model.strategyPattern.SnakeRenderStrategy;
import view.GameBoard;

public class Snake extends GameElement implements Subject {

    public enum Direction {
        LEFT, RIGHT, UP, DOWN
    }

    public enum Event {
        Atefood, Atepoison, LeftScene, SelfCollision
    }

    
    public ArrayList<GameElement> composite = new ArrayList<>(); // holds game elements
    private ArrayList<Observer> observers = new ArrayList<>(); 

    private final int INIT_XLOC = GameBoard.CELL_SIZE * 7; // starting locations of the snake
    private final int INIT_YLOC = GameBoard.CELL_SIZE * 3;
    private final int INIT_BODY_SIZE = 4; // initial size of snake

    public Direction direction = Direction.RIGHT; // starting direction of snake

    private SnakeMoveStrategy moveStrategy; // Reference variable!!!!!!!!!!!!!!!! of our strategy
    private SnakeRenderStrategy renderStrategy;// ""

    public void init() { // we will use init to reset the position of snake
        direction = Direction.RIGHT;
        composite.clear();
        super.x = INIT_XLOC;
        super.y = INIT_YLOC;
        var head = new SnakeHead(super.x, super.y);
        head.color = Color.yellow;
        composite.add(head); // head location and snake location are the same
        for (int i = 1; i < INIT_BODY_SIZE; i++) {
            int x = INIT_XLOC - i * GameBoard.CELL_SIZE; // the body location is one cell behind the current location
                                                         // therefore i * cell size
            int y = INIT_YLOC;
            var body = new SnakeBody(x, y);
            body.color = Color.white;
            composite.add(body);
        }

        moveStrategy = new SnakeMoveAliveStrategy(this); // initialize our strategy // we will change the strategy based
                                                         // on dead or alive
        renderStrategy = new SnakeRenderAliveStrategy(this);
        // moveStrategy = new SnakeMoveDeadStrategy(this);
        // renderStrategy = new SnakeRenderDeadStrategy(this);

    }

    public void setMoveStrategy(SnakeMoveStrategy moveStrategy) { // when snake is initialized, we need to call set move
                                                                  // strategy
        this.moveStrategy = moveStrategy;
    }

    public void setRenderStrategy(SnakeRenderStrategy renderStrategy) {
        this.renderStrategy = renderStrategy;
    }
    @Override
    public void render(Graphics2D g2) {
        this.renderStrategy.renderAlgorithm(g2);

    }

    @Override
    public void move() {
        this.moveStrategy.moveAlgorithm(); // calls the move algorithm of the current strategy
        // When we INIT with SnakeMoveAliveStrategy, this move algorithm for this
        // strategy will be called.

    }

    public ArrayList<GameElement> getComposite() {
        return composite;
    }

    @Override
    public void addSnakeListener(Observer o) {
        observers.add(o);

    }

    @Override
    public void removeSnakeListener(Observer o) {
        observers.remove(o);

    }

    @Override
    public void notifiyObservers(Event event) { // we will call this when events happen such as snakes eating food, collisions, etc
        switch(event){
            case Atefood:
            for(var o: observers){
                o.snakeAteFood();
            }
                break;
            case Atepoison:
                break;
            case LeftScene:
            for(var o: observers){
                o.snakeLeftScene();
            }
                break;
            case SelfCollision:
                for(var o: observers){
                    o.snakeSelfCollision();
                }
                break;

        }
    }

    public boolean selfCollision(){
        GameElement head = composite.get(0); //Head starts at index zero
        for(int i = 1; i < composite.size(); i++){
            var body = composite.get(i); //get the body
            if(head.collideWith(body)) return true; //if the head collides with body return true
        }
        return false;
    }

   
   
    
}
