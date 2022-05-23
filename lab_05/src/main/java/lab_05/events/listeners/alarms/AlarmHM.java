/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.events.listeners.alarms;

import javax.persistence.*;
import lab_05.events.listeners.IAlarmListener;
import lab_05.watches.classic_watches.IWatches;

/**
 *
 * @author AlibekovMurad5202
 */

@Entity(name = "Alarms_HM")
@Table(name = "Alarms_HM")
public class AlarmHM implements IAlarm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    public int id;
    
    @Column (name = "hours")
    public int h = 0;
    
    @Column (name = "minutes")
    public int m = 0;
    
    public AlarmHM() {
    }
    
    public AlarmHM(int h, int m) {
        this.h = h;
        this.m = m;
    }
    
    @Override
    public void handleEvent(IWatches watches) throws Exception {
        if ((this.h == watches.getHours()) && (this.m == watches.getMinutes())) {
            update(watches);
        }
    }
    
    @Override
    public void update(IWatches watches) throws Exception {
        for (IAlarmListener eventListener : alarmListeners) {
            eventListener.handleEvent(watches, this);
            removeListener(eventListener);
        }
    }

    @Override
    public void addListener(IAlarmListener listener) {
        alarmListeners.add(listener);
    }

    @Override
    public void removeListener(IAlarmListener listener) {
        alarmListeners.remove(listener);
    }
}
