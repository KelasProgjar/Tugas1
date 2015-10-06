/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientsfe;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        System.out.println("Connecting to host " + host + " on port 6666");
        
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        
        try {
            socket = new Socket(host, 6666);
            System.out.println("Connected to host " + host + " on port 6666");
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(ClientSFE.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        BufferedReader userIn;
        userIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        
        System.out.print("input: ");
        while(!(userInput = userIn.readLine()).equals("exit")){
            out.println(userInput);
            out.flush();
            System.out.println("server: " + in.readLine());
            for(int i = 1; in.ready(); i++) {
                System.out.println(in.readLine());
            }
            System.out.print("input: ");
        while(!(userInput = userIn.readLine()).equals("exit")){
        out.println(userInput);
        out.flush();
        System.out.println(in.readLine());
        }
        
        in.close();
        out.close();
        userIn.close();
        socket.close();
        
    }
    
}
