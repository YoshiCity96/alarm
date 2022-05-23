/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.events.publishers;

import java.util.ArrayList;
import lab_05.events.listeners.IServerEventListener;
import lab_05.server.RequestsPool;

/**
 *
 * @author AlibekovMurad5202
 */
public interface IServerEventPublisher {
    public RequestsPool allRequests = new RequestsPool();
    public ArrayList<IServerEventListener> allListeners = new ArrayList<>();

    void update();
    void addListener(IServerEventListener listener);
    void removeListener(IServerEventListener listener);
}
