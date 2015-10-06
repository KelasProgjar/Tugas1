/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serversfe;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
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
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(6666);
        Socket client = ss.accept();
        
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        
        String user;
        String path = "C:/xampp/";
        File file = new File(path);
        File oldFile = null;
        File newFile = null;
                
        while((user = in.readLine()) != null){
            
            if (user.equals("list")){
                File[] files = file.listFiles();
                out.println("List of Directories:");
                for (int i = 0; i < files.length; i++) {
                    out.println(files[i]);
                    }
                out.println("Current directory: " + file.getAbsolutePath());
                out.flush();
            }
            
            else if (user.equals("parent")){
                try{
                    oldFile = file;
                    file = file.getParentFile();
                    File[] files = file.listFiles();
                    out.println("List of Directories:");
                    for (int i = 0; i < files.length; i++) {
                        out.println(files[i]);
                    }
                    out.flush();
                }
                catch(Exception ex){
                    file = oldFile;
                    out.println("Already on root");
                    out.flush();
                }
                out.println("Current directory: " + file.getAbsolutePath());
                out.flush();
            }
            
            else if (user.equals("select")){
                oldFile = file;
                out.println("Select folder:");
                out.flush();
                user=in.readLine();
                String newPath = file.getAbsolutePath() + user + "/";
                file = new File (newPath);
                if(file.exists()){
                    File[] files = file.listFiles();
                    out.println("List of Subdirectories:");
                    for (int i = 0; i < files.length; i++) {
                        out.println(files[i]);
                    }
                    out.flush();
                }
                else{
                    out.println("Folder doesnt exist");
                    out.flush();
                    file = oldFile;
                }
                out.println("Current directory: " + file.getAbsolutePath());
                out.flush();
            }
            
            else if (user.equals("new")){
                out.println("Enter name for new folder:");
                out.flush();
                user=in.readLine();
                String newPath = file.getAbsolutePath() + "/" + user + "/";
                file = new File (newPath);
                file.mkdir();
                out.println("Managed to create a new folder");
                out.println("Current directory: " + file.getAbsolutePath());
                out.flush();
            }
            
            else if (user.equals("delete")){
                out.println("Enter name of the folder you want to delete:");
                out.flush();
                user=in.readLine();
                String newPath = file.getAbsolutePath() + user + "/";
                newFile = new File (newPath);
                if(newFile.exists()){
                    newFile.delete();
                    out.println("Folder Deleted");
                    out.flush();
                }
                else{
                    out.println("Folder doesnt exist");
                    out.flush();                    
                }
                out.println("Current directory: " + file.getAbsolutePath());
                out.flush();
            }
            
            else{
                out.println("Command invalid");
                out.println("Current directory: " + file.getAbsolutePath());
                out.flush();
            }
        }
        
        in.close();
        out.close();
        client.close();
        ss.close();
    }
    
}
