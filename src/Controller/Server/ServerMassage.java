package Controller.Server;

import Controller.Battle;
import Controller.MenuList;
import Model.Account;
import Model.Collection;
import Model.CollectionItem.Card;
import Model.CollectionItem.CollectionItem;
import Model.Deck;
import Model.Massage;

import java.io.Serializable;
import java.util.ArrayList;

public class ServerMassage implements Serializable {


    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Deck getImportedDeck() {
        return importedDeck;
    }

    public void setImportedDeck(Deck importedDeck) {
        this.importedDeck = importedDeck;
    }

    public ArrayList<CollectionItem> getCollectionItems() {
        return collectionItems;
    }

    public void setCollectionItems(ArrayList<CollectionItem> collectionItems) {
        this.collectionItems = collectionItems;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Boolean getValidateDeck() {
        return validateDeck;
    }

    public void setValidateDeck(Boolean validateDeck) {
        this.validateDeck = validateDeck;
    }

    public Collection getShopCollection() {
        return shopCollection;
    }

    public void setShopCollection(Collection shopCollection) {
        this.shopCollection = shopCollection;
    }

    private ArrayList<Card> graveYard;

    public ArrayList<Card> getGraveYard() {
        return graveYard;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public enum Type implements Serializable {
        Error, Accept;
    }

    public enum ErrorType implements Serializable {
        LogInFailed, InvalidPasswordForSignUp, InvalidUsernameForSignUp, InvalidAuthToken, InvalidDeckForFirstPlayer,
        InvalidSecondPlayerUsername, InvalidDeckForSecondPlayer, RunningBattleNotFound, PlayerAreNotAvailable,
        InvalidDeckNameForCreate, InvalidDeckNameForImport, NotFoundCollectionItem, LowBudget, WTF, CantBuyItem;
    }

    public enum Command {
        ClearAuthToken;
    }

    public ServerMassage(Type type, ErrorType errorType) {
        this.type = type;
        this.errorType = errorType;
    }

    public void setGraveYard(ArrayList<Card> graveYard) {
        this.graveYard = graveYard;
    }

    private Type type;
    private ErrorType errorType;
    private Command command;
    private MenuList currentMenu;

    //Answer to runningBattle request
    private Battle runningBattle;
    private ArrayList<Account> accounts;

    //Answer to GiveAllMassages
    private ArrayList<Massage> massages;
    //give budget
    private int budget;
// give collection and show
    private Collection collection;
//import
    private Deck importedDeck;

    //search collection
    private ArrayList<CollectionItem> collectionItems;
//show deck
    private Deck deck;
    //validate deck
    private Boolean validateDeck;
//show shop
    private Collection shopCollection;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public MenuList getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(MenuList currentMenu) {
        this.currentMenu = currentMenu;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Battle getRunningBattle() {
        return runningBattle;
    }

    public void setRunningBattle(Battle runningBattle) {
        this.runningBattle = runningBattle;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public ArrayList<Massage> getMassages() {
        return massages;
    }

    public void setMassages(ArrayList<Massage> massages) {
        this.massages = massages;
    }
}
