package controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/** FXML Modify Part Controller Class.
 * This class controllers the functionality of the modify part screen.
 * @author Derek Mclaws.
 * */
public class ModifyPartController implements Initializable {

    @FXML
    private RadioButton modifyInHouseRadio;

    @FXML
    private RadioButton modifyOutsourcedRadio;

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
    private Label companyLabel;

    @FXML
    private TextField machineID;

    @FXML
    private Button modifyPartSaveButton;

    @FXML
    private Button cancelModifyPartButton;

    /** Initialize the controller class.
     * This initializes the modify part screen controller class.
     * */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Part partToBeModified = Inventory.getAllParts().get(MainScreenController.getModifyPartIndex());

        id.setText(String.valueOf(partToBeModified.getPartID()));
        name.setText(String.valueOf(partToBeModified.getPartName()));
        inv.setText(String.valueOf(partToBeModified.getPartInStock()));
        price.setText(String.valueOf(partToBeModified.getPartPrice()));
        max.setText(String.valueOf(partToBeModified.getMax()));
        min.setText(String.valueOf(partToBeModified.getMin()));

        if(partToBeModified instanceof InHouse) {
            companyLabel.setText("Machine ID");
            machineID.setText(String.valueOf(((InHouse) partToBeModified).getMachineID()));
            modifyInHouseRadio.setSelected(true);
            modifyOutsourcedRadio.setSelected(false);
        }
        else if(partToBeModified instanceof OutSourced) {
            companyLabel.setText("Company Name");
            machineID.setText(String.valueOf(((OutSourced) partToBeModified).getCompanyName()));
            modifyInHouseRadio.setSelected(false);
            modifyOutsourcedRadio.setSelected(true);
        }
    }

    /** Cancel Modify Part.
     * This method exits the modify part screen.
     * */
    @FXML
    void cancelModifiedPart(MouseEvent event) throws IOException {
        Alert modPartCancel = new Alert(Alert.AlertType.CONFIRMATION);
        modPartCancel.setTitle("Cancel");
        modPartCancel.setHeaderText("Confirm");
        modPartCancel.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> outcome = modPartCancel.showAndWait();


        if(outcome.get() == ButtonType.OK) {
            Parent mainScreen = FXMLLoader.load(getClass().getResource("/view/Main_Screen.fxml"));
            Scene mainScreenScene = new Scene(mainScreen);
            Stage mainScreenStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            mainScreenStage.setScene((mainScreenScene));
            mainScreenStage.show();

        }

    }

    /** Save Modified Part.
     * This method saves the changes to the selected part to the all parts list.
     * */
    @FXML
    void saveModifiedPart(MouseEvent event) throws IOException{
        //need to create a method here that checks for outsourced or inhouse
        try {
            int tempId = Integer.parseInt(id.getText());
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
            } else {

                Alert confirmModPart = new Alert(Alert.AlertType.CONFIRMATION);
                confirmModPart.setTitle("Save");
                confirmModPart.setHeaderText("Confirm");
                confirmModPart.setContentText("Are you sure you want to save?");
                Optional<ButtonType> outcome = confirmModPart.showAndWait();

                if (outcome.get() == ButtonType.OK) {
                    if (modifyInHouseRadio.isSelected()) {
                        int tempMachineId = Integer.parseInt(machineID.getText());
                        InHouse newInPart = new InHouse(tempId, tempName, tempPrice, tempStock, tempMin, tempMax, tempMachineId);
                        Inventory.updatePart(MainScreenController.getModifyPartIndex(), newInPart);
                    }
                    if (modifyOutsourcedRadio.isSelected()) {
                        String tempCompanyName = machineID.getText();
                        OutSourced newOutPart = new OutSourced(tempId, tempName, tempPrice, tempStock, tempMin, tempMax, tempCompanyName);
                        Inventory.updatePart(MainScreenController.getModifyPartIndex(), newOutPart);
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

    /** Select InHouse.
     * This method changes labels depending on whether the InHouse radio is selected.
     * */
        @FXML
    void selectInHouse(MouseEvent event) {
        this.companyLabel.setText("Machine ID");
        this.modifyInHouseRadio.setSelected(true);
        modifyOutsourcedRadio.setSelected(false);
        this.modifyInHouseRadio.setToggleGroup(sourceToggleGroup);
    }

    /** Select Outsourced.
     * This method changes labels depending on whether the Outsourced radio is selected.
     * */
    @FXML
    void selectOutsourced(MouseEvent event) {
        this.companyLabel.setText("Company Name");
        this.modifyOutsourcedRadio.setSelected(true);
        modifyInHouseRadio.setSelected(false);
        this.modifyOutsourcedRadio.setToggleGroup(sourceToggleGroup);

    }
}
