package tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class server {

    static ServerSocket server;
    static Socket socket;
    static ArrayList<EchoThread> client = new ArrayList<>();
    

    public static void main(String args[]) {
    
        try {
            server = new ServerSocket(1201);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        while(true){
        try {
            socket=server.accept();
           
        } catch (Exception e) {}
    
EchoThread et=new EchoThread(socket);                       
    client.add(et);
    et.start();
            }
        }
}