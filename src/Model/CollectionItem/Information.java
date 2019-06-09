package Model.CollectionItem;

public class Information {

    //TODO ghoole bozorg ro divert kardim raft
    private boolean isKingsGuard;

    private boolean isOnDefence;
    private boolean isOnSpawn;
    private boolean isPassive;
    private boolean isOnDeath;
    private boolean isCombo;
    private boolean isOnTurn;
    private boolean isOnAttack;

    //AttackArea section

    private boolean isEnemyImpact;
    private boolean isUsImpact;
    private boolean isMultipleImpact;
    private boolean isMinionImpact;
    private boolean isHeroImpact;
    private boolean isCellImpact;
    private boolean locationLimit;
    private boolean isSquareOfCellsImpact;
    private int lengthOfSquareOfCellsImpact;
    private boolean isImpactColumn;
    private boolean isImpactRow;
    private boolean isImpactNeighbors;
    private boolean isImpactItself;
    private boolean isImpactAllArea;
    //TODO fasele manhatanie
    private boolean isImpactArea;
    private int distanceOfImpactArea;

    //Attack section

    private boolean canIncreaseAP;
    private int amountOfIncreaseAP;
    private int increaseRemainTime;
    private boolean increaseAPPermanent;


    private boolean canHolyBuffAdd;
    private int timeOfHolyBuff;
    private boolean isHolyBuffPermanent;
    private int numberOfHolyBuff;

    private boolean canDisarmBuffAdd;
    private int timeOfDisarmBuff;
    private boolean isDisarmBuffPermanent;

    private boolean canWeaknessBuffAdd;
    private int timeOfWeaknessBuff;
    private boolean isWeaknessBuffPermanent;
    private int changeHPByWeakness;
    private int changeAPByWeakness;

    private boolean canPoisonBuffAdd;
    private int timeOfPoisonBuff;
    private boolean isPoisonBuffPermanent;
    private int decreaseHPOfPoisonBuff;

    private boolean canStunBuffAdd;
    private int timeOfStunBuff;
    private boolean isStunBuffPermanent;

    private boolean canPowerBuffAdd;
    private int timeOfPowerBuff;
    private boolean isPowerBuffPermanent;
    private int changeHPByPowerBuff;
    private int changeAPByPowerBuff;

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
    private boolean isAntiHollyBuff;
    private boolean canIncreaseRangeOfAttack;
    private int amountOfIncreaseRangeOfAttack;
    private boolean canDoubleHp;
    private boolean canIncreaseMana;
    private int amountOfIncreaseMana;
    private int timeOfIncreaseMana;
    private int isIncreaseManaPermanent;
    private boolean canAddNefrineMarg;

    private boolean canKillHeroOfEnemyAfterRounds;
    private int numberOfRoundsNeededForKillHeroOfEnemy;

    private boolean addGhosleTamid;
    private int timeOfGhosleTamid;
    private boolean terrorHood;
    private boolean poisonousDagger;
    private boolean shockHammer;


    private boolean forMelee;
    private boolean forRange;
    private boolean forHybrid;

    private boolean pareSimorgh;
    private int minOfPareSimorgh;
    private boolean isShamshireChini;
    private boolean isSoulEater;
    private boolean isAssassinationDagger;

    private boolean canDecreaseHpNextRound;
    private int amountOfDecreaseHPNextRound;

    private boolean canDecreaseHP2NextRound;
    private int amountOfDecreaseHP2NextRound;

    private boolean antiDisarm;
    private boolean antiShock;
    private boolean antiPoison;
    private boolean antiAttackAgainstWeek;
    private boolean canAttackToHeroWhenDead;
    private int damageToHeroWhenDead;

    public int getChangeAPByWeakness() {
        return changeAPByWeakness;
    }

    public void setChangeAPByWeakness(int changeAPByWeakness) {
        this.changeAPByWeakness = changeAPByWeakness;
    }

    public int getChangeHPByWeakness() {
        return changeHPByWeakness;
    }

