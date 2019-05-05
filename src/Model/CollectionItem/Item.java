package Model.CollectionItem;

import Controller.Impact;
import Model.Collection;

import java.util.ArrayList;

public  class Item extends CollectionItem {


    public static Item createItem(String playerName, String itemName){
        Item item = loadItemFromJsonFile(itemName);
        int numberOfThisItemType = 0;
        ArrayList<Item> usedItems = CollectionItem.getAllItems();
        for(Item usedItem : usedItems){
            if(usedItem.getName().equals(itemName))
                numberOfThisItemType++;
        }
        String ID = playerName + "_" + itemName + "_" + numberOfThisItemType;
        item.setID(ID);
        CollectionItem.addItemToAllItems(item);
        return item;
    }

    @Override
    public void doImpact(String livingCardID) {
        Impact.impactItem(this, livingCardID);
    }

    @Override
    public String  getInfo(){
        String info = "Name : " + this.getName() + " - Desc " + this.getDescription();
        return info;
    }

    //Here is Setters && Getters

}
