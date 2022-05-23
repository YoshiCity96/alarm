/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab_05.client.gui.ClientMainFrame;
import lab_05.server.Request;

/**
 *
 * @author AlibekovMurad5202
 */
public class GUI_Controller {
    public static OutputStream os;
    public static DataOutputStream dos;
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    public static void init(Socket socket) {
        try {
            os = socket.getOutputStream();
            dos = new DataOutputStream(os);
        } catch (IOException ex) {
            Logger.getLogger(ClientMainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void event(int eventType, int watchesID, int H, int M, int S) {
        try {
            Request req = new Request();
            req.setRequestType(eventType);
            String msg;
            
            req.setWatchesID(watchesID);
            req.setH(H);
            req.setM(M);
            req.setS(S);
            msg = gson.toJson(req);
            dos.writeUTF(msg);
        } catch (IOException ex) {
            Logger.getLogger(ClientMainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    };
}
