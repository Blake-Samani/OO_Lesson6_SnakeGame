package model.strategyPattern;

import model.Snake;
import view.GameBoard;

public class SnakeMoveDeadStrategy implements SnakeMoveStrategy {

    private Snake snake;

    public SnakeMoveDeadStrategy(Snake snake){
        this.snake = snake;
    }

    @Override
    public void moveAlgorithm() {
        var composite = snake.getComposite(); // get the array list that contains snake body and head.
        for(var s: composite){
            s.y += GameBoard.CELL_SIZE; //here snake keeps falling and updates the composite arraylist accordingly
        }
        snake.y = composite.get(0).y; //update the location in the snake with our new y location
        if(snake.y >= GameBoard.HEIGHT){ //gameboard.height is bottom of screen
            for(var s: composite){ // when the snake has fallen to the vottom of the screen, we reset the location at zero to make it start at the top again and keep falling
                s.y = 0;
            }
        }
        snake.y = 0; //update y location in the snake object
    }
    
}
