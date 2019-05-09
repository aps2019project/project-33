package Generator;

import Controller.Application;
import Model.Collection;
import Model.CollectionItem.*;
import Model.Deck;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.management.MemoryNotificationInfo;

public class ShopGenerator {

    public static void generate() throws IOException {
        Collection shop = new Collection();
        int numberOfEachCard = 10;
        DeckGenerator deckGenerator = new DeckGenerator();
        for (int i = 0; i < numberOfEachCard; i++) {
            for (String heroName : deckGenerator.heroNames) {
                Hero hero = Hero.createHero(heroName, "Shop");
                shop.addCollectionItemToCollection(hero.getID());
            }
            for (String spellName : deckGenerator.spellNames) {
                Spell spell = Spell.createSpell(spellName, "Shop");
                shop.addCollectionItemToCollection(spell.getID());
            }
            for(String itemName : deckGenerator.itemNames){
                Item item = Item.createItem(itemName, "Shop");
                shop.addCollectionItemToCollection(item.getID());
            }
            for(String minionName : deckGenerator.minionNames){
                Minion minion = Minion.createMinion(minionName, "Shop");
                shop.addCollectionItemToCollection(minion.getID());
            }
        }
        Application.writeJSON(shop, "Data/Memory/Shop/Shop.json");
    }
}

