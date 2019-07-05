package Model;

import Controller.Battle;
import Model.CollectionItem.*;
import Model.Enviroment.Cell;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class AI extends Player implements Serializable {

    ArrayList<String> orders = new ArrayList<>();

    public AI() {
        super(new Account("admin", "1234", 0));
    }

    public void selectMainDeck(Deck deck) throws FileNotFoundException {
        this.getAccount().getCollection().createDeck("AI");
        for (CollectionItem collectionItem : deck.getCards()) {
            CollectionItem newCollectionItem = null;
            String name = collectionItem.getName();
            if (collectionItem instanceof Spell)
                newCollectionItem = Spell.createSpell(collectionItem.getName(), name);
            if (collectionItem instanceof Hero)
                newCollectionItem = Hero.createHero(collectionItem.getName(), name);
            if (collectionItem instanceof Minion)
                newCollectionItem = Minion.createMinion(collectionItem.getName(), name);
            if (collectionItem instanceof Item)
                newCollectionItem = Item.createItem(collectionItem.getName(), name);
            this.getAccount().getCollection().addCollectionItemToCollection(newCollectionItem.getID());
            this.getAccount().getCollection().addCollectionItemToDeck(newCollectionItem.getID(), "AI");
        }
        this.getAccount().getCollection().selectMainDeck("AI");
    }

    public void preProcess(Battle battle) {
        ArrayList<LivingCard> opponentCards = battle.getPlayerOff().getAliveCards();
        ArrayList<Cell> cells = new ArrayList<Cell>();
        for (int i = 0; i < battle.getMap().getHeight(); i++)
            for (int j = 0; j < battle.getMap().getWidth(); j++)
                cells.add(battle.getMap().getCellByCoordination(i, j));

        Collections.shuffle(this.getAliveCards());
        Collections.shuffle(opponentCards);
        Collections.shuffle(cells);

        for (LivingCard livingCard : this.getAliveCards()) {
            orders.add("select " + livingCard.getID());
            for (Cell cell : cells)
                orders.add("move to (" + cell.getX() + ", " + cell.getY() + ")");
            for (LivingCard livingCard1 : opponentCards)
                orders.add("attack " + livingCard1.getID());
        }

        for (CollectionItem collectionItem : this.getHand().getHandCards()) {
            for (Cell cell : cells)
                orders.add("insert " + collectionItem.getID() + " in (" + cell.getX() + ", " + cell.getY() + ")");
        }
    }

    public void outputSomeRandomOrder(Battle battle) {
        if (orders.size() == 0) preProcess(battle);
        while(orders.size() > 1) {
            String result = orders.get(0);
            orders.remove(0);
            sendCommandToBattle(result, battle);
        }
        String result = "End turn";
        sendCommandToBattle(result, battle);
        orders.clear();
    }

    void sendCommandToBattle(String command, Battle battle){
        battle.inputCommandLine(command, this.getAccount().getUsername());
    }
}
