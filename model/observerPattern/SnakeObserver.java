package model.observerPattern;

import model.Snake;
import model.strategyPattern.SnakeMoveDeadStrategy;
import model.strategyPattern.SnakeRenderDeadStrategy;
import view.GameBoard;
import view.Text;

public class SnakeObserver implements Observer {

    private GameBoard gameBoard;

    public SnakeObserver(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    @Override
    public void snakeAteFood() {    //update the score when snake eats food
        int score = gameBoard.getScore();
        ++score;
        gameBoard.setScore(score);
        gameBoard.getScoreDisplay().setText("" + score);
    }

    @Override
    public void snakeAtePoison() {
        // TODO Auto-generated method stub

    }

    @Override
    public void snakeLeftScene() { //what happens when the snake leaves the scene
       gameBoard.getCanvas().getFigures().add(new Text("Game Over - Out of Bound", 100, 200));
       Snake snake = gameBoard.getSnake();
       snake.setMoveStrategy(new SnakeMoveDeadStrategy(snake));
       snake.setRenderStrategy(new SnakeRenderDeadStrategy(snake));

    }

    @Override
    public void snakeSelfCollision() { //what happens when the snake collides with itself
        gameBoard.getCanvas().getFigures().add(new Text("Game Over - Self Collision", 100, 200));// add text to our game elements
        Snake snake = gameBoard.getSnake(); //get the snake object
        snake.setMoveStrategy(new SnakeMoveDeadStrategy(snake)); //change strategies
        snake.setRenderStrategy(new SnakeRenderDeadStrategy(snake));

    }
    
}
