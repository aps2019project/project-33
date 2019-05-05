package Model.CollectionItem;

import Controller.AttackArea;
import Controller.Impact;
import Model.Enviroment.Cell;

import java.util.ArrayList;

public class Hero extends LivingCard {

    public void impactSpell(String opponentID){
        Impact.impactSpellOfHero(opponentID);
    }

    @Override
    public void showCardInBattle(){
        System.out.println("remaining HP : " + this.getRemainingHP() + " attack power : " + this.getDecreaseHPByAttack()
                + " required mana : " + this.getMP() + this.getDescription());
        //TODO
    }

    //deghat konim ke description baraye hero hamoon tozihare special power e !!
    @Override
    public String getInfo() {
        String info = "Name : " + this.getName() + " - AP : " + this.getDecreaseHPByAttack() + " - HP : "
                + this.getHP() + " Class : " + this.getClass() + " Special power: " + this.getDescription();
        return info;
    }

 /*   public static Hero loadAHeroFromJsonFile(String fileName){
        Hero hero = null;
        Gson gson = new Gson();
        try {
            //addresse in bayd chi bashe ?
            JsonReader reader = new JsonReader(new FileReader(".\\.\\Model\\CollectionItem"+fileName+".json"));
            hero = gson.fromJson(reader, Hero.class);
        }
        catch( Exception e ){

        }
        return hero;
    }
*/

//    public Hero createHero(String playerName, String heroName){
//        Hero hero = loadAHeroFromJsonFile(heroName);
//        int numberOfThisHeroType = 0;
//        ArrayList<LivingCard> usedLivingCards = CollectionItem.getAllLivingCards();
//        for(LivingCard livingCard : usedLivingCards){
//            if(livingCard instanceof Hero){
//                if(livingCard.getName().equals(heroName))
//                    numberOfThisHeroType++;
//            }
//        }
//        String ID = playerName + "_" + heroName + numberOfThisHeroType;
//        hero.setID(ID);
//        CollectionItem.addLivingCardToAllLivingCards(hero);
//        return hero;
//    }
}
