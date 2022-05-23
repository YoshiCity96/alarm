/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.watches;

import lab_05.watches.alarm_clocks.IAlarmClock;
import lab_05.watches.alarm_clocks.AlarmClockHM;
import lab_05.watches.alarm_clocks.AlarmClockHMS;

/**
 *
 * @author AlibekovMurad5202
 */
public class BAlarmClock {
    public static IAlarmClock build(WatchesType type, String brand, double price) {
        switch (type) {
            case AlarmClockHM:
                return new AlarmClockHM(brand, price);
            case AlarmClockHMS:
                return new AlarmClockHMS(brand, price);
            default:
                return null;
        }
    }
}
