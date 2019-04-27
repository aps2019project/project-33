import java.util.ArrayList;

public class Collection {
    private ArrayList<CollectionItem> cards;
    private Deck mainDeck;

    public void removeCardFromCollection(String cardName){}
    public void showDeck(String deckName){}
    public void showAllDecks(){}
    public void selectDeck(String deckName){}
    public boolean checkValidateDeck(String deckName){
        return false;
    }
    public void removeCardFromDeck(String ID, String deckName){}
    public void addCardToDeck(String ID, String deckName){}
    public void deleteDeck(String deckName){}
    public void createDeck(String deckName){}
    public void save(){}
    public void showCollection(){}
    public int search(String cardName){
        return 0;
    }
}
