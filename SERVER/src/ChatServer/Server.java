/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ChatServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ezi
 */
public class Server extends Thread{
    private final int serverPort;
    public boolean stop =false;
    ServerSocket  serverSocket ;
    private ArrayList<ServerWorker> workerList = new ArrayList<ServerWorker>();
    Server(int serverPort) {
      this.serverPort = serverPort ;
    }
    
      public boolean Stop()
     {  try {
        for(ServerWorker s : workerList)
       { 
           s.top();
           this.stop =true ;
           serverSocket.close();
        
       } 
       return true;
       
       } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
     return false ;  
    
    }
    
    
    
    
    @Override
    public void run()
    {   
         
        try {
             serverSocket = new  ServerSocket(serverPort);           
              while(!stop){
                Socket clientSocket = serverSocket.accept();
                System.out.println("ok");
                ServerWorker serverWorker = new ServerWorker(this,clientSocket);
                workerList.add(serverWorker); 
                serverWorker.start();     
              }
               } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public ArrayList<ServerWorker> getWorkerList()
     {
       return this.workerList;
     }

    public void removeWorker(ServerWorker aThis) {
       this.workerList.remove(aThis);
    }

   
    
    
    
    
    
    
    
    
    
    
    
    
    
}
