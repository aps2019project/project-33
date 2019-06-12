//tavakkol be khoda

package Controller;

import Model.Buffs.*;
import Model.Collection;
import Model.CollectionItem.*;
import Model.Enviroment.Cell;

import Model.Player;

import java.util.ArrayList;
import java.util.Collections;

public class Impact {

    public static void addWeaknessToCard(int remainTime, boolean isPermanent, boolean isPassive, int changeHP, int changePower,
                                         LivingCard livingCard) {
        WeaknessBuff weaknessBuff = new WeaknessBuff(remainTime, isPermanent, isPassive, changeHP, changePower);
        livingCard.addNewBuff(weaknessBuff);
    }

    public static void addDisarmToCard(int remainTime, boolean isPermanent, boolean isPassive, LivingCard livingCard) {
        DisarmBuff disarmBuff = new DisarmBuff(remainTime, isPermanent, isPassive);
        livingCard.addNewBuff(disarmBuff);
    }

    public static void addHolyToCard(int remainTime, boolean isPermanent, boolean isPassive, int shieldPower, LivingCard livingCard) {
        HolyBuff holyBuff = new HolyBuff(remainTime, isPermanent, isPassive, shieldPower);
        livingCard.addNewBuff(holyBuff);
    }

    public static void addStunToCard(int remainTime, boolean isPermanent, boolean isPassive, LivingCard livingCard) {
        StunBuff stunBuff = new StunBuff(remainTime, isPermanent, isPassive);
        livingCard.addNewBuff(stunBuff);
    }

    public static void addPoisonToCard(int remainTime, boolean isPermanent, boolean isPassive, int decreaseHP,
                                       LivingCard livingCard) {
        PoisonBuff PoisonBuff = new PoisonBuff(remainTime, isPermanent, isPassive, decreaseHP);
        livingCard.addNewBuff(PoisonBuff);
    }

    public static void addPowerBuffToCard(int remainTime, boolean isPermanent, boolean isPassive, int changeHP, int changePower, LivingCard livingCard) {
        PowerBuff powerBuff = new PowerBuff(remainTime, isPermanent, isPassive, changeHP, changePower);
        livingCard.addNewBuff(powerBuff);
    }

    //todo ta inja baeide kheili ride bashe

    public static void removeBadBuffsOfLivingCard(LivingCard livingCard) {
        ArrayList<Buff> LivingCardBuffs = livingCard.getEffects();
        int numberOfBuffs = LivingCardBuffs.size();
        // TODO har turn bayad isactiva true shan
        for (int i = numberOfBuffs - 1; i > -1; i--) {
            Buff buff = LivingCardBuffs.get(i);
            if (buff.isPermanent()) {
                buff.setIsActive(false);
                continue;
            }
            if (buff instanceof WeaknessBuff) LivingCardBuffs.remove(i);
            if (buff instanceof PoisonBuff) LivingCardBuffs.remove(i);
            if (buff instanceof StunBuff) LivingCardBuffs.remove(i);
            if (buff instanceof DisarmBuff) LivingCardBuffs.remove(i);
            //TODO holly buffe manfio che ghalati konim
        }
    }

    //TODO
    //tasir dadane passive
    public static void removeGoodBuffsOfLivingCard(LivingCard livingCard) {
        ArrayList<Buff> effects = livingCard.getEffects();
        int numberOfBuffs = effects.size();

        for (int i = numberOfBuffs - 1; i > -1; i--) {
            Buff buff = effects.get(i);
            if (buff.isPermanent()) {
                buff.setIsActive(false);
                continue;
            }
            if (buff instanceof HolyBuff) effects.remove(i);
            if (buff instanceof PowerBuff) effects.remove(i);
        }
    }

    public static void activeBuffs(Battle battle) {
        for (LivingCard livingCard : battle.getPlayerOn().getAliveCards()) {
            for (Buff buff : livingCard.getEffects()) {
                buff.setIsActive(true);
            }
        }
        for (LivingCard livingCard : battle.getPlayerOff().getAliveCards()) {
            for (Buff buff : livingCard.getEffects()) {
                buff.setIsActive(true);
            }
        }
    }


