package model.prank;

import model.mail.Person;
import model.mail.Message;
import java.util.ArrayList;
import java.util.List;

public class Prank {
    private Person victimSender;
    private final List<Person> victimRecipients = new ArrayList<Person>();
    private final List<Person> witnessRecipients = new ArrayList<Person>();
    private String message;

    public void setVictimSender(Person victimSender) {
        this.victimSender = victimSender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Person getVictimSender() {
        return victimSender;
    }

    public List<Person> getVictimRecipients() {
        return new ArrayList(victimRecipients);
    }

    public List<Person> getWitnessRecipients() {
        return new ArrayList(witnessRecipients);
    }

    public void addVictimRecipient(List<Person> victims){
        this.victimRecipients.addAll(victims);
    }

    public void addWitnessRecipient(List<Person> witnesses){
        this.witnessRecipients.addAll(witnesses);
    }

    public Message generateMAilMessage(){
        Message message = new Message();

        if(victimSender.getFirstName() != null) {
            message.setBody(this.message + "\r\n" + victimSender.getFirstName());
        }else{
            message.setBody(this.message);
        }

        message.setFrom(victimSender.getAddresseMail());

        List<String> to = new ArrayList();
        for(Person p : victimRecipients){
            to.add(p.getAddresseMail());
        }
        message.setTo(to);

        List<String> cc = new ArrayList();
        for(Person p : witnessRecipients){
            cc.add(p.getAddresseMail());
        }
        message.setCc(to);

        return message;
    }
}
