//tavakkol be khoda

package Controller;

import Model.Buffs.*;
import Model.CollectionItem.*;
import Model.Enviroment.Cell;

import Model.Enviroment.Map1;
import Model.Player;
//import javafx.geometry.Pos;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Impact {

    public static void addDisarmToCard(int remainTime, boolean isPermanent, boolean isPassive, LivingCard livingCard){
        DisarmBuff disarmBuff = new DisarmBuff(remainTime, isPermanent, isPassive);
        livingCard.addNewBuff(disarmBuff);
    }

    public static void addHolyToCard(int remainTime, boolean isPermanent, boolean isPassive, int shieldPower, LivingCard livingCard){
        HolyBuff holyBuff = new HolyBuff(remainTime, isPermanent, isPassive, shieldPower);
        livingCard.addNewBuff(holyBuff);
    }

    public static void addStunToCard(int remainTime, boolean isPermanent, boolean isPassive, LivingCard livingCard){
        StunBuff stunBuff = new StunBuff(remainTime, isPermanent, isPassive);
        livingCard.addNewBuff(stunBuff);
    }

    public static void addPoisonToCard(int remainTime, boolean isPermanent, boolean isPassive, int decreaseHP,
                                       LivingCard livingCard){
        PoisonBuff PoisonBuff = new PoisonBuff(remainTime, isPermanent, isPassive, decreaseHP);
        livingCard.addNewBuff(PoisonBuff);
    }

    public static void addPowerBuffToCard(int remainTime, boolean isPermanent, boolean isPassive, LivingCard livingCard){
        PowerBuff powerBuff = new PowerBuff(remainTime, isPermanent, isPassive);
        livingCard.addNewBuff(powerBuff);
    }

    public static void removeBadBuffsOfLivingCard(LivingCard livingCard){
        ArrayList<Buff> effects = livingCard.getEffects();
        int numberOfBuffs = effects.size();

        for(int i = numberOfBuffs - 1; i > -1; i --){
            Buff buff = effects.get(i);
            if(buff.isPassive()){
                buff.setIsActive(false);
                continue;
            }
            if(buff instanceof WeaknessBuff) effects.remove(i);
            if(buff instanceof PoisonBuff) effects.remove(i);
            if(buff instanceof StunBuff) effects.remove(i);
            if(buff instanceof DisarmBuff) effects.remove(i);
        }

    }

    public static void activeBuffs(Battle battle){
        for(LivingCard livingCard : battle.getPlayerOn().getAliveCards()){
            for(Buff buff : livingCard.getEffects()){
                buff.setIsActive(true);
            }
        }
        for(LivingCard livingCard : battle.getPlayerOff().getAliveCards()){
            for(Buff buff : livingCard.getEffects()){
                buff.setIsActive(true);
            }
        }
    }

    public static void removeGoodBuffsOfLivingCard(LivingCard livingCard){
        ArrayList<Buff> effects = livingCard.getEffects();
        int numberOfBuffs = effects.size();

        for(int i = numberOfBuffs; i > -1; i --){
            Buff buff = effects.get(i);
            if(buff instanceof HolyBuff) effects.remove(i);
            if(buff instanceof PowerBuff) effects.remove(i);
        }
    }

    public static void damageToEnemy(LivingCard opponentLivingCard, int damage){
        opponentLivingCard.handleAttack(damage);
        return;
    }

    public static void addFieryBuffToCell(Cell cell){
        //TODO
        //deghat konim aslan fiery buff nadarim
    }

    //deghat konim nabayad chize daemi be cell ha ezafe she
    //passive boodan ham deghat  konim ke nadare
    public static void addPoisonBuffToCell(int remainTime, int decreaseHP,  Cell cell){
        PoisonBuff poisonBuff = new PoisonBuff(remainTime, false, false, decreaseHP);
        cell.addEffect(poisonBuff);
    }

    public static void killMinionOfEnemy(Minion minion){
        minion.kill();
    }

    public static void killOurMinionAndHealHero(Hero hero, Minion minion){
        hero.setHP(hero.getHP() + minion.getHP());
        minion.kill();
    }

 /*   public static void anyKindOfAttack(LivingCard ourLivingCard, LivingCard enemyLivingCard){
        enemyLivingCard.handleAttack(ourLivingCard.getDecreaseHPByAttack());
        Impact.counterAttack(enemyLivingCard, ourLivingCard);
    }
    */

    public static void specialAttackOfGhooleBozorg(Minion bigGiant, int bigGiantDamage){
        int numberOfImpactedCells = 8;
        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, 1, 1, 1, 0, -1, -1, -1};
        for(int i = 0; i < numberOfImpactedCells; i ++){
            int newX = bigGiant.getCell().getX() + dx[i], newY = bigGiant.getCell().getY() + dy[i];
            Cell cell = bigGiant.getBattle().getMap().getCellByCoordination(newX, newY);
            if(cell == null) continue;
            LivingCard livingCard = cell.getLivingCard();
            if(livingCard == null) continue;
            if(!(livingCard instanceof Minion)) continue;
            livingCard.handleAttack(bigGiantDamage);
        }
    }

    //wa
    public static void spellOfZahhak(Hero zahhak){
        int numberOfImpactedCells = 8;
        ArrayList<Cell> emptyCells = new ArrayList<>();
        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, 1, 1, 1, 0, -1, -1, -1};
        for(int i = 0; i < numberOfImpactedCells; i ++){
            int newX = zahhak.getCell().getX() + dx[i], newY = zahhak.getCell().getY() + dy[i];
            Cell cell = zahhak.getBattle().getMap().getCellByCoordination(newX, newY);
            if(cell == null) continue;
            if(cell.getLivingCard() != null) continue;
            emptyCells.add(cell);
        }
        Collections.shuffle(emptyCells);
        int numberOfNeededCells = Integer.min(2, emptyCells.size());
        for(int i = 0; i < numberOfNeededCells; i ++){
            zahhak.getBattle().addMareBozorg();
        }
    }

    //im not sure
    public static void increaseRangeOfAttack(LivingCard livingCard, int amount){
        livingCard.setRangeOfAttack(livingCard.getRangeOfAttack() + amount);
    }

    public static void doubleHP(Hero hero){
        hero.setHP(hero.getHP() * 2);
    }

    //daghighan chio ezafe mikone ?
    public static void increaseMana(Player player){

    }

    public static void attackForce(LivingCard ourCard, LivingCard enemyCard){}

    public static boolean isInRange(ArrayList<Cell> attackCells, LivingCard defender){
        boolean isCardInRange = false;
        for(Cell cell : attackCells){
            if(cell.getX() == defender.getPositionRow() && cell.getY() == defender.getPositionColumn())
                isCardInRange = true;
        }
        return isCardInRange;
    }

    public static boolean checkAlive(Battle battle, LivingCard checkedLivingCard){
        if(checkedLivingCard.getHP() <= 0){
            battle.removeAliveCard(checkedLivingCard);
            return false;
        }
        return true;
    }

    public static void counterAttack(Battle battle, LivingCard livingCard, LivingCard opponentCard) {
        ArrayList<Cell> attackCells = Impact.getAttackCells(battle, livingCard);
        boolean isInRange = Impact.isInRange(attackCells, opponentCard);
        if(!isInRange || !livingCard.isCanCounterAttack()){
            return;
        }
        opponentCard.setHP(opponentCard.getHP() - livingCard.getDecreaseHPByAttack());
        checkAlive(battle, opponentCard);
    }

