package Model.CollectionItem;

import Controller.Impact;

public class Information {

    //AttackArea section

    private boolean isEnemyImpact;
    private boolean isUsImpact;
    private boolean isMultipleImpact;
    private boolean isMinionImpact;
    private boolean isHeroImpact;
    private boolean cellImpact;
    private boolean isSquareOfCellsImpact;
    private int lengthOfSquareOfCellsImpact;
    private boolean isImpactAColumn;
    private boolean isKingsGuard;

    public boolean isEnemyImpact() {
        return isEnemyImpact;
    }

    public void setEnemyImpact(boolean enemyImpact) {
        isEnemyImpact = enemyImpact;
    }

    public boolean isMultipleImpact() {
        return isMultipleImpact;
    }

    public void setMultipleImpact(boolean multilpleImpact) {
        isMultipleImpact = multilpleImpact;
    }

    public boolean isMinionImpact() {
        return isMinionImpact;
    }

    public void setMinionImpact(boolean minionImpact) {
        isMinionImpact = minionImpact;
    }

    public boolean isHeroImpact() {
        return isHeroImpact;
    }

    public void setHeroImpact(boolean heroImpact) {
        isHeroImpact = heroImpact;
    }

    public boolean isCellImpact() {
        return cellImpact;
    }

    public void setCellImpact(boolean cellImpact) {
        this.cellImpact = cellImpact;
    }

    public boolean isKingsGuard() {
        return isKingsGuard;
    }

    public void setKingsGuard(boolean kingsGuard) {
        isKingsGuard = kingsGuard;
    }

    public boolean isUsImpact() {
        return isUsImpact;
    }

    public void setUsImpact(boolean usImpact) {
        isUsImpact = usImpact;
    }

    public boolean isSquareOfCellsImpact() {
        return isSquareOfCellsImpact;
    }

    public void setSquareOfCellsImpact(boolean squareOfCellsImpact) {
        isSquareOfCellsImpact = squareOfCellsImpact;
    }

    public int getLengthOfSquareOfCellsImpact() {
        return lengthOfSquareOfCellsImpact;
    }

    public void setLengthOfSquareOfCellsImpact(int lengthOfSquareOfCellsImpact) {
        this.lengthOfSquareOfCellsImpact = lengthOfSquareOfCellsImpact;
    }

    public boolean isImpactAColumn() {
        return isImpactAColumn;
    }

    public void setImpactAColumn(boolean impactAColumn) {
        isImpactAColumn = impactAColumn;
    }

    //Attack section

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

    public static Boolean getCanHolyBuffAdd() {
        return canHolyBuffAdd;
    }

    public static void setCanHolyBuffAdd(Boolean canHolyBuffAdd) {
        Information.canHolyBuffAdd = canHolyBuffAdd;
    }

    public static void setTimeOfHolyBuff(int timeOfHolyBuff) {
        Information.timeOfHolyBuff = timeOfHolyBuff;
    }

    public static int getTimeOfHolyBuff() {
        return timeOfHolyBuff;
    }

    public static void setIsHolyBuffPermanent(Boolean isHolyBuffPermanent) {
        Information.isHolyBuffPermanent = isHolyBuffPermanent;
    }

    public static Boolean getIsHolyBuffPermanent() {
        return isHolyBuffPermanent;
    }

    public static void setCanDisarmBuffAdd(Boolean canDisarmBuffAdd) {
        Information.canDisarmBuffAdd = canDisarmBuffAdd;
    }

    public static Boolean getCanDisarmBuffAdd() {
        return canDisarmBuffAdd;
    }

    public static void setTimeOfDisarmBuff(int timeOfDisarmBuff) {
        Information.timeOfDisarmBuff = timeOfDisarmBuff;
    }

    public static int getTimeOfDisarmBuff() {
        return timeOfDisarmBuff;
    }

    public static void setIsDisarmBuffPermanent(Boolean isDisarmBuffPermanent) {
        Information.isDisarmBuffPermanent = isDisarmBuffPermanent;
    }

    public static Boolean getIsDisarmBuffPermanent() {
        return isDisarmBuffPermanent;
    }

