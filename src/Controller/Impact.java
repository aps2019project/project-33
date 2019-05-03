package Controller;

import Model.CollectionItem.*;
import Model.Enviroment.Cell;
import Model.Player;

import javax.swing.text.StyledEditorKit;

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
    private static Boolean canIncreaseRangeOfAttack;
    private static Boolean canDoubleHp;
    private static Boolean canIncreaseMana;

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

    public static Boolean getCanHolyBuffAdd() {
        return canHolyBuffAdd;
    }

    public static void setCanHolyBuffAdd(Boolean canHolyBuffAdd) {
        Impact.canHolyBuffAdd = canHolyBuffAdd;
    }

    public static void setTimeOfHolyBuff(int timeOfHolyBuff) {
        Impact.timeOfHolyBuff = timeOfHolyBuff;
    }

    public static int getTimeOfHolyBuff() {
        return timeOfHolyBuff;
    }

    public static void setIsHolyBuffPermanent(Boolean isHolyBuffPermanent) {
        Impact.isHolyBuffPermanent = isHolyBuffPermanent;
    }

    public static Boolean getIsHolyBuffPermanent() {
        return isHolyBuffPermanent;
    }

    public static void setCanDisarmBuffAdd(Boolean canDisarmBuffAdd) {
        Impact.canDisarmBuffAdd = canDisarmBuffAdd;
    }

    public static Boolean getCanDisarmBuffAdd() {
        return canDisarmBuffAdd;
    }

    public static void setTimeOfDisarmBuff(int timeOfDisarmBuff) {
        Impact.timeOfDisarmBuff = timeOfDisarmBuff;
    }

    public static int getTimeOfDisarmBuff() {
        return timeOfDisarmBuff;
    }

    public static void setIsDisarmBuffPermanent(Boolean isDisarmBuffPermanent) {
        Impact.isDisarmBuffPermanent = isDisarmBuffPermanent;
    }

    public static Boolean getIsDisarmBuffPermanent() {
        return isDisarmBuffPermanent;
    }

    public static void setCanWeaknessBuffAdd(Boolean canWeaknessBuffAdd) {
        Impact.canWeaknessBuffAdd = canWeaknessBuffAdd;
    }

    public static Boolean getCanWeaknessBuffAdd() {
        return canWeaknessBuffAdd;
    }

    public static void setTimeOfWeaknessBuff(int timeOfWeaknessBuff) {
        Impact.timeOfWeaknessBuff = timeOfWeaknessBuff;
    }

    public static int getTimeOfWeaknessBuff() {
        return timeOfWeaknessBuff;
    }

    public static void setIsWeaknessBuffPermanent(Boolean isWeaknessBuffPermanent) {
        Impact.isWeaknessBuffPermanent = isWeaknessBuffPermanent;
    }

    public static Boolean getIsWeaknessBuffPermanent() {
        return isWeaknessBuffPermanent;
    }

    public static void setCanPosionBuffAdd(Boolean canPosionBuffAdd) {
        Impact.canPosionBuffAdd = canPosionBuffAdd;
    }

    public static Boolean getCanPosionBuffAdd() {
        return canPosionBuffAdd;
    }

    public static void setTimeOfPosionBuff(int timeOfPosionBuff) {
        Impact.timeOfPosionBuff = timeOfPosionBuff;
    }

    public static int getTimeOfPosionBuff() {
        return timeOfPosionBuff;
    }

    public static void setIsPosionBuffPermanent(Boolean isPosionBuffPermanent) {
        Impact.isPosionBuffPermanent = isPosionBuffPermanent;
    }

    public static Boolean getIsPosionBuffPermanent() {
        return isPosionBuffPermanent;
    }

    public static void setCanStunBuffAdd(Boolean canStunBuffAdd) {
        Impact.canStunBuffAdd = canStunBuffAdd;
    }

    public static Boolean getCanStunBuffAdd() {
        return canStunBuffAdd;
    }

    public static void setTimeOfStunBuff(int timeOfStunBuff) {
        Impact.timeOfStunBuff = timeOfStunBuff;
    }

    public static int getTimeOfStunBuff() {
        return timeOfStunBuff;
    }

    public static void setIsStunBuffPermanenet(Boolean isStunBuffPermanenet) {
        Impact.isStunBuffPermanenet = isStunBuffPermanenet;
    }

    public static Boolean getIsStunBuffPermanenet() {
        return isStunBuffPermanenet;
    }

    public static void setCanPowerBuffAdd(Boolean canPowerBuffAdd) {
        Impact.canPowerBuffAdd = canPowerBuffAdd;
    }

    public static Boolean getCanPowerBuffAdd() {
        return canPowerBuffAdd;
    }

    public static void setTimeOfPowerBuff(int timeOfPowerBuff) {
        Impact.timeOfPowerBuff = timeOfPowerBuff;
    }

    public static int getTimeOfPowerBuff() {
        return timeOfPowerBuff;
    }

    public static void setIsPowerBuffPermanenet(Boolean isPowerBuffPermanenet) {
        Impact.isPowerBuffPermanenet = isPowerBuffPermanenet;
    }

    public static Boolean getIsPowerBuffPermanenet() {
        return isPowerBuffPermanenet;
    }

    public static void setCanRemoveBadBuffsOfOurselves(Boolean canRemoveBadBuffsOfOurselves) {
        Impact.canRemoveBadBuffsOfOurselves = canRemoveBadBuffsOfOurselves;
    }

    public static Boolean getCanRemoveBadBuffsOfOurselves() {
        return canRemoveBadBuffsOfOurselves;
    }

    public static void setCanRemoveGoodBuffsOfEnemy(Boolean canRemoveGoodBuffsOfEnemy) {
        Impact.canRemoveGoodBuffsOfEnemy = canRemoveGoodBuffsOfEnemy;
    }

    public static Boolean getCanRemoveGoodBuffsOfEnemy() {
        return canRemoveGoodBuffsOfEnemy;
    }

    public static void setCanDamageToEnemy(Boolean canDamageToEnemy) {
        Impact.canDamageToEnemy = canDamageToEnemy;
    }

    public static Boolean getCanDamageToEnemy() {
        return canDamageToEnemy;
    }

    public static void setDamageToEnemy(int damageToEnemy) {
        Impact.damageToEnemy = damageToEnemy;
    }

    public static int getDamageToEnemy() {
        return damageToEnemy;
    }

    public static void setCanAddfieryBuffToCell(Boolean canAddfieryBuffToCell) {
        Impact.canAddfieryBuffToCell = canAddfieryBuffToCell;
    }

    public static Boolean getCanAddfieryBuffToCell() {
        return canAddfieryBuffToCell;
    }

    public static void setTimeOfAddfieryBuffToCell(int timeOfAddfieryBuffToCell) {
        Impact.timeOfAddfieryBuffToCell = timeOfAddfieryBuffToCell;
    }

    public static int getTimeOfAddfieryBuffToCell() {
        return timeOfAddfieryBuffToCell;
    }

    public static void setCanAddPosionBuffToCell(Boolean canAddPosionBuffToCell) {
        Impact.canAddPosionBuffToCell = canAddPosionBuffToCell;
    }

    public static Boolean getCanAddPosionBuffToCell() {
        return canAddPosionBuffToCell;
    }

    public static void setTimeOfAddPosionBuffToCell(int timeOfAddPosionBuffToCell) {
        Impact.timeOfAddPosionBuffToCell = timeOfAddPosionBuffToCell;
    }

    public static int getTimeOfAddPosionBuffToCell() {
        return timeOfAddPosionBuffToCell;
    }

    public static void setCanKillMinionOfEnemy(Boolean canKillMinionOfEnemy) {
        Impact.canKillMinionOfEnemy = canKillMinionOfEnemy;
    }

    public static Boolean getCanKillMinionOfEnemy() {
        return canKillMinionOfEnemy;
    }

    public static void setCanKillOurMinionAndHealHero(Boolean canKillOurMinionAndHealHero) {
        Impact.canKillOurMinionAndHealHero = canKillOurMinionAndHealHero;
    }

    public static Boolean getCanKillOurMinionAndHealHero() {
        return canKillOurMinionAndHealHero;
    }

    public static void setCanDoRangedAttack(Boolean canDoRangedAttack) {
        Impact.canDoRangedAttack = canDoRangedAttack;
    }

    public static Boolean getCanDoRangedAttack() {
        return canDoRangedAttack;
    }

    public static void setCanDoHybridAttack(Boolean canDoHybridAttack) {
        Impact.canDoHybridAttack = canDoHybridAttack;
    }

    public static Boolean getCanDoHybridAttack() {
        return canDoHybridAttack;
    }

    public static void setCanDoMelleAttack(Boolean canDoMelleAttack) {
        Impact.canDoMelleAttack = canDoMelleAttack;
    }

    public static Boolean getCanDoMelleAttack() {
        return canDoMelleAttack;
    }

    public static void setIsGhooleBozorg(Boolean isGhooleBozorg) {
        Impact.isGhooleBozorg = isGhooleBozorg;
    }

    public static Boolean getIsGhooleBozorg() {
        return isGhooleBozorg;
    }

    public static void setIsShireDarrande(Boolean isShireDarrande) {
        Impact.isShireDarrande = isShireDarrande;
    }

    public static Boolean getIsShireDarrande() {
        return isShireDarrande;
    }

    public static void setIsZahhak(Boolean isZahhak) {
        Impact.isZahhak = isZahhak;
    }

    public static Boolean getIsZahhak() {
        return isZahhak;
    }

    public static void setCanIncreaseRangeOfAttack(Boolean canIncreaseRangeOfAttack) {
        Impact.canIncreaseRangeOfAttack = canIncreaseRangeOfAttack;
    }

    public static Boolean getCanIncreaseRangeOfAttack() {
        return canIncreaseRangeOfAttack;
    }

    public static void setCanDoubleHp(Boolean canDoubleHp) {
        Impact.canDoubleHp = canDoubleHp;
    }

    public static Boolean getCanDoubleHp() {
        return canDoubleHp;
    }

    public static void setCanIncreaseMana(Boolean canIncreaseMana) {
        Impact.canIncreaseMana = canIncreaseMana;
    }

    public static Boolean getCanIncreaseMana() {
        return canIncreaseMana;
    }
}
