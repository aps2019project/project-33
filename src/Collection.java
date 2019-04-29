import java.util.ArrayList;

public class Collection {
    private ArrayList<CollectionItem> cards;
    private ArrayList<Deck> allDecks = new ArrayList<>();
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
    public void addDeck(Deck deck){
        this.allDecks.add(deck);
    }

    //Here is Setters && Getters

    public ArrayList<CollectionItem> getCards() {
        return cards;
    }

    public void setCards(ArrayList<CollectionItem> cards) {
        this.cards = cards;
    }

    public ArrayList<Deck> getAllDecks() {
        return allDecks;
    }

    public void setAllDecks(ArrayList<Deck> allDecks) {
        this.allDecks = allDecks;
    }

    public Deck getMainDeck() {
        return mainDeck;
    }

    public void setMainDeck(Deck mainDeck) {
        this.mainDeck = mainDeck;
    }
}
