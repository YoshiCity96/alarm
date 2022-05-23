/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.events.publishers;

import java.util.LinkedList;
import lab_05.events.listeners.IWatchesEventListener;
import lab_05.watches.classic_watches.IWatches;

/**
 *
 * @author AlibekovMurad5202
 */
public class WatchesEventPublisher implements IWatchesEventPublisher {
    public LinkedList<IWatchesEventListener> listeners = new LinkedList<>();
    
    // subscribe or addEvent
    @Override
    public void addListener(IWatchesEventListener listener) {
        listeners.add(listener);
    }

    // unsubscribe or removeEvent
    @Override
    public void removeListener(IWatchesEventListener listener) {
        listeners.remove(listener);
    }

    // notify
    @Override
    public void update(IWatches watches) throws Exception {
        for (IWatchesEventListener eventListener : listeners) {
            eventListener.handleEvent(watches);
        }
    }
}
