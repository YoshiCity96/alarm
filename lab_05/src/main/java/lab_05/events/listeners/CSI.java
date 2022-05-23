/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.events.listeners;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab_05.events.listeners.alarms.AlarmHM;
import lab_05.events.listeners.alarms.AlarmHMS;
import lab_05.events.publishers.ServerModel;
import lab_05.server.Request;
import lab_05.server.RequestsPool;
import lab_05.watches.BAlarmClock;
import lab_05.watches.WatchesType;
import lab_05.events.publishers.IServerEventPublisher;

/**
 *
 * @author AlibekovMurad5202
 */
public class CSI extends Thread implements IServerEventListener {
    Socket clientSocket;
    ServerModel model;
    
    OutputStream os;
    InputStream is;
    DataInputStream dis;
    DataOutputStream dos;
    
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    public CSI(Socket clientSocket, ServerModel model) {
        this.clientSocket = clientSocket;
        this.model = model;
        try {
            os = clientSocket.getOutputStream();
            dos = new DataOutputStream(os);
        } catch (IOException ex) {
            Logger.getLogger(CSI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.sendResponse(model.allRequests.getRequests());
        this.model.addListener(this);
        this.start();
    }
    
    @Override
    public void handleEvent(IServerEventPublisher model) {
        ArrayList<Request> lastReq = new ArrayList<>();
        lastReq.add(model.allRequests.getLastRequest());
        this.sendResponse(lastReq);
    }
    
    @Override
    public void run() {
        try {
            is = clientSocket.getInputStream();
            dis = new DataInputStream(is);
            
            while (true) {
                String msg = dis.readUTF();
                Request req = gson.fromJson(msg, Request.class);
                
                model.allRequests.addRequest(req);
                switch (req.getRequestType()) {
                    case 0:
                        System.out.println("req: " + req.toString());
                        int watchID_HM = model.clocks.size();
                        model.allRequests.getLastRequest().setWatchesID(watchID_HM);
                        model.addClock(BAlarmClock.build(WatchesType.AlarmClockHM, "$ " + watchID_HM + " $", 0));
                        model.clocks.get(watchID_HM).clock.setHours(req.getH());
                        model.clocks.get(watchID_HM).clock.setMinutes(req.getM());
                        model.clocks.get(watchID_HM).clock.addEvent(model);
                        break;
                    case 1:
                        System.out.println("req: " + req.toString());
                        int watchID_HMS = model.clocks.size();
                        model.allRequests.getLastRequest().setWatchesID(watchID_HMS);
                        model.addClock(BAlarmClock.build(WatchesType.AlarmClockHMS, "$ " + watchID_HMS + " $", 0));
                        model.clocks.get(watchID_HMS).clock.setHours(req.getH());
                        model.clocks.get(watchID_HMS).clock.setMinutes(req.getM());
                        model.clocks.get(watchID_HMS).clock.setSeconds(req.getS());
                        model.clocks.get(watchID_HMS).clock.addEvent(model);
                        break;
                    case 2:
                        System.out.println("req: " + req.toString());
                        AlarmHM alarm_HM = new AlarmHM(req.getH(), req.getM());
                        model.addAlarm(alarm_HM, req.getWatchesID());
                            break;
                    case 3:
                        System.out.println("req: " + req.toString());
                        AlarmHMS alarm_HMS = new AlarmHMS(req.getH(), req.getM(), req.getS());
                        model.addAlarm(alarm_HMS, req.getWatchesID());
                            break;
                    case 4:
                        System.out.println("req: " + req.toString());
                        model.clocks.get(req.getWatchesID()).iwtc.start();
                            break;
                    case 5:
                        System.out.println("req: " + req.toString());
                        model.clocks.get(req.getWatchesID()).iwtc.stop();
                            break;
                    case 6:
                        System.out.println("req: " + req.toString());
                        model.clocks.get(req.getWatchesID()).iwtc.pause();
                            break;
                    case 7:
                        System.out.println("req: " + req.toString());
                        model.clocks.get(req.getWatchesID()).iwtc.reset();
                            break;
                    case 8:
                        System.out.println("req: " + req.toString());
                        int count_HMS = model.clocks.get(req.getWatchesID()).alarms.size();
                        for (int i = 0; i < count_HMS; i++) {
                            if ((req.getH() == ((AlarmHMS) model.clocks.get(req.getWatchesID()).alarms.get(i)).h) &&
                                (req.getM() == ((AlarmHMS) model.clocks.get(req.getWatchesID()).alarms.get(i)).m) &&
                                (req.getS() == ((AlarmHMS) model.clocks.get(req.getWatchesID()).alarms.get(i)).s)) {
                                model.deleteAlarm(model.clocks.get(req.getWatchesID()).alarms.get(i), req.getWatchesID());
                            }
                        }
                            break;
                    case 9:
                        System.out.println("req: " + req.toString());
                        int count_HM = model.clocks.get(req.getWatchesID()).alarms.size();
                        for (int i = 0; i < count_HM; i++) {
                            if ((req.getH() == ((AlarmHM) model.clocks.get(req.getWatchesID()).alarms.get(i)).h) &&
                                (req.getM() == ((AlarmHM) model.clocks.get(req.getWatchesID()).alarms.get(i)).m)) {
                                model.deleteAlarm(model.clocks.get(req.getWatchesID()).alarms.get(i), req.getWatchesID());
                            }
                        }
                            break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(CSI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(CSI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CSI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendResponse(ArrayList<Request> requests) {
        RequestsPool reqPool = new RequestsPool();
        for (Request req : requests) {
            reqPool.addRequest(req);
        }
        String msg = gson.toJson(reqPool);
        try {
            dos.writeUTF(msg);
        } catch (IOException ex) {
            Logger.getLogger(CSI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
