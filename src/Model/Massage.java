package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Massage implements Serializable {
    private static ArrayList<Massage> massages = new ArrayList<>();
    private String massageText;
    private Account speaker;

    public Massage(String massageText, Account speaker) {
        this.massageText = massageText;
        this.speaker = speaker;
    }

    public synchronized static void addMassage(Massage massage) {
        synchronized (massages) {
            massages.add(massage);
        }
    }

    public String getMassageText() {
        return massageText;
    }

    public void setMassageText(String massageText) {
        this.massageText = massageText;
    }

    public Account getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Account speaker) {
        this.speaker = speaker;
    }

    public static ArrayList<Massage> getMassages() {
        return massages;
    }

}
