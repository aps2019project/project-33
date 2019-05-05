package Model.Buffs;

import Controller.*;


public abstract class Buff {

    private boolean isPermanent;
    private int remainTime;
    private boolean isPassive;

    public Buff(int remainTime, boolean isPermanent, boolean isPassive){
        this.isPermanent = isPermanent;
        this.remainTime = remainTime;
        this.isPassive = isPassive;
    }

    abstract public void doEffect(String cardName);

    public boolean isPermanent() {
        return isPermanent;
    }

    public void setPermanent(boolean permanent) {
        isPermanent = permanent;
    }

    public int getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(int remainTime) {
        this.remainTime = remainTime;
    }

    public boolean isPassive() {
        return isPassive;
    }

    public void setPassive(boolean passive) {
        isPassive = passive;
    }
}