//counter attackam bayad haminja seda she
    //mana nemikhad in ?
    public static void attack(Battle battle, LivingCard attacker, LivingCard defender){
        ArrayList<Cell> attackCells = Impact.getAttackCells(battle, attacker);
        boolean isInRange = Impact.isInRange(attackCells, defender);
        if(!isInRange){
            System.out.println("opponent minion is unavailable for attack");
            return;
        }
        if(!attacker.isCanAttack()){
            System.out.println("attacker cant attack");
            return;
        }
        //changeHP e esme ap ?
        //too getter ba taghirat jam miazanim
        defender.setHP(defender.getHP() - attacker.getDecreaseHPByAttack());
        if(checkAlive(battle, defender))
            Impact.counterAttack(battle, defender, attacker);

    }
// mahdude hamle o ina check she
    public static void comboAttack(LivingCard opponentLivingCard, ArrayList<LivingCard> attackers){

    }

    public static ArrayList<Cell> getAttackCells(Battle battle, LivingCard livingCard){
        ArrayList<Cell> attackCells = new ArrayList<>();
        if(livingCard.getCounterAttackType().equals("melee")){
            attackCells = AttackArea.findMeleeAttackArea(battle, livingCard);
        }
        if(livingCard.getCounterAttackType().equals("ranged")){
            attackCells = AttackArea.findRangedAttackArea(battle, livingCard);
        }
        if(livingCard.getCounterAttackType().equals("hybrid")){
            attackCells = AttackArea.findHybridAttackArea(battle, livingCard);
        }
        return attackCells;
    }


//manash berese - baraye hero e in minion chi ?
    public static void specialPower(Hero hero, int x, int y) {

    }

    public static void impactItem(Item item, Cell cell) {
    }
