package Model.CollectionItem;

import Controller.Application;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Hero extends LivingCard {
    private int deadAfterRounds = 0;
    private boolean havePareSimorgh, haveAssassinationDagger, haveKingKiller;
    private boolean haveSoulEater;
    private int minOfPareSimorgh;
    private int coolDown, maxCoolDown;


    public void setMaxCoolDown(int maxCoolDown){
        this.maxCoolDown = maxCoolDown;
    }

    public int getMaxCoolDown(){
        return this.maxCoolDown;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public Hero(){

    }

    public Hero(String name, int price, int HP, int AP, String counterAttackType, int MP, int maxCoolDown){
        this.setName(name);
        this.setPrice(price);
        this.setHP(HP);
        this.setAP(AP);
        this.setCounterAttackType(counterAttackType);
        this.setMP(MP);
        this.setMaxCoolDown(maxCoolDown);
        this.setCoolDown(maxCoolDown);
        this.setHP(HP);
    }

    /*
    public void impactSpell(String opponentID){
        Impact.impactSpellOfHero(opponentID);
    }
*/
    @Override
    public void showCardInBattle() {
        System.out.println("Hero:");
        System.out.println("Name: " + this.getName());
        System.out.println("Cost: " + this.getPrice());
        System.out.println("Desc: " + this.getDescription());
    }

    //deghat konim ke description baraye hero hamoon tozihare special power e !!
    @Override
    public ArrayList<Label> getInfo() {
        ArrayList<Label> labels = new ArrayList<>();

        Label nameLabel = new Label("NAME : " + this.getName());
        labels.add(nameLabel);

        Label APLabel = new Label("AP : " + this.getAP());
        labels.add(APLabel);

        Label HPLabel = new Label("HP : " + this.getHP());
        labels.add(HPLabel);

        Label specialPowerLabel = new Label("SPECIAL POWER : " + this.getDescription());
        labels.add(specialPowerLabel);

        Label priceLabel = new Label("PRICE : " + this.getPrice());
        labels.add(priceLabel);

        return labels;
    }

    public void checkPareSimorgh() {
        if (!this.havePareSimorgh) return;
        if (this.getHP() < minOfPareSimorgh) {
            this.changeHP(this.getHP());
            this.havePareSimorgh = false;
        }
    }

    public int getDeadAfterRounds() {
        return deadAfterRounds;
    }

    public void setDeadAfterRounds(int deadAfterRounds) {
        this.deadAfterRounds = deadAfterRounds;
    }

    public boolean isHavePareSimorgh() {
        return havePareSimorgh;
    }

    public void setHavePareSimorgh(boolean havePareSimorgh) {
        this.havePareSimorgh = havePareSimorgh;
    }

    public int getMinOfPareSimorgh() {
        return minOfPareSimorgh;
    }

    public void setMinOfPareSimorgh(int minOfPareSimorgh) {
        this.minOfPareSimorgh = minOfPareSimorgh;
    }


    public static Hero createHero(String heroName, String playerName) throws FileNotFoundException {
        String address = "Data/CollectionItem/Hero/" + heroName + ".json";
        Hero hero = (Hero) Application.readJSON(Hero.class, address);
        int numberOfThisHeroType = 0;
        ArrayList<LivingCard> usedLivingCards = CollectionItem.getAllLivingCards();
        for(LivingCard livingCard : usedLivingCards){
            if(livingCard instanceof Hero){
                if(livingCard.getName().equals(heroName))
                    numberOfThisHeroType++;
            }
        }

        String ID = playerName + "_" + heroName + "_" + numberOfThisHeroType;
        hero.setID(ID);
        CollectionItem.addLivingCardToAllLivingCards(hero);
        return hero;
    }

    public boolean isHaveAssassinationDagger() {
        return haveAssassinationDagger;
    }

    public void setHaveAssassinationDagger(boolean haveAssassinationDagger) {
        this.haveAssassinationDagger = haveAssassinationDagger;
    }

    public boolean isHaveKingKiller() {
        return haveKingKiller;
    }

    public void setHaveKingKiller(boolean haveKingKiller) {
        this.haveKingKiller = haveKingKiller;
    }

    @Override
    public boolean isHaveSoulEater() {
        return haveSoulEater;
    }

    @Override
    public void setHaveSoulEater(boolean haveSoulEater) {
        this.haveSoulEater = haveSoulEater;
    }
}
