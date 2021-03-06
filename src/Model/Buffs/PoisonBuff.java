package Model.Buffs;

import Model.Buffs.Buff;

public class PoisonBuff extends Buff {

    private int decreaseHP;

    public PoisonBuff(int remainTime, boolean isPermanent, boolean isPassive, int decreaseHP){
        super(remainTime, isPermanent, isPassive);
        this.decreaseHP = decreaseHP;
    }


    @Override
    public void doEffect(String cardName) {

    }

    public int getDecreaseHP() {
        return decreaseHP;
    }

    public void setDecreaseHP(int decreaseHP) {
        this.decreaseHP = decreaseHP;
    }
}
