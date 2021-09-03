package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/** FXML Modify Product Controller Class.
 * This class controllers the functionality of the modify product screen.
 * @author Derek Mclaws.
 * */
public class ModifyProductController implements Initializable {

    @FXML
    private TextField modifyProductSearch;

    @FXML
    private TextField iDField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField invField;

    @FXML
    private TextField priceField;

    @FXML
    private Label Id;

    @FXML
    private Label Name;

    @FXML
    private Label Inv;

    @FXML
    private Label Price;

    @FXML
    private Label Max;

    @FXML
    private TextField maxField;

    @FXML
    private Label Min;

    @FXML
    private TextField minField;

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> partID;

    @FXML
    private TableColumn<Part, String> partName;

    @FXML
    private TableColumn<Part, Integer> invLevel;

    @FXML
    private TableColumn<Part, Double> priceCost;

    @FXML
    private TableView<Part> associatedPartsTable;

    @FXML
    private TableColumn<Part, Integer> aPartID;

    @FXML
    private TableColumn<Part, String> aPartName;

    @FXML
    private TableColumn<Part, Integer> aInvLevel;

    @FXML
    private TableColumn<Part, Double> aPriceCost;

    @FXML
    private Button addPartButton;

    @FXML
    private Button modProdSaveButton;

    @FXML
    private Button modProdCancelButton;

    @FXML
    private Button removeAssociatedPart;

    private ObservableList<Part> partInventory = FXCollections.observableArrayList();


    /** Initialize the controller class.
     * This initializes the modify product screen controller class.
     * */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        partsTable.setItems(Inventory.getAllParts());
        associatedPartsTable.setItems(getCurrentAssociatedParts());


        iDField.setText(String.valueOf(getProductToBeModified().getId()));
        nameField.setText(String.valueOf(getProductToBeModified().getName()));
        invField.setText(String.valueOf(getProductToBeModified().getStock()));
        priceField.setText(String.valueOf(getProductToBeModified().getPrice()));
        maxField.setText(String.valueOf(getProductToBeModified().getMax()));
        minField.setText(String.valueOf(getProductToBeModified().getMin()));

    }
     /** Get Product to be modified.
      * This method retrieves the product that was selected to be used in other parts of program.
      * @return productToBeModified The product that is selected.
      * */
    private Product getProductToBeModified() {
        Product productToBeModified = Inventory.getAllProducts().get(MainScreenController.getModifyProductIndex());
        return productToBeModified;
    }
    /** Get Current Associated Parts.
     * This method gets the associated parts connected to the selected product.
     * @return currentAssociatedParts The list of parts connected to the product.
     * */
    private ObservableList<Part> getCurrentAssociatedParts() {
        ObservableList<Part> currentAssociatedParts = getProductToBeModified().getAllAssociatedParts();
        return currentAssociatedParts;

    }

    /** Add Associateed Part.
     * This method adds associated parts to the product.
     * */
    @FXML
    void addAssociatedPart(MouseEvent event) {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        getCurrentAssociatedParts().add(selectedPart);

    }

    /** Modify Product Cancel.
     * This method exits the modify product screen.
     * */
    @FXML
    void modProdCancel(MouseEvent event) throws IOException {
        Alert modProdCancel = new Alert(Alert.AlertType.CONFIRMATION);
        modProdCancel.setTitle("Cancel");
        modProdCancel.setHeaderText("Confirm");
        modProdCancel.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> outcome = modProdCancel.showAndWait();


        if (outcome.get() == ButtonType.OK) {
            Parent mainScreen = FXMLLoader.load(getClass().getResource("/view/Main_Screen.fxml"));
            Scene mainScreenScene = new Scene(mainScreen);
            Stage mainScreenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainScreenStage.setScene((mainScreenScene));
            mainScreenStage.show();

        }

    }

    /** Modify Product Save.
     * This method saves the changes to selected product.
     * */
    @FXML
    void modProdSave(MouseEvent event) throws IOException {
        try {
            int tempId = Integer.parseInt(iDField.getText());
            String tempName = nameField.getText();
            double tempPrice = Double.parseDouble(priceField.getText());
            int tempStock = Integer.parseInt(invField.getText());
            int tempMin = Integer.parseInt(minField.getText());
            int tempMax = Integer.parseInt(maxField.getText());

            MainScreenController.errorTracking = MainScreenController.minMaxCheck(tempMin, tempMax, tempStock, MainScreenController.errorTracking);
            if (MainScreenController.errorTracking.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText(MainScreenController.errorTracking);
                alert.showAndWait();
                MainScreenController.errorTracking = "";
            } else {
                Alert confirmSaveModPart = new Alert(Alert.AlertType.CONFIRMATION);
                confirmSaveModPart.setTitle("Save");
                confirmSaveModPart.setHeaderText("Confirm");
                confirmSaveModPart.setContentText("Are you sure you want to save?");
                Optional<ButtonType> outcome = confirmSaveModPart.showAndWait();

                    if (outcome.get() == ButtonType.OK) {
                        Product newProduct = new Product(tempId, tempName, tempPrice, tempStock, tempMin, tempMax);

                        //for loop to grab all associated parts and add them with product.
                        for (int i = 0; i < getCurrentAssociatedParts().size(); i++) {
                            Part tempPart = getCurrentAssociatedParts().get(i);
                            newProduct.addAssociatedPart(tempPart);
                        }

                    Inventory.updateProduct(MainScreenController.getModifyProductIndex(), newProduct);
                    associatedPartsTable.getItems().clear();

                    Parent mainScreen = FXMLLoader.load(getClass().getResource("/view/Main_Screen.fxml"));
                    Scene mainScreenScene = new Scene(mainScreen);
                    Stage mainScreenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    mainScreenStage.setScene((mainScreenScene));
                    mainScreenStage.show();
                }

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

    /** Remove Associated Part.
     * This method removes associated parts from the selected product.
     * */
    @FXML
    void removeAPart(MouseEvent event) throws IOException {

        Alert removeAPart = new Alert(Alert.AlertType.CONFIRMATION);
        removeAPart.initModality(Modality.NONE);
        removeAPart.setTitle("Delete");
        removeAPart.setHeaderText("Confirm");
        removeAPart.setContentText("Are you sure you want remove this associated part?");
        Optional<ButtonType> outcome = removeAPart.showAndWait();
        if (outcome.get() == ButtonType.OK) {
            Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();
            getCurrentAssociatedParts().remove(selectedPart);
        }
    }

    /** Modify Product Search.
     * This method searches parts to be added to the selected product.
     * */
    @FXML
    void searchModProd(MouseEvent event) throws IOException {
        if (modifyProductSearch.getText().isEmpty() == true) {
            partsTable.setItems(Inventory.getAllParts());
        } else {
            partInventory = Inventory.lookupPart(modifyProductSearch.getText());
            partsTable.setItems(partInventory);
            try {
                Part searchID = Inventory.lookupPart(Integer.parseInt(modifyProductSearch.getText()));
                if (searchID != null) {
                    partInventory.add(searchID);
                    partsTable.setItems(partInventory);
                }
            } catch (NumberFormatException e) {
                //ignore
            }

            if (partInventory.isEmpty()) {
                Alert doesNotExist = new Alert(Alert.AlertType.INFORMATION);
                doesNotExist.setTitle("Error!");
                doesNotExist.setHeaderText("Part not found");
                doesNotExist.setContentText("The search text entered did not match any parts");
                doesNotExist.showAndWait();
                partsTable.setItems(Inventory.getAllParts());
                modifyProductSearch.setText("");
            }
        }
    }
}