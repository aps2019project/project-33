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
    private boolean isImpactColumn;
    private boolean isImpactRow;
    private boolean isKingsGuard;
    private boolean isImpactNeighbors;

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

    public boolean isImpactColumn() {
        return isImpactColumn;
    }

    public void setImpactColumn(boolean impactColumn) {
        isImpactColumn = impactColumn;
    }

    public boolean isImpactRow() {
        return isImpactRow;
    }

    public void setImpactRow(boolean impactRow) {
        isImpactRow = impactRow;
    }

    public boolean isImpactNeighbors() {
        return isImpactNeighbors;
    }

    public void setImpactNeighbors(boolean impactNeighbors) {
        isImpactNeighbors = impactNeighbors;
    }

    //Attack section

    private  boolean canHolyBuffAdd;
    private   int timeOfHolyBuff;
    private   boolean isHolyBuffPermanent;

    private   boolean canDisarmBuffAdd;
    private   int timeOfDisarmBuff;
    private   boolean isDisarmBuffPermanent;

    private   boolean canWeaknessBuffAdd;
    private   int timeOfWeaknessBuff;
    private   boolean isWeaknessBuffPermanent;

    private   boolean canPoisonBuffAdd;
    private   int timeOfPoisonBuff;
    private   boolean isPoisonBuffPermanent;

    private   boolean canStunBuffAdd;
    private   int timeOfStunBuff;
    private   boolean isStunBuffPermanent;

    private   boolean canPowerBuffAdd;
    private   int timeOfPowerBuff;
    private   boolean isPowerBuffPermanent;

    private   boolean canRemoveBadBuffsOfOurselves;
    private   boolean canRemoveGoodBuffsOfEnemy;

    private   boolean canDamageToEnemy;
    private   int damageToEnemy;

    private   boolean canAddFieryBuffToCell;
    private   int timeOfAddFieryBuffToCell;

    
    private   boolean canAddPoisonBuffToCell;
    private   int timeOfAddPoisonBuffToCell;
    private   int decreaseHpOfPoisonBuffOfCell;

    private   boolean canKillMinionOfEnemy;
    private   boolean canKillOurMinionAndHealHero;
    private   boolean canDoRangedAttack;
    private   boolean canDoHybridAttack;
    private   boolean canDoMeleeAttack;
    private   boolean isGhooleBozorg;
    private int ghooleBozorgDamage;
    private   boolean isShireDarrande;
    private   boolean isZahhak;
    private   boolean canIncreaseRangeOfAttack;
    private   boolean canDoubleHp;
    private   boolean canIncreaseMana;

    public   boolean getCanHolyBuffAdd() {
        return canHolyBuffAdd;
    }

    public   void setCanHolyBuffAdd(boolean canHolyBuffAdd) {
        Information.canHolyBuffAdd = canHolyBuffAdd;
    }

    public   void setTimeOfHolyBuff(int timeOfHolyBuff) {
        Information.timeOfHolyBuff = timeOfHolyBuff;
    }

    public   int getTimeOfHolyBuff() {
        return timeOfHolyBuff;
    }

    public   void setIsHolyBuffPermanent(boolean isHolyBuffPermanent) {
        Information.isHolyBuffPermanent = isHolyBuffPermanent;
    }

    public   boolean getIsHolyBuffPermanent() {
        return isHolyBuffPermanent;
    }

    public   void setCanDisarmBuffAdd(boolean canDisarmBuffAdd) {
        Information.canDisarmBuffAdd = canDisarmBuffAdd;
    }

    public   boolean getCanDisarmBuffAdd() {
        return canDisarmBuffAdd;
    }

    public   void setTimeOfDisarmBuff(int timeOfDisarmBuff) {
        Information.timeOfDisarmBuff = timeOfDisarmBuff;
    }

    public   int getTimeOfDisarmBuff() {
        return timeOfDisarmBuff;
    }

    public   void setIsDisarmBuffPermanent(boolean isDisarmBuffPermanent) {
        Information.isDisarmBuffPermanent = isDisarmBuffPermanent;
    }

    public   boolean getIsDisarmBuffPermanent() {
        return isDisarmBuffPermanent;
    }

    public   void setCanWeaknessBuffAdd(boolean canWeaknessBuffAdd) {
        Information.canWeaknessBuffAdd = canWeaknessBuffAdd;
    }

    public   boolean getCanWeaknessBuffAdd() {
        return canWeaknessBuffAdd;
    }

    public   void setTimeOfWeaknessBuff(int timeOfWeaknessBuff) {
        Information.timeOfWeaknessBuff = timeOfWeaknessBuff;
    }

    public   int getTimeOfWeaknessBuff() {
        return timeOfWeaknessBuff;
    }

    public   void setIsWeaknessBuffPermanent(boolean isWeaknessBuffPermanent) {
        Information.isWeaknessBuffPermanent = isWeaknessBuffPermanent;
    }

    public   boolean getIsWeaknessBuffPermanent() {
        return isWeaknessBuffPermanent;
    }

    public   void setCanPoisonBuffAdd(boolean canPoisonBuffAdd) {
        Information.canPoisonBuffAdd = canPoisonBuffAdd;
    }

    public   boolean getCanPoisonBuffAdd() {
        return canPoisonBuffAdd;
    }

    public   void setTimeOfPoisonBuff(int timeOfPoisonBuff) {
        Information.timeOfPoisonBuff = timeOfPoisonBuff;
    }

    public   int getTimeOfPoisonBuff() {
        return timeOfPoisonBuff;
    }

    public   void setIsPoisonBuffPermanent(boolean isPoisonBuffPermanent) {
        Information.isPoisonBuffPermanent = isPoisonBuffPermanent;
    }

    public   boolean getIsPoisonBuffPermanent() {
        return isPoisonBuffPermanent;
    }

    public   void setCanStunBuffAdd(boolean canStunBuffAdd) {
        Information.canStunBuffAdd = canStunBuffAdd;
    }

    public   boolean getCanStunBuffAdd() {
        return canStunBuffAdd;
    }

    public   void setTimeOfStunBuff(int timeOfStunBuff) {
        Information.timeOfStunBuff = timeOfStunBuff;
    }

    public   int getTimeOfStunBuff() {
        return timeOfStunBuff;
    }

    public   void setIsStunBuffPermanent(boolean isStunBuffPermanent) {
        Information.isStunBuffPermanent = isStunBuffPermanent;
    }

    public   boolean getIsStunBuffPermanent() {
        return isStunBuffPermanent;
    }

    public   void setCanPowerBuffAdd(boolean canPowerBuffAdd) {
        Information.canPowerBuffAdd = canPowerBuffAdd;
    }

    public   boolean getCanPowerBuffAdd() {
        return canPowerBuffAdd;
    }

    public   void setTimeOfPowerBuff(int timeOfPowerBuff) {
        Information.timeOfPowerBuff = timeOfPowerBuff;
    }

    public   int getTimeOfPowerBuff() {
        return timeOfPowerBuff;
    }

    public   void setIsPowerBuffPermanent(boolean isPowerBuffPermanent) {
        Information.isPowerBuffPermanent = isPowerBuffPermanent;
    }

    public   boolean getIsPowerBuffPermanent() {
        return isPowerBuffPermanent;
    }

    public   void setCanRemoveBadBuffsOfOurselves(boolean canRemoveBadBuffsOfOurselves) {
        Information.canRemoveBadBuffsOfOurselves = canRemoveBadBuffsOfOurselves;
    }

    public   boolean getCanRemoveBadBuffsOfOurselves() {
        return canRemoveBadBuffsOfOurselves;
    }

    public   void setCanRemoveGoodBuffsOfEnemy(boolean canRemoveGoodBuffsOfEnemy) {
        Information.canRemoveGoodBuffsOfEnemy = canRemoveGoodBuffsOfEnemy;
    }

    public   boolean getCanRemoveGoodBuffsOfEnemy() {
        return canRemoveGoodBuffsOfEnemy;
    }

    public   void setCanDamageToEnemy(boolean canDamageToEnemy) {
        Information.canDamageToEnemy = canDamageToEnemy;
    }

    public   boolean getCanDamageToEnemy() {
        return canDamageToEnemy;
    }

    public   void setDamageToEnemy(int damageToEnemy) {
        Information.damageToEnemy = damageToEnemy;
    }

    public   int getDamageToEnemy() {
        return damageToEnemy;
    }

    public   void setCanAddFieryBuffToCell(boolean canAddFieryBuffToCell) {
        Information.canAddFieryBuffToCell = canAddFieryBuffToCell;
    }

    public   boolean getCanAddFieryBuffToCell() {
        return canAddFieryBuffToCell;
    }

    public   void setTimeOfAddFieryBuffToCell(int timeOfAddFieryBuffToCell) {
        Information.timeOfAddFieryBuffToCell = timeOfAddFieryBuffToCell;
    }

    public   int getTimeOfAddFieryBuffToCell() {
        return timeOfAddFieryBuffToCell;
    }

    public   void setCanAddPoisonBuffToCell(boolean canAddPoisonBuffToCell) {
        Information.canAddPoisonBuffToCell = canAddPoisonBuffToCell;
    }

    public   boolean getCanAddPoisonBuffToCell() {
        return canAddPoisonBuffToCell;
    }

    public   void setTimeOfAddPoisonBuffToCell(int timeOfAddPoisonBuffToCell) {
        Information.timeOfAddPoisonBuffToCell = timeOfAddPoisonBuffToCell;
    }

    public   int getTimeOfAddPoisonBuffToCell() {
        return timeOfAddPoisonBuffToCell;
    }

    public   void setCanKillMinionOfEnemy(boolean canKillMinionOfEnemy) {
        Information.canKillMinionOfEnemy = canKillMinionOfEnemy;
    }

    public   boolean getCanKillMinionOfEnemy() {
        return canKillMinionOfEnemy;
    }

    public   void setCanKillOurMinionAndHealHero(boolean canKillOurMinionAndHealHero) {
        Information.canKillOurMinionAndHealHero = canKillOurMinionAndHealHero;
    }

    public   boolean getCanKillOurMinionAndHealHero() {
        return canKillOurMinionAndHealHero;
    }

    public   void setCanDoRangedAttack(boolean canDoRangedAttack) {
        Information.canDoRangedAttack = canDoRangedAttack;
    }

    public   boolean getCanDoRangedAttack() {
        return canDoRangedAttack;
    }

    public   void setCanDoHybridAttack(boolean canDoHybridAttack) {
        Information.canDoHybridAttack = canDoHybridAttack;
    }

    public   boolean getCanDoHybridAttack() {
        return canDoHybridAttack;
    }

    public   void setCanDoMeleeAttack(boolean canDoMeleeAttack) {
        Information.canDoMeleeAttack = canDoMeleeAttack;
    }

    public   boolean getCanDoMeleeAttack() {
        return canDoMeleeAttack;
    }

    public   void setIsGhooleBozorg(boolean isGhooleBozorg) {
        Information.isGhooleBozorg = isGhooleBozorg;
    }

    public   boolean getIsGhooleBozorg() {
        return isGhooleBozorg;
    }

    public   void setIsShireDarrande(boolean isShireDarrande) {
        Information.isShireDarrande = isShireDarrande;
    }

    public   boolean getIsShireDarrande() {
        return isShireDarrande;
    }

    public   void setIsZahhak(boolean isZahhak) {
        Information.isZahhak = isZahhak;
    }

    public   boolean getIsZahhak() {
        return isZahhak;
    }

    public   void setCanIncreaseRangeOfAttack(boolean canIncreaseRangeOfAttack) {
        Information.canIncreaseRangeOfAttack = canIncreaseRangeOfAttack;
    }

    public   boolean getCanIncreaseRangeOfAttack() {
        return canIncreaseRangeOfAttack;
    }

    public   void setCanDoubleHp(boolean canDoubleHp) {
        Information.canDoubleHp = canDoubleHp;
    }

    public   boolean getCanDoubleHp() {
        return canDoubleHp;
    }

    public   void setCanIncreaseMana(boolean canIncreaseMana) {
        Information.canIncreaseMana = canIncreaseMana;
    }

    public   boolean getCanIncreaseMana() {
        return canIncreaseMana;
    }

    public   int getDecreaseHpOfPoisonBuffOfCell() {
        return decreaseHpOfPoisonBuffOfCell;
    }

    public   void setDecreaseHpOfPoisonBuffOfCell(int decreaseHpOfPoisonBuffOfCell) {
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
        //TODO
        return;
    }
}
