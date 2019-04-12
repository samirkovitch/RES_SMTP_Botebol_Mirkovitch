package model.prank;

import config.ConfigManager;
import model.mail.Group;
import model.mail.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class PrankGenerator {
    private ConfigManager configManager;
    private static final Logger LOG = Logger.getLogger(PrankGenerator.class.getName());

    public PrankGenerator(ConfigManager configManager){
        this.configManager = configManager;
    }

    public List<Group> generateGroups(List<Person> victims, int numGroups){
        List<Person> correctVictims = victims;
        Collections.shuffle(correctVictims);
        List<Group> groups = new ArrayList<Group>();
        int turn = 0;
        Group target;

        for (int i = 0; i < numGroups; ++i){
            Group group = new Group();
            groups.add(group);
        }

        while (correctVictims.size() > 0){
            target = groups.get(turn);
            turn = (turn + 1) % groups.size();
            Person victim = correctVictims.remove(0);
            target.addMember(victim);
        }

        return groups;
    }

    public List<Prank> generatePranks(){
        List<Prank> pranks = new ArrayList<Prank>();
        List<String> msgs = configManager.getMessage();
        int msgIndex = 0;
        int numGroups = configManager.getNbGroups();
        int numVictims = configManager.getVictims().size();
        List<Group> groups;

        // At leat 2 victims per group
        if (numVictims / numGroups < 2){
            numGroups = numVictims / 2;
            LOG.warning("Not enough victims per group");
        }

        groups = generateGroups(configManager.getVictims(), numGroups);

        for(int i = 0; i < groups.size(); ++i){
            Prank prank = new Prank();
            String msg = msgs.get(msgIndex);

            List<Person> victims = groups.get(i).getMembers();
            Collections.shuffle(victims);
            System.out.println("victim.size : " + victims.size());

            Person sender = victims.remove(0);
            prank.setVictimSender(sender);
            prank.addVictimRecipient(victims);

            prank.addWitnessRecipient(configManager.getWitness());

            msgIndex = ++msgIndex % msgs.size();
            prank.setMessage(msg);

            pranks.add(prank);
        }

        return pranks;
    }
}