    public static void damageToEnemy(Battle battle, Player player, LivingCard myLivingCard, LivingCard opponentLivingCard, int damage) {
        if (opponentLivingCard.getInformation().isAntiShock()) return;

        if (opponentLivingCard.getInformation().isAntiAttackAgainstWeek()) {
            if (myLivingCard.getAP() < myLivingCard.getAP())
                return;
        }

        if (myLivingCard instanceof Hero && opponentLivingCard instanceof Hero) {
            if (((Hero) myLivingCard).isHaveAssassinationDagger())
                damage += 8;
        }

        if (player.isCanAddPoisonWhileAttacking())
            addPoisonToCard(1, false, false, 1, opponentLivingCard);
        if (player.isCanAddStunWhileAttacking())
            addStunToCard(1, false, false, opponentLivingCard);
        if (myLivingCard.isHaveSoulEater())
            myLivingCard.increaseHP(2);
        if (myLivingCard.isHaveShamshireChini())
            damage += 5;

        if (myLivingCard instanceof Minion) {

            Minion minion = (Minion) myLivingCard;
            Information information = minion.getInformation();

            if (information.isCanStunBuffAdd())
                addStunToCard(information.getTimeOfStunBuff(), false, false, opponentLivingCard);

            if (information.isAntiHollyBuff())
                damage += opponentLivingCard.getShield();

            if (opponentLivingCard instanceof Minion) {
                if (information.isCanDecreaseHpNextRound()) {
                    opponentLivingCard.setAP(opponentLivingCard.getDecreasHPNextRound() +
                            information.getAmountOfDecreaseHPNextRound());
                }
                if (information.isCanDecreaseHP2NextRound()) {
                    opponentLivingCard.setDecreaseHP2NextRound(opponentLivingCard.getDecreaseHP2NextRound() +
                            information.getAmountOfDecreaseHP2NextRound());
                }
            }
        }
        opponentLivingCard.handleAttack(battle, damage);

        attacker.setCanAttack(false);
        attacker.setCanMove(false);

        if (checkAlive(battle, defender))
            Impact.counterAttack(battle, defender, attacker);


        return;
    }

    public static void addFieryBuffToCell(Cell cell) {
        //TODO
        //deghat konim aslan fiery buff nadarim
    }

    //deghat konim nabayad chize daemi be cell ha ezafe she
    //passive boodan ham deghat  konim ke nadare
    public static void addPoisonBuffToCell(int remainTime, boolean isPermanent, boolean isPassive, int decreaseHP, Cell cell) {
        PoisonBuff poisonBuff = new PoisonBuff(remainTime, isPermanent, isPassive, decreaseHP);
        cell.addEffect(poisonBuff);
    }

    public static void addWeaknessBuffToCell(int remainTime, boolean isPermanent, boolean isPassive, int changeHP, int changePower, Cell cell) {
        WeaknessBuff weaknessBuff = new WeaknessBuff(remainTime, isPermanent, isPassive, changeHP, changePower);
        cell.addEffect(weaknessBuff);
    }

    public static void killMinionOfEnemy(Minion minion) {
        minion.kill();
    }

    public static void killOurMinionAndHealHero(Hero hero, Minion minion) {
        hero.setHP(hero.getHP() + minion.getHP());
        minion.kill();
    }

    public static void increaseRangeOfAttack(LivingCard livingCard, int amount) {
        livingCard.setRangeOfAttack(livingCard.getRangeOfAttack() + amount);
    }

    public static void doubleHP(Hero hero) {
        hero.setHP(hero.getHP() * 2);
    }

    public static boolean isInRange(ArrayList<Cell> attackCells, LivingCard defender) {
        boolean isCardInRange = false;
        for (Cell cell : attackCells) {
            if (cell.getX() == defender.getPositionRow() && cell.getY() == defender.getPositionColumn())
                isCardInRange = true;
        }
        return isCardInRange;
    }

