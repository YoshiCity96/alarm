/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.controllers.threads;

import lab_05.watches.classic_watches.IWatches;

/**
 *
 * @author AlibekovMurad5202
 */
public class WatchesThreadController implements IWatchesThreadController {
    private IWatches watches;
    Thread thread;
    boolean flag;
    boolean pause;
    
    public WatchesThreadController(IWatches watches) {
        this.watches = watches;
    }
    
    @Override
    public void start() throws InterruptedException {
        if (thread == null) {
            thread = new Thread() {
                @Override
                public void run() {
                    flag = true;
                    System.out.println("Watches launched!");
                    
                    while(flag) {
                        try {
                            
                            if (pause) {
                                synchronized(watches) {
                                    watches.wait();
                                }
                                pause = false;
                            }
                            
                            thread.sleep(watches.getDelay());
                            watches.increaseTime();
                        } catch (InterruptedException e) {
                            flag = false;
                        } catch (Exception ex) {
                            System.err.println("Ouch!!!");
                        }
                    }
                }
            };
        }
        
        thread.start();
    }

    @Override
    public void stop() {
        if (thread != null) {
            thread.interrupt();
            thread = null;
            System.out.println("Watches stopped!");
        }
    }

    @Override
    public void pause() {
        pause = true;
    }

    @Override
    public void reset() {
        synchronized(watches) {
            watches.notifyAll();
        }
    }
}
