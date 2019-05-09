package Generator;

import Controller.Application;
import Model.CollectionItem.*;
import Model.Deck;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DeckGenerator {

    public static String[] heroNames = {"DiveSefid", "Simorgh", "Ezhdaha", "Rakhsh", "Zahhak", "Kaveh", "Arash", "Afsaneh", "Esfandiar",
            "Rostam"};
    public static String[] spellNames = {"TotalDisarm", "AreaDispel", "Empower", "Fireball", "GodStrength", "HellFire", "LightingBolt",
            "PoisonLake", "Madness", "AllDisarm", "AllPoison", "Dispel", "HealthWithProfit", "GhazaBokhorJoonBegiri",
            "AllPower", "AllAttack", "Weakening", "Sacrifice", "KingsGuard", "Shock"};
    public static String[] minionNames = {"KamandareFars", "ShamshirzaneFars", "NeyzedareFars", "AsbSavareFars", "PahlevaneFars", "SepahSalareFars"
            , "KamandareTorani", "GholabsangdareTorani", "NeyzedareTorani", "JasoseTorani", "GorzdareTorani", "ShahzadeTorani", "DiveSiah"
            , "GhooleSangAndaz", "Oghab", "DiveGorazSavar", "GhooleBozorg", "MareSami", "EzhdehayeAtashAndaz", "ShireDarande"
            , "MareGhoolPeykar", "GorgeSefid", "Palang", "Gorg", "Jadogar", "JadogarAzam", "Jen", "GorazeVahshi"
            , "Piran", "Giv", "Bahman", "Ashkbos", "Iraj", "GhooleBozorg2", "GhooleDoSar", "NaneSarma", "FoladZere", "Siavash"
            , "ShahGhool", "ArzhangeDiv"};
    public static String[] itemNames = {"TajeDanaei", "NamuseSepar", "KamaneDamul", "JunBaw", "TireSeShakh", "PareSimorgh", "Exir"
            , "MajuneMana", "MajuneRueinTani", "NefrineMarg", "RandomDamage", "TerrorHood", "BladesOfAgility", "KingKiller"
            , "AssassinationDagger", "PoisonousDagger", "ShockHammer", "SoulEater", "GhosleTamid", "ShamshireChini"};

    private static int[] heroNumber = {1, 5, 7};
    private static int[][] spellNumber = {{1, 7, 10, 11, 12, 18, 20}, {2, 3, 5, 9, 8, 13, 19}, {6, 10, 12, 14, 15, 16, 17}};
    private static int[][] minionNumber = {{1, 9, 11, 13, 17, 18, 21, 22, 26, 38, 36, 40}, {2, 3, 5, 8, 12, 15, 19, 23, 27, 30, 33, 39}
            , {6, 7, 10, 14, 16, 20, 24, 25, 28, 29, 31, 34}};
    private static int[][] itemNumber = {{1}, {18}, {12}};

    public static void deckGenerator() throws IOException {
        for (int i = 0; i < 3; i++) {
            Deck deck = new Deck("Story" + i);
            Hero hero = Hero.createHero(heroNames[heroNumber[i] - 1], "AI");
            deck.addCard(hero);
            for (int j = 0; j < spellNumber[i].length; j++) {
                Spell spell = Spell.createSpell(spellNames[spellNumber[i][j] - 1], "AI");
                deck.addCard(spell);
            }
            for (int j = 0; j < minionNumber[i].length; j++) {
                Minion minion = Minion.createMinion(minionNames[minionNumber[i][j] - 1], "AI");
                deck.addCard(minion);
            }
            for (int j = 0; j < itemNumber[i].length; j++) {
                Item item = Item.createItem(itemNames[itemNumber[i][j] - 1], "AI");
                deck.addCard(item);
            }
            String address = "Data/Battle/Story/Story" + i + ".json";
            Application.writeJSON(deck, address);
        }
    }
}
