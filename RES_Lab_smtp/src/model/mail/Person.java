package model.mail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
    private String firstName;
    private String lastName;
    private String addresseMail;

    public Person(String firstName, String lastName, String addresseMail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addresseMail = addresseMail;
    }

    public Person(String addresseMail) {
        this.addresseMail = addresseMail;
        Pattern pattern = Pattern.compile("(.*)\\.(.*@)");
        Matcher matcher = pattern.matcher((addresseMail));
        boolean okay = matcher.find();

        if (okay){
            this.firstName = matcher.group(1);
            firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1);

            this.lastName = matcher.group(2);
            lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddresseMail() {
        return addresseMail;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddresseMail(String addresseMail) {
        this.addresseMail = addresseMail;
    }
}
