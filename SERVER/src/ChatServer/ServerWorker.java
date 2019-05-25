/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ChatServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.apache.commons.lang3.StringUtils;
/**
 *
 * @author ezi
 */
public class ServerWorker extends Thread {
    private Socket clientSocket;
    private  String login;
    private final Server server;
    public String cam = "";
    private boolean connect =false ;
     
    private ObjectOutputStream outputStream ;
    private   ObjectInputStream inputStream ;
    private HashSet<String> topicSet = new HashSet<String>();
    
      public void top() throws IOException
       { this.outputStream.close();
        this.inputStream.close();
      this.clientSocket.close();
    }  
        public ServerWorker(Server server,Socket clientSocket){
            this.server = server;
            this.clientSocket = clientSocket ; 
        }

        public String getLogin(){
          return login ;
        }
    
       
    
        @Override
        public void run(){
          try {
              try { 
                  this.handleClientSocket();
              } catch (ClassNotFoundException ex) {
                  Logger.getLogger(ServerWorker.class.getName()).log(Level.SEVERE, null, ex);
              }
              } catch (IOException ex) {
                  Logger.getLogger(ServerWorker.class.getName()).log(Level.SEVERE, null, ex);
              } catch (InterruptedException ex) {
                  Logger.getLogger(ServerWorker.class.getName()).log(Level.SEVERE, null, ex);
              }
    
        }
        

        
        private void handleLogin(String[] tokens) throws IOException{
        if(tokens.length == 3){
         String login = tokens[1];         
         String password = tokens[2];
         
            if(Sing_in(login ,password))
            {  String msg = "ok login\n";
               EcrireMessage(msg);             
              this.login = login;              
              System.out.println("login succesfully: "+ login);               
              List<ServerWorker> workerList = this.server.getWorkerList();                           
              for(ServerWorker worker :workerList )
                {  if(!login.equalsIgnoreCase(worker.getLogin()))
                     {  if(worker.getLogin()!=null){                         
                         String Msg2 = "online "+ worker.getLogin()+"\n";
                         send(Msg2);
                     }
                     }                
                 }
              
             String onlineMsg ="online "+login+"\n"; 
              for(ServerWorker worker :workerList )
              {  if(!login.equalsIgnoreCase(worker.getLogin())){
                   worker.send(onlineMsg);
                }
              }
            }else{
             String msg = "error login\n" ;
             EcrireMessage(msg);
            }
        
        
        }
        
        
        }
        public void handleClientSocket() throws IOException, InterruptedException, ClassNotFoundException{
              inputStream =new  ObjectInputStream(clientSocket.getInputStream());
                   
              this.outputStream =  new ObjectOutputStream(clientSocket.getOutputStream());     
   
             String line="" ; 
               Object o  ;
             while (!line.equals("quit") ) {
                  o = inputStream.readObject();
                  if(o instanceof String){
                    line =(String) o ; 
                      System.err.println(line);
                    String [] tokens =StringUtils.split(line);
                 
                  System.out.println(line);
                  
                 if(tokens!=null && tokens.length> 0 )  
                  {  String cmd = tokens[0];
                     if("quit".equalsIgnoreCase(cmd) || "logoff".equalsIgnoreCase(cmd) ){
                       
                           handleLogoff();
                          
                          break;
                       }else if("login".equalsIgnoreCase(cmd)){
                          handleLogin(tokens);
                      
                       }else if("msg".equalsIgnoreCase(cmd)){
                          String [] tokensMsg =StringUtils.split(line,null,3); 
                           handleMessage(tokensMsg);
                       }else if("join".equalsIgnoreCase(cmd)){
                           handleJoin(tokens);
                       }else if("leave".equalsIgnoreCase(cmd)){
                           handleLeave(tokens);
                       }else{ if("insc".equalsIgnoreCase(cmd)){
                            System.err.println("mouh");
                            handleInscription(tokens,line);
                            System.err.println("mouh");
                        }else{                          
                           if("cam".equalsIgnoreCase(cmd)){
                                this.cam = tokens[1];
                                handleCam();
                              
                           }else{                                                      
                           
                           String msg = "unknown   " + cmd + "\n";                        
                           
                           EcrireMessage(msg);
                           }
                         }
                      }
                  }     
                }else{
                      if(!connect){handleCam();}                     
                      if(o instanceof ImageIcon && !cam.equalsIgnoreCase("") && connect ){
                      List<ServerWorker> workerList = this.server.getWorkerList();
                         for(ServerWorker worker :workerList ){
                          if(cam.equalsIgnoreCase(worker.getLogin()))
                          { worker.EcrireMessage(o);
                            break ; 
                          }
                         }               
                   }                
                 }             
              }       
          }
   
 
    private void send(String Msg) throws IOException {
      if(login!=null) {
         
        EcrireMessage(Msg);
      }
    }

    private void handleLogoff() throws IOException {
       outputStream.writeObject("offline\n");
        server.removeWorker(this);   
        
        String onlineMsg ="offline "+login+"\n";               
            List<ServerWorker> workerList = this.server.getWorkerList();
             for(ServerWorker worker :workerList )
              {  if(!login.equalsIgnoreCase(worker.getLogin())){
                     
                  worker.send(onlineMsg);
                }
              }
        
        this.clientSocket.close();
    }
 
