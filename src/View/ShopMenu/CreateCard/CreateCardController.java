package View.ShopMenu.CreateCard;

import Controller.Application;
import Controller.Client;
import Controller.MenuList;
import Model.CollectionItem.Hero;
import Model.CollectionItem.Information;
import Model.CollectionItem.Minion;
import Model.CollectionItem.Spell;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateCardController implements Initializable {
    public ImageView backButton;
    public Label createButton;
    public CheckBox isEnemy;
    public CheckBox isUs;
    public CheckBox isMultiple;
    public CheckBox isMinion;
    public CheckBox isHero;
    public CheckBox isCell;
    public CheckBox isColumn;
    public CheckBox isRow;
    public CheckBox isNeighbour;
    public CheckBox isItSelf;
    public CheckBox isArea;
    public TextField distanceOfArea;
    public CheckBox isSquare;
    public TextField lengthOfSquare;
    public ChoiceBox cardType;
    public TextField nameField;
    public CheckBox isHoly;
    public CheckBox isHolyCell;
    public TextField timeOfHoly;
    public TextField numberOfHolies;
    public CheckBox isIncreaseAP;
    public TextField amountOfIncreaseAP;
    public TextField timeOfIncreaseAP;
    public CheckBox isDisarm;
    public TextField timeOfDisarm;
    public CheckBox isWeakness;
    public TextField weaknessChangeHP;
    public TextField weaknessChangeAP;
    public TextField timeOfWeakness;
    public CheckBox isPoison;
    public TextField timeOfPoison;
    public TextField poisonDecreaseHP;
    public CheckBox isIncreaseMana;
    public TextField timeOfIncreaseMana;
    public TextField amountOfIncreaseMana;
    public CheckBox isFiery;
    public TextField timeOfFiery;
    public CheckBox isPoisonToCell;
    public TextField timeOfPoisonCell;
    public TextField poisonCellDecreaseHP;
    public CheckBox isStun;
    public TextField timeOfStun;
    public CheckBox isPower;
    public TextField timeOfPower;
    public TextField powerChangeAP;
    public TextField powerChangeHP;
    public CheckBox isRemoveBadBuffs;
    public CheckBox isRemoveGoodBuffs;

    public ChoiceBox spellSpecialActivation;
    public TextField APField;
    public TextField HPField;
    public TextField coolDownField;
    public AnchorPane mainPane;
    public ChoiceBox attackType;

    public static boolean isFirstTime = true;
    public TextField priceField;
    public Label errorLabel;
    public TextField rangeOfAttack;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cardType.getItems().add("HERO");
        cardType.getItems().add("MINION");
        cardType.getItems().add("SPELL");
        cardType.getSelectionModel().selectFirst();

        attackType.getItems().add("MELEE");
        attackType.getItems().add("RANGED");
        attackType.getItems().add("HYBRID");
        attackType.getSelectionModel().selectFirst();

        spellSpecialActivation.getItems().add("ON SPAWN");
        spellSpecialActivation.getItems().add("ON DEFENCE");
        spellSpecialActivation.getItems().add("ON ATTACK");
        spellSpecialActivation.getItems().add("ON DEATH");
        spellSpecialActivation.getSelectionModel().selectFirst();

        createButton.setOnMouseClicked(event -> {
            String ID = "";
            String type = (String) cardType.getValue();
            Information information = new Information();

            if (!type.equals(null)) {
                information.setEnemyImpact(isEnemy.isSelected());
                information.setUsImpact(isUs.isSelected());
                information.setMultipleImpact(isMultiple.isSelected());
                information.setMinionImpact(isMinion.isSelected());
                information.setHeroImpact(isHero.isSelected());
                information.setCellImpact(isCell.isSelected());
                information.setImpactColumn(isColumn.isSelected());
                information.setImpactRow(isRow.isSelected());
                information.setImpactNeighbors(isNeighbour.isSelected());
                information.setImpactItself(isItSelf.isSelected());
                information.setImpactArea(isArea.isSelected());
                information.setDistanceOfImpactArea(getValue(distanceOfArea.getText()));
                information.setSquareOfCellsImpact(isSquare.isSelected());
                information.setLengthOfSquareOfCellsImpact(getValue(lengthOfSquare.getText()));
                if (spellSpecialActivation.getValue().equals("ON ATTACK"))
                    information.setOnAttack(true);
                if (spellSpecialActivation.getValue().equals("ON DEFENCE"))
                    information.setOnDefence(true);
                if (spellSpecialActivation.getValue().equals("ON SPAWN"))
                    information.setOnSpawn(true);
                if (spellSpecialActivation.getValue().equals("ON DEATH"))
                    information.setOnDeath(true);
                information.setCanHolyBuffAdd(isHoly.isSelected());
                information.setCanAddHolyBuffToCell(isHolyCell.isSelected());
                information.setTimeOfHolyBuff(getValue(timeOfHoly.getText()));
                information.setNumberOfHolyBuff(getValue(numberOfHolies.getText()));
                information.setCanIncreaseAP(isIncreaseAP.isSelected());
                information.setAmountOfIncreaseAP(getValue(amountOfIncreaseAP.getText()));
                information.setIncreaseRemainTime(getValue(timeOfIncreaseAP.getText()));
                information.setCanDisarmBuffAdd(isDisarm.isSelected());
                information.setTimeOfDisarmBuff(getValue(timeOfDisarm.getText()));
                information.setCanWeaknessBuffAdd(isWeakness.isSelected());
                information.setChangeAPByWeakness(getValue(weaknessChangeAP.getText()));
                information.setChangeHPByWeakness(getValue(weaknessChangeHP.getText()));
                information.setTimeOfWeaknessBuff(getValue(timeOfWeakness.getText()));
                information.setCanPoisonBuffAdd(isPoison.isSelected());
                information.setTimeOfPoisonBuff(getValue(timeOfPoison.getText()));
                information.setDecreaseHPOfPoisonBuff(getValue(poisonDecreaseHP.getText()));
                if (attackType.getValue().equals("MELEE"))
                    information.setCanDoMeleeAttack(true);
                if (attackType.getValue().equals("RANGED"))
                    information.setCanDoRangedAttack(true);
                if (attackType.getValue().equals("HYBRID"))
                    information.setCanDoHybridAttack(true);
                information.setCanIncreaseMana(isIncreaseMana.isSelected());
                information.setTimeOfIncreaseMana(getValue(timeOfIncreaseMana.getText()));
                information.setAmountOfIncreaseMana(getValue(amountOfIncreaseMana.getText()));
                information.setCanAddFieryBuffToCell(isFiery.isSelected());
                information.setTimeOfAddFieryBuffToCell(getValue(timeOfFiery.getText()));
                information.setCanAddPoisonBuffToCell(isPoisonToCell.isSelected());
                information.setTimeOfAddPoisonBuffToCell(getValue(timeOfPoisonCell.getText()));
                information.setDecreaseHpOfPoisonBuffOfCell(getValue(poisonCellDecreaseHP.getText()));
                information.setCanStunBuffAdd(isStun.isSelected());
                information.setTimeOfStunBuff(getValue(timeOfStun.getText()));
                information.setCanPowerBuffAdd(isPower.isSelected());
                information.setTimeOfPowerBuff(getValue(timeOfPower.getText()));
                information.setChangeAPByPowerBuff(getValue(powerChangeAP.getText()));
                information.setChangeHPByPowerBuff(getValue(powerChangeHP.getText()));
                information.setCanRemoveBadBuffsOfOurselves(isRemoveBadBuffs.isSelected());
                information.setCanRemoveGoodBuffsOfEnemy(isRemoveGoodBuffs.isSelected());

                //todo be shop ezafe she
                if (type.equals("HERO")) {
                    try {
                        Hero hero = new Hero();
                        hero.setName(nameField.getText());
                        hero.setInformation(information);
                        hero.setMaxCoolDown(getValue(coolDownField.getText()));
                        hero.setPrice(getValue(priceField.getText()));
                        hero.setRangeOfAttack(getValue(rangeOfAttack.getText()));
                        hero.setHP(getValue(HPField.getText()));
                        hero.setAP(getValue(APField.getText()));
                        Application.writeJSON(hero, "Data/collectionItem/Hero/" + hero.getName() + ".json");
                        hero = Hero.createHero(hero.getName(), "SHOP");
                        ID = hero.getID();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (type.equals("MINION")) {
                    Minion minion = new Minion();
                    minion.setName(nameField.getText());
                    minion.setInformation(information);
                    minion.setPrice(getValue(priceField.getText()));
                    minion.setRangeOfAttack(getValue(rangeOfAttack.getText()));
                    minion.setHP(getValue(HPField.getText()));
                    minion.setAP(getValue(APField.getText()));
                    try {
                        Application.writeJSON(minion, "Data/collectionItem/Minion/" + minion.getName() + ".json");
                        minion = Minion.createMinion(minion.getName(), "SHOP");
                        ID = minion.getID();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (type.equals("SPELL")) {
                    Spell spell = new Spell();
                    spell.setName(nameField.getText());
                    spell.setInformation(information);
                    spell.setPrice(getValue(priceField.getText()));
                    try {
                        Application.writeJSON(spell, "Data/collectionItem/Spell/" + spell.getName() + ".json");
                        spell = Spell.createSpell(spell.getName(), "SHOP");
                        ID = spell.getID();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Client.getClient().getShopMenu().getShop().addCollectionItemToCollection(ID);
                Client.getClient().setCurrentMenu(MenuList.ShopMenu);
                // todo inja ID basse ?
                Client.getClient().getShopMenu().getShop().addCollectionItemToCollection(ID);
            }
        });
        backButton.setOnMouseClicked(event -> {
            Client.getClient().setCurrentMenu(MenuList.ShopMenu);

        });
    }

    public static int getValue(String field) {
        int value = 0;
        try {
            value = Integer.parseInt(field);
        } catch (NumberFormatException e) {

        }
        return value;
    }
}
