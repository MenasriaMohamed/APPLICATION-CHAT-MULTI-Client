/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server_client;
import com.github.sarxos.webcam.Webcam;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author ezi
 */
public class chat_client extends javax.swing.JFrame {
      static Socket s ; 
      Thread t; 
      static ObjectInputStream din ;
      static ObjectOutputStream dout ;
      static String us, pas ;
      int i =0 ;
      static String online ;
      boolean stop = false ;
      boolean OpenCam = false ;
      EmailValidator os = new EmailValidator();
      public HashMap<String, String> Messages = new   HashMap<String, String>();
    /**
     * Creates new form chat_client
     */
     public chat_client() {
        initComponents();
        this.setLocationRelativeTo(null);

    }
        
     public static void displayTray(String login , String message) throws AWTException, MalformedURLException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("not.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage(login,message, MessageType.INFO);
    }
   
      public chat_client(Socket s ,ObjectInputStream din ,ObjectOutputStream dout, String us , String pas) throws IOException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.us = us ;
        this.pas = pas ;
        this.din = din ;
        this.dout =dout ;
        begin();
         

         if(i==0){  connection(); }
          
          i++;
         setTitle(us);
    
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jMenuItem1 = new javax.swing.JMenuItem();
        msg_text = new javax.swing.JTextField();
        msg_send = new javax.swing.JButton();
        Panel = new javax.swing.JPanel();
        lab = new javax.swing.JLabel();
        Amie = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        cam = new javax.swing.JButton();
        ami = new javax.swing.JLabel();
        me = new javax.swing.JLabel();
        open = new javax.swing.JButton();
        ret = new javax.swing.JButton();
        msg_area = new java.awt.TextArea();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CLIENT");
        setBackground(new java.awt.Color(44, 62, 80));

