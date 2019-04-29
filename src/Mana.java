public class Mana {

    private int curentMana;
    private int maximumMana;

    public Boolean decreaseMana (int spendMana) {
        if (curentMana < spendMana) {
            return false;
        } else {
            curentMana -= spendMana;
            return true;
        }
    }

    public void configueMana (){
        if (maximumMana < 9) {
            maximumMana++;
        }
        curentMana = maximumMana;
    }

    public int getCurentMana(){
        return curentMana;
    }

    public int getMaximumMana() {
        return maximumMana;
    }

    public void setCurentMana(int curentMana) {
        this.curentMana = curentMana;
    }

    public void setMaximumMana(int maximumMana) {
        this.maximumMana = maximumMana;
    }
}
