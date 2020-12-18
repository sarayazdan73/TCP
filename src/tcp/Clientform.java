package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Clientform extends javax.swing.JFrame {

    Socket sckt;
    DataInputStream dtinpt;
    DataOutputStream dtotpt;
    String id;
    String[] contact = new String[20];
    String[] message = new String[1000];

    public Clientform() {
        initComponents();
    }

    public Clientform(String id, Socket sckt) {
        this.id = id;
        this.sckt = sckt;
        try {
            initComponents();
            idLabel.setText(id);
            this.setTitle(id);
            dtinpt = new DataInputStream(sckt.getInputStream());
            dtotpt = new DataOutputStream(sckt.getOutputStream());
            new Read().start();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    class Read extends Thread {

        public void run() {
            try {
                while (true) {
                    String temp = dtinpt.readUTF();
                    if (temp.contains("*")) {
                        temp = temp.substring(1);
                        contact = temp.trim().split(",");
                        for (int i = 0; i < contact.length; i++) {
                            if (!contact[i].equals(id)) {
                                contacts.addItem(contact[i]);
                            }
                        }
                    }
                    else if(temp.contains("###CHAT###")) {
                        String msg = temp;
                        msg=msg.substring(10);
                        chatjTextArea.append(msg);
                    }                   
                     else if(temp.contains("#GROUP@####")) {
                       message= temp.split(":");    
                        message[1]=message[1].substring(11);
                        grpjTextArea.append(message[0]+":"+message[1]+"\n");
                    }  
                    
                     else if(temp.contains("#CHANNEL@####")) {
                         String msg = temp;
                        msg=msg.substring(13);
                        String myId= msg.split(":")[0];
                        Admin.setText(myId);
                        jTextArea5.append(msg+"\n");
                        if(!id.equals(myId)){
                          msgCh.setVisible(false);
                          sendTochnl.setVisible(false);
                        }
                    }
                    
                }
            
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatjTextArea = new javax.swing.JTextArea();
        idLabel = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        SEND = new javax.swing.JButton();
        label1 = new java.awt.Label();
        contacts = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        grpjTextArea = new javax.swing.JTextArea();
        grpjTextField = new javax.swing.JTextField();
        SENDTOGR = new javax.swing.JButton();
        label4 = new java.awt.Label();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        sendTochnl = new javax.swing.JButton();
        msgCh = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Admin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 255));
        jTabbedPane1.setAlignmentX(1.0F);
        jTabbedPane1.setAlignmentY(1.0F);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setText("Hello");

        chatjTextArea.setColumns(20);
        chatjTextArea.setRows(5);
        jScrollPane1.setViewportView(chatjTextArea);

        idLabel.setText("...................");

        jTextField1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        SEND.setBackground(new java.awt.Color(0, 204, 153));
        SEND.setText("SEND");
        SEND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SENDActionPerformed(evt);
            }
        });

        label1.setText("messege");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(SEND, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(contacts, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idLabel)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contacts, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SEND, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("CHAT", jPanel1);

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));

        grpjTextArea.setColumns(20);
        grpjTextArea.setRows(5);
        jScrollPane4.setViewportView(grpjTextArea);

        grpjTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        SENDTOGR.setBackground(new java.awt.Color(255, 102, 102));
        SENDTOGR.setText("SEND");
        SENDTOGR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SENDTOGRActionPerformed(evt);
            }
        });

        label4.setText("messege");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(grpjTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SENDTOGR, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grpjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SENDTOGR, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("GROUP", jPanel4);

        jPanel5.setBackground(new java.awt.Color(153, 255, 255));

        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jScrollPane5.setViewportView(jTextArea5);

        sendTochnl.setBackground(new java.awt.Color(0, 204, 204));
        sendTochnl.setText("SEND");
        sendTochnl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendTochnlActionPerformed(evt);
            }
        });

        jLabel2.setText("ADMIN : ");

        Admin.setText(".............");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(385, 385, 385)
                        .addComponent(sendTochnl, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(msgCh, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Admin)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Admin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendTochnl, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(msgCh, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("CHANNEL", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SENDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SENDActionPerformed
        try {
            String msgout = contacts.getSelectedItem().toString() + "," + jTextField1.getText().trim();
            dtotpt.writeUTF("###CHAT###"+msgout);
        } catch (Exception e) {
        }
        jTextField1.setText(null);
    }//GEN-LAST:event_SENDActionPerformed

    private void SENDTOGRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SENDTOGRActionPerformed
    
          try {
            String msgout =grpjTextField.getText().trim();
            dtotpt.writeUTF("#GROUP@####"+ msgout);
        } catch (Exception e) {
        }
        grpjTextField.setText(null);
      
    }//GEN-LAST:event_SENDTOGRActionPerformed

    private void sendTochnlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendTochnlActionPerformed
          try {
            String msgout =msgCh.getText().trim();
            dtotpt.writeUTF(id+","+"#CHANNEL@####"+","+ msgout);
        } catch (Exception e) {
        }
        msgCh.setText(null);       
    }//GEN-LAST:event_sendTochnlActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Admin;
    private javax.swing.JButton SEND;
    private javax.swing.JButton SENDTOGR;
    private static javax.swing.JTextArea chatjTextArea;
    private javax.swing.JComboBox<String> contacts;
    private static javax.swing.JTextArea grpjTextArea;
    private javax.swing.JTextField grpjTextField;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private static javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextField jTextField1;
    private java.awt.Label label1;
    private java.awt.Label label4;
    private javax.swing.JTextField msgCh;
    private javax.swing.JButton sendTochnl;
    // End of variables declaration//GEN-END:variables
}
