package Model.CollectionItem;

import Controller.Application;
import Controller.Impact;
import Model.Collection;
import Model.Enviroment.Cell;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public  class Item extends CollectionItem {
    private Cell cell;

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
    public ArrayList<Label> getInfo() {
        ArrayList<Label> labels = new ArrayList<>();

        Label nameLabel = new Label("NAME : " + this.getName());
        labels.add(nameLabel);

        Label descLabel = new Label("DESC : " + this.getDescription());
        labels.add(descLabel);

        Label priceLabel = new Label("PRICE : " + this.getPrice());
        labels.add(priceLabel);

        return labels;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    //Here is Setters && Getters

}
