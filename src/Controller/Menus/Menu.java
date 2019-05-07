package Controller.Menus;

import java.io.FileNotFoundException;
import java.io.IOException;

abstract public class Menu {
    private Menu previousMenu;

    abstract public void inputCommandLine() throws IOException;

    //Here is Setters && Getters

    public Menu getPreviousMenu() {
        return previousMenu;
    }
    public void setPreviousMenu(Menu previousMenu) {
        this.previousMenu = previousMenu;
    }
}
