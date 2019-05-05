package Model.CollectionItem;

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

    private static boolean canHolyBuffAdd;
    private static int timeOfHolyBuff;
    private static boolean isHolyBuffPermanent;

    private static boolean canDisarmBuffAdd;
    private static int timeOfDisarmBuff;
    private static boolean isDisarmBuffPermanent;

    private static boolean canWeaknessBuffAdd;
    private static int timeOfWeaknessBuff;
    private static boolean isWeaknessBuffPermanent;

    private static boolean canPoisonBuffAdd;
    private static int timeOfPoisonBuff;
    private static boolean isPoisonBuffPermanent;

    private static boolean canStunBuffAdd;
    private static int timeOfStunBuff;
    private static boolean isStunBuffPermanent;

    private static boolean canPowerBuffAdd;
    private static int timeOfPowerBuff;
    private static boolean isPowerBuffPermanent;

    private static boolean canRemoveBadBuffsOfOurselves;
    private static boolean canRemoveGoodBuffsOfEnemy;

    private static boolean canDamageToEnemy;
    private static int damageToEnemy;

    private static boolean canAddFieryBuffToCell;
    private static int timeOfAddFieryBuffToCell;

    
    private static boolean canAddPoisonBuffToCell;
    private static int timeOfAddPoisonBuffToCell;
    private static int decreaseHpOfPoisonBuffOfCell;

    private static boolean canKillMinionOfEnemy;
    private static boolean canKillOurMinionAndHealHero;
    private static boolean canDoRangedAttack;
    private static boolean canDoHybridAttack;
    private static boolean canDoMeleeAttack;
    private static boolean isGhooleBozorg;
    private int ghooleBozorgDamage;
    private static boolean isShireDarrande;
    private static boolean isZahhak;
    private static boolean canIncreaseRangeOfAttack;
    private static boolean canDoubleHp;
    private static boolean canIncreaseMana;

    public static boolean getCanHolyBuffAdd() {
        return canHolyBuffAdd;
    }

    public static void setCanHolyBuffAdd(boolean canHolyBuffAdd) {
        Information.canHolyBuffAdd = canHolyBuffAdd;
    }

    public static void setTimeOfHolyBuff(int timeOfHolyBuff) {
        Information.timeOfHolyBuff = timeOfHolyBuff;
    }

    public static int getTimeOfHolyBuff() {
        return timeOfHolyBuff;
    }

    public static void setIsHolyBuffPermanent(boolean isHolyBuffPermanent) {
        Information.isHolyBuffPermanent = isHolyBuffPermanent;
    }

    public static boolean getIsHolyBuffPermanent() {
        return isHolyBuffPermanent;
    }

    public static void setCanDisarmBuffAdd(boolean canDisarmBuffAdd) {
        Information.canDisarmBuffAdd = canDisarmBuffAdd;
    }

    public static boolean getCanDisarmBuffAdd() {
        return canDisarmBuffAdd;
    }

    public static void setTimeOfDisarmBuff(int timeOfDisarmBuff) {
        Information.timeOfDisarmBuff = timeOfDisarmBuff;
    }

    public static int getTimeOfDisarmBuff() {
        return timeOfDisarmBuff;
    }

    public static void setIsDisarmBuffPermanent(boolean isDisarmBuffPermanent) {
        Information.isDisarmBuffPermanent = isDisarmBuffPermanent;
    }

    public static boolean getIsDisarmBuffPermanent() {
        return isDisarmBuffPermanent;
    }

    public static void setCanWeaknessBuffAdd(boolean canWeaknessBuffAdd) {
        Information.canWeaknessBuffAdd = canWeaknessBuffAdd;
    }

    public static boolean getCanWeaknessBuffAdd() {
        return canWeaknessBuffAdd;
    }

    public static void setTimeOfWeaknessBuff(int timeOfWeaknessBuff) {
        Information.timeOfWeaknessBuff = timeOfWeaknessBuff;
    }

    public static int getTimeOfWeaknessBuff() {
        return timeOfWeaknessBuff;
    }

    public static void setIsWeaknessBuffPermanent(boolean isWeaknessBuffPermanent) {
        Information.isWeaknessBuffPermanent = isWeaknessBuffPermanent;
    }

    public static boolean getIsWeaknessBuffPermanent() {
        return isWeaknessBuffPermanent;
    }

    public static void setCanPoisonBuffAdd(boolean canPoisonBuffAdd) {
        Information.canPoisonBuffAdd = canPoisonBuffAdd;
    }

    public static boolean getCanPoisonBuffAdd() {
        return canPoisonBuffAdd;
    }

    public static void setTimeOfPoisonBuff(int timeOfPoisonBuff) {
        Information.timeOfPoisonBuff = timeOfPoisonBuff;
    }

    public static int getTimeOfPoisonBuff() {
        return timeOfPoisonBuff;
    }

    public static void setIsPoisonBuffPermanent(boolean isPoisonBuffPermanent) {
        Information.isPoisonBuffPermanent = isPoisonBuffPermanent;
    }

    public static boolean getIsPoisonBuffPermanent() {
        return isPoisonBuffPermanent;
    }

    public static void setCanStunBuffAdd(boolean canStunBuffAdd) {
        Information.canStunBuffAdd = canStunBuffAdd;
    }

    public static boolean getCanStunBuffAdd() {
        return canStunBuffAdd;
    }

    public static void setTimeOfStunBuff(int timeOfStunBuff) {
        Information.timeOfStunBuff = timeOfStunBuff;
    }

    public static int getTimeOfStunBuff() {
        return timeOfStunBuff;
    }

    public static void setIsStunBuffPermanent(boolean isStunBuffPermanent) {
        Information.isStunBuffPermanent = isStunBuffPermanent;
    }

    public static boolean getIsStunBuffPermanent() {
        return isStunBuffPermanent;
    }

    public static void setCanPowerBuffAdd(boolean canPowerBuffAdd) {
        Information.canPowerBuffAdd = canPowerBuffAdd;
    }

    public static boolean getCanPowerBuffAdd() {
        return canPowerBuffAdd;
    }

    public static void setTimeOfPowerBuff(int timeOfPowerBuff) {
        Information.timeOfPowerBuff = timeOfPowerBuff;
    }

    public static int getTimeOfPowerBuff() {
        return timeOfPowerBuff;
    }

    public static void setIsPowerBuffPermanent(boolean isPowerBuffPermanent) {
        Information.isPowerBuffPermanent = isPowerBuffPermanent;
    }

    public static boolean getIsPowerBuffPermanent() {
        return isPowerBuffPermanent;
    }

    public static void setCanRemoveBadBuffsOfOurselves(boolean canRemoveBadBuffsOfOurselves) {
        Information.canRemoveBadBuffsOfOurselves = canRemoveBadBuffsOfOurselves;
    }

    public static boolean getCanRemoveBadBuffsOfOurselves() {
        return canRemoveBadBuffsOfOurselves;
    }

    public static void setCanRemoveGoodBuffsOfEnemy(boolean canRemoveGoodBuffsOfEnemy) {
        Information.canRemoveGoodBuffsOfEnemy = canRemoveGoodBuffsOfEnemy;
    }

    public static boolean getCanRemoveGoodBuffsOfEnemy() {
        return canRemoveGoodBuffsOfEnemy;
    }

    public static void setCanDamageToEnemy(boolean canDamageToEnemy) {
        Information.canDamageToEnemy = canDamageToEnemy;
    }

    public static boolean getCanDamageToEnemy() {
        return canDamageToEnemy;
    }

    public static void setDamageToEnemy(int damageToEnemy) {
        Information.damageToEnemy = damageToEnemy;
    }

    public static int getDamageToEnemy() {
        return damageToEnemy;
    }

    public static void setCanAddFieryBuffToCell(boolean canAddFieryBuffToCell) {
        Information.canAddFieryBuffToCell = canAddFieryBuffToCell;
    }

    public static boolean getCanAddFieryBuffToCell() {
        return canAddFieryBuffToCell;
    }

    public static void setTimeOfAddFieryBuffToCell(int timeOfAddFieryBuffToCell) {
        Information.timeOfAddFieryBuffToCell = timeOfAddFieryBuffToCell;
    }

    public static int getTimeOfAddFieryBuffToCell() {
        return timeOfAddFieryBuffToCell;
    }

    public static void setCanAddPoisonBuffToCell(boolean canAddPoisonBuffToCell) {
        Information.canAddPoisonBuffToCell = canAddPoisonBuffToCell;
    }

    public static boolean getCanAddPoisonBuffToCell() {
        return canAddPoisonBuffToCell;
    }

    public static void setTimeOfAddPoisonBuffToCell(int timeOfAddPoisonBuffToCell) {
        Information.timeOfAddPoisonBuffToCell = timeOfAddPoisonBuffToCell;
    }

    public static int getTimeOfAddPoisonBuffToCell() {
        return timeOfAddPoisonBuffToCell;
    }

    public static void setCanKillMinionOfEnemy(boolean canKillMinionOfEnemy) {
        Information.canKillMinionOfEnemy = canKillMinionOfEnemy;
    }

    public static boolean getCanKillMinionOfEnemy() {
        return canKillMinionOfEnemy;
    }

    public static void setCanKillOurMinionAndHealHero(boolean canKillOurMinionAndHealHero) {
        Information.canKillOurMinionAndHealHero = canKillOurMinionAndHealHero;
    }

    public static boolean getCanKillOurMinionAndHealHero() {
        return canKillOurMinionAndHealHero;
    }

    public static void setCanDoRangedAttack(boolean canDoRangedAttack) {
        Information.canDoRangedAttack = canDoRangedAttack;
    }

    public static boolean getCanDoRangedAttack() {
        return canDoRangedAttack;
    }

    public static void setCanDoHybridAttack(boolean canDoHybridAttack) {
        Information.canDoHybridAttack = canDoHybridAttack;
    }

    public static boolean getCanDoHybridAttack() {
        return canDoHybridAttack;
    }

    public static void setCanDoMeleeAttack(boolean canDoMeleeAttack) {
        Information.canDoMeleeAttack = canDoMeleeAttack;
    }

    public static boolean getCanDoMeleeAttack() {
        return canDoMeleeAttack;
    }

    public static void setIsGhooleBozorg(boolean isGhooleBozorg) {
        Information.isGhooleBozorg = isGhooleBozorg;
    }

    public static boolean getIsGhooleBozorg() {
        return isGhooleBozorg;
    }

    public static void setIsShireDarrande(boolean isShireDarrande) {
        Information.isShireDarrande = isShireDarrande;
    }

    public static boolean getIsShireDarrande() {
        return isShireDarrande;
    }

    public static void setIsZahhak(boolean isZahhak) {
        Information.isZahhak = isZahhak;
    }

    public static boolean getIsZahhak() {
        return isZahhak;
    }

    public static void setCanIncreaseRangeOfAttack(boolean canIncreaseRangeOfAttack) {
        Information.canIncreaseRangeOfAttack = canIncreaseRangeOfAttack;
    }

    public static boolean getCanIncreaseRangeOfAttack() {
        return canIncreaseRangeOfAttack;
    }

    public static void setCanDoubleHp(boolean canDoubleHp) {
        Information.canDoubleHp = canDoubleHp;
    }

    public static boolean getCanDoubleHp() {
        return canDoubleHp;
    }

    public static void setCanIncreaseMana(boolean canIncreaseMana) {
        Information.canIncreaseMana = canIncreaseMana;
    }

    public static boolean getCanIncreaseMana() {
        return canIncreaseMana;
    }

    public static int getDecreaseHpOfPoisonBuffOfCell() {
        return decreaseHpOfPoisonBuffOfCell;
    }

    public static void setDecreaseHpOfPoisonBuffOfCell(int decreaseHpOfPoisonBuffOfCell) {
        Information.decreaseHpOfPoisonBuffOfCell = decreaseHpOfPoisonBuffOfCell;
    }

    public int getGhooleBozorgDamage() {
        return ghooleBozorgDamage;
    }

    public void setGhooleBozorgDamage(int ghooleBozorgDamage) {
        this.ghooleBozorgDamage = ghooleBozorgDamage;
    }

    //methods

    public void readInformation(){
        return;
    }

}