    public static boolean checkAlive(Battle battle, LivingCard checkedLivingCard) {

        if (checkedLivingCard.getHP() <= 0) {
            if (battle.getPlayerOn().getAliveCards().contains(checkedLivingCard)) {
                battle.getPlayerOn().getGraveYard().addCard(checkedLivingCard);
            } else {
                battle.getPlayerOff().getGraveYard().addCard(checkedLivingCard);
            }
            battle.removeAliveCard(checkedLivingCard);
            battle.handleFlags();
            if (battle.getSelectedCard().getID().equals(checkedLivingCard.getID()))
                battle.removeSelectedCard();
            checkedLivingCard.kill();
            return false;
        }
        return true;
    }

    public static void counterAttack(Battle battle, LivingCard livingCard, LivingCard opponentCard) {
        ArrayList<Cell> attackCells = AttackArea.getImpactCellsOfCounterAttack(livingCard, battle);
        boolean isInRange = Impact.isInRange(attackCells, opponentCard);
        if (!isInRange || !livingCard.isCanCounterAttack())
            return;
        damageToEnemy(battle, battle.getPlayerOff(), livingCard, opponentCard, livingCard.getAP());
        checkAlive(battle, opponentCard);
    }

    public static void specialAttackOfMinion(Battle battle, Minion attacker, LivingCard defender) {
        //TODO agar multiple bood hamashun asar mmidim vagarna yekish
        Information information = attacker.getInformation();
        ArrayList<Cell> attackArea = AttackArea.getImpactCells(attacker, attacker.getCell(), battle);
        if(!information.isMultipleImpact()){
            if(!attackArea.contains(defender.getCell())){
                System.out.println("defender isn't in range");
                return;
            }
            attackArea.clear();
            attackArea.add(defender.getCell());
        }
        for(Cell cell : attackArea){
            LivingCard livingCard = cell.getLivingCard();
            if(livingCard == null)
                continue;
            if(information.isCanHolyBuffAdd()){
                Impact.addHolyToCard(information.getTimeOfHolyBuff(), information.isHolyBuffPermanent(), false, 1, livingCard);
            }
            if(information.isCanStunBuffAdd()){
                Impact.addStunToCard(information.getTimeOfStunBuff(), information.isStunBuffPermanent(), false, livingCard);
            }
            if(attacker.getName().equals("PahlevaneFars")){
                int damage = Impact.pahlevaneFarsChangeDamage(attacker, defender.getID());
                damageToEnemy(battle, battle.getPlayerOn(), attacker, livingCard, damage);
            }
            if(information.isCanDisarmBuffAdd()){
                Impact.addDisarmToCard(information.getTimeOfDisarmBuff(), information.isDisarmBuffPermanent(), false, livingCard);
            }
            if(information.isCanPowerBuffAdd()){
                Impact.addPowerBuffToCard(information.getTimeOfPowerBuff(), information.isPowerBuffPermanent(), false, information.getChangeHPByPowerBuff(),
                        information.getChangeAPByPowerBuff(), attacker);
            }
            if(information.isCanDamageToEnemy()){
                Impact.damageToEnemy(battle, battle.getPlayerOn(), attacker, livingCard, information.getDamageToEnemy());
            }
            if(information.isCanPoisonBuffAdd()){
                Impact.addPoisonToCard(information.getTimeOfPoisonBuff(), information.isPoisonBuffPermanent(), false, information.getDecreaseHPOfPoisonBuff(), livingCard);
            }
            if(information.isCanDecreaseHpNextRound()){
                //TODO
            }
            if(information.isCanWeaknessBuffAdd()){
                Impact.addWeaknessToCard(information.getTimeOfWeaknessBuff(), information.isWeaknessBuffPermanent(), false, information.getChangeHPByWeakness(),
                        information.getChangeAPByWeakness(), livingCard);
            }
            // TODO ashkboos too defend handle she
            if(information.isCanRemoveGoodBuffsOfEnemy()){
                removeGoodBuffsOfLivingCard(livingCard);
            }
            //TODO siavash tuye death - ashkboos - giv - gorg - palang - gorge sefid
        }
    }


