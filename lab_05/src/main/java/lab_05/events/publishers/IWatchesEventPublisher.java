/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.events.publishers;

import lab_05.events.listeners.IWatchesEventListener;
import lab_05.watches.classic_watches.IWatches;

/**
 *
 * @author AlibekovMurad5202
 */
public interface IWatchesEventPublisher {
    void update(IWatches watches) throws Exception;
    void addListener(IWatchesEventListener listener);
    void removeListener(IWatchesEventListener listener);
}
