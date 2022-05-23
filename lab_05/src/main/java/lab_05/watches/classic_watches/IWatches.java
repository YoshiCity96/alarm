/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.watches.classic_watches;

import lab_05.events.listeners.IWatchesEventListener;

/**
 *
 * @author AlibekovMurad5202
 */
public interface IWatches {
    void setHours(int hours) throws Exception;
    void setMinutes(int minutes) throws Exception;
    void setSeconds(int seconds) throws Exception;
    
    String getBrand();
    double getPrice();
    int getHours();
    int getMinutes();
    int getSeconds() throws Exception;
    
    void addEvent(IWatchesEventListener event);
    void removeEvent(IWatchesEventListener event);
    
    void increaseTime() throws Exception;
    int getDelay();
}
