/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.events.publishers;

import java.util.LinkedList;
import lab_05.events.listeners.IAlarmListener;
import lab_05.watches.classic_watches.IWatches;

/**
 *
 * @author AlibekovMurad5202
 */
public interface IAlarmPublisher {
    public LinkedList<IAlarmListener> alarmListeners = new LinkedList<>();
    
    void update(IWatches watches) throws Exception;
    void addListener(IAlarmListener listener);
    void removeListener(IAlarmListener listener);
}
