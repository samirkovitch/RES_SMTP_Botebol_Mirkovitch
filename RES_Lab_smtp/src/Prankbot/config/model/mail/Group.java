package Prankbot.config.model.mail;

import java.util.ArrayList;
import java.util.List;

public class Group {
   private List<Person> members = new ArrayList<Person>();

   public void addMember(Person newMember){
       this.members.add(newMember);
   }

   public List<Person> getMembers(){
       return new ArrayList(this.members);
   }
}
