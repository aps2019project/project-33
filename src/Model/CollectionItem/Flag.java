package Model.CollectionItem;

import Model.Player;

public class Flag extends Item {
    private int positionRow;
    private int PositionColumn;
    //in vase flag bashe ya moteghayer too battle?
    private Player flagOwner;
    private int numberOfGotRounds;
    private LivingCard flagLivingCard;

    {
        numberOfGotRounds = 0;
        flagOwner = null;
    }

    public void setFlagLivingCard(LivingCard livingCard){
        this.flagLivingCard = livingCard;
    }

    public LivingCard getFlagLivingCard(){
        return this.flagLivingCard;
    }

    public void setNumberOfGotRounds(int numberOfGotRounds){
        this.numberOfGotRounds = numberOfGotRounds;
    }

    public int getNumberOfGotRounds(){
        return this.numberOfGotRounds;
    }

    public void setFlagOwner(Player player){
        this.flagOwner = player;
    }
    public Player getFlagOwner(){
        return this.flagOwner;
    }

    public void setPosition(int positionRow, int positionColumn){
        this.PositionColumn = positionColumn;
        this.positionRow = positionRow;
    }

    public int getPositionRow(){
        return this.positionRow;
    }

    public int getPositionColumn(){
        return this.PositionColumn;
    }
}
