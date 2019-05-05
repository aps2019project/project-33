//tavakkol be khoda

package Controller;

import Model.Buffs.*;
import Model.CollectionItem.*;
import Model.Enviroment.Cell;
import Model.Player;
import javafx.geometry.Pos;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;

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

    public static void addPosionToCard(int remainTime, boolean isPermanent, boolean isPassive, int decreaseHP,
                                       LivingCard livingCard){
        PosionBuff posionBuff = new PosionBuff(remainTime, isPermanent, isPassive, decreaseHP);
        livingCard.addNewBuff(posionBuff);
    }

    public static void addPowerBuffToCard(int remainTime, boolean isPermanent, boolean isPassive, LivingCard livingCard){
        PowerBuff powerBuff = new PowerBuff(remainTime, isPermanent, isPassive);
        livingCard.addNewBuff(powerBuff);
    }

    public static void removeBadBuffsOfLivingCard(LivingCard livingCard){
        ArrayList<Buff> effects = livingCard.getEffects();
        int numberOfBuffs = livingCard.getEffects().size();

        for(int i = numberOfBuffs - 1; i > -1; i --){
            Buff buff = effects.get(i);
            if(buff instanceof WeaknessBuff) effects.remove(i);
            if(buff instanceof )

        }
    }

    
    public static void removeGoodBuffsOfLivingCard(LivingCard livingCard){}

    public static void damageToEnemy(Card ourCard, Card enemyCard){}

    public static void addFieryBuffToCell(Cell cell){}

    public static void addPosionBuffToCell(Cell cell){}

    public static void killMinionOfEnemy(Card card){}

    public static void killOurMinionAndHealHero(Card card){}

    public static void rangeAttack(Card ourCard, Card enemyCard){}

    public static void hybridAttack(Card ourCard, Card enemyCard){}

    public static void melleAttack(Card ourCard, Card enemyCard){}

    public static void specialAttackOfGhooleBozorg(Card card){}

    public static void spellOfZahhak(Card card){}

    public static void increaseRangeOfAttack(Card card){}

    public static void doubleHP(Card card){}

    public static void increaseMana(Player player){}

    public static void attackForce(LivingCard ourCard, LivingCard enemyCard){}

    public static void attack(LivingCard attacker, LivingCard defender){}

    public static void counterAttack(LivingCard livingCard, LivingCard opponentCard) {

    }

    public static void specialPower(Minion minion, LivingCard opponentCard) {
    }

    public static void impactItem(Item item, String livingCardID) {
    }

    public static void impactSpellOfHero(String opponentID) {
    }

    public static void impactSpell(Spell spell, Cell cell) {
    }

    public static void main(Battle battle, Card ourCard){}

}
