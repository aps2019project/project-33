package Model;

import Controller.Battle;
import Model.CollectionItem.*;
import Model.Enviroment.Cell;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class AI extends Player {

    ArrayList<String> orders = new ArrayList<>();

    public AI() {
        super(new Account("admin", "1234"));
    }

    public void selectMainDeck(Deck deck) throws FileNotFoundException {
        this.getAccount().setUsername("AI");
        this.getAccount().getCollection().createDeck("AI");
        for(CollectionItem collectionItem : deck.getCards()) {
            CollectionItem newCollectionItem = null;
            if(collectionItem instanceof Spell) newCollectionItem = Spell.createSpell(collectionItem.getName(), "AI");
            if(collectionItem instanceof Hero) newCollectionItem = Hero.createHero(collectionItem.getName(), "AI");
            if(collectionItem instanceof Minion) newCollectionItem = Minion.createMinion(collectionItem.getName(), "AI");
            if(collectionItem instanceof Item) newCollectionItem = Item.createItem(collectionItem.getName(), "AI");
            this.getAccount().getCollection().addCollectionItemToCollection(newCollectionItem.getID());
            this.getAccount().getCollection().addCollectionItemToDeck(newCollectionItem.getID(), "AI");
        }
        this.getAccount().getCollection().selectMainDeck("AI");
    }

    public void preProcess(Battle battle){
        ArrayList<LivingCard> opponentCards = battle.getPlayerOff().getAliveCards();
        ArrayList<Cell> cells = new ArrayList<Cell>();
        for(int i = 0; i < battle.getMap().getHeight(); i ++)
            for(int j = 0; j < battle.getMap().getWidth(); j ++)
                cells.add(battle.getMap().getCellByCoordination(i, j));

        Collections.shuffle(this.getAliveCards());
        Collections.shuffle(opponentCards);
        Collections.shuffle(cells);

        for(LivingCard livingCard : this.getAliveCards()) {
            orders.add("select " + livingCard.getID());
            for(Cell cell : cells)
                orders.add("move to (" + cell.getX() + ", " + cell.getY()+ ")");
            for(LivingCard livingCard1 : opponentCards)
                orders.add("attack " + livingCard1.getID());
        }

        for(CollectionItem collectionItem : this.getHand().getHandCards()){
            for(Cell cell : cells)
                orders.add("insert " + collectionItem.getID() + " in (" + cell.getX() + ", " + cell.getY() +")");
        }
    }

    public String outputSomeRandomOrder(Battle battle){
        if(orders.size() == 0) preProcess(battle);
        if(orders.size() == 1){
          orders.clear();
          return "End turn";
        }
        String result = orders.get(0);
        orders.remove(0);
        return result;
    }
}
