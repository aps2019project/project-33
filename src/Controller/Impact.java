//tavakkol be khoda

package Controller;

import Model.Buffs.*;
import Model.CollectionItem.*;
import Model.Enviroment.Cell;

import Model.Mana;
import Model.Player;

import java.util.ArrayList;
import java.util.Collections;

public class Impact {

    //impact, ghesmate buff

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

    public static void addFieryBuffToCell(Cell cell) {
        //TODO
    }

    //passive boodan ham deghat  konim ke nadare
    public static void addPoisonBuffToCell(int remainTime, boolean isPermanent, boolean isPassive, int decreaseHP, Cell cell) {
        PoisonBuff poisonBuff = new PoisonBuff(remainTime, isPermanent, isPassive, decreaseHP);
        cell.addEffect(poisonBuff);
    }

    public static void addHolyBuffToCell(int remainTime, boolean isPermanent, boolean isPassive, Cell cell) {
        HolyBuff holyBuff = new HolyBuff(remainTime, isPermanent, isPassive, 1);
        cell.addEffect(holyBuff);
    }

    public static void addWeaknessBuffToCell(int remainTime, boolean isPermanent, boolean isPassive, int changeHP, int changePower, Cell cell) {
        WeaknessBuff weaknessBuff = new WeaknessBuff(remainTime, isPermanent, isPassive, changeHP, changePower);
        cell.addEffect(weaknessBuff);
    }

    //todo alan vaghti ye khoone add mishe effectesh, bayad delete she az khodesh ya kheir
    public static void impactBuffInCell(Buff buff, Cell cell) {
        LivingCard livingCard = cell.getLivingCard();
        if (livingCard == null)
            return;
        impactBuffInLivingCard(buff, livingCard);
        cell.getEffects().remove(buff);
    }

    //todo in taghirati ke alan in buff ha ijad mikonan, avvale har round bayad 0 she
    public static void impactBuffInLivingCard(Buff buff, LivingCard livingCard) {
        if (!buff.getIsActive()) return;

        if (buff instanceof HolyBuff)
            livingCard.setShield(livingCard.getShield() + ((HolyBuff) buff).getShieldPower());

        if (buff instanceof PowerBuff) {
            livingCard.changeExtraHP(((PowerBuff) buff).getChangeHP());
            livingCard.changeExtraAP(((PowerBuff) buff).getChangePower());
        }

        if (livingCard.getInformation().isAntiShock()) return;

        if (buff instanceof DisarmBuff && !livingCard.getInformation().isAntiDisarm())
            livingCard.setCanCounterAttack(false);

        if (buff instanceof StunBuff) {
            livingCard.setCanAttack(false);
            livingCard.setCanMove(false);
        }

        if (buff instanceof PoisonBuff && !livingCard.getInformation().isAntiPoison())
            livingCard.changeHP(-((PoisonBuff) buff).getDecreaseHP());

        if (buff instanceof WeaknessBuff) {
            livingCard.changeExtraHP(-((WeaknessBuff) buff).getChangeHP());
            livingCard.changeExtraAP(-((WeaknessBuff) buff).getChangePower());
        }
    }

    //tamam

    public static void damageToEnemy(Battle battle, Card myLivingCard, LivingCard opponentLivingCard, int damage) {
        if (opponentLivingCard.getInformation().isAntiShock()) return;

        if (myLivingCard instanceof LivingCard) {
            LivingCard attacker = (LivingCard) myLivingCard;
            Information information = myLivingCard.getInformation();

            if (opponentLivingCard.getInformation().isAntiAttackAgainstWeek())
                if (attacker.getAP() < opponentLivingCard.getAP())
                    return;
            if (information.isAntiHollyBuff())
                damage += opponentLivingCard.getShield();

            attacker.setCanAttack(false);
            attacker.setCanMove(false);
        }

        opponentLivingCard.handleAttack(battle, damage);
    }

    public static boolean isInRange(ArrayList<Cell> attackCells, LivingCard defender) {
        boolean isCardInRange = false;
        for (Cell cell : attackCells) {
            if (cell.getX() == defender.getPositionRow() && cell.getY() == defender.getPositionColumn())
                isCardInRange = true;
        }
        return isCardInRange;
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

        damageToEnemy(battle, attacker, defender, attacker.getAP());

        if (attacker instanceof Minion && attacker.getInformation().isOnAttack())
            specialAttackOfMinion(battle, (Minion) attacker, defender);

        Impact.counterAttack(battle, defender, attacker);
    }

    public static void counterAttack(Battle battle, LivingCard livingCard, LivingCard opponentCard) {
        ArrayList<Cell> attackCells = AttackArea.getImpactCellsOfCounterAttack(livingCard, battle);
        boolean isInRange = Impact.isInRange(attackCells, opponentCard);
        if (!isInRange || !livingCard.isCanCounterAttack())
            return;
        damageToEnemy(battle, livingCard, opponentCard, livingCard.getAP());
        opponentCard.checkAlive(battle);
    }

    //tamam

