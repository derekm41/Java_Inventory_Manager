package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import model.*;

/** FXML Add Part Controller Class.
 * This class controllers the functionality of the add part screen.
 * @author Derek Mclaws.
 * */
public class AddPartController implements Initializable {

    @FXML
    private RadioButton inHouseRadio;

    @FXML
    private RadioButton outsourcedRadio;

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField inv;

    @FXML
    private TextField price;

    @FXML
    private TextField max;

    @FXML
    private TextField min;

    @FXML
    private TextField machineCompany;

    @FXML
    private Label machineCompanyLabel;

    @FXML
    private Button addPartSaveButton;


    /** Initialize the controller class.
     * This initializes the add part controller class.
     * */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

    /** Cancel Part
     * This method exits the add part screen.
     * */
    @FXML
    void cancelPart(MouseEvent event) throws IOException {
        //confirm alert
        Alert confirmCancelPart = new Alert(Alert.AlertType.CONFIRMATION);
        confirmCancelPart.setTitle("Cancel");
        confirmCancelPart.setHeaderText("Confirm");
        confirmCancelPart.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> outcome = confirmCancelPart.showAndWait();

        if(outcome.get() == ButtonType.OK) {
            Parent mainScreen = FXMLLoader.load(getClass().getResource("/view/Main_Screen.fxml"));
            Scene mainScreenScene = new Scene(mainScreen);
            Stage mainScreenStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            mainScreenStage.setScene((mainScreenScene));
            mainScreenStage.show();
        }

    }

    /** Save Part
     * This method gets part information and saves it to all parts list.
     * */
    @FXML
    void savePart(MouseEvent event) throws IOException {
        try {
            int tempId = MainScreenController.generatePartID();
            String tempName = name.getText();
            double tempPrice = Double.parseDouble(price.getText());
            int tempStock = Integer.parseInt(inv.getText());
            int tempMin = Integer.parseInt(min.getText());
            int tempMax = Integer.parseInt(max.getText());

            MainScreenController.errorTracking = MainScreenController.minMaxCheck(tempMin, tempMax, tempStock, MainScreenController.errorTracking);
            if (MainScreenController.errorTracking.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText(MainScreenController.errorTracking);
                alert.showAndWait();
                MainScreenController.errorTracking = "";
                MainScreenController.newPartID = MainScreenController.newPartID - 1;
            } else {

                Alert confirmSavePart = new Alert(Alert.AlertType.CONFIRMATION);
                confirmSavePart.setTitle("Save");
                confirmSavePart.setHeaderText("Confirm");
                confirmSavePart.setContentText("Are you sure you want to save?");
                Optional<ButtonType> outcome = confirmSavePart.showAndWait();

                if (outcome.get() == ButtonType.OK) {
                    if (inHouseRadio.isSelected()) {
                        int tempMachineId = Integer.parseInt(machineCompany.getText());
                        InHouse newInPart = new InHouse(tempId, tempName, tempPrice, tempStock, tempMin, tempMax, tempMachineId);
                        Inventory.addPart(newInPart);
                    }
                    if (outsourcedRadio.isSelected()) {
                        String tempCompanyName = machineCompany.getText();
                        OutSourced newOutPart = new OutSourced(tempId, tempName, tempPrice, tempStock, tempMin, tempMax, tempCompanyName);
                        Inventory.addPart(newOutPart);
                    }
                }

                Parent mainScreen = FXMLLoader.load(getClass().getResource("/view/Main_Screen.fxml"));
                Scene mainScreenScene = new Scene(mainScreen);
                Stage mainScreenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                mainScreenStage.setScene((mainScreenScene));
                mainScreenStage.show();
            }
        }
        catch (NumberFormatException e) {
            Alert blankFieldAlert = new Alert(Alert.AlertType.INFORMATION);
            blankFieldAlert.setTitle("Error");
            blankFieldAlert.setHeaderText("Error adding part");
            blankFieldAlert.setContentText("Blank fields are not allowed");
            blankFieldAlert.showAndWait();
        }
    }
    ToggleGroup sourceToggleGroup;
    {
        sourceToggleGroup = new ToggleGroup();
    }

    /** Select InHouse
     * This method changes certain label depending on radio button selection.
     * */
    @FXML
    void selectInHouse(MouseEvent event) {
        this.machineCompanyLabel.setText("Machine ID");
        this.inHouseRadio.setSelected(true);
        outsourcedRadio.setSelected(false);
        this.inHouseRadio.setToggleGroup(sourceToggleGroup);
    }
    /** Select Outsourced
     * This method changes certain labels depending on radio button selection.
     * */
    @FXML
    void selectOutsourced(MouseEvent event) {
        this.machineCompanyLabel.setText("Company Name");
        this.outsourcedRadio.setSelected(true);
        inHouseRadio.setSelected(false);
        this.outsourcedRadio.setToggleGroup(sourceToggleGroup);
    }


}
