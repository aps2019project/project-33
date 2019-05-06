package Controller;

import Model.CollectionItem.*;
import Model.Enviroment.Cell;
import Model.Enviroment.Map;
import Model.Player;

import java.util.ArrayList;

public class AttackArea {

    //Inja moshakhas mikone ke che khoone hayi ro az cell ha migire, mostaghel az in ke che type i ro lazem dare

    private static ArrayList<Cell> getCellsInArea(Cell cell, int maxDistance, Battle battle) {
        ArrayList<Cell> cells = new ArrayList<>();
        Map map = battle.getMap();
        for (int i = 0; i < map.getHeight(); i++)
            for (int j = 0; j < map.getWidth(); j++) {
                if (AttackArea.distance(cell.getX(), cell.getY(), i, j) > maxDistance) continue;
                Cell cell1 = map.getCellByCoordination(i, j);
                cells.add(cell1);
            }
    }

    private static ArrayList<Cell> getNeighbors(Cell cell, Battle battle) {
        ArrayList<Cell> neighbors = new ArrayList<>();
        Map map = battle.getMap();
        for (int i = 0; i < map.getHeight(); i++)
            for (int j = 0; j < map.getWidth(); j++) {
                if (!isNeighbor(cell.getX(), cell.getY(), i, j))
                    continue;
                Cell cell1 = map.getCellByCoordination(i, j);
                neighbors.add(cell1);
            }
        return neighbors;
    }

    // in ja baraye spell e o hamaro satisfy mikone
    private static ArrayList<Cell> getCellsOfColumn(Cell cell, Battle battle) {
        ArrayList<Cell> cellsOfColumn = new ArrayList<>();
        Map map = battle.getMap();
        for (int i = 0; i < map.getHeight(); i++)
            for (int j = 0; j < map.getWidth(); j++) {
                Cell cell1 = map.getCellByCoordination(i, j);
                if (j == cell.getY())
                    cellsOfColumn.add(cell1);
            }
        return cellsOfColumn;
    }

    private static ArrayList<Cell> getCellsOfRow(Cell cell, Battle battle) {
        ArrayList<Cell> cellsOfRow = new ArrayList<>();
        Map map = battle.getMap();
        for (int i = 0; i < map.getHeight(); i++)
            for (int j = 0; j < map.getWidth(); j++) {
                Cell cell1 = map.getCellByCoordination(i, j);
                if (i == cell.getX())
                    cellsOfRow.add(cell1);
            }
        return cellsOfRow;
    }

    private static ArrayList<Cell> getSquareOfCells(Spell spell, Cell cell, Battle battle, int length) {
        ArrayList<Cell> squareOfCells = new ArrayList<>();
        int x = cell.getX(), y = cell.getY();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int newX = x + (length - 1) / 2 + i;
                int newY = y + (length - 1) / 2 + j;
                Cell cell1 = battle.getMap().getCellByCoordination(newX, newY);
                if (cell1 != null) {
                    squareOfCells.add(cell1);
                }
            }
        }
        return squareOfCells;
    }

    private static int distance(int i, int j, int x, int y) {
        return Math.abs(i - x) + Math.abs(j - y);
    }

    private static boolean isNeighbor(int x1, int y1, int x2, int y2) {
        if (Math.abs(x1 - x2) > 1) return false;
        if (Math.abs(y1 - y2) > 1) return false;
        return true;
    }

    // in ja ham tamoom mishe

    // in ja miad mige che noe LivingCard hayi tahte tasire in lanati hastan
    // albate Cell hashoono mige behemoon khodeshoono kar nadare

    private static ArrayList<Cell> getCells(Information information, Player player) {
        ArrayList<Cell> impactCells = new ArrayList<>();
        if (information.isHeroImpact())
            impactCells.addAll(getCellOfLivingCard(player, (LivingCard) new Hero()));
        if (information.isMinionImpact())
            impactCells.addAll(getCellOfLivingCard(player, (LivingCard) new Minion()));
        return impactCells;
    }

    private static ArrayList<Cell> getCellOfLivingCard(Player player, LivingCard livingCard) {
        ArrayList<Cell> cellOfHero = new ArrayList<>();
        for (CollectionItem collectionItem : player.getAliveCards()) {
            if (collectionItem.getClass().equals(livingCard.getClass())) {
                LivingCard livingCard1 = (LivingCard) collectionItem;
                cellOfHero.add(livingCard1.getCell());
            }
        }
        return cellOfHero;
    }

    // tamoom shod :D


    public static ArrayList<Cell> findMeleeAttackArea(Battle battle, LivingCard attackingCard) {
        return getNeighbors(attackingCard.getCell(), battle);
    }

    public static ArrayList<Cell> findHybridAttackArea(Battle battle, LivingCard attackingCard) {
        return getCellsInArea(attackingCard.getCell(), attackingCard.getRangeOfAttack(), battle);
    }

    public static ArrayList<Cell> findRangedAttackArea(Battle battle, LivingCard attackingCard) {
        ArrayList<Cell> impactedCells = new ArrayList<>();
        Map map = battle.getMap();
        for (int i = 0; i < map.getHeight(); i++)
            for (int j = 0; j < map.getWidth(); j++) {
                int distance = AttackArea.distance(i, j, attackingCard.getCell().getX(), attackingCard.getCell().getY());
                if (isNeighbor(i, j, attackingCard.getCell().getX(), attackingCard.getCell().getY()))
                    continue;
                if (distance > attackingCard.getRangeOfAttack())
                    continue;
                Cell cell = map.getCellByCoordination(i, j);
                impactedCells.add(cell);
            }
        return impactedCells;
    }

    public static ArrayList<Cell> getImpactCellsOfSpecialPower(LivingCard livingCard) {


        return null;
    }

    public static ArrayList<Cell> getImpactCellsOfAttack(LivingCard livingCard) {

        return null;
    }

    public static ArrayList<Cell> getImpactCellsOfCounterAttack(LivingCard livingCard) {

        return null;
    }

    public static ArrayList<Cell> getCellsOfSpecialPower(Minion minion) {
        return null;
    }


    public static ArrayList<Cell> getImpactCellsOfSpell(Spell spell, Cell cell, Battle battle) {
        ArrayList<Cell> impactCells = new ArrayList<>();

        Information information = spell.getInformation();
        information.readInformation();

        if (information.isCellImpact()) {
            if (information.isSquareOfCellsImpact())
                impactCells.addAll(getSquareOfCells(spell, cell, battle, information.getLengthOfSquareOfCellsImpact()));
            if (information.isImpactAColumn())
                getCellsOfColumn(cell, battle, impactCells, information);
        } else {
            if (information.isEnemyImpact())
                getCells(impactCells, information, battle.getPlayerOff());
            if (information.isUsImpact())
                getCells(impactCells, information, battle.getPlayerOn());
        }
        return impactCells;
    }

}