    public static void attack(Battle battle, LivingCard attacker, LivingCard defender) {
        ArrayList<Cell> attackCells = AttackArea.getImpactCellsOfAttack(attacker, battle);
        boolean isInRange = Impact.isInRange(attackCells, defender);
        if (!isInRange) {
            System.out.println("opponent force is unavailable for attack");
            return;
        }
        if (!attacker.isCanAttack()) {
            System.out.println("attacker can't attack");
            return;
        }

        if (attacker instanceof Minion && attacker.getInformation().isOnAttack())
            specialAttackOfMinion(battle, (Minion) attacker, defender);

        damageToEnemy(battle, battle.getPlayerOn(), attacker, defender, attacker.getAP());

        if (checkAlive(battle, defender))
            Impact.counterAttack(battle, defender, attacker);
    }

    // mahdude hamle o ina check she
    public static void comboAttack(Battle battle, LivingCard opponentLivingCard, ArrayList<LivingCard> attackers) {
        for (LivingCard attacker : attackers)
            damageToEnemy(battle, battle.getPlayerOn(), attacker, opponentLivingCard, attacker.getAP());

        if (checkAlive(battle, opponentLivingCard))
            Impact.counterAttack(battle, opponentLivingCard, attackers.get(0));
    }

    public static int pahlevaneFarsChangeDamage(Minion minion, String defenderID) {
        int changeDamage = 0;
        for (LivingCard livingCard : minion.getAttackedLivingCards()) {
            if (livingCard.getID().equals(defenderID)) {
                changeDamage += 5;
            }
        }
        return changeDamage;
    }

    public static void impactSpell(Battle battle, Spell spell, Cell cell) {
        Information information = spell.getInformation();
        ArrayList<Cell> attackArea = AttackArea.getImpactCells(spell, cell, battle);
        if (!spell.getInformation().isMultipleImpact()) {
            if (!attackArea.contains(cell)) {
                System.out.println("cell is not in range");
                return;
            }
            attackArea.clear();
            attackArea.add(cell);
        }

        for (Cell targetCell : attackArea) {
            // karaye cell
            if (information.isCanAddPoisonBuffToCell())
                addPoisonBuffToCell(information.getTimeOfPoisonBuff(), information.isPoisonBuffPermanent(), false, 1, cell);
            // karaye living card
            LivingCard livingCard = targetCell.getLivingCard();
            if (livingCard == null) continue;
            if (information.isCanDisarmBuffAdd())
                addDisarmToCard(information.getTimeOfDisarmBuff(), information.isDisarmBuffPermanent(), false, livingCard);
            if (information.isCanRemoveBadBuffsOfOurselves() && battle.getPlayerOn().getAliveCards().contains(livingCard))
                removeBadBuffsOfLivingCard(livingCard);
            if (information.isCanRemoveGoodBuffsOfEnemy() && battle.getPlayerOff().getAliveCards().contains(livingCard))
                removeGoodBuffsOfLivingCard(livingCard);
            if (information.isCanPowerBuffAdd())
                addPowerBuffToCard(information.getTimeOfPowerBuff(), information.isPowerBuffPermanent(), false, information.getChangeHPByPowerBuff(), information.getChangeAPByPowerBuff(), livingCard);

            //todo
//            if (information.isCanDamageToEnemy())
//                damageToEnemy(battle, );
            if (information.isCanPoisonBuffAdd())
                addPoisonToCard(information.getTimeOfPoisonBuff(), information.isPoisonBuffPermanent(), false, 1, livingCard);
            if (information.isCanHolyBuffAdd()) {
                for (int i = 0; i < information.getNumberOfHolyBuff(); i++)
                    addHolyToCard(information.getTimeOfHolyBuff(), information.isHolyBuffPermanent(), false, 1, livingCard );
            }
            if (information.isCanWeaknessBuffAdd())
                addWeaknessToCard(information.getTimeOfWeaknessBuff(), information.isWeaknessBuffPermanent(), false, information.getChangeHPByWeakness(), information.getChangeAPByWeakness(), livingCard);
            if (information.isCanKillMinionOfEnemy())
                killMinionOfEnemy((Minion)livingCard);
            if (information.isCanStunBuffAdd())
                addStunToCard(information.getTimeOfStunBuff(), information.isStunBuffPermanent(), false, livingCard);
        }
    }

