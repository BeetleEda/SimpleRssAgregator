/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Евдокимова
 */
public class MessageBus {

    private static MessageBus instance;

    public static synchronized MessageBus getInstance() {
        if (instance == null) {
            instance = new MessageBus();
        }
        return instance;
    }

    private List<IObserver> observers = new ArrayList<>();

    public void subscribe(IObserver obj) {
        if (obj != null) {
            if (!observers.contains(obj)) {
                observers.add(obj);
            }
        }

    }

    public void unsubscribe(IObserver obj) {
        observers.remove(obj);
    }

    public void sendMessage() {
        for (IObserver obj : observers) {
            obj.update();
        }
    }
}
