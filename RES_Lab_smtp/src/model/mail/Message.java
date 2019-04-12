package model.mail;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private String from;
    private List<String> to = new ArrayList<String>();
    private List<String> cc = new ArrayList<String>();
    private String subject;
    private String body;

    public void setFrom(String from) {
        this.from = from;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCc(List<String> cc) {this.cc = cc;}

    public void setTo(List<String> to) {this.to = to;}

    public String getFrom() {
        return from;
    }

    public List<String> getCc() {return cc;}

    public List<String> getTo() {return to;}

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}
