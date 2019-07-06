package Controller;

public enum MenuList {
    AccountMenu, MainMenu, ShopMenu, CollectionMenu, ChooseType, ChooseMode, ChooseKind, StoryChapters, Battle, SearchShop,
    ShowingBuy, ShopShowCollection, ShopSearchCollection, BuyMenu, SellMenu, ShopShowSearch, ShowShop, CollectionShowCollection,
    CollectionSearch, CollectionShowSearch, CollectionCreateDeck, CollectionDeleteDeck, CollectionSelectDeckForAdd,
    CollectionAddCardToDeck, CollectionSelectDeckForRemove, CollectionRemoveCardFromDeck, CollectionValidateDeck,
    CollectionSelectDeck;

    public String getAddressOfFile() {
        if (this.equals(AccountMenu)) return "AccountMenu/AccountMenu.fxml";
        if (this.equals(MainMenu)) return "MainMenu/MainMenu.fxml";
        if (this.equals(ShopMenu)) return "ShopMenu/ShopMenu.fxml";
        if (this.equals(ChooseType)) return "BattleMenu/ChooseType.fxml";
        if (this.equals(ChooseMode)) return "BattleMenu/ChooseMode.fxml";
        if (this.equals(ChooseKind)) return "BattleMenu/ChooseKind.fxml";
        if (this.equals(StoryChapters)) return "BattleMenu/StoryChapters.fxml";
        if (this.equals(SearchShop)) return "ShopMenu/SearchShop.fxml";
        if (this.equals(ShowingBuy)) return "ShopMenu/ShowingBuy.fxml";
        if (this.equals(Battle)) return "Battle/Battle.fxml";
        if (this.equals(ShopShowCollection)) return "ShopMenu/ShopShowCollection.fxml";
        if (this.equals(ShopSearchCollection)) return "ShopMenu/SearchCollection.fxml";
        if (this.equals(BuyMenu)) return "ShopMenu/Buy.fxml";
        if (this.equals(SellMenu)) return "ShopMenu/SellMenu.fxml";
        if (this.equals(ShopShowSearch)) return "ShopMenu/ShopShowSearch.fxml";
        if (this.equals(ShowShop)) return "ShopMenu/ShowShop.fxml";
        if (this.equals(CollectionMenu)) return "CollectionMenu/CollectionMenu/CollectionMenu.fxml";
        if (this.equals(CollectionShowCollection)) return "CollectionMenu/ShowCollection/ShowCollection.fxml";
        if (this.equals(CollectionSearch)) return "CollectionMenu/Search/Search.fxml";
        if (this.equals(CollectionShowSearch)) return "CollectionMenu/Search/ShowSearch.fxml";
        if (this.equals(CollectionCreateDeck)) return "CollectionMenu/CreateDeck/CreateDeck.fxml";
        if (this.equals(CollectionDeleteDeck)) return "CollectionMenu/DeleteDeck/DeleteDeck.fxml";
        if (this.equals(CollectionSelectDeckForAdd)) return "CollectionMenu/AddCardToDeck/SelectDeck.fxml";
        if (this.equals(CollectionAddCardToDeck)) return "CollectionMenu/AddCardToDeck/SelectCard.fxml";
        if (this.equals(CollectionSelectDeckForRemove)) return "CollectionMenu/RemoveCardFromDeck/SelectDeck.fxml";
        if (this.equals(CollectionRemoveCardFromDeck)) return "CollectionMenu/RemoveCardFromDeck/SelectCard.fxml";
        if (this.equals(CollectionValidateDeck)) return "CollectionMenu/ValidateDeck/ValidateDeck.fxml";
        if (this.equals(CollectionSelectDeck)) return "CollectionMenu/SelectDeck/SelectDeck.fxml";
        return null;
    }
}

