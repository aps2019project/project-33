package Controller;

import Model.CollectionItem.*;
import Model.Enviroment.Cell;

public class Impact {

    private static Boolean canHolyBuffAdd;
    private static int timeOfHolyBuff;
    private static Boolean isHolyBuffPermanent;
    private static Boolean canDisarmBuffAdd;
    private static int timeOfDisarmBuff;
    private static Boolean isDisarmBuffPermanent;
    private static Boolean canWeaknessBuffAdd;
    private static int timeOfWeaknessBuff;
    private static Boolean isWeaknessBuffPermanent;
    private static Boolean canPosionBuffAdd;
    private static int timeOfPosionBuff;
    private static Boolean isPosionBuffPermanent;
    private static Boolean canStunBuffAdd;
    private static int timeOfStunBuff;
    private static Boolean isStunBuffPermanenet;
    private static Boolean canPowerBuffAdd;
    private static int timeOfPowerBuff;
    private static Boolean isPowerBuffPermanenet;
    private static Boolean canRemoveBadBuffsOfOurselves;
    private static Boolean canRemoveGoodBuffsOfEnemy;
    private static Boolean canDamageToEnemy;
    private static int damageToEnemy;
    private static Boolean canAddfieryBuffToCell;
    private static int timeOfAddfieryBuffToCell;
    private static Boolean canAddPosionBuffToCell;
    private static int timeOfAddPosionBuffToCell;
    private static Boolean canKillMinionOfEnemy;
    private static Boolean canKillOurMinionAndHealHero;
    private static Boolean canDoRangedAttack;
    private static Boolean canDoHybridAttack;
    private static Boolean canDoMelleAttack;
    private static Boolean isGhooleBozorg;
    private static Boolean isShireDarrande;
    private static Boolean isZahhak;
    private static Boolean canIncreasRangeOfAttack;
    private static Boolean canDoubleHp;
    private static Boolean canIncreaseMana;

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
}
