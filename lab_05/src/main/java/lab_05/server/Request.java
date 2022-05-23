/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.server;

/**
 *
 * @author AlibekovMurad5202
 */
public class Request {
    private int requestType;
    private int watchesID = 0;
    private int H = 0;
    private int M = 0;
    private int S = 0;

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }
    
    public String getRequestName() {
        if (this.getRequestType() == 0)
            return "Add ClockHM";
        if (this.getRequestType() == 1)
            return "Add ClockHMS";
        if (this.getRequestType() == 2)
            return "Set alarm_HM!!!";
        if (this.getRequestType() == 3)
            return "Set alarm_HMS!!!";
        if (this.getRequestType() == 4)
            return "Start";
        if (this.getRequestType() == 5)
            return "Stop";
        if (this.getRequestType() == 6)
            return "Pause";
        if (this.getRequestType() == 7)
            return "Continue";
        if (this.getRequestType() == 8)
            return "Delete alarm_HMS";
        if (this.getRequestType() == 9)
            return "Delete alarm_HM";
        if (this.getRequestType() == 10)
            return "Alarm!!!";
        if (this.getRequestType() == 11)
            return "Increase time";
        return "undefined request";
    }

    public int getWatchesID() {
        return watchesID;
    }

    public void setWatchesID(int watchesID) {
        this.watchesID = watchesID;
    }

    public int getH() {
        return H;
    }

    public void setH(int H) {
        this.H = H;
    }

    public int getM() {
        return M;
    }

    public void setM(int M) {
        this.M = M;
    }

    public int getS() {
        return S;
    }

    public void setS(int S) {
        this.S = S;
    }
    
    @Override
    public String toString() {
       return "(" 
               + requestType + " " 
               + watchesID + " " 
               + H + " " 
               + M + " " 
               + S + ")";
    }
}
