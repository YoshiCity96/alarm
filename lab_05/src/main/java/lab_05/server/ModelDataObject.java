/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.server;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab_05.controllers.threads.IWatchesThreadController;
import lab_05.controllers.threads.WatchesThreadController;
import lab_05.events.listeners.alarms.IAlarm;
import lab_05.watches.alarm_clocks.IAlarmClock;

/**
 *
 * @author AlibekovMurad5202
 */
public class ModelDataObject {
    public IAlarmClock clock;
    public IWatchesThreadController iwtc;
    public ArrayList<IAlarm> alarms = new ArrayList<>();
    
    public ModelDataObject(IAlarmClock clock) {
        this.clock = clock;
        iwtc = new WatchesThreadController(this.clock);
    }
    
    public void addAlarm(IAlarm alarm) {
        alarms.add(alarm);
        try {
            clock.addAlarm(alarm);
        } catch (Exception ex) {
            Logger.getLogger(ModelDataObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteAlarm(IAlarm alarm) {
        alarms.remove(alarm);
        try {
            clock.removeAlarm(alarm);
        } catch (Exception ex) {
            Logger.getLogger(ModelDataObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