//simorgh chie
    public static void impactSpellOfHero(Battle battle, Hero hero, Cell cell) {
        if(hero.getName().equals("diveSefid")){
            Impact.addPowerBuffToCard(100, true, false, hero);
        }
        if(hero.getName().equals("ezhdaha")){
            LivingCard livingCard = cell.getLivingCard();
            if(livingCard == null){
                System.out.println("there isnt living card here");
                return;
            }
            //daemie ?
            Impact.addDisarmToCard(10, true, false, livingCard);
        }
        if(hero.getName().equals("rakhsh")){
            LivingCard livingCard = cell.getLivingCard();
            if(livingCard == null){
                System.out.println("there isnt living card here");
                return;
            }
            Impact.addStunToCard(1, false, false, livingCard);
        }
        if(hero.getName().equals("zahhak")){
            Impact.spellOfZahhak(hero);
        }
        if(hero.getName().equals("kaveh")){
            LivingCard livingCard = cell.getLivingCard();
            if(livingCard == null){
                System.out.println("there isnt living card here");
                return;
            }
            Impact.addHolyToCard(3, false, false, 1, livingCard);
        }
        if(hero.getName().equals("arash")){
            Cell heroCell = battle.getMap().getCellByCoordination(hero.getPositionRow(), hero.getPositionColumn());
            ArrayList<Cell> attackCells = AttackArea.getCellsOfRow(heroCell, battle);
            for(Cell attackCell : attackCells){
                LivingCard livingCard = attackCell.getLivingCard();
                if(livingCard == null)
                    continue;
                //handle attacko ok kon -> checkAlive o changeHP
                livingCard.handleAttack(4);
            }
        }
        if(hero.getName().equals("afsaneh")){
            LivingCard livingCard = cell.getLivingCard();
            if(livingCard == null){
                System.out.println("there isnt living card here");
                return;
            }
            for(LivingCard playerOffLivingCard : battle.getPlayerOff().getAliveCards()){
                if(playerOffLivingCard.getID().equals(livingCard.getID())){
                    Impact.removeGoodBuffsOfLivingCard(livingCard);
                }
            }
        }
        if(hero.getName().equals("esfandiar")){
            Impact.addHolyToCard(10, true, true, 1, hero);
        }
    }

    public static void impactSpell(Spell spell, Cell cell, Battle battle) {
        LivingCard livingCard = cell.getLivingCard();
        ArrayList<Cell> impactCells = AttackArea.getImpactCellsOfSpell(spell, cell, battle);
        if(!spell.getInformation().isMultipleImpact()){
            if(livingCard == null){
                System.out.println("there isnt living card");
                return;
            }
            impactCells = new ArrayList<>();
            impactCells.add(cell);

        }
        if(spell.getInformation().isCanDisarmBuffAdd()){
            for(Cell impactCell : impactCells){
                LivingCard cellLivingCard = impactCell.getLivingCard();
                if(cellLivingCard == null)
                    continue;
                //isEnemyImpact : roo doshmane asaresh
                if(spell.getInformation().isEnemyImpact()){
                    for(LivingCard aliveCard : battle.getPlayerOff().getAliveCards()){
                        if(aliveCard.getID().equals(cellLivingCard.getID())){
                            int disarmTime = spell.getInformation().getTimeOfDisarmBuff();
                            boolean isPermanent = spell.getInformation().isDisarmBuffPermanent();
                            Impact.addDisarmToCard(disarmTime, isPermanent,false, cellLivingCard);
                        }
                    }
                }
                if(spell.getInformation().isUsImpact()){
                    for(LivingCard aliveCard : battle.getPlayerOn().getAliveCards()){
                        if(aliveCard.getID().equals(cellLivingCard.getID())){
                            int disarmTime = spell.getInformation().getTimeOfDisarmBuff();
                            boolean isPermanent = spell.getInformation().isDisarmBuffPermanent();
                            Impact.addDisarmToCard(disarmTime, isPermanent, false, cellLivingCard);
                        }
                    }
                }
            }
        }
        if(spell.getInformation().isCanRemoveGoodBuffsOfEnemy()){
            for(Cell impactCell : impactCells){
                LivingCard cellLivingCard = impactCell.getLivingCard();
                if(cellLivingCard == null)
                    continue;
                boolean isLivingCardOurs = false;
                for(LivingCard aliveCard : battle.getPlayerOn().getAliveCards()){
                    if(aliveCard.getID().equals(cellLivingCard.getID())){
                        isLivingCardOurs = true;
                    }
                }
                if(isLivingCardOurs){
                    Impact.removeBadBuffsOfLivingCard(cellLivingCard);
                }
                else{
                    Impact.removeGoodBuffsOfLivingCard(cellLivingCard);
                }
            }
        }
        if(spell.getInformation().isCanDamageToEnemy()){
            for(Cell impactCell : impactCells){
                LivingCard cellLivingCard = impactCell.getLivingCard();
                if(cellLivingCard == null)
                    continue;
                for(LivingCard aliveCard : battle.getPlayerOff().getAliveCards()){
                    if(aliveCard.getID().equals(cellLivingCard.getID())){
                        aliveCard.handleAttack(spell.getInformation().getDamageToEnemy());
                        break;
                    }
                }
            }
        }

    }

    public static void main(Battle battle, Card ourCard){}

}
