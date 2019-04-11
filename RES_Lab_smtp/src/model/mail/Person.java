package model.mail;

public class Person {
    private String firstName;
    private String lastName;
    private String addresseMail;

    public Person(String firstName, String lastName, String addresseMail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addresseMail = addresseMail;
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
