package Model.CollectionItem;

public class Information {


    //methods

    public void readInformation() {
        //TODO
        return;
    }


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

    private boolean canIncreaseAP;
    private int amountOfIncreaseAP;
    private int remainTurn;

    private boolean increasePermanent;

    private boolean isImpactAllArea;

    private boolean isImpactArea;
    private int distanceOfImpactArea;

    private boolean isImpactItself;

    private boolean isSpecialMinion;

    private boolean canHolyBuffAdd;
    private int timeOfHolyBuff;
    private boolean isHolyBuffPermanent;
    private int numberOfHolyBuff;
    private boolean isHolyBuffPassive;

    private boolean canDisarmBuffAdd;
    private int timeOfDisarmBuff;
    private boolean isDisarmBuffPermanent;

    private boolean canWeaknessBuffAdd;
    private int timeOfWeaknessBuff;
    private boolean isWeaknessBuffPermanent;

    private boolean canPoisonBuffAdd;
    private int timeOfPoisonBuff;
    private boolean isPoisonBuffPermanent;

    private boolean canStunBuffAdd;
    private int timeOfStunBuff;
    private boolean isStunBuffPermanent;

    private boolean canPowerBuffAdd;
    private int timeOfPowerBuff;
    private boolean isPowerBuffPermanent;

    private boolean canRemoveBadBuffsOfOurselves;
    private boolean canRemoveGoodBuffsOfEnemy;

    private boolean canDamageToEnemy;
    private int damageToEnemy;

    private boolean canAddFieryBuffToCell;
    private int timeOfAddFieryBuffToCell;


    private boolean canAddPoisonBuffToCell;
    private int timeOfAddPoisonBuffToCell;
    private int decreaseHpOfPoisonBuffOfCell;

    private boolean canKillMinionOfEnemy;
    private boolean canKillOurMinionAndHealHero;
    private boolean canDoRangedAttack;
    private boolean canDoHybridAttack;
    private boolean canDoMeleeAttack;
    private boolean isGhooleBozorg;
    private int ghooleBozorgDamage;
    private boolean isShireDarrande;
    private boolean isZahhak;
    private boolean canIncreaseRangeOfAttack;
    private boolean canDoubleHp;
    private boolean canIncreaseMana;
    private int amountOfIncreaseMana;
    private boolean canIncreaseManaAfter3Rounds;
    private boolean canAddNefrineMarg;

    private boolean canKillHeroOfEnemyAfterRounds;
    private int numberOfRoundsNeededForKillHeroOfEnemy;

    private boolean addGhosleTamid;
    private int timeOfGhosleTamid;
    private boolean terrorHood;
    private boolean poisonousDagger;
    private boolean shockHammer;

    public int getRemainTurn() {
        return remainTurn;
    }

    public void setRemainTurn(int remainTurn) {
        this.remainTurn = remainTurn;
    }



    public boolean isIncreasePermanent() {
        return increasePermanent;
    }

    public void setIncreasePermanent(boolean increasePermanent) {
        this.increasePermanent = increasePermanent;
    }

    public int getNumberOfHolyBuff() {
        return numberOfHolyBuff;
    }

    public void setNumberOfHolyBuff(int numberOfHolyBuff) {
        this.numberOfHolyBuff = numberOfHolyBuff;
    }

    public void setCanHolyBuffAdd(boolean canHolyBuffAdd) {
        this.canHolyBuffAdd = canHolyBuffAdd;
    }

    public boolean isCanHolyBuffAdd() {
        return canHolyBuffAdd;
    }

    public void setTimeOfHolyBuff(int timeOfHolyBuff) {
        this.timeOfHolyBuff = timeOfHolyBuff;
    }

    public int getTimeOfHolyBuff() {
        return timeOfHolyBuff;
    }

    public void setHolyBuffPermanent(boolean holyBuffPermanent) {
        isHolyBuffPermanent = holyBuffPermanent;
    }

    public boolean isHolyBuffPermanent() {
        return isHolyBuffPermanent;
    }

    public void setCanDisarmBuffAdd(boolean canDisarmBuffAdd) {
        this.canDisarmBuffAdd = canDisarmBuffAdd;
    }

    public boolean isCanDisarmBuffAdd() {
        return canDisarmBuffAdd;
    }

    public void setTimeOfDisarmBuff(int timeOfDisarmBuff) {
        this.timeOfDisarmBuff = timeOfDisarmBuff;
    }

    public int getTimeOfDisarmBuff() {
        return timeOfDisarmBuff;
    }

    public void setDisarmBuffPermanent(boolean disarmBuffPermanent) {
        isDisarmBuffPermanent = disarmBuffPermanent;
    }

