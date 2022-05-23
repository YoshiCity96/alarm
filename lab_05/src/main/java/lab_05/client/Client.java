/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_05.client;

import lab_05.controllers.GUI_Controller;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab_05.client.gui.ClientMainFrame;

/**
 *
 * @author AlibekovMurad5202
 */
public class Client {
    
    static int port = 3124;
    static InetAddress host;
    
    static Socket socket;
    
    public static Socket connect() {                               
        try {
            Client.host = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientMainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Client.socket = new Socket(host, port);
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        GUI_Controller.init(Client.socket);
        return Client.socket;
    }
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new ClientMainFrame().setVisible(true);
        });
    }
}
