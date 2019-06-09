package Generator;

import Controller.Application;
import Model.CollectionItem.Hero;
import Model.CollectionItem.Information;
import Model.CollectionItem.Minion;
import Model.CollectionItem.Spell;

import java.io.IOException;

public class Generator {
    private static Application application;

    private static String[] heroNames = {"DiveSefid", "Simorgh", "Ezhdaha", "Rakhsh", "Zahhak", "Kaveh", "Arash", "Afsaneh", "Esfandiar",
            "Rostam"};
    private static String[] spellNames = {"TotalDisarm", "AreaDispel", "Empower", "Fireball", "GodStrength", "HellFire", "LightingBolt",
            "PoisonLake", "Madness", "AllDisarm", "AllPoison", "Dispel", "HealthWithProfit", "GhazaBokhorJoonBegiri",
            "AllPower", "AllAttack", "Weakening", "Sacrifice", "KingsGuard", "Shock"};

    public static void setApplication(Application setApplication){
        application = setApplication;
    }

    public static void createCards(Application settedApplication) throws IOException {
        setApplication(settedApplication);
        createMinions();
        createSpells();
        createHeroes();
    }

    public static void createSpells() throws IOException {
        Generator.TotalDisarm();
        Generator.AreaDispel();
        Generator.Empower();
        Generator.Fireball();
        Generator.GodStrength();
        Generator.HellFire();
        Generator.LightingBolt();
        Generator.PoisonLake();
        Generator.Madness();
        Generator.AllDisarm();
        Generator.AllPoison();
        Generator.Dispel();
        Generator.HealthWithProfit();
        Generator.GhazaBokhorJoonBegiri();
        Generator.AllPower();
        Generator.AllAttack();
        Generator.Weakening();
        Generator.Sacrifice();
        Generator.KingsGuard();
        Generator.Shock();
    }

    public static void createHeroes() throws IOException {
        Generator.DiveSefid();
        Generator.Simorgh();
        Generator.Ezhdaha();
        Generator.Rakhsh();
        Generator.Zahhak();
        Generator.Kaveh();
        Generator.Arash();
        Generator.Afsaneh();
        Generator.Esfandiar();
        Generator.Rostam();
    }

    public static void createMinions() throws IOException {
        Generator.createKamandareFars();
        Generator.createShamshirzaneFars();
        Generator.createNeyzedareFars();
        Generator.createAsbSavareFars();
        Generator.createPahlevaneFars();
        Generator.createSepahSalareFars();
        Generator.createKamandareTorani();
        Generator.createGholabsangdareTorani();
        Generator.createNeyzedareTorani();
        Generator.createJasoseTorani();
        Generator.createGorzdareTorani();
        Generator.createShahzadeTorani();
        Generator.createDiveSiah();
        Generator.createGhooleSangAndaz();
        Generator.createOghab();
        Generator.createDiveGorazSavar();
        Generator.createGhooleBozorg();
        Generator.createMareSami();
        Generator.createEzhdehayeAtashAndaz();
        Generator.createShireDarande();
        Generator.createMareGhoolPeykar();
        Generator.createGorgeSefid();
        Generator.createPalang();
        Generator.createGorg();
        Generator.createJadogar();
        Generator.createJadogarAzam();
        Generator.createJen();
        Generator.createGorazeVahshi();
        Generator.createPiran();
        Generator.createGiv();
        Generator.createBahman();
        Generator.createAshkbos();
        Generator.createIraj();
        Generator.createGhooleBozorg2();
        Generator.createGhooleDoSar();
        Generator.createNaneSarma();
        Generator.createFoladZere();
        Generator.createSiavash();
        Generator.createShahGhool();
        Generator.createArzhangeDiv();
    }

