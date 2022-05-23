/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.server;

import java.util.ArrayList;

/**
 *
 * @author AlibekovMurad5202
 */
public class RequestsPool {
    ArrayList<Request> requests;
    
    public RequestsPool() {
        requests = new ArrayList<>();
    }
    
    public ArrayList<Request> getRequests() {
        return requests;
    }
    
    public void setRequests(ArrayList<Request> requests) {
        this.requests = requests;
    }
    
    public Request getLastRequest() {
        return requests.get(requests.size() - 1);
    }
    
    public void addRequest(Request request) {
        this.requests.add(request);
    }
}