    //special e minion

    //todo inam moshkel dare yekam

    public static void increaseRangeOfAttack(LivingCard livingCard, int amount) {
        livingCard.setRangeOfAttack(livingCard.getRangeOfAttack() + amount);
    }

    public static void specialAttackOfMinion(Battle battle, Minion attacker, LivingCard defender) {
        //TODO agar multiple bood hamashun asar midim vagarna yekish
        Information information = attacker.getInformation();

        ArrayList<Cell> attackArea = AttackArea.getImpactCells(attacker, attacker.getCell(), battle);
        if (information.isRandomLivingCard()) {
            Collections.shuffle(attackArea);
            Cell targetCell = attackArea.get(0);
            attackArea.clear();
            attackArea.add(targetCell);
        } else if (!information.isMultipleImpact()) {
            //todo in ja exception mikhore
            System.out.println(attacker.getName());
            if (!attackArea.contains(defender.getCell())) {
                System.out.println("defender isn't in range");
                return;
            }
            attackArea.clear();
            attackArea.add(defender.getCell());
        }

        for (Cell cell : attackArea) {
            LivingCard livingCard = cell.getLivingCard();
            if (livingCard == null)
                continue;
            if (information.isCanHolyBuffAdd()) {
                Impact.addHolyToCard(information.getTimeOfHolyBuff(), information.isHolyBuffPermanent(), false, 1, livingCard);
            }
            if (information.isCanStunBuffAdd()) {
                Impact.addStunToCard(information.getTimeOfStunBuff(), information.isStunBuffPermanent(), false, livingCard);
            }
            if (attacker.getName().equals("PahlevaneFars")) {
                int damage = Impact.pahlevaneFarsChangeDamage(attacker, defender.getID());
                damageToEnemy(battle, attacker, livingCard, damage);
            }
            if (information.isCanDisarmBuffAdd()) {
                Impact.addDisarmToCard(information.getTimeOfDisarmBuff(), information.isDisarmBuffPermanent(), false, livingCard);
            }
            if (information.isCanPowerBuffAdd()) {
                Impact.addPowerBuffToCard(information.getTimeOfPowerBuff(), information.isPowerBuffPermanent(), false, information.getChangeHPByPowerBuff(),
                        information.getChangeAPByPowerBuff(), attacker);
            }
            if (information.isCanDamageToEnemy()) {
                Impact.damageToEnemy(battle, attacker, livingCard, information.getDamageToEnemy());
            }
            if (information.isCanPoisonBuffAdd()) {
                Impact.addPoisonToCard(information.getTimeOfPoisonBuff(), information.isPoisonBuffPermanent(), false, information.getDecreaseHPOfPoisonBuff(), livingCard);
            }
            if (information.isCanDecreaseHpNextRound()) {
                //TODO
            }
            if (information.isCanWeaknessBuffAdd()) {
                Impact.addWeaknessToCard(information.getTimeOfWeaknessBuff(), information.isWeaknessBuffPermanent(), false, information.getChangeHPByWeakness(),
                        information.getChangeAPByWeakness(), livingCard);
            }
            // TODO ashkboos too defend handle she
            if (information.isCanRemoveGoodBuffsOfEnemy()) {
                removeGoodBuffsOfLivingCard(livingCard);
            }
            //TODO siavash tuye death - ashkboos - giv - gorg - palang - gorge sefid
        }
    }

    public static void comboAttack(Battle battle, LivingCard opponentLivingCard, ArrayList<LivingCard> attackers) {
        for (LivingCard attacker : attackers)
            damageToEnemy(battle, attacker, opponentLivingCard, attacker.getAP());

        if (opponentLivingCard.checkAlive(battle))
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
    //minion in ja tamoome

    //spell
    //todo in gharare ba ye zarb bokoshe
    public static void killMinionOfEnemy(Battle battle, Minion minion) {
        minion.handleAttack(battle, minion.getHP() + minion.getShield() + 10);
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

            if (information.isCanDamageToEnemy())
                damageToEnemy(battle, spell, livingCard, information.getDamageToEnemy());
            if (information.isCanPoisonBuffAdd())
                addPoisonToCard(information.getTimeOfPoisonBuff(), information.isPoisonBuffPermanent(), false, 1, livingCard);
            if (information.isCanHolyBuffAdd()) {
                for (int i = 0; i < information.getNumberOfHolyBuff(); i++)
                    addHolyToCard(information.getTimeOfHolyBuff(), information.isHolyBuffPermanent(), false, 1, livingCard);
            }
            if (information.isCanWeaknessBuffAdd())
                addWeaknessToCard(information.getTimeOfWeaknessBuff(), information.isWeaknessBuffPermanent(), false, information.getChangeHPByWeakness(), information.getChangeAPByWeakness(), livingCard);
            if (information.isCanKillMinionOfEnemy())
                killMinionOfEnemy(battle, (Minion) livingCard);
            if (information.isCanStunBuffAdd())
                addStunToCard(information.getTimeOfStunBuff(), information.isStunBuffPermanent(), false, livingCard);
        }
    }