    public boolean isDisarmBuffPermanent() {
        return isDisarmBuffPermanent;
    }

    public void setCanWeaknessBuffAdd(boolean canWeaknessBuffAdd) {
        this.canWeaknessBuffAdd = canWeaknessBuffAdd;
    }

    public boolean isCanWeaknessBuffAdd() {
        return canWeaknessBuffAdd;
    }

    public void setTimeOfWeaknessBuff(int timeOfWeaknessBuff) {
        this.timeOfWeaknessBuff = timeOfWeaknessBuff;
    }

    public int getTimeOfWeaknessBuff() {
        return timeOfWeaknessBuff;
    }

    public void setWeaknessBuffPermanent(boolean weaknessBuffPermanent) {
        isWeaknessBuffPermanent = weaknessBuffPermanent;
    }

    public boolean isWeaknessBuffPermanent() {
        return isWeaknessBuffPermanent;
    }

    public void setCanPoisonBuffAdd(boolean canPoisonBuffAdd) {
        this.canPoisonBuffAdd = canPoisonBuffAdd;
    }

    public boolean isCanPoisonBuffAdd() {
        return canPoisonBuffAdd;
    }

    public void setTimeOfPoisonBuff(int timeOfPoisonBuff) {
        this.timeOfPoisonBuff = timeOfPoisonBuff;
    }

    public int getTimeOfPoisonBuff() {
        return timeOfPoisonBuff;
    }

    public void setPoisonBuffPermanent(boolean poisonBuffPermanent) {
        isPoisonBuffPermanent = poisonBuffPermanent;
    }

    public boolean isPoisonBuffPermanent() {
        return isPoisonBuffPermanent;
    }

    public void setCanStunBuffAdd(boolean canStunBuffAdd) {
        this.canStunBuffAdd = canStunBuffAdd;
    }

    public boolean isCanStunBuffAdd() {
        return canStunBuffAdd;
    }

    public void setTimeOfStunBuff(int timeOfStunBuff) {
        this.timeOfStunBuff = timeOfStunBuff;
    }

    public int getTimeOfStunBuff() {
        return timeOfStunBuff;
    }

    public void setStunBuffPermanent(boolean stunBuffPermanent) {
        isStunBuffPermanent = stunBuffPermanent;
    }

    public boolean isStunBuffPermanent() {
        return isStunBuffPermanent;
    }

    public void setCanPowerBuffAdd(boolean canPowerBuffAdd) {
        this.canPowerBuffAdd = canPowerBuffAdd;
    }

    public boolean isCanPowerBuffAdd() {
        return canPowerBuffAdd;
    }

    public void setTimeOfPowerBuff(int timeOfPowerBuff) {
        this.timeOfPowerBuff = timeOfPowerBuff;
    }

    public int getTimeOfPowerBuff() {
        return timeOfPowerBuff;
    }

    public void setPowerBuffPermanent(boolean powerBuffPermanent) {
        isPowerBuffPermanent = powerBuffPermanent;
    }

    public boolean isPowerBuffPermanent() {
        return isPowerBuffPermanent;
    }

    public void setCanRemoveBadBuffsOfOurselves(boolean canRemoveBadBuffsOfOurselves) {
        this.canRemoveBadBuffsOfOurselves = canRemoveBadBuffsOfOurselves;
    }

    public boolean isCanRemoveBadBuffsOfOurselves() {
        return canRemoveBadBuffsOfOurselves;
    }

    public void setCanRemoveGoodBuffsOfEnemy(boolean canRemoveGoodBuffsOfEnemy) {
        this.canRemoveGoodBuffsOfEnemy = canRemoveGoodBuffsOfEnemy;
    }

    public boolean isCanRemoveGoodBuffsOfEnemy() {
        return canRemoveGoodBuffsOfEnemy;
    }

    public void setCanDamageToEnemy(boolean canDamageToEnemy) {
        this.canDamageToEnemy = canDamageToEnemy;
    }

    public boolean isCanDamageToEnemy() {
        return canDamageToEnemy;
    }

    public void setDamageToEnemy(int damageToEnemy) {
        this.damageToEnemy = damageToEnemy;
    }

    public int getDamageToEnemy() {
        return damageToEnemy;
    }

    public void setCanAddFieryBuffToCell(boolean canAddFieryBuffToCell) {
        this.canAddFieryBuffToCell = canAddFieryBuffToCell;
    }

    public boolean isCanAddFieryBuffToCell() {
        return canAddFieryBuffToCell;
    }

