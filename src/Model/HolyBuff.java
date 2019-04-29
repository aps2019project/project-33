package Model;

public class HolyBuff extends Buff {

    private int shieldPower;

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
