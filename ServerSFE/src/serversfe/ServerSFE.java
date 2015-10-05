/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serversfe;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sasmito
 */
public class ServerSFE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            try (ServerSocket ss = new ServerSocket(6666)) {
                while(true){
                    try (Socket socket = ss.accept()) {
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                        out.println(new Date().toString());
                        out.flush();
                    }
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ServerSFE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
