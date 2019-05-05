package Model.Buffs;

import Model.Buffs.Buff;

public class ChangeHPAndPower extends Buff {

    private int changeHP;
    private int changePower;

    public ChangeHPAndPower(int remainTime, boolean isPermanent, boolean isPassive) {
        super(remainTime, isPermanent, isPassive);
    }

    @Override
    public void doEffect(String cardName) {

    }

    public int getChangeHP() {
        return changeHP;
    }

    public void setChangeHP(int changeHP) {
        this.changeHP = changeHP;
    }

    public int getChangePower() {
        return changePower;
    }

    public void setChangePower(int changePower) {
        this.changePower = changePower;
    }
}
