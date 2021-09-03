package controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import javafx.scene.input.MouseEvent;

/** FXML MainScreen Controller Class.
 * This class controllers the functionality of the main screen.
 * @author Derek Mclaws.
 * */
public class MainScreenController implements Initializable {

    @FXML
    private Button exitButton;

    @FXML
    private Button deletePartButton;

    @FXML
    private Button modifyPartButton;

    @FXML
    private Button addPartButton;

    @FXML
    private Button searchPartButton;

    @FXML
    private TextField searchPart;

    @FXML
    private TableView<Part> partTable;

    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Integer> partInvCountColumn;

    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    @FXML
    private Button deleteProductButton;

    @FXML
    private Button modifyProductButton;

    @FXML
    private Button addProductButton;

    @FXML
    private Button searchProductButton;

    @FXML
    private TextField searchProduct;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, Integer> productIdColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Integer> productInvCountColumn;

    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    //helper variables and functions
    private ObservableList<Part> partInventory = FXCollections.observableArrayList();
    private ObservableList<Product> productInventory = FXCollections.observableArrayList();
    private static int modifyPartIndex;
    private static int modifyProductIndex;
    public static int newPartID = 10;
    public static int newProductID = 500;
    public static String errorTracking;

    /**
     * Min Max Check.
     * This method validates that Min and Max are correct.
     *
     * @param min   The selected min.
     * @param max   The selected max.
     * @param stock The selected stock.
     * @param error Obsolete string.
     * @return error Message to be used as an error.
     */
    public static String minMaxCheck(int min, int max, int stock, String error) {
        //first statement needs to be false in order to move to second else if

        if (min > max) {
            error = "Minimum level cannot be less than the maximum level. Please change.";
            return error;
        } else if (stock < min || stock > max) {
            error = "Inventory value cannot be less than the minimum value or greater than the maximum value!";
            return error;
        } else {
            return error = "";
        }
    }


    /**
     * Part Lookup method.
     * This method gets the part to be searched as searches.
     * */
    public void getPartLookup() {
        String searchedPart = searchPart.getText();
        partInventory = Inventory.lookupPart(searchedPart);
    }

    /**
     * Product Lookup method.
     * This method gets the product to be searched and searches.
     * */
    public void getProductLookup() {
        String searchedProduct = searchProduct.getText();
        productInventory = Inventory.lookupProduct(searchedProduct);
    }

    /**
     * Product search by ID method.
     * This method gets selected product id to be searched and searches.
     *
     * @return productIDSearched The product found by id.
     */
    public Product productSearchByID() {
        int searchedProdID = Integer.parseInt(searchProduct.getText());
        Product productIDSearched = Inventory.lookupProduct(searchedProdID);
        return productIDSearched;
    }

    /**
     * Part search by ID method.
     * This method gets the selected part id to be searched and searches.
     *
     * @return partIDSearched The part found by id.
     */
    public Part partSearchByID() {
        int searchedPartID = Integer.parseInt(searchPart.getText());
        Part partIDSearched = Inventory.lookupPart(searchedPartID);
        return partIDSearched;
    }

    /**
     * Generate Part ID method.
     * This method generates a new ID for parts.
     *
     * @return newPartID The new part Id.
     */
    public static int generatePartID() {
        newPartID = newPartID + 1;
        return newPartID;
    }

    /**
     * Get Part ID method.
     * This method gets the new part ID.
     *
     * @return newPartID The newPartID public variable.
     */
    public static int getPartID() {
        return newPartID;
    }

    /**
     * Generate Product ID.
     * This method generates a new product id.
     *
     * @return newProductID The new product id.
     */
    public static int generateProductID() {
        newProductID = newProductID + 100;
        return newProductID;
    }

    /**
     * Get new Product ID.
     * This method gets the new product ID.
     *
     * @return newProductID The newProduct public variable.
     */
    public static int getNewProductID() {
        return newProductID;
    }

    /**
     * Get modify part index method.
     * This method gets the index of the part to be modified.
     *
     * @return modifyPartIndex The index of the part to be modified.
     */
    public static int getModifyPartIndex() {
        return modifyPartIndex;
    }

    /**
     * Get modify product index method.
     * This method gets the index of the product to be modified.
     *
     * @return modifyProductIndex The index of the product to be modified.
     */
    public static int getModifyProductIndex() {
        return modifyProductIndex;
    }

    /**
     * Initialize the controller class.
     * This initializes the main screen controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generatePartsTable();
        generateProductTable();
    }

    /**
     * Generate Parts Table method.
     * This method generates a parts table to add to tableview.
     */
    private void generatePartsTable() {
        partInventory.setAll(Inventory.getAllParts());
        partTable.setItems(partInventory);
        partTable.refresh();

    }