    public void setTimeOfAddFieryBuffToCell(int timeOfAddFieryBuffToCell) {
        this.timeOfAddFieryBuffToCell = timeOfAddFieryBuffToCell;
    }

    public int getTimeOfAddFieryBuffToCell() {
        return timeOfAddFieryBuffToCell;
    }

    public void setCanAddPoisonBuffToCell(boolean canAddPoisonBuffToCell) {
        this.canAddPoisonBuffToCell = canAddPoisonBuffToCell;
    }

    public boolean isCanAddPoisonBuffToCell() {
        return canAddPoisonBuffToCell;
    }

    public void setTimeOfAddPoisonBuffToCell(int timeOfAddPoisonBuffToCell) {
        this.timeOfAddPoisonBuffToCell = timeOfAddPoisonBuffToCell;
    }

    public int getTimeOfAddPoisonBuffToCell() {
        return timeOfAddPoisonBuffToCell;
    }

    public void setDecreaseHpOfPoisonBuffOfCell(int decreaseHpOfPoisonBuffOfCell) {
        this.decreaseHpOfPoisonBuffOfCell = decreaseHpOfPoisonBuffOfCell;
    }

    public int getDecreaseHpOfPoisonBuffOfCell() {
        return decreaseHpOfPoisonBuffOfCell;
    }

    public void setCanKillMinionOfEnemy(boolean canKillMinionOfEnemy) {
        this.canKillMinionOfEnemy = canKillMinionOfEnemy;
    }

    public boolean isCanKillMinionOfEnemy() {
        return canKillMinionOfEnemy;
    }

    public void setCanKillOurMinionAndHealHero(boolean canKillOurMinionAndHealHero) {
        this.canKillOurMinionAndHealHero = canKillOurMinionAndHealHero;
    }

    public boolean isCanKillOurMinionAndHealHero() {
        return canKillOurMinionAndHealHero;
    }

    public void setCanDoRangedAttack(boolean canDoRangedAttack) {
        this.canDoRangedAttack = canDoRangedAttack;
    }

    public boolean isCanDoRangedAttack() {
        return canDoRangedAttack;
    }

    public void setCanDoHybridAttack(boolean canDoHybridAttack) {
        this.canDoHybridAttack = canDoHybridAttack;
    }

    public boolean isCanDoHybridAttack() {
        return canDoHybridAttack;
    }

    public void setCanDoMeleeAttack(boolean canDoMeleeAttack) {
        this.canDoMeleeAttack = canDoMeleeAttack;
    }

    public boolean isCanDoMeleeAttack() {
        return canDoMeleeAttack;
    }

    public void setGhooleBozorg(boolean ghooleBozorg) {
        isGhooleBozorg = ghooleBozorg;
    }

    public boolean isGhooleBozorg() {
        return isGhooleBozorg;
    }

    public void setGhooleBozorgDamage(int ghooleBozorgDamage) {
        this.ghooleBozorgDamage = ghooleBozorgDamage;
    }

    public int getGhooleBozorgDamage() {
        return ghooleBozorgDamage;
    }

    public void setShireDarrande(boolean shireDarrande) {
        isShireDarrande = shireDarrande;
    }

    public boolean isShireDarrande() {
        return isShireDarrande;
    }

    public void setZahhak(boolean zahhak) {
        isZahhak = zahhak;
    }

    public boolean isZahhak() {
        return isZahhak;
    }

    public void setCanIncreaseRangeOfAttack(boolean canIncreaseRangeOfAttack) {
        this.canIncreaseRangeOfAttack = canIncreaseRangeOfAttack;
    }

    public boolean isCanIncreaseRangeOfAttack() {
        return canIncreaseRangeOfAttack;
    }

    public void setCanDoubleHp(boolean canDoubleHp) {
        this.canDoubleHp = canDoubleHp;
    }

    public boolean isCanDoubleHp() {
        return canDoubleHp;
    }

    public void setCanIncreaseMana(boolean canIncreaseMana) {
        this.canIncreaseMana = canIncreaseMana;
    }

    public boolean isCanIncreaseMana() {
        return canIncreaseMana;
    }

    public boolean isSpecialMinion() {
        return isSpecialMinion;
    }

    public void setSpecialMinion(boolean specialMinion) {
        isSpecialMinion = specialMinion;
    }

    public boolean isImpactArea() {
        return isImpactArea;
    }

    public void setImpactArea(boolean impactArea) {
        isImpactArea = impactArea;
    }

    public int getDistanceOfImpactArea() {
        return distanceOfImpactArea;
    }

