package Controller;

abstract public class Menu {
    private Menu previousMenu;

    abstract public void inputCommandLine();

    //Here is Setters && Getters

    public Menu getPreviousMenu() {
        return previousMenu;
    }
    public void setPreviousMenu(Menu previousMenu) {
        this.previousMenu = previousMenu;
    }
}