    public void setChangeHPByWeakness(int changeHPByWeakness) {
        this.changeHPByWeakness = changeHPByWeakness;
    }

    public int getDecreaseHPOfPoisonBuff() {
        return decreaseHPOfPoisonBuff;
    }

    public void setDecreaseHPOfPoisonBuff(int decreaseHPOfPoisonBuff) {
        this.decreaseHPOfPoisonBuff = decreaseHPOfPoisonBuff;
    }

    public int getIncreaseRemainTime() {
        return increaseRemainTime;
    }

    public void setIncreaseRemainTime(int remainTime) {
        this.increaseRemainTime = remainTime;
    }



    public boolean isIncreaseAPPermanent() {
        return increaseAPPermanent;
    }

    public void setIncreaseAPPermanent(boolean increaseAPPermanent) {
        this.increaseAPPermanent = increaseAPPermanent;
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

    public boolean isForMelee() {
        return forMelee;
    }

    public void setForMelee(boolean forMelee) {
        this.forMelee = forMelee;
    }

    public boolean isForRange() {
        return forRange;
    }

    public void setForRange(boolean forRange) {
        this.forRange = forRange;
    }

    public boolean isForHybrid() {
        return forHybrid;
    }

    public void setForHybrid(boolean forHybrid) {
        this.forHybrid = forHybrid;
    }

    public boolean isPareSimorgh() {
        return pareSimorgh;
    }

    public void setPareSimorgh(boolean pareSimorgh) {
        this.pareSimorgh = pareSimorgh;
    }

    public int getMinOfPareSimorgh() {
        return minOfPareSimorgh;
    }

    public void setMinOfPareSimorgh(int minOfPareSimorgh) {
        this.minOfPareSimorgh = minOfPareSimorgh;
    }

    public int getAmountOfIncreaseRangeOfAttack() {
        return amountOfIncreaseRangeOfAttack;
    }

    public void setAmountOfIncreaseRangeOfAttack(int amountOfIncreaseRangeOfAttack) {
        this.amountOfIncreaseRangeOfAttack = amountOfIncreaseRangeOfAttack;
    }

    public boolean isShamshireChini() {
        return isShamshireChini;
    }

    public void setShamshireChini(boolean shamshireChini) {
        isShamshireChini = shamshireChini;
    }

    public boolean isSoulEater() {
        return isSoulEater;
    }

    public void setSoulEater(boolean soulEater) {
        isSoulEater = soulEater;
    }

    public boolean isAssassinationDagger() {
        return isAssassinationDagger;
    }

    public void setAssassinationDagger(boolean assassinationDagger) {
        isAssassinationDagger = assassinationDagger;
    }

    public boolean isAntiHollyBuff() {
        return isAntiHollyBuff;
    }

    public void setAntiHollyBuff(boolean antiHollyBuff) {
        isAntiHollyBuff = antiHollyBuff;
    }

    public boolean isCanDecreaseHpNextRound() {
        return canDecreaseHpNextRound;
    }

    public void setCanDecreaseHpNextRound(boolean canDecreaseHpNextRound) {
        this.canDecreaseHpNextRound = canDecreaseHpNextRound;
    }

    public int getAmountOfDecreaseHPNextRound() {
        return amountOfDecreaseHPNextRound;
    }

    public void setAmountOfDecreaseHPNextRound(int amountOfDecreaseHPNextRound) {
        this.amountOfDecreaseHPNextRound = amountOfDecreaseHPNextRound;
    }

    public boolean isCanDecreaseHP2NextRound() {
        return canDecreaseHP2NextRound;
    }

    public void setCanDecreaseHP2NextRound(boolean canDecreaseHP2NextRound) {
        this.canDecreaseHP2NextRound = canDecreaseHP2NextRound;
    }

    public int getAmountOfDecreaseHP2NextRound() {
        return amountOfDecreaseHP2NextRound;
    }

    public void setAmountOfDecreaseHP2NextRound(int amountOfDecreaseHP2NextRound) {
        this.amountOfDecreaseHP2NextRound = amountOfDecreaseHP2NextRound;
    }

    public boolean isAntiDisarm() {
        return antiDisarm;
    }

    public void setAntiDisarm(boolean antiDisarm) {
        this.antiDisarm = antiDisarm;
    }

    public boolean isAntiShock() {
        return antiShock;
    }

    public void setAntiShock(boolean antiShock) {
        this.antiShock = antiShock;
    }

    public boolean isAntiAttackAgainstWeek() {
        return antiAttackAgainstWeek;
    }

    public void setAntiAttackAgainstWeek(boolean antiAttackAgainstWeek) {
        this.antiAttackAgainstWeek = antiAttackAgainstWeek;
    }

    public boolean isCanAttackToHeroWhenDead() {
        return canAttackToHeroWhenDead;
    }

    public void setCanAttackToHeroWhenDead(boolean canAttackToHeroWhenDead) {
        this.canAttackToHeroWhenDead = canAttackToHeroWhenDead;
    }

    public int getDamageToHeroWhenDead() {
        return damageToHeroWhenDead;
    }

    public void setDamageToHeroWhenDead(int damageToHeroWhenDead) {
        this.damageToHeroWhenDead = damageToHeroWhenDead;
    }

    public boolean isAntiPoison() {
        return antiPoison;
    }

    public void setAntiPoison(boolean antiPoison) {
        this.antiPoison = antiPoison;
    }

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

    public boolean isLocationLimit() {
        return locationLimit;
    }

    public void setLocationLimit(boolean locationLimit) {
        this.locationLimit = locationLimit;
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

    public boolean isOnAttack() {
        return isOnAttack;
    }

    public void setOnAttack(boolean onAttack) {
        isOnAttack = onAttack;
    }

    public boolean isOnDefence() {
        return isOnDefence;
    }

    public void setOnDefence(boolean onDefence) {
        isOnDefence = onDefence;
    }

    public boolean isOnSpawn() {
        return isOnSpawn;
    }

    public void setOnSpawn(boolean onSpawn) {
        isOnSpawn = onSpawn;
    }

    public boolean isPassive() {
        return isPassive;
    }

    public void setPassive(boolean passive) {
        isPassive = passive;
    }

    public boolean isOnDeath() {
        return isOnDeath;
    }

    public void setOnDeath(boolean onDeath) {
        isOnDeath = onDeath;
    }

    public boolean isCombo() {
        return isCombo;
    }

    public void setCombo(boolean combo) {
        isCombo = combo;
    }

    public boolean isOnTurn() {
        return isOnTurn;
    }

    public void setOnTurn(boolean onTurn) {
        this.isOnTurn = onTurn;
    }

    public int getTimeOfIncreaseMana() {
        return timeOfIncreaseMana;
    }

    public void setTimeOfIncreaseMana(int timeOfIncreaseMana) {
        this.timeOfIncreaseMana = timeOfIncreaseMana;
    }

    public int getIsIncreaseManaPermanent() {
        return isIncreaseManaPermanent;
    }

    public void setIsIncreaseManaPermanent(int isIncreaseManaPermanent) {
        this.isIncreaseManaPermanent = isIncreaseManaPermanent;
    }

    public boolean isCellImpact() {
        return isCellImpact;
    }

    public void setCellImpact(boolean cellImpact) {
        isCellImpact = cellImpact;
    }

    public int getChangeHPByPowerBuff() {
        return changeHPByPowerBuff;
    }

    public void setChangeHPByPowerBuff(int changeHPByPowerBuff) {
        this.changeHPByPowerBuff = changeHPByPowerBuff;
    }

    public int getChangeAPByPowerBuff() {
        return changeAPByPowerBuff;
    }

    public void setChangeAPByPowerBuff(int changeAPByPowerBuff) {
        this.changeAPByPowerBuff = changeAPByPowerBuff;
    }
}