    public void setDistanceOfImpactArea(int distanceOfImpactArea) {
        this.distanceOfImpactArea = distanceOfImpactArea;
    }

    public boolean isImpactItself() {
        return isImpactItself;
    }

    public void setImpactItself(boolean impactItself) {
        isImpactItself = impactItself;
    }

    public boolean isImpactAllArea() {
        return isImpactAllArea;
    }

    public void setImpactAllArea(boolean impactAllArea) {
        isImpactAllArea = impactAllArea;
    }

    //ina bishtar baraye item hast

    private boolean canIncreaseHPOfLivingCard;
    private int amountOfIncreaseHPOfLivingCard;

    public boolean isCanIncreaseHPOfLivingCard() {
        return canIncreaseHPOfLivingCard;
    }

    public void setCanIncreaseHPOfLivingCard(boolean canIncreaseHPOfLivingCard) {
        this.canIncreaseHPOfLivingCard = canIncreaseHPOfLivingCard;
    }

    public int getAmountOfIncreaseHPOfLivingCard() {
        return amountOfIncreaseHPOfLivingCard;
    }

    public void setAmountOfIncreaseHPOfLivingCard(int amountOfIncreaseHPOfLivingCard) {
        this.amountOfIncreaseHPOfLivingCard = amountOfIncreaseHPOfLivingCard;
    }

    public boolean isHolyBuffPassive() {
        return isHolyBuffPassive;
    }

    public void setHolyBuffPassive(boolean holyBuffPassive) {
        isHolyBuffPassive = holyBuffPassive;
    }

    public boolean isCanIncreaseAP() {
        return canIncreaseAP;
    }

    public void setCanIncreaseAP(boolean canIncreaseAP) {
        this.canIncreaseAP = canIncreaseAP;
    }

    public int getAmountOfIncreaseAP() {
        return amountOfIncreaseAP;
    }

    public void setAmountOfIncreaseAP(int amountOfIncreaseAP) {
        this.amountOfIncreaseAP = amountOfIncreaseAP;
    }

    public boolean isCanIncreaseManaAfter3Rounds() {
        return canIncreaseManaAfter3Rounds;
    }

    public void setCanIncreaseManaAfter3Rounds(boolean canIncreaseManaAfter3Rounds) {
        this.canIncreaseManaAfter3Rounds = canIncreaseManaAfter3Rounds;
    }

    public int getAmountOfIncreaseMana() {
        return amountOfIncreaseMana;
    }

    public void setAmountOfIncreaseMana(int amountOfIncreaseMana) {
        this.amountOfIncreaseMana = amountOfIncreaseMana;
    }

    public boolean isCanAddNefrineMarg() {
        return canAddNefrineMarg;
    }

    public void setCanAddNefrineMarg(boolean canAddNefrineMarg) {
        this.canAddNefrineMarg = canAddNefrineMarg;
    }

    public boolean isCanKillHeroOfEnemyAfterRounds() {
        return canKillHeroOfEnemyAfterRounds;
    }

    public void setCanKillHeroOfEnemyAfterRounds(boolean canKillHeroOfEnemyAfterRounds) {
        this.canKillHeroOfEnemyAfterRounds = canKillHeroOfEnemyAfterRounds;
    }

    public int getNumberOfRoundsNeededForKillHeroOfEnemy() {
        return numberOfRoundsNeededForKillHeroOfEnemy;
    }

    public void setNumberOfRoundsNeededForKillHeroOfEnemy(int numberOfRoundsNeededForKillHeroOfEnemy) {
        this.numberOfRoundsNeededForKillHeroOfEnemy = numberOfRoundsNeededForKillHeroOfEnemy;
    }

    public boolean isAddGhosleTamid() {
        return addGhosleTamid;
    }

    public void setAddGhosleTamid(boolean addGhosleTamid) {
        this.addGhosleTamid = addGhosleTamid;
    }

    public int getTimeOfGhosleTamid() {
        return timeOfGhosleTamid;
    }

    public void setTimeOfGhosleTamid(int timeOfGhosleTamid) {
        this.timeOfGhosleTamid = timeOfGhosleTamid;
    }

    public boolean isTerrorHood() {
        return terrorHood;
    }

    public void setTerrorHood(boolean terrorHood) {
        this.terrorHood = terrorHood;
    }

    public boolean isPoisonousDagger() {
        return poisonousDagger;
    }

    public void setPoisonousDagger(boolean poisonousDagger) {
        this.poisonousDagger = poisonousDagger;
    }

    public boolean isShockHammer() {
        return shockHammer;
    }

    public void setShockHammer(boolean shockHammer) {
        this.shockHammer = shockHammer;
    }
}