    public static void setSpellAttackArea(Spell spell, boolean isUs, boolean isEnemy, boolean isHero, boolean isMinion,
                                          boolean isCell, boolean isSquare, int length, boolean isNeighbor, boolean isRow, boolean isColumn,
                                          boolean isKingsGuard){
        Information information = spell.getInformation();
        information.setUsImpact(isUs);
        information.setEnemyImpact(isEnemy);
        information.setHeroImpact(isHero);
        information.setMinionImpact(isMinion);
        information.setCellImpact(isCell);
        information.setSquareOfCellsImpact(isSquare);
        information.setLengthOfSquareOfCellsImpact(length);
        information.setImpactNeighbors(isNeighbor);
        information.setImpactRow(isRow);
        information.setImpactColumn(isColumn);
        information.setKingsGuard(isKingsGuard);
    }

    public static void setMinionAttackArea(Minion minion, boolean isPassive, boolean isOnAttack, boolean isOnDefence, boolean isOnDeath,
                                           boolean isOnSpawn, boolean isCombo, boolean isEnemy, boolean isUs, boolean isHero,
                                           boolean isMinion, boolean isCell, boolean isNeighbor, boolean isMelee,
                                           boolean isHybrid, boolean isRanged, int range){
        Information information = minion.getInformation();
        information.setPassive(isPassive);
        information.setOnAttack(isOnAttack);
        information.setOnDefence(isOnDefence);
        information.setOnDeath(isOnDeath);
        information.setOnSpawn(isOnSpawn);
        information.setCombo(isCombo);
        information.setEnemyImpact(isEnemy);
        information.setUsImpact(isUs);
        information.setHeroImpact(isHero);
        information.setMinionImpact(isMinion);
        information.setCellImpact(isCell);
        information.setImpactNeighbors(isNeighbor);
        information.setCanDoMeleeAttack(isMelee);
        information.setCanDoHybridAttack(isHybrid);
        information.setCanDoRangedAttack(isRanged);
        minion.setRangeOfAttack(range);
    }

    public static void TotalDisarm() throws IOException {
        Spell spell = new Spell();
        spell.setPrice(1000);
        spell.setMp(0);
        spell.setName("TotalDisarm");

        setSpellAttackArea(spell, false, true, true, true, false, false, 0, false,
                false, false, false);

        spell.getInformation().setCanDisarmBuffAdd(true);
        spell.getInformation().setDisarmBuffPermanent(true);

        Application.writeJSON(spell, "Data/CollectionItem/Spell/TotalDisarm.json");
    }

    public static void AreaDispel() throws IOException {
        Spell spell = new Spell();
        spell.setPrice(1500);
        spell.setMp(2);
        spell.setName("AreaDispel");

        setSpellAttackArea(spell, true, true, true, true, true, true, 2, false,
                false, false, false);

        spell.getInformation().setMultipleImpact(true);
        spell.getInformation().setCanRemoveGoodBuffsOfEnemy(true);
        spell.getInformation().setCanRemoveBadBuffsOfOurselves(true);

        Application.writeJSON(spell, "Data/CollectionItem/Spell/AreaDispel.json");
    }

    public static void Empower() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(250);
        spell.setMp(1);
        spell.setName("Empower");

        setSpellAttackArea(spell, true, false, true, true, false, false, 0, false,
                false, false, false);

        spell.getInformation().setCanIncreaseAP(true);
        spell.getInformation().setAmountOfIncreaseAP(2);
        spell.getInformation().setIncreaseAPPermanent(true);

