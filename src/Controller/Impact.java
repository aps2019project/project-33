//tavakkol be khoda

package Controller;

import Model.Buffs.*;
import Model.CollectionItem.*;
import Model.Enviroment.Cell;

import Model.Player;

import java.util.ArrayList;
import java.util.Collections;

public class Impact {

    public static void addWeaknessToCard(int remainTime, boolean isPermanent, boolean isPassive, int changeHP, int changePower,
                                         LivingCard livingCard) {
        WeaknessBuff weaknessBuff = new WeaknessBuff(remainTime, false, false, changeHP, changePower);
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

    public static void removeBadBuffsOfLivingCard(LivingCard livingCard) {
        ArrayList<Buff> effects = livingCard.getEffects();
        int numberOfBuffs = effects.size();

        for (int i = numberOfBuffs - 1; i > -1; i--) {
            Buff buff = effects.get(i);
            if (buff.isPassive()) {
                buff.setIsActive(false);
                continue;
            }
            if (buff instanceof WeaknessBuff) effects.remove(i);
            if (buff instanceof PoisonBuff) effects.remove(i);
            if (buff instanceof StunBuff) effects.remove(i);
            if (buff instanceof DisarmBuff) effects.remove(i);
            if (buff instanceof HolyBuff){
                if(((HolyBuff)buff).getShieldPower() < 0)
                    effects.remove(i);
            }
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

    //TODO
    //tasir dadane passive
    public static void removeGoodBuffsOfLivingCard(LivingCard livingCard) {
        ArrayList<Buff> effects = livingCard.getEffects();
        int numberOfBuffs = effects.size();

        for (int i = numberOfBuffs - 1; i > -1; i--) {
            Buff buff = effects.get(i);
            if (buff instanceof HolyBuff) {
                if(((HolyBuff)buff).getShieldPower() > 0)
                    effects.remove(i);
            }
            if (buff instanceof PowerBuff) effects.remove(i);
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
        return;
    }

    public static void addFieryBuffToCell(Cell cell) {
        //TODO
        //deghat konim aslan fiery buff nadarim
    }

    //deghat konim nabayad chize daemi be cell ha ezafe she
    //passive boodan ham deghat  konim ke nadare
    public static void addPoisonBuffToCell(int remainTime, int decreaseHP, Cell cell) {
        PoisonBuff poisonBuff = new PoisonBuff(remainTime, false, false, decreaseHP);
        cell.addEffect(poisonBuff);
    }

    public static void addWeaknessBuffToCell(int remainTime, int changeHP, int changePower, Cell cell) {
        WeaknessBuff weaknessBuff = new WeaknessBuff(remainTime, false, false, changeHP, changePower);
        cell.addEffect(weaknessBuff);
    }

    public static void killMinionOfEnemy(Minion minion) {
        minion.kill();
    }

    public static void killOurMinionAndHealHero(Hero hero, Minion minion) {
        hero.setHP(hero.getHP() + minion.getHP());
        minion.kill();
    }

    public static void specialAttackOfGhooleBozorg(Battle battle, Minion bigGiant, int bigGiantDamage) {
        int numberOfImpactedCells = 8;
        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, 1, 1, 1, 0, -1, -1, -1};
        for (int i = 0; i < numberOfImpactedCells; i++) {
            int newX = bigGiant.getCell().getX() + dx[i], newY = bigGiant.getCell().getY() + dy[i];
            Cell cell = bigGiant.getBattle().getMap().getCellByCoordination(newX, newY);
            if (cell == null) continue;
            LivingCard livingCard = cell.getLivingCard();
            if (livingCard == null) continue;
            if (!(livingCard instanceof Minion)) continue;
            livingCard.handleAttack(battle, bigGiantDamage);
        }
    }

    //wa
    public static void spellOfZahhak(Hero zahhak) {
        int numberOfImpactedCells = 8;
        ArrayList<Cell> emptyCells = new ArrayList<>();
        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, 1, 1, 1, 0, -1, -1, -1};
        for (int i = 0; i < numberOfImpactedCells; i++) {
            int newX = zahhak.getCell().getX() + dx[i], newY = zahhak.getCell().getY() + dy[i];
            Cell cell = zahhak.getBattle().getMap().getCellByCoordination(newX, newY);
            if (cell == null) continue;
            if (cell.getLivingCard() != null) continue;
            emptyCells.add(cell);
        }
        Collections.shuffle(emptyCells);
        int numberOfNeededCells = Integer.min(2, emptyCells.size());
        for (int i = 0; i < numberOfNeededCells; i++) {
            zahhak.getBattle().addMareBozorg();
        }
    }

    //im not sure
    public static void increaseRangeOfAttack(LivingCard livingCard, int amount) {
        livingCard.setRangeOfAttack(livingCard.getRangeOfAttack() + amount);
    }

    public static void doubleHP(Hero hero) {
        hero.setHP(hero.getHP() * 2);
    }

    //daghighan chio ezafe mikone ?
    public static void increaseManaAfter3Round(Player player) {
        player.getMana().increaseChangeManaByItem();
    }

    //public static void attackForce(LivingCard ourCard, LivingCard enemyCard){}

    public static boolean isInRange(ArrayList<Cell> attackCells, LivingCard defender) {
        boolean isCardInRange = false;
        for (Cell cell : attackCells) {
            if (cell.getX() == defender.getPositionRow() && cell.getY() == defender.getPositionColumn())
                isCardInRange = true;
        }
        return isCardInRange;
    }

    //TODO -> done
    //in inja chi kara mikone ?! karaei ke bayad vase carte morde anjam she kollan
    //add to grave yard -> done
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
            return false;
        }
        return true;
    }

