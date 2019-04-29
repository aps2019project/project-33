public class AccountMenu extends Menu {


    //tahesh in ja bayad har koodoom az bakhsh ha beshe ye tabe

    @Override
    public void inputCommandLine() {
        String inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();
        String[] input = inputLine.split("[ ]+");

        if (inputLine.matches("create account .*")) {
            createAccount(input[2]);
        } else if (inputLine.matches("login *.")) {
            String username = input[1];
            login(username);
        } else if (inputLine.equals("show leaderboard")){
            Account.showLeaderBoard();
        }
        else if(inputLine.equals("save")){
            Main.application.getLoggedInAccount().save();
        }
        else if(inputLine.equals("help")){
            AccountMenu.showHelp();
        }
        else if(inputLine.equals("logout")){
            this.logout();
        }
    }

    public static void showHelp() {
        System.out.println("1. create account [username]");
        System.out.println("2. login [username]");
        System.out.println("3. show leaderboard");
        System.out.println("4. save");
        System.out.println("5. logout");
        System.out.println("6. help");
    }

    private void logout(){
        Main.application.setLoggedInAccount(null);
    }

    private void createAccount(String username) {
        if (Account.getAccountByUsername(username) != null) {
            System.out.println("this username is used");
            return;
        }
        String password = Main.scanner.next();
        Account.createAccount(username, password);
    }

    private void login(String username) {
        Account account = Account.getAccountByUsername(username);
        if (account == null) {
            System.out.println("Invalid Username !!");
        }
        String password = Main.scanner.next();
        if(!account.getPassword().equals(password)){
            System.out.println("Invalid password !!");
        }
        Main.application.setLoggedInAccount(account);
        return;
    }
}
