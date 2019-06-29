package Controller;

public enum MenuList {
    AccountMenu, MainMenu, ShopMenu, CollectionMenu, ChooseType, ChooseMode, ChooseKind, ChooseChapter, Battle;

    public String getAddressOfFile() {
        if(this.equals(AccountMenu)) return "AccountMenu/AccountMenu.fxml";
        if(this.equals(MainMenu)) return "MainMenu/MainMenu.fxml";
        if(this.equals(ShopMenu)) return "ShopMenu/ShopMenu.fxml";
        if(this.equals(CollectionMenu)) return "CollectionMenu/CollectionMenu.fxml";
        if(this.equals(ChooseType)) return "BattleMenu/ChooseType.fxml";
        if(this.equals(ChooseMode)) return "BattleMenu/ChooseMode.fxml";
        if(this.equals(ChooseKind)) return "BattleMenu/ChooseKind.fxml";
        if(this.equals(ChooseChapter)) return "BattleMenu/ChooseChapter.fxml";
        return null;
    }
}

