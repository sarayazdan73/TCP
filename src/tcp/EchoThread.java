package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import static tcp.server.client;

public class EchoThread extends Thread {

    Socket socket;
    DataInputStream din;
    DataOutputStream dout, dout1;
    String msg = "";
    String name, sender, reciever;
    ArrayList<String> clientName = new ArrayList<>();
    String[] n = new String[2];
    
    public EchoThread(Socket socket) {
        this.socket = socket;
    }

    public String getname() throws IOException {
        return name;
    }

    public void run() {

        try {
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());
            name = din.readUTF();
            System.out.println("name:" + name);
            clientName.clear();
            for (int i = 0; i < client.size(); i++) {
                clientName.add(client.get(i).getname());
                client.get(i).dout.writeUTF("*" + name);
                this.dout.writeUTF("*" + clientName.get(i));
            }
         
        } catch (IOException e) {
            return;
        }

        while (true) {
            try {
                sender = this.getname();
                msg = din.readUTF();
                if (msg.contains("###CHAT###")) {
                   System.out.println(msg);
                   msg=msg.substring(10);
                    reciever = msg.split(",")[0];
                    System.out.println("reciever:" + reciever);      
                    for (int i = 0; i < client.size(); i++) {
                        System.out.println("client:" + client.get(i).getname());
                        if (reciever.equals(client.get(i).getname())) {
                            dout1 = client.get(i).dout;
                            dout1.writeUTF("###CHAT###"+"<" + sender + " to " + reciever + " > " + msg.split(",")[1] + "\n");
                        }
                    }
                }
                else if(msg.contains("#GROUP@####")){
                   
                  for (int i = 0; i < client.size(); i++) {
                client.get(i).dout.writeUTF(sender+":"+msg);     
           }}
                
                 else if(msg.contains("#CHANNEL@####")){
                   n=  msg.split(",");                
                            for (int i = 0; i < client.size(); i++) {
                          client.get(i).dout.writeUTF(n[1]+n[0]+":"+n[2]);                       
                      }
                 }
                
            } catch (IOException e) {
                return;
            }

        }

    }

}
