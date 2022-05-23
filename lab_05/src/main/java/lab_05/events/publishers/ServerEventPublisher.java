/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.events.publishers;

import lab_05.events.listeners.IServerEventListener;

/**
 *
 * @author AlibekovMurad5202
 */
public class ServerEventPublisher implements IServerEventPublisher {
    @Override
    public void update() {
        for (IServerEventListener eventListener : allListeners) {
            eventListener.handleEvent(this);
        }
    }

    @Override
    public void addListener(IServerEventListener listener) {
        allListeners.add(listener);
    }

    @Override
    public void removeListener(IServerEventListener listener) {
        allListeners.remove(listener);
    }
}
