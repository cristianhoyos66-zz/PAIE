package co.com.chat.thread;

import co.com.chat.gui.GUIWindow;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadReceive implements Runnable {
    
    private Socket con;
    private GUIWindow window;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private String msg;

    public ThreadReceive(Socket con, GUIWindow window) {
        this.con = con;
        this.window = window;
    }
    
    @Override
    public void run() {
        try {
            dataIn = new DataInputStream(con.getInputStream());
            while (true) {
                msg = dataIn.readUTF();
                window.setMsg(msg);
            }
        } catch (IOException ex) {
            Logger.getLogger(ThreadReceive.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