        Application.writeJSON(spell, "Data/CollectionItem/Spell/Empower.json");
    }

    public static void Fireball() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(400);
        spell.setMp(1);
        spell.setName("Fireball");

        setSpellAttackArea(spell, false, true, true, true, false, false, 0, false,
                false, false, false);

        spell.getInformation().setCanDamageToEnemy(true);
        spell.getInformation().setDamageToEnemy(4);

        Application.writeJSON(spell, "Data/CollectionItem/Spell/Fireball.json");
    }

    public static void GodStrength() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(450);
        spell.setMp(2);
        spell.setName("GodStrength");

        setSpellAttackArea(spell, true, false, true, false, false, false, 0, false,
                false, false, false);

        spell.getInformation().setCanIncreaseAP(true);
        spell.getInformation().setIncreaseAPPermanent(true);
        spell.getInformation().setAmountOfIncreaseAP(4);

        Application.writeJSON(spell, "Data/CollectionItem/Spell/GodStrength.json");
    }

    //nemidunam chie
    public static void HellFire() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(600);
        spell.setMp(3);
        spell.setName("HellFire");

        //TODO attack area baraye kasaei ke kollan roo cell ha anjam mishan baayad doros she
        setSpellAttackArea(spell, false, false, false, false, true, true, 2, false,
                false, false, false);

        //TODO impact


        Application.writeJSON(spell, "Data/CollectionItem/Spell/HellFire.json");
    }

    public static void LightingBolt() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(1250);
        spell.setMp(2);
        spell.setName("LightingBolt");

        setSpellAttackArea(spell, false, true, true, false, false, false, 0, false,
                false, false, false);

        spell.getInformation().setCanDamageToEnemy(true);
        spell.getInformation().setDamageToEnemy(8);
        Application.writeJSON(spell, "Data/CollectionItem/Spell/LightingBolt.json");
    }

    public static void PoisonLake() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(900);
        spell.setMp(5);
        spell.setName("PoisonLake");

        setSpellAttackArea(spell, true, true, true, true, true, true, 3, false,
                false, false, false);

        spell.getInformation().setCanAddPoisonBuffToCell(true);
        spell.getInformation().setMultipleImpact(true);
        spell.getInformation().setTimeOfAddPoisonBuffToCell(1);
        Application.writeJSON(spell, "Data/CollectionItem/Spell/PoisonLake.json");
    }

    public static void Madness() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(650);
        spell.setMp(0);
        spell.setName("Madness");

        setSpellAttackArea(spell, true, false, true, true, false, false, 0, false,
                false, false, false);

        spell.getInformation().setCanDisarmBuffAdd(true);
        spell.getInformation().setTimeOfDisarmBuff(3);
        spell.getInformation().setCanIncreaseAP(true);
        spell.getInformation().setAmountOfIncreaseAP(4);
        spell.getInformation().setIncreaseRemainTime(3);
        Application.writeJSON(spell, "Data/CollectionItem/Spell/Madness.json");
    }

    public static void AllDisarm() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(2000);
        spell.setMp(9);
        spell.setName("AllDisarm");

        setSpellAttackArea(spell, false, true, true, true, false, false, 0, false,
                false, false, false);

        spell.getInformation().setMultipleImpact(true);
        spell.getInformation().setTimeOfDisarmBuff(1);
        spell.getInformation().setCanDisarmBuffAdd(true);
        Application.writeJSON(spell, "Data/CollectionItem/Spell/AllDisarm.json");
    }

    public static void AllPoison() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(1500);
        spell.setMp(8);
        spell.setName("AllPoison");

        setSpellAttackArea(spell, false, true, true, true, false, false, 0, false,
                false, false, false);

        spell.getInformation().setMultipleImpact(true);
        spell.getInformation().setCanPoisonBuffAdd(true);
        spell.getInformation().setTimeOfPoisonBuff(4);
        Application.writeJSON(spell, "Data/CollectionItem/Spell/AllPoison.json");
    }

    public static void Dispel() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(2100);
        spell.setMp(0);
        spell.setName("Dispel");

        setSpellAttackArea(spell, true, true, true, true, false, false, 0, false,
                false, false, false);

        spell.getInformation().setCanRemoveGoodBuffsOfEnemy(true);
        spell.getInformation().setCanRemoveBadBuffsOfOurselves(true);
        Application.writeJSON(spell, "Data/CollectionItem/Spell/Dispel.json");
    }

    public static void HealthWithProfit() throws  IOException{
        Spell spell = new Spell();
        spell.setPrice(2250);
        spell.setMp(0);
        spell.setName("HealthWithProfit");

        setSpellAttackArea(spell, true, false, true, true, false, false, 0, false,
                false, false, false);

        spell.getInformation().setCanWeaknessBuffAdd(true);
        spell.getInformation().setChangeHPByWeakness(6);
        spell.getInformation().setWeaknessBuffPermanent(true);
        spell.getInformation().setNumberOfHolyBuff(2);
        spell.getInformation().setCanHolyBuffAdd(true);
        spell.getInformation().setTimeOfHolyBuff(3);
        Application.writeJSON(spell, "Data/CollectionItem/Spell/HealthWithProfit.json");
    }

    public static void GhazaBokhorJoonBegiri() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(2500);
        spell.setMp(2);
        spell.setName("GhazaBokhorJoonBegiri");

        setSpellAttackArea(spell, true, false, true, true, false, false, 0, false,
                false, false, false);

        spell.getInformation().setPowerBuffPermanent(true);
        spell.getInformation().setGhazaPowerBuff(true);
        spell.getInformation().setCanPowerBuffAdd(true);
        Application.writeJSON(spell, "Data/CollectionItem/Spell/GhazaBokhorJoonBegiri.json");
    }

    public static void  AllPower() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(2000);
        spell.setMp(4);
        spell.setName("AllPower");

        setSpellAttackArea(spell, true, false, true, true, false, false, 0, false,
                false, false, false);

        spell.getInformation().setMultipleImpact(true);
        spell.getInformation().setCanPowerBuffAdd(true);
        spell.getInformation().setPowerBuffPermanent(true);
        spell.getInformation().setAmountOfIncreaseAP(2);
        Application.writeJSON(spell, "Data/CollectionItem/Spell/AllPower.json");
    }

    public static void AllAttack() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(1500);
        spell.setMp(4);
        spell.setName("AllAttack");

        setSpellAttackArea(spell, false, true, true, true, true, false, 0, false,
                false, true, false);

        spell.getInformation().setMultipleImpact(true);
        spell.getInformation().setDamageToEnemy(6);
        spell.getInformation().setCanDamageToEnemy(true);
        Application.writeJSON(spell, "Data/CollectionItem/Spell/AllAttack.json");
    }

    public static void Weakening() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(1000);
        spell.setMp(1);
        spell.setName("Weakening");

        setSpellAttackArea(spell, false, true, false, true, false, false, 0, false,
                false, false, false);

        spell.getInformation().setCanWeaknessBuffAdd(true);
        spell.getInformation().setWeaknessBuffPermanent(true);
        spell.getInformation().setChangePowerByWeakness(4);
        Application.writeJSON(spell, "Data/CollectionItem/Spell/Weakening.json");
    }

    public static void Sacrifice() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(1600);
        spell.setMp(3);
        spell.setName("Sacrifice");

        setSpellAttackArea(spell, true, false, false, true, false, false, 0, false,
                false, false, false);

        spell.getInformation().setCanKillOurMinionAndHealHero(true);
        Application.writeJSON(spell, "Data/CollectionItem/Spell/Sacrifice.json");
    }

    public static void KingsGuard() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(1750);
        spell.setMp(3);
        spell.setName("KingsGuard");

        setSpellAttackArea(spell, false, true, false, true, false, false, 0, false,
                false, false, true);

        spell.getInformation().setCanKillMinionOfEnemy(true);
        Application.writeJSON(spell, "Data/CollectionItem/Spell/KingsGuard.json");
    }

    public static void Shock() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(1200);
        spell.setMp(1);
        spell.setName("Shock");

        setSpellAttackArea(spell, false, true, true, true, false, false, 0, false,
                false, false, false);

        spell.getInformation().setCanStunBuffAdd(true);
        spell.getInformation().setTimeOfStunBuff(2);
        Application.writeJSON(spell, "Data/CollectionItem/Spell/Shock.json");
    }

    public static void DiveSefid() throws IOException{
        Hero hero = new Hero("DiveSefid", 8000, 50, 4, "melee", 1, 2);
        hero.getInformation().setCanDoMeleeAttack(true);
        Application.writeJSON(hero, "Data/CollectionItem/Hero/DiveSefid.json");
    }

    public static void Simorgh() throws IOException{
        Hero hero = new Hero("Simorgh", 9000, 50, 4, "melee", 3, 8);
        hero.getInformation().setCanDoMeleeAttack(true);
        Application.writeJSON(hero, "Data/CollectionItem/Hero/Simorgh.json");
    }

    public static void Ezhdaha() throws IOException{
        Hero hero = new Hero("Ezhdaha", 8000, 50, 4, "melee", 0, 1);
        hero.getInformation().setCanDoMeleeAttack(true);
        Application.writeJSON(hero, "Data/CollectionItem/Hero/Ezhdaha.json");
    }

    public static void  Rakhsh() throws IOException{
        Hero hero = new Hero("Rakhsh", 8000, 50, 4, "melee", 1, 2);
        hero.getInformation().setCanDoMeleeAttack(true);
        Application.writeJSON(hero, "Data/CollectionItem/Hero/Rakhsh.json");
    }

    public static void Zahhak() throws IOException{
        Hero hero = new Hero("Zahhak", 10000, 50, 4, "melee", 1, 3);
        hero.getInformation().setCanDoMeleeAttack(true);
        Application.writeJSON(hero, "Data/CollectionItem/Hero/Zahhak.json");
    }

    public static void Kaveh() throws IOException{
        Hero hero = new Hero("Kaveh", 8000, 50, 4, "melee", 1, 3);
        hero.getInformation().setCanDoMeleeAttack(true);
        Application.writeJSON(hero, "Data/CollectionItem/Hero/Kaveh.json");
    }

    public static void Arash() throws IOException{
        Hero hero = new Hero("Arash", 10000, 30, 2, "ranged", 2, 2);
        hero.getInformation().setCanDoRangedAttack(true);
        hero.setRangeOfAttack(6);
        Application.writeJSON(hero, "Data/CollectionItem/Hero/Arash.json");
    }

    public static void Afsaneh() throws IOException{
        Hero hero = new Hero("Afsaneh", 11000, 40, 3, "ranged", 1, 2);
        hero.getInformation().setCanDoRangedAttack(true);
        hero.setRangeOfAttack(3);
        Application.writeJSON(hero, "Data/CollectionItem/Hero/Afsaneh.json");
    }

    public static void Esfandiar() throws IOException{
        Hero hero = new Hero("Esfandiar", 12000, 35, 3, "hybrid", 0, 1);
        hero.getInformation().setCanDoHybridAttack(true);
        hero.setRangeOfAttack(3);
        Application.writeJSON(hero, "Data/CollectionItem/Hero/Esfandiar.json");
    }

    public static void Rostam() throws IOException{
        //chera mp o cooldown nadare?s
        Hero hero = new Hero("Rostam", 8000, 55, 7, "hybrid", 0, 0);
        hero.getInformation().setCanDoHybridAttack(true);
        hero.setRangeOfAttack(4);
        Application.writeJSON(hero, "Data/CollectionItem/Hero/Rostam.json");
    }

