package Model.CollectionItem;

import Controller.Impact;

public  class Item extends CollectionItem {


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
