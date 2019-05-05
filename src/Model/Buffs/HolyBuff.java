package Model.Buffs;

import Model.Buffs.Buff;

public class HolyBuff extends Buff {

    private int shieldPower;

    public HolyBuff(int remainTime, boolean isPermanent, boolean isPassive, int shieldPower){
        super(remainTime, isPermanent, isPassive);
        this.shieldPower = shieldPower;
    }

    @Override
    public void doEffect(String cardName) {

    }

    public int getShieldPower() {
        return shieldPower;
    }

    public void setShieldPower(int shieldPower) {
        this.shieldPower = shieldPower;
    }
}
