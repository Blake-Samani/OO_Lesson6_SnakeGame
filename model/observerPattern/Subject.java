package model.observerPattern;

import model.Snake;

public interface Subject {

    void addSnakeListener(Observer o);
    void removeSnakeListener(Observer o);
    void notifiyObservers(Snake.Event event);
    
}
