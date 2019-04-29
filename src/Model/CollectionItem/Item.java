package Model.CollectionItem;

public  class Item extends CollectionItem {


    @Override
    public void doImpact() {
    }

    @Override
    public String  getInfo(){
        String info = "Name : " + this.getName() + " - Desc " + this.getDesc();
    }

    //Here is Setters && Getters

}
