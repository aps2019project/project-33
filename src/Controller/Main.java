package Controller;

import Model.CollectionItem.Item;
import Model.CollectionItem.Minion;
import Model.CollectionItem.Spell;
import Model.CollectionItem.Hero;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;
import jdk.jshell.spi.SPIResolutionException;

import java.io.*;
import java.util.Scanner;


public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Application application = new Application();


    public static void main(String[] args) throws IOException {
        application.runApplication();
        Main.TotalDisarm();
        Main.AreaDispel();
        Main.Empower();
        Main.Fireball();
        Main.GodStrength();
        Main.HellFire();
        Main.LightingBolt();
        Main.PoisonLake();
        Main.Madness();
        Main.AllDisarm();
        Main.AllPoison();
        Main.Dispel();
        Main.HealthWithProfit();
        Main.GhazaBokhorJoonBegiri();
        Main.AllPower();
        Main.AllAttack();
        Main.Weakening();
        Main.Sacrifice();
        Main.KingsGuard();
        Main.Shock();
        Main.DiveSefid();
        Main.Simorgh();
        Main.Ezhdaha();
        Main.Rakhsh();
        Main.Zahhak();
        Main.Kaveh();
        Main.Arash();
        Main.Afsaneh();
        Main.Esfandiar();
        Main.Rostam();
    }

    String[] heroNames = {"Dive Sefid", "Simorgh", "Ezhdaha", "Rakhsh", "Zahhak", "Kaveh", "Arash", "Afsaneh", "Esfandiar",
            "Rostam"};
    String[] spellNames = {"Total Disarm", "Area Dispel", "Empower", "Fireball", "God Strength", "HellFire", "Lighting Bolt",
            "Poison Lake", "Madness", "All Disarm", "All Poison", "Dispel", "Health with profit", "Ghaza bokhor joon begiri",
            "All Power", "All Attack", "Weakening", "Sacrifice", "Kings Guard", "Shock"};
    public static void TotalDisarm() throws IOException {
        Spell spell = new Spell();
        spell.setPrice(1000);
        spell.setMp(0);
        spell.setName("Total Disarm");
        spell.getInformation().setEnemyImpact(true);
        spell.getInformation().setCanDisarmBuffAdd(true);
        spell.getInformation().setDisarmBuffPermanent(true);
        spell.getInformation().setImpactAllArea(true);
        application.writeJSON(spell, "Data/CollectionItem/Spell/TotalDisarm.json");
    }

    public static void AreaDispel() throws IOException {
        Spell spell = new Spell();
        spell.setPrice(1500);
        spell.setMp(2);
        spell.setName("Area Dispel");
        spell.getInformation().setCellImpact(true);
        spell.getInformation().setCanRemoveGoodBuffsOfEnemy(true);
        spell.getInformation().setSquareOfCellsImpact(true);
        spell.getInformation().setLengthOfSquareOfCellsImpact(2);
        spell.getInformation().setCanRemoveBadBuffsOfOurselves(true);
        spell.getInformation().setMultipleImpact(true);
        spell.getInformation().setImpactAllArea(true);
        application.writeJSON(spell, "Data/CollectionItem/Spell/AreaDispel.json");
    }

    public static void Empower() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(250);
        spell.setMp(1);
        spell.setName("Empower");
        spell.getInformation().setUsImpact(true);
        spell.getInformation().setCanIncreaseAP(true);
        spell.getInformation().setAmountOfIncreaseAP(2);
        spell.getInformation().setIncreaseAPPermanent(true);
        spell.getInformation().setImpactAllArea(true);
        application.writeJSON(spell, "Data/CollectionItem/Spell/Empower.json");
    }

    public static void Fireball() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(400);
        spell.setMp(1);
        spell.setName("Fireball");
        spell.getInformation().setEnemyImpact(true);
        spell.getInformation().setCanDamageToEnemy(true);
        spell.getInformation().setDamageToEnemy(4);
        spell.getInformation().setImpactAllArea(true);
        application.writeJSON(spell, "Data/CollectionItem/Spell/Fireball.json");
    }

    public static void GodStrength() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(450);
        spell.setMp(2);
        spell.setName("God Strength");
        spell.getInformation().setUsImpact(true);
        spell.getInformation().setHeroImpact(true);
        spell.getInformation().setCanIncreaseAP(true);
        spell.getInformation().setIncreaseAPPermanent(true);
        spell.getInformation().setAmountOfIncreaseAP(4);
        spell.getInformation().setImpactAllArea(true);
        application.writeJSON(spell, "Data/CollectionItem/Spell/GodStrength.json");
    }

    //nemidunam chie
    public static void HellFire() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(600);
        spell.setMp(3);
        spell.setName("HellFire");
    }

    public static void LightingBolt() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(1250);
        spell.setMp(2);
        spell.setName("Lighting Bolt");
        spell.getInformation().setEnemyImpact(true);
        spell.getInformation().setHeroImpact(true);
        spell.getInformation().setImpactAllArea(true);
        spell.getInformation().setCanDamageToEnemy(true);
        spell.getInformation().setDamageToEnemy(8);
        application.writeJSON(spell, "Data/CollectionItem/Spell/LightingBolt.json");
    }

    public static void PoisonLake() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(900);
        spell.setMp(5);
        spell.setName("Poison Lake");
        spell.getInformation().setCellImpact(true);
        spell.getInformation().setLengthOfSquareOfCellsImpact(3);
        spell.getInformation().setSquareOfCellsImpact(true);
        spell.getInformation().setCanAddPoisonBuffToCell(true);
        spell.getInformation().setMultipleImpact(true);
        spell.getInformation().setTimeOfAddPoisonBuffToCell(1);
        application.writeJSON(spell, "Data/CollectionItem/Spell/PoisonLake.json");
    }

    public static void Madness() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(650);
        spell.setMp(0);
        spell.setName("Madness");
        spell.getInformation().setUsImpact(true);
        spell.getInformation().setImpactAllArea(true);
        spell.getInformation().setCanDisarmBuffAdd(true);
        spell.getInformation().setTimeOfDisarmBuff(3);
        spell.getInformation().setCanIncreaseAP(true);
        spell.getInformation().setAmountOfIncreaseAP(4);
        spell.getInformation().setIncreaseRemainTime(3);
        application.writeJSON(spell, "Data/CollectionItem/Spell/Madness.json");
    }

    public static void AllDisarm() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(2000);
        spell.setMp(9);
        spell.setName("All Disarm");
        spell.getInformation().setEnemyImpact(true);
        spell.getInformation().setMultipleImpact(true);
        spell.getInformation().setImpactAllArea(true);
        spell.getInformation().setTimeOfDisarmBuff(1);
        spell.getInformation().setCanDisarmBuffAdd(true);
        application.writeJSON(spell, "Data/CollectionItem/Spell/AllDisarm.json");
    }

    public static void AllPoison() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(1500);
        spell.setMp(8);
        spell.setName("All Poison");
        spell.getInformation().setEnemyImpact(true);
        spell.getInformation().setMultipleImpact(true);
        spell.getInformation().setImpactAllArea(true);
        spell.getInformation().setCanPoisonBuffAdd(true);
        spell.getInformation().setTimeOfPoisonBuff(4);
        application.writeJSON(spell, "Data/CollectionItem/Spell/AllPoison.json");
    }

    public static void Dispel() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(2100);
        spell.setMp(0);
        spell.setName("Dispel");
        spell.getInformation().setUsImpact(true);
        spell.getInformation().setEnemyImpact(true);
        spell.getInformation().setImpactAllArea(true);
        spell.getInformation().setCanRemoveGoodBuffsOfEnemy(true);
        spell.getInformation().setCanRemoveBadBuffsOfOurselves(true);
        application.writeJSON(spell, "Data/CollectionItem/Spell/Dispel.json");
    }

    public static void HealthWithProfit() throws  IOException{
        Spell spell = new Spell();
        spell.setPrice(2250);
        spell.setMp(0);
        spell.setName("Health with profit");
        spell.getInformation().setUsImpact(true);
        spell.getInformation().setImpactAllArea(true);
        spell.getInformation().setCanWeaknessBuffAdd(true);
        spell.getInformation().setChangeHPByWeakness(6);
        spell.getInformation().setWeaknessBuffPermanent(true);
        spell.getInformation().setNumberOfHolyBuff(2);
        spell.getInformation().setCanHolyBuffAdd(true);
        spell.getInformation().setTimeOfHolyBuff(3);
        application.writeJSON(spell, "Data/CollectionItem/Spell/HealthWithProfit.json");
    }

    public static void GhazaBokhorJoonBegiri() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(2500);
        spell.setMp(2);
        spell.setName("Ghaza bokhor joon begiri");
        spell.getInformation().setUsImpact(true);
        spell.getInformation().setImpactAllArea(true);
        spell.getInformation().setPowerBuffPermanent(true);
        spell.getInformation().setGhazaPowerBuff(true);
        spell.getInformation().setCanPowerBuffAdd(true);
        application.writeJSON(spell, "Data/CollectionItem/Spell/GhazaBokhorJoonBegiri.json");
    }

    public static void  AllPower() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(2000);
        spell.setMp(4);
        spell.setName("All Power");
        spell.getInformation().setUsImpact(true);
        spell.getInformation().setMultipleImpact(true);
        spell.getInformation().setImpactAllArea(true);
        spell.getInformation().setCanPowerBuffAdd(true);
        spell.getInformation().setPowerBuffPermanent(true);
        spell.getInformation().setAmountOfIncreaseAP(2);
        application.writeJSON(spell, "Data/CollectionItem/Spell/AllPower.json");
    }

    public static void AllAttack() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(1500);
        spell.setMp(4);
        spell.setName("All Attack");
        spell.getInformation().setEnemyImpact(true);
        spell.getInformation().setMultipleImpact(true);
        spell.getInformation().setImpactColumn(true);
        spell.getInformation().setDamageToEnemy(6);
        spell.getInformation().setCanDamageToEnemy(true);
        application.writeJSON(spell, "Data/CollectionItem/Spell/AllAttack.json");
    }

    public static void Weakening() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(1000);
        spell.setMp(1);
        spell.setName("Weakening");
        spell.getInformation().setImpactAllArea(true);
        spell.getInformation().setEnemyImpact(true);
        spell.getInformation().setMinionImpact(true);
        spell.getInformation().setCanWeaknessBuffAdd(true);
        spell.getInformation().setWeaknessBuffPermanent(true);
        spell.getInformation().setChangePowerByWeakness(4);
        application.writeJSON(spell, "Data/CollectionItem/Spell/Weakening.json");
    }

    public static void Sacrifice() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(1600);
        spell.setMp(3);
        spell.setName("Sacrifice");
        spell.getInformation().setImpactAllArea(true);
        spell.getInformation().setUsImpact(true);
        spell.getInformation().setMinionImpact(true);
        spell.getInformation().setCanKillOurMinionAndHealHero(true);
        application.writeJSON(spell, "Data/CollectionItem/Spell/Sacrifice.json");
    }

    public static void KingsGuard() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(1750);
        spell.setMp(3);
        spell.setName("Kings Guard");
        spell.getInformation().setEnemyImpact(true);
        spell.getInformation().setMinionImpact(true);
        spell.getInformation().setImpactNeighbors(true);
        spell.getInformation().setCanKillMinionOfEnemy(true);
        application.writeJSON(spell, "Data/CollectionItem/Spell/KingsGuard.json");
    }

    public static void Shock() throws IOException{
        Spell spell = new Spell();
        spell.setPrice(1200);
        spell.setMp(1);
        spell.setName("Shock");
        spell.getInformation().setEnemyImpact(true);
        spell.getInformation().setImpactAllArea(true);
        spell.getInformation().setCanStunBuffAdd(true);
        spell.getInformation().setTimeOfStunBuff(2);
        application.writeJSON(spell, "Data/CollectionItem/Spell/Shock.json");
    }

    public static void DiveSefid() throws IOException{
        Hero hero = new Hero("Dive Sefid", 8000, 50, 4, "melee", 1, 2);
        application.writeJSON(hero, "Data/CollectionItem/Hero/DiveSefid.json");
    }

    public static void Simorgh() throws IOException{
        Hero hero = new Hero("Simorgh", 9000, 50, 4, "melee", 3, 8);
        application.writeJSON(hero, "Data/CollectionItem/Hero/Simorgh.json");
    }

    public static void Ezhdaha() throws IOException{
        Hero hero = new Hero("Ezhdaha", 8000, 50, 4, "melee", 0, 1);
        application.writeJSON(hero, "Data/CollectionItem/Hero/Ezhdaha.json");
    }

    public static void  Rakhsh() throws IOException{
        Hero hero = new Hero("Rakhsh", 8000, 50, 4, "melee", 1, 2);
        application.writeJSON(hero, "Data/CollectionItem/Hero/Rakhsh.json");
    }

    public static void Zahhak() throws IOException{
        Hero hero = new Hero("Zahhak", 10000, 50, 4, "melee", 1, 3);
        application.writeJSON(hero, "Data/CollectionItem/Hero/Zahhak.json");
    }

    public static void Kaveh() throws IOException{
        Hero hero = new Hero("Kaveh", 8000, 50, 4, "melee", 1, 3);
        application.writeJSON(hero, "Data/CollectionItem/Hero/Kaveh.json");
    }

    public static void Arash() throws IOException{
        Hero hero = new Hero("Arash", 10000, 30, 2, "ranged", 2, 2);
        hero.setRangeOfAttack(6);
        application.writeJSON(hero, "Data/CollectionItem/Hero/Arash.json");
    }

    public static void Afsaneh() throws IOException{
        Hero hero = new Hero("Afsaneh", 11000, 40, 3, "ranged", 1, 2);
        hero.setRangeOfAttack(3);
        application.writeJSON(hero, "Data/CollectionItem/Hero/Afsaneh.json");
    }

    public static void Esfandiar() throws IOException{
        Hero hero = new Hero("Esfandiar", 12000, 35, 3, "hybrid", 0, 1);
        hero.setRangeOfAttack(3);
        application.writeJSON(hero, "Data/CollectionItem/Hero/Esfandiar.json");
    }

    public static void Rostam() throws IOException{
        //chera mp o cooldown nadare?
        Hero hero = new Hero("Rostam", 8000, 55, 7, "hybrid", 0, 0);
        hero.setRangeOfAttack(4);
        application.writeJSON(hero, "Data/CollectionItem/Hero/Rostam.json");
    }
}


//getClass e tooye show info cherto perte
//get AP ham bayad dashte bashe