    //TODO -> done
    //bayad az damage enemy estefade she, chon momkene option dashte bashe taraf -> done
    //az in item ha va ...
    public static void counterAttack(Battle battle, LivingCard livingCard, LivingCard opponentCard) {
        //tabe sho dorost zaadam ? baraye melee o ranged o hybrid
        ArrayList<Cell> attackCells = AttackArea.getImpactCellsOfAttack(livingCard, battle);
        boolean isInRange = Impact.isInRange(attackCells, opponentCard);
        if (!isInRange || !livingCard.isCanCounterAttack()) {
            return;
        }
        damageToEnemy(battle, battle.getPlayerOff(), livingCard, opponentCard, livingCard.getAP());
        checkAlive(battle, opponentCard);
    }

    //counter attackam bayad haminja seda she
    //mana nemikhad in ?
    public static int pahlevaneFarsChangeDamage(Minion minion, String defenderID){
        int changeDamage = 0;
        for(LivingCard livingCard : minion.getAttackedLivingCards()){
            if(livingCard.getID().equals(defenderID)){
                changeDamage += 5;
            }
        }
        return changeDamage;
    }

    public static void specialAttackOfMinion(Battle battle, Minion minion, LivingCard defender){
        if(minion.getName().equals("PahlevaneFars")){
            int changeDamageForMinion = pahlevaneFarsChangeDamage(minion, defender.getID());
            int damage = minion.getAP() + changeDamageForMinion;
            damageToEnemy(battle, battle.getPlayerOn(), (LivingCard)minion, defender, damage);
            minion.addAttackedLivingCard(defender);
        }
        if(minion.getName().equals("JasoseTorani")){
            Impact.addDisarmToCard(1, false, false, defender);
            Impact.addPoisonToCard(4, false, false, 1, defender);
        }
        if(minion.getName().equals("MareSami")){
            Impact.addPoisonToCard(3, false, false, 1, defender);
        }
        if(minion.getName().equals("GhooleDoSar")){
            Impact.removeGoodBuffsOfLivingCard(defender);
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

        //changeHP e esme ap ?
        //too getter ba taghirat jam miazanim

        if(attacker instanceof Minion){
            specialAttackOfMinion(battle, (Minion)attacker, defender);
        }

        damageToEnemy(battle, battle.getPlayerOn(), attacker, defender, attacker.getAP());

        attacker.setCanAttack(false);
        attacker.setCanMove(false);

        if (checkAlive(battle, defender))
            Impact.counterAttack(battle, defender, attacker);

    }

    // mahdude hamle o ina check she
    public static void comboAttack(Battle battle, LivingCard opponentLivingCard, ArrayList<LivingCard> attackers) {
        int[] numberOfComboers = new int[4];
        for(int i = 0; i < 4; i++)
            numberOfComboers[i] = 0;
        String[] comboerNames = {"SepahSalareFars", "ShahzadeTorani", "ShahGhool", "ArzhangeDiv"};
        for(LivingCard livingCard : attackers){
            boolean find = false;
            for(int i = 0; i < 4; i++){
                if(comboerNames[i].equals(livingCard.getName())){
                    numberOfComboers[i]++;
                    find = true;
                }
                if(!find){
                    System.out.println("these cards cant combo attack!");
                    return;
                }
            }
        }
        for(LivingCard livingCard : attackers){
            boolean canChangeDamage = false;
            int index = 0;
            if(livingCard.getName().equals(comboerNames[0]))
                canChangeDamage = true;
            if(livingCard.getName().equals(comboerNames[1])){
                canChangeDamage = true;
                index = 1;
            }
            if(canChangeDamage){
                int damage = livingCard.getAP() + 4 * (numberOfComboers[index] - 1);
                damageToEnemy(battle, battle.getPlayerOn(), livingCard, opponentLivingCard, damage);
            }
        }
        Impact.addDisarmToCard(numberOfComboers[2], false, false, opponentLivingCard);
        Impact.addWeaknessToCard(10, true, false, 0, 3 * (numberOfComboers[3] - 1), opponentLivingCard);
    }

    //in tike baraye special power minion e
    //manash berese
    public static void specialPower(Minion minion, int x, int y) {

    }

    //payane in bakhsh

    //simorgh chie
    //TODO -> done
    //Esm ha cammle case nistan
    public static void impactSpellOfHero(Battle battle, Hero hero, Cell cell) {
        if (hero.getName().equals("DiveSefid")) {
            Impact.addPowerBuffToCard(100, true, false, 0, 4, hero);
        }
        if (hero.getName().equals("Ezhdaha")) {
            LivingCard livingCard = cell.getLivingCard();
            if (livingCard == null) {
                System.out.println("there isnt living card here");
                return;
            }
            //daemie ?
            Impact.addDisarmToCard(10, true, false, livingCard);
        }
        if (hero.getName().equals("Rakhsh")) {
            LivingCard livingCard = cell.getLivingCard();
            if (livingCard == null) {
                System.out.println("there isnt living card here");
                return;
            }
            Impact.addStunToCard(1, false, false, livingCard);
        }
        if (hero.getName().equals("Zahhak")) {
            Impact.spellOfZahhak(hero);
        }
        if (hero.getName().equals("Kaveh")) {
            LivingCard livingCard = cell.getLivingCard();
            if (livingCard == null) {
                System.out.println("there isnt living card here");
                return;
            }
            Impact.addHolyToCard(3, false, false, 1, livingCard);
        }
        if (hero.getName().equals("Arash")) {
            Cell heroCell = battle.getMap().getCellByCoordination(hero.getPositionRow(), hero.getPositionColumn());
            ArrayList<Cell> attackCells = AttackArea.getCellsOfRow(heroCell, battle);
            for (Cell attackCell : attackCells) {
                LivingCard livingCard = attackCell.getLivingCard();
                if (livingCard == null)
                    continue;
                //handle attacko ok kon -> checkAlive o changeHP
                livingCard.handleAttack(battle, 4);
            }
        }
        if (hero.getName().equals("Afsaneh")) {
            LivingCard livingCard = cell.getLivingCard();
            if (livingCard == null) {
                System.out.println("there isnt living card here");
                return;
            }
            for (LivingCard playerOffLivingCard : battle.getPlayerOff().getAliveCards()) {
                if (playerOffLivingCard.getID().equals(livingCard.getID())) {
                    Impact.removeGoodBuffsOfLivingCard(livingCard);
                }
            }
        }
        if (hero.getName().equals("Esfandiar")) {
            Impact.addHolyToCard(10, true, true, 1, hero);
        }
    }

    //in tike baraye spell hast

    //TODO
    //Arraylist contain dare mitooni begi aya toosh felan object hast ya na, lazem nis for bezani !

    //TODO
    //age avale kar tamame khune haye khalio delete mikardim kar kheili rahat tar mishod
    //alan tabe shode 200 khat va ehtemal bugesh ziade :(
    public static void impactSpell(Spell spell, Cell cell, Battle battle) {
        LivingCard livingCard = cell.getLivingCard();
        ArrayList<Cell> impactCells = AttackArea.getImpactCells(spell, cell, battle);
        Information information = spell.getInformation();
        if (!spell.getInformation().isMultipleImpact()) {
            if (livingCard == null) {
                System.out.println("there isn't living card");
                return;
            }
            boolean inRange = false;
            for (Cell impactCell : impactCells)
                if (impactCell.getY() == cell.getY() && impactCell.getX() == cell.getX())
                    inRange = true;
            if (!inRange) {
                System.out.println("cell isn't in range");
                return;
            }
            impactCells = new ArrayList<>();
            impactCells.add(cell);
        }
        if (spell.getInformation().isCanDisarmBuffAdd()) {
            for (Cell impactCell : impactCells) {
                LivingCard cellLivingCard = impactCell.getLivingCard();
                if (cellLivingCard == null)
                    continue;
                int disarmTime = spell.getInformation().getTimeOfDisarmBuff();
                boolean isPermanent = spell.getInformation().isDisarmBuffPermanent();
                Impact.addDisarmToCard(disarmTime, isPermanent, false, cellLivingCard);
            }
        }
        if (spell.getInformation().isCanRemoveGoodBuffsOfEnemy()) {
            for (Cell impactCell : impactCells) {
                LivingCard cellLivingCard = impactCell.getLivingCard();
                if (cellLivingCard == null)
                    continue;
                boolean isLivingCardOurs = battle.getPlayerOn().getAliveCards().contains(cellLivingCard);
                if (isLivingCardOurs) {
                    Impact.removeBadBuffsOfLivingCard(cellLivingCard);
                } else {
                    Impact.removeGoodBuffsOfLivingCard(cellLivingCard);
                }
            }
        }
        if (spell.getInformation().isCanDamageToEnemy()) {
            for (Cell impactCell : impactCells) {
                LivingCard cellLivingCard = impactCell.getLivingCard();
                if (cellLivingCard == null)
                    continue;
                cellLivingCard.handleAttack(battle, spell.getInformation().getDamageToEnemy());
            }
        }
        if (spell.getInformation().isCanIncreaseAP()) {
            for (Cell impactCell : impactCells) {
                LivingCard cellLivingCard = impactCell.getLivingCard();
                if (cellLivingCard == null)
                    continue;
                //TODO
                //in esme moteghayere ok e?
                int remainTime = information.getIncreaseRemainTime();
                int changePower = information.getAmountOfIncreaseAP();
                boolean isPermanent = information.isIncreaseAPPermanent();
                Impact.addPowerBuffToCard(remainTime, isPermanent, false, 0, changePower, cellLivingCard);
            }
        }
        if (information.isCanAddPoisonBuffToCell()) {
            for (Cell impactCell : impactCells) {
                int remainTime = information.getTimeOfAddPoisonBuffToCell();
                int decreaseHP = information.getDecreaseHpOfPoisonBuffOfCell();
                Impact.addPoisonBuffToCell(remainTime, decreaseHP, impactCell);
            }
        }
        if (information.isCanPoisonBuffAdd()) {
            for (Cell impactCell : impactCells) {
                LivingCard cellLivingCard = impactCell.getLivingCard();
                if (cellLivingCard == null)
                    continue;
                int remainTime = information.getTimeOfPoisonBuff();
                boolean isPermanent = information.isPoisonBuffPermanent();
                int decreaseHP = information.getDecreaseHPOfPoisonBuff();
                Impact.addPoisonToCard(remainTime, isPermanent, false, decreaseHP, cellLivingCard);

            }
        }
        //havaset bashe ja nandakhte basham chizio 2 ta azina bood yekisho pak kardam
        if (information.isCanWeaknessBuffAdd()) {
            for (Cell impactCell : impactCells) {
                LivingCard cellLivingCard = impactCell.getLivingCard();
                if (cellLivingCard == null)
                    continue;
                int remainTime = information.getTimeOfWeaknessBuff();
                boolean isPermanent = information.isWeaknessBuffPermanent();
                int changeHP = information.getChangeHPByWeakness();
                int changePower = information.getChangeAPByWeakness();
                //havaset bashee change power + e vali bayad az ap kam she
                Impact.addWeaknessToCard(remainTime, isPermanent, false, changeHP, changePower, cellLivingCard);
            }
        }
        if (information.isCanHolyBuffAdd()) {
            for (Cell impactCell : impactCells) {
                LivingCard cellLivingCard = impactCell.getLivingCard();
                if (cellLivingCard == null)
                    continue;
                int remainTime = information.getTimeOfHolyBuff();
                boolean isPermanent = information.isHolyBuffPermanent();
                Impact.addHolyToCard(remainTime, isPermanent, false, 1, cellLivingCard);
                Impact.addHolyToCard(remainTime, isPermanent, false, 1, cellLivingCard);
            }
        }
        if (information.isCanPowerBuffAdd()) {
            for (Cell impactCell : impactCells) {
                LivingCard cellLivingCard = impactCell.getLivingCard();
                if (cellLivingCard == null)
                    continue;
                int remainTime = information.getTimeOfPowerBuff();
                boolean isPermanent = information.isPowerBuffPermanent();
                int changePower = information.getAmountOfIncreaseAP();
                if (information.isGhazaPowerBuff())
                    changePower = cellLivingCard.getHP();
                //faghat changePower dare na HP -> khob unam 0 e beharhal
                Impact.addPowerBuffToCard(remainTime, isPermanent, false, 0, changePower, cellLivingCard);
                //bara doshmano nazadam nadarim hanuz
            }
        }
        if (information.isCanKillMinionOfEnemy()) {
            for (Cell impactCell : impactCells) {
                LivingCard cellLivingCard = impactCell.getLivingCard();
                if (cellLivingCard == null)
                    continue;
                //kill hanuz khalie -> ok shod
                cellLivingCard.kill();
                checkAlive(battle, cellLivingCard);
            }
        }
        if (information.isCanKillOurMinionAndHealHero()) {
            Hero hero = battle.getPlayerOn().getHero();
            hero.setHP(hero.getHP() + livingCard.getHP());
            livingCard.kill();
            checkAlive(battle, livingCard);
        }
        if (information.isCanStunBuffAdd()) {
            for (Cell impactCell : impactCells) {
                LivingCard cellLivingCard = impactCell.getLivingCard();
                if (cellLivingCard == null)
                    continue;
                int remainTime = information.getTimeOfStunBuff();
                boolean isPermanent = information.isStunBuffPermanent();
                Impact.addStunToCard(remainTime, isPermanent, false, cellLivingCard);
            }
        }
    }

    //payane in bakhsh

    //In tike bishtar marboot be item hast

    //esme information haro dorost konam

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