/////////////////////////////////////////////////

    public static void createArzhangeDiv() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(600);
        minion.setMP(3);
        minion.setHP(6);
        minion.setAP(6);
        minion.setName("ArzhangeDiv");

        setMinionAttackArea(minion, false, false, false, false, false, true, true, false, true, true, false, false, true, false, false, 0);

        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/ArzhangeDiv.json");
    }
    public static void createAsbSavareFars() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(200);
        minion.setMP(4);
        minion.setHP(10);
        minion.setAP(6);
        minion.setName("AsbSavareFars");

        setMinionAttackArea(minion, false, false, false, false, false, false, false, false, false, false, false, false,  true, false, false, 6);


        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/AsbSavareFars.json");
    }
    public static void createAshkbos() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(400);
        minion.setMP(7);
        minion.setHP(14);
        minion.setAP(8);
        minion.setName("Ashkbos");

        setMinionAttackArea(minion, false, false, true, false, false, false, false, false, false, false, false, false,  true, false, false, 0);

        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/Ashkbos.json");
    }
    public static void createBahman() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(450);
        minion.setMP(8);
        minion.setHP(16);
        minion.setAP(9);
        minion.setName("Bahman");

        setMinionAttackArea(minion, false, false, false, false, true, false, true, false, false, true, false, false,  true, false, false, 0);


        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/Bahman.json");
    }
    public static void createDiveGorazSavar() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(300);
        minion.setMP(6);
        minion.setHP(16);
        minion.setAP(8);
        minion.setName("DiveGorazSavar");

        setMinionAttackArea(minion, false, false, false, false, false, false, false, false, false, false, false, false,  true, false, false, 0);

        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/DiveGorazSavar.json");
    }
    public static void createDiveSiah() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(300);
        minion.setMP(9);
        minion.setHP(14);
        minion.setAP(10);
        minion.setName("DiveSiah");

        setMinionAttackArea(minion, false, false, false, false, false, false, false, false, false, false, false, false,  false, true, false, 7);

        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/DiveSiah.json");
    }
    public static void createEzhdehayeAtashAndaz() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(250);
        minion.setMP(5);
        minion.setHP(9);
        minion.setAP(5);
        minion.setName("EzhdehayeAtashAndaz");

        setMinionAttackArea(minion, false, false, false, false, false, false, false, false, false, false, false, false,  false, false, true, 4);


        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/EzhdehayeAtashAndaz.json");
    }
    public static void createFoladZere() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(650);
        minion.setMP(3);
        minion.setHP(1);
        minion.setAP(1);
        minion.setName("FoladZere");

        setMinionAttackArea(minion, false, false, false, false, false, false, false, false, false, false, false, false,  false, false, false, 0);


        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/FoladZere.json");
    }
    public static void createGholabsangdareTorani() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(600);
        minion.setMP(1);
        minion.setHP(4);
        minion.setAP(2);
        minion.getInformation().setCanDoRangedAttack(true);
        minion.setRangeOfAttack(7);
        minion.setName("GholabsangdareTorani");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/GholabsangdareTorani.json");
    }
    public static void createGhooleBozorg() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(500);
        minion.setMP(7);
        minion.setHP(12);
        minion.setAP(11);
        minion.getInformation().setCanDoHybridAttack(true);
        minion.setRangeOfAttack(3);
        minion.setName("GhooleBozorg");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/GhooleBozorg.json");
    }
    public static void createGhooleBozorg2() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(600);
        minion.setMP(9);
        minion.setHP(30);
        minion.setAP(8);
        minion.getInformation().setCanDoHybridAttack(true);
        minion.setRangeOfAttack(2);
        minion.setName("GhooleBozorg2");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/GhooleBozorg2.json");
    }
    public static void createGhooleDoSar() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(550);
        minion.setMP(4);
        minion.setHP(10);
        minion.setAP(4);
        minion.getInformation().setCanDoMeleeAttack(true);
        minion.setRangeOfAttack(0);
        minion.setName("GhooleDoSar");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/GhooleDoSar.json");
    }
    public static void createGhooleSangAndaz() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(300);
        minion.setMP(9);
        minion.setHP(12);
        minion.setAP(12);
        minion.getInformation().setCanDoRangedAttack(true);
        minion.setRangeOfAttack(7);
        minion.setName("GhooleSangAndaz");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/GhooleSangAndaz.json");
    }
    public static void createGiv() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(450);
        minion.setMP(4);
        minion.setHP(5);
        minion.setAP(7);
        minion.getInformation().setCanDoRangedAttack(true);
        minion.setRangeOfAttack(5);
        minion.setName("Giv");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/Giv.json");
    }
    public static void createGorazeVahshi() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(500);
        minion.setMP(6);
        minion.setHP(10);
        minion.setAP(14);
        minion.getInformation().setCanDoMeleeAttack(true);
        minion.setRangeOfAttack(0);
        minion.setName("GorazeVahshi");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/GorazeVahshi.json");
    }
    public static void createGorg() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(400);
        minion.setMP(3);
        minion.setHP(6);
        minion.setAP(1);
        minion.getInformation().setCanDoMeleeAttack(true);
        minion.setRangeOfAttack(0);
        minion.setName("Gorg");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/Gorg.json");
    }
    public static void createGorgeSefid() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(400);
        minion.setMP(5);
        minion.setHP(8);
        minion.setAP(2);
        minion.getInformation().setCanDoMeleeAttack(true);
        minion.setRangeOfAttack(0);
        minion.setName("GorgeSefid");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/GorgeSefid.json");
    }
    public static void createGorzdareTorani() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(450);
        minion.setMP(2);
        minion.setHP(3);
        minion.setAP(10);
        minion.getInformation().setCanDoMeleeAttack(true);
        minion.setRangeOfAttack(0);
        minion.setName("GorzdareTorani");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/GorzdareTorani.json");
    }
    public static void createIraj() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(500);
        minion.setMP(4);
        minion.setHP(6);
        minion.setAP(20);
        minion.getInformation().setCanDoRangedAttack(true);
        minion.setRangeOfAttack(3);
        minion.setName("Iraj");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/Iraj.json");
    }
    public static void createJadogar() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(550);
        minion.setMP(4);
        minion.setHP(5);
        minion.setAP(4);
        minion.getInformation().setCanDoRangedAttack(true);
        minion.setRangeOfAttack(3);
        minion.setName("Jadogar");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/Jadogar.json");
    }
    public static void createJadogarAzam() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(550);
        minion.setMP(6);
        minion.setHP(6);
        minion.setAP(6);
        minion.getInformation().setCanDoRangedAttack(true);
        minion.setRangeOfAttack(5);
        minion.setName("JadogarAzam");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/JadogarAzam.json");
    }
    public static void createJasoseTorani() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(700);
        minion.setMP(4);
        minion.setHP(6);
        minion.setAP(6);
        minion.getInformation().setCanDoMeleeAttack(true);
        minion.setRangeOfAttack(0);
        minion.setName("JasoseTorani");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/JasoseTorani.json");
    }
    public static void createJen() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(500);
        minion.setMP(5);
        minion.setHP(10);
        minion.setAP(4);
        minion.getInformation().setCanDoRangedAttack(true);
        minion.setRangeOfAttack(4);
        minion.setName("Jen");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/Jen.json");
    }
    public static void createKamandareFars() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(300);
        minion.setMP(2);
        minion.setHP(6);
        minion.setAP(4);
        minion.getInformation().setCanDoRangedAttack(true);
        minion.setRangeOfAttack(7);
        minion.setName("KamandareFars");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/KamandareFars.json");
    }
    public static void createKamandareTorani() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(500);
        minion.setMP(1);
        minion.setHP(3);
        minion.setAP(4);
        minion.getInformation().setCanDoRangedAttack(true);
        minion.setRangeOfAttack(5);
        minion.setName("KamandareTorani");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/KamandareTorani.json");
    }
    public static void createMareGhoolPeykar() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(500);
        minion.setMP(8);
        minion.setHP(14);
        minion.setAP(7);
        minion.getInformation().setCanDoRangedAttack(true);
        minion.setRangeOfAttack(5);
        minion.setName("MareGhoolPeykar");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/MareGhoolPeykar.json");
    }
    public static void createMareSami() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(300);
        minion.setMP(4);
        minion.setHP(5);
        minion.setAP(6);
        minion.getInformation().setCanDoRangedAttack(true);
        minion.setRangeOfAttack(4);
        minion.setName("MareSami");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/MareSami.json");
    }
    public static void createNaneSarma() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(500);
        minion.setMP(3);
        minion.setHP(3);
        minion.setAP(4);
        minion.getInformation().setCanDoRangedAttack(true);
        minion.setRangeOfAttack(5);
        minion.setName("NaneSarma");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/NaneSarma.json");
    }
    public static void createNeyzedareFars() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(500);
        minion.setMP(1);
        minion.setHP(5);
        minion.setAP(3);
        minion.getInformation().setCanDoHybridAttack(true);
        minion.setRangeOfAttack(3);
        minion.setName("NeyzedareFars");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/NeyzedareFars.json");
    }
    public static void createNeyzedareTorani() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(600);
        minion.setMP(1);
        minion.setHP(4);
        minion.setAP(4);
        minion.getInformation().setCanDoHybridAttack(true);
        minion.setRangeOfAttack(3);
        minion.setName("NeyzedareTorani");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/NeyzedareTorani.json");
    }
    public static void createOghab() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(200);
        minion.setMP(2);
        minion.setHP(0);
        minion.setAP(2);
        minion.getInformation().setCanDoRangedAttack(true);
        minion.setRangeOfAttack(3);
        minion.setName("Oghab");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/Oghab.json");
    }
    public static void createPahlevaneFars() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(600);
        minion.setMP(9);
        minion.setHP(24);
        minion.setAP(6);
        minion.getInformation().setCanDoMeleeAttack(true);
        minion.setRangeOfAttack(0);
        minion.setName("PahlevaneFars");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/PahlevaneFars.json");
    }
    public static void createPalang() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(400);
        minion.setMP(4);
        minion.setHP(6);
        minion.setAP(2);
        minion.getInformation().setCanDoMeleeAttack(true);
        minion.setRangeOfAttack(0);
        minion.setName("Palang");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/Palang.json");
    }
    public static void createPiran() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(400);
        minion.setMP(8);
        minion.setHP(20);
        minion.setAP(12);
        minion.getInformation().setCanDoMeleeAttack(true);
        minion.setRangeOfAttack(0);
        minion.setName("Piran");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/Piran.json");
    }
    public static void createSepahSalareFars() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(800);
        minion.setMP(7);
        minion.setHP(12);
        minion.setAP(4);
        minion.getInformation().setCanDoMeleeAttack(true);
        minion.setRangeOfAttack(0);
        minion.setName("SepahSalareFars");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/SepahSalareFars.json");
    }
    public static void createShahGhool() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(600);
        minion.setMP(5);
        minion.setHP(10);
        minion.setAP(4);
        minion.getInformation().setCanDoMeleeAttack(true);
        minion.setRangeOfAttack(0);
        minion.setName("ShahGhool");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/ShahGhool.json");
    }
    public static void createShahzadeTorani() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(800);
        minion.setMP(6);
        minion.setHP(6);
        minion.setAP(10);
        minion.getInformation().setCanDoMeleeAttack(true);
        minion.setRangeOfAttack(0);
        minion.setName("ShahzadeTorani");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/ShahzadeTorani.json");
    }
    public static void createShamshirzaneFars() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(400);
        minion.setMP(2);
        minion.setHP(6);
        minion.setAP(4);
        minion.setName("ShamshirzaneFars");

        minion.getInformation().setCanStunBuffAdd(true);
        minion.getInformation().setTimeOfStunBuff(1);

        setMinionAttackArea(minion, false, true, true, false, false, false, true, false, true, true, false, false,  true, false, false, 0);

        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/ShamshirzaneFars.json");
    }
    public static void createShireDarande() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(600);
        minion.setMP(2);
        minion.setHP(1);
        minion.setAP(8);
        minion.getInformation().setCanDoMeleeAttack(true);
        minion.setRangeOfAttack(0);
        minion.setName("ShireDarande");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/ShireDarande.json");
    }
    public static void createSiavash() throws IOException {
        Minion minion = new Minion();
        minion.setPrice(350);
        minion.setMP(4);
        minion.setHP(8);
        minion.setAP(5);
        minion.getInformation().setCanDoMeleeAttack(true);
        minion.setRangeOfAttack(0);
        minion.setName("Siavash");
        Application.writeJSON(minion, "Data/CollectionItem/Card/LivingCard/Minion/Siavash.json");
    }

}
