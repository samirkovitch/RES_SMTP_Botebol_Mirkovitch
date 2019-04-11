package model.prank;

import model.mail.Person;
import model.mail.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Prank {
    private Person victimSender;
    private final List<Person> victimRecipients = new ArrayList<>();
    private final List<Person> witnessRecipients = new ArrayList<>();
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

        message.setBody(this.message + "\r\n" + victimSender.getFirstName());

        message.setFrom(victimSender.getAddresseMail());

        // A EXPLIQUER CAR PEU CLAIR
        String[] to = victimRecipients
                .stream()
                .map(p -> p.getAddresseMail())
                .collect(Collectors.toList())
                .toArray(new String[] {});
        message.setTo(to);

        // A EXPLIQUER CAR PEU CLAIR
        String[] cc = witnessRecipients
                .stream()
                .map(p -> p.getAddresseMail())
                .collect(Collectors.toList())
                .toArray(new String[] {});
        message.setCc(cc);

        return message;
    }
}
