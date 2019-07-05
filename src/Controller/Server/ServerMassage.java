package Controller.Server;

import Controller.Battle;
import Controller.MenuList;
import Model.Account;

import java.io.Serializable;
import java.util.ArrayList;

public class ServerMassage implements Serializable {
    public enum Type implements Serializable {
        Error, Accept;
    }

    public enum ErrorType implements Serializable {
        LogInFailed, InvalidPasswordForSignUp, InvalidUsernameForSignUp, InvalidAuthToken, InvalidDeckForFirstPlayer,
        InvalidSecondPlayerUsername, InvalidDeckForSecondPlayer, RunningBattleNotFound;
    }

    public enum Command {
        ClearAuthToken;
    }

    public ServerMassage(Type type, ErrorType errorType) {
        this.type = type;
        this.errorType = errorType;
    }

    private Type type;
    private ErrorType errorType;
    private Command command;
    private MenuList currentMenu;

    //Answer to runningBattle request
    private Battle runningBattle;
    private ArrayList<Account> accounts;


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
}
