package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Food;
import model.GameElement;
import model.Snake;
import model.SnakeBody;
import model.Snake.Event;
import view.GameBoard;

public class TimerListener implements ActionListener {

    private GameBoard gameBoard;

    public TimerListener(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    //GAME LOOP
    @Override
    public void actionPerformed(ActionEvent e) {
        for (var f: gameBoard.getCanvas().getFigures()){
            f.move(); //move the figure
        }
        if(!gameBoard.isGameOver()){
            detectCollision(); //detect collisions
        }

        gameBoard.getCanvas().repaint(); //redraw
    }
    
    private void detectCollision(){
        var figures = gameBoard.getCanvas().getFigures(); //our array list of game elements
        //find snake
        Snake snake = null; 
        for (var f: figures){
            if(f instanceof Snake){
                snake = (Snake) f;
                break;
            }
        }
        if (snake == null) return;

        //left game scene?
        if(snake.x < 0 || snake.x >= gameBoard.WIDTH || snake.y < 0 || snake.y >= gameBoard.HEIGHT){
            snake.notifiyObservers(Event.LeftScene);
            gameBoard.setGameOver(true);
        }

        //self collision: head collides with any part of the body
        if(snake.selfCollision()){
            snake.notifiyObservers(Event.SelfCollision);
            gameBoard.setGameOver(true);
        }
        // snake vs food
        var removeFoods = new ArrayList<GameElement>();
        for(var f: figures){
            if (f instanceof Snake) continue;
            if (snake.collideWith(f)){
                if(f instanceof Food) {
                    removeFoods.add(f);
                    snake.getComposite().add(new SnakeBody(-100, -100)); // if we find food, we grow our body outside of the game scene and in the next frame we will add it to the body
                    snake.notifiyObservers(Event.Atefood);
                }
            }
        }

        if (removeFoods.size()>0){ //if at least 1 food is to be removed
            figures.removeAll(removeFoods); //remove all the elements that are in remove foods from the figures array list
            gameBoard.createFood();
        }
    }
}
