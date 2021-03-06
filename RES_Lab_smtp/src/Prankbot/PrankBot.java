package Prankbot;

import Prankbot.Config.ConfigManager;
import Prankbot.model.prank.Prank;
import Prankbot.model.prank.PrankGenerator;
import Prankbot.smtp.SmtpClient;

import java.io.IOException;
import java.util.List;

public class PrankBot {

    public static void main(String[] args) {
        String path = "Config/";
        String cp = path + "config.properties";
        String msg = path + "messages.utf8";
        String vctm = path + "victims.RES.utf8";
        ConfigManager cm = null;
        try {
            cm = new ConfigManager(cp, msg, vctm);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrankGenerator pg = new PrankGenerator(cm);
        SmtpClient sc = new SmtpClient(cm.getSmtpServerAddress(), cm.getPort());
        List<Prank> lp = pg.generatePranks();
        for (Prank p : lp){
            try {
                sc.sendMessage(p.generateMAilMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
