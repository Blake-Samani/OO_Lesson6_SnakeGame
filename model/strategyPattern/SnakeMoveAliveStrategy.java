package model.strategyPattern;

import model.Snake;
import view.GameBoard;

public class SnakeMoveAliveStrategy implements SnakeMoveStrategy {

    private Snake snake;

    public SnakeMoveAliveStrategy(Snake snake){ //snake reference
        this.snake = snake;
    }

    @Override
    public void moveAlgorithm() {
        var composite = snake.getComposite(); //gets our snake array list of GameElement type // this does not include the Snake Head since we start at i - 1
        for ( int i = composite.size()-1; i> 0; i--){ //composize size - 1 = last index
            composite.get(i).x = composite.get(i-1).x; // first iteration index three is copied into index 4 (snake cells)
            composite.get(i).y = composite.get(i-1).y;
        }

        switch(snake.direction){
            case LEFT:
                composite.get(0).x -= GameBoard.CELL_SIZE; //Going left move head to left by subracting a cell (20) from current x location, on the x axis 
                snake.x = composite.get(0).x; //update the location of snake
                break;
            case RIGHT:
                composite.get(0).x += GameBoard.CELL_SIZE;
                snake.x = composite.get(0).x;
                break;
            case UP:
                composite.get(0).y -= GameBoard.CELL_SIZE;
                snake.y = composite.get(0).y;
                break;
            case DOWN:
                composite.get(0).y += GameBoard.CELL_SIZE;
                snake.y = composite.get(0).y;
                break;
        }

    }
    
}
