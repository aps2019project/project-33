package View.ShopMenu.CreateCard;

import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
    public ChoiceBox AttackType;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
