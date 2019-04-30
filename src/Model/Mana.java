package Model;

public class Mana {

    private int currentMana;
    private int maximumMana;

    public Boolean decreaseMana (int spendMana) {
        if (currentMana < spendMana) {
            return false;
        } else {
            currentMana -= spendMana;
            return true;
        }
    }

    public void configueMana (){
        if (maximumMana < 9) {
            maximumMana++;
        }
        currentMana = maximumMana;
    }

    public int getCurrentMana(){
        return currentMana;
    }

    public int getMaximumMana() {
        return maximumMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public void setMaximumMana(int maximumMana) {
        this.maximumMana = maximumMana;
    }
}
