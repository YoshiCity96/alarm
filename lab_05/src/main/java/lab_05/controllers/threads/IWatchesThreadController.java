/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.controllers.threads;

/**
 *
 * @author AlibekovMurad5202
 */
public interface IWatchesThreadController {
    void start() throws InterruptedException;
    void stop();
    void pause();
    void reset();
}
