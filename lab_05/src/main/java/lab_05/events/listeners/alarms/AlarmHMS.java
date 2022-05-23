/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.events.listeners.alarms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lab_05.events.listeners.IAlarmListener;
import lab_05.watches.classic_watches.IWatches;

/**
 *
 * @author AlibekovMurad5202
 */
@Entity(name = "Alarms_HMS")
@Table(name = "Alarms_HMS")
public class AlarmHMS implements IAlarm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    public int id;
    
    @Column (name = "hours")
    public int h = 0;
    
    @Column (name = "minutes")
    public int m = 0;
    
    @Column (name = "seconds")
    public int s = 0;
    
    public AlarmHMS() {
    }
    
    public AlarmHMS(int h, int m, int s) {
        this.h = h;
        this.m = m;
        this.s = s;
    }
    
    @Override
    public void handleEvent(IWatches watches) throws Exception {
        if ((this.h == watches.getHours())
          && this.m == watches.getMinutes()
          && this.s == watches.getSeconds()) {
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