    //TODO kollan az in ja be paein, kirie
    public static void impactItem(Item item, Cell cell, Battle battle) {
        Information information = item.getInformation();

        if (information.isLocationLimit()) {

            ArrayList<Cell> impactArea = AttackArea.getImpactCellsOfItem(item, battle);
            if (!impactArea.contains(cell)) {
                System.out.println("Item can't impact here !! please choose right coordination");
                return;
            }

            LivingCard livingCard = cell.getLivingCard();

            if (information.isCanIncreaseRangeOfAttack())
                increaseRangeOfAttack(livingCard, information.getAmountOfIncreaseRangeOfAttack());
            if (information.isCanHolyBuffAdd()) {
                for (int i = 0; i < information.getNumberOfHolyBuff(); i++)
                    addHolyToCard(information.getTimeOfHolyBuff(), information.isHolyBuffPermanent(), information.isHolyBuffPassive(),
                            1, livingCard);
            }
            if (information.isCanIncreaseHPOfLivingCard())
                increaseHPOfLivingHero(livingCard, information.getAmountOfIncreaseHPOfLivingCard());
            if (information.isCanIncreaseAP())
                increaseAP(livingCard, information.getAmountOfIncreaseAP());
            if (information.isCanAddNefrineMarg())
                addNefrineMarg((Minion) livingCard);
            if (information.isTerrorHood())
                terrorHood(battle.getPlayerOn());
            if (information.isPareSimorgh())
                pareSimorgh((Hero) livingCard, information);
            if (information.isShamshireChini())
                shamshireChini(livingCard);
            if (information.isSoulEater())
                soulEater(livingCard);
            if (information.isAssassinationDagger())
                assassinationDaggaer((Hero) livingCard);
        } else {
            if (information.isCanIncreaseManaAfter3Rounds())
                increaseManaAfter3Round(battle.getPlayerOn());
            if (information.isCanIncreaseMana())
                increaseMana(battle.getPlayerOn(), information.getAmountOfIncreaseMana());
            if (information.isCanKillHeroOfEnemyAfterRounds())
                killHeroOfEnemyAfterRounds(information.getNumberOfRoundsNeededForKillHeroOfEnemy(), battle);
            if (information.isAddGhosleTamid())
                addGhosleTamid(battle, information.getTimeOfGhosleTamid());
            if (information.isPoisonousDagger())
                poisonousDagger(battle);
            if (information.isShockHammer())
                shockHammer(battle);
        }
    }

    private static void assassinationDaggaer(Hero livingCard) {
        livingCard.setHaveAssassinationDagger(true);
    }

    private static void soulEater(LivingCard livingCard) {
        livingCard.setHaveSoulEater(true);
    }

    private static void shamshireChini(LivingCard livingCard) {
        livingCard.setHaveShamshireChini(true);
    }

    private static void pareSimorgh(Hero hero, Information information) {
        hero.setHavePareSimorgh(information.isPareSimorgh());
        hero.setMinOfPareSimorgh(information.getMinOfPareSimorgh());
    }

    private static void shockHammer(Battle battle) {
        battle.getPlayerOn().setCanAddStunWhileAttacking(true);
    }

    private static void poisonousDagger(Battle battle) {
        battle.getPlayerOn().setCanAddPoisonWhileAttacking(true);
    }

    private static void terrorHood(Player player) {
        player.setHaveTerrorHood(true);
    }

    private static void addGhosleTamid(Battle battle, int timeOfGhosleTamid) {
        battle.getPlayerOn().setHaveGhosleTamid(true);
        battle.getPlayerOn().setTimeOfGhosleTamid(timeOfGhosleTamid);
    }

