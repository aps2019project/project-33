package Model.Buffs;

import Model.Mana;

public class ManaBuff {
    private int amount, remainTime;

    public ManaBuff(int amount, int remainTime){
        this.amount = amount;
        this.remainTime = remainTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(int remainTime) {
        this.remainTime = remainTime;
    }

    public void effect(Mana mana) {
        mana.increaseChangeManaByItem(this.amount);
        this.remainTime --;
        if(this.remainTime <= 0)
            mana.getManaBuffs().remove(this);
    }
}
