package Model.Buffs;

import Model.Buffs.Buff;

public class StunBuff extends Buff {

    public StunBuff(int remainTime, boolean isPermanent, boolean isPassive){
        super(remainTime, isPermanent, isPassive);
    }

    @Override
    public void doEffect(String cardName) {

    }
}
