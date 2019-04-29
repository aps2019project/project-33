public class CollectionMenu extends Menu{
    @Override
    public void inputCommandLine() {
        String inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();
        String[] input = inputLine.split("[ ]+");

        if(inputLine.equals("exit"))
            return;
        else if(inputLine.equals("show")){
            Main.application.getLoggedInAccount().getCollection().showCollection();
        }

    }
}
