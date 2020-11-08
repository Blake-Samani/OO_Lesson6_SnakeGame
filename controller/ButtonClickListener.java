package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import view.GameBoard;

public class ButtonClickListener implements ActionListener {

    private GameBoard gameBoard;

    public ButtonClickListener(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton)e.getSource(); //we can only do this because we know all buttons are jbutton types, otherwise use if(e.getSource = gameboard.getsomebutton)
        if (button== gameBoard.getStartButton()){
            gameBoard.getCanvas().getFigures().clear();//clear the figures array list off the screen
            gameBoard.getSnake().init(); //start a new snake at some location
            gameBoard.getCanvas().getFigures().add(gameBoard.getSnake()); //our initialized snake from gameboard is now added to the figures array list
            gameBoard.createFood(); //create food when we hit start button
            gameBoard.setScore(0);
            gameBoard.setGameOver(false);
            
        }else if (button == gameBoard.getStopButton()){
            String label = button.getText();
            if(label.equals("Stop")){
                button.setText("Resume");
                gameBoard.getTimer().stop(); //stops the time
            }else{
                //current label is "Resume"
                button.setText("Stop");
                gameBoard.getTimer().start();
            }
        }else if( button == gameBoard.getExitButton()){
            System.exit(0); //operating system command that will exit the program
        }

    }
    
}
