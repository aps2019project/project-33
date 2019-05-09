package Model.CollectionItem;

import Controller.Application;
import Controller.Impact;
import Model.Collection;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public  class Item extends CollectionItem {

    public static Item createItem(String itemName, String playerName) throws FileNotFoundException {
        String address = "Data/CollectionItem/Item/" + itemName + ".json";
        Item item = (Item) Application.readJSON(Item.class, address);

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
    public String  getInfo(){
        String info = "Name : " + this.getName() + " - ID : " + this.getID() + " - Desc " + this.getDescription();
        return info;
    }

    //Here is Setters && Getters

}
