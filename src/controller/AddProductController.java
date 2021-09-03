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

/** FXML Add Product Controller Class.
 * This class controllers the functionality of the add product screen.
 * @author Derek Mclaws.
 * */
public class AddProductController implements Initializable {

    @FXML
    private TextField addProductSearch;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField invField;

    @FXML
    private TextField priceField;

    @FXML
    private Label ID;

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
    private TableColumn<Part, Integer> partIDColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Integer> invLevelColumn;

    @FXML
    private TableColumn<Part, Double> priceCostColumn;

    @FXML
    private TableView<Part> associatedPartsTable;

    @FXML
    private TableColumn<Part, Integer> aPartIDColumn;

    @FXML
    private TableColumn<Part, String> aPartNameColumn;

    @FXML
    private TableColumn<Part, Integer> aInvLevelColumn;

    @FXML
    private TableColumn<Part, Double> aPriceCostColumn;

    @FXML
    private Button addProductButton;

    @FXML
    private Button addProductSaveButton;

    @FXML
    private Button addProductCancelButton;

    @FXML
    private Button removeAPartButton;

    @FXML
    private Button addProdSearchButton;

    private static final ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private ObservableList<Part> partInventory = FXCollections.observableArrayList();

    /** Initialize the controller class.
     * This initializes the add product screen controller class.
     * */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partsTable.setItems(Inventory.getAllParts());
        associatedPartsTable.setItems(associatedParts);


    }

    /** Add Product.
     * This method adds an associated part to the associatedParts table.
     * */
    @FXML
    void addProduct(MouseEvent event) {
        Part selectedAddPart = partsTable.getSelectionModel().getSelectedItem();
        associatedParts.add(selectedAddPart);
    }

    /** Cancel Product.
     * This method exits the add product screen.
     * */
    @FXML
    void addProductCancel(MouseEvent event) throws IOException {
        Alert addProdCancel = new Alert(Alert.AlertType.CONFIRMATION);
        addProdCancel.setTitle("Cancel");
        addProdCancel.setHeaderText("Confirm");
        addProdCancel.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> outcome = addProdCancel.showAndWait();


        if (outcome.get() == ButtonType.OK) {
            Parent mainScreen = FXMLLoader.load(getClass().getResource("/view/Main_Screen.fxml"));
            Scene mainScreenScene = new Scene(mainScreen);
            Stage mainScreenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainScreenStage.setScene((mainScreenScene));
            mainScreenStage.show();

        }
    }

    /** Save Product.
     * This method saves the new product into the allProducts list.
     * */
    @FXML
    void addProductSave(MouseEvent event) throws IOException {
        try {
            int tempId = MainScreenController.generateProductID();
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
                MainScreenController.newProductID = MainScreenController.newProductID - 100;
            } else {
                Alert confirmSavePart = new Alert(Alert.AlertType.CONFIRMATION);
                confirmSavePart.setTitle("Save");
                confirmSavePart.setHeaderText("Confirm");
                confirmSavePart.setContentText("Are you sure you want to save?");
                Optional<ButtonType> outcome = confirmSavePart.showAndWait();

                Product newProduct = new Product(tempId, tempName, tempPrice, tempStock, tempMin, tempMax);

                if (outcome.get() == ButtonType.OK) {

                    //for loop to grab all associated parts and add them with product.
                    for (int i = 0; i < associatedParts.size(); i++) {
                        Part tempPart = associatedParts.get(i);
                        newProduct.addAssociatedPart(tempPart);
                    }

                    Inventory.addProduct(newProduct);
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

    /** Remove Associated Parts
     * This method removes associated parts associated to selected product.
     * */
    @FXML
    void removeAssociatedPart(MouseEvent event) {

        Alert removeAPart = new Alert(Alert.AlertType.CONFIRMATION);
        removeAPart.initModality(Modality.NONE);
        removeAPart.setTitle("Delete");
        removeAPart.setHeaderText("Confirm");
        removeAPart.setContentText("Are you sure you want remove this associated part?");
        Optional<ButtonType> outcome = removeAPart.showAndWait();

        if (outcome.get() == ButtonType.OK) {
            Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();
            associatedParts.remove(selectedPart);
        }

    }
    /** Add Product Search.
     * This method searches for parts to be selected and added to associated parts list.
     * */
    @FXML
    void addProdSearch(MouseEvent event) {
        if (addProductSearch.getText().isEmpty() == true) {
            partsTable.setItems(Inventory.getAllParts());
        } else {
            partInventory = Inventory.lookupPart(addProductSearch.getText());
            partsTable.setItems(partInventory);
            try {
                Part searchID = Inventory.lookupPart(Integer.parseInt(addProductSearch.getText()));
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
                addProductSearch.setText("");
            }
        }
    }
}
















