/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.watches.alarm_clocks;

import lab_05.events.listeners.alarms.AlarmHMS;
import lab_05.events.listeners.alarms.IAlarm;
import lab_05.watches.classic_watches.WatchesHMS;

/**
 *
 * @author AlibekovMurad5202
 */
public class AlarmClockHMS extends WatchesHMS implements IAlarmClock {
    
    public AlarmClockHMS(String brand, double price) {
        super(brand, price);
    }
    
    @Override
    public void addAlarm(int ... time) throws Exception {
        if (time.length != 3)
            throw new Exception("Bad alarm time!");
        int h = time[0];
        int m = time[1];
        int s = time[2];
        checkHMS(h, m, s);
        events.addListener(new AlarmHMS(h, m, s));
    }

    @Override
    public void removeAlarm(int ... time) throws Exception {
        if (time.length != 3)
            throw new Exception("Bad alarm time!");
        int h = time[0];
        int m = time[1];
        int s = time[2];
        checkHMS(h, m, s);
        events.removeListener(new AlarmHMS(h, m, s));
    }
    
    private void checkHMS(int h, int m, int s) throws Exception {
        if ((h < 0) || (h > 11)) {
            throw new Exception("Invalid value of hours");
        }
        if ((m < 0) || (m > 59)) {
            throw new Exception("Invalid value of minutes");
        }
        if ((s < 0) || (s > 59)) {
            throw new Exception("Invalid value of seconds");
        }
    }
    
    @Override
    public void addAlarm(IAlarm alarm) throws Exception {
        events.addListener(alarm);
    }

    @Override
    public void removeAlarm(IAlarm alarm) throws Exception {
        events.removeListener(alarm);
    }
}
