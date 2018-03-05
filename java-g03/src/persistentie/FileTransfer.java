package persistentie;

import java.io.IOException;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

public class FileTransfer {

    private Socket socket;
    private Scanner socketinput;
    private Formatter socketOutput;

    public FileTransfer() {
        try{
            String host ="";
            int port = 4;
            socket = new Socket(host, port);
            socketinput = new Scanner(socket.getInputStream());
            socketOutput = new Formatter(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
