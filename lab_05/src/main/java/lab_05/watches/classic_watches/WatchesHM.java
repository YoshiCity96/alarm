/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.watches.classic_watches;

import lab_05.events.listeners.IWatchesEventListener;
import lab_05.events.publishers.WatchesEventPublisher;
import lab_05.events.publishers.IWatchesEventPublisher;

/**
 *
 * @author AlibekovMurad5202
 */
public class WatchesHM implements IWatches {
    protected String brand;
    protected double price;
    protected int hours = 0;
    protected int minutes = 0;
    
    Thread thread;
    boolean flag = false;
    
    public IWatchesEventPublisher events;

    public WatchesHM(String brand, double price) {
        this.brand = brand;
        this.price = price;
        
        events = new WatchesEventPublisher();
    } 
    
    public String getBrand() {
        return brand;
    }
    
    public double getPrice() {
        return price;
    }
    
    @Override
    public void setHours(int hours) throws Exception {
        if ((hours < 0) || (hours > 11)) {
            throw new Exception("Invalid value of hours");
        }
        this.hours = hours;
    }

    @Override
    public void setMinutes(int minutes) throws Exception {
        if ((minutes < 0) || (minutes > 59)) {
            throw new Exception("Invalid value of minutes");
        }
        this.minutes = minutes;
    }

    @Override
    public void setSeconds(int seconds) throws Exception {
        throw new Exception("There are not second hands");
    }
    
    @Override
    public String toString() {
       return hours + ":" + minutes;
    }

    @Override
    public int getHours() {
        return hours;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    @Override
    public int getSeconds() throws Exception {
        throw new Exception("There are not second hands");
    }

    @Override
    public void increaseTime() throws Exception {
        this.addMinutes(1);
        events.update(this);
    }

    protected void addMinutes(int minutes) {
        this.hours = (this.hours + (this.minutes + minutes) / 60) % 12;
        this.minutes = (this.minutes + minutes) % 60;
    }

    @Override
    public int getDelay() {
        return 1000 * 60;
    }

    @Override
    public void addEvent(IWatchesEventListener event) {
        events.addListener(event);
    }

    @Override
    public void removeEvent(IWatchesEventListener event) {
        events.removeListener(event);
    }
}