    public static void setCanWeaknessBuffAdd(Boolean canWeaknessBuffAdd) {
        Information.canWeaknessBuffAdd = canWeaknessBuffAdd;
    }

    public static Boolean getCanWeaknessBuffAdd() {
        return canWeaknessBuffAdd;
    }

    public static void setTimeOfWeaknessBuff(int timeOfWeaknessBuff) {
        Information.timeOfWeaknessBuff = timeOfWeaknessBuff;
    }

    public static int getTimeOfWeaknessBuff() {
        return timeOfWeaknessBuff;
    }

    public static void setIsWeaknessBuffPermanent(Boolean isWeaknessBuffPermanent) {
        Information.isWeaknessBuffPermanent = isWeaknessBuffPermanent;
    }

    public static Boolean getIsWeaknessBuffPermanent() {
        return isWeaknessBuffPermanent;
    }

    public static void setCanPosionBuffAdd(Boolean canPosionBuffAdd) {
        Information.canPosionBuffAdd = canPosionBuffAdd;
    }

    public static Boolean getCanPosionBuffAdd() {
        return canPosionBuffAdd;
    }

    public static void setTimeOfPosionBuff(int timeOfPosionBuff) {
        Information.timeOfPosionBuff = timeOfPosionBuff;
    }

    public static int getTimeOfPosionBuff() {
        return timeOfPosionBuff;
    }

    public static void setIsPosionBuffPermanent(Boolean isPosionBuffPermanent) {
        Information.isPosionBuffPermanent = isPosionBuffPermanent;
    }

    public static Boolean getIsPosionBuffPermanent() {
        return isPosionBuffPermanent;
    }

    public static void setCanStunBuffAdd(Boolean canStunBuffAdd) {
        Information.canStunBuffAdd = canStunBuffAdd;
    }

    public static Boolean getCanStunBuffAdd() {
        return canStunBuffAdd;
    }

    public static void setTimeOfStunBuff(int timeOfStunBuff) {
        Information.timeOfStunBuff = timeOfStunBuff;
    }

    public static int getTimeOfStunBuff() {
        return timeOfStunBuff;
    }

    public static void setIsStunBuffPermanenet(Boolean isStunBuffPermanenet) {
        Information.isStunBuffPermanenet = isStunBuffPermanenet;
    }

    public static Boolean getIsStunBuffPermanenet() {
        return isStunBuffPermanenet;
    }

    public static void setCanPowerBuffAdd(Boolean canPowerBuffAdd) {
        Information.canPowerBuffAdd = canPowerBuffAdd;
    }

    public static Boolean getCanPowerBuffAdd() {
        return canPowerBuffAdd;
    }

    public static void setTimeOfPowerBuff(int timeOfPowerBuff) {
        Information.timeOfPowerBuff = timeOfPowerBuff;
    }

    public static int getTimeOfPowerBuff() {
        return timeOfPowerBuff;
    }

    public static void setIsPowerBuffPermanenet(Boolean isPowerBuffPermanenet) {
        Information.isPowerBuffPermanenet = isPowerBuffPermanenet;
    }

    public static Boolean getIsPowerBuffPermanenet() {
        return isPowerBuffPermanenet;
    }

    public static void setCanRemoveBadBuffsOfOurselves(Boolean canRemoveBadBuffsOfOurselves) {
        Information.canRemoveBadBuffsOfOurselves = canRemoveBadBuffsOfOurselves;
    }

    public static Boolean getCanRemoveBadBuffsOfOurselves() {
        return canRemoveBadBuffsOfOurselves;
    }

    public static void setCanRemoveGoodBuffsOfEnemy(Boolean canRemoveGoodBuffsOfEnemy) {
        Information.canRemoveGoodBuffsOfEnemy = canRemoveGoodBuffsOfEnemy;
    }

    public static Boolean getCanRemoveGoodBuffsOfEnemy() {
        return canRemoveGoodBuffsOfEnemy;
    }

    public static void setCanDamageToEnemy(Boolean canDamageToEnemy) {
        Information.canDamageToEnemy = canDamageToEnemy;
    }

    public static Boolean getCanDamageToEnemy() {
        return canDamageToEnemy;
    }