    /**
     * Generate Product Table method.
     * This method generates a product table to add to tableview.
     */
    private void generateProductTable() {
        productInventory.setAll(Inventory.getAllProducts());
        productTable.setItems(productInventory);
        productTable.refresh();
    }

    /**
     * Add Part method.
     * This method loads the add Part form stage.
     */
    @FXML
    private void addPart(MouseEvent event) throws IOException {

        Parent partsScreen = FXMLLoader.load(getClass().getResource("/view/Add_Part_Form.fxml"));
        Scene addPartScene = new Scene(partsScreen);
        Stage addPartStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addPartStage.setScene((addPartScene));
        addPartStage.show();

    }

    /**
     * Add Product method.
     * This method loads the add product stage.
     */
    @FXML
    void addProduct(MouseEvent event) throws IOException {

        Parent productScreen = FXMLLoader.load(getClass().getResource("/view/Add_Product_Form.fxml"));
        Scene addProductScene = new Scene(productScreen);
        Stage addProductStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addProductStage.setScene((addProductScene));
        addProductStage.show();
    }

    /**
     * Delete Part method.
     * This method deletes a selected part.
     */
    @FXML
    void deletePart(MouseEvent event) throws IOException {
        Part itemSelected = partTable.getSelectionModel().getSelectedItem();
        int deletePartIndex = Inventory.getAllParts().indexOf(itemSelected);

        if (deletePartIndex == -1) {
            Alert noneSelected = new Alert(Alert.AlertType.INFORMATION);
            noneSelected.setTitle("Error!");
            noneSelected.setHeaderText("No part selected");
            noneSelected.setHeaderText("Please select a part");
            noneSelected.showAndWait();
        } else {

            Alert deletePartConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
            deletePartConfirmation.initModality(Modality.NONE);
            deletePartConfirmation.setTitle("Delete");
            deletePartConfirmation.setHeaderText("Confirm Part Delete");
            deletePartConfirmation.setContentText("Are you sure you want to delete this part?");
            Optional<ButtonType> outcome = deletePartConfirmation.showAndWait();

            if (outcome.get() == ButtonType.OK) {
                Boolean didDelete = Inventory.deletePart(partTable.getSelectionModel().getSelectedItem());
                generatePartsTable();

                if (didDelete == false) {
                    Alert didNotDelete = new Alert(Alert.AlertType.INFORMATION);
                    didNotDelete.setTitle("Error!");
                    didNotDelete.setHeaderText("Part not deleted");
                    didNotDelete.setHeaderText("That part was not deleted");
                    didNotDelete.showAndWait();
                }
            }
        }
    }

    /**
     * Delete Product method.
     * This method deletes a selected product.
     */
    @FXML
    void deleteProduct(MouseEvent event) {

        Product itemSelected = productTable.getSelectionModel().getSelectedItem();
        int deleteProductIndex = Inventory.getAllProducts().indexOf(itemSelected);
        if (deleteProductIndex == -1) {
            Alert noneSelected = new Alert(Alert.AlertType.INFORMATION);
            noneSelected.setTitle("Error!");
            noneSelected.setHeaderText("No product selected");
            noneSelected.setHeaderText("Please select a product");
            noneSelected.showAndWait();
        } else {

            ObservableList<Part> associatedPartsDelete = itemSelected.getAllAssociatedParts();

            if (associatedPartsDelete.size() != 0) {
                Alert noDeleteWithParts = new Alert(Alert.AlertType.INFORMATION);
                noDeleteWithParts.setTitle("Error");
                noDeleteWithParts.setHeaderText("Cannot delete selected item");
                noDeleteWithParts.setContentText("You cannot delete a product that has associated parts. Please go modify product and remove all associated parts");
                noDeleteWithParts.showAndWait();
            } else {

                Alert deleteProductConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
                deleteProductConfirmation.initModality(Modality.NONE);
                deleteProductConfirmation.setTitle("Delete");
                deleteProductConfirmation.setHeaderText("Confirm Product Delete");
                deleteProductConfirmation.setContentText("Are you sure you want to delete this product?");
                Optional<ButtonType> outcome = deleteProductConfirmation.showAndWait();

                if (outcome.get() == ButtonType.OK) {

                    Boolean didDelete = Inventory.deleteProduct(productTable.getSelectionModel().getSelectedItem());
                    generateProductTable();
                    if (didDelete == false) {
                        Alert didNotDelete = new Alert(Alert.AlertType.INFORMATION);
                        didNotDelete.setTitle("Error!");
                        didNotDelete.setHeaderText("Product not deleted");
                        didNotDelete.setHeaderText("That product was not deleted");
                        didNotDelete.showAndWait();
                    }
                }
            }
        }
    }

