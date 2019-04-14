package Prankbot.config.Config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import Prankbot.config.model.mail.Person;

public class ConfigManager {
    private String smtpServerAddress;
    private int port;
    private List<String> message;
    private List<Person> victims;
    private List<Person> witness;
    private int nbGroups;
    private String configFile;
    private String messageFile;
    private String victimFile;



public ConfigManager(String configFile, String messageFile, String victimFile) throws IOException {
    this.configFile = configFile;
    this.messageFile = messageFile;
    this.victimFile = victimFile;
    victims = loadAddresses(victimFile);
    message = loadMessage (messageFile);
    loadProperties(configFile);
}

    private List<String> loadMessage(String file) throws IOException {
        List<String> result = new ArrayList<String>();
        FileInputStream f = new FileInputStream(file);
        InputStreamReader i = new InputStreamReader(f, "UTF-8");
        BufferedReader b = new BufferedReader(i);
        String line = b.readLine();
        while (line != null){
            String individualMessage = "";
            while (line != null && !line.equals("---")){
             individualMessage += line + "\r\n";
             line = b.readLine();
            }
            result.add(individualMessage);
            line = b.readLine();
        }
        return result;
    }

    private List<Person> loadAddresses(String file) throws IOException {
    List<Person> result = new ArrayList<Person>();
    FileInputStream f = new FileInputStream(file);
    InputStreamReader i = new InputStreamReader(f, "UTF-8");
    BufferedReader b = new BufferedReader(i);
    String address = b.readLine();
    while (address != null){
        result.add(new Person(address));
        address = b.readLine();
    }
    return result;
    }

    private void loadProperties(String file) throws IOException {
        FileInputStream f = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(f);
        this.smtpServerAddress = properties.getProperty("smtpServerAddress");
        this.port = Integer.parseInt(properties.getProperty("smtpServerPort"));
        this.nbGroups = Integer.parseInt(properties.getProperty("numberOfGroups"));
        this.witness = new ArrayList();
        String[] witnessAddresses = properties.getProperty("witnessToCC").split(",");
        for(String address : witnessAddresses){
            this.witness.add(new Person(address));
        }
    }
    public List<String> getMessage() {
        return message;
    }

    public List<Person> getVictims() {
        return victims;
    }

    public List<Person> getWitness() {
        return witness;
    }

    public String getSmtpServerAddress() {
        return smtpServerAddress;
    }

    public int getPort() {
        return port;
    }

    public int getNbGroups() {
        return nbGroups;
    }
}























