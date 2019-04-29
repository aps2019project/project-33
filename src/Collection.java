import java.util.ArrayList;

public class Collection {
    private ArrayList<CollectionItem> cards;
    private Deck mainDeck;

    public void removeCardFromCollection(String cardName){

    }

    public void showDeck(String deckName){
        Deck.showDeck(deckName, false);
    }

    public void showAllDecks(){
        Deck.showAllDecks();
    }

    public void selectDeck(String deckName){
        Deck deck = Deck.getDeckByName(deckName);
        if(deck == null){
            System.out.println("deck not found");
            return;
        }
        this.mainDeck = deck;
    }

    public boolean checkValidateDeck(String deckName){
        Deck deck = Deck.getDeckByName(deckName);
        if(deck == null) {
            System.out.println("deck not found");
            return false;
        }
        return deck.checkValidateDeck();
    }

    boolean validateInput(CollectionItem collectionItem, Deck deck){
        if(deck == null){
            System.out.println("deck not found");
            return false;
        }

        if(collectionItem == null){
            System.out.println("card not found");
            return false;
        }
        return true;
    }

    public void removeCollectionItemFromDeck(String ID, String deckName){
        Deck deck = Deck.getDeckByName(deckName);
        CollectionItem collectionItem = deck.findCollectionItemInDeck(ID);
        if(this.validateInput(collectionItem, deck))
            deck.removeCollectionItem(collectionItem);
    }

    public void addCardToDeck(String ID, String deckName){
        Deck deck = Deck.getDeckByName(deckName);
        CollectionItem collectionItem = CollectionItem.getCollectionItemByID(ID);
        if(this.validateInput(collectionItem, deck))
            deck.addCard(collectionItem);

    }

    public void deleteDeck(String deckName){
        Deck.deleteDeck(deckName);
    }

    public void createDeck(String deckName){
        Deck.createDeck(deckName);
    }

    public ArrayList<CollectionItem> getCards(){
        return this.cards;
    }

    public ArrayList<String> search(String cardName){
        ArrayList<String> IDs = new ArrayList<String>()
        for(CollectionItem collectionItem : this.getCards()){
            if(collectionItem.getName().equals(cardName)){
                IDs.add(collectionItem.getID());
            }
        }
        return IDs;
    }

    public void showHeroes(){
        System.out.println("Heroes :");
        int numberOfHeroes = 0;
        for(CollectionItem collectionItem : this.getCards()){
            if(collectionItem instanceof Hero){
                numberOfHeroes++;
                System.out.print(numberOfHeroes + " : ");
                Hero hero = (Hero)collectionItem;
                hero.showHero();
            }
        }
    }

    public void showItems(){
        System.out.println("Items :");
        int numberOfItems = 0;
        for(CollectionItem collectionItem : this.getCards()){
            if(collectionItem instanceof Item){
                numberOfItems++;
                System.out.print(numberOfItems + " : ");
                Item item = (Item)collectionItem;
                item.showItem();
            }
        }
    }

    public void showCards(){
        int numberOfCards = 0;
        for(CollectionItem collectionItem : this.getCards()){
            if(collectionItem instanceof Card){
                numberOfCards++;
                System.out.print(numberOfCards + " : ");
                Card card = (Card)collectionItem;
                card.showCard();
            }
        }
    }

    public void showCollection(){
        this.showHeroes();
        this.showItems();
        this.showCards();
    }

    public void save(){}

}