    public static void setDamageToEnemy(int damageToEnemy) {
        Information.damageToEnemy = damageToEnemy;
    }

    public static int getDamageToEnemy() {
        return damageToEnemy;
    }

    public static void setCanAddfieryBuffToCell(Boolean canAddfieryBuffToCell) {
        Information.canAddfieryBuffToCell = canAddfieryBuffToCell;
    }

    public static Boolean getCanAddfieryBuffToCell() {
        return canAddfieryBuffToCell;
    }

    public static void setTimeOfAddfieryBuffToCell(int timeOfAddfieryBuffToCell) {
        Information.timeOfAddfieryBuffToCell = timeOfAddfieryBuffToCell;
    }

    public static int getTimeOfAddfieryBuffToCell() {
        return timeOfAddfieryBuffToCell;
    }

    public static void setCanAddPosionBuffToCell(Boolean canAddPosionBuffToCell) {
        Information.canAddPosionBuffToCell = canAddPosionBuffToCell;
    }

    public static Boolean getCanAddPosionBuffToCell() {
        return canAddPosionBuffToCell;
    }

    public static void setTimeOfAddPosionBuffToCell(int timeOfAddPosionBuffToCell) {
        Information.timeOfAddPosionBuffToCell = timeOfAddPosionBuffToCell;
    }

    public static int getTimeOfAddPosionBuffToCell() {
        return timeOfAddPosionBuffToCell;
    }

    public static void setCanKillMinionOfEnemy(Boolean canKillMinionOfEnemy) {
        Information.canKillMinionOfEnemy = canKillMinionOfEnemy;
    }

    public static Boolean getCanKillMinionOfEnemy() {
        return canKillMinionOfEnemy;
    }

    public static void setCanKillOurMinionAndHealHero(Boolean canKillOurMinionAndHealHero) {
        Information.canKillOurMinionAndHealHero = canKillOurMinionAndHealHero;
    }

    public static Boolean getCanKillOurMinionAndHealHero() {
        return canKillOurMinionAndHealHero;
    }

    public static void setCanDoRangedAttack(Boolean canDoRangedAttack) {
        Information.canDoRangedAttack = canDoRangedAttack;
    }

    public static Boolean getCanDoRangedAttack() {
        return canDoRangedAttack;
    }

    public static void setCanDoHybridAttack(Boolean canDoHybridAttack) {
        Information.canDoHybridAttack = canDoHybridAttack;
    }

    public static Boolean getCanDoHybridAttack() {
        return canDoHybridAttack;
    }

    public static void setCanDoMelleAttack(Boolean canDoMelleAttack) {
        Information.canDoMelleAttack = canDoMelleAttack;
    }

    public static Boolean getCanDoMelleAttack() {
        return canDoMelleAttack;
    }

    public static void setIsGhooleBozorg(Boolean isGhooleBozorg) {
        Information.isGhooleBozorg = isGhooleBozorg;
    }

    public static Boolean getIsGhooleBozorg() {
        return isGhooleBozorg;
    }

    public static void setIsShireDarrande(Boolean isShireDarrande) {
        Information.isShireDarrande = isShireDarrande;
    }

    public static Boolean getIsShireDarrande() {
        return isShireDarrande;
    }

    public static void setIsZahhak(Boolean isZahhak) {
        Information.isZahhak = isZahhak;
    }

    public static Boolean getIsZahhak() {
        return isZahhak;
    }

    public static void setCanIncreaseRangeOfAttack(Boolean canIncreaseRangeOfAttack) {
        Information.canIncreaseRangeOfAttack = canIncreaseRangeOfAttack;
    }

    public static Boolean getCanIncreaseRangeOfAttack() {
        return canIncreaseRangeOfAttack;
    }

    public static void setCanDoubleHp(Boolean canDoubleHp) {
        Information.canDoubleHp = canDoubleHp;
    }

    public static Boolean getCanDoubleHp() {
        return canDoubleHp;
    }

    public static void setCanIncreaseMana(Boolean canIncreaseMana) {
        Information.canIncreaseMana = canIncreaseMana;
    }

    public static Boolean getCanIncreaseMana() {
        return canIncreaseMana;
    }

    //methods

    public void readInformation(){
        return;
    }

}
