/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.events.publishers;

import java.util.ArrayList;
import lab_05.database.DBManager;
import lab_05.events.listeners.IAlarmListener;
import lab_05.events.listeners.IWatchesEventListener;
import lab_05.events.listeners.alarms.IAlarm;
import lab_05.server.ModelDataObject;
import lab_05.server.Request;
import lab_05.watches.BAlarmClock;
import lab_05.watches.WatchesType;
import lab_05.watches.alarm_clocks.IAlarmClock;
import lab_05.watches.classic_watches.IWatches;

/**
 *
 * @author AlibekovMurad5202
 */
public class ServerModel extends ServerEventPublisher 
        implements IWatchesEventListener, IAlarmListener {
    public ArrayList<ModelDataObject> clocks = new ArrayList<>();
    DBManager db = new DBManager();
    
    public ServerModel() {
        ArrayList<IAlarm> alarmsHM = db.fetchAlarmsHM();
        if (!alarmsHM.isEmpty()) {
            addClock(BAlarmClock.build(WatchesType.AlarmClockHM, "0", 0));
            int clockID = clocks.size() < 1 ? 0 : clocks.size() - 1;
            Request newReq = new Request();
            newReq.setRequestType(0);
            newReq.setWatchesID(clockID);
            allRequests.addRequest(newReq);
            for (IAlarm alarm : alarmsHM) {
                Request newAlarmReq = new Request();
                newAlarmReq.setRequestType(2);
                newAlarmReq.setWatchesID(clockID);
                
                alarm.addListener(this);
                clocks.get(clockID).addAlarm(alarm);
                update();
            }
        }
        
        ArrayList<IAlarm> alarmsHMS = db.fetchAlarmsHMS();
        if (!alarmsHMS.isEmpty()) {
            addClock(BAlarmClock.build(WatchesType.AlarmClockHMS, "0", 0));
            int clockID = clocks.size() < 1 ? 0 : clocks.size() - 1;
            Request newReq = new Request();
            newReq.setRequestType(1);
            newReq.setWatchesID(clockID);
            allRequests.addRequest(newReq);
            for (IAlarm alarm : alarmsHMS) {
                Request newAlarmReq = new Request();
                newAlarmReq.setRequestType(3);
                newAlarmReq.setWatchesID(clockID);
                
                alarm.addListener(this);
                clocks.get(clockID).addAlarm(alarm);
                update();
            }
        }
    }
    
    public void addClock(IAlarmClock clock) {
        ModelDataObject modelClock = new ModelDataObject(clock);
        clocks.add(modelClock);
        clocks.get(clocks.size() - 1).clock.addEvent(this);
        update();
    }
        
    public void addAlarm(IAlarm alarm, int clockID) {
        alarm.addListener(this);
        clocks.get(clockID).addAlarm(alarm);
        db.insertAlarm(alarm);
        update();
    }
    
    public void deleteAlarm(IAlarm alarm, int clockID) {
        clocks.get(clockID).deleteAlarm(alarm);
        db.deleteAlarm(alarm);
        update();
    }

    @Override
    public void handleEvent(IWatches watches) throws Exception {
        for (int i = 0; i < clocks.size(); i++) {
            if (clocks.get(i).clock == watches) {
                Request req = new Request();
                req.setWatchesID(i);
                req.setH(watches.getHours());
                req.setM(watches.getMinutes());
                try {
                    req.setS(watches.getSeconds());
                } catch (Exception ex) {
                } finally {
                    req.setRequestType(11);
                    allRequests.addRequest(req);
                }
            }
        }
        update();
    }

    @Override
    public void handleEvent(IWatches watches, IAlarm alarm) throws Exception {
        for (int i = 0; i < clocks.size(); i++) {
            if (clocks.get(i).clock == watches) {
                Request req = new Request();
                req.setWatchesID(i);
                req.setH(watches.getHours());
                req.setM(watches.getMinutes());
                try {
                    req.setS(watches.getSeconds());
                } catch (Exception ex) {
                } finally {
                    deleteAlarm(alarm, i);
                    req.setRequestType(10);
                    allRequests.addRequest(req);
                }
            }
        }
        update();
    }
}
