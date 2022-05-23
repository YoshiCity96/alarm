/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import lab_05.events.listeners.CSI;
import lab_05.events.publishers.ServerModel;

/**
 *
 * @author AlibekovMurad5202
 */
public class Server {
    int port = 3124;
    InetAddress host;
    
    ServerModel model = new ServerModel();
    
    public Server() {
        
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            System.out.println(ex);
        }
        
        try {
            ServerSocket serverSocket = new ServerSocket(port, 0, host);
            System.out.println("Server was born!");
            
            int count = 0;
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client #" + count + " connected!");
                
                count++;
                
                CSI csi = new CSI(clientSocket, this.model);
            }
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
        
    public static void main(String[] args) {
        new Server();
    }
}