    /**
     * Exit method.
     * This method exits the program.
     */
    @FXML
    void exit(MouseEvent event) {
        Alert deletePartConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        deletePartConfirmation.initModality(Modality.NONE);
        deletePartConfirmation.setTitle("Exit");
        deletePartConfirmation.setHeaderText("Exit Program");
        deletePartConfirmation.setContentText("Are you sure you want to exit the program?");
        Optional<ButtonType> outcome = deletePartConfirmation.showAndWait();

        if (outcome.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    /**
     * Modify Part method.
     * This gets a selected part and loads the modify part window.
     */
    @FXML
    void modifyPart(MouseEvent event) throws IOException {
        Part tempPart = partTable.getSelectionModel().getSelectedItem();
        modifyPartIndex = Inventory.getAllParts().indexOf(tempPart);

        if (modifyPartIndex == -1) {
            Alert noneSelected = new Alert(Alert.AlertType.INFORMATION);
            noneSelected.setTitle("Error!");
            noneSelected.setHeaderText("No part selected");
            noneSelected.setHeaderText("Please select a part");
            noneSelected.showAndWait();
        } else {

            Parent modPartsScreen = FXMLLoader.load(getClass().getResource("/view/Modify_Part_Form.fxml"));
            Scene modPartsScene = new Scene(modPartsScreen);
            Stage modPartsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            modPartsStage.setScene((modPartsScene));
            modPartsStage.show();
        }

    }

    /**
     * Modify Product method.
     * This method gets a selected product and loads teh modify product window. i
     */
    @FXML
    void modifyProduct(MouseEvent event) throws IOException {
        Product tempProduct = productTable.getSelectionModel().getSelectedItem();
        modifyProductIndex = Inventory.getAllProducts().indexOf(tempProduct);


        if (modifyProductIndex == -1) {
            Alert noneSelected = new Alert(Alert.AlertType.INFORMATION);
            noneSelected.setTitle("Error!");
            noneSelected.setHeaderText("No product selected");
            noneSelected.setHeaderText("Please select a product");
            noneSelected.showAndWait();
        } else {

            Parent modProductScreen = FXMLLoader.load(getClass().getResource("/view/Modify_Product_Form.fxml"));
            Scene modProductScene = new Scene(modProductScreen);
            Stage modProductStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            modProductStage.setScene(modProductScene);
            modProductStage.show();
        }


    }

    /**
     * Search Part method.
     * This method uses various other methods to preform a search.
     *
     * RUNTIME ERROR I kept having issues with this method working correctly. I wanted to use the lookup methods that
     * were created in the Inventory class, but I kept having problems with it only searching the ID or only searching
     * the part name. I created helper methods in this main screen controller to break the process down and separate
     * some of the work being done. This helped keep the search types from interfering with each other. I still ran
     * into a number format exception when a value was entered that could not be parsed into an integer. I used a try
     * and catch statement to exclude it. Now it works great, and with my limited knowledge I don't foresee any logical
     * errors that may arise as parts are added and changed.
     * @param event The mouse click event.
     */
    @FXML
    public void searchPart(MouseEvent event) {
        if (searchPart.getText().isEmpty() == true) {
            generatePartsTable();
        } else {
            getPartLookup();
            partTable.setItems(partInventory);
            try {
                Part searchID = partSearchByID();
                if (searchID != null) {
                    partInventory.add(searchID);
                    partTable.setItems(partInventory);
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
                generatePartsTable();
                searchPart.setText("");
            }
        }

    }


    /**
     * Search Product.
     * This method uses various other methods to preform product search.
     */
    @FXML
    void searchProduct(MouseEvent event) {
        if (searchProduct.getText().isEmpty()) {
            generateProductTable();
        } else {
            getProductLookup();
            productTable.setItems(productInventory);
            try {
                Product tempSearchProduct = productSearchByID();
                if (tempSearchProduct != null) {
                    productInventory.add(tempSearchProduct);
                    productTable.setItems(productInventory);
                }
            } catch (NumberFormatException e) {
                //ignore
            }
            if (productInventory.isEmpty()) {
                Alert doesNotExist = new Alert(Alert.AlertType.INFORMATION);
                doesNotExist.setTitle("Error!");
                doesNotExist.setHeaderText("Product not found");
                doesNotExist.setContentText("The search text entered did not match any products");
                doesNotExist.showAndWait();
                generateProductTable();
                searchProduct.setText("");
            }

        }
    }
}


