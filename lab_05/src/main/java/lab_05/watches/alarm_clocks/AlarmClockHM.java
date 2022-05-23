/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.watches.alarm_clocks;

import lab_05.events.listeners.alarms.AlarmHM;
import lab_05.events.listeners.alarms.IAlarm;
import lab_05.watches.classic_watches.WatchesHM;

/**
 *
 * @author AlibekovMurad5202
 */
public class AlarmClockHM extends WatchesHM implements IAlarmClock {

    public AlarmClockHM(String brand, double price) {
        super(brand, price);
    }
    
    @Override
    public void addAlarm(int ... time) throws Exception {
        if (time.length != 2)
            throw new Exception("Bad alarm time!");
        int h = time[0];
        int m = time[1];
        checkHM(h, m);
        events.addListener(new AlarmHM(h, m));
    }

    @Override
    public void removeAlarm(int ... time) throws Exception {
        if (time.length != 2)
            throw new Exception("Bad alarm time!");
        int h = time[0];
        int m = time[1];
        checkHM(h, m);
        events.removeListener(new AlarmHM(h, m));
    }
    
    private void checkHM(int h, int m) throws Exception {
        if ((h < 0) || (h > 11)) {
            throw new Exception("Invalid value of hours");
        }
        if ((m < 0) || (m > 59)) {
            throw new Exception("Invalid value of minutes");
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
