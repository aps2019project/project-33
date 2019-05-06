package Model.Buffs;

public class WeaknessBuff extends ChangeHPAndPower {
    public WeaknessBuff(int remainTime, boolean isPermanent, boolean isPassive, int changeHP, int changePower){
        super(remainTime, isPermanent, isPassive, changeHP, changePower);
    }

}
