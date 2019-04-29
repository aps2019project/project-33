public class AccountMenu extends Menu {

    public void inputCommandLine(){
        String inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();
        String[] input = inputLine.split("[ ]+");

        if(inputLine.matches("create account .*")){
            String username = input[2];
            if(Account.isFoundAccoutn(username)){
                System.out.println("this username is used");
                this.inputCommandLine();
            }
            String password = Main.scanner.next();
            Account.
        }

    }
}
