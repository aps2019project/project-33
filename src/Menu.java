public class Menu {
    private Menu previousMenu;

    public void inputCommandLine(){}
    public void exit(){}
    public void showHelp(){}

    //Here is Setters && Getters

    public Menu getPreviousMenu() {
        return previousMenu;
    }

    public void setPreviousMenu(Menu previousMenu) {
        this.previousMenu = previousMenu;
    }
}
