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
        System.out.println("MOCKMOCK SERVER INDIQUE : " + line);
        writer.printf("EHLO localhost\r\n");
        while (line.startsWith("250-")){
            line = reader.readLine();
            System.out.println("MOCKMOCK SERVER INDIQUE : " + line);
        }
        writer.printf("MAIL FROM:<%s>\r\n", message.getFrom());
        while (!(line.startsWith("250 "))){
            line = reader.readLine();
            System.out.println("MOCKMOCK SERVER INDIQUE : " + line);
        }
        writer.printf("RCPT TO:<%s>\r\n", message.getTo().get(0));
        while (!(line.startsWith("250 "))){
            line = reader.readLine();
            System.out.println("MOCKMOCK SERVER INDIQUE : " + line);
        }
        writer.printf("DATA\r\n");
        System.out.println("MOCKMOCK SERVER INDIQUE : " + line);
        while (!line.startsWith("354 ")){
            System.out.println("MOCKMOCK SERVER INDIQUE : " + line);
            line = reader.readLine();
            System.out.println("MOCKMOCK SERVER INDIQUE : " + line);
        }

        writer.printf("From : %s\r\n",message.getFrom());
        writer.printf("To : %s\r\n", message.getTo().get(0));
        writer.printf("Subject : fr--%s to--%s\r\n", message.getFrom(), message.getTo().get(0));
        writer.printf("%s", message.getBody());
        writer.printf("\r\n.\r\n");
        line = reader.readLine();
        System.out.println("MOCKMOCK SERVER INDIQUE : " + line);
        System.out.println("mail send");
    }
}








































