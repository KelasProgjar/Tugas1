/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientsfe;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sasmito
 */
public class ClientSFE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            try (Socket socket = new Socket("127.0.0.1", 6666)) {
                System.out.println("Connected to localhost");
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println(input.readLine());
                socket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientSFE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