    public static void impactSpellOfHero(Battle battle, Hero hero, Cell cell) {
        ArrayList<Cell> attackArea = AttackArea.getImpactCells(hero, cell, battle);
        Information information = hero.getInformation();
        if (!information.isMultipleImpact()) {
            if (!attackArea.contains(cell)) {
                System.out.println("this cell is not in range");
                return;
            }
            attackArea.clear();
            attackArea.add(cell);
        }

        for (Cell targetCell : attackArea) {
            if (information.isCanAddHolyBuffToCell())
                addHolyBuffToCell(information.getTimeOfHolyBuff(), information.isHolyBuffPermanent(), false, cell);
            LivingCard livingCard = targetCell.getLivingCard();
            if (livingCard == null) continue;
            if (information.isCanPowerBuffAdd())
                addPowerBuffToCard(information.getTimeOfPowerBuff(), information.isPowerBuffPermanent(), false, information.getChangeHPByPowerBuff(), information.getChangeAPByPowerBuff(), livingCard);
            if (information.isCanStunBuffAdd())
                addStunToCard(information.getTimeOfStunBuff(), information.isStunBuffPermanent(), false, livingCard);
            if (information.isCanDisarmBuffAdd())
                addDisarmToCard(information.getTimeOfDisarmBuff(), information.isDisarmBuffPermanent(), false, livingCard);
            if (information.isCanPoisonBuffAdd())
                addPoisonToCard(information.getTimeOfPoisonBuff(), information.isPoisonBuffPermanent(), false, 1, livingCard);
            if (information.isCanDamageToEnemy())
                damageToEnemy(battle, hero, livingCard, information.getDamageToEnemy());
            if (information.isCanRemoveGoodBuffsOfEnemy() && battle.getPlayerOff().getAliveCards().contains(livingCard))
                removeGoodBuffsOfLivingCard(livingCard);
            if (information.isCanRemoveBadBuffsOfOurselves() && battle.getPlayerOn().getAliveCards().contains(livingCard))
                removeBadBuffsOfLivingCard(livingCard);
            if (information.isCanHolyBuffAdd())
                addHolyToCard(information.getTimeOfHolyBuff(), information.isHolyBuffPermanent(), false, 1, livingCard);
        }
    }

    //todo kamane damul moshkel dare
    //todo nefrine marg moshkel dare
    //todo assassination dagger moshkel dare
    //todo ta tahesh joz shamshire chini
    public static void impactItem(Item item, Cell cell, Battle battle) {
        Information information = item.getInformation();

        if (information.isLocationLimit() || information.isUsImpact() || information.isEnemyImpact()) {

            ArrayList<Cell> attackArea = AttackArea.getImpactCells(item, cell, battle);
            if (information.isRandomLivingCard()) {
                Collections.shuffle(attackArea);
                Cell targetCell = attackArea.get(0);
                attackArea.clear();
                attackArea.add(targetCell);
            } else {
                if (!information.isMultipleImpact()) {
                    if (!attackArea.contains(cell)) {
                        System.out.println("this cell is not in range");
                        return;
                    }
                    attackArea.clear();
                    attackArea.add(cell);
                }
            }

            for (Cell targetCell : attackArea) {
                LivingCard livingCard = targetCell.getLivingCard();
                if (livingCard == null) continue;
                if (livingCard.getInformation().isCanDoMeleeAttack() && !information.isForMelee()) continue;
                if (livingCard.getInformation().isCanDoRangedAttack() && !information.isForRange()) continue;
                if (livingCard.getInformation().isCanDoHybridAttack() && !information.isForHybrid()) continue;

                if (information.isCanHolyBuffAdd())
                    for (int i = 0; i < information.getNumberOfHolyBuff(); i++)
                        addHolyToCard(information.getTimeOfHolyBuff(), information.isHolyBuffPermanent(), false,
                                1, livingCard);
                if (information.isCanPowerBuffAdd())
                    addPowerBuffToCard(information.getTimeOfPowerBuff(), information.isPowerBuffPermanent(), false, information.getChangeHPByPowerBuff(), information.getChangeAPByPowerBuff(), livingCard);
                if (information.isCanWeaknessBuffAdd())
                    addWeaknessToCard(information.getTimeOfWeaknessBuff(), information.isWeaknessBuffPermanent(), false, information.getChangeHPByPowerBuff(), information.getChangeAPByWeakness(), livingCard);
            }
        } else {
            if (information.isCanIncreaseMana())
                addManaBuffToPlayer(battle.getPlayerOn(), information.getAmountOfIncreaseMana(), information.getTimeOfIncreaseMana());
        }
    }

    private static void addManaBuffToPlayer(Player playerOn, int amountOfIncreaseMana, int timeOfIncreaseMana) {
        ManaBuff manaBuff = new ManaBuff(amountOfIncreaseMana, timeOfIncreaseMana);
        playerOn.getMana().addManaBuff(manaBuff);
    }

    //payane in bakhsh
}