    private static void killHeroOfEnemyAfterRounds(int numberOfRoundsNeededForKillHeroOfEnemy, Battle battle) {
        battle.getPlayerOff().getHero().setDeadAfterRounds(numberOfRoundsNeededForKillHeroOfEnemy);
        battle.getPlayerOff().getHero().setHaveKingKiller(true);
    }

    private static void addNefrineMarg(Minion minion) {
        minion.setHaveNefrineMarg(true);
    }

    private static void increaseMana(Player playerOn, int amountOfIncreaseMana) {
        playerOn.getMana().increaseMaximumMana(amountOfIncreaseMana);
    }

    private static void increaseAP(LivingCard livingCard, int amountOfIncreaseAP) {
        livingCard.increaseAP(amountOfIncreaseAP);
    }

    private static void increaseHPOfLivingHero(LivingCard livingCard, int amount) {
        livingCard.increaseHP(amount);
    }
    //payane in bakhsh

    public static void impactTerrorHood(Cell cell, Battle battle) {
        ArrayList<Cell> neighbors = AttackArea.getNeighbors(cell, battle);
        for (Cell cell1 : neighbors) {
            if (battle.getPlayerOff().getAliveCards().contains(cell1.getLivingCard()))
                addWeaknessToCard(1, false, false, 2, 0, cell1.getLivingCard());
        }
    }

    //payane in bakhsh

    //impact e buff

    public static void impactBuffInCell(Buff buff, Cell cell) {
        LivingCard livingCard = cell.getLivingCard();
        if (livingCard == null)
            return;
        impactBuffInLivingCard(buff, livingCard);
    }

    public static void impactBuffInLivingCard(Buff buff, LivingCard livingCard) {
        if (!buff.getIsActive()) return;
        if (buff instanceof HolyBuff)
            livingCard.setShield(livingCard.getShield() + ((HolyBuff) buff).getShieldPower());
        if (buff instanceof PowerBuff) {
            livingCard.increaseHP(((PowerBuff) buff).getChangeHP());
            livingCard.increaseAP(((PowerBuff) buff).getChangePower());
        }

        if (livingCard.getInformation().isAntiShock()) return;

        if (buff instanceof DisarmBuff && !livingCard.getInformation().isAntiDisarm()) {
            livingCard.setCanCounterAttack(false);
        }
        if (buff instanceof StunBuff) {
            livingCard.setCanAttack(false);
            livingCard.setCanMove(false);
        }
        if (buff instanceof PoisonBuff && !livingCard.getInformation().isAntiPoison()) {
            livingCard.increaseAP(-((PoisonBuff) buff).getDecreaseHP());
        }
        if (buff instanceof WeaknessBuff) {
            livingCard.increaseAP(-((WeaknessBuff) buff).getChangeHP());
            livingCard.increaseAP(-((WeaknessBuff) buff).getChangePower());
        }
    }

    // special of minion

    public static void impactGhooleBozorg(Minion minion) {
        if (!minion.getInformation().isGhooleBozorg()) return;
        ArrayList<Cell> impactCells = AttackArea.getNeighbors(minion.getCell(), minion.getBattle());
        for (Cell cell : impactCells) {
            LivingCard livingCard = cell.getLivingCard();
            if (livingCard == null) continue;
            if (livingCard instanceof Hero) continue;
            livingCard.handleAttack(minion.getBattle(), 2);
        }
    }

    public static void impactNefrineMarg(Minion minion) {
        if (!minion.getInformation().isCanAddNefrineMarg()) return;

    }

    public static void damageToHeroWhenDead(Minion minion) {
        Player opponent = minion.getBattle().getPlayerOn();
        if (!opponent.getAliveCards().contains(minion)) opponent = minion.getBattle().getPlayerOff();
        opponent.getHero().handleAttack(minion.getBattle(), minion.getInformation().getDamageToHeroWhenDead());
    }

}

