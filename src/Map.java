import java.util.ArrayList;

public class Map {

    private ArrayList<Cell> cells = new ArrayList<>();
    private int height;
    private int width;

    public void addNewCell (Cell cell) {
        cells.add(cell);
    }

    public ArrayList<Cell> getCells (){
        return cells;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Cell getCellByCoordination (int x, int y){
        for (Cell cell : cells){
            if (cell.getX() == x && cell.getY() == y) {
                return cell;
            }
        }
        return null;
    }

    public void insertCard (String cardName, int x, int y){
        Cell cell = getCellByCoordination(x, y);
        cell.insertCard(cardName);
    }

    public void deleteCard (int x, int y){
        Cell cell = getCellByCoordination(x, y);
        getCells().remove(cell);
    }

}