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
        writer.printf("EHLO localhost\r\n");
        while (line.startsWith("250-")){
            line = reader.readLine();
        }
        writer.printf("MAIL FROM:<%s>\r\n", message.getFrom());
        while (!(line.startsWith("250 "))){
            line = reader.readLine();
        }

        String victimlist = message.getTo().toString();
        victimlist = victimlist.replace("]", "");
        victimlist = victimlist.replace("[", "");
        String witnesslist = message.getCc().toString();
        witnesslist = witnesslist.replace("]", "");
        witnesslist = witnesslist.replace("[", "");
        writer.printf("RCPT TO: %s, %s\r\n", victimlist, witnesslist);
            while (!(line.startsWith("250 "))) {
                line = reader.readLine();
            }

        writer.printf("DATA\r\n");
        while (!line.startsWith("354 ")){
            line = reader.readLine();
        }

        writer.printf("From : %s\r\n",message.getFrom());
        writer.printf("To : %s\r\n", victimlist);
        writer.printf("Cc : %s\r\n", witnesslist);
        writer.printf("Subject : %s\r\n\r\n", message.getSubject());
        writer.printf("%s", message.getBody());
        writer.printf("\r\n.\r\n");
        line = reader.readLine();
    }
}








