    private void handleMessage(String[] tokens) throws IOException {
       String sendTo = tokens[1];
       String body = tokens[2];
         boolean isTopic = sendTo.charAt(0)=='#';
       
       
          List<ServerWorker> workerList = this.server.getWorkerList();
             for(ServerWorker worker :workerList )
              { if(isTopic){
                  if(worker.isMemberOfTopic(sendTo)){    
                         String msg =  sendTo+" : " + login +" : " +body +"\n";
                         
                         worker.send(msg);
                      
                   }
                 }          
                 else{                  
                  if(sendTo.equalsIgnoreCase(worker.getLogin())){
                   
                   String msg = login +" : " +body +"\n";
                   worker.send(msg);
                }
              }
            }
    
    
    }

    public boolean isMemberOfTopic(String topic){
      
      return  this.topicSet.contains(topic);
    }
    
    
    
    
    private void handleJoin(String[] tokens) {
      if(tokens.length>1){
           String topic = tokens[1];
           topicSet.add(topic);
      }
    }

    private void handleLeave(String[] tokens) {
        if(tokens.length>1){
           String topic = tokens[1];
           topicSet.remove(topic);
        }
    }

    private void inscription(String line)
    {
       File file = Connect_BD();
        try {
            FileWriter writer = new FileWriter(file ,true);
            BufferedWriter bw = new BufferedWriter(writer);                        
            bw.write(line);            
            bw.newLine();
            bw.close();
            writer.close();
           
        
        } catch (IOException ex) {
            Logger.getLogger(ServerWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }        
    
    private BufferedReader read() throws UnsupportedEncodingException
    {
      File file = Connect_BD() ;
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ServerWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ; 
    }        
    
    
    private boolean EmailExist(String email) throws UnsupportedEncodingException
    {  BufferedReader reader = read();
      
        try {
             String [] information ;
             String line = reader.readLine();           
              while(line!=null ){
                       if(!line.equalsIgnoreCase("")){              
                information = StringUtils.split(line);
                if(email.equalsIgnoreCase(information[2]))
                { line = null ;
                  return true ;}
                }
                line = reader.readLine();   
    
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(ServerWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           
        
        return false ;
    }        
    private boolean UserNameExist(String user_name) throws UnsupportedEncodingException
    {  BufferedReader reader = read();      
        try {
             String [] information ;
             String line = reader.readLine();           
            while(line!=null){
                      if(!line.equalsIgnoreCase("")){               
                information = StringUtils.split(line);
                if(user_name.equalsIgnoreCase(information[1]))
                { line = null ;
                  return true ;}
                      }
                line = reader.readLine();   
                
                
                
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(ServerWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           
        
        return false ;
        
   
    }        
    private void handleInscription(String[] tokens ,String line) throws UnsupportedEncodingException {
       
        if(tokens.length<4)
       {        
                 EcrireMessage("Error\n");       
        }else{
             if(UserNameExist(tokens[1])){
                     EcrireMessage("Error : User Name Exist deja \n");                
        }else{
             if(EmailExist(tokens[2])){                               
                     EcrireMessage("Error : Email Exist deja \n");
               
             }else{             
                     inscription(line);
                     EcrireMessage("inscrire\n");             
             }                  
           }      
         }
       }
    
    private void EcrireMessage(Object MSG)
    {
        try {
            this.outputStream.writeObject(MSG);
        } catch (IOException ex) {
            Logger.getLogger(ServerWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }        
    
    
    
    
     private File Connect_BD()
     {    
       File file =new File("C:BD.txt") ;
        if(!file.exists()){
        try {
         file.createNewFile();
         } catch (IOException ex) {
         Logger.getLogger(ServerWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
     } 
          return file;
   } 

    private boolean Sing_in(String login, String password) throws UnsupportedEncodingException {
      BufferedReader reader = read();      
        try {
             String [] information ;
             String line = reader.readLine();           
            while(line!=null ){
                      if(!line.equalsIgnoreCase("")){         
                information = StringUtils.split(line);
                if(login.equalsIgnoreCase(information[1])&& password.equalsIgnoreCase(information[3]))
                { line = null ;
                  return true  ; 
                }
                }
                line = reader.readLine();       
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(ServerWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           
        
        return false ;
    }

    
    private ServerWorker  ConnectCam()
    {   ServerWorker S = null ;
        
        List<ServerWorker> workerList = this.server.getWorkerList();
 
         for(ServerWorker worker :workerList ){
          
             if(this.cam.equalsIgnoreCase(worker.getLogin()))
             {     S = worker ;
                  
                   break ;}
             }
        return S ;
    
    }  
    
    private void handleCam() throws IOException, ClassNotFoundException {
        ServerWorker S =  ConnectCam();
        if(S.cam.equalsIgnoreCase(this.login))
        {    
         connect = true ;
         }
       }     
    }
    
    
    
  
