//tavakkol be khoda

package Controller;

import Model.CollectionItem.*;
import Model.Enviroment.Cell;
import Model.Player;

import javax.swing.text.StyledEditorKit;

public class Impact {

    public static void addDisarmToCard(int time, Boolean isPermanent, Card card){}

    public static void addHolyToCard(int time, Boolean iPermanent, Card card){}

    public static void addStunToCard(int time, Boolean isPermanent, Card card){}

    public static void addPosionToCard(int time, Boolean isPermanent, Card card){}

    public static void addPowerBuffToCard(int time, Boolean isPermanent, Card card){}

    public static void removeBadBuffsOfOurselves(Card card){}

    public static void removeGoodBuffsOfEnemy(Card card){}

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
