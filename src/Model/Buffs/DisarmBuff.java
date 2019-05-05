package Model.Buffs;

import Model.Buffs.Buff;

public class DisarmBuff extends Buff {

    public DisarmBuff(int remainTime, boolean isPermanent, boolean isPassive) {
        super(remainTime, isPermanent, isPassive);
    }

    @Override
    public void doEffect(String cardName) {

    }
}
