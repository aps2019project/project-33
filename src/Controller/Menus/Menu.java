package Controller.Menus;

import java.io.FileNotFoundException;

abstract public class Menu {
    private Menu previousMenu;

    abstract public void inputCommandLine() throws FileNotFoundException;

    //Here is Setters && Getters

    public Menu getPreviousMenu() {
        return previousMenu;
    }
    public void setPreviousMenu(Menu previousMenu) {
        this.previousMenu = previousMenu;
    }
}