        msg_text.setBackground(new java.awt.Color(44, 62, 80));
        msg_text.setForeground(new java.awt.Color(244, 244, 244));
        msg_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msg_textActionPerformed(evt);
            }
        });

        msg_send.setBackground(new java.awt.Color(44, 62, 80));
        msg_send.setText("Send");
        msg_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msg_sendActionPerformed(evt);
            }
        });

        Panel.setBackground(new java.awt.Color(248, 148, 16));

        lab.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lab.setForeground(new java.awt.Color(240, 240, 240));
        lab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab.setText("Connecté");

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(lab, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Amie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AmieActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel1.setText("Amie connecté :");

        cam.setBackground(new java.awt.Color(44, 62, 80));
        cam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/server_client/image10.png"))); // NOI18N
        cam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                camActionPerformed(evt);
            }
        });

        ami.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        ami.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ami.setText("En Attend La connection...!! ");

        me.setBackground(new java.awt.Color(204, 255, 255));
        me.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        me.setText("ME");

        open.setBackground(new java.awt.Color(248, 148, 16));
        open.setText("Open");
        open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openActionPerformed(evt);
            }
        });

        ret.setBackground(new java.awt.Color(248, 148, 16));
        ret.setText("Retourn");
        ret.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retActionPerformed(evt);
            }
        });

        msg_area.setBackground(new java.awt.Color(44, 62, 80));
        msg_area.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        msg_area.setForeground(new java.awt.Color(244, 244, 244));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Amie, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(msg_area, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(msg_text, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(msg_send, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cam, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ami, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(me, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(open, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ret, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Amie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(msg_area, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(msg_text, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(msg_send))
                    .addComponent(cam, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(me, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(open, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ret, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ami, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void msg_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msg_textActionPerformed
         
            if(Amie.getSelectedItem()!="" && Amie.getSelectedItem()!=null ){
            String msgout  ="" ;            
            msgout = msg_text.getText().trim() ;            
            msg_area.setText(msg_area.getText().trim() + "\n ME : \t" +  msgout + "\n");
            Messages.put(Amie.getSelectedItem().toString(),msg_area.getText().toString());
            msgout = "msg " +Amie.getSelectedItem() +" "+msgout;           
            msg_text.setText("");           
            try {
                dout.writeObject(msgout);
            } catch (IOException ex) {
                Logger.getLogger(chat_client.class.getName()).log(Level.SEVERE, null, ex);}            
         }

    }//GEN-LAST:event_msg_textActionPerformed

    private void AmieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AmieActionPerformed
        // TODO add your handling code here:
       String messages = Messages.get(Amie.getSelectedItem().toString());            
       msg_area.setText(messages);        

    }//GEN-LAST:event_AmieActionPerformed
    private void CreateFace()
    {   Amie.setVisible(false);
        msg_text.setVisible(false);
        jLabel1.setVisible(false);
        msg_area.setVisible(false);
        msg_send.setVisible(false);
        cam.setVisible(false);
        me.setVisible(true);
        ami.setVisible(true);
        open.setVisible(true);
        ret.setVisible(true);  
        this.setLocationRelativeTo(null);
   
    }    
    
      private void Back()
    {   Amie.setVisible(true);
        msg_text.setVisible(true);
        jLabel1.setVisible(true);
        msg_area.setVisible(true);
        msg_send.setVisible(true);
        cam.setVisible(true);
        me.setVisible(false);
        ami.setVisible(false);
        open.setVisible(false);
        ret.setVisible(false);  
        this.setLocationRelativeTo(null);
   
    }   
    
    private void camActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_camActionPerformed
         if(!Amie.getSelectedItem().toString().equals("") &&Amie.getSelectedItem()!=null){   
        CreateFace() ;

          // TODO add your handling code here:
          if(Amie.getSelectedItem()!="" && Amie.getSelectedItem()!=null ){
             String msgout  ="" ;            
           msgout = "cam " + Amie.getSelectedItem() ;   
              
              try {
                dout.writeObject(msgout);
              } catch (IOException ex) {
                Logger.getLogger(chat_client.class.getName()).log(Level.SEVERE, null, ex);}    
             
       }  
     }         
    }//GEN-LAST:event_camActionPerformed

    private void openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openActionPerformed
       if(OpenCam){
        OpenCam = false;
        open.setText("Open");
       }else{
        
            OpenCam = true;
            open.setText("Close");
            open();
       }
       

    }//GEN-LAST:event_openActionPerformed

    private void msg_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msg_sendActionPerformed

        if(Amie.getSelectedItem()!="" && Amie.getSelectedItem()!=null ){
            String msgout  ="" ;
            msgout = msg_text.getText().trim() ;
            msg_area.setText(msg_area.getText().trim() + "\n ME : \t" +  msgout + "\n");
            Messages.put(Amie.getSelectedItem().toString(),msg_area.getText().toString());

            msgout = "msg " +Amie.getSelectedItem()+" "+msgout;

            msg_text.setText("");

            try {
                dout.writeObject(msgout);
            } catch (IOException ex) {
                Logger.getLogger(chat_client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_msg_sendActionPerformed

    private void retActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retActionPerformed
        Back() ;
        this.OpenCam =false ;
        this.open.setText("Open");
        
    }//GEN-LAST:event_retActionPerformed

            private  void connection()
            {  Amie.addItem("");
           
            t = new Thread(){
            @Override
             public void run() {        
            try {                                      
                  String msgin ="";
                  Object o = null ;
                  while(!stop )
                  {
                      try {
                           o = din.readObject();
                         } catch (ClassNotFoundException ex) {
                        Logger.getLogger(chat_client.class.getName()).log(Level.SEVERE, null, ex);
                      }
                            if( o instanceof String){
                                
                            msgin =  (String) o ;
                          
                           
                           
                           
                           
                   
                    String [] tokens =StringUtils.split(msgin);
                    
                    
                  if (!"ok login\n".equalsIgnoreCase(msgin)) {                      
                      String cmd = tokens[0];
                     if("online".equalsIgnoreCase(cmd) && !Messages.containsKey(cmd)){                       
                       cmd =  tokens[1];
                        Amie.addItem(cmd);
                       Messages.put(cmd,"");                                              
                    }else{                                                                    
                      String messages = Messages.get(tokens[0]);
                      messages   = messages + "\n  " + msgin ;                     
                      Messages.put(tokens[0],messages);   
                     
                      if(tokens[0].equalsIgnoreCase(Amie.getSelectedItem().toString()))
                      {  
                        
                         msg_area.setText(msg_area.getText().trim() + "\n  " + msgin);}
                        else{
                             
                             lab.setText(Amie.getSelectedItem()+" Connecté");
                             try {
                                if(!tokens[0].equalsIgnoreCase("connect"))
                                {displayTray(tokens[0] ,msgin);}
                                 } catch (AWTException ex) {
                                 Logger.getLogger(chat_client.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (MalformedURLException ex) {
                               Logger.getLogger(chat_client.class.getName()).log(Level.SEVERE, null, ex);
                        }
                      }                      
                     }                  
                    }
                   }else{
                              if(o instanceof ImageIcon)
                              {   
                                 ami.setText("");
                                 ami.setIcon((ImageIcon) o );
                              }
                                    
                            
                            
                            }
                  }
             
              
        } catch (IOException ex) {
            Logger.getLogger(chat_client.class.getName()).log(Level.SEVERE, null, ex);
        }
          
              
           }                           

 
           };
        t.start();  
    
    
    }      
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(chat_client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(chat_client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(chat_client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(chat_client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>                   
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
             new chat_client().setVisible(true);            
            }
        });      
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Amie;
    private javax.swing.JPanel Panel;
    private javax.swing.JLabel ami;
    private javax.swing.JButton cam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lab;
    private javax.swing.JLabel me;
    private java.awt.TextArea msg_area;
    private javax.swing.JButton msg_send;
    private javax.swing.JTextField msg_text;
    private javax.swing.JButton open;
    private javax.swing.JButton ret;
    // End of variables declaration//GEN-END:variables

    private void begin() {
       me.setVisible(false);
       ami.setVisible(false);    
       open.setVisible(false);
       ret.setVisible(false);
       this.setSize(533, 360);
       this.setLocationRelativeTo(null);
       
    }

    private void open() {
    t = new Thread(){
            @Override
             public void run() {   
                     
                     me.setText("");
                     Webcam webcam = Webcam.getDefault();
                     webcam.open();
                     BufferedImage bn = webcam.getImage();
                     ImageIcon ic;
                     while(OpenCam){
                         bn = webcam.getImage();
                         ic = new ImageIcon(bn);
                         
                         me.setIcon(ic);
                         try {
                             dout.writeObject(ic);
                              dout.flush(); 
                         } catch (IOException ex) {
                             Logger.getLogger(chat_client.class.getName()).log(Level.SEVERE, null, ex);
                         }
                     }
                     webcam.close() ;
                     
                 }
                 
         };
    t.start();
    
    }
            
}
