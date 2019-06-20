package Controller.Menus;

import java.io.FileNotFoundException;
import java.io.IOException;

abstract public class Menu {
    abstract public void inputCommandLine(String inputLine) throws IOException;
}
