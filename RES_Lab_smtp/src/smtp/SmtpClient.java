package smtp;

import model.mail.Message;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class SmtpClient {
    private String smtpServerAddress;
    private int port = 25;

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public SmtpClient(String smtpServerAddress, int port){
        this.smtpServerAddress = smtpServerAddress;
        this.port = port;
    }

    public void sendMessage(Message message) throws IOException {
        socket =  new Socket(smtpServerAddress, port);
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        String line = reader.readLine();
        writer.println("EHLO localhost");
        line = reader.readLine();
        while (line.startsWith("250-")){
            line = reader.readLine();
        }
        writer.println("");
    }
}








